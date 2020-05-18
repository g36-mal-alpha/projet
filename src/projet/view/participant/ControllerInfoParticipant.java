package projet.view.participant;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class ControllerInfoParticipant {
	// Composants de la vue
	
	@FXML
	private Label		textNom;
	@FXML


	
	// Autres champs
	
	@Inject
	private ModelInfoParticipant	modelInfoParticipant;
	
	
	// Initialisation
	
	@FXML
	private void initialize() {
		
		// Data binding
		textNom.textProperty().bind( modelInfoParticipant.nomProperty() );
		modelInfoParticipant.nomProperty().setValue( "Bienvenue" );
	
		
	}

}
