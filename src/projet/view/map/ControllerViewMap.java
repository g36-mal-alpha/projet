package projet.view.map;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import projet.dao.DaoBenevole;
import projet.dao.DaoPoste;
import projet.data.Benevole;


public class ControllerViewMap {
	
	private int idposte;
	
	private int nombrePlaceRestant;
	// Composants visuales
	
	@FXML
	private  TextArea		 textArea;
    @FXML
    private  ProgressBar     progressBar;  
	@FXML
	private  Circle          circle1; 
	@FXML
	private  Circle          circle2; 
	@FXML
	private  Circle          circle3; 
	@FXML
	private  Circle          circle4; 
	@FXML
	private  Circle          circle5; 
	@FXML
	private  Circle          circle6; 
	@FXML
	private  Circle          circle7; 
	@FXML
	private  Circle          circle8; 
	@FXML
	private  Circle          circle9; 
	@FXML
	private  Circle          circle10; 
	@FXML
	private  Circle          circle11; 
	@FXML
	private  Circle          circle12; 
	
	// Autres champs
	
	@Inject
	private DaoPoste	dao;

	private void masquer() {
		circle1.setOpacity(0.00);
		circle2.setOpacity(0.00);
		circle3.setOpacity(0.00);
		circle4.setOpacity(0.00);
		circle5.setOpacity(0.00);
		circle6.setOpacity(0.00);
		circle7.setOpacity(0.00);
		circle8.setOpacity(0.00);
		circle9.setOpacity(0.00);
		circle10.setOpacity(0.00);
		circle11.setOpacity(0.00);
		circle12.setOpacity(0.00);
	}
	


	// Actions
	@FXML
	private void doTous(){
		circle1.setOpacity(0.80);
		circle2.setOpacity(0.80);
		circle3.setOpacity(0.80);
		circle4.setOpacity(0.80);
		circle5.setOpacity(0.80);
		circle6.setOpacity(0.80);
		circle7.setOpacity(0.80);
		circle8.setOpacity(0.80);
		circle9.setOpacity(0.80);
		circle10.setOpacity(0.80);
		circle11.setOpacity(0.80);
		circle12.setOpacity(0.80);
	}
	
	@FXML
	private void doSignaleur() {
		masquer();
		idposte = 1; 
		nombrePlaceRestant = dao.totalPourPoste(idposte)-dao.compterPourPoste(idposte);
		
		circle1.setOpacity(0.80);
		textArea.clear();
		textArea.appendText( nombrePlaceRestant +" places disponibles pour ce poste");
	}
	
	@FXML
	private void doBuvette() {
		masquer();
		idposte = 2; 
		nombrePlaceRestant = dao.totalPourPoste(idposte)-dao.compterPourPoste(idposte);
		
		circle2.setOpacity(0.80);
		textArea.clear();
		textArea.appendText(nombrePlaceRestant +" places disponibles pour ce poste");	
	}
	
	@FXML
	private void doMoto() {
		masquer();
		idposte = 3; 
		nombrePlaceRestant = dao.totalPourPoste(idposte)-dao.compterPourPoste(idposte);
		
		circle4.setOpacity(0.80);
		textArea.clear();
		textArea.appendText(nombrePlaceRestant +" places disponibles pour ce poste");	
	}
	
	@FXML
	private void doPhoto() {
		masquer();
		idposte = 4; 
		nombrePlaceRestant = dao.totalPourPoste(idposte)-dao.compterPourPoste(idposte);
		
		circle3.setOpacity(0.80);
		textArea.clear();
		textArea.appendText(nombrePlaceRestant +" places disponibles pour ce poste");	
	}

	@FXML
	private void doVoiture() {
		masquer();
		idposte = 5; 
		nombrePlaceRestant = dao.totalPourPoste(idposte)-dao.compterPourPoste(idposte);
		
		textArea.clear();
		circle5.setOpacity(0.80);
		textArea.appendText(nombrePlaceRestant +" places disponibles pour ce poste");	
	}
	
	@FXML
	private void doVelo() {
		masquer();
		idposte = 6; 
		nombrePlaceRestant = dao.totalPourPoste(idposte)-dao.compterPourPoste(idposte);
		
		textArea.clear();
		circle6.setOpacity(0.80);
		textArea.appendText(nombrePlaceRestant +" places disponibles pour ce poste");	
	}
	
	@FXML
	private void doRemise() {
		masquer();
		idposte = 7; 
		nombrePlaceRestant = dao.totalPourPoste(idposte)-dao.compterPourPoste(idposte);
		
		textArea.clear();
		circle7.setOpacity(0.80);
		textArea.appendText(nombrePlaceRestant +" places disponibles pour ce poste");	
	}
	
	@FXML
	private void doRavitaillement() {
		masquer();
		idposte = 8; 
		nombrePlaceRestant = dao.totalPourPoste(idposte)-dao.compterPourPoste(idposte);
		
		textArea.clear();
		circle9.setOpacity(0.80);
		textArea.appendText(nombrePlaceRestant +" places disponibles pour ce poste");	
	}

	@FXML
	private void doSecu() {
		masquer();
		idposte = 9; 
		nombrePlaceRestant = dao.totalPourPoste(idposte)-dao.compterPourPoste(idposte);

		textArea.clear();
		circle10.setOpacity(0.80);
		textArea.appendText(nombrePlaceRestant +" places disponibles pour ce poste");	
	}
	
	@FXML
	private void doChrono() {
		masquer();
		idposte = 10; 
		nombrePlaceRestant = dao.totalPourPoste(idposte)-dao.compterPourPoste(idposte);
		
		textArea.clear();
		circle8.setOpacity(0.80);
		textArea.appendText(nombrePlaceRestant +" places disponibles pour ce poste");	
	}
	
	@FXML
	private void doRepas() {
		masquer();
		idposte = 11; 
		nombrePlaceRestant = dao.totalPourPoste(idposte)-dao.compterPourPoste(idposte);
		
		textArea.clear();
		circle12.setOpacity(0.80);
		textArea.appendText(nombrePlaceRestant +" places disponibles pour ce poste");	
	}
	
	@FXML
	private void doRecup() {
		masquer();
		idposte = 12; 
		nombrePlaceRestant = dao.totalPourPoste(idposte)-dao.compterPourPoste(idposte);

		textArea.clear();
		circle11.setOpacity(0.80);
		textArea.appendText(nombrePlaceRestant +" places disponibles pour ce poste");
	}	
}
