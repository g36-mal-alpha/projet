package projet.view.poste;

import java.time.LocalDate;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoBenevole;
import projet.dao.DaoPoste;
import projet.data.Benevole;
import projet.data.Categorie;
import projet.data.Poste;
import projet.view.personne.ModelCategorie;


public class ModelPoste  {
	
	
	// Données observables 
	
	private final ObservableList<Poste> liste = FXCollections.observableArrayList(); 
	
	private final ObservableList<Benevole> benevolesPourDialogAjout = FXCollections.observableArrayList();

	
	private final Poste	courant = new Poste();

	
	// Autres champs
    @Inject
	private IMapper			mapper;
    @Inject
	private DaoPoste	    daoPoste;
    @Inject
	private DaoBenevole	    daoBenevole;
    @Inject
    private ModelCategorie  modelCategorie;
	
	
	// Getters 
	
	public Poste getCourant() {
		return courant;
	}
	
	public ObservableList<Poste> getListe() {
		return liste;
	}
	
	public ObservableList<Categorie> getCategorie() {
		 return modelCategorie.getListe();
	}
	
	public ObservableList<Benevole> getBenevolesesPourDialogAjout() {
		 return benevolesPourDialogAjout;
	}

	
	// Actualisations
	
	public void actualiserListe() {
		liste.setAll( daoPoste.listerTout() );
 	}

	public void  actualiserListeBenevolesPourDialogAjout() {
		liste.setAll( daoBenevole.benevolesPourDialogAjout());
		benevolesPourDialogAjout.removeAll( courant.getBenevoles() );

 	}

	// Actions
	
	public void preparerAjouter() {
		modelCategorie.actualiserListe();
		mapper.update( courant, new Poste() );
	}
	
	public void preparerModifier( Poste item ) {
		modelCategorie.actualiserListe();
		mapper.update( courant, daoPoste.retrouver( item.getId() ) );
	}
	
	
	public void validerMiseAJour() {

		// Vérifie la validité des données
		
		StringBuilder message = new StringBuilder();

		if( courant.getLibelle() == null || courant.getLibelle().isEmpty() ) {
			message.append( "\nLe libelle du poste ne doit pas être vide." );
		} else  if ( courant.getLibelle().length()> 50 ) {
			message.append( "\nLe mémo est trop long : 50 maxi." );
		}
		
		if( courant.getLieu() == null || courant.getLieu().isEmpty() ) {
			message.append( "\nLe lieu du poste ne doit pas être vide." );
		} else  if ( courant.getLieu().length()> 50 ) {
			message.append( "\nLe lieu est trop long : 50 maxi." );
		}
		
		if( courant.getJour() != null) {
			
			LocalDate mini=LocalDate.of(2000, 01, 01);
			LocalDate maxi=LocalDate.of(2099, 12, 31);
	
			if(courant.getJour().isBefore(mini))
			{
				message.append( "\nLe jour doit être comprise entre le 01/01/2000 et le 31/12/2099." );
			}		
			if(courant.getJour().isAfter(maxi)) {
				message.append( "\nLe jour doit être comprise entre le 01/01/2000 et le 31/12/2099." );
			}
			
		}
		
		if ( message.length() > 0 ) {
			throw new ExceptionValidation( message.toString().substring(1) );
		}
		
		
		// Effectue la mise à jour
		
		if ( courant.getId() == null ) {
			// Insertion
			courant.setId( daoPoste.inserer( courant ) );
		} else {
			// modficiation
			daoPoste.modifier( courant );
		}
	}
	
	
	public void supprimer( Poste item ) {
		
		// Vérifie l'abence de personnes rattachées à la catégorie
		daoPoste.supprimer( item.getId() );
		mapper.update( courant, UtilFX.findNext( liste, item ) );
	}
	
	public void supprimerBenevole( Benevole item ) {
		 courant.getBenevoles().remove( item );
	}
		
	public void ajouterBenevole( Benevole item ) {
		 courant.getBenevoles().add( item );
	}
}
