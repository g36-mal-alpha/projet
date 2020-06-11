package projet.view.epreuve;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.converter.IntegerStringConverter;
import jfox.javafx.util.ConverterStringInteger;
import jfox.javafx.util.ConverterStringLocalDate;
import jfox.javafx.util.ListenerFocusValidation;
import jfox.javafx.view.IManagerGui;
import projet.data.Epreuve;
import projet.view.EnumView;


public class ControllerEpreuveForm {

	
	// Composants de la vue
	
	@FXML
	private TextField		textFieldId;
	@FXML
	private TextField		textFieldNom;
	@FXML
	private TextField		textFieldTarif;
	@FXML
	private TextField		textFieldLieu;
	@FXML
	private DatePicker	    datePickerDate;


	
	// Autres champs
	
	@Inject
	private IManagerGui		managerGui;
	@Inject
	private ModelEpreuve	modelEpreuve;


	// Initialisation du Controller

	@FXML
	private void initialize() {

		// Data binding
		
		Epreuve courant = modelEpreuve.getCourant();

		textFieldId.textProperty().bindBidirectional( courant.idProperty(), new IntegerStringConverter()  );

		textFieldNom.textProperty().bindBidirectional( courant.nom_epreuveProperty() );
		
		textFieldTarif.textProperty().bindBidirectional( courant.tarifProperty(), new ConverterStringInteger( "###0" ) );
		textFieldTarif.focusedProperty().addListener( new ListenerFocusValidation( courant.tarifProperty()  ));
		
		textFieldLieu.textProperty().bindBidirectional( courant.lieuProperty() );
		
		datePickerDate.getEditor().textProperty().bindBidirectional( courant.date_epreuveProperty(), new ConverterStringLocalDate() );
		datePickerDate.getEditor().focusedProperty().addListener(new ListenerFocusValidation( courant.date_epreuveProperty(), "Jour incorrect." ) );
		
		
	}
	
	
	// Actions
	
	@FXML
	private void doAnnuler() {
		managerGui.showView( EnumView.EpreuveListe);
	}
	
	@FXML
	private void doValider() {
		modelEpreuve.validerMiseAJour();
		managerGui.showView( EnumView.EpreuveListe );
	}
	
}
