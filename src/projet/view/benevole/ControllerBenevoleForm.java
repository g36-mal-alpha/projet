package projet.view.benevole;

import java.awt.Checkbox;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import jfox.javafx.control.EditingCell;
import jfox.javafx.util.ConverterStringInteger;
import jfox.javafx.util.ConverterStringLocalDate;
import jfox.javafx.util.ListenerFocusValidation;
import jfox.javafx.view.IManagerGui;
import projet.data.Categorie;
import projet.data.Poste;
import projet.data.Benevole;
import projet.data.Telephone;
import projet.view.EnumView;
import projet.view.personne.ModelBenevole;
import projet.view.poste.ModelPoste;

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
		private Checkbox			checkMineur;
		@FXML
		private ComboBox<Poste> 	comboPoste;

		
		// Autres champs
		@Inject
		private IManagerGui			managerGui;
		@Inject
		private ModelBenevole		modelBenevole;
		@Inject
		private Poste				modelPoste;
	    
		
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
			
			comboPoste.setItems( modelPoste.getLibelle() );
			comboPoste.valueProperty().bindBidirectional( courant.libelleProperty());
			
			checkMineur.selectedProperty().bindBidirectional( courant.mineursProperty() );
		
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
