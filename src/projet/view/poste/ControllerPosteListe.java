package projet.view.poste;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.data.Poste;
import projet.view.EnumView;


public class ControllerPosteListe {
	
	
	// Composants de la vue

	@FXML
	private ListView<Poste>	   listView;
	@FXML
	private Button				buttonModifier;
	@FXML
	private Button				buttonSupprimer;


	// Autres champs
	
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelPoste		modelPoste;
	
	
	// Initialisation du Controller

	@FXML
	private void initialize() {

		// Data binding
		listView.setItems( modelPoste.getListe() );
		
		// Configuraiton des boutons
		listView.getSelectionModel().selectedItemProperty().addListener(
				(obs, oldVal, newVal) -> {
					configurerBoutons();
		});
		configurerBoutons();
		
		//CellFactory
		listView.setCellFactory( UtilFX.cellFactory( item -> item.getLibelle() ) );
		
	}
	
	public void refresh() {
		modelPoste.actualiserListe();
		UtilFX.selectInListView( listView, modelPoste.getCourant() );
		listView.requestFocus();
	}

	
	// Actions
	
	@FXML
	private void doDetail() {
		modelPoste.preparerDetail();
		managerGui.showView( EnumView.PosteFormDetail );
	}
	
	@FXML
	private void doAjouter() {
		modelPoste.preparerAjouter();;
		managerGui.showView( EnumView.PosteForm );
	}

	@FXML
	private void doModifier() {
		modelPoste.preparerModifier( listView.getSelectionModel().getSelectedItem() );
		managerGui.showView( EnumView.PosteForm );
	}

	@FXML
	private void doSupprimer() {
		if ( managerGui.showDialogConfirm( "Confirmez-vous la suppresion ?" ) ) {
			modelPoste.supprimer( listView.getSelectionModel().getSelectedItem() );
			refresh();
		}
	}
	
	
	// Gestion des évènements

	// Clic sur la liste
	@FXML
	private void gererClicSurListe( MouseEvent event ) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (event.getClickCount() == 2) {
				if ( listView.getSelectionModel().getSelectedIndex() == -1 ) {
					managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
				} else {
					doModifier();
				}
			}
		}
	}

	
	// Méthodes auxiliaires
	
	private void configurerBoutons() {
		
    	if( listView.getSelectionModel().getSelectedItems().isEmpty() ) {
			buttonModifier.setDisable(true);
			buttonSupprimer.setDisable(true);
		} else {
			buttonModifier.setDisable(false);
			buttonSupprimer.setDisable(false);
		}
	}

}
