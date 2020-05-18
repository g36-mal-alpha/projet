package projet.data;

import java.time.LocalDate;
import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;



public class Participant {
	
	// erreur ligne 123/25
	// Champs
	//nom, prenom, sexe, numero_tel,  date_naissance, adresse, role, certificat_medical, mail, niveau, materiel_utilise
	
	private final Property<Integer>		id				   = new SimpleObjectProperty<>();
	private final StringProperty   	 	nom     	  	   = new SimpleStringProperty();
	private final StringProperty   	 	prenom     	       = new SimpleStringProperty();
	private final StringProperty   	 	sexe     	  	   = new SimpleStringProperty();
	private final StringProperty   	 	numero_tel         = new SimpleStringProperty();
	private final Property<LocalDate>   date_naissance     = new SimpleObjectProperty<LocalDate>();
	private final StringProperty   	 	adresse     	   = new SimpleStringProperty();
	private final StringProperty		role               = new SimpleStringProperty();
	private final StringProperty   	 	certificat_medical = new SimpleStringProperty();
	private final StringProperty   	 	mail               = new SimpleStringProperty();
	private final StringProperty   	 	niveau             = new SimpleStringProperty();
	private final StringProperty   	 	materiel_utilise   = new SimpleStringProperty();

	
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
	
	public final StringProperty nomProperty() {
		return this.nom;
	}
	
	public final String getNom() {
		return this.nomProperty().get();
	}
	
	public final void setNom(final String nom) {
		this.nomProperty().set(nom);
	}
	
	
	public final String getPrenom() {
		return this.prenomProperty().get();
	}
	

	public final void setPrenom(final String prenom) {
		this.prenomProperty().set(prenom);
	}
	

	public final StringProperty sexeProperty() {
		return this.sexe;
	}
	

	public final String getSexe() {
		return this.sexeProperty().get();
	}
	

	public final void setSexe(final String sexe) {
		this.sexeProperty().set(sexe);
	}
	

	public final StringProperty numero_telProperty() {
		return this.numero_tel;
	}
	

	public final String getNumero_tel() {
		return this.numero_telProperty().get();
	}
	

	public final void setNumero_tel(final String numero_tel) {
		this.numero_telProperty().set(numero_tel);
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

	public final StringProperty adresseProperty() {
		return this.adresse;
	}
	

	public final String getAdresse() {
		return this.adresseProperty().get();
	}
	

	public final void setAdresse(final String adresse) {
		this.adresseProperty().set(adresse);
	}
	
	public final String getRole() {
		return this.roleProperty().get();
	}
	

	public final void setRole(final String role) {
		this.roleProperty().set(role);
	}
	
	
	public final StringProperty certificat_medicalProperty() {
		return this.certificat_medical;
	}
	

	public final String getCertificat_medical() {
		return this.certificat_medicalProperty().get();
	}
	

	public final void setCertificat_medical(final String certificat_medical) {
		this.certificat_medicalProperty().set(certificat_medical);
	}
	

	public final StringProperty mailProperty() {
		return this.mail;
	}
	

	public final String getMail() {
		return this.mailProperty().get();
	}
	

	public final void setMail(final String mail) {
		this.mailProperty().set(mail);
	}
	

	public final StringProperty niveauProperty() {
		return this.niveau;
	}
	

	public final String getNiveau() {
		return this.niveauProperty().get();
	}
	

	public final void setNiveau(final String niveau) {
		this.niveauProperty().set(niveau);
	}
	

	public final StringProperty materiel_utiliseProperty() {
		return this.materiel_utilise;
	}
	

	public final String getMateriel_utilise() {
		return this.materiel_utiliseProperty().get();
	}
	

	public final void setMateriel_utilise(final String materiel_utilise) {
		this.materiel_utiliseProperty().set(materiel_utilise);
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
		Participant other = (Participant) obj;
		return Objects.equals(id.getValue(), other.id.getValue());
	}

	
	// toString()
	
	@Override
	public String toString() {
		return getNom();
	}

	public final StringProperty prenomProperty() {
		return this.prenom;
	}

	public final StringProperty roleProperty() {
		return this.role;
	}

}
