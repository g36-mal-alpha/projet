package projet.data;

import java.time.LocalDate;
import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Benevole {
	
	
	// Champs
	
	private final Property<Integer>		id				= new SimpleObjectProperty<>();
	private final StringProperty   	 	nom     	  	= new SimpleStringProperty();
	private final StringProperty   	 	prenom     	    = new SimpleStringProperty();
	private final Property<LocalDate>   date_naissance  = new SimpleObjectProperty<LocalDate>();
	private final StringProperty   	 	permis_conduire = new SimpleStringProperty();
	private final Property<Boolean>		mineurs		    = new SimpleObjectProperty<>( false );
	//private final BooleanProperty		mineurs		    = new SimpleBooleanProperty();
	private final Property<Boolean>		permanent		= new SimpleObjectProperty<>( false );
	//private final BooleanProperty		permanent		= new SimpleBooleanProperty();
	 private final Property<Categorie> categorie        = new SimpleObjectProperty<>();
	 private final Property<Poste>		poste 			= new SimpleObjectProperty<>();
	 private final ObservableList<Poste> postes    		= FXCollections.observableArrayList();
		
	// Constructeurs
		
	public Benevole() {
	}

	public Benevole( int id, String nom, String prenom, LocalDate date_naissance, String permis_conduire, Boolean mineurs, Boolean permanent, Categorie categorie, Poste poste ) {
		setId(id);
		setNom(nom);
		setPrenom(prenom);
		setDate_naissance(date_naissance);
		setPermis_conduire(permis_conduire);
		setMineurs(mineurs);
		setPermanent(permanent);
		setCategorie(categorie);
		setPoste(poste);
	}
	
	 
	// Getters & setters

	public ObservableList<Poste> getPostes() {
		return postes;
	}

	public final Property<Integer> idProperty() {
		return this.id;
	}
	
	public final Integer getId() {
		return this.idProperty().getValue();
	}
	
	public final void setId(final Integer id) {
		this.idProperty().setValue(id);
	}
	
	public final StringProperty nomProperty() {
		return this.nom;
	}
	
	public final String getNom() {
		return this.nomProperty().get();
	}
	
	public final void setNom(final String nom) {
		this.nomProperty().set(nom);
	}
	

	public final StringProperty prenomProperty() {
		return this.prenom;
	}
	

	public final String getPrenom() {
		return this.prenomProperty().get();
	}
	

	public final void setPrenom(final String prenom) {
		this.prenomProperty().set(prenom);
	}
	
	public final Property<LocalDate> date_naissanceProperty() {
		return this.date_naissance;
	}

	public final LocalDate getDate_naissance() {
		return this.date_naissanceProperty().getValue();
	}
	

	public final void setDate_naissance(final LocalDate date_naissance) {
		this.date_naissanceProperty().setValue(date_naissance);
	}

	public final StringProperty permis_conduireProperty() {
		return this.permis_conduire;
	}
	

	public final String getPermis_conduire() {
		return this.permis_conduireProperty().get();
	}
	

	public final void setPermis_conduire(final String permis_conduire) {
		this.permis_conduireProperty().set(permis_conduire);
	}
	
//	public final Property<Boolean> mineursProperty() {
//	return this.mineurs;
//}
//
//public final Boolean getMineurs() {
//	return this.mineursProperty().getValue();
//}
//
//public final void setMineurs(final Boolean mineurs) {
//	this.mineursProperty().setValue(mineurs);
//}


	public final Property<Boolean> mineursProperty() {
		return this.mineurs;
	}
	

	public final Boolean getMineurs() {
		return this.mineursProperty().getValue();
	}
	

	public final void setMineurs(final Boolean mineurs) {
		this.mineursProperty().setValue(mineurs);
	}
	
	
//	public final Property<Boolean> permanentProperty() {
//	return this.permanent;
//}
//
//public final Boolean getPermanent() {
//	return this.permanentProperty().getValue();
//}
//
//public final void setPermanent(final Boolean permanent) {
//	this.permanentProperty().setValue(permanent);
//}


	public final Property<Boolean> permanentProperty() {
		return this.permanent;
	}
	

	public final Boolean getPermanent() {
		return this.permanentProperty().getValue();
	}
	

	public final void setPermanent(final Boolean permanent) {
		this.permanentProperty().setValue(permanent);
	}
	
	
	public final Property<Categorie> categorieProperty() {
		return this.categorie;
	}
	

	public final Categorie getCategorie() {
		return this.categorieProperty().getValue();
	}
	

	public final void setCategorie(final Categorie categorie) {
		this.categorieProperty().setValue(categorie);
	}
	

	
	// hashCode() & equals()

	@Override
	public int hashCode() {
		return Objects.hash(id.getValue());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Benevole other = (Benevole) obj;
		return Objects.equals(id.getValue(), other.id.getValue());
	}

	
	// toString()
	
	@Override
	public String toString() {
		return getNom() + " " +getPrenom();
	}

	public final Property<Poste> posteProperty() {
		return this.poste;
	}
	

	public final Poste getPoste() {
		return this.posteProperty().getValue();
	}
	

	public final void setPoste(final Poste poste) {
		this.posteProperty().setValue(poste);
	}
	

}
