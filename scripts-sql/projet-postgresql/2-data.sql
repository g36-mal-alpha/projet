SET search_path TO projet;


-- Supprimer toutes les données

DELETE FROM compte;
DELETE FROM role;

DELETE FROM categorie;
DELETE FROM sexe;
DELETE FROM hierarchie;

DELETE FROM personne;
DELETE FROM telephone;
DELETE FROM service;

DELETE FROM benevole;
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
  (1, 'M' ),
  (2, 'E' ),
  (3, 'M ou E'),
  (4, 'M et E');
 
ALTER TABLE categorie ALTER COLUMN idcategorie RESTART WITH 5;

-- Sexe

INSERT INTO sexe (idsexe, libelle ) VALUES
  (1, 'homme' ),
  (2, 'femme' );
  
ALTER TABLE sexe ALTER COLUMN idsexe RESTART WITH 3;
  
 -- hierarchie

INSERT INTO hierarchie (idhierarchie, libelle ) VALUES
  (1, 'chef équipe' ),
  (2, 'équipier' );
  
 ALTER TABLE hierarchie ALTER COLUMN idhierarchie RESTART WITH 3;
  

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

ALTER TABLE service ALTER COLUMN idservice RESTART WITH 5;
 
-- Bénévole 

INSERT INTO benevole ( idbenevole, nom, prenom, date_naissance, permis_conduire, mineurs, idcategorie ) VALUES
  ( 1, 'Musk', 'Elon', '12/08/1968','lien_fichier1' ,TRUE, 1 ),
  ( 2, 'Tesla', 'Nicolas', '12/08/1978','lien_fichier1', TRUE , 1),
  ( 3, 'Boussard', 'Lucas', '12/08/1986','lien_fichier1', FALSE , 2);
  
ALTER TABLE benevole ALTER COLUMN idbenevole RESTART WITH 50;

-- Poste 

INSERT INTO poste ( idposte, lieu, libelle, nombre ,jour, heure_debut, numero_poste, heure_fin, idcategorie) VALUES
  ( 1, 'lieu1', 'Signaleur', 37,'12/08/2020', '08:30:00' , 1, '13:30:00', 4),
  ( 2, 'lieu2', 'Buvette',5,'12/08/2020', '07:00:00' , 2, '15:00:00', 3),
  ( 3, 'lieu3', 'Moto', 2, '12/08/2020', '09:00:00' , 3, '13:30:00', 3),
  ( 4, 'lieu4', 'Photographe', 2, '12/08/2020', '07:00:00' , 4, '14:00:00', 3),
  ( 5, 'lieu5', 'Parking Voiture', 1, '12/08/2020', '07:00:00', 9, '09:00:00', 1),
  ( 6, 'lieu6', 'Parking Velo', 1, '12/08/2020', '07:00:00', 10, '09:00:00', 1),
  ( 7, 'lieu7', 'Remise Dossards', 4, '12/08/2020', '07:00:00', 11, '09:00:00', 1),
  ( 8, 'lieu8', 'Ravitaillement', 6, '12/08/2020', '09:00:00', 12, '13:00:00', 4),
  ( 9, 'lieu9', 'Securite sur eau', 6, '12/08/2020', '09:00:00', 13, '10:30:00', 1),
  ( 10, 'lieu10', 'Chronometrage', 6, '12/08/2020', '09:30:00', 14, '13:30:00', 4),
  ( 11, 'lieu11', 'Repas', 3, '12/08/2020', '12:00:00', 15, '14:00:00', 2),
  ( 12, 'lieu12', 'Recuperation Dossards', 1, '12/08/2020', '12:00:00', 16, '13:30:00', 1);


ALTER TABLE poste ALTER COLUMN idposte RESTART WITH 20;
  
-- Equipe

INSERT INTO equipe ( idequipe, nom_equipe, valide, paye, nb_plateau) VALUES
  ( 1, 'equipe 1',TRUE, FALSE, 1),
  ( 2, 'equipe 2', TRUE , TRUE, 2),
  ( 3, 'equipe 3', TRUE, TRUE, 1),
  ( 4, 'equipe 4', TRUE, TRUE, 2),
  ( 5, 'equipe 5', TRUE, TRUE, 2),
  ( 6, 'equipe 6', TRUE, TRUE, 2),
  ( 7, 'equipe 7', TRUE, TRUE, 2),
  ( 8, 'equipe 8', TRUE, TRUE, 2),
  ( 9, 'equipe 9', TRUE, TRUE, 2),
  ( 10, 'equipe 10', TRUE, TRUE, 2);


ALTER TABLE equipe ALTER COLUMN idequipe RESTART WITH 20;
  
-- Epreuve

INSERT INTO epreuve ( idepreuve, nom_epreuve, date_epreuve, lieu, tarif) VALUES
  ( 1, 'epreuve a', '12/08/2020',  'lieu 1','10'),
  ( 2, 'epreuve b', '17/08/2020', 'lieu 2', '10'),
  ( 3, 'epreuve c', '11/08/2020',  'lieu 1', '20');
  
ALTER TABLE epreuve ALTER COLUMN idepreuve RESTART WITH 10;

-- Participant
 
INSERT INTO participant (idparticipant, idequipe, idepreuve, nom, prenom, idsexe, numero_tel,  date_naissance, adresse, idHierarchie, certificat_medical, mail, niveau, materiel_utilise) VALUES
  ( 1,1,1, 'nom1', 'leo', 1, '0652369825','12/08/1995', 'adresse 1', 1,'fichier 1', 'mail1@gmail.com',  'junior' , 'velo, kanoé'),
  ( 2,2,2, 'nom2', 'bob', 2, '0695582102','12/08/1965', 'adresse 2', 2,   'fichier 2', 'mail2@hotmail.fr', 'senior', 'velo'),
  ( 3,3,3, 'nom3', 'toto',1, '0687109632','12/08/1998','adresse 3', 1,   'fichier 3', 'mail3@orange.fr',  'junior', 'kanoe'),
  (	4,3,2, 'nom4', 'clement',2, '0687107832','12/08/1999','adresse 4', 2,   'fichier 4', 'mail4@orange.fr',  'senior', 'kanoe'),
  ( 5, 4, 2, 'Raymond', 'Sellers', 1, '0891730627', '11/06/1997', 'CP385, 5045 Nec Chemin', 1, 'fichier5', 'cursus@llam.edu', 'senior', 'kanoe'),
  ( 6, 4, 2, 'Brady', 'Kaufman', 2, '0218410983', '03/05/1996', '842-5222 Nec Chemin', 2, 'fichier6', 'vestibulum@dictum.net', 'senior', 'kanoe'),
  ( 7, 5, 2, 'Timon', 'Pratt', 1, '0825681927', '07/12/2000', 'P 584, 4977 Posuere', 1, 'fichier7', 'tincidunt@Fusquam.ca', 'senior', 'kanoe');
  
ALTER TABLE participant ALTER COLUMN idparticipant RESTART WITH 100;
 
-- Documents Licencies

INSERT INTO documents_licencies ( iddocument, idparticipant, nom_doc, date_doc, lien_doc) VALUES
  ( 1, 1, 'doc a', '12/08/2020',  'lien 1'),
  ( 2, 2, 'doc b', '17/08/2020', 'lien 2'),
  ( 3, 3, 'doc c', '11/08/2020',  'lien 1');

ALTER TABLE documents_licencies ALTER COLUMN iddocument RESTART WITH 100;

-- Avoir

INSERT INTO avoir ( idposte, idbenevole) VALUES
  (1, 1),
  (1, 2),
  (2, 3);

  


-- Encadrer

 INSERT INTO encadrer ( idepreuve, idbenevole) VALUES
  (1, 1),
  (2, 2);


