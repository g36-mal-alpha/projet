package projet.view.participant;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoHierarchie;
import projet.dao.DaoParticipant;
import projet.data.Hierarchie;



public class ModelHierarchie  {
	
	
	// Données observables 
	
	private final ObservableList<Hierarchie> liste = FXCollections.observableArrayList(); 
	
	private final Hierarchie	courant = new Hierarchie();

	
	// Autres champs
    @Inject
	private IMapper			mapper;
    @Inject
	private DaoHierarchie	daoHierarchie;
    @Inject
    private DaoParticipant	daoParticipant;
    @Inject
    private ModelHierarchie  modelHierarchie;
	
	// Getters 
	
	public ObservableList<Hierarchie> getListe() {
		return liste;
	}
	
	public Hierarchie getCourant() {
		return courant;
	}
	
	
	// Actualisations
	
	public void actualiserListe() {
		liste.setAll( daoHierarchie.listerTout() );
 	}


	// Actions
	
	public void preparerAjouter() {
		mapper.update( courant, new Hierarchie() );
	}
	
	public void preparerModifier( Hierarchie item ) {
		modelHierarchie.actualiserListe();
		mapper.update( courant, daoHierarchie.retrouver( item.getId() ) );
	}
	
	
	public void validerMiseAJour() {

		// Vérifie la validité des données
		
		//StringBuilder message = new StringBuilder();
		
		// Effectue la mise à jour
		
		if ( courant.getId() == null ) {
			// Insertion
			courant.setId( daoHierarchie.inserer( courant ) );
		} else {
			// modficiation
			daoHierarchie.modifier( courant );
		}
	}
	
	
	public void supprimer( Hierarchie item ) {
		
		// Vérifie l'abence de personnes rattachées à la catégorie
		if ( daoParticipant.compterPourSexe( item.getId() ) != 0 ) {
			throw new ExceptionValidation( "Des personnes sont rattachées à cette catégorie.." ) ;
		}
		
		daoHierarchie.supprimer( item.getId() );
		mapper.update( courant, UtilFX.findNext( liste, item ) );
	}
	
}
