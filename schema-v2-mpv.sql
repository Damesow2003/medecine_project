
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cabinet_medecin`
--

LOCK TABLES `cabinet_medecin` WRITE;
/*!40000 ALTER TABLE `cabinet_medecin` DISABLE KEYS */;
/*!40000 ALTER TABLE `cabinet_medecin` ENABLE KEYS */;
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
  `image` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id_cabinet`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cabinet_medical`
--

LOCK TABLES `cabinet_medical` WRITE;
/*!40000 ALTER TABLE `cabinet_medical` DISABLE KEYS */;
INSERT INTO `cabinet_medical` VALUES (1,'123 Rue Cheikh Anta Diop, Dakar','Cabinet Médical Dakar','https://lh3.googleusercontent.com/p/AF1QipM4ek-uh5o9XRaxOm19PGXOToVn-cQHx4A6CBP0=s680-w680-h510'),(2,'456 Avenue Léopold Sédar Senghor, Thiès','Cabinet Santé Thiès','https://lh3.googleusercontent.com/p/AF1QipO1ne_mMDuawfHDD7BpU9YSAHC9YzMcYjKA789x=s680-w680-h510'),(3,'string updated','string updated',NULL),(4,'101 Rue Blaise Diagne, Ziguinchor','Cabinet Médical Ziguinchor','https://lh3.googleusercontent.com/p/AF1QipPlNLbUi2EtwlL7-wPuCF_gT7ebmHXr6z2OQqD_=s680-w680-h510'),(5,'123 Rue Cheikh Anta Diop, Dakar','Cabinet Médical Dakar Dakar Dakar','https://lh3.googleusercontent.com/p/AF1QipP1E-IKIFEVjIDAIoSDw6OlRnHpAKeWoC7Go2I9=s680-w680-h510'),(6,'123 Rue Cheikh Anta Diop, Dakar','Cabinet Médical Dakar Dakar Dakar updated','https://lh5.googleusercontent.com/p/AF1QipPZVC-PYkXKTBSfDbHZdUSDo88G5Vb4bv8zKMGs=w408-h244-k-no'),(8,'123 Rue Keur masssar, Dakar','Cabinet Médical Dakar','https://lh5.googleusercontent.com/p/AF1QipM3jsnh6-hQDVm8ONNY9xantbx5rRZKdxWjSwY1=w408-h306-k-no'),(10,'abou','string',NULL),(12,'string add','string add',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=1005 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medecin`
--

LOCK TABLES `medecin` WRITE;
/*!40000 ALTER TABLE `medecin` DISABLE KEYS */;
INSERT INTO `medecin` VALUES (1,1,'Cardiologie'),(2,2,'Neurologie'),(5,1003,NULL),(6,1004,NULL);
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
  `date_envoie` varchar(250) DEFAULT NULL,
  `id_rendezvous` int DEFAULT NULL,
  PRIMARY KEY (`id_notification`),
  KEY `id_rendezvous` (`id_rendezvous`),
  CONSTRAINT `notification_ibfk_1` FOREIGN KEY (`id_rendezvous`) REFERENCES `rendezvous` (`id_rendezvous`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `status` varchar(250) DEFAULT 'NOPAYER',
  PRIMARY KEY (`id_paiement`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paiement`
--

LOCK TABLES `paiement` WRITE;
/*!40000 ALTER TABLE `paiement` DISABLE KEYS */;
INSERT INTO `paiement` VALUES (1,'Mode11',200.50,'PAYER'),(2,'Mode2',150.75,'PAYER'),(3,'Mode3',300.20,'NOPAYER'),(4,'Mode4',250.00,'NOPAYER'),(5,'Mode5',100.00,'NOPAYER'),(7,NULL,152.30,'PAYER');
/*!40000 ALTER TABLE `paiement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient` (
  `id_utilisateur` int NOT NULL,
  `id_patient` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_utilisateur`),
  UNIQUE KEY `id_patient` (`id_patient`),
  CONSTRAINT `patient_ibfk_1` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (1,1),(7,8),(8,9),(9,10),(10,11),(11,12),(12,13),(13,14),(14,15),(15,16),(16,17),(17,18),(18,19);
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
  `date` varchar(250) DEFAULT NULL,
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
  `date_rv` varchar(250) DEFAULT NULL,
  `heure_rv` varchar(250) DEFAULT NULL,
  `duree` int DEFAULT NULL,
  `id_paiement` int DEFAULT NULL,
  `id_cabinet` int DEFAULT NULL,
  PRIMARY KEY (`id_rendezvous`),
  KEY `id_paiement` (`id_paiement`),
  KEY `id_cabinet` (`id_cabinet`),
  CONSTRAINT `rendezvous_ibfk_1` FOREIGN KEY (`id_paiement`) REFERENCES `paiement` (`id_paiement`),
  CONSTRAINT `rendezvous_ibfk_2` FOREIGN KEY (`id_cabinet`) REFERENCES `cabinet_medical` (`id_cabinet`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rendezvous`
--

LOCK TABLES `rendezvous` WRITE;
/*!40000 ALTER TABLE `rendezvous` DISABLE KEYS */;
INSERT INTO `rendezvous` VALUES (1,'2024-08-06','10:30:00',60,1,1),(2,'2024-08-07','11:00:00',45,2,2),(3,'2024-08-08','12:00:00',30,3,3),(4,'2024-08-09','13:30:00',90,4,3),(5,'2025-08-10','21:55:00',60,5,3),(7,'2055-08-10','21:55:00',11,5,2),(9,'2024-08-06','17:30:00',60,1,2),(14,'2024-08-19','05:41:26',1150,2,3),(15,'2024-08-19','05:41:26',1150,1,3),(16,'2024-08-19','05:41:26',1150,1,2),(19,'2024-08-06','15:10:00',60,2,1),(20,'2024-07-06','15:15:00',65,3,1),(21,'2024-07-06','16:15:00',30,3,1),(22,'2024-07-06','17:15:00',25,4,1);
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
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utilisateur` (
  `id_utilisateur` int NOT NULL AUTO_INCREMENT,
  `prenom` varchar(50) DEFAULT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `mot_de_passe` varchar(255) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `role` varchar(50) DEFAULT NULL,
  `confirm_email` varchar(100) DEFAULT NULL,
  `confirm_mot_de_passe` varchar(500) DEFAULT NULL,
  `contact_preference` varchar(255) DEFAULT NULL,
  `nom_user` varchar(100) DEFAULT NULL,
  `date_de_naissance` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id_utilisateur`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilisateur`
--

LOCK TABLES `utilisateur` WRITE;
/*!40000 ALTER TABLE `utilisateur` DISABLE KEYS */;
INSERT INTO `utilisateur` VALUES (1,'Ibrahima','Watt','watt@example.com','$2a$12$eMqWD4GrJXgts4PbfZkM0O5UHbFbkpp.6kfZQsrz0yaPbVzRXDYje','0123456789','123 Rue Principale, Bambey','medecin','watt@example.com','$2a$12$eMqWD4GrJXgts4PbfZkM0O5UHbFbkpp.6kfZQsrz0yaPbVzRXDYje','email','ibrahima watt','2023-06-12'),(2,'Omar','Seye','omar@example.com','$2a$12$v/HZ0fzWDzWLCwqzs1liZerKYkKPBATha1.jt/BkRVmcGhXWQBjNy','0123456790','456 Avenue des Champs, Yeum','medecin','omar@example.com','$2a$12$v/HZ0fzWDzWLCwqzs1liZerKYkKPBATha1.jt/BkRVmcGhXWQBjNy','email','omar seye','2022-08-12'),(3,'Abou','Sow','abou@example.com','$2a$12$4FV6PJ09Uups/Ukb4hRh3.UBDTXoHuG3hrPZsoyZtGOBvblg2gvUS','0123456791','789 Boulevard Dakar','patient','abou@example.com','$2a$12$4FV6PJ09Uups/Ukb4hRh3.UBDTXoHuG3hrPZsoyZtGOBvblg2gvUS','email','abou sow','2020-08-22'),(4,'Dmzz','buhaha','dmzz@example.com','$2a$12$lZd45MVSHaFbdc7u6rHipeiUaGN98rkU8xmtMGmR.CIP47HAv306.','0123456792','101 Rue de la République buhaha','patient','dmzz@example.com','$2a$12$lZd45MVSHaFbdc7u6rHipeiUaGN98rkU8xmtMGmR.CIP47HAv306.','email','dmzz','2018-05-12'),(5,'patient','Watt','patientwatt@example.com','$2a$10$t6TQqHMRQIcQyGyMMQG11uIP4Ui8hnJJ5MxcqZLr5JGjO42ux7gba',NULL,'123 Rue Principale, Bambey','medecin',NULL,NULL,NULL,'patient watt','2005-05-12'),(6,'patient2','Watt','patient2watt@example.com','$2a$10$Bj4uQxisJAX2BCCvCRPr7OGHsUDiMBWzfYKwRWkVqwqwFecmRydSi','0123456789','123 Rue Principale, Bambey','medecin',NULL,NULL,NULL,'patient watt','2005-10-13'),(7,'zeum','patient','zeumPatient@example.com','$2a$10$s0PG3MayhUjgISPkGfAqCOWMy5ukWcuCv2yMCVTnm0L0Dy65gOd3a','0123456789','123 Rue Principale, Bambey','patient','zeumPatient@example.com','$2a$12$TTDyK3GSGQaKAT/E3ojsgO4QajuEx4a03t1OFMkT7JlKBQRYaGb26','email','zeum patient','2005-10-13'),(8,'issa','patient','issaPatient@example.com','$2a$10$xrTfeIHsGCClfby2WDDHTuw.asU4Gu/MjAsd18abkQPoD1P3/GC2W','0123456789','123 Rue Principale, Bambey','patient','issaPatient@example.com','$2a$12$TTDyK3GSGQaKAT/E3ojsgO4QajuEx4a03t1OFMkT7JlKBQRYaGb26','email','issa patient','2005-10-13'),(9,'issa','patient','issaPatient@example.com','$2a$10$UdT0FTI.Q.srU67zYrJGI.YhZS3XQbG8stTPNY4bWcSCDRFsAsQMS','0123456789','123 Rue Principale, Bambey','patient','issaPatient@example.com','$2a$12$TTDyK3GSGQaKAT/E3ojsgO4QajuEx4a03t1OFMkT7JlKBQRYaGb26','email','issa patient','2005-10-13'),(10,'sadikh','patient','sadikhPatient@example.com','$2a$10$FB9L7aRHbUhcgrBhf.qNsuhf6iwhK3GN67LiCtqeG4gucV42DsKNG','0123456789','Avenue Keur Massar, EcoBank','patient','sadikhPatient@example.com','$2a$12$xi/ZxczWQs8uYIXgRAtuSeOAdrSEuvh6HPEzIwmNiGZuEEJ8VP9MG','email','sadikh patient','2005-10-13'),(11,'Sow','Adams','','$2a$10$YSTfdq5fwY37xwIh5h8q6elHNfWHdJc90ePUhyPwwf4dVsfvGpNRC','adams','Sotrac, Keur Massar','patient','','adams','telephone','Sow Adams','2003-04-20'),(12,'Sow','Adams','','$2a$10$YM6NpFjCDBH2YI3Eqg8bq./u6xeF.711Z1Xjj8yYRpWDgLhhW3nom','adams','Sotrac, Keur Massar','patient','','adams','telephone','Sow Adams','2003-04-20'),(13,'Sow','cheikh','cheikh@example.com','$2a$10$Kig4wq1zR0qFm6r9Rthpf.8rLBBRoaMcZoKPIrl/GfR.MQvKbJEzi','cheikh','Darou rahmane, Keur Massar','patient','cheikh@example.com','cheikh','email','Sow cheikh','2001-01-01'),(14,'Moustapha','Diao','diao@example.com','$2a$10$KiX21StuGdaMouwwCaVOverXSBWwW.p.LuIw0Mb086g0Vu5BEw2zW','diao','Keur Massar, Sotrac','patient','diao@example.com','$2a$10$RXy3yP0prXVpaNhllndsUugZagbmZTW3rLjTq.UUd19qXVSouB/uS','email','moustapha diao','2025-01-01'),(15,'abou','sow','papegrand481@gmail.com','$2a$10$gSYhtZN/V1eiAAgqiIYukexcRZkZCmRNe2.02lXo0CnP3DyF4x87e','0000','keur massar','patient','papegrand481@gmail.com','$2a$10$6.Nc4mv416FWMPnXN6pJR.htheya8myH8o8eOG4HiZYGVo3Q21JBm','email','incoOrdi','1996-01-30'),(16,'patientZ','test1','test@example.com','$2a$10$rbHio7aJ4nCDl1D9LTX6zed9HAjWFhIiNHDbftvb4RzL7kFjoW2kW','0123456789','test, test','patient','test@example.com','$2a$10$TQ.LD//G.smWNFT1JepY9unWzNrzFx5UgwQ.R1o0rN4SL8TSL8MHm','email','test1','1999-01-01'),(17,'test','test5','test@example.com','$2a$10$KlU1scDARlpPfADeNDvgmOpYb7J4KS0gJgfKZM0wcKMqNCAh2dZnm','','testt','patient','test@example.com','$2a$10$kShGX8.RqmVVVbF8X8b2o.IrETs1ut6aBSIzIuGKrvlcxy68/sRk.','email','test5','1999-01-01'),(18,'sadikh','patient','sadikhPatient@example.com','$2a$10$kZ44P6wws9yyrETq57x/2eDaaKDIZGzfz41JtgQmWTyY2P8uyzYhC','0123456789','Avenue Keur Massar, EcoBank','patient','sadikhPatient@example.com','$2a$10$fuvdLRU6y3l4dch7qfc67ODqrrEMHPSLQXwB2Ooi80SYWLox4Als2','email','sadikh patient','2005-10-13');
/*!40000 ALTER TABLE `utilisateur` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-18  7:36:10
