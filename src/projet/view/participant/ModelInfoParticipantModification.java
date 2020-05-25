package projet.view.participant;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoParticipant;
import projet.data.Participant;

public class ModelInfoParticipantModification {
	
	
	// DonnÃ©es observables 
	
	private final ObservableList<Participant> liste = FXCollections.observableArrayList(); 
	
	private final Participant	courantParticipant = new Participant();

	
	// Autres champs
    @Inject
	private IMapper			mapper;
    @Inject
	private DaoParticipant	daoParticipant;
	
	
	// Getters 
	
	public ObservableList<Participant> getListe() {
		return liste;
	}
	
	public Participant getCourantParticipant() {
		return courantParticipant;
	}
	
	
	// Actualisations
	
	public void actualiserListe() {
		liste.setAll( daoParticipant.listerTout() );
 	}

	
	// Actions
	
	public void preparerAjouter() {
		mapper.update( courantParticipant, new Participant() );
	}
	
	public void preparerModifier( Participant item ) {
		mapper.update( courantParticipant, daoParticipant.retrouver( item.getId() ) );
	}
	
	
	public void validerMiseAJour() {
	
		// Effectue la mise Ã  jour
		
		if ( courantParticipant.getId() == null ) {
			// Insertion
			courantParticipant.setId( daoParticipant.inserer( courantParticipant ) );
		} else {
			// modficiation
			daoParticipant.modifier( courantParticipant );
		}
	}
	
	
	public void supprimer( Participant item ) {
		
		// VÃ©rifie l'abence de personnes rattachÃ©es Ã  la catÃ©gorie
		
		
		daoParticipant.supprimer( item.getId() );
		//mapper.update( Participant, UtilFX.findNext( liste, item ) );
	}
	

}
