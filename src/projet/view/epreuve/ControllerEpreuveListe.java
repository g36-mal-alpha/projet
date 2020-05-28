package projet.view.epreuve;

import javax.inject.Inject;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.data.Equipe;
import projet.view.EnumView;


public class ControllerEpreuveListe {
	
	
	// Composants de la vue

	@FXML
	private ListView<Equipe>		listView;
	@FXML
	private Button				buttonModifier;
	@FXML
	private Button				buttonSupprimer;


	// Autres champs
	
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelEpreuve		modelEpreuve;
	
	
	// Initialisation du Controller

	@FXML
	private void initialize() {

		// Data binding
		listView.setItems( modelEpreuve.getListe() );
		
		listView.setCellFactory(  UtilFX.cellFactory( item -> item.getNom_equipe() ));
		
		// Configuraiton des boutons
		listView.getSelectionModel().getSelectedItems().addListener( (ListChangeListener<Equipe>) (c) -> {configurerBoutons();});     	
		configurerBoutons();

	}
	
	public void refresh() {
		modelEpreuve.actualiserListe();
		UtilFX.selectInListView( listView, modelEpreuve.getCourant() );
		listView.requestFocus();
	}

	
	// Actions
	
	@FXML
	private void doAjouter() {
		modelEpreuve.preparerAjouter();;
		managerGui.showView( EnumView.EquipeForm );
	}

	@FXML
	private void doModifier() {
		Equipe item = listView.getSelectionModel().getSelectedItem();
		if ( item == null ) {
			managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
		} else {
			modelEpreuve.preparerModifier(item);
			managerGui.showView( EnumView.EquipeForm );
		}
	}

	@FXML
	private void doSupprimer() {
		Equipe item = listView.getSelectionModel().getSelectedItem();
		if ( item == null ) {
			managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
		} else {
			boolean reponse = managerGui.showDialogConfirm( "Confirmez-vous la suppresion ?" );
			if ( reponse ) {
				modelEpreuve.supprimer( item );
				refresh();
			}
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
