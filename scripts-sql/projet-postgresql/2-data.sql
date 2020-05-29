SET search_path TO projet;


-- Supprimer toutes les données
DELETE FROM service;
DELETE FROM telephone;
DELETE FROM personne;
DELETE FROM categorie;
DELETE FROM sexe;
DELETE FROM hierarchie;
DELETE FROM avoir;
DELETE FROM encadrer;
DELETE FROM role;
DELETE FROM poste;
DELETE FROM documents_licencies;
DELETE FROM participant;
DELETE FROM equipe;
DELETE FROM epreuve;
DELETE FROM benevole;
DELETE FROM compte;




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
  (1, 'M' ),
  (2, 'E' ),
  (3, 'M ou E'),
  (4, 'M et E');
 
ALTER TABLE compte ALTER COLUMN idcompte RESTART WITH 2;

-- Sexe

INSERT INTO sexe (idsexe, libelle ) VALUES
  (1, 'homme' ),
  (2, 'femme' );
  
ALTER TABLE sexe ALTER COLUMN idsexe RESTART WITH 2;
  
 -- hierarchie

INSERT INTO hierarchie (idhierarchie, libelle ) VALUES
  (1, 'chef équipe' ),
  (2, 'équipier' );
  
 ALTER TABLE hierarchie ALTER COLUMN idhierarchie RESTART WITH 2;
  

-- Personne

INSERT INTO personne (idpersonne, idcategorie, nom, prenom) VALUES
  ( 1, 1, 'GRASSET', 'Jérôme' ),
  ( 2, 1, 'BOUBY', 'Claude' ),
  ( 3, 1, 'AMBLARD', 'Emmanuel' );

ALTER TABLE personne ALTER COLUMN idpersonne RESTART WITH 4;


-- Telephone

INSERT INTO telephone (idtelephone, idpersonne, libelle, numero ) VALUES
  ( 11, 1, 'Portable', '0611111111' ),
  ( 12, 1, 'Fax', '0555991111' ),
  ( 13, 1, 'Bureau', '0555111111' ),
  ( 21, 2, 'Portable', '0622222222' ),
  ( 22, 2, 'Fax', '0555992222' ),
  ( 23, 2, 'Bureau', '0555222222' ),
  ( 31, 3, 'Portable', '0633333333' ),
  ( 32, 3, 'Fax', '0555993333' ),
  ( 33, 3, 'Bureau', '0555333333' );

ALTER TABLE telephone ALTER COLUMN idtelephone RESTART WITH 100;


-- Service

INSERT INTO service ( idservice, nom, anneecreation, flagsiege ) VALUES
  ( 1, 'Direction', 2007, TRUE ),
  ( 2, 'Comptabilité', NULL, TRUE ),
  ( 3, 'Agence Limoges', 2008, FALSE ),
  ( 4, 'Agence Brive', 2014, FALSE );

ALTER TABLE service ALTER COLUMN idservice RESTART WITH 4;
 
-- Bénévole 

INSERT INTO benevole ( idbenevole, nom, prenom, date_naissance, permis_conduire, mineurs, idcategorie ) VALUES
  ( 1, 'Musk', 'Elon', '12/08/1968','lien_fichier1' ,TRUE, 1 ),
  ( 2, 'Tesla', 'Nicolas', '12/08/1978','lien_fichier1', TRUE , 1),
  ( 3, 'Boussard', 'Lucas', '12/08/1986','lien_fichier1', FALSE , 2);
  
ALTER TABLE benevole ALTER COLUMN idbenevole RESTART WITH 8;

-- Poste 

INSERT INTO poste ( idposte, lieu, libelle, nombre ,jour, heure_debut, numero_poste, heure_fin, idcategorie) VALUES
  ( 1, 'lieu1', 'signaleur',2,'12/08/2020', '17:00:00' , 1, '18:00:00', 1),
  ( 2, 'lieu2', 'buvette',5,'12/08/2020', '17:00:00' , 2, '18:00:00', 1),
  ( 3, 'lieu3', 'moto',6,'12/08/2020' ,  '09:00:00' , 17, '11:00:00', 1),
  ( 4, 'lieu4', 'photographe',3, '12/08/2020', '07:00:00' , 45, '10:00:00', 1);
 
ALTER TABLE poste ALTER COLUMN idposte RESTART WITH 6;
  
-- Equipe

INSERT INTO equipe ( idequipe, nom_equipe, valide, paye, nb_plateau) VALUES
  ( 1, 'equipe 1',TRUE, FALSE, 1),
  ( 2, 'equipe 2', TRUE , TRUE, 2),
  ( 3, 'equipe 3', TRUE, TRUE, 1);

ALTER TABLE equipe ALTER COLUMN idequipe RESTART WITH 4;
  
-- Epreuve

INSERT INTO epreuve ( idepreuve, nom_epreuve, date_epreuve, lieu, tarif) VALUES
  ( 1, 'epreuve a', '12/08/2020',  'lieu 1','10'),
  ( 2, 'epreuve b', '17/08/2020', 'lieu 2', '10'),
  ( 3, 'epreuve c', '11/08/2020',  'lieu 1', '20');
  
ALTER TABLE epreuve ALTER COLUMN idepreuve RESTART WITH 4;

-- Participant
 
INSERT INTO participant (idparticipant, idequipe, idepreuve, nom, prenom, idsexe, numero_tel,  date_naissance, adresse, role, certificat_medical, mail, niveau, materiel_utilise) VALUES
  ( 1,1,1, 'nom1', 'leo', 1, '06 52 36 98 25','12/08/1995', 'adresse 1', 'chef equipe','fichier 1', 'mail1@gmail.com',  'junior' , 'velo, kanoé'),
  ( 2,2,2, 'nom2', 'bob', 2, '06 95 58 21 02','12/08/1965', 'adresse 2', 'equipier',   'fichier 2', 'mail2@hotmail.fr', 'senior', 'velo'),
  ( 3,3,3, 'nom3', 'toto',1, '06 87 10 96 32','12/08/1998','adresse 3', 'equipier',   'fichier 3', 'mail3@orange.fr',  'junior', 'kanoe'),
  (	4,3,2, 'nom4', 'clement',2, '06 87 10 78 32','12/08/1999','adresse 4', 'chef equipe',   'fichier 4', 'mail4@orange.fr',  'senior', 'kanoe');
  
ALTER TABLE participant ALTER COLUMN idparticipant RESTART WITH 16;
 
-- Documents Licencies

INSERT INTO documents_licencies ( iddocument, idparticipant, nom_doc, date_doc, lien_doc) VALUES
  ( 1, 1, 'doc a', '12/08/2020',  'lien 1'),
  ( 2, 2, 'doc b', '17/08/2020', 'lien 2'),
  ( 3, 3, 'doc c', '11/08/2020',  'lien 1');

ALTER TABLE documents_licencies ALTER COLUMN iddocument RESTART WITH 5;

-- Avoir

INSERT INTO avoir ( idposte, idbenevole) VALUES
  (1, 3),
  (1, 2),
  (2, 1),
  (3, 1),
  (4, 2),
  (2, 3);


-- Encadrer

 INSERT INTO encadrer ( idepreuve, idbenevole) VALUES
  (1, 1),
  (2, 2);


