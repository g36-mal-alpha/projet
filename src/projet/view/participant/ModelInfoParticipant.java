package projet.view.participant;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import projet.data.Participant;

public class ModelInfoParticipant {
	
	// DonnÃ©es observables 
	
	private final Property<String>	nom	= new SimpleObjectProperty<>();
	private final Property<String>	poste	= new SimpleObjectProperty<>();
	private final Property<String>	prenom	= new SimpleObjectProperty<>();
	private final Property<String>	telephone	= new SimpleObjectProperty<>();
	private final Property<String>	adressemail	= new SimpleObjectProperty<>();
	private final Property<String>	adresse	= new SimpleObjectProperty<>();
	private final Property<String>	course	= new SimpleObjectProperty<>();
	private final Property<String>	permis	= new SimpleObjectProperty<>();
	private final Property<String>	majeur	= new SimpleObjectProperty<>();
	private final Property<String>	certificat	= new SimpleObjectProperty<>();
	private final Property<String>	cout	= new SimpleObjectProperty<>();

	// Getters 
	
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
		return adressemail;
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



}
