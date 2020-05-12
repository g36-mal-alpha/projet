SET search_path TO projet;


-- Supprimer toutes les données
DELETE FROM service;
DELETE FROM telephone;
DELETE FROM personne;
DELETE FROM categorie;
DELETE FROM role;
DELETE FROM compte;


-- Compte

INSERT INTO compte (idcompte, pseudo, motdepasse, email ) VALUES
  (1, 'geek', 'geek', 'geek@3il.fr' ),
  (2, 'chef', 'chef', 'chef@3il.fr' ),
  (3, 'job', 'job', 'job@3il.fr' );

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

  
 /*
-- Bénévole 

  INSERT INTO benevole ( idbenevole, nom, prenom, age, permis_conduire, mineurs, permanent ) VALUES
  ( 1, 'nom1', 'bob', 34,'lien_fichier1' ,TRUE, TRUE),
  ( 2, 'nom2', 'paul', 25,'lien_fichier1', TRUE, FALSE),
  ( 3, 'nom3', 'jack', 36,'lien_fichier1', FALSE, TRUE ),
  ( 4, 'nom4', 'bouss', 45,'lien_fichier1', FALSE, TRUE );

-- Poste 

  INSERT INTO poste ( idposte, lieu, signaleur, horaires, numero_poste ) VALUES
  ( 1, 'lieu1', TRUE,  '17:00:00' , 1),
  ( 2, 'lieu2', FALSE, '18:00:00' , 22),
  ( 3, 'lieu3', TRUE,  '09:00:00' , 17),
  ( 4, 'lieu4', TRUE , '07:00:00' , 45);
  
-- Equipe

  INSERT INTO equipe ( idequipe, nom_equipe) VALUES
  ( 1, 'equipe1'),
  ( 2, 'equipe2');

-- Epreuve

  INSERT INTO epreuve ( idepreuve, nom, date_epreuve, lieu) VALUES
  ( 1, 'epreuve a', '12/08/2020',  'lieu 1'),
  ( 2, 'epreuve b', '17/08/2020', 'lieu 2'),
  ( 3, 'epreuve c', '11/08/2020',  'lieu 1');

-- Participant
 
  INSERT INTO paticipant ( idparticpant, nom, prenom,, age, sexe, numero_tel,  date_naissance, adresse, role, certificat_medical, mail, niveau, materiel_utilise, nb_plateaux) VALUES
  ( 1,'nom1', 'leo', 25, 'M', '06 52 36 98 25','12/08/1995', 'adresse 1', 'chef equipe','fichier 1', 'mail1@gmail.com',  'junior' , 'velo, kanoé',  1),
  ( 2, 'nom2','bob', 35, 'M', '06 95 58 21 02','12/08/1965', 'adresse 2', 'equipier',   'fichier 2', 'mail2@hotmail.fr', 'senior', 'velo', 2),
  ( 3,'nom3', 'toto', 22,'M', '06 87 10 96 32','12/08/1998','adresse 3', 'equipier',   'fichier 3', 'mail3@orange.fr',  'junior', 'kanoe' , 2);
  
-- Documents Licncies

  INSERT INTO documents_licencies ( iddocument, nom_doc, date_doc, doc) VALUES
  ( 1, 'doc a', '12/08/2020',  'lien 1'),
  ( 2, 'doc b', '17/08/2020', 'lien 2'),
  ( 3, 'doc c', '11/08/2020',  'lien 1');

*/

ALTER TABLE service ALTER COLUMN idservice RESTART WITH 5;
