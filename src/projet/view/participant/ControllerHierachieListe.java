package projet.view.participant;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import jfox.javafx.util.UtilFX;
import projet.data.Hierarchie;


public class ControllerHierachieListe {
	
	
	// Composants de la vue

	@FXML
	private ListView<Hierarchie>	listView;
	@FXML
	private Button				buttonModifier;
	@FXML
	private Button				buttonSupprimer;


	// Autres champs
	
	@Inject
	private ModelHierarchie		modelHierarchie;
	
	
	// Initialisation du Controller

	@FXML
	private void initialize() {

		// Data binding
		listView.setItems( modelHierarchie.getListe() );
		
	}
	
	public void refresh() {
		modelHierarchie.actualiserListe();
		UtilFX.selectInListView( listView, modelHierarchie.getCourant() );
		listView.requestFocus();
	}

	
	
	}

