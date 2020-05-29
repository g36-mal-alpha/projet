package projet.view.poste;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import jfox.javafx.control.EditingCell;
import jfox.javafx.util.ConverterStringInteger;
import jfox.javafx.view.IManagerGui;
import projet.data.Poste;
import projet.data.Telephone;
import projet.view.EnumView;


public class ControllerPosteListeDetail  {
	
	
	// Composants de la vue
	
	@FXML
	private TextField			textFieldId;
	@FXML
	private TextField			textFieldLibelle;
	@FXML	
	private TextField			textFieldLieu;
	@FXML
	private TableView<Poste>	tableViewPostes;
	@FXML
	private TableColumn<Poste, Integer> columnId;
	@FXML
	private TableColumn<Poste, String> columnLibelle;
	@FXML
	private TableColumn<Poste, String> columnLieu;
	



	
	// Autres champs
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelPoste		    modelPoste;
    
	
	// Initialisation du controller
	
	public void initialize() {

		Poste courant = modelPoste.getCourant();
		
		// Champs simples
		textFieldId.textProperty().bindBidirectional( courant.idProperty(), new ConverterStringInteger() );
		textFieldLibelle.textProperty().bindBidirectional( courant.libelleProperty() );
		textFieldLieu.textProperty().bindBidirectional( courant.lieuProperty() );

        
		
		// Configuration du TableView

		// Data binding
		tableViewPostes.setItems(  courant.getPoste() );
		
		columnId.setCellValueFactory( t -> t.getValue().idProperty() );
		
		columnLibelle.setCellValueFactory( t -> t.getValue().libelleProperty() );
		columnLieu.setCellValueFactory( t -> t.getValue().libelleProperty() );


		columnLibelle.setCellFactory(  p -> new EditingCell<>() );
		columnLibelle.setCellValueFactory( t -> t.getValue().libelleProperty() );
		
	
	}
	
	
	// Actions

	@FXML
	private void doAnnuler() {
		managerGui.showView( EnumView.PersonneListe );
	}
    
}
