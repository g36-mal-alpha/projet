package projet.data;

import java.time.LocalDate;
import java.util.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;



public class Participant {
	
	// Champs
	
	private final Property<Integer>		id				   = new SimpleObjectProperty<>();
	private final StringProperty   	 	nom     	  	   = new SimpleStringProperty();
	private final StringProperty   	 	prenom     	       = new SimpleStringProperty();
	private final Property<Sexe>    	sexe			   = new SimpleObjectProperty<>();
	private final StringProperty   	 	numero_tel         = new SimpleStringProperty();
	private final Property<LocalDate>   date_naissance     = new SimpleObjectProperty<LocalDate>();
	private final StringProperty   	 	adresse     	   = new SimpleStringProperty();
	private final Property<Hierarchie>	Hierarchie         = new SimpleObjectProperty<Hierarchie>();
	private final StringProperty   	 	certificat_medical = new SimpleStringProperty();
	private final StringProperty   	 	mail               = new SimpleStringProperty();
	private final StringProperty   	 	niveau             = new SimpleStringProperty();
	private final StringProperty   	 	materiel_utilise   = new SimpleStringProperty();
	private final Property<Equipe>		Equipe        	   = new SimpleObjectProperty<Equipe>();
	
	// Constructeurs
	
	public Participant() {
	}

	public Participant( int id, String nom, String prenom, Sexe sexe, String numero_tel, LocalDate date_naissance, String adresse, Hierarchie Hierarchie, String certificat_medical, String mail, String niveau, String materiel_utilise, Equipe Equipe) {
		setId(id);
		setNom(nom);
		setPrenom(prenom);
		setSexe(sexe);
		setNumero_tel(numero_tel);
		setDate_naissance(date_naissance);
		setAdresse(adresse);
		setHierarchie(Hierarchie);
		setCertificat_medical(certificat_medical);
		setMail(mail);
		setNiveau(niveau);
		setMateriel_utilise(materiel_utilise);
		setEquipe(Equipe);
		
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
	
	public final Property<Hierarchie> HierarchieProperty()
	{
		return this.Hierarchie;
	}
	
	public final projet.data.Hierarchie getHierarchie()
	{
		return this.HierarchieProperty().getValue();
	}
	
	public final void setHierarchie(final projet.data.Hierarchie Hierarchie) {
		this.HierarchieProperty().setValue(Hierarchie);
	}
	
	public final Property<Sexe> sexeProperty()
	{
		return this.sexe;
	}
	
	public final projet.data.Sexe getSexe()
	{
		return this.sexeProperty().getValue();
	}
	
	public final void setSexe(final projet.data.Sexe sexe) {
		this.sexeProperty().setValue(sexe);
	}

	public final Property<Equipe> equipeProperty()
	{
		return this.Equipe;
	}
	
	public final projet.data.Equipe getEquipe()
	{
		return this.equipeProperty().getValue();
	}
	
	public final void setEquipe(final projet.data.Equipe Equipe) {
		this.equipeProperty().setValue(Equipe);
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
		return getNom() + " " + getPrenom();
	} 

}
