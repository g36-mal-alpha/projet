SET search_path TO projet;


-- Schéma

DROP SCHEMA IF EXISTS projet CASCADE;
CREATE SCHEMA projet AUTHORIZATION projet;
GRANT ALL PRIVILEGES ON SCHEMA projet TO projet;


-- Tables

DROP TABLE IF EXISTS memo CASCADE;
DROP TABLE IF EXISTS concerner CASCADE;


-- TABLES DE CHOIX 

CREATE TABLE categorie (
	idcategorie		INT				GENERATED BY DEFAULT AS IDENTITY,
	libelle			VARCHAR(25)		NOT NULL,
	
	PRIMARY KEY (idcategorie)
);


CREATE TABLE sexe (
	idsexe		INT				GENERATED BY DEFAULT AS IDENTITY,
	libelle			VARCHAR(25)		NOT NULL,
	
	PRIMARY KEY (idsexe)
);


CREATE TABLE hierarchie (
	idhierarchie		INT				GENERATED BY DEFAULT AS IDENTITY,
	libelle			VARCHAR(25)		NOT NULL,
	
	PRIMARY KEY (idhierarchie)
);

-- Tables

CREATE TABLE compte (
	idcompte		INT				GENERATED BY DEFAULT AS IDENTITY,
	pseudo			VARCHAR(25)		NOT NULL,
	motdepasse		VARCHAR(25) 	NOT NULL,
	email			VARCHAR(100)	NOT NULL,
	
	PRIMARY KEY (idcompte),
	UNIQUE (pseudo)
);

CREATE TABLE role (
	idcompte		INT				NOT NULL,
	role			VARCHAR(20)		NOT NULL,
	CHECK( Role IN ('ADMINISTRATEUR','UTILISATEUR') ),
	
	PRIMARY KEY (idcompte, role),
	FOREIGN KEY (idcompte) REFERENCES compte (idcompte)
);

CREATE TABLE personne (
	idpersonne		INT				GENERATED BY DEFAULT AS IDENTITY,
	idcategorie		INT				NOT NULL,
	nom				VARCHAR(25)  	NOT NULL,
	prenom			VARCHAR(25) 	NOT NULL,
	
	PRIMARY KEY (idpersonne),
 	FOREIGN KEY (idcategorie) REFERENCES categorie (idcategorie)
);

CREATE TABLE telephone (
	idtelephone		INT				GENERATED BY DEFAULT AS IDENTITY,
	idpersonne		INT			 	NOT NULL,
	libelle			VARCHAR(25)		NOT NULL,
	numero			VARCHAR(25)		NOT NULL,
	
	PRIMARY KEY (idtelephone),
	FOREIGN KEY (idpersonne) REFERENCES personne (idpersonne)
);

CREATE TABLE service (
	idservice 		INT				GENERATED BY DEFAULT AS IDENTITY,
	nom 			VARCHAR(50)		NOT NULL,
	description		VARCHAR(1000),
	anneecreation	SMALLINT,
	flagsiege		BOOLEAN			NOT NULL,
	PRIMARY KEY (idservice)
);

--TABLES DONNEES

------------------------------------------------------------
-- Table: Bénévole
------------------------------------------------------------
CREATE TABLE benevole(
	idbenevole        INT 		 GENERATED BY DEFAULT AS IDENTITY ,
	idcategorie		  INT,
	nom               VARCHAR (255) NOT NULL ,
	prenom            VARCHAR (255) NOT NULL ,
	date_naissance    DATE  NOT NULL ,
	permis_conduire   VARCHAR (255) NOT NULL ,
	mineurs           BOOLEAN  NOT NULL ,
	
	PRIMARY KEY (idbenevole),

	FOREIGN KEY (idcategorie) REFERENCES categorie (idcategorie)
	
);


------------------------------------------------------------
-- Table: Poste
------------------------------------------------------------
CREATE TABLE poste(
	idposte        INT 		 GENERATED BY DEFAULT AS IDENTITY,
	idcategorie    INT,
	lieu           VARCHAR (255) NOT NULL ,
	nombre		   INT  NOT NULL,
	libelle 	   VARCHAR (255) NOT NULL,
	jour		   DATE  NOT NULL ,
	--heure_debut    DATE  NOT NULL ,
	heure_debut    VARCHAR (255) NOT NULL,
	numero_poste   INT  NOT NULL,
	--heure_fin      DATE NOT NULL,
	heure_fin      VARCHAR (255) NOT NULL,
	
	
	PRIMARY KEY (idposte),
	
	FOREIGN KEY (idcategorie) REFERENCES categorie (idcategorie)
	
);


------------------------------------------------------------
-- Table: Equipe
------------------------------------------------------------
CREATE TABLE equipe(
	idequipe     INT 		 GENERATED BY DEFAULT AS IDENTITY,
	nom_equipe   VARCHAR (255) NOT NULL ,
	valide		 BOOLEAN  NOT NULL,
	paye  		 BOOLEAN NOT NULL,
	nb_plateau   INT NOT NULL,
	
	PRIMARY KEY (idequipe)
);


------------------------------------------------------------
-- Table: Epreuve
------------------------------------------------------------
CREATE TABLE epreuve(
	idepreuve    INT 		 GENERATED BY DEFAULT AS IDENTITY,
	nom_epreuve          VARCHAR (255) NOT NULL ,
	date_epreuve         DATE  NOT NULL ,
	lieu                 VARCHAR (255) NOT NULL  ,
	tarif		         INT NOT NULL ,
	
	PRIMARY KEY (idepreuve)
);


------------------------------------------------------------
-- Table: Participant
------------------------------------------------------------
CREATE TABLE participant(
	idparticipant       INT 		 GENERATED BY DEFAULT AS IDENTITY,
	idequipe            INT  ,
	idepreuve           INT  ,
	--idsexe			INT,
	--idhierarchie			INT,
	nom                 VARCHAR (255) NOT NULL ,
	prenom              VARCHAR (255) NOT NULL ,
	sexe                VARCHAR (255) NOT NULL ,
	numero_tel          VARCHAR (255) NOT NULL ,
	date_naissance      DATE  NOT NULL ,
	adresse             VARCHAR (255) NOT NULL ,
	role                VARCHAR (255) NOT NULL ,
	certificat_medical  VARCHAR (255) NOT NULL ,
	mail                VARCHAR (255) NOT NULL ,
	niveau              VARCHAR (255) NOT NULL ,
	materiel_utilise    VARCHAR (255) NOT NULL ,
	
	PRIMARY KEY (idparticipant),
	
	FOREIGN KEY (idequipe) REFERENCES equipe (idequipe),
	FOREIGN KEY (idepreuve) REFERENCES epreuve (idepreuve)

	--FOREIGN KEY (idhierarchie) REFERENCES hierarchie (idhierarchie),
	--FOREIGN KEY (idsexe) REFERENCES sexe (idsexe),
	
	--UNIQUE (mail)

);


------------------------------------------------------------
-- Table: Documents Licencies
------------------------------------------------------------
CREATE TABLE documents_licencies(
	iddocument       INT 		 GENERATED BY DEFAULT AS IDENTITY,
	idparticipant    INT  ,
	nom_doc          VARCHAR (255) NOT NULL ,
	date_doc         DATE  NOT NULL ,
	lien_doc         VARCHAR (255) NOT NULL ,
	
	PRIMARY KEY (iddocument),
	
	FOREIGN KEY (idparticipant) REFERENCES participant (idparticipant),

	UNIQUE (idparticipant)
);

--TABLES DE LIAISON

------------------------------------------------------------
-- Table: Avoir
------------------------------------------------------------
CREATE TABLE avoir(
	idposte      INT  NOT NULL ,
	idbenevole   INT  NOT NULL  ,
	
	PRIMARY KEY (idposte,idbenevole),
	
	FOREIGN KEY (idposte) REFERENCES poste (idposte),
	FOREIGN KEY (idbenevole) REFERENCES benevole (idbenevole)

);


------------------------------------------------------------
-- Table: Encadrer
------------------------------------------------------------
CREATE TABLE encadrer(
	idepreuve    INT  NOT NULL,
	idbenevole   INT  NOT NULL,
	
	PRIMARY KEY (idepreuve,idbenevole),
	
	FOREIGN KEY (idepreuve) REFERENCES epreuve (idepreuve),
	FOREIGN KEY (idbenevole) REFERENCES benevole (idbenevole)
);



-- Vues

CREATE VIEW v_compte_avec_roles AS
	SELECT c.*, ARRAY_AGG( r.role ) AS roles
	FROM compte c
	LEFT JOIN ROLE r ON c.idcompte = r.idcompte
	GROUP BY c.idcompte;
