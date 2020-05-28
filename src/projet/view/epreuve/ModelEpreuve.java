package projet.view.epreuve;

import java.time.LocalDate;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoEpreuve;
import projet.data.Epreuve;


public class ModelEpreuve  {
	
	
	// Données observables 
	
	private final ObservableList<Epreuve> liste = FXCollections.observableArrayList(); 
	
	private final Epreuve	courant = new Epreuve();

	
	// Autres champs
    @Inject
	private IMapper			mapper;
    @Inject
	private DaoEpreuve		daoEpreuve;
	
	
	// Getters 
	
	public ObservableList<Epreuve> getListe() {
		return liste;
	}
	
	public Epreuve getCourant() {
		return courant;
	}
	
	
	// Actualisations
	
	public void actualiserListe() {
		liste.setAll( daoEpreuve.listerTout() );
 	}


	// Actions
	
	public void preparerAjouter() {
		mapper.update( courant, new Epreuve() );
	}
	
	public void preparerModifier( Epreuve item ) {
		mapper.update( courant, daoEpreuve.retrouver( item.getId() ) );
	}
	
	
	public void validerMiseAJour() {

		// Vérifie la validité des données
		
		StringBuilder message = new StringBuilder();

		if( courant.getNom_epreuve() == null || courant.getNom_epreuve().isEmpty() ) {
			message.append( "\nLe nom ne doit pas être vide." );
		} else  if ( courant.getNom_epreuve().length()> 50 ) {
			message.append( "\nLe nom est trop long : 50 maxi." );
		}

		if( courant.getTarif() != null ) {
			if (courant.getTarif() > 100 ) {
				message.append( "\nAttention prix trop élevé !!" );
			}
		}
		
		if( courant.getLieu() == null || courant.getLieu().isEmpty() ) {
			message.append( "\nLe lieu du poste ne doit pas être vide." );
		} else  if ( courant.getLieu().length()> 50 ) {
			message.append( "\nLe lieu est trop long : 50 maxi." );
		}
		
		if( courant.getDate_epreuve() != null) {
			
			LocalDate mini=LocalDate.of(2000, 01, 01);
			LocalDate maxi=LocalDate.of(2099, 12, 31);
	
			if(courant.getDate_epreuve().isBefore(mini))
			{
				message.append( "\nLe jour doit être comprise entre le 01/01/2000 et le 31/12/2099." );
			}		
			if(courant.getDate_epreuve().isAfter(maxi)) {
				message.append( "\nLe jour doit être comprise entre le 01/01/2000 et le 31/12/2099." );
			}
			
		}
		
		if ( message.length() > 0 ) {
			throw new ExceptionValidation( message.toString().substring(1) );
		}
		
		
		// Effectue la mise à jour
		
		if ( courant.getId() == null ) {
			// Insertion
			courant.setId( daoEpreuve.inserer( courant ) );
		} else {
			// modficiation
			daoEpreuve.modifier( courant );
		}
	}
	
	
	public void supprimer( Epreuve item ) {
		
		daoEpreuve.supprimer( item.getId() );
		System.out.println( UtilFX.findNext( liste, item ) );
		mapper.update( courant, UtilFX.findNext( liste, item ) );
	}
	
}