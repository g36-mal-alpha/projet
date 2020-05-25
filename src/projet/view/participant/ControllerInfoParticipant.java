package projet.view.participant;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import jfox.javafx.view.IManagerGui;
import projet.data.Participant;


public class ControllerInfoParticipant {
	// Composants de la vue
	
	@FXML
	private Label		labelNom;
	@FXML
	private Label		labelPoste;
	@FXML
	private Label		labelPrenom;
	@FXML
	private Label		labelTelephone;
	@FXML
	private Label		labelAdresseMail;
	@FXML
	private Label		labelAdresse;
	@FXML
	private Label		labelCourse;
	@FXML
	private Label		labelPermis;
	@FXML
	private Label		labelMajeur;
	@FXML
	private Label		labelCertificat;
	@FXML
	private Label		labelCout;
		
	// Autres champs
	
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelInfoParticipant	modelInfoParticipant;
	
	
	// Initialisation
	
	@FXML
	private void initialize() {
		
		Participant courantParticipant = modelInfoParticipant.getCourantParticipant();
		
		// Data binding

		labelNom.textProperty().bind(modelInfoParticipant.nomProperty());
		/* labelPoste.textProperty().bindBidirectional(courantParticipant.adresseProperty());

		
		labelPrenom.textProperty().bind( modelInfoParticipant.prenomProperty() );
		modelInfoParticipant.prenomProperty().setValue( "Prenom : " );
		
		labelTelephone.textProperty().bind( modelInfoParticipant.telephoneProperty() );
		modelInfoParticipant.telephoneProperty().setValue( "Téléphone : " );
		
		labelAdresseMail.textProperty().bind( modelInfoParticipant.adressemailProperty() );
		modelInfoParticipant.adressemailProperty().setValue( "Adresse Mail : " );
		
		labelAdresse.textProperty().bind( modelInfoParticipant.adresseProperty() );
		modelInfoParticipant.adresseProperty().setValue( "Adresse :" );
		
		labelCourse.textProperty().bind( modelInfoParticipant.adresseProperty() );
		modelInfoParticipant.courseProperty().setValue( "Course : " );
		
		labelPermis.textProperty().bind( modelInfoParticipant.courseProperty() );
		modelInfoParticipant.permisProperty().setValue( "Permis : " );
		
		labelMajeur.textProperty().bind( modelInfoParticipant.majeurProperty() );
		modelInfoParticipant.majeurProperty().setValue( "Majeur : " );
		
		labelCertificat.textProperty().bind( modelInfoParticipant.certificatProperty() );
		modelInfoParticipant.certificatProperty().setValue( "Certificat Médicale : " );
		
		labelCout.textProperty().bind( modelInfoParticipant.coutProperty() );
		modelInfoParticipant.coutProperty().setValue( "Coût : " );
		*/
	}

}
