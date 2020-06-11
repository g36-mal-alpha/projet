package projet.view.participant;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoSexe;
import projet.dao.DaoParticipant;
import projet.data.Sexe;


public class ModelSexe  {
	
	
	// Données observables 
	
	private final ObservableList<Sexe> liste = FXCollections.observableArrayList(); 
	
	private final Sexe	courant = new Sexe();

	
	// Autres champs
    @Inject
	private IMapper			mapper;
    @Inject
	private DaoSexe			daoSexe;
    @Inject
    private DaoParticipant	daoParticipant;
    @Inject
    private ModelSexe  modelSexe;
	
	// Getters 
	
	public ObservableList<Sexe> getListe() {
		return liste;
	}
	
	public Sexe getCourant() {
		return courant;
	}
	
	
	// Actualisations
	
	public void actualiserListe() {
		liste.setAll( daoSexe.listerTout() );
 	}


	// Actions
	
	public void preparerAjouter() {
		mapper.update( courant, new Sexe() );
	}
	
	public void preparerModifier( Sexe item ) {
		modelSexe.actualiserListe();
		mapper.update( courant, daoSexe.retrouver( item.getId() ) );
	}
	
	
	public void validerMiseAJour() {

		// Vérifie la validité des données
		
		//StringBuilder message = new StringBuilder();
		
		// Effectue la mise à jour
		
		if ( courant.getId() == null ) {
			// Insertion
			courant.setId( daoSexe.inserer( courant ) );
		} else {
			// modficiation
			daoSexe.modifier( courant );
		}
	}
	
	
	public void supprimer( Sexe item ) {
		
		// Vérifie l'abence de personnes rattachées à la catégorie
		if ( daoParticipant.compterPourSexe( item.getId() ) != 0 ) {
			throw new ExceptionValidation( "Des personnes sont rattachées à cette catégorie.." ) ;
		}
		
		daoSexe.supprimer( item.getId() );
		mapper.update( courant, UtilFX.findNext( liste, item ) );
	}
	
}
