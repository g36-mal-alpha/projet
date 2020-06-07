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
  ( 3, 'Boussard', 'Lucas', '12/08/1986','lien_fichier1', FALSE , 2),
  ( 4, 'Clinton', 'Forbes', '05/12/2000', 'fichier', FALSE, 1),
  ( 5, 'Price', 'Pollard', '11/11/1999', 'fichier', FALSE, 1),
  ( 6, 'Duncan', 'Whitney', '12/01/1998', 'fichier', FALSE, 1),
  ( 7, 'Jarrod', 'Howe', '10/11/1998', 'fichier', FALSE, 1),
  ( 8, 'Garth', 'Rodgers', '04/03/1995', 'fichier', FALSE, 1),
  ( 9, 'Tobias', 'Olson', '26/11/2003', 'fichier' ,TRUE, 1),
  ( 10, 'Ignacia', 'Hodge', '18/08/2000', 'fichier', FALSE, 1),
  ( 11, 'Berk', 'Rojas', '25/10/1994', 'fichier', FALSE, 1),
  ( 12, 'Lamar', 'Dodson', '04/08/1999', 'fichier', FALSE, 1),
  ( 13, 'Gisela', 'Carter', '04/08/1995', 'fichier', FALSE, 1),
  ( 14, 'Malik', 'Acosta', '14/09/2005', 'fichier', TRUE, 1),
  ( 15, 'Aileen', 'Pace', '30/03/1987', 'fichier', FALSE, 1),
  ( 16, 'Maxime', 'Morse', '25/10/1996', 'fichier', FALSE, 1),
  ( 17, 'Ursa', 'James', '30/08/2001', 'fichier', FALSE, 1),
  ( 18, 'Darrel', 'Knight', '09/08/1992', 'fichier', FALSE, 1),
  ( 19, 'Giacomo', 'Marks', '23/05/1979', 'fichier', FALSE, 1),
  ( 20, 'Stuart', 'Pope', '23/02/1998', 'fichier', FALSE, 1);

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
  ( 10, 'equipe 10', TRUE, TRUE, 2),
  ( 11, 'equipe 11', TRUE, TRUE, 2);


ALTER TABLE equipe ALTER COLUMN idequipe RESTART WITH 20;

-- Epreuve

INSERT INTO epreuve ( idepreuve, nom_epreuve, date_epreuve, lieu, tarif) VALUES
  ( 1, 'epreuve a', '12/08/2020',  'lieu 1','10'),
  ( 2, 'epreuve b', '17/08/2020', 'lieu 2', '10'),
  ( 3, 'epreuve c', '11/08/2020',  'lieu 1', '20');

ALTER TABLE epreuve ALTER COLUMN idepreuve RESTART WITH 10;

-- Participant

INSERT INTO participant (idparticipant, idequipe, idepreuve, nom, prenom, idsexe, numero_tel,  date_naissance, adresse, idHierarchie, certificat_medical, mail, niveau, materiel_utilise) VALUES
  ( 1,1,1, 'nom1', 'leo', 1, '0652369825','12/08/1999', 'adresse 1', 1,'fichier 1', 'mail1@gmail.com',  'junior' , 'velo, kanoé'),
  ( 2,2,2, 'nom2', 'bob', 2, '0695582102','12/08/1999', 'adresse 2', 2,   'fichier 2', 'mail2@hotmail.fr', 'senior', 'velo'),
  ( 3,3,3, 'nom3', 'toto',1, '0687109632','12/08/1999','adresse 3', 1,   'fichier 3', 'mail3@orange.fr',  'junior', 'kanoe'),
  (	4,3,2, 'nom4', 'clement',2, '0687107832','12/08/1999','adresse 4', 2,   'fichier 4', 'mail4@orange.fr',  'senior', 'kanoe'),
  ( 5, 4, 2, 'Raymond', 'Sellers', 1, '0891730627', '11/06/1999', 'CP385, 5045 Nec Chemin', 1, 'fichier5', 'cursus@llam.edu', 'senior', 'kanoe'),
  ( 6, 4, 2, 'Brady', 'Kaufman', 2, '0218410983', '03/05/1999', '842-5222 Nec Chemin', 2, 'fichier6', 'vestibulum@dictum.net', 'senior', 'kanoe'),
  ( 7, 5, 2, 'Timon', 'Pratt', 1, '0825681927', '07/12/2000', 'P 584, 4977 Posuere', 1, 'fichier7', 'tincidunt@Fusquam.ca', 'senior', 'kanoe'),
  ( 8, 5, 2, 'Beck', 'Rosa', 2, '0999799168', '01/02/1999', '9683 Nonummy. Rd.', 2, 'fichier8', 'magnis@ismod.edu', 'senior', 'kanoe'),
  ( 9, 6, 2, 'Abdul', 'Shaffer', 1, '0572181455', '02/12/2001', '897-7981 Imperdiet Ave', 1, 'fichier9', 'lacinia@ferment.org', 'senior', 'kanoe'),
  ( 10, 6, 2, 'Tiger', 'Curry', 1, '0783391828', '07/05/1999', '102-7827 Turpis. Avenue', 2, 'fichier10', 'lobortis.mauris@massa.net', 'senior', 'kanoe'),
  ( 11, 7, 2, 'Colton', 'Shepard', 1, '0320283380', '15/01/1998', 'CP 350, 1711 Aliquet Impasse', 1, 'fichier11', 'sed@nec.ca', 'senior', 'kanoe'),
  ( 12, 7, 2, 'Uma', 'Chaney', 2, '0428547038', '25/08/1992', 'Appartement 914-3760', 2, 'fichier12', 'non@nonmassa.com', 'senior', 'kanoe'),
  ( 13, 8, 2, 'Jane', 'Garett', 1, '0167135145', '11/02/1999', 'CP 226, 421 Diam. Chemin', 1, 'fichier13', 'lacus@euismod.com', 'senior', 'kanoe'),
  ( 14, 8, 2, 'Joy', 'Castillo', 2, '0387361313', '30/09/1998', '406-3040 Eu Route', 2, 'fichier14', 'In@aliquetProinvelit.ne', 'senior', 'kanoe'),
  ( 15, 9, 2, 'Winifred', 'Booker', 1, '0464436693', '26/03/2003', 'CP 106, 4535 Arcu. Ave', 1, 'fichier15', 'enim@In.ca', 'senior', 'kanoe'),
  ( 16, 9, 2, 'Maggy', 'Hooper', 2, '0327778074', '01/01/2005', '6702 Fringilla Rd.', 2, 'fichier16', 'dis.parturient@at.com', 'senioer', 'kanoe'),
  ( 17, 10, 2, 'Odysseus', 'Rivers', 1, '0673287643', '25/12/2000', '8196 Neque Rue', 1, 'fichier17', 'Nullam@penatibus.co.uk', 'senior', 'kanoe'),
  ( 18, 10, 2, 'Ina', 'Case', 2, '0804941321', '14/11/1999', 'CP 809, 4831 Nullam Av', 2, 'fichier18', 'a.sollicitudin@PeFusce.org', 'senior', 'kanoe'),
  ( 19, 11, 2, 'Joy', 'Castillo', 2, '0387361313', '18/05/2001', '406-3040 Eu Route', 1, 'fichier19', 'In@aliquetProinvelit.net', 'senior', 'kanoe'),
  ( 20, 11, 2, 'Maggy', 'Hooper', 2, '0327778074', '02/10/2003', '6702 Fringilla Rd.', 2, 'fichier20', 'dis.parturient@at.com', 'senior', 'kanoe'); 

ALTER TABLE participant ALTER COLUMN idparticipant RESTART WITH 100;

-- Documents Licencies

INSERT INTO documents_licencies ( iddocument, idparticipant, nom_doc, date_doc, lien_doc) VALUES
  ( 1, 1, 'doc a', '12/08/2020',  'lien 1'),
  ( 2, 2, 'doc b', '17/08/2020', 'lien 2'),
  ( 3, 3, 'doc c', '11/08/2020',  'lien 1'),
  ( 4, 4, 'doc b', '17/08/2020', 'lien 2'),
  ( 5, 5, 'doc b', '17/08/2020', 'lien 2'),
  ( 6, 6, 'doc b', '17/08/2020', 'lien 2'),
  ( 7, 7, 'doc b', '17/08/2020', 'lien 2'),
  ( 8, 8, 'doc b', '17/08/2020', 'lien 2'),
  ( 9, 9, 'doc b', '17/08/2020', 'lien 2'),
  ( 10, 10, 'doc b', '17/08/2020', 'lien 2'),
  ( 11, 11, 'doc b', '17/08/2020', 'lien 2'),
  ( 12, 12, 'doc b', '17/08/2020', 'lien 2'),
  ( 13, 13, 'doc b', '17/08/2020', 'lien 2'),
  ( 14, 14, 'doc b', '17/08/2020', 'lien 2'),
  ( 15, 15, 'doc b', '17/08/2020', 'lien 2'),
  ( 16, 16, 'doc b', '17/08/2020', 'lien 2'),
  ( 17, 17, 'doc b', '17/08/2020', 'lien 2'),
  ( 18, 18, 'doc b', '17/08/2020', 'lien 2'),
  ( 19, 19, 'doc b', '17/08/2020', 'lien 2'),
  ( 20, 20, 'doc b', '17/08/2020', 'lien 2');

ALTER TABLE documents_licencies ALTER COLUMN iddocument RESTART WITH 100;

-- Avoir

INSERT INTO avoir ( idposte, idbenevole) VALUES
  (1, 1),
  (1, 2),
  (2, 3),
  (5, 4),
  (9, 5),
  (7, 6),
  (4, 7),
  (8, 8),
  (4, 9),
  (9, 10),
  (3, 11),
  (6, 12),
  (12, 13),
  (10, 14),
  (3, 15),
  (2, 16),
  (11, 17),
  (10, 18),
  (8, 19),
  (2, 20);





-- Encadrer

 INSERT INTO encadrer ( idepreuve, idbenevole) VALUES
  (1, 1),
  (2, 2);
