package projet.view.participant;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

public class ModelInfoParticipant {
	
	// DonnÃ©es observables 
	
	private final Property<String>	nom	= new SimpleObjectProperty<>();
	private final Property<String>	message	= new SimpleObjectProperty<>();
	

	// Getters 
	
	public Property<String> nomProperty() {
		return nom;
	}
	
	public Property<String> messageProperty() {
		return message;
	}
	

}
