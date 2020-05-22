package projet.view.memo;

import java.time.LocalDate;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoMemo;
import projet.data.Categorie;
import projet.data.Memo;
import projet.view.personne.ModelCategorie;


public class ModelMemo  {
	
	
	// Données observables 
	
	private final ObservableList<Memo> liste = FXCollections.observableArrayList(); 
	
	private final Memo	courant = new Memo();

	
	// Autres champs
    @Inject
	private IMapper			mapper;
    @Inject
	private DaoMemo	daoMemo;
    @Inject
    private ModelCategorie modelCategorie;
	
	
	// Getters 
	
	public ObservableList<Memo> getListe() {
		return liste;
	}
	
	public Memo getCourant() {
		return courant;
	}
	
	public ObservableList<Categorie> getCategories() {
		 return modelCategorie.getListe();
	}

	
	// Actualisations
	
	public void actualiserListe() {
		liste.setAll( daoMemo.listerTout() );
 	}


	// Actions
	
	public void preparerAjouter() {
		modelCategorie.actualiserListe();
		mapper.update( courant, new Memo() );
	}
	
	public void preparerModifier( Memo item ) {
		modelCategorie.actualiserListe();
		mapper.update( courant, daoMemo.retrouver( item.getId() ) );
	}
	
	
	public void validerMiseAJour() {

		// Vérifie la validité des données
		
		StringBuilder message = new StringBuilder();

		if( courant.getTitre() == null || courant.getTitre().isEmpty() ) {
			message.append( "\nLe mémo ne doit pas être vide." );
		} else  if ( courant.getTitre().length()> 50 ) {
			message.append( "\nLe mémo est trop long : 50 maxi." );
		}
		
		if( courant.getEffectif() != null) {
			if(courant.getEffectif()<0)
			{
				message.append( "\nL'effectif ne peut pas être inférieur à zéro." );
			}
			else if(courant.getEffectif()>1000) {
				message.append( "\nEffectif trop grand : 1000 maxi" );
			}
			
		}
		
		if( courant.getBudget() != null) {
			if(courant.getBudget()<0)
			{
				message.append( "\nLe Budget ne peut pas être inférieur à zéro." );
			}
			else if(courant.getBudget()>1000000) {
				message.append( "\nBudget trop grand : 1 000 000 maxi" );
			}
			
		}
		
		if( courant.getEcheance() != null) {
			
			LocalDate mini=LocalDate.of(2000, 01, 01);
			LocalDate maxi=LocalDate.of(2099, 12, 31);
	
			if(courant.getEcheance().isBefore(mini))
			{
				message.append( "\nLa date d'échéance doit être comprise entre le 01/01/2000 et le 31/12/2099." );
			}		
			if(courant.getEcheance().isAfter(maxi)) {
				message.append( "\nLa date d'échéance doit être comprise entre le 01/01/2000 et le 31/12/2099." );
			}
			
		}
		
		if ( message.length() > 0 ) {
			throw new ExceptionValidation( message.toString().substring(1) );
		}
		
		
		// Effectue la mise à jour
		
		if ( courant.getId() == null ) {
			// Insertion
			courant.setId( daoMemo.inserer( courant ) );
		} else {
			// modficiation
			daoMemo.modifier( courant );
		}
	}
	
	
	public void supprimer( Memo item ) {
		
		// Vérifie l'abence de personnes rattachées à la catégorie
		daoMemo.supprimer( item.getId() );
		mapper.update( courant, UtilFX.findNext( liste, item ) );
	}
	
}
