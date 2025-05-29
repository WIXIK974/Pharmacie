SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

DROP DATABASE IF EXISTS `PharmacixWE`;
CREATE DATABASE `PharmacixWE` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `PharmacixWE`;

DROP TABLE IF EXISTS `Employe`;
CREATE TABLE `Employe` (
  `idPersonne` int NOT NULL AUTO_INCREMENT,
  `numEmploye` varchar(20) DEFAULT NULL,
  `profession` varchar(255) DEFAULT NULL,
  `salaire` decimal(10,2) DEFAULT NULL,
  `typeContrat` varchar(50) DEFAULT NULL,
  `identifiant` varchar(50) NOT NULL,
  `password` varchar(64) NOT NULL,
  `status` tinyint(1) NOT NULL,
  PRIMARY KEY (`idPersonne`),
  UNIQUE KEY `identifiant` (`identifiant`),
  UNIQUE KEY `numEmploye` (`numEmploye`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `Employe` (`idPersonne`, `numEmploye`, `profession`, `salaire`, `typeContrat`, `identifiant`, `password`, `status`) VALUES
(5,	'EMP001',	'Développeur',	3000.00,	'CDI',	'pepe',	'$2a$10$PlHnUyIFkybT5lOE8UHsuuP2dizH2Dfim22aw9cyYUoxjD/LvPENy',	1),
(12,	NULL,	'test3',	3.30,	'cdd',	'test3',	'test3mdp',	0);

DROP TABLE IF EXISTS `Fournisseur`;
CREATE TABLE `Fournisseur` (
  `numFournisseur` varchar(20) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`numFournisseur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `Fournisseur` (`numFournisseur`, `nom`, `adresse`, `status`) VALUES
('F001',	'PharmaSupply',	'123 rue du Médicament, Paris',	1);


DROP TABLE IF EXISTS `Medicament`;
CREATE TABLE `Medicament` (
  `numMedicament` int NOT NULL AUTO_INCREMENT,
  `prix` decimal(10,2) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `type` varchar(50) DEFAULT NULL,
  `tableau` varchar(50) DEFAULT NULL,
  `quantite` varchar(20) DEFAULT NULL,
  `enVenteLibre` tinyint(1) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `dateExpiration` date DEFAULT NULL,
  PRIMARY KEY (`numMedicament`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `Medicament` (`numMedicament`, `prix`, `nom`, `type`, `tableau`, `quantite`, `enVenteLibre`, `status`, `dateExpiration`) VALUES
(6,	20.00,	'Smecta',	'En poudre',	'Tableau A',	'30',	1,	NULL,	NULL),
(8,	33.00,	'T',	'AEZ',	'A',	'12',	1,	NULL,	NULL),
(9,	32.70,	'test',	'test',	'1',	'3',	1,	NULL,	NULL);



DROP TABLE IF EXISTS `MedicamentArchive`;
CREATE TABLE `MedicamentArchive` (
  `numMedicament` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `prix` decimal(10,2) NOT NULL,
  `nom` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `tableau` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `quantite` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `enVenteLibre` tinyint(1) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `DateArchivage` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `MedicamentArchive` (`numMedicament`, `prix`, `nom`, `type`, `tableau`, `quantite`, `enVenteLibre`, `status`, `DateArchivage`) VALUES
('0',	4.00,	'TEST',	'Comprimé',	'Antalgique',	'20',	0,	NULL,	'2025-05-12 15:50:09'),
('003',	4.90,	'Spasfon',	'Comprimé',	'C',	'30',	1,	1,	'2025-05-12 15:50:09'),
('MED001',	12.50,	'Doliprane',	'Antalgique',	'Tableau A',	'20',	1,	1,	'2025-05-12 15:50:09'),
('1',	10.00,	'SpasCon',	'Comprimé',	'A',	'10',	1,	NULL,	'2025-05-12 16:05:28'),
('2',	2.00,	's',	's',	'2',	'2',	1,	NULL,	'2025-05-12 16:12:21'),
('3',	1.00,	'1',	'comprimé',	'A',	'3',	1,	NULL,	'2025-05-12 16:22:10'),
('4',	10.00,	'Spasfon',	'Comprimé',	'A',	'10',	1,	NULL,	'2025-05-12 18:50:52'),
('5',	1.00,	'1',	'comprimé',	'1',	'2',	1,	NULL,	'2025-05-12 20:19:46'),
('7',	3.00,	'test',	'test',	'1',	'3',	1,	NULL,	'2025-05-13 12:14:08'),
('10',	30.00,	'test2',	'3',	'1',	'15',	0,	NULL,	'2025-05-18 13:27:06'),
('11',	40.00,	'test2',	'test2',	'test2',	'13',	1,	NULL,	'2025-05-26 12:05:37');



DROP TABLE IF EXISTS `Personne`;
CREATE TABLE `Personne` (
  `idPersonne` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `dateNaissance` date NOT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `numeroTel` varchar(20) DEFAULT NULL,
  `type` enum('Employe','Patient','Medecin') NOT NULL,
  PRIMARY KEY (`idPersonne`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `Personne` (`idPersonne`, `nom`, `prenom`, `dateNaissance`, `adresse`, `numeroTel`, `type`) VALUES
(1,	'Dupont',	'Jean',	'1980-05-12',	'10 rue des Lilas, Paris',	'0601020304',	'Employe'),
(2,	'Martin',	'Alice',	'1992-07-23',	'15 avenue de la République, Lyon',	'0612345678',	'Patient'),
(3,	'Durand',	'Paul',	'1975-02-17',	'5 boulevard Haussmann, Marseille',	'0623456789',	'Medecin'),
(4,	'weil',	'Matteo',	'2000-02-01',	'rtest',	'67890',	'Employe');

DROP TABLE IF EXISTS `Patient`;
CREATE TABLE `Patient` (
  `idPersonne` int NOT NULL,
  `numeroSS` varchar(20) NOT NULL,
  `mutuelle` varchar(255) DEFAULT NULL,
  `taille` decimal(5,2) DEFAULT NULL,
  `poids` decimal(5,2) DEFAULT NULL,
  `sexe` char(1) DEFAULT NULL,
  `fk_medecin` varchar(20) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idPersonne`),
  UNIQUE KEY `numeroSS` (`numeroSS`),
  CONSTRAINT `Patient_ibfk_1` FOREIGN KEY (`idPersonne`) REFERENCES `Personne` (`idPersonne`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `Patient` (`idPersonne`, `numeroSS`, `mutuelle`, `taille`, `poids`, `sexe`, `fk_medecin`, `status`) VALUES
(2,	'123456789012345',	'MutuelleXYZ',	1.65,	55.20,	'F',	'DURAND123',	1);


DROP TABLE IF EXISTS `Medecin`;
CREATE TABLE `Medecin` (
  `idPersonne` int NOT NULL,
  `numeroRPPS` varchar(20) NOT NULL,
  `specialite` varchar(255) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idPersonne`),
  UNIQUE KEY `numeroRPPS` (`numeroRPPS`),
  CONSTRAINT `Medecin_ibfk_1` FOREIGN KEY (`idPersonne`) REFERENCES `Personne` (`idPersonne`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `Medecin` (`idPersonne`, `numeroRPPS`, `specialite`, `status`) VALUES
(3,	'DURAND123',	'Généraliste',	1);

DROP TABLE IF EXISTS `Ordonnance`;
CREATE TABLE `Ordonnance` (
  `numOrdonnance` int NOT NULL AUTO_INCREMENT,
  `dateOrdonnance` date NOT NULL,
  `dateValidite` date NOT NULL,
  `periodicite` varchar(50) DEFAULT NULL,
  `fk_numeroRPPS` varchar(20) NOT NULL,
  `fk_numeroSS` varchar(20) NOT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`numOrdonnance`),
  KEY `fk_numeroRPPS` (`fk_numeroRPPS`),
  KEY `fk_numeroSS` (`fk_numeroSS`),
  CONSTRAINT `Ordonnance_ibfk_1` FOREIGN KEY (`fk_numeroRPPS`) REFERENCES `Medecin` (`numeroRPPS`) ON DELETE CASCADE,
  CONSTRAINT `Ordonnance_ibfk_2` FOREIGN KEY (`fk_numeroSS`) REFERENCES `Patient` (`numeroSS`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `Ordonnance` (`numOrdonnance`, `dateOrdonnance`, `dateValidite`, `periodicite`, `fk_numeroRPPS`, `fk_numeroSS`, `status`) VALUES
(1001,	'2025-05-10',	'2025-06-10',	'mensuelle',	'DURAND123',	'123456789012345',	1);

DROP TABLE IF EXISTS `Vente`;
CREATE TABLE `Vente` (
  `numVente` int NOT NULL AUTO_INCREMENT,
  `fk_numeroSS` varchar(20) NOT NULL,
  `fk_numEmploye` varchar(20) NOT NULL,
  `fk_numMedicament` int DEFAULT NULL,
  `fk_numOrdonnance` int DEFAULT NULL,
  `dateVente` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `quantiteVendue` varchar(20) NOT NULL,
  PRIMARY KEY (`numVente`),
  KEY `fk_numeroSS` (`fk_numeroSS`),
  KEY `fk_numEmploye` (`fk_numEmploye`),
  KEY `fk_numMedicament` (`fk_numMedicament`),
  KEY `fk_numOrdonnance` (`fk_numOrdonnance`),
  CONSTRAINT `Vente_ibfk_1` FOREIGN KEY (`fk_numeroSS`) REFERENCES `Patient` (`numeroSS`) ON DELETE CASCADE,
  CONSTRAINT `Vente_ibfk_2` FOREIGN KEY (`fk_numEmploye`) REFERENCES `Employe` (`numEmploye`) ON DELETE CASCADE,
  CONSTRAINT `Vente_ibfk_3` FOREIGN KEY (`fk_numMedicament`) REFERENCES `Medicament` (`numMedicament`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `Vente_ibfk_4` FOREIGN KEY (`fk_numOrdonnance`) REFERENCES `Ordonnance` (`numOrdonnance`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `Vente` (`numVente`, `fk_numeroSS`, `fk_numEmploye`, `fk_numMedicament`, `fk_numOrdonnance`, `dateVente`, `quantiteVendue`) VALUES
(5,	'123456789012345',	'EMP001',	6,	1001,	'2025-05-26 00:00:00',	'5'),
(6,	'123456789012345',	'EMP001',	6,	1001,	'2025-05-26 00:00:00',	'10');

DROP TABLE IF EXISTS `Commande`;
CREATE TABLE `Commande` (
  `numCommande` int NOT NULL AUTO_INCREMENT,
  `dateCommande` date NOT NULL,
  `dateLivraison` date NOT NULL,
  `quantiteCommande` int NOT NULL,
  `fk_idPersonne` int NOT NULL,
  `fk_numFournisseur` varchar(20) NOT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`numCommande`),
  KEY `fk_idPersonne` (`fk_idPersonne`),
  KEY `fk_numFournisseur` (`fk_numFournisseur`),
  CONSTRAINT `Commande_ibfk_1` FOREIGN KEY (`fk_idPersonne`) REFERENCES `Employe` (`idPersonne`) ON DELETE CASCADE,
  CONSTRAINT `Commande_ibfk_2` FOREIGN KEY (`fk_numFournisseur`) REFERENCES `Fournisseur` (`numFournisseur`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


DELIMITER ;;

CREATE TRIGGER `maj_quantite_apres_vente` AFTER INSERT ON `Vente` FOR EACH ROW
BEGIN
    UPDATE Medicament
    SET quantite = CAST(quantite AS UNSIGNED) - CAST(NEW.quantiteVendue AS UNSIGNED)
    WHERE numMedicament = NEW.fk_numMedicament;
END;;

DELIMITER ;


DELIMITER ;;

CREATE TRIGGER `archivageMed` AFTER DELETE ON `Medicament` FOR EACH ROW
BEGIN
    INSERT INTO MedicamentArchive (
        numMedicament, prix, nom, type, tableau, quantite, EnVenteLibre, `status`, DateArchivage
    )
    VALUES (
        OLD.numMedicament, OLD.prix, OLD.nom, OLD.type, OLD.tableau,
        OLD.quantite, OLD.EnVenteLibre, OLD.`status`, NOW()
    );
END;;

DELIMITER ;

-- 2025-05-27 17:51:30
