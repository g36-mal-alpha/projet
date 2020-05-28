package projet.view.equipe;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.util.converter.IntegerStringConverter;
import jfox.javafx.util.ConverterStringInteger;
import jfox.javafx.util.ListenerFocusValidation;
import jfox.javafx.view.IManagerGui;
import projet.data.Service;
import projet.view.EnumView;


public class ControllerEquipeForm {

	
	// Composants de la vue
	
	@FXML
	private TextField		textFieldId;
	@FXML
	private TextField		textFieldNom;
	@FXML
	private TextField		textFieldAnneeCreation;
	@FXML
	private CheckBox		checkBoxSiege;

	
	// Autres champs
	
	@Inject
	private IManagerGui		managerGui;
	@Inject
	private ModelEquipe	modelEquipe;


	// Initialisation du Controller

	@FXML
	private void initialize() {

		// Data binding
		
		Service courant = modelEquipe.getCourant();

		textFieldId.textProperty().bindBidirectional( courant.idProperty(), new IntegerStringConverter()  );

		textFieldNom.textProperty().bindBidirectional( courant.nomProperty() );
		
		textFieldAnneeCreation.textProperty().bindBidirectional( courant.anneeCreationProperty(), new ConverterStringInteger( "###0" ) );
		textFieldAnneeCreation.focusedProperty().addListener( new ListenerFocusValidation( courant.anneeCreationProperty()  ));
		
		checkBoxSiege.selectedProperty().bindBidirectional( courant.flagSiegeProperty() );
		
	}
	
	
	// Actions
	
	@FXML
	private void doAnnuler() {
		managerGui.showView( EnumView.ServiceListe );
	}
	
	@FXML
	private void doValider() {
		modelEquipe.validerMiseAJour();
		managerGui.showView( EnumView.ServiceListe );
	}
	
}
