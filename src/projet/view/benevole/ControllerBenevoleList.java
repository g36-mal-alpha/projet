package projet.view.benevole;

import javax.inject.Inject;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.data.Benevole;
import projet.data.Service;
import projet.view.EnumView;
import projet.view.benevole.ModelBenevole;


public class ControllerBenevoleList {
	
	
	// Composants de la vue
	
		@FXML
		private ListView<Benevole>	listView;
		@FXML
		private Button				buttonModifier;
		@FXML
		private Button				buttonSupprimer;
		
		
		// Autres champs
		
		@Inject
		private IManagerGui			managerGui;
		@Inject
		private ModelBenevole		modelBenevole;
		
		
		// Initialisation du controller

		@FXML
		private void initialize() {
			
			// Data binding
			listView.setItems( modelBenevole.getListe() );
			
			// Configuraiton des boutons
			listView.getSelectionModel().getSelectedItems().addListener( 
			        (ListChangeListener<Benevole>) (c) -> {
			        	configurerBoutons();
			});
	    	configurerBoutons();
		}
		
		public void refresh() {
			modelBenevole.actualiserListe();
			UtilFX.selectInListView(listView, modelBenevole.getCourant() );
			listView.requestFocus();
		}
		
		
		// Actions
	
		@FXML
		private void doDetail() {
			modelBenevole.preparerDetail();
			managerGui.showDialog( EnumView.BenevoleListeDetail );
		}
		
		@FXML
		private void doAjouter() {
			modelBenevole.preparerAjouter();
			managerGui.showView( EnumView.BenevoleForm );
		}
		
		@FXML
		private void doModifier() {
			modelBenevole.preparerModifier( listView.getSelectionModel().getSelectedItem() );
			managerGui.showView( EnumView.BenevoleForm );
		}
		
		@FXML
		private void doSupprimer() {
			Benevole item = listView.getSelectionModel().getSelectedItem();
			if ( item == null ) {
				managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
			} else {
				boolean reponse = managerGui.showDialogConfirm( "Confirmez-vous la suppresion ?" );
				if ( reponse ) {
					modelBenevole.supprimer( item );
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
