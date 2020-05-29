package projet.view.poste;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import jfox.javafx.control.EditingCell;
import jfox.javafx.util.ConverterStringInteger;
import jfox.javafx.view.IManagerGui;
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

		// Configuration du TableView
		
		columnId.setCellValueFactory(new PropertyValueFactory<Poste, Integer>("id"));
		columnLibelle.setCellValueFactory(new PropertyValueFactory<Poste, String>("libelle"));
		columnLieu.setCellValueFactory(new PropertyValueFactory<Poste, String>("lieu"));
		
		/*epreuve.setCellValueFactory(new Callback<CellDataFeatures<Participant,String>,ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(CellDataFeatures<Participant, String> param) {
                return new SimpleStringProperty(param.getValue().getEquipe().getEpreuve().getNomEpreuve());
            }
        });*/
		// Data binding
		tableViewPostes.setItems( modelPoste.getListe());
	
	}
	
	
	// Actions

	@FXML
	private void doAnnuler() {
		managerGui.showView( EnumView.PersonneListe );
	}
    
}
