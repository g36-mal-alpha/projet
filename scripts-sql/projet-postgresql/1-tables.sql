SET search_path TO projet;


-- Schéma

DROP SCHEMA IF EXISTS projet CASCADE;
CREATE SCHEMA projet AUTHORIZATION projet;
GRANT ALL PRIVILEGES ON SCHEMA projet TO projet;


-- Tables

DROP TABLE IF EXISTS memo CASCADE;

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

CREATE TABLE categorie (
	idcategorie		INT				GENERATED BY DEFAULT AS IDENTITY,
	libelle			VARCHAR(25)		NOT NULL,
	PRIMARY KEY (idcategorie)
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
	Libelle			VARCHAR(25)		NOT NULL,
	Numero			VARCHAR(25)		NOT NULL,
	PRIMARY KEY (idtelephone),
	FOREIGN KEY (idpersonne) REFERENCES personne (idpersonne)
);

CREATE TABLE concerner (
	idmemo		INT				NOT NULL,
	idpersonne	INT				NOT NULL,
	PRIMARY KEY (idmemo, idpersonne),
	FOREIGN KEY (idmemo) REFERENCES memo (idmemo),
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


-- Vues

CREATE VIEW v_compte_avec_roles AS
	SELECT c.*, ARRAY_AGG( r.role ) AS roles
	FROM compte c
	LEFT JOIN ROLE r ON c.idcompte = r.idcompte
	GROUP BY c.idcompte;
