package projet.data;

import java.time.LocalDate;
import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Documents  {
	

	// Données observables nom date lieu tarif
	
	private final Property<Integer>   	id		        = new SimpleObjectProperty<>();
	private final StringProperty	    nom_doc	        = new SimpleStringProperty();
	private final Property<LocalDate>   date_doc        = new SimpleObjectProperty<LocalDate>();
	private final StringProperty	    lien_doc	        = new SimpleStringProperty();

	
	// Constructeurs
	
	public Documents() {
	}

	public Documents( final int id, final String nom_doc, LocalDate date_doc, String lien_doc) {
		setId(id);
		setNom_doc(nom_doc);
		setDate_doc(date_doc);
		setLien_doc(lien_doc);
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

	public final StringProperty nom_docProperty() {
		return this.nom_doc;
	}
	

	public final String getNom_doc() {
		return this.nom_docProperty().get();
	}
	

	public final void setNom_doc(final String nom_doc) {
		this.nom_docProperty().set(nom_doc);
	}
	

	public final Property<LocalDate> date_docProperty() {
		return this.date_doc;
	}
	

	public final LocalDate getDate_doc() {
		return this.date_docProperty().getValue();
	}
	

	public final void setDate_doc(final LocalDate date_doc) {
		this.date_docProperty().setValue(date_doc);
	}
	

	public final StringProperty lien_docProperty() {
		return this.lien_doc;
	}
	

	public final String getLien_doc() {
		return this.lien_docProperty().get();
	}
	

	public final void setLien_doc(final String lien_doc) {
		this.lien_docProperty().set(lien_doc);
	}
	
	
	// toString()
	
	@Override
	public String toString() {
		return getNom_doc();
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
		Documents other = (Documents) obj;
		return Objects.equals(id.getValue(), other.id.getValue() );
	}

}

