package projet.view.participant;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class ControllerInfoParticipant {
	// Composants de la vue
	
	@FXML
	private Label		textNom;
	@FXML
	private Label		textPoste;
	@FXML
	private Label		textPrenom;
	@FXML
	private Label		textTelephone;
	@FXML
	private Label		textAdresseMail;
	@FXML
	private Label		textAdresse;
	@FXML
	private Label		textCourse;
	@FXML
	private Label		textPermis;
	@FXML
	private Label		textMajeur;
	@FXML
	private Label		textCertificat;
	@FXML
	private Label		textCout;
		
	// Autres champs
	
	@Inject
	private ModelInfoParticipant	modelInfoParticipant;
	
	
	// Initialisation
	
	@FXML
	private void initialize() {
		
		// Data binding
		textNom.textProperty().bind( modelInfoParticipant.nomProperty() );
		modelInfoParticipant.nomProperty().setValue( "Nom : %d " );
		textPoste.textProperty().bind( modelInfoParticipant.posteProperty() );
		modelInfoParticipant.posteProperty().setValue( "Poste : " );
		textPrenom.textProperty().bind( modelInfoParticipant.prenomProperty() );
		modelInfoParticipant.prenomProperty().setValue( "Prenom : " );
		textTelephone.textProperty().bind( modelInfoParticipant.telephoneProperty() );
		modelInfoParticipant.telephoneProperty().setValue( "Téléphone : " );
		textAdresseMail.textProperty().bind( modelInfoParticipant.adressemailProperty() );
		modelInfoParticipant.adressemailProperty().setValue( "Adresse Mail : " );
		textAdresse.textProperty().bind( modelInfoParticipant.adresseProperty() );
		modelInfoParticipant.adresseProperty().setValue( "Adresse :" );
		textCourse.textProperty().bind( modelInfoParticipant.adresseProperty() );
		modelInfoParticipant.courseProperty().setValue( "Course : " );
		textPermis.textProperty().bind( modelInfoParticipant.courseProperty() );
		modelInfoParticipant.permisProperty().setValue( "Permis : " );
		textMajeur.textProperty().bind( modelInfoParticipant.majeurProperty() );
		modelInfoParticipant.majeurProperty().setValue( "Majeur : " );
		textCertificat.textProperty().bind( modelInfoParticipant.certificatProperty() );
		modelInfoParticipant.certificatProperty().setValue( "Certificat Médicale : " );
		textCout.textProperty().bind( modelInfoParticipant.coutProperty() );
		modelInfoParticipant.coutProperty().setValue( "Coût : " );
		
	}

}
