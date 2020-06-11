package projet.view.benevole;



import javax.inject.Inject;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import jfox.javafx.util.ConverterStringInteger;
import jfox.javafx.util.ConverterStringLocalDate;
import jfox.javafx.util.ListenerFocusValidation;
import jfox.javafx.view.IManagerGui;
import projet.data.Poste;
import projet.data.Benevole;
import projet.data.Categorie;
import projet.view.EnumView;
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
		private CheckBox			checkMineur;
		@FXML
		private Poste			   poste;
		@FXML
		private ComboBox<Categorie>	comboBoxCategorie;
	    @FXML
	    private ListView<Poste>  listViewPostes;
		

		
		// Autres champs
		@Inject
		private IManagerGui			managerGui;
		@Inject
		private ModelBenevole		modelBenevole;
		@Inject
		private ModelPoste		    modelPoste;
	    
		
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
			comboBoxCategorie.setItems( modelBenevole.getCategorie() );
			comboBoxCategorie.valueProperty().bindBidirectional( courant.categorieProperty());
			
			listViewPostes.setItems( courant.getPostes() );
			
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
		
		@FXML
		private void doSupprimerCategorie() {
		 comboBoxCategorie.setValue( null );
		}
		
		@FXML
		private void doSupprimerPostes() {
		 ObservableList<Poste> selectedItems = listViewPostes.getSelectionModel().getSelectedItems();
		 for ( int i = selectedItems.size() - 1; i>=0; --i ) {
		 modelBenevole.supprimerPoste( selectedItems.get(i) );
		 }
		}
		
		@FXML
		private void doAjoutPostes() {
			managerGui.showDialog( EnumView.BenevolesAjoutPostes );
			modelPoste.actualiserListeBenevolesPourDialogAjout();
		}
		

}
