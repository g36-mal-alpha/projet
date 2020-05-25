package projet.view.participant;

import java.time.LocalDate;

import javax.inject.Inject;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoParticipant;
import projet.data.Participant;




public class ModelInfoParticipant {
	
	// DonnÃ©es observables 
	private final Participant		courantParticipant = new Participant();
	private final ObservableList<Participant> liste = FXCollections.observableArrayList(); 
	
	private final Property<String>	nom	= new SimpleObjectProperty<>();
	private final Property<String>	poste	= new SimpleObjectProperty<>();
	private final Property<String>	prenom	= new SimpleObjectProperty<>();
	private final Property<String>	telephone	= new SimpleObjectProperty<>();
	private final Property<String>	mail	= new SimpleObjectProperty<>();
	private final Property<String>	adresse	= new SimpleObjectProperty<>();
	private final Property<String>	course	= new SimpleObjectProperty<>();
	private final Property<String>	permis	= new SimpleObjectProperty<>();
	private final Property<String>	majeur	= new SimpleObjectProperty<>();
	private final Property<String>	certificat	= new SimpleObjectProperty<>();
	private final Property<String>	cout	= new SimpleObjectProperty<>();

	//Autre champs
	
    @Inject
	private IMapper		        mapper;
    @Inject
	private DaoParticipant			daoParticipant;
    
	// Getters 
	public ObservableList<Participant> getListe() {
		return liste;
	}
	
	public Participant getCourantParticipant() {
		return courantParticipant;
	} 
	
	public Property<String> nomProperty() {
		return nom;
	}
	
	public Property<String> posteProperty() {
		return poste;
	}
	
	public Property<String> prenomProperty() {
		return prenom;
	}
	
	public Property<String> telephoneProperty() {
		return telephone;
	}
	
	public Property<String> adressemailProperty() {
		return mail;
	}
	
	public Property<String> adresseProperty() {
		return adresse;
	}
	
	public Property<String> courseProperty() {
		return course;
	}
	
	public Property<String> permisProperty() {
		return permis;
	}
	
	public Property<String> majeurProperty() {
		return majeur;
	}
	
	public Property<String> certificatProperty() {
		return certificat;
	}
	
	public Property<String> coutProperty() {
		return cout;
	}


	// Actualisations
	
	public void actualiserListe() {
		liste.setAll( daoParticipant.listerTout() );
 	}


	
	public void preparerAjouter() {
		mapper.update( courantParticipant, new Participant() );
	}
	
	public void preparerModifier( Participant item ) {
		mapper.update( courantParticipant, daoParticipant.retrouver( item.getId() ) );
	}

	
	public void validerMiseAJour() {
		// Effectue la mise à jour
		
		if ( courantParticipant.getId() == null ) {
			// Insertion
			courantParticipant.setId( daoParticipant.inserer( courantParticipant ) );
		} else {
			// modficiation
			daoParticipant.modifier( courantParticipant );
		}
	}
	
	
	public void supprimer( Participant item ) 
	{
		
		daoParticipant.supprimer( item.getId() );
		System.out.println( UtilFX.findNext( liste, item ) );
		mapper.update( courantParticipant, UtilFX.findNext( liste, item ) );
	}
	
}
