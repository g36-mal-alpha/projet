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
import projet.data.Equipe;
import projet.data.Hierarchie;
import projet.data.Participant;
import projet.view.EnumView;
import projet.data.Sexe;
import projet.data.Epreuve;



public class ControllerParticipantForm {
	
	
	// Composants de la vue
	
		@FXML
		private TextField			textFieldId;
		@FXML
		private TextField			textFieldNom;
		@FXML	
		private TextField			textFieldPrenom;
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
		private ComboBox<Equipe>		comboBoxEquipe;
		@FXML
		private ComboBox<Sexe>			comboBoxSexe;
		@FXML	
		private ComboBox<Hierarchie>	comboBoxHierarchie;
		@FXML	
		private ComboBox<Epreuve>		comboBoxEpreuve;

		
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
	        
			comboBoxHierarchie.setItems(modelParticipant.getHierarchie());
	        comboBoxHierarchie.valueProperty().bindBidirectional( courant.HierarchieProperty() );
	        
			comboBoxEquipe.setItems(modelParticipant.getEquipe());
	        comboBoxEquipe.valueProperty().bindBidirectional(courant.equipeProperty() );
	      
	    	comboBoxEpreuve.setItems(modelParticipant.getEpreuve());
	        comboBoxEpreuve.valueProperty().bindBidirectional( courant.epreuveProperty() );
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
		
		@FXML
		private void doSupprimerSexe() {
		 comboBoxSexe.setValue( null );
		}
		
		@FXML
		private void doSupprimerHierarchie() {
		 comboBoxHierarchie.setValue( null );
		}
		


}