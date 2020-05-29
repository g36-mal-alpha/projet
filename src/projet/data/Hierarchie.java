package projet.data;

import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Hierarchie  {
	

	// Données observables
	
	private final Property<Integer>	id		= new SimpleObjectProperty<>();
	private final StringProperty	libelle	= new SimpleStringProperty();
	
	
	// Constructeurs
	
	public Hierarchie() {
	}

	public Hierarchie( final int id, final String libelle ) {
		setId(id);
		setLibelle(libelle);
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

	public final StringProperty libelleProperty() {
		return this.libelle;
	}

	public final String getLibelle() {
		return this.libelleProperty().getValue();
	}

	public final void setLibelle(final String libelle) {
		this.libelleProperty().setValue(libelle);
	}

	
	// toString()
	
	@Override
	public String toString() {
		return getLibelle();
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
		Hierarchie other = (Hierarchie) obj;
		return Objects.equals(id.getValue(), other.id.getValue() );
	}
	
}

