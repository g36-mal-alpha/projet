package projet.view.poste;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.converter.IntegerStringConverter;
import jfox.javafx.util.ConverterStringLocalDate;
import jfox.javafx.util.ListenerFocusValidation;
import jfox.javafx.view.IManagerGui;
import projet.data.Categorie;
import projet.data.Poste;
import projet.view.EnumView;


public class ControllerPosteForm {

	
	// Composants de la vue
	
	@FXML
	private TextField			textFieldId;
	@FXML
	private TextField			textFieldLibelle;
	@FXML
	private TextArea			textAreaLieu;
	@FXML
	private DatePicker			datePickerJour;
	@FXML
	private DatePicker			datePickerHeure_debut;
	@FXML
	private DatePicker			datePickerHeure_fin;
	@FXML
	private ComboBox<Categorie>	comboBoxCategorie;


	// Autres champs
	
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelPoste		modelPoste;


	// Initialisation du Controller

	@FXML
	private void initialize() {

		// Data binding
		
		Poste courant = modelPoste.getCourant();
		//actualiserStatutDansVue();
		textFieldId.textProperty().bindBidirectional( courant.idProperty(), new IntegerStringConverter()  );
		
		textFieldLibelle.textProperty().bindBidirectional( courant.libelleProperty()  );
		
		textAreaLieu.textProperty().bindBidirectional( courant.lieuProperty()  );
	
		datePickerJour.getEditor().textProperty().bindBidirectional( courant.jourProperty(), new ConverterStringLocalDate() );
		datePickerJour.getEditor().focusedProperty().addListener(new ListenerFocusValidation( courant.jourProperty(), "Jour incorrect." ) );
		
		/*datePickerHeure_debut.getEditor().textProperty().bindBidirectional( courant.heure_debutProperty(), new ConverterStringLocalDate() );
		datePickerHeure_debut.getEditor().focusedProperty().addListener(new ListenerFocusValidation( courant.heure_debutProperty(), "Heure incorrect." ) );
		
		datePickerHeure_fin.getEditor().textProperty().bindBidirectional( courant.heure_finProperty(), new ConverterStringLocalDate() );
		datePickerHeure_fin.getEditor().focusedProperty().addListener(new ListenerFocusValidation( courant.heure_finProperty(), "Heure incorrect." ) );
		*/
		comboBoxCategorie.setItems( modelPoste.getCategories() );
		comboBoxCategorie.valueProperty().bindBidirectional( courant.categorieProperty());

	}
	
	
	// Actions
	
	@FXML
	private void doAnnuler() {
		managerGui.showView( EnumView.PosteListe );
	}
	
	@FXML
	private void doValider() {
		modelPoste.validerMiseAJour();
		managerGui.showView( EnumView.PosteListe );
	}
	
	@FXML
	private void doSupprimerCategorie() {
	 comboBoxCategorie.setValue( null );
	}

	/*
	//Statut Radio
	private void actualiserStatutDansModele() { 
		
		// Modifie le statut en fonction du bouton radio sélectionné  
		
		Toggle bouton = toggleGroupStatut.getSelectedToggle();    
		int statut = toggleGroupStatut.getToggles().indexOf( bouton  );    
		modelPoste.getCourant().setStatut( statut );
	}
	
	
	private void actualiserStatutDansVue() {   
		// Sélectionne le bouton radio correspondant au statut   
		
		int statut = modelPoste.getCourant().getStatut();   
		Toggle bouton = toggleGroupStatut.getToggles().get( statut ); 
		toggleGroupStatut.selectToggle(  bouton );
	}*/
	
	
}
