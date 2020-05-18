SET search_path TO projet;


-- Supprimer toutes les données
DELETE FROM service;
DELETE FROM telephone;
DELETE FROM personne;
DELETE FROM categorie;
DELETE FROM role;
DELETE FROM benevole;
DELETE FROM compte;


DELETE FROM poste;
DELETE FROM equipe;
DELETE FROM epreuve;
DELETE FROM participant;
DELETE FROM documents_licencies;
DELETE FROM avoir;
DELETE FROM encadrer;


-- Compte

INSERT INTO compte (idcompte, pseudo, motdepasse, email ) VALUES
  (1, 'president', 'president', 'president@boldair.fr' ),
  (2, 'tresorier', 'tresorier', 'tresorier@boldair.fr' ),
  (3, 'benevolePermanent', 'benevolePermanent', 'benevole@boldair.fr' );

ALTER TABLE compte ALTER COLUMN idcompte RESTART WITH 4;


-- Role

INSERT INTO role (idcompte, role) VALUES
  ( 1, 'ADMINISTRATEUR' ),
  ( 1, 'UTILISATEUR' ),
  ( 2, 'UTILISATEUR' ),
  ( 3, 'UTILISATEUR' );


-- Categorie

INSERT INTO categorie (idcategorie, libelle ) VALUES
  (1, 'Employés' ),
  (2, 'Partenaires' ),
  (3, 'Clients' ),
  (4, 'Fournisseurs' ),
  (5, 'Dirigeants' );

ALTER TABLE categorie ALTER COLUMN idcategorie RESTART WITH 6;


-- Personne

INSERT INTO personne (idpersonne, idcategorie, nom, prenom) VALUES
  ( 1, 1, 'GRASSET', 'Jérôme' ),
  ( 2, 1, 'BOUBY', 'Claude' ),
  ( 3, 1, 'AMBLARD', 'Emmanuel' );

ALTER TABLE personne ALTER COLUMN idpersonne RESTART WITH 4;


-- Telephone

INSERT INTO telephone (idtelephone, idpersonne, libelle, numero ) VALUES
  ( 11, 1, 'Portable', '06 11 11 11 11' ),
  ( 12, 1, 'Fax', '05 55 99 11 11' ),
  ( 13, 1, 'Bureau', '05 55 11 11 11' ),
  ( 21, 2, 'Portable', '06 22 22 22 22' ),
  ( 22, 2, 'Fax', '05 55 99 22 22' ),
  ( 23, 2, 'Bureau', '05 55 22 22 22' ),
  ( 31, 3, 'Portable', '06 33 33 33 33' ),
  ( 32, 3, 'Fax', '05 55 99 33 33' ),
  ( 33, 3, 'Bureau', '05 55 33 33 33' );

ALTER TABLE telephone ALTER COLUMN idtelephone RESTART WITH 100;


-- Service

INSERT INTO service ( idservice, nom, anneecreation, flagsiege ) VALUES
  ( 1, 'Direction', 2007, TRUE ),
  ( 2, 'Comptabilité', NULL, TRUE ),
  ( 3, 'Agence Limoges', 2008, FALSE ),
  ( 4, 'Agence Brive', 2014, FALSE );

ALTER TABLE service ALTER COLUMN idservice RESTART WITH 4;
 
-- Bénévole 

INSERT INTO benevole ( idbenevole, idcompte, nom, prenom, date_naissance, permis_conduire, mineurs, permanent ) VALUES
  ( 1,1, 'nom1', 'bob', '12/08/1968','lien_fichier1' ,TRUE, TRUE),
  ( 2,2, 'nom2', 'paul', '12/08/1978','lien_fichier1', TRUE, FALSE),
  ( 3,3, 'nom3', 'jack', '12/08/1986','lien_fichier1', FALSE, TRUE );
  
ALTER TABLE benevole ALTER COLUMN idbenevole RESTART WITH 8;

-- Poste 

INSERT INTO poste ( idposte, lieu, signaleur, heure_debut, numero_poste, heure_fin ) VALUES
  ( 20, 'lieu1', TRUE,  '17:00:00' , 1, '18:00:00'),
  ( 30, 'lieu2', FALSE, '18:00:00' , 22, '19:00:00'),
  ( 40, 'lieu3', TRUE,  '09:00:00' , 17, '11:00:00'),
  ( 50, 'lieu4', TRUE , '07:00:00' , 45, '10:00:00');
 
ALTER TABLE poste ALTER COLUMN idposte RESTART WITH 5;
  
-- Equipe

INSERT INTO equipe ( idequipe, nom_equipe, valide, paye, nb_plateau) VALUES
  ( 1, 'equipe1',TRUE, FALSE, 1),
  ( 2, 'equipe2', TRUE , TRUE, 2),
  ( 3, 'equipe 3', TRUE, TRUE, 1);

ALTER TABLE equipe ALTER COLUMN idequipe RESTART WITH 4;
  
-- Epreuve

INSERT INTO epreuve ( idepreuve, nom, date_epreuve, lieu, tarif) VALUES
  ( 1, 'epreuve a', '12/08/2020',  'lieu 1','10 €'),
  ( 2, 'epreuve b', '17/08/2020', 'lieu 2', '10 €'),
  ( 3, 'epreuve c', '11/08/2020',  'lieu 1', '20 €');
  
ALTER TABLE epreuve ALTER COLUMN idepreuve RESTART WITH 4;

-- Participant
 
INSERT INTO participant (idparticipant, idequipe, idepreuve, idcompte, nom, prenom, sexe, numero_tel,  date_naissance, adresse, role, certificat_medical, mail, niveau, materiel_utilise) VALUES
  ( 1,1,1,1, 'nom1', 'leo', 'M', '06 52 36 98 25','12/08/1995', 'adresse 1', 'chef equipe','fichier 1', 'mail1@gmail.com',  'junior' , 'velo, kanoé'),
  ( 2,2,2,2, 'nom2', 'bob', 'M', '06 95 58 21 02','12/08/1965', 'adresse 2', 'equipier',   'fichier 2', 'mail2@hotmail.fr', 'senior', 'velo'),
  ( 3,3,3,3, 'nom3', 'toto','M', '06 87 10 96 32','12/08/1998','adresse 3', 'equipier',   'fichier 3', 'mail3@orange.fr',  'junior', 'kanoe'),
  (	4,3,3,2, 'nom4', 'clement','M', '06 87 10 78 32','12/08/1999','adresse 4', 'chef equipe',   'fichier 4', 'mail4@orange.fr',  'senior', 'kanoe');
  
ALTER TABLE participant ALTER COLUMN idparticipant RESTART WITH 16;
 
-- Documents Licencies

INSERT INTO documents_licencies ( iddocument, idparticipant, nom_doc, date_doc, doc) VALUES
  ( 1, 1, 'doc a', '12/08/2020',  'lien 1'),
  ( 2, 2, 'doc b', '17/08/2020', 'lien 2'),
  ( 3, 3, 'doc c', '11/08/2020',  'lien 1');

ALTER TABLE documents_licencies ALTER COLUMN iddocument RESTART WITH 5;

-- Avoir

INSERT INTO avoir ( idposte, idbenevole) VALUES
  ( 30, 1),
  ( 20, 2);

--ALTER TABLE avoir ALTER COLUMN idposte, idbenevole RESTART WITH 2;

-- Encadrer

 INSERT INTO encadrer ( idepreuve, idbenevole) VALUES
  ( 1, 1),
  ( 2, 2);

--ALTER TABLE encadrer ALTER COLUMN idepreuve, idbenevole RESTART WITH 2;
