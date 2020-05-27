package projet.view.benevole;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoBenevole;
import projet.data.Benevole;
import projet.data.Categorie;
import projet.data.Poste;
import projet.view.poste.ModelPoste;

public class ModelBenevole {
	
	// Données observables 
	
		private final ObservableList<Benevole> liste = FXCollections.observableArrayList(); 
		
		private final Benevole	courant = new Benevole();
		
		
		// Autres champs
	    @Inject
		private IMapper			mapper;
	    @Inject
		private DaoBenevole		daoBenevole;
	    @Inject
	    private ModelPoste		modelPoste;
		
		
		// Getters
		
		public ObservableList<Benevole> getListe() {
			return liste;
		}

		public Benevole getCourant() {
			return courant;
		}
		
		public ObservableList<Poste> getListePoste() {
			 return modelPoste.getListe();
		}
		

		
		
		// Actualisations
		
		public void actualiserListe() {
			liste.setAll( daoBenevole.listerTout() );
	 	}
		
		
		// Actions
		
		public void preparerAjouter() {
			mapper.update( courant, new Benevole() );
		}

		
		public void preparerModifier( Benevole item ) {
			mapper.update( courant, daoBenevole.retrouver( item.getId() ) );
		}
		
		
		public void validerMiseAJour() {
			
			
			// Effectue la mise à jour
			
			if ( courant.getId() == null ) {
				// Insertion
				courant.setId( daoBenevole.inserer( courant ) );
			} else {
				// modficiation
				daoBenevole.modifier( courant );
			}
		}
	
		
		public void supprimer( Benevole item ) {
			daoBenevole.supprimer( item.getId() );
			mapper.update( courant, UtilFX.findNext( liste, item ) );
		}

}
