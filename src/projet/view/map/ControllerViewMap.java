package projet.view.map;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import projet.dao.DaoPoste;



public class ControllerViewMap {
	
	private int idposte;
	
	private int nombrePlaceRestant;
	
	private int nombrePlaceRestantTotal;
	
	// Composants visuales
	
	@FXML
	private  TextArea		 textArea;
	@FXML
	private  Rectangle       rectPlace;
	@FXML 
	private  Label           pourcentage;
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
	@FXML
	private  Circle          circle13; 
	@FXML
	private  Circle          circle14; 
	@FXML
	private  Circle          circle15; 
	@FXML
	private  Circle          circle16; 
	@FXML
	private  Circle          circle17; 
	@FXML
	private  Circle          circle18; 
	@FXML
	private  Circle          circle19; 
	@FXML
	private  Circle          circle20; 
	@FXML
	private  Circle          circle21; 
	@FXML
	private  Circle          circle22; 
	@FXML
	private  Circle          circle23; 
	@FXML
	private  Circle          circle24;
	@FXML
	private  Circle          circle25; 
	@FXML
	private  Circle          circle26; 
	@FXML
	private  Circle          circle27; 
	@FXML
	private  Circle          circle28; 
	@FXML
	private  Circle          circle29; 
	@FXML
	private  Circle          circle30; 
	@FXML
	private  Circle          circle31; 
	@FXML
	private  Circle          circle32; 
	@FXML
	private  Circle          circle33; 
	@FXML
	private  Circle          circle34; 
	@FXML
	private  Circle          circle35; 
	@FXML
	private  Circle          circle36; 
	@FXML
	private  Circle          circle37; 
	@FXML
	private  Circle          circle38; 
	@FXML
	private  Circle          circle39; 
	@FXML
	private  Circle          circle40; 
	@FXML
	private  Circle          circle41; 
	@FXML
	private  Circle          circle42;
	
	
	
	// Autres champs
	
	@Inject
	private DaoPoste	dao;
	
	public void couleur (int pourcent) {
		if(pourcent<25) {
		rectPlace.setFill(Color.GREEN);
		}
		else if(pourcent>=25 && pourcent<50) {
		rectPlace.setFill(Color.YELLOW);
		}
		else if(pourcent>=50 && pourcent<75) {
		rectPlace.setFill(Color.ORANGE);
		}
		else if(pourcent>=75) {
		rectPlace.setFill(Color.RED);
		}
	}
	
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
		circle13.setOpacity(0.00);
		circle14.setOpacity(0.00);
		circle15.setOpacity(0.00);
		circle16.setOpacity(0.00);
		circle17.setOpacity(0.00);
		circle18.setOpacity(0.00);
		circle19.setOpacity(0.00);
		circle20.setOpacity(0.00);
		circle21.setOpacity(0.00);
		circle22.setOpacity(0.00);
		circle23.setOpacity(0.00);
		circle24.setOpacity(0.00);
		circle25.setOpacity(0.00);
		circle26.setOpacity(0.00);
		circle27.setOpacity(0.00);
		circle28.setOpacity(0.00);
		circle29.setOpacity(0.00);
		circle30.setOpacity(0.00);
		circle31.setOpacity(0.00);
		circle32.setOpacity(0.00);
		circle33.setOpacity(0.00);
		circle34.setOpacity(0.00);
		circle35.setOpacity(0.00);
		circle36.setOpacity(0.00);
		circle37.setOpacity(0.00);
		circle38.setOpacity(0.00);
		circle39.setOpacity(0.00);
		circle40.setOpacity(0.00);
		circle41.setOpacity(0.00);
		circle42.setOpacity(0.00);
	}

	// Actions
	@FXML
	private void doTous(){
		//somme tous les postes
		
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
		circle13.setOpacity(0.80);
		circle14.setOpacity(0.80);
		circle15.setOpacity(0.80);
		circle16.setOpacity(0.80);
		circle17.setOpacity(0.80);
		circle18.setOpacity(0.80);
		circle19.setOpacity(0.80);
		circle20.setOpacity(0.80);
		circle21.setOpacity(0.80);
		circle22.setOpacity(0.80);
		circle23.setOpacity(0.80);
		circle24.setOpacity(0.80);
		circle25.setOpacity(0.80);
		circle26.setOpacity(0.80);
		circle27.setOpacity(0.80);
		circle28.setOpacity(0.80);
		circle29.setOpacity(0.80);
		circle30.setOpacity(0.80);
		circle31.setOpacity(0.80);
		circle32.setOpacity(0.80);
		circle33.setOpacity(0.80);
		circle34.setOpacity(0.80);
		circle35.setOpacity(0.80);
		circle36.setOpacity(0.80);
		circle37.setOpacity(0.80);
		circle38.setOpacity(0.80);
		circle39.setOpacity(0.80);
		circle40.setOpacity(0.80);
		circle41.setOpacity(0.80);
		circle42.setOpacity(0.80);
		
		idposte = 1; 
		nombrePlaceRestantTotal = dao.totalDisponiblePourPoste() - dao.totalOccupePourPoste();
		
		textArea.clear();
		textArea.appendText( nombrePlaceRestantTotal + " place(s) restante(s) " + "sur les " + dao.totalDisponiblePourPoste() +" poste(s) disponible(s)");
		
		int pourcent = dao.totalOccupePourPoste()*100/dao.totalDisponiblePourPoste();
		couleur(pourcent);
		
		rectPlace.setWidth(pourcent*2.5);
 		pourcentage.setText(pourcent+"%"); 		
	}
	
	@FXML
	private void doSignaleur() {
		masquer();
		idposte = 1; 
		nombrePlaceRestant = dao.totalDisponilbeParPoste(idposte)-dao.compterParPoste(idposte);
		
		circle3.setOpacity(0.80);
		circle5.setOpacity(0.80);
		circle6.setOpacity(0.80);
		circle8.setOpacity(0.80);
		circle9.setOpacity(0.80);
		circle11.setOpacity(0.80);
		circle13.setOpacity(0.80);
		circle14.setOpacity(0.80);
		circle16.setOpacity(0.80);
		circle18.setOpacity(0.80);
		circle19.setOpacity(0.80);
		circle20.setOpacity(0.80);
		circle22.setOpacity(0.80);
		circle29.setOpacity(0.80);
		circle30.setOpacity(0.80);
		circle32.setOpacity(0.80);
		circle33.setOpacity(0.80);
		circle35.setOpacity(0.80);
		circle36.setOpacity(0.80);
		circle38.setOpacity(0.80);
		
		textArea.clear();
		textArea.appendText( nombrePlaceRestant + " place(s) restante(s) " + "sur " + dao.totalDisponilbeParPoste(idposte) +" disponible(s) pour ce poste");
		
		int pourcent = dao.compterParPoste(idposte)*100/dao.totalDisponilbeParPoste(idposte);
		couleur(pourcent);
		
		rectPlace.setWidth(pourcent*2.5);
 		pourcentage.setText(pourcent+"%");

 		
	}
	
	@FXML
	private void doBuvette() {
		masquer();
		idposte = 2; 
		nombrePlaceRestant = dao.totalDisponilbeParPoste(idposte)-dao.compterParPoste(idposte);
		
		circle42.setOpacity(0.80);
		
		textArea.clear();
		textArea.appendText( nombrePlaceRestant + " place(s) restante(s) " + "sur " + dao.totalDisponilbeParPoste(idposte) +" disponible(s) pour ce poste");	
		
		int pourcent = dao.compterParPoste(idposte)*100/dao.totalDisponilbeParPoste(idposte);
		couleur(pourcent);
		rectPlace.setWidth(pourcent*2.5);
 		pourcentage.setText(pourcent+"%");
	}
	
	@FXML
	private void doMoto() {
		masquer();
		idposte = 3; 
		nombrePlaceRestant = dao.totalDisponilbeParPoste(idposte)-dao.compterParPoste(idposte);
		
		circle41.setOpacity(0.80);
		
		textArea.clear();
		textArea.appendText( nombrePlaceRestant + " place(s) restante(s) " + "sur " + dao.totalDisponilbeParPoste(idposte) +" disponible(s) pour ce poste");	
		
		int pourcent = dao.compterParPoste(idposte)*100/dao.totalDisponilbeParPoste(idposte);
		couleur(pourcent);
		rectPlace.setWidth(pourcent*2.5);
 		pourcentage.setText(pourcent+"%");
	}
	
	@FXML
	private void doPhoto() {
		masquer();
		idposte = 4; 
		nombrePlaceRestant = dao.totalDisponilbeParPoste(idposte)-dao.compterParPoste(idposte);
		
		circle17.setOpacity(0.80);
		circle24.setOpacity(0.80);
		
		textArea.clear();
		textArea.appendText( nombrePlaceRestant + " place(s) restante(s) " + "sur " + dao.totalDisponilbeParPoste(idposte) +" disponible(s) pour ce poste");	
		
		int pourcent = dao.compterParPoste(idposte)*100/dao.totalDisponilbeParPoste(idposte);
		couleur(pourcent);
		rectPlace.setWidth(pourcent*2.5);
 		pourcentage.setText(pourcent+"%");
	}

	@FXML
	private void doVoiture() {
		masquer();
		idposte = 5; 
		nombrePlaceRestant = dao.totalDisponilbeParPoste(idposte)-dao.compterParPoste(idposte);
		
		circle40.setOpacity(0.80);
		
		textArea.clear();
		textArea.appendText( nombrePlaceRestant + " place(s) restante(s) " + "sur " + dao.totalDisponilbeParPoste(idposte) +" disponible(s) pour ce poste");	
	
		int pourcent = dao.compterParPoste(idposte)*100/dao.totalDisponilbeParPoste(idposte);
		couleur(pourcent);
		rectPlace.setWidth(pourcent*2.5);
 		pourcentage.setText(pourcent+"%");
	}
	
	@FXML
	private void doVelo() {
		masquer();
		idposte = 6; 
		nombrePlaceRestant = dao.totalDisponilbeParPoste(idposte)-dao.compterParPoste(idposte);
		
		circle41.setOpacity(0.80);
		
		textArea.clear();
		textArea.appendText( nombrePlaceRestant + " place(s) restante(s) " + "sur " + dao.totalDisponilbeParPoste(idposte) +" disponible(s) pour ce poste");
		
		int pourcent = dao.compterParPoste(idposte)*100/dao.totalDisponilbeParPoste(idposte);
		couleur(pourcent);
		rectPlace.setWidth(pourcent*2.5);
 		pourcentage.setText(pourcent+"%");
	}
	
	@FXML
	private void doRemise() {
		masquer();
		idposte = 7; 
		nombrePlaceRestant = dao.totalDisponilbeParPoste(idposte)-dao.compterParPoste(idposte);
		
		circle39.setOpacity(0.80);
		
		textArea.clear();
		textArea.appendText( nombrePlaceRestant + " place(s) restante(s) " + "sur " + dao.totalDisponilbeParPoste(idposte) +" disponible(s) pour ce poste");	
		
		int pourcent = dao.compterParPoste(idposte)*100/dao.totalDisponilbeParPoste(idposte);
		couleur(pourcent);
		rectPlace.setWidth(pourcent*2.5);
 		pourcentage.setText(pourcent+"%");
	}
	
	@FXML
	private void doRavitaillement() {
		masquer();
		idposte = 8; 
		nombrePlaceRestant = dao.totalDisponilbeParPoste(idposte)-dao.compterParPoste(idposte);
		
		circle34.setOpacity(0.80);
		circle28.setOpacity(0.80);
		circle21.setOpacity(0.80);
		circle15.setOpacity(0.80);
		circle10.setOpacity(0.80);
		
		textArea.clear();
		textArea.appendText( nombrePlaceRestant + " place(s) restante(s) " + "sur " + dao.totalDisponilbeParPoste(idposte) +" disponible(s) pour ce poste");	
		
		int pourcent = dao.compterParPoste(idposte)*100/dao.totalDisponilbeParPoste(idposte);
		couleur(pourcent);
		rectPlace.setWidth(pourcent*2.5);
 		pourcentage.setText(pourcent+"%");
	}

	@FXML
	private void doSecu() {
		masquer();
		idposte = 9; 
		nombrePlaceRestant = dao.totalDisponilbeParPoste(idposte)-dao.compterParPoste(idposte);

		circle1.setOpacity(0.80);
		circle25.setOpacity(0.80);
		circle26.setOpacity(0.80);
		circle27.setOpacity(0.80);
		
		textArea.clear();
		textArea.appendText( nombrePlaceRestant + " place(s) restante(s) " + "sur " + dao.totalDisponilbeParPoste(idposte) +" disponible(s) pour ce poste");	
		
		int pourcent = dao.compterParPoste(idposte)*100/dao.totalDisponilbeParPoste(idposte);
		couleur(pourcent);
		rectPlace.setWidth(pourcent*2.5);
 		pourcentage.setText(pourcent+"%");
	}
	
	@FXML
	private void doChrono() {
		masquer();
		idposte = 10; 
		nombrePlaceRestant = dao.totalDisponilbeParPoste(idposte)-dao.compterParPoste(idposte);
		
		circle12.setOpacity(0.80);
		circle13.setOpacity(0.80);
		circle31.setOpacity(0.80);
		circle7.setOpacity(0.80);
		circle23.setOpacity(0.80);
		
		textArea.clear();
		textArea.appendText( nombrePlaceRestant + " place(s) restante(s) " + "sur " + dao.totalDisponilbeParPoste(idposte) +" disponible(s) pour ce poste");	
		
		int pourcent = dao.compterParPoste(idposte)*100/dao.totalDisponilbeParPoste(idposte);
		couleur(pourcent);
		rectPlace.setWidth(pourcent*2.5);
 		pourcentage.setText(pourcent+"%");
	}
	
	@FXML
	private void doRepas() {
		masquer();
		idposte = 11; 
		nombrePlaceRestant = dao.totalDisponilbeParPoste(idposte)-dao.compterParPoste(idposte);
		
		circle4.setOpacity(0.80);
		
		textArea.clear();
		textArea.appendText( nombrePlaceRestant + " place(s) restante(s) " + "sur " + dao.totalDisponilbeParPoste(idposte) +" disponible(s) pour ce poste");	
		
		int pourcent = dao.compterParPoste(idposte)*100/dao.totalDisponilbeParPoste(idposte);
		couleur(pourcent);
		rectPlace.setWidth(pourcent*2.5);
 		pourcentage.setText(pourcent+"%");
	}
	
	@FXML
	private void doRecup() {
		masquer();
		idposte = 12; 
		nombrePlaceRestant = dao.totalDisponilbeParPoste(idposte)-dao.compterParPoste(idposte);

		circle2.setOpacity(0.80);
		
		textArea.clear();
		textArea.appendText( nombrePlaceRestant + " place(s) restante(s) " + "sur " + dao.totalDisponilbeParPoste(idposte) +" disponible(s) pour ce poste");
		
		int pourcent = dao.compterParPoste(idposte)*100/dao.totalDisponilbeParPoste(idposte);
		couleur(pourcent);
		rectPlace.setWidth(pourcent*2.5);
 		pourcentage.setText(pourcent+"%");
	}	
}
