package projet.view.benevole;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import jfox.javafx.control.EditingCell;
import jfox.javafx.util.ConverterStringInteger;
import jfox.javafx.view.IManagerGui;
import projet.data.Categorie;
import projet.data.Benevole;
import projet.data.Telephone;
import projet.view.EnumView;
import projet.view.personne.ModelBenevole;

public class ControllerBenevoleForm {
	
	
	// Composants de la vue
	
		@FXML
		private TextField			textFieldId;
		@FXML
		private TextField			textFieldNom;
		@FXML	
		private TextField			textFieldPrenom;
	    @FXML
	    private ComboBox<Categorie>	comboBoxCategorie;
		@FXML
		private TableView<Telephone>	tableViewTelphones;
		@FXML
		private TableColumn<Telephone, Integer> columnId;
		@FXML
		private TableColumn<Telephone, String> columnLibelle;
		@FXML
		private TableColumn<Telephone, String> columnNumero;

		
		// Autres champs
		@Inject
		private IManagerGui			managerGui;
		@Inject
		private ModelBenevole		modelBenevole;
	    
		
		// Initialisation du controller
		
		public void initialize() {

			Benevole courant = modelBenevole.getCourant();
			
			// Champs simples
			textFieldId.textProperty().bindBidirectional( courant.idProperty(), new ConverterStringInteger() );
			textFieldNom.textProperty().bindBidirectional( courant.nomProperty() );
			textFieldPrenom.textProperty().bindBidirectional( courant.prenomProperty() );

	        
			// Configuration de la combo box

			// Data binding
			comboBoxCategorie.setItems(  modelBenevole.getCategories());
	        comboBoxCategorie.valueProperty().bindBidirectional( courant.categorieProperty() );
	 		
			
			// Configuration du TableView

			// Data binding
			tableViewTelphones.setItems(  courant.getTelephones() );
			
			columnId.setCellValueFactory( t -> t.getValue().idProperty() );
			columnLibelle.setCellValueFactory( t -> t.getValue().libelleProperty() );
			columnNumero.setCellValueFactory( t -> t.getValue().numeroProperty() );

			columnLibelle.setCellFactory(  p -> new EditingCell<>() );
			columnNumero.setCellFactory(  p -> new EditingCell<>() );		
		
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
		private void doAjouterTelephone() {
			modelBenevole.ajouterTelephone();
		}
		
		
		@FXML
		private void doiSupprimerTelephone() {
			Telephone telephone = tableViewTelphones.getSelectionModel().getSelectedItem();
			modelBenevole.supprimerTelephone(telephone);
		}
	    

}
