package projet.view.benevole;



import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import jfox.javafx.util.ConverterStringInteger;
import jfox.javafx.util.ConverterStringLocalDate;
import jfox.javafx.util.ListenerFocusValidation;
import jfox.javafx.view.IManagerGui;
import projet.data.Poste;
import projet.data.Benevole;
import projet.view.EnumView;

public class ControllerBenevoleForm {
	
	
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
		private TextField			textFieldPermis;
		@FXML
		private CheckBox			checkMineur;
		@FXML
		private ComboBox<Poste> 	comboPoste;

		
		// Autres champs
		@Inject
		private IManagerGui			managerGui;
		@Inject
		private ModelBenevole		modelBenevole;
		@Inject
		private Benevole				bene;
	    
		
		// Initialisation du controller
		
		public void initialize() {

			Benevole courant = modelBenevole.getCourant();
			
			// Champs simples
			textFieldId.textProperty().bindBidirectional( courant.idProperty(), new ConverterStringInteger() );
			textFieldNom.textProperty().bindBidirectional( courant.nomProperty() );
			textFieldPrenom.textProperty().bindBidirectional( courant.prenomProperty() );

			dateNaissance.getEditor().textProperty().bindBidirectional( courant.date_naissanceProperty(), new ConverterStringLocalDate() );
			dateNaissance.getEditor().focusedProperty().addListener(new ListenerFocusValidation( courant.date_naissanceProperty(), "Jour incorrect." ) );
			

			// Data binding
			
			comboPoste.setItems( modelBenevole.getPostes());
			comboPoste.valueProperty().bindBidirectional( courant.posteProperty());
			
			//checkMineur.selectedProperty().bindBidirectional( courant.mineursProperty() );
		
		}
		
		
		// Actions
		
		@FXML
		private void doValider() {
			modelBenevole.validerMiseAJour();
			managerGui.showView( EnumView.BenevoleListe );
		}
		
		@FXML
		private void doAnnuler() {
			managerGui.showView( EnumView.BenevoleListe );
		}
		


}
