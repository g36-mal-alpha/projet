package projet.view.participant;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import projet.data.Sexe;

public class ModelSexe {
	
	// Données observables 
	
	private final ObservableList<Sexe> liste = FXCollections.observableArrayList(); 
	
	private final Sexe	courant1 = new Sexe();
	
	// Autres champs

	// Getters 
	
	public ObservableList<Sexe> getListe() {
		return liste;
	}
	
	public Sexe getCourant1() {
		return courant1;
	}

}
