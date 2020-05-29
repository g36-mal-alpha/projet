package projet.view.participant;


import java.time.LocalDate;

import javax.inject.Inject;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoParticipant;
import projet.data.Participant;
import projet.data.Sexe;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;


public class ModelParticipant  {
	
	
	// Données observables 
	
	private final ObservableList<Participant> liste = FXCollections.observableArrayList(); 
	
	private final Participant	courant = new Participant();
	
	
	// Autres champs
    @Inject
	private IMapper			mapper;
    
    @Inject
   	private DaoParticipant	    daoParticipant;
    @Inject
    private ModelSexe		modelSexe;
	
	// Getters 
	
	public ObservableList<Participant> getListe() {
		return liste;
	}
	
	public Participant getCourant() {
		return courant;
	}	
	
	public ObservableList<Sexe> getSexe() {
		return modelSexe.getListe();
	}

	
	// Actualisations
	
		public void actualiserListe() {
			liste.setAll( daoParticipant.listerTout() );
	 	}


		// Actions
		
		public void preparerAjouter() {
			mapper.update( courant, new Participant() );
		}
		
		public void preparerModifier( Participant item ) {
			mapper.update( courant, daoParticipant.retrouver( item.getId() ) );
		}
		
	
	
	public void validerMiseAJour() {

		// Vérifie la validité des données
		
	StringBuilder message = new StringBuilder();
	
	//Nom
	if( courant.getNom() == null || courant.getNom().isEmpty() ) {
		message.append( "\nLe nom ne doit pas être vide." );
	} else  if ( courant.getNom().length()> 50 ) {
		message.append( "\nLe nom est trop long." );
	}
	
	//Prenom
	if( courant.getPrenom() == null || courant.getPrenom().isEmpty() ) {
		message.append( "\nLe prenom ne doit pas être vide." );
	} else  if ( courant.getPrenom().length()> 50 ) {
		message.append( "\nLe prenom est trop long." );
	}
	
	//Numero de téléphone
	if( courant.getNumero_tel() == null || courant.getNumero_tel().isEmpty() ) {
		message.append( "\nLe nom ne doit pas être vide." );
	} else  if ( courant.getNumero_tel().length()> 10 ) {
		message.append( "\nLe numero de téléphone est trop long." );
	}
			
	//Date de naissance	
		if( courant.getDate_naissance() != null) {
			
			LocalDate mini=LocalDate.of(1950, 01, 01);
			LocalDate maxi=LocalDate.of(2099, 12, 31);
	
			if(courant.getDate_naissance().isBefore(mini))
			{
				message.append( "\nLe jour doit être comprise entre le 01/01/2000 et le 31/12/2099." );
			}		
			if(courant.getDate_naissance().isAfter(maxi)) {
				message.append( "\nLe jour doit être comprise entre le 01/01/2000 et le 31/12/2099." );
			}
			
			//Mail
			if( courant.getMail() == null || courant.getMail().isEmpty() ) {
				message.append( "\nL'adresse e-mail ne doit pas être vide." );
			} else  if ( courant.getMail().length()> 100 ) {
				message.append( "\nL'adresse e-mail est trop longue : 100 maxi." );
			}
			
		}
		
		if ( message.length() > 0 ) {
			throw new ExceptionValidation( message.toString().substring(1) );
		}
		
		// Effectue la mise à jour
		
		if ( courant.getId() == null ) {
			// Insertion
			courant.setId( daoParticipant.inserer( courant ) );
		} else {
			// modficiation
			daoParticipant.modifier( courant );
		}
	}


	
	public void supprimer( Participant item ) {
		
		// Vérifie l'abence de personnes rattachées à la catégorie
		daoParticipant.supprimer( item.getId() );
		mapper.update( courant, UtilFX.findNext( liste, item ) );
	}
	
	/*public void supprimerBenevole( Benevole item ) {
		 courant.getBenevoles().remove( item );
	}
		
	public void ajouterBenevole( Benevole item ) {
		 courant.getBenevoles().add( item );
	}*/
}
