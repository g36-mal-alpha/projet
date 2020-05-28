package projet.view.equipe;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoService;
import projet.data.Service;


public class ModelService  {
	
	
	// Données observables 
	
	private final ObservableList<Service> liste = FXCollections.observableArrayList(); 
	
	private final Service	courant = new Service();

	
	// Autres champs
    @Inject
	private IMapper			mapper;
    @Inject
	private DaoService		daoService;
	
	
	// Getters 
	
	public ObservableList<Service> getListe() {
		return liste;
	}
	
	public Service getCourant() {
		return courant;
	}
	
	
	// Actualisations
	
	public void actualiserListe() {
		liste.setAll( daoService.listerTout() );
 	}


	// Actions
	
	public void preparerAjouter() {
		mapper.update( courant, new Service() );
	}
	
	public void preparerModifier( Service item ) {
		mapper.update( courant, daoService.retrouver( item.getId() ) );
	}
	
	
	public void validerMiseAJour() {

		// Vérifie la validité des données
		
		StringBuilder message = new StringBuilder();

		if( courant.getNom() == null || courant.getNom().isEmpty() ) {
			message.append( "\nLe nom ne doit pas être vide." );
		} else  if ( courant.getNom().length()> 50 ) {
			message.append( "\nLe nom est trop long : 50 maxi." );
		}

		if( courant.getAnneeCreation() != null ) {
			if ( courant.getAnneeCreation() < 1900  
					|| courant.getAnneeCreation() > 2100 ) {
				message.append( "\nValeur incorrecte pour l'année de création." );
			}
		}
		
		if ( message.length() > 0 ) {
			throw new ExceptionValidation( message.toString().substring(1) );
		}
		
		
		// Effectue la mise à jour
		
		if ( courant.getId() == null ) {
			// Insertion
			courant.setId( daoService.inserer( courant ) );
		} else {
			// modficiation
			daoService.modifier( courant );
		}
	}
	
	
	public void supprimer( Service item ) {
		
		daoService.supprimer( item.getId() );
		System.out.println( UtilFX.findNext( liste, item ) );
		mapper.update( courant, UtilFX.findNext( liste, item ) );
	}
	
}
