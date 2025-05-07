--$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$&&&$$$$$$$$$$$$$$$$$$$$$
-- Script de création des tables pour la base Gestion de dominante
--$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$&&&$$$$$$$$$$$$$$$$$$$$$$

CREATE TABLE utilisateur (
    idUtilisateur NUMBER,
    password VARCHAR2(255) NOT NULL, 
    type VARCHAR2(20) CHECK (type IN ('etudiant', 'administrateur')) NOT NULL,
    CONSTRAINT pk_utilisateur PRIMARY KEY(idUtilisateur),
);

CREATE TABLE etapeTraitement (  
    idEtape NUMBER,
    niveauTraitement VARCHAR2(10) CHECK (type IN ('en attente', 'en cour','terminé')) NOT NULL,
    CONSTRAINT pk_etape PRIMARY KEY(idEtape),
);

CREATE TABLE promotion (
    idPromotion NUMBER,
    promotion_id_etape NUMBER,
    anneDiplomation NUMBER(4) CHECK (anneDiplomation BETWEEN 2020 AND 2100),
    CONSTRAINT pk_promotion PRIMARY KEY(idPromotion),
    CONSTRAINT fk_etapeTraitement FOREIGN KEY(promotion_id_etape) REFERENCES etapeTraitement(idEtape)
);

CREATE TABLE etudiant (
    idEtudiant NUMBER,
    etudiant_id_utilisateur NUMBER,
    etudiant_id_promotion NUMBER,
    nom VARCHAR2(50) NOT NULL,
    prenom VARCHAR2(100) NOT NULL,
    dateNaissance DATE NOT NULL,
    classement1A NUMBER CHECK (classement1A > 0),
    filiere VARCHAR2(10) DEFAULT 'classique' CHECK (filiere IN('alternant', 'classqiue')),
    CONSTRAINT pk_etudiant PRIMARY KEY(idEtudiant),
    CONSTRAINT fk_promotion FOREIGN KEY(etudiant_id_promotion) REFERENCES promotion(idPromotion),
    CONSTRAINT fk_utilisateur FROEIGN KEY(etudiant_id_utilisateur) REFERENCES utilisateur(idUtilisateur)
);


CREATE TABLE dominante (
    idDominante NUMBER,
    nom VARCHAR2(200) NOT NULL,
    sigle VARCHAR2(10) NOT NULL,
    CONSTRAINT pk_dominante PRIMARY KEY(idDominante),
);

CREATE TABLE choix (
    choix_id_etudiant NUMBER,
    choix_id_dominante NUMBER,
    numeroDordre NUMBER NOT NULL,
    validation VARCHAR2(20) DEFAULT 'en attente' CHECK (validation IN('en attente', 'valide')),
    CONSTRAINT pk_choix PRIMARY KEY(choix_id_etudiant, choix_id_dominante),
    CONSTRAINT fk_choix_id_etudiant FOREIGN KEY(choix_id_etudiant) REFERENCES etudiant(idEtudiant),
    CONSTRAINT fk_choix_id_dominante FOREIGN KEY(choix_id_dominante) REFERENCES dominante(idDominante)
);

CREATE TABLE place (
    place_id_promotion NUMBER,
    place_id_dominante NUMBER,
    nombrePlaceMax NUMBER,
    nombrePlaceReserveAlternant NUMBER,
    CONSTRAINT pk_place PRIMARY KEY(place_id_promotion, place_id_dominante),
    CONSTRAINT fk_place_id_promotion FOREIGN KEY(place_id_promotion) REFERENCES promotion(idPromotion)ON DELETE CASCADE,
    CONSTRAINT fk_place_id_dominante FOREIGN KEY(place_id_dominante) REFERENCES dominante(idDominante)ON DELETE CASCADE
);


--$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$&&&$$$$$$$$$$$$$$$$$$$$$
-- Script d'insertion des données pour la base Geestion de dominante
--$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$&&&$$$$$$$$$$$$$$$$$$$$$

INSERT INTO utilisateur(idUtilisateur, password, type) VALUES
(1,'admin1-Dom2025', 'administrateur'),
(2,'admin2-Dom2025', 'administrateur'),
(3,'etudiant1-Esigelec', 'etudiant'),
(4,'etudiant2-Eisgelec', 'etudiant'),
(5,'etudiant3-Eisgelec', 'etudiant'),
(6,'etudiant4-Eisgelec', 'etudiant'),
(7,'etudiant5-Eisgelec', 'etudiant'),
(8,'etudiant6-Eisgelec', 'etudiant'),
(9,'etudiant7-Eisgelec', 'etudiant'),
(10,'etudiant8-Eisgelec', 'etudiant'),
(11,'etudiant9-Eisgelec', 'etudiant'),
(12,'etudiant10-Eisgelec', 'etudiant');

INSERT INTO etapeTraitement(idEtape, niveauTraitement) VALUES 
(1, 'en attente'),
(2, 'etape choix'),
(3, 'etape validation');
(4, 'traitement terminé');


INSERT INTO promotion(idPromotion, promotion_id_etape, anneDiplomation) VALUES 
(1, 3, 2025),
(2, 3, 2026),
(3, 2, 2027),
(4, 1, 2028),
(5, 1, 2029);

INSERT INTO dominante(iddominante, nom, sigle) VALUES (1, 'Intelligence Artificielle et Big Data-Campus de Poitiers', 'IABD');
INSERT INTO dominante(iddominante, nom, sigle) VALUES (2, 'Développement Logiciel, Test et Qualité - Campus de Poitiers', 'DLTQ');
INSERT INTO dominante(iddominante, nom, sigle) VALUES (3, 'Electronique des Systèmes pour l"Automobile et l"Aerospace', 'ESAA');
INSERT INTO dominante(iddominante, nom, sigle) VALUES (4, 'Ingénierie des Systèmes Embarqués, Mobiles, Autonomes et Connectés', 'ISEMAC');
INSERT INTO dominante(iddominante, nom, sigle) VALUES (5, 'Digitalisation, Automatisation, Robotique et IA pour l"Industrie', 'DARIA');
INSERT INTO dominante(iddominante, nom, sigle) VALUES (6, 'Energie et Développement Durable', 'EDD');
INSERT INTO dominante(iddominante, nom, sigle) VALUES (7, 'Génie Electrique et Transport', 'GET');
INSERT INTO dominante(iddominante, nom, sigle) VALUES (8, 'Ingénieur d"Affaires-Distribution d"Energie et Signaux', 'IADES');
INSERT INTO dominante(iddominante, nom, sigle) VALUES (9, 'Big Data pour la Transformation Numérique', 'BDTN');
INSERT INTO dominante(iddominante, nom, sigle) VALUES (10, 'Cybersécurité des Réseaux et de l"IoT', 'CERT');
INSERT INTO dominante(iddominante, nom, sigle) VALUES (11, 'Ingénieur d"Affaires-Informatique et Réseaux', 'IAIR');
INSERT INTO dominante(iddominante, nom, sigle) VALUES (12, 'Ingénieur Finance', 'IF');
INSERT INTO dominante(iddominante, nom, sigle) VALUES (13, 'Ingénierie des Services du Numérique', 'ISN');


insert into etudiant(idetudiant, etudiant_id_utilisateur, etudiant_id_promotion, nom, prenom, datenaissance, classement1a, filiere) values 
(12056, 3, 3, 'KAKOU', 'Joseph', TO_DATE('01-01-2004', 'DD-MM-YYYY'), 1, 'classique')
(12057, 4, 3, 'DIOP', 'Sidy', TO_DATE('02-02-2004', 'DD-MM-YYYY'), 2, 'classique')
(12058, 5, 3, 'CHEVALIER', 'Lucas', TO_DATE('02-02-2004', 'DD-MM-YYYY'), 3, 'classique')
(12059, 6, 3, 'ROMAY', 'Ornella', TO_DATE('02-02-2004', 'DD-MM-YYYY'), 4, 'classique')
(12060, 7, 3, 'OSIRUS', 'Nelly', TO_DATE('02-02-2004', 'DD-MM-YYYY'), 5, 'classique')
(12061, 8, 3, 'NEL', 'Lidia', TO_DATE('02-02-2004', 'DD-MM-YYYY'), 6, 'classique')
(12062, 9, 3, 'GOUE', 'Loic', TO_DATE('02-02-2004', 'DD-MM-YYYY'), 1, 'alternant')
(12063, 10, 3, 'OMBA', 'Paul', TO_DATE('02-02-2004', 'DD-MM-YYYY'), 2, 'alternant')
(12063, 11, 3, 'KAKOU', 'Esaie', TO_DATE('02-02-2004', 'DD-MM-YYYY'), 3, 'alternant')
(12063, 12, 3, 'JONG', 'Son', TO_DATE('02-02-2004', 'DD-MM-YYYY'), 4, 'alternant');


insert into etudiant(idetudiant, etudiant_id_utilisateur, etudiant_id_promotion, nom, prenom, datenaissance, classement1a, filiere) values 
(12056, 3, 3, 'KAKOU', 'Joseph', TO_DATE('01-01-2004', 'DD-MM-YYYY'), 1, 'classique');
insert into etudiant(idetudiant, etudiant_id_utilisateur, etudiant_id_promotion, nom, prenom, datenaissance, classement1a, filiere) values 
(12057, 4, 3, 'DIOP', 'Sidy', TO_DATE('02-01-2004', 'DD-MM-YYYY'), 2, 'classique');
insert into etudiant(idetudiant, etudiant_id_utilisateur, etudiant_id_promotion, nom, prenom, datenaissance, classement1a, filiere) values 
(12058, 5, 3, 'CHEVALIER', 'Lucas', TO_DATE('03-01-2004', 'DD-MM-YYYY'), 3, 'classique');
insert into etudiant(idetudiant, etudiant_id_utilisateur, etudiant_id_promotion, nom, prenom, datenaissance, classement1a, filiere) values 
(12059, 6, 3, 'ROMAY', 'Ornella', TO_DATE('04-01-2004', 'DD-MM-YYYY'), 4, 'classique');
insert into etudiant(idetudiant, etudiant_id_utilisateur, etudiant_id_promotion, nom, prenom, datenaissance, classement1a, filiere) values 
(12060, 7, 3, 'OSIRUS', 'Nelly', TO_DATE('05-01-2004', 'DD-MM-YYYY'), 5, 'classique');
insert into etudiant(idetudiant, etudiant_id_utilisateur, etudiant_id_promotion, nom, prenom, datenaissance, classement1a, filiere) values 
(12061, 8, 3, 'NEL', 'Lidia', TO_DATE('06-01-2004', 'DD-MM-YYYY'), 6, 'classique');


