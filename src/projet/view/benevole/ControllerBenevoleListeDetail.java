package projet.view.benevole;

import java.time.LocalDate;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import jfox.javafx.view.IManagerGui;
import projet.data.Benevole;
import projet.view.EnumView;


public class ControllerBenevoleListeDetail  {
	
	
	// Composants de la vue
	
	@FXML
	private TextField			textFieldId;
	@FXML
	private TableView<Benevole>	tableViewBenvoles;
	@FXML
	private TableColumn<Benevole, Integer> columnId;
	@FXML
	private TableColumn<Benevole, String> columnNom;
	@FXML
	private TableColumn<Benevole, String> columnPrenom;
	@FXML
	private TableColumn<Benevole, LocalDate> columnDateN;
	@FXML
	private TableColumn<Benevole, String> columnPermis;
	@FXML
	private TableColumn<Benevole, String> columnCategorie;
	@FXML
	private TableColumn<Benevole, String> columnPoste;
	@FXML
	private TableColumn<Benevole, String> columnMineurs;
	
	
	
	// Autres champs
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelBenevole		modelBenevole;
    
	
	// Initialisation du controller
	
	public void initialize() {

		// Data Binding
		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		columnPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		columnDateN.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
		columnPermis.setCellValueFactory(new PropertyValueFactory<>("permis_conduire"));
		columnMineurs.setCellValueFactory(new PropertyValueFactory<>("mineurs"));
		
		
		//columnCategorie.setCellValueFactory(param -> param.getValue().getCategorie().libelleProperty());
		//columnPoste.setCellValueFactory(param -> ((Benevole) param.getValue().getPostes()).libelleProperty());
		
		// Tableview
		tableViewBenvoles.setItems(modelBenevole.getListe());

	}
	
	
	// Actions

	@FXML
	private void doAnnuler() {
		managerGui.showView( EnumView.BenevoleListe );
	}
    
}
