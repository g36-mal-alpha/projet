package projet.view.participant;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import jfox.javafx.util.UtilFX;
import projet.data.Sexe;


public class ControllerSexeListe {
	
	
	// Composants de la vue

	@FXML
	private ListView<Sexe>		listView;
	@FXML
	private Button				buttonModifier;
	@FXML
	private Button				buttonSupprimer;


	// Autres champs
	
	@Inject
	private ModelSexe		modelSexe;
	
	
	// Initialisation du Controller

	@FXML
	private void initialize() {

		// Data binding
		listView.setItems( modelSexe.getListe() );
		
	}
	
	public void refresh() {
		modelSexe.actualiserListe();
		UtilFX.selectInListView( listView, modelSexe.getCourant() );
		listView.requestFocus();
	}

	
	
	}

