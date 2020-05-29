package projet.view;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import jfox.javafx.view.IManagerGui;
import projet.commun.Roles;
import projet.data.Compte;
import projet.report.EnumReport;
import projet.report.ManagerReport;
import projet.view.systeme.ModelConnexion;


public class MenuBarAppli extends MenuBar {

	
	// Champs
	
	private Menu	menuDonnees;
	private Menu	menuEtats;
	private Menu	menuTests;
	private Menu 	menuMap;
	//private Menu 	menuPostes;
	
	private MenuItem itemDeconnecter;

	private MenuItem itemCategories;
	//private MenuItem itemSexe;
	//private MenuItem itemHierarchie;
	private MenuItem itemComptes;
	private MenuItem itemPostes;
	
	@Inject
	private IManagerGui 	managerGui;
	@Inject
	private ManagerReport 	managerReport;
	@Inject
	private ModelConnexion	modelConnexion;	
	
	
	// Initialisation
	
	@PostConstruct
	public void init() {

		
		// Variables de travail
		Menu menu;
		MenuItem item;
		
		
		// Manu SystÃ¨me
		
		menu =  new Menu( "Système" );
		this.getMenus().add(menu);
		
		item = new MenuItem( "Se déconnecter" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.Connexion )  );
		menu.getItems().add( item );
		itemDeconnecter = item;
		
		item = new MenuItem( "Quitter" );
		item.setOnAction(  (e) -> managerGui.exit()  );
		menu.getItems().add( item );

		
		// Manu DonnÃ©es
		
		menu =  new Menu( "Gestion" );;
		this.getMenus().add(menu);
		menuDonnees = menu;
		
		item = new MenuItem( "Services" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.ServiceListe )  );
		menu.getItems().add( item );
		
		item = new MenuItem( "Personnes" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.PersonneListe )  );
		menu.getItems().add( item );
		
		item = new MenuItem( "Bénévoles" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.BenevoleListe )  );
		menu.getItems().add( item );
		
		item = new MenuItem( "Equipes" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.EquipeListe )  );
		menu.getItems().add( item );

		
		item = new MenuItem( "Epreuves" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.EpreuveListe )  );
		menu.getItems().add( item );

		
		item = new MenuItem( "Participants" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.ParticipantListe  )  );
		menu.getItems().add( item );
		
		item = new MenuItem( "Catégories" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.CategorieListe )  );
		menu.getItems().add( item );
		itemCategories = item;
		
		item = new MenuItem( "Comptes" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.CompteListe )  );
		menu.getItems().add( item );
		itemComptes = item;
		
		item = new MenuItem( "Postes" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.PosteListe )  );
		menu.getItems().add( item );
		itemPostes = item;
		
		/*item = new MenuItem( "Sexe" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.SexeListe )  );
		menu.getItems().add( item );
		itemSexe = item;
		
		item = new MenuItem( "Hierarchie" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.HierarchieListe )  );
		menu.getItems().add( item );
		itemHierarchie = item;*/
		
		
		// Manu Etats
		
		menu =  new Menu( "Etats" );;
		this.getMenus().add(menu);
		menuEtats = menu;
		
		//Participants etat
		item = new MenuItem("Liste des participants (PDF)");
		item.setOnAction((e) ->  
				managerReport.openFilePdf( EnumReport.ParticipantsList, null ) );
		menu.getItems().add( item );
		
		item = new MenuItem( "Liste des participants (viewer)" );
		item.setOnAction(  (e) ->  
				managerReport.showViewer( EnumReport.ParticipantsList, null ) );
		menu.getItems().add( item );
		
		//Bénévoles état
		item = new MenuItem("Liste des bénévoles (PDF)");
		item.setOnAction((e) ->  
			managerReport.openFilePdf( EnumReport.BenevolesList, null ) );
		menu.getItems().add( item );
		
		item = new MenuItem( "Liste des bénévoles (viewer)" );
		item.setOnAction(  (e) ->  
				managerReport.showViewer( EnumReport.BenevolesList, null ) );
		menu.getItems().add( item );

		
		// Manu Tests
		
		menu =  new Menu( "Tests" );;
		this.getMenus().add(menu);
		menuTests = menu;
		
		item = new MenuItem( "DaoCategorie" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.TestDaoCategorie )  );
		menu.getItems().add( item );
		
		item = new MenuItem( "DaoSexe" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.TestDaoSexe )  );
		menu.getItems().add( item );
		
		item = new MenuItem( "DaoHierarchie" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.TestDaoHierarchie )  );
		menu.getItems().add( item );
		
		item = new MenuItem( "DaoService" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.TestDaoService )  );
		menu.getItems().add( item );
		
		item = new MenuItem( "DaoBenevole" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.TestDaoBenevole )  );
		menu.getItems().add( item );
		
		item = new MenuItem( "DaoParticipant" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.TestDaoParticipant )  );
		menu.getItems().add( item );
		
		item = new MenuItem( "DaoPoste" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.TestDaoPoste )  );
		menu.getItems().add( item );
		
		item = new MenuItem( "DaoEquipe" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.TestDaoEquipe )  );
		menu.getItems().add( item );
		
		item = new MenuItem( "DaoEpreuve" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.TestDaoEpreuve )  );
		menu.getItems().add( item );
		
		item = new MenuItem( "DaoDocuments" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.TestDaoDocuments )  );
		menu.getItems().add( item );
		
		

		
		/*
		// PROJET - Menu Postes
		menu = new Menu("Poste");
		this.getMenus().add(menu);
		menuPostes = menu;
		
		item = new MenuItem("Poste des bénévoles");
		item.setOnAction( (e) -> managerGui.showView( EnumView.PosteList )  );
		menu.getItems().add(item);
		*/
		
		/*Menu MAP*/
		menu =  new Menu( "Carte" );;
		this.getMenus().add(menu);
		menuMap = menu;
		
		item = new MenuItem( "Carte des signaleurs" );
		item.setOnAction(  (e) -> managerGui.showView( EnumView.ViewMap )  );
		menu.getItems().add( item );

		// Configuration initiale du menu
		configurerMenu( modelConnexion.getCompteActif() );

		// Le changement du compte connecté modifie automatiquement le menu
		modelConnexion.compteActifProperty().addListener( (obs) -> {
					Platform.runLater( () -> configurerMenu( modelConnexion.getCompteActif() ) );
				}
			); 
		
	}

	
	// MÃ©thodes auxiliaires
	
	private void configurerMenu( Compte compteActif  ) {

		itemDeconnecter.setDisable(true);
		
		menuDonnees.setVisible(false);
		itemCategories.setVisible(false);
		itemComptes.setVisible(false);
		itemPostes.setVisible(false);
		menuEtats.setVisible(false);
		menuTests.setVisible(false);
		menuEtats.setVisible(false);
		//menuPostes.setVisible(false);
		menuMap.setVisible(false);
		
		if( compteActif != null ) {
			itemDeconnecter.setDisable(false);
			if( compteActif.isInRole( Roles.UTILISATEUR) ) {
				menuDonnees.setVisible(true);
				menuEtats.setVisible(true);
				menuMap.setVisible(true);
			}
			if( compteActif.isInRole( Roles.ADMINISTRATEUR ) ) {
				menuDonnees.setVisible(true);
				itemCategories.setVisible(true);
				itemComptes.setVisible(true);
				itemPostes.setVisible(true);
				menuTests.setVisible(true);
				//menuPostes.setVisible(true);
				menuMap.setVisible(true);
			}
		}
	}
	
}
