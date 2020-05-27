package projet.data;

import java.time.LocalDate;
import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Epreuve  {
	

	// Données observables nom date lieu tarif
	
	private final Property<Integer>   	id		        = new SimpleObjectProperty<>();
	private final StringProperty	    nom_epreuve	    = new SimpleStringProperty();
	private final Property<LocalDate>   date_epreuve    = new SimpleObjectProperty<LocalDate>();
	private final StringProperty	    lieu	        = new SimpleStringProperty();
	private final StringProperty	    tarif    	    = new SimpleStringProperty();
	
	// Constructeurs
	
	public Epreuve() {
	}

	public Epreuve( final int id, final String nom_epreuve, LocalDate date_epreuve, String lieu , String tarif ) {
		setId(id);
		setNom_epreuve(nom_epreuve);
		setDate_epreuve(date_epreuve);
		setLieu(lieu);
		setTarif(tarif);
	}
	
	
	// Getters et Setters

	public final Property<Integer> idProperty() {
		return this.id;
	}

	public final Integer getId() {
		return this.idProperty().getValue();
	}

	public final void setId(final Integer id) {
		this.idProperty().setValue(id);
	}
	
	public final StringProperty nom_epreuveProperty() {
		return this.nom_epreuve;
	}
	

	public final String getNom_epreuve() {
		return this.nom_epreuveProperty().get();
	}
	

	public final void setNom_epreuve(final String nom_epreuve) {
		this.nom_epreuveProperty().set(nom_epreuve);
	}
	

	public final Property<LocalDate> date_epreuveProperty() {
		return this.date_epreuve;
	}
	

	public final LocalDate getDate_epreuve() {
		return this.date_epreuveProperty().getValue();
	}
	

	public final void setDate_epreuve(final LocalDate date_epreuve) {
		this.date_epreuveProperty().setValue(date_epreuve);
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
	

	public final StringProperty tarifProperty() {
		return this.tarif;
	}
	

	public final String getTarif() {
		return this.tarifProperty().get();
	}
	

	public final void setTarif(final String tarif) {
		this.tarifProperty().set(tarif);
	}
	
	
	
	// toString()
	
	@Override
	public String toString() {
		return getNom_epreuve();
	}
	
	
	// hashCode() & equals()

	@Override
	public int hashCode() {
		return Objects.hash(id.getValue() );
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Epreuve other = (Epreuve) obj;
		return Objects.equals(id.getValue(), other.id.getValue() );
	}

}

