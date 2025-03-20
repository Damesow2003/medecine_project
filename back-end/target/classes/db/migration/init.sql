CREATE DATABASE IF NOT EXISTS projet_medecine;
USE projet_medecine;

DROP TABLE IF EXISTS `cabinet_medecin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cabinet_medecin` (
                                   `id_cabinet` int DEFAULT NULL,
                                   `matricule` int DEFAULT NULL,
                                   KEY `id_cabinet` (`id_cabinet`),
                                   KEY `matricule` (`matricule`),
                                   CONSTRAINT `cabinet_medecin_ibfk_1` FOREIGN KEY (`id_cabinet`) REFERENCES `cabinet_medical` (`id_cabinet`),
                                   CONSTRAINT `cabinet_medecin_ibfk_2` FOREIGN KEY (`matricule`) REFERENCES `medecin` (`matricule`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


LOCK TABLES `cabinet_medecin` WRITE;

UNLOCK TABLES;

--
-- Table structure for table `cabinet_medical`
--

DROP TABLE IF EXISTS `cabinet_medical`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cabinet_medical` (
                                   `id_cabinet` int NOT NULL AUTO_INCREMENT,
                                   `adresse` varchar(255) NOT NULL,
                                   `nom` varchar(100) NOT NULL,
                                   PRIMARY KEY (`id_cabinet`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Dumping data for table `cabinet_medical`
--

LOCK TABLES `cabinet_medical` WRITE;
/*!40000 ALTER TABLE `cabinet_medical` DISABLE KEYS */;
INSERT INTO `cabinet_medical` VALUES (1,'123 Rue Cheikh Anta Diop, Dakar','Cabinet Médical Dakar'),(2,'456 Avenue Léopold Sédar Senghor, Thiès','Cabinet Santé Thiès'),(3,'789 Boulevard du Président, Saint-Louis','Clinique Saint-Louis'),(4,'101 Rue Blaise Diagne, Ziguinchor','Cabinet Médical Ziguinchor'),(5,'123 Rue Cheikh Anta Diop, Dakar','Cabinet Médical Dakar Dakar Dakar'),(6,'123 Rue Cheikh Anta Diop, Dakar','Cabinet Médical Dakar Dakar Dakar updated'),(7,'123 Rue Cheikh Anta Diop, Dakar','Cabinet Médical Dakar Dakar Dakar updated'),(8,'123 Rue Keur masssar, Dakar','Cabinet Médical Dakar'),(10,'abou','string');
/*!40000 ALTER TABLE `cabinet_medical` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medecin`
--

DROP TABLE IF EXISTS `medecin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medecin` (
                           `id_utilisateur` int NOT NULL,
                           `matricule` int NOT NULL AUTO_INCREMENT,
                           `specialite` varchar(100) DEFAULT NULL,
                           PRIMARY KEY (`id_utilisateur`),
                           UNIQUE KEY `matricule` (`matricule`),
                           CONSTRAINT `medecin_ibfk_1` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`)
) ENGINE=InnoDB AUTO_INCREMENT=1003 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medecin`
--

LOCK TABLES `medecin` WRITE;
/*!40000 ALTER TABLE `medecin` DISABLE KEYS */;
INSERT INTO `medecin` VALUES (1,1,'Cardiologie'),(2,2,'Neurologie');
/*!40000 ALTER TABLE `medecin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
                                `id_notification` int NOT NULL AUTO_INCREMENT,
                                `contenu` text,
                                `date_envoie` date DEFAULT NULL,
                                `id_rendezvous` int DEFAULT NULL,
                                PRIMARY KEY (`id_notification`),
                                KEY `id_rendezvous` (`id_rendezvous`),
                                CONSTRAINT `notification_ibfk_1` FOREIGN KEY (`id_rendezvous`) REFERENCES `rendezvous` (`id_rendezvous`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (1,'Contenu de la notification 1','2024-08-01',1),(2,'Contenu de la notification 2','2024-08-02',2),(4,'Contenu de la notification 4','2024-08-04',4),(5,'Contenu de la notification 5','2024-08-05',5),(6,'Contenu de la notification 1 inconnu inconnu inconnu 66','2024-08-01',1),(7,'Contenu de la notification 1 inconnu inconnu inconnu','2024-08-01',1),(8,'Contenu de la notification nouvelle','2024-08-01',2),(9,'contenu add updated','2024-09-12',3),(10,'Expliquer c vraiment difiicile hhh','2024-08-19',NULL);
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paiement`
--

DROP TABLE IF EXISTS `paiement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paiement` (
                            `id_paiement` int NOT NULL AUTO_INCREMENT,
                            `mode_paiement` varchar(50) DEFAULT NULL,
                            `montant` decimal(10,2) DEFAULT NULL,
                            PRIMARY KEY (`id_paiement`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paiement`
--

LOCK TABLES `paiement` WRITE;
/*!40000 ALTER TABLE `paiement` DISABLE KEYS */;
INSERT INTO `paiement` VALUES (1,'Mode11',200.50),(2,'Mode2',150.75),(3,'Mode3',300.20),(4,'Mode4',250.00),(5,'Mode5',100.00),(7,NULL,152.30);
/*!40000 ALTER TABLE `paiement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;

CREATE TABLE `patient` (
                           `id_utilisateur` int NOT NULL,
                           `id_patient` int NOT NULL AUTO_INCREMENT,
                           `date_de_naissance` date DEFAULT NULL,
                           PRIMARY KEY (`id_utilisateur`),
                           UNIQUE KEY `id_patient` (`id_patient`),
                           CONSTRAINT `patient_ibfk_1` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (1,1,'2024-08-10');
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prescription`
--

DROP TABLE IF EXISTS `prescription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prescription` (
                                `id_prescription` int NOT NULL AUTO_INCREMENT,
                                `medicament` varchar(255) DEFAULT NULL,
                                `date` date DEFAULT NULL,
                                `id_rendezvous` int DEFAULT NULL,
                                PRIMARY KEY (`id_prescription`),
                                KEY `id_rendezvous` (`id_rendezvous`),
                                CONSTRAINT `prescription_ibfk_1` FOREIGN KEY (`id_rendezvous`) REFERENCES `rendezvous` (`id_rendezvous`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prescription`
--

LOCK TABLES `prescription` WRITE;
/*!40000 ALTER TABLE `prescription` DISABLE KEYS */;
INSERT INTO `prescription` VALUES (2,'Medicament2','2024-08-02',2),(3,'Medicament3','2024-08-03',3),(4,'Medicament4','2024-08-04',4),(23,'string addedd addedd V2 updated','2024-09-15',5);
/*!40000 ALTER TABLE `prescription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rendezvous`
--

DROP TABLE IF EXISTS `rendezvous`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rendezvous` (
                              `id_rendezvous` int NOT NULL AUTO_INCREMENT,
                              `date_rv` date DEFAULT NULL,
                              `heure_rv` time DEFAULT NULL,
                              `duree` int DEFAULT NULL,
                              `id_paiement` int DEFAULT NULL,
                              `id_cabinet` int DEFAULT NULL,
                              PRIMARY KEY (`id_rendezvous`),
                              KEY `id_paiement` (`id_paiement`),
                              KEY `id_cabinet` (`id_cabinet`),
                              CONSTRAINT `rendezvous_ibfk_1` FOREIGN KEY (`id_paiement`) REFERENCES `paiement` (`id_paiement`),
                              CONSTRAINT `rendezvous_ibfk_2` FOREIGN KEY (`id_cabinet`) REFERENCES `cabinet_medical` (`id_cabinet`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rendezvous`
--

LOCK TABLES `rendezvous` WRITE;
/*!40000 ALTER TABLE `rendezvous` DISABLE KEYS */;
INSERT INTO `rendezvous` VALUES (1,'2024-08-06','10:30:00',60,1,1),(2,'2024-08-07','11:00:00',45,2,2),(3,'2024-08-08','12:00:00',30,3,3),(4,'2024-08-09','13:30:00',90,4,NULL),(5,'2025-08-10','21:55:00',60,5,NULL),(7,'2055-08-10','21:55:00',11,5,NULL),(9,'2024-08-06','17:30:00',60,1,NULL),(14,'2024-08-19','05:41:26',1150,NULL,NULL),(15,'2024-08-19','05:41:26',1150,NULL,NULL),(16,'2024-08-19','05:41:26',1150,NULL,NULL);
/*!40000 ALTER TABLE `rendezvous` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salle`
--

DROP TABLE IF EXISTS `salle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salle` (
                         `id_salle` int NOT NULL AUTO_INCREMENT,
                         `numero_salle` varchar(100) DEFAULT NULL,
                         `nom_salle` varchar(100) DEFAULT NULL,
                         `id_cabinet` int DEFAULT NULL,
                         PRIMARY KEY (`id_salle`),
                         KEY `id_cabinet` (`id_cabinet`),
                         CONSTRAINT `salle_ibfk_1` FOREIGN KEY (`id_cabinet`) REFERENCES `cabinet_medical` (`id_cabinet`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salle`
--

LOCK TABLES `salle` WRITE;
/*!40000 ALTER TABLE `salle` DISABLE KEYS */;
INSERT INTO `salle` VALUES (1,'testXX','sallexx',1),(2,'BLOCB12','Consultation 2',1),(3,'BLOCC2','Radiologie',2),(4,'BLOCD3','Pédiatrie',3),(7,'stringTestt','TestTester',6);
/*!40000 ALTER TABLE `salle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `traitement`
--

DROP TABLE IF EXISTS `traitement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `traitement` (
                              `id_traitement` int NOT NULL AUTO_INCREMENT,
                              `nom` varchar(100) DEFAULT NULL,
                              `id_patient` int DEFAULT NULL,
                              `id_salle` int DEFAULT NULL,
                              `id_rendezvous` int DEFAULT NULL,
                              `matricule_medecin` int DEFAULT NULL,
                              PRIMARY KEY (`id_traitement`),
                              KEY `id_salle` (`id_salle`),
                              KEY `id_patient` (`id_patient`),
                              KEY `id_rendezvous` (`id_rendezvous`),
                              KEY `matricule_medecin` (`matricule_medecin`),
                              CONSTRAINT `traitement_ibfk_1` FOREIGN KEY (`id_salle`) REFERENCES `salle` (`id_salle`),
                              CONSTRAINT `traitement_ibfk_2` FOREIGN KEY (`id_patient`) REFERENCES `patient` (`id_patient`),
                              CONSTRAINT `traitement_ibfk_3` FOREIGN KEY (`id_rendezvous`) REFERENCES `rendezvous` (`id_rendezvous`),
                              CONSTRAINT `traitement_ibfk_4` FOREIGN KEY (`matricule_medecin`) REFERENCES `medecin` (`matricule`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `traitement`
--

LOCK TABLES `traitement` WRITE;
/*!40000 ALTER TABLE `traitement` DISABLE KEYS */;
INSERT INTO `traitement` VALUES (10,'TraitementA',1,1,1,1),(11,'TraitementB',1,2,2,2),(14,'TestUpdated',1,1,3,1);
/*!40000 ALTER TABLE `traitement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;

CREATE TABLE `utilisateur` (
                               `id_utilisateur` int NOT NULL AUTO_INCREMENT,
                               `prenom` varchar(50) DEFAULT NULL,
                               `nom` varchar(50) DEFAULT NULL,
                               `email` varchar(100) DEFAULT NULL,
                               `mot_de_passe` varchar(255) DEFAULT NULL,
                               `telephone` varchar(20) DEFAULT NULL,
                               `adresse` varchar(255) DEFAULT NULL,
                               `role` varchar(50) DEFAULT NULL,
                               PRIMARY KEY (`id_utilisateur`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Dumping data for table `utilisateur`
--

LOCK TABLES `utilisateur` WRITE;

INSERT INTO `utilisateur` VALUES (1,'Ibrahima','Watt','watt@example.com','$2a$12$eMqWD4GrJXgts4PbfZkM0O5UHbFbkpp.6kfZQsrz0yaPbVzRXDYje','0123456789','123 Rue Principale, Bambey','medecin'),(2,'Omar','Seye','omar@example.com','$2a$12$v/HZ0fzWDzWLCwqzs1liZerKYkKPBATha1.jt/BkRVmcGhXWQBjNy','0123456790','456 Avenue des Champs, Yeum','medecin'),(3,'Abou','Sow','abou@example.com','$2a$12$4FV6PJ09Uups/Ukb4hRh3.UBDTXoHuG3hrPZsoyZtGOBvblg2gvUS','0123456791','789 Boulevard Dakar','patient'),(4,'Dmzz','buhaha','dmzz@example.com','$2a$12$lZd45MVSHaFbdc7u6rHipeiUaGN98rkU8xmtMGmR.CIP47HAv306.','0123456792','101 Rue de la République buhaha','patient');
