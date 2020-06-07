package projet.view.map;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import jfox.javafx.util.UtilFX;
import projet.dao.DaoPoste;


public class ControllerViewMap {
	
	
	// Composants visuales
	
	@FXML
	private TextArea		textArea;
	@FXML
	final Circle            circle1 = new Circle(); 
	
	
	// Autres champs
	
	@Inject
	private DaoPoste	dao;
	
	private boolean		afficher = false;	
	
	
	// Actions
	
	@FXML
	private void doSignaleur() {
		afficher = true;
		textArea.appendText(  "Test n°1 OK \n");
		//circle1.setVisible(new Circle());
	}

	
}
