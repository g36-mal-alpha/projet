package projet.view.benevole;

import java.time.LocalDate;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoBenevole;
import projet.dao.DaoPoste;
import projet.data.Benevole;
import projet.data.Categorie;
import projet.data.Poste;
import projet.view.map.ControllerViewMap;
import projet.view.personne.ModelCategorie;
import projet.view.poste.ModelPoste;

public class ModelBenevole {
	
	// Données observables 
	
		private final ObservableList<Benevole> liste = FXCollections.observableArrayList();
		
		private final ObservableList<Poste> postesPourDialogAjout = FXCollections.observableArrayList();

		private final Benevole	courant = new Benevole();
		
		
		
		// Autres champs
	    @Inject
		private IMapper			mapper;
	    @Inject
		private DaoBenevole		daoBenevole;
	    @Inject
		private DaoPoste	    daoPoste;
	    @Inject
	    private ModelPoste		modelPoste;
	    @Inject
	    private ModelCategorie  modelCategorie;
	    
		
		// Getters
	    
		public Benevole getCourant() {
			return courant;
		}
		
		public ObservableList<Categorie> getCategorie() {
			 return modelCategorie.getListe();
		}
	    
		public ObservableList<Benevole> getListe() {
			return liste;
		}
		
		public ObservableList<Poste> getPostesPourDialogAjout() {
			 return postesPourDialogAjout;
		}
		
		
		// Actualisations
		
		public void actualiserListe() {
			liste.setAll( daoBenevole.listerTout() );
	 	}
		
		public void  actualiserListePostesPourDialogAjout() {
			postesPourDialogAjout.setAll( daoPoste.listerTout());
			postesPourDialogAjout.removeAll( courant.getPostes() );

	 	}
		
		
		// Actions
		
		public void preparerAjouter() {
			modelPoste.actualiserListe();
			mapper.update( courant, new Benevole() );
		}

		
		public void preparerModifier( Benevole item ) {
			modelPoste.actualiserListe();
			mapper.update( courant, daoBenevole.retrouver( item.getId() ) );
		}
		
	
		public void validerMiseAJour() {
			
			// Vérifie la validité des données
			
			StringBuilder message = new StringBuilder();
		
			/*int nombrePlaceRestant = daoPoste.totalDisponilbeParPoste(idposte)-daoPoste.compterParPoste(idposte);
			if (nombrePlaceRestant<=0) {
				message.append( "\nDesolé plus de places disponibles pour ce poste" );
			}
			*/
			
			if( courant.getNom() == null || courant.getNom().isEmpty() ) {
				message.append( "\nLe nom du benevole ne doit pas être vide." );
			} else  if ( courant.getNom().length()> 50 ) {
				message.append( "\nLe nom est trop long : 50 maxi." );
			}
			
			if( courant.getPrenom() == null || courant.getPrenom().isEmpty() ) {
				message.append( "\nLe lieu du poste ne doit pas être vide." );
			} else  if ( courant.getPrenom().length()> 50 ) {
				message.append( "\nLe lieu est trop long : 50 maxi." );
			}
			
			if( courant.getDate_naissance() != null) {
				
				LocalDate mini=LocalDate.of(1900, 01, 01);
				LocalDate maxi=LocalDate.of(2021, 12, 31);
		
				if(courant.getDate_naissance().isBefore(mini))
				{
					message.append( "\nLe jour doit être comprise entre le 01/01/1900 et le 31/12/2021." );
				}		
				if(courant.getDate_naissance().isAfter(maxi)) {
					message.append( "\nLe jour doit être comprise entre le 01/01/1900 et le 31/12/2021." );
				}
				
			}
			
			if ( message.length() > 0 ) {
				throw new ExceptionValidation( message.toString().substring(1) );
			}
			
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
		
		public void supprimerPoste( Poste item ) {
			 courant.getPostes().remove( item );
		}
			
		public void ajouterBenevole( Poste item ) {
			 courant.getPostes().add( item );
		}
		
		public void preparerDetail() {

		}
		
		

}
