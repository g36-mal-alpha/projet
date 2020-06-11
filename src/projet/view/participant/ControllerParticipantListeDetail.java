package projet.view.participant;

import java.time.LocalDate;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
		columnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		columnPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		columnNumero_tel.setCellValueFactory(new PropertyValueFactory<>("numero_tel"));
		columnDateN.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
		columnAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
		columnMail.setCellValueFactory(new PropertyValueFactory<>("mail"));
		columnNiveau.setCellValueFactory(new PropertyValueFactory<>("niveau"));
		columnMateriel.setCellValueFactory(new PropertyValueFactory<>("materiel_utilise"));
		columnCertificat.setCellValueFactory(new PropertyValueFactory<>("certificat_medical"));
		
		
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
		managerGui.showView( EnumView.ParticipantListe );
	}
    
}
