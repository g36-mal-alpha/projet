package projet.view.participant;

import java.time.LocalDate;
import java.time.LocalTime;

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
import projet.data.Participant;
import projet.view.EnumView;


public class ControllerParticipantListeDetail  {
	
	
	// Composants de la vue
	
	@FXML
	private TextField			textFieldId;
	@FXML
	private TableView<Participant>	tableViewParticpants;
	@FXML
	private TableColumn<Participant, Integer> columnId;
	@FXML
	private TableColumn<Participant, String> columnNom;
	@FXML
	private TableColumn<Participant, String> columnPrenom;
	@FXML
	private TableColumn<Participant, String> columnNumero_tel;
	@FXML
	private TableColumn<Participant, LocalDate> columnDateN;
	@FXML
	private TableColumn<Participant, String> columnAdresse;
	@FXML
	private TableColumn<Participant, String> columnMail;
	@FXML
	private TableColumn<Participant, String> columnCertificat;
	@FXML
	private TableColumn<Participant, String> columnNiveau;
	@FXML
	private TableColumn<Participant, String> columnMateriel;
	
	// Autres champs
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelParticipant		    modelParticipant;
    
	
	// Initialisation du controller
	
	public void initialize() {

		// Data Binding
		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNom.setCellValueFactory(new PropertyValueFactory<>("libelle"));
		columnPrenom.setCellValueFactory(new PropertyValueFactory<>("lieu"));
		columnNumero_tel.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		columnDateN.setCellValueFactory(new PropertyValueFactory<>("jour"));
		columnAdresse.setCellValueFactory(new PropertyValueFactory<>("heure_debut"));
		columnMail.setCellValueFactory(new PropertyValueFactory<>("heure_fin"));
		columnNiveau.setCellValueFactory(new PropertyValueFactory<>("numero_poste"));
		columnMateriel.setCellValueFactory(new PropertyValueFactory<>("numero_poste"));
		columnCertificat.setCellValueFactory(new PropertyValueFactory<>("numero_poste"));
		
		
		//columnEpreuve.setCellValueFactory(param -> param.getValue().getCategorie().libelleProperty());
		//columnSexe.setCellValueFactory(param -> param.getValue().getCategorie().libelleProperty())
		//columnEquipe.setCellValueFactory(param -> param.getValue().getCategorie().libelleProperty());
		//columnHierarchie.setCellValueFactory(param -> ((Benevole) param.getValue().getBenevoles()).nomProperty());
		
		// Tableview
		tableViewParticpants.setItems(modelParticipant.getListe());

	}
	
	
	// Actions

	@FXML
	private void doAnnuler() {
		managerGui.showView( EnumView.PosteListe );
	}
    
}
