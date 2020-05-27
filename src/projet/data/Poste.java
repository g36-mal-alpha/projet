package projet.data;


import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Poste {
	
	
	// Champs
	
	private final Property<Integer>		id				= new SimpleObjectProperty<>();
	private final StringProperty   	 	libelle     	= new SimpleStringProperty();
	private final StringProperty   	 	lieu   	        = new SimpleStringProperty();
	private final Property<Integer>	    statut 		    = new SimpleObjectProperty<>(0);
	private final Property<LocalDate>   jour            = new SimpleObjectProperty<>();
	
	//private final Property<LocalTime> heure_debut     = new SimpleObjectProperty<>();
	private final StringProperty      heure_debut     = new SimpleStringProperty();
	//private final DateFormat            heure_debut     = new SimpleDateFormat("hh:mm:ss");
	
	/*Date parseDateString(String heure_debut){
    SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
    Date date = dateFormat.parse(heure_debut);
	}*/
	
	//private final Property<LocalTime> heure_fin       = new SimpleObjectProperty<>();
	private final StringProperty      heure_fin       = new SimpleStringProperty();
	//private final DateFormat            heure_fin       = new SimpleDateFormat("hh:mm:ss");
	
	private final Property<Integer>		numero_poste    = new SimpleObjectProperty<>();
	private final Property<Poste>       poste           = new SimpleObjectProperty<>();
	private final Property<Categorie>   categorie       = new SimpleObjectProperty<>();
	private final ObservableList<Benevole> benevoles    = FXCollections.observableArrayList();
	
	// Constructeurs
	
	public Poste() {
	}
	
	public Poste( int id, String libelle, String lieu, Integer statut, LocalDate jour, String heure_debut, String heure_fin, Integer numero_poste , Poste poste, Categorie categorie ) {
		setId(id);
		setLibelle(libelle);
		setLieu(lieu);
		setStatut(statut);
		setJour(jour);
		setHeure_debut(heure_debut);
		setHeure_fin(heure_fin);
		setNumero_poste(numero_poste);
		setPoste(poste);
		setCategorie(categorie);
	}
	
	
	
	// Getters & setters

	public final Property<Integer> idProperty() {
		return this.id;
	}
	
	public final Integer getId() {
		return this.idProperty().getValue();
	}
	
	public final void setId(final Integer id) {
		this.idProperty().setValue(id);
	}
	
	public final StringProperty libelleProperty() {
		return this.libelle;
	}
	

	public final String getLibelle() {
		return this.libelleProperty().get();
	}
	

	public final void setLibelle(final String libelle) {
		this.libelleProperty().set(libelle);
	}
	

	public final StringProperty lieuProperty() {
		return this.lieu;
	}
	

	public final String getLieu() {
		return this.lieuProperty().get();
	}
	

	public final void setLieu(final String lieu) {
		this.lieuProperty().set(lieu);
	}
	
	public final Property<Integer> statutProperty() {
		return this.statut;
	}
	
	public final Integer getStatut() {
		return this.statutProperty().getValue();
	}

	public final void setStatut(final Integer statut) {
		this.statutProperty().setValue(statut);
	}
	

	/*public final Property<LocalTime> heure_debutProperty() {
		return this.heure_debut;
	}
	

	public final LocalTime getHeure_debut() {
		return this.heure_debutProperty().getValue();
	}
	

	public final void setHeure_debut(final LocalTime heure_debut) {
		this.heure_debutProperty().setValue(heure_debut);
	}
	

	public final Property<LocalTime> heure_finProperty() {
		return this.heure_fin;
	}
	

	public final LocalTime getHeure_fin() {
		return this.heure_finProperty().getValue();
	}
	

	public final void setHeure_fin(final LocalTime heure_fin) {
		this.heure_finProperty().setValue(heure_fin);
	}*/
	 
	
	public final StringProperty heure_debutProperty() {
		return this.heure_debut;
	}
	

	public final String getHeure_debut() {
		return this.heure_debutProperty().get();
	}
	

	public final void setHeure_debut(final String heure_debut) {
		this.heure_debutProperty().set(heure_debut);
	}
	

	public final StringProperty heure_finProperty() {
		return this.heure_fin;
	}
	

	public final String getHeure_fin() {
		return this.heure_finProperty().get();
	}
	

	public final void setHeure_fin(final String heure_fin) {
		this.heure_finProperty().set(heure_fin);
	}
	
	public final Property<Integer> numero_posteProperty() {
		return this.numero_poste;
	}
	

	public final Integer getNumero_poste() {
		return this.numero_posteProperty().getValue();
	}
	

	public final void setNumero_poste(final Integer numero_poste) {
		this.numero_posteProperty().setValue(numero_poste);
	}
	
	public ObservableList<Benevole> getBenevoles() {
		return benevoles;
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
	
	public final Property<Categorie> categorieProperty() {
		return this.categorie;
	}
	

	public final Categorie getCategorie() {
		return this.categorieProperty().getValue();
	}
	

	public final void setCategorie(final Categorie categorie) {
		this.categorieProperty().setValue(categorie);
	}
	
	
	public final Property<LocalDate> jourProperty() {
		return this.jour;
	}
	

	public final LocalDate getJour() {
		return this.jourProperty().getValue();
	}
	

	public final void setJour(final LocalDate jour) {
		this.jourProperty().setValue(jour);
	}
	
	/*public final DateFormat heure_debutProperty() {
		return this.heure_debut;
	}
	
	public final DateFormat getHeure_debut() {
		return this.heure_debutProperty().getValue();
	}

	public final void setHeure_debut(final DateFormat heure_debut) {
		this.heure_debutProperty().setValue(heure_debut);
	}
	

	public final DateFormat heure_finProperty() {
		return this.heure_fin;
	}
	
	public final DateFormat getHeure_fin() {
		return this.heure_finProperty().getValue();
	}

	public final void setHeure_fin(final DateFormat heure_fin) {
		this.heure_finProperty().setValue(heure_fin);
	*/
	
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
		Poste other = (Poste) obj;
		return Objects.equals(id.getValue(), other.id.getValue());
	}

	
	// toString()
	
	@Override
	public String toString() {
		return getLibelle();
	}

}
