package projet.view.participant;



import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import jfox.javafx.util.ConverterStringInteger;
import jfox.javafx.util.ConverterStringLocalDate;
import jfox.javafx.util.ListenerFocusValidation;
import jfox.javafx.view.IManagerGui;
import projet.data.Hierarchie;
import projet.data.Participant;
import projet.view.EnumView;
import projet.data.Sexe;



public class ControllerParticipantForm {
	
	
	// Composants de la vue
	
		@FXML
		private TextField			textFieldId;
		@FXML
		private TextField			textFieldNom;
		@FXML	
		private TextField			textFieldPrenom;
		@FXML
		private ComboBox<Sexe>		comboBoxSexe;
		@FXML
		private DatePicker			dateNaissance;
		@FXML
		private TextField			textFieldNumero_tel;
		@FXML
		private TextField			textFieldCertificat_Medical;
		@FXML
		private TextField			textFieldAdresse;
		@FXML
		private TextField			textFieldMail;
		@FXML
		private TextField			textFieldNiveau;
		@FXML	
		private TextField			textFieldMateriel_utilise;
		@FXML	
		private ComboBox<Hierarchie>		comboBoxHierarchie;

		
		// Autres champs
		@Inject
		private IManagerGui				managerGui;
		@Inject
		private ModelParticipant		modelParticipant;
		@Inject
		private Participant				participant;
	    
		
		// Initialisation du controller
		
		public void initialize() {

			Participant courant = modelParticipant.getCourant();
			
			// Champs simples
			textFieldId.textProperty().bindBidirectional( courant.idProperty(), new ConverterStringInteger() );
			textFieldNom.textProperty().bindBidirectional( courant.nomProperty() );
			textFieldPrenom.textProperty().bindBidirectional( courant.prenomProperty() );
			textFieldNumero_tel.textProperty().bindBidirectional( courant.numero_telProperty());
			
			dateNaissance.getEditor().textProperty().bindBidirectional( courant.date_naissanceProperty(), new ConverterStringLocalDate() );
			dateNaissance.getEditor().focusedProperty().addListener(new ListenerFocusValidation( courant.date_naissanceProperty(), "Jour incorrect." ) );
			textFieldCertificat_Medical.textProperty().bindBidirectional( courant.certificat_medicalProperty() );
			textFieldAdresse.textProperty().bindBidirectional( courant.adresseProperty() );
			textFieldMail.textProperty().bindBidirectional( courant.mailProperty() );
			textFieldNiveau.textProperty().bindBidirectional( courant.niveauProperty() );
			textFieldMateriel_utilise.textProperty().bindBidirectional( courant.materiel_utiliseProperty() );

			// Data binding
			comboBoxSexe.setItems(modelParticipant.getSexe());
	        comboBoxSexe.valueProperty().bindBidirectional(courant.sexeProperty() );
	        
			// Data binding
			comboBoxHierarchie.setItems(  modelParticipant.getHierarchie());
	        comboBoxHierarchie.valueProperty().bindBidirectional( courant.HierarchieProperty() );
		}
		
		
		// Actions
		
		@FXML
		private void doValider() {
			modelParticipant.validerMiseAJour();
			managerGui.showView( EnumView.ParticipantListe );
		}
		
		@FXML
		private void doAnnuler() {
			managerGui.showView( EnumView.ParticipantListe );
		}
		


}
