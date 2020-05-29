package projet.view;

import javafx.scene.Scene;
import jfox.javafx.view.IEnumView;


public enum EnumView implements IEnumView {

	
	// Valeurs
	
	Info						( "systeme/ViewInfo.fxml" ),
	Connexion					( "systeme/ViewPageDeConnexion.fxml" ),
	CompteListe					( "compte/ViewCompteListe.fxml" ),
	CompteForm					( "compte/ViewCompteForm.fxml" ),
	CategorieListe				( "personne/ViewCategorieListe.fxml" ),
	CategorieForm				( "personne/ViewCategorieForm.fxml" ),
	PersonneListe				( "personne/ViewPersonneListe.fxml" ),
	PersonneForm				( "personne/ViewPersonneForm.fxml" ),
	ServiceListe	      	    ( "service/ViewServiceListe.fxml" ),
	ServiceForm					( "service/ViewServiceForm.fxml" ),
	PosteListe		   			( "poste/ViewPosteListe.fxml" ),
	PosteAjoutBenevoles		    ( "poste/ViewPosteAjoutBenevoles.fxml" ),
	PosteForm					( "poste/ViewPosteForm.fxml" ),
	PosteFormDetail			    ( "poste/ViewPosteFormDetail.fxml" ),
	BenevoleListe				( "benevole/ViewBenevoleList.fxml" ),
	BenevoleForm				( "benevole/ViewBenevoleForm.fxml" ),
	EquipeForm				    ( "equipe/ViewEquipeForm.fxml" ),
	EquipeListe				    ( "equipe/ViewEquipeListe.fxml" ),
	EpreuveForm				    ( "epreuve/ViewEpreuveForm.fxml" ),
	EpreuveListe			    ( "epreuve/ViewEpreuveListe.fxml" ),
	InfoParticipant 			("participant/ViewInfoParticipant.fxml"),
	ParticipantListe 			("participant/ViewListeParticipant.fxml"),
	ParticipantForm				("participant/ViewParticipantForm.fxml"),
	TestDaoCategorie			( "test/ViewTestDaoCategorie.fxml" ),
	TestDaoService				( "test/ViewTestDaoService.fxml" ),
	TestDaoBenevole				( "test/ViewTestDaoBenevole.fxml" ),
	TestDaoParticipant 		    ( "test/ViewTestDaoParticipant.fxml" ),
	TestDaoEquipe      			( "test/ViewTestDaoEquipe.fxml" ),
	TestDaoPoste				( "test/ViewTestDaoPoste.fxml" ),
	TestDaoEpreuve				( "test/ViewTestDaoEpreuve.fxml" ),
	TestDaoDocuments			( "test/ViewTestDaoDocuments.fxml" ),
	TestDaoSexe			        ( "test/ViewTestDaoSexe.fxml" ),
	TestDaoHierarchie			( "test/ViewTestDaoHierarchie.fxml" ),
	EtatPersonnesParCateogire1	( "personne/ViewEtatPersonnesParCategorie1.fxml" ),
	EtatPersonnesParCateogire2	( "personne/ViewEtatPersonnesParCategorie2.fxml" ),
	PosteList					("postes/ViewPosteList.fxml"),
	ViewMap						( "map/ViewMap.fxml" );

	
	// Champs
	
	private String		path;
	private Object		controller;
	private Scene		scene;

	
	// Constructeur 
	
	EnumView( String path ) {
		this.path = path;
	}

	
	// Getters & setters

	@Override
	public String getPath() {
		return path;
	}
	
	@Override
	public Object getController() {
		return controller;
	}

	@Override
	public void setController(Object controller) {
		this.controller = controller;
	}
	
	@Override
	public Scene getScene() {
		return scene;
	}
	
	@Override
	public void setScene(Scene scene) {
		this.scene = scene;
	}

}
