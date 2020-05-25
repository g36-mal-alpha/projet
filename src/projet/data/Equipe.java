package projet.data;

import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Equipe  {
	

	// Données observables
	
	private final Property<Integer>   	id		        = new SimpleObjectProperty<>();
	private final StringProperty	    nom_equipe	    = new SimpleStringProperty();
	private final Property<Boolean>		valide		    = new SimpleObjectProperty<>( false );
	//private final BooleanProperty		valide		    = new SimpleBooleanProperty();
	private final Property<Boolean>		paye		    = new SimpleObjectProperty<>( false );
	//private final BooleanProperty		paye		    = new SimpleBooleanProperty();
	private final Property<Integer>	    nb_plateau 	    = new SimpleObjectProperty<>();
	
	
	// Constructeurs
	
	public Equipe() {
	}

	public Equipe( final int id, final String nom_equipe, Boolean valide, Boolean paye, Integer nb_plateau ) {
		setId(id);
		setNom_equipe(nom_equipe);
		setValide(valide);
		setPaye(paye);
		setNb_plateau(nb_plateau);
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
	
	public final StringProperty nom_equipeProperty() {
		return this.nom_equipe;
	}
	

	public final String getNom_equipe() {
		return this.nom_equipeProperty().get();
	}
	

	public final void setNom_equipe(final String nom_equipe) {
		this.nom_equipeProperty().set(nom_equipe);
	}
	

	public final Property<Boolean> valideProperty() {
		return this.valide;
	}
	

	public final Boolean getValide() {
		return this.valideProperty().getValue();
	}
	

	public final void setValide(final Boolean valide) {
		this.valideProperty().setValue(valide);
	}
	
//	public final Property<Boolean> valideProperty() {
//	return this.valide;
//}
//
//public final Boolean getValide() {
//	return this.valideProperty().getValue();
//}
//
//public final void setValide(final Boolean valide) {
//	this.valideProperty().setValue(valide);
//}
	

	public final Property<Boolean> payeProperty() {
		return this.paye;
	}
	

	public final Boolean getPaye() {
		return this.payeProperty().getValue();
	}
	

	public final void setPaye(final Boolean paye) {
		this.payeProperty().setValue(paye);
	}
	
//	public final Property<Boolean> payeProperty() {
//	return this.paye;
//}
//
//public final Boolean getValide() {
//	return this.payeProperty().getPaye();
//}
//
//public final void setValide(final Boolean paye) {
//	this.payeProperty().setValue(paye);
//}
	

	public final Property<Integer> nb_plateauProperty() {
		return this.nb_plateau;
	}
	

	public final Integer getNb_plateau() {
		return this.nb_plateauProperty().getValue();
	}
	

	public final void setNb_plateau(final Integer nb_plateau) {
		this.nb_plateauProperty().setValue(nb_plateau);
	}
	
	
	// toString()
	
	@Override
	public String toString() {
		return getNom_equipe();
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
		Equipe other = (Equipe) obj;
		return Objects.equals(id.getValue(), other.id.getValue() );
	}

}

