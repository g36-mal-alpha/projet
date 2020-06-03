package projet.view.participant;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.data.Hierarchie;
import projet.view.EnumView;


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
	private IManagerGui			managerGui;
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

