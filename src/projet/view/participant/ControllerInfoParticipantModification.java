package projet.view.participant;

import javax.inject.Inject;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import jfox.javafx.view.IManagerGui;
import projet.data.Participant;


public class ControllerInfoParticipantModification {
	// Composants de la vues
	
	@FXML
	private TextField		textFieldNom;
	@FXML
	private TextField		textPoste;
	@FXML
	private TextField		textPrenom;
	@FXML
	private TextField		textTelephone;
	@FXML
	private TextField		textAdresseMail;
	@FXML
	private TextField		textAdresse;
	@FXML
	private TextField		textCourse;
	@FXML
	private TextField		textPermis;
	@FXML
	private TextField		textMajeur;
	@FXML
	private TextField		textCertificat;
	@FXML
	private TextField		textCout;
		
	// Autres champs
	private Participant	courantParticipant;
	
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelInfoParticipantModification	modelInfoParticipantModification;
	
	
	
	// Initialisation
	
	@FXML
	private void initialize() {
		
		courantParticipant = modelInfoParticipantModification.getCourantParticipant();
		
		// Data binding

		textFieldNom.textProperty().bindBidirectional( courantParticipant.nomProperty()  );
		
		/*textPoste.textProperty().bindBidirectional( courantParticipant.adresseProperty() );

		
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
		*/
	}

}