package projet.view.benevole;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import jfox.javafx.view.IManagerGui;
import projet.data.Poste;



public class ControllerBenevolesAjoutPostes {
	
	
	// Composants de la vue

	@FXML
	private ListView<Poste>	   listView;
	@FXML
	private Button				buttonAjouter;
	@FXML
	private Button				buttonSupprimer;


	// Autres champs
	
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelBenevole		    modelBenevole;
	@Inject
//	private ModelBenevole      modelBenevoles;
	
	// Initialisation du Controller

	@FXML
	private void initialize() {

		
		// Data binding
		listView.setItems( modelBenevole.getPostesPourDialogAjout() );
		
		// Configuraiton des boutons
		listView.getSelectionModel().selectedItemProperty().addListener(
				(obs, oldVal, newVal) -> {
					configurerBoutons();
		});
		configurerBoutons();
		
		listView.getSelectionModel().setSelectionMode( SelectionMode.MULTIPLE );
	}
	
	public void refresh() {
		modelBenevole.actualiserListePostesPourDialogAjout();
		listView.requestFocus();
	}

	
	// Actions
	
	@FXML
	private void doFermer() {
	 managerGui.closeStage();
	}
	
	
	
	@FXML
	private void doAjouter() {
	 for ( Poste item : listView.getSelectionModel().getSelectedItems() ) {
	 modelBenevole.ajouterBenevole( item );
	 }
	 managerGui.closeStage();
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
					doAjouter();
				}
			}
		}
	}

	
	// Méthodes auxiliaires
	
	private void configurerBoutons() {
		
    	if( listView.getSelectionModel().getSelectedItems().isEmpty() ) {
			buttonAjouter.setDisable(true);
		} else {
			buttonAjouter.setDisable(false);
		}
	}

}
