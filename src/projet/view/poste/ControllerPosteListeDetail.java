package projet.view.poste;

import java.time.LocalDate;

import javax.inject.Inject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import jfox.javafx.control.EditingCell;
import jfox.javafx.util.ConverterStringInteger;
import jfox.javafx.view.IManagerGui;
import projet.data.Benevole;
import projet.data.Categorie;
import projet.data.Poste;
import projet.view.EnumView;


public class ControllerPosteListeDetail  {
	
	
	// Composants de la vue
	
	@FXML
	private TextField			textFieldId;
	@FXML
	private TableView<Poste>	tableViewPostes;
	@FXML
	private TableColumn<Poste, Integer> columnId;
	@FXML
	private TableColumn<Poste, String> columnLibelle;
	@FXML
	private TableColumn<Poste, String> columnLieu;
	@FXML
	private TableColumn<Poste, Integer> columnNombre;
	@FXML
	private TableColumn<Poste, String> columnJour;
	@FXML
	private TableColumn<Poste, LocalDate> columnDebut;
	@FXML
	private TableColumn<Poste, LocalDate> columnFin;
	@FXML
	private TableColumn<Poste, Integer> columnNumero;
	@FXML
	private TableColumn<Categorie, String> columnCategorie;
	@FXML
	private TableColumn<Benevole, String> columnBenevole;
	
	// Autres champs
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelPoste		    modelPoste;
    
	
	// Initialisation du controller
	
	public void initialize() {

		// Data Binding
		columnLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
		columnLieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
		columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		columnJour.setCellValueFactory(new PropertyValueFactory<>("jour"));
		columnDebut.setCellValueFactory(new PropertyValueFactory<>("heure_debut"));
		columnFin.setCellValueFactory(new PropertyValueFactory<>("heure_fin"));
		columnNumero.setCellValueFactory(new PropertyValueFactory<>("numero_poste"));
		
		
		//columnCategorie.setCellValueFactory(param -> param.getValue().getLibelle().toString());
		//columnBenevole.setCellValueFactory(param -> param.getValue().getId());
		
		// Tableview
		tableViewPostes.setItems(modelPoste.getListe());

	}
	
	
	// Actions

	@FXML
	private void doAnnuler() {
		managerGui.showView( EnumView.PosteListe );
	}
    
}