package projet.view.participant;


import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoParticipant;
import projet.data.Participant;



public class ModelParticipant  {
	
	
	// Données observables 
	
	private final ObservableList<Participant> liste = FXCollections.observableArrayList(); 
	
	private final Participant	courant = new Participant();
	

	
	// Autres champs
    @Inject
	private IMapper			mapper;
    
    @Inject
   	private DaoParticipant	    daoParticipant;

	
	// Getters 
	
	public ObservableList<Participant> getListe() {
		return liste;
	}
	
	public Participant getCourant() {
		return courant;
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
		/*
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
		
		*/
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
