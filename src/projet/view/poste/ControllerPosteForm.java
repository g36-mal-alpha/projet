package projet.view.memo;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.util.converter.IntegerStringConverter;
import jfox.javafx.util.ConverterStringDouble;
import jfox.javafx.util.ConverterStringInteger;
import jfox.javafx.util.ConverterStringLocalDate;
import jfox.javafx.util.ListenerFocusValidation;
import jfox.javafx.view.IManagerGui;
import projet.data.Categorie;
import projet.data.Memo;
import projet.view.EnumView;


public class ControllerMemoForm {

	
	// Composants de la vue
	
	@FXML
	private TextField			textFieldId;
	@FXML
	private TextField			textFieldTitre;
	@FXML
	private TextArea			textAreaDescription;
	@FXML
	private CheckBox			checkBoxUrgent;
	@FXML
	private ToggleGroup			toggleGroupStatut;
	@FXML
	private TextField			textFieldEffectif;
	@FXML
	private TextField			textFieldBudget;
	@FXML
	private DatePicker			datePickerEcheance;
	@FXML
	private ComboBox<Categorie>	comboBoxCategorie;


	// Autres champs
	
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelMemo		modelMemo;


	// Initialisation du Controller

	@FXML
	private void initialize() {

		// Data binding
		
		Memo courant = modelMemo.getCourant();
		actualiserStatutDansVue();
		textFieldId.textProperty().bindBidirectional( courant.idProperty(), new IntegerStringConverter()  );
		textFieldTitre.textProperty().bindBidirectional( courant.titreProperty()  );
		textAreaDescription.textProperty().bindBidirectional( courant.descriptionProperty()  );
		checkBoxUrgent.selectedProperty().bindBidirectional( courant.flagUrgentProperty()  );
		
		toggleGroupStatut.selectedToggleProperty().addListener( obs -> actualiserStatutDansModele() ) ; 
		courant.statutProperty().addListener( obs -> actualiserStatutDansVue() );
		
		textFieldEffectif.textProperty().bindBidirectional( courant.effectifProperty(), new ConverterStringInteger()  );
		textFieldEffectif.focusedProperty().addListener(new ListenerFocusValidation( courant.effectifProperty() ) );
		
		textFieldBudget.textProperty().bindBidirectional( courant.budgetProperty(), new ConverterStringDouble("#,##0.00") );
		textFieldBudget.focusedProperty().addListener(new ListenerFocusValidation( courant.budgetProperty(), "Valeur incorrecte pour le budget." ) );
		
		datePickerEcheance.getEditor().textProperty().bindBidirectional( courant.echeanceProperty(), new ConverterStringLocalDate() );
		datePickerEcheance.getEditor().focusedProperty().addListener(new ListenerFocusValidation( courant.echeanceProperty(), "Valeur de l'échéance incorrect." ) );
		
		comboBoxCategorie.setItems( modelMemo.getCategories() );
		comboBoxCategorie.valueProperty().bindBidirectional( courant.categorieProperty());

	}
	
	
	// Actions
	
	@FXML
	private void doAnnuler() {
		managerGui.showView( EnumView.MemoListe );
	}
	
	@FXML
	private void doValider() {
		modelMemo.validerMiseAJour();
		managerGui.showView( EnumView.MemoListe );
	}
	
	@FXML
	private void doSupprimerCategorie() {
	 comboBoxCategorie.setValue( null );
	}

	
	//Statut Radio
	private void actualiserStatutDansModele() { 
		
		// Modifie le statut en fonction du bouton radio sélectionné  
		
		Toggle bouton = toggleGroupStatut.getSelectedToggle();    
		int statut = toggleGroupStatut.getToggles().indexOf( bouton  );    
		modelMemo.getCourant().setStatut( statut );
	}
	
	
	private void actualiserStatutDansVue() {   
		// Sélectionne le bouton radio correspondant au statut   
		
		int statut = modelMemo.getCourant().getStatut();   
		Toggle bouton = toggleGroupStatut.getToggles().get( statut ); 
		toggleGroupStatut.selectToggle(  bouton );
	}
	
	
}
