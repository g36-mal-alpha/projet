package projet.view.poste;

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
	private TableColumn<Poste, String> columnEpreuve;
	
	

	
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
		
		//TableView<Poste> table = new TableView<Poste>();
	    //TableColumn<Poste, String> libelleCol = new TableColumn<Poste, String>("Libelle");
		
		columnId.setCellValueFactory(new PropertyValueFactory<Poste, Integer>("id"));
		columnLibelle.setCellValueFactory(new PropertyValueFactory<Poste, String>("libelle"));
		columnLieu.setCellValueFactory(new PropertyValueFactory<Poste, String>("lieu"));
		
		/*columnEpreuve.setCellValueFactory(new Callback<CellDataFeatures<Poste,String>,ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(CellDataFeatures<Poste, String> param) {
                return new SimpleStringProperty(param.getValue().getEquipe().getEpreuve().getNomEpreuve());
            }
        });*/
		
		// Data binding
	    /*libelleCol.setCellValueFactory(new PropertyValueFactory<>("Libelle"));
	    
	    libelleCol.setSortType(TableColumn.SortType.DESCENDING);
	     
	    
	    ObservableList<Poste> list = modelPoste.getListe();
	    table.setItems(list);
	    table.getColumns().addAll("id");*/
	    
		tableViewPostes.setItems( modelPoste.getListe());
	     
	     
	}
	
	
	// Actions

	@FXML
	private void doAnnuler() {
		managerGui.showView( EnumView.PersonneListe );
	}
    
}
