package projet.view.equipe;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.util.converter.IntegerStringConverter;
import jfox.javafx.util.ConverterStringInteger;
import jfox.javafx.util.ListenerFocusValidation;
import jfox.javafx.view.IManagerGui;
import projet.data.Equipe;
import projet.view.EnumView;


public class ControllerEquipeForm {

	
	// Composants de la vue
	
	@FXML
	private TextField		textFieldId;
	@FXML
	private TextField		textFieldNom;
	@FXML
	private TextField		textFieldNb_plateau;
	@FXML
	private CheckBox		checkBoxValide;
	@FXML
	private CheckBox		checkBoxPaye;
	
	// Autres champs
	
	@Inject
	private IManagerGui		managerGui;
	@Inject
	private ModelEquipe	modelEquipe;


	// Initialisation du Controller

	@FXML
	private void initialize() {

		// Data binding
		
		Equipe courant = modelEquipe.getCourant();

		textFieldId.textProperty().bindBidirectional( courant.idProperty(), new IntegerStringConverter()  );

		textFieldNom.textProperty().bindBidirectional( courant.nom_equipeProperty() );
		
		textFieldNb_plateau.textProperty().bindBidirectional( courant.nb_plateauProperty(), new ConverterStringInteger( "###0" ) );
		textFieldNb_plateau.focusedProperty().addListener( new ListenerFocusValidation( courant.nb_plateauProperty()  ));
		
		checkBoxPaye.selectedProperty().bindBidirectional( courant.payeProperty() );
		
		checkBoxPaye.selectedProperty().bindBidirectional( courant.valideProperty() );
	}
	
	
	// Actions
	
	@FXML
	private void doAnnuler() {
		managerGui.showView( EnumView.EquipeListe );
	}
	
	@FXML
	private void doValider() {
		modelEquipe.validerMiseAJour();
		managerGui.showView( EnumView.EquipeListe );
	}
	
}
