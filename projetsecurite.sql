-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: db_projetsecurite
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `administrateur`
--

DROP TABLE IF EXISTS `administrateur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrateur` (
  `email` varchar(50) NOT NULL,
  `nomUser` varchar(40) NOT NULL,
  `mdp` varchar(50) NOT NULL,
  `idHopital` int NOT NULL,
  `datenaiss` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`email`),
  KEY `fk_admin_hopital` (`idHopital`),
  CONSTRAINT `fk_admin_hopital` FOREIGN KEY (`idHopital`) REFERENCES `hopital` (`idHopital`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrateur`
--

LOCK TABLES `administrateur` WRITE;
/*!40000 ALTER TABLE `administrateur` DISABLE KEYS */;
INSERT INTO `administrateur` VALUES ('admin2@gmail.com','admin2','projetssad2',2,'1999-11-12'),('admin3@gmail.com','admin3','projetssad3',3,'1989-02-01'),('admin4@gmail.com','admin4','projetssad4',4,'1985-05-10'),('admin@gmail.com','admin','projetssad',1,'2000-12-15');
/*!40000 ALTER TABLE `administrateur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consultation`
--

DROP TABLE IF EXISTS `consultation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consultation` (
  `idConsultation` int NOT NULL AUTO_INCREMENT,
  `taille` varchar(255) DEFAULT NULL,
  `poids` varchar(255) DEFAULT NULL,
  `tension` varchar(255) DEFAULT NULL,
  `observation` text,
  `diagnostic` text,
  `dateConsult` date DEFAULT NULL,
  `idPatient` int NOT NULL,
  PRIMARY KEY (`idConsultation`),
  KEY `idPatient_idx` (`idPatient`),
  CONSTRAINT `idPatient` FOREIGN KEY (`idPatient`) REFERENCES `patient` (`idPatient`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consultation`
--

LOCK TABLES `consultation` WRITE;
/*!40000 ALTER TABLE `consultation` DISABLE KEYS */;
INSERT INTO `consultation` VALUES (15,'zABQtz0XchvrD5gPHwaK9A==','YHpBVP4+gDz0PgiqdVR2jw==',NULL,'sz5hxXTidb5N7AGzEIRNsg==',NULL,'2021-12-29',14),(23,'8H9vwoJR9x6RKRTa5tDUvw==','9j2JUaj/7E7ZlIZCQhxpXA==',NULL,'BSRk21T7wPEk5APshSVmqczfRidDBtPsAKBtsW5t5AC6sUGfSCyk60OigjW4H0JD',NULL,'2021-12-29',15),(24,'zABQtz0XchvrD5gPHwaK9A==','YHpBVP4+gDz0PgiqdVR2jw==',NULL,'iyCkmF9sU9rra49cadfoMQ==',NULL,'2021-12-29',14),(25,'Xosak3kGhdaYokSZlpu6Rw==','JRhCBkjK47Qpkq+htl1SZQ==',NULL,'x9mF9gAN9TLG/bzSStlwx566w2tyocLHOzwMrdCMbjo=',NULL,'2021-12-29',40),(26,'ksuMAbRALwvQR6WOjTItrg==','yYa3xVX/9D2Wbkx55vIAVw==',NULL,'OWzZbtfmffj2vNBOK4gcg8MFohz431MwC3BOb8awgRc=',NULL,'2021-12-29',17),(27,'ZgrmPEO6K5HVP4fY0/GNDg==','S6dqxBiCIRiGiB2zqqKCwA==',NULL,'PRUEx5FjKFJ6S1r4UCkMyHDi/QexVajw8qGoQcrxLHY=',NULL,'2021-12-29',18),(28,'Ryjyy19j4js/i7rWiybnEw==','yYa3xVX/9D2Wbkx55vIAVw==',NULL,'iyCkmF9sU9rra49cadfoMQ==',NULL,'2021-12-29',19),(29,'MDgLYovXazCXrEUET7ht5w==','ocAfeOztwVvejDmICO52Fw==',NULL,'We9/7voKDn+72G1sfyKdDiwOxW6AcgYNJp+3uR/VgYEDbixbJTSzd2nIhiYSowoz',NULL,'2021-12-29',19),(30,'MDgLYovXazCXrEUET7ht5w==','+i+0CBpjRzRvc5CBozaSLA==',NULL,'W2t0XAfeHq+2KdQFmAlntSwOxW6AcgYNJp+3uR/VgYEDbixbJTSzd2nIhiYSowoz',NULL,'2021-12-29',42),(31,'MDgLYovXazCXrEUET7ht5w==','hkKJSDxa6kKRjJ0h+5EMtA==',NULL,'tZTs7iBRJvTljbjqMvDIec/hVvASYUFnYt0y1e5oUKo=',NULL,'2021-12-29',42),(32,NULL,NULL,'WMVaXluc6bNwNN3UOeKNYA==',NULL,'UdNPhfNhm7duoAxR1+ekeRYMHfHEssY+v9Js3YTJYIM=','2021-12-29',26),(33,NULL,NULL,'BrDACzXRcktCS4782LdKog==',NULL,'UdNPhfNhm7duoAxR1+ekeSKShbbuz/4ZKmyj+vq1vYmeusNrcqHCxzs8DK3QjG46','2021-12-29',28),(34,NULL,NULL,'3/xqJzh5dTwWUS1cUyEKXw==',NULL,'iyCkmF9sU9rra49cadfoMQ==','2021-12-29',31),(35,'6kr5AzodVOL/SyDHjElr9Q==','WI85FagCyv9L213xxzrjfA==',NULL,'PRUEx5FjKFJ6S1r4UCkMyHDi/QexVajw8qGoQcrxLHY=',NULL,'2021-12-29',21),(36,'1v8CZlt4bB3uMZsnRAFZQg==','oUFkaEkx3yY80IkM2JqbQA==',NULL,'iyCkmF9sU9rra49cadfoMQ==',NULL,'2021-12-29',25),(37,NULL,NULL,'OZrevHFw8/GhOLTU54lbxA==',NULL,'BDvYVQSABXVxkL/w3cQStcVpJYyW0Wc8G8sq9lCq39g=','2021-12-29',34),(38,NULL,NULL,'WMVaXluc6bNwNN3UOeKNYA==',NULL,'r7Zo2XFa9iXD4053tuxT0K2o8ozvR5Eb+p93wPXq+Ak=','2021-12-29',37);
/*!40000 ALTER TABLE `consultation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consultationantecedents`
--

DROP TABLE IF EXISTS `consultationantecedents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consultationantecedents` (
  `idconsultantecedent` int NOT NULL AUTO_INCREMENT,
  `idConsultation` int DEFAULT NULL,
  `antecedent` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idconsultantecedent`),
  KEY `idConsultation_idx` (`idConsultation`),
  CONSTRAINT `idConsultation` FOREIGN KEY (`idConsultation`) REFERENCES `consultation` (`idConsultation`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consultationantecedents`
--

LOCK TABLES `consultationantecedents` WRITE;
/*!40000 ALTER TABLE `consultationantecedents` DISABLE KEYS */;
INSERT INTO `consultationantecedents` VALUES (12,15,'rJo3NjzGip79wVIxcp6NACqEhauCkcnnpkcJ1vNshpItpt+1ZJYtk+5ioxy9iUwO9CRwJoHqkBNd\r\nAQcO/yUCdA=='),(13,15,'FU9OkztyBb05LPbfwXeApIU4zmIOpxuHcvbUKsl192ooZ8RpelVMcZkYZUsfaASz'),(42,23,'Y/xq5EkeiNZu86h4fnOy6g=='),(43,23,'qA6ljHSdoKFYY6dr+cB6PbGAb9Ol54RSNyJRK8azMhA='),(44,24,'FU9OkztyBb05LPbfwXeApIU4zmIOpxuHcvbUKsl192qT7nBTg8X8YsnhrCmfR8Z+'),(45,24,'fgNYEBhR/0qn81YmxlyexyqEhauCkcnnpkcJ1vNshpItpt+1ZJYtk+5ioxy9iUwO9CRwJoHqkBNd\r\nAQcO/yUCdA=='),(46,27,'fmdbBql0Dyy8meI4c7JRRg=='),(47,27,'qRWqzSCq4A3o8O3Z6CK7Tw=='),(48,28,'XE7cLaqXREQJ8YwzDP4l5/wjuxSCh5NmCpVC/yNZesg1bKJGim/KN1kUPkz+33r1'),(49,28,'HRfnbOMdP1+zXWOXtv8GHg=='),(50,29,'+9G3vrzvNEMnI1OfoHhlC40peVk8NVs6gnV9xI/cN/cLx1gPg+J+2CS1t9WbA3gC'),(51,32,'fgNYEBhR/0qn81YmxlyexyqEhauCkcnnpkcJ1vNshpItpt+1ZJYtk+5ioxy9iUwO0HMXJi991WbO\r\nWrjiPRJvFg=='),(52,32,'ssN+WMX+ChBnnkNdSVFMJ15/INOgbzrnR7VI4g9l6Gc='),(53,32,'dM8SjpVrx8OTSGf08R2Nwg=='),(54,35,'wHnPJG01kwPCNrdUCueChA=='),(55,35,'ouF4gJkEOqcAy8oR7eRrx2XHiVTUT2WgnkgtArZ08Do='),(56,35,'LyeBI+91fh7cW1LXOuPRkw=='),(58,38,'E9hzfnLwB/QDIFy9asAlhsFYkVS2IxiQsAsBcKN39eY=');
/*!40000 ALTER TABLE `consultationantecedents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consultationbilans`
--

DROP TABLE IF EXISTS `consultationbilans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consultationbilans` (
  `idconsultationbilans` int NOT NULL AUTO_INCREMENT,
  `idConsultation` int NOT NULL,
  `analyse` varchar(255) DEFAULT NULL,
  `resultat` varchar(255) DEFAULT NULL,
  `commentaire` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idconsultationbilans`),
  KEY `idconsultation_idx` (`idConsultation`),
  CONSTRAINT `idConsultationBilan` FOREIGN KEY (`idConsultation`) REFERENCES `consultation` (`idConsultation`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consultationbilans`
--

LOCK TABLES `consultationbilans` WRITE;
/*!40000 ALTER TABLE `consultationbilans` DISABLE KEYS */;
INSERT INTO `consultationbilans` VALUES (7,15,'scKkn9GaI8NkR1Qf0UpJKg==','0fxKYqCu+RMQe4L4BWj16NmB13vsZtI4qqL31Mek3N8=',NULL),(8,15,'Mr4EZM+RlTKa2WuJX82+eVxhdF3+njb4tRPdyEK4Xl0=','0fxKYqCu+RMQe4L4BWj16IOh+x06GVcWqil1M6eqO4yIyiI+6MAPXzD1wWIDV+fo',NULL),(9,15,'40OuoVovjuoz79/VQgYtvb5lANFIOEqke05oaUiSLaY=','0fxKYqCu+RMQe4L4BWj16ON3aLr6+oUmDiFqVq5tchHJL6B61HZS0ek4OiTBM+9d',NULL),(29,23,'5c/dRgl2viGY/wsnOclgTUj+I0eDRSzECK0Y/HSnvoVx5EQ4LLvu2tehhCa9PKsE','0fxKYqCu+RMQe4L4BWj16OLlmt+Lx3zwcilS9kqOLp04Ru39upUcUHh7pnG3rJD2',NULL),(30,24,'nkk78p7bFVLQ8iFDR/K4b2sivYUwTIuIkBtJ0sheZOOeusNrcqHCxzs8DK3QjG46','0fxKYqCu+RMQe4L4BWj16ATiYJMZdoPHZOJP1kXmbTaCwnlYxOPyzReP5SzqwlP2H9bdUf4I9wB+\r\nkvtNIpgGlQ==',NULL),(31,25,'+GnjAO44cPuRUsbfmAJsZxWeKZsh4E800JPAXhB/OrM=','0fxKYqCu+RMQe4L4BWj16CQi3FS9U2gInqUp7r0fB5sOYLpKyP/GPDya0YDztS52',NULL),(32,25,'4C6b+l46x10TmN9vgDISEQ==','0fxKYqCu+RMQe4L4BWj16LeVigkzT+PswWCsBd1j7rQ=',NULL),(33,25,'obRoWommOk3cbiRy5ZaAol/bcC8ilR+7ZwHpiAIz56o=','0fxKYqCu+RMQe4L4BWj16NZEZhE4zUgcHGrrPLCMbGRGB7z4GmFKFtJE7pBIsMiJ',NULL),(34,26,'+WKGhlmdGyYRFL8wSi0cSw==','0fxKYqCu+RMQe4L4BWj16AjcnrCxGZtTe8z2yRRByzA=',NULL),(35,28,'+Ax0LjRa7ZBwCMn2HB8ptw==','0fxKYqCu+RMQe4L4BWj16BfwyRGdrDzusm4Ky1EWYCA=',NULL),(36,29,'cMg6k4RDLtGoIYn5GoTjog==','0fxKYqCu+RMQe4L4BWj16IulQU4GnTYJPLGIkvGqOHY=',NULL),(37,29,'qyTg6kMlDqab7PKpBKFhgQ==','0fxKYqCu+RMQe4L4BWj16KH5W9TPWIbba4h9ZXzjBf8=',NULL),(38,30,'qyTg6kMlDqab7PKpBKFhgQ==','0fxKYqCu+RMQe4L4BWj16KH5W9TPWIbba4h9ZXzjBf8=',NULL),(39,32,'buVkMCin8m9dLq9Bdh5figsXoCkGNRGIrBfiBJzB+7Y=','JMFcAYBcE6lP/c0V0bHaIqrbt+qHtBjW4ZBXQ9HkG/s=','8jbiJxg9W12n3gts8iC/tadDrR83LQcMw4ey5NjyJhY3Bj7MPwar/SxLpA/8mJU8'),(40,32,'JNnoyS/A7Xw0McPaaYtFUQ==','UGZE/l10aDXc7MWp8v4SyL4e94mxActIzq2kBfwsVfs=','1d9vsjAvJ0hUPrk4veAwO+X/sEcx/0RFuunum62CyxA='),(41,33,'4MOHi49AHX2VVAv1uOVBzg==','d6lCbJwD2UBUxMtHgdtsZjEYB6HVDzpDipYqVN8cku8=','n+8295En16ATrgsGluixPTuqmX536gj1upeJbAw32iw='),(42,34,'dEwDW9ioBQPcEBy5nxTBlg==','grd9VJL2RP7cjLQ4LzKJptLpWU2ytNTU1fX+tUANDCg=','TmZjwSad3d0R9++PPxye91t9+njjs7LMCTC12tPhGQ8='),(43,34,'sUEjRKZwK0jEhHq+uBZIBw==','uG1MfQ3z/Zq4OrRE/UMHEvwzUt94T70573QaNdT++RQ=','+E69RH6I5++1pkzQSYvhdKAbHgdUbOXZwGS9qkyXdt0='),(44,36,'djfgcuWYBYvyeL/NyfoJIg==','0fxKYqCu+RMQe4L4BWj16EXNgMsXMqlI8iRtuq4JFYk=',NULL),(45,36,'MetL5jP2uKls4E+c6m3LXg==','0fxKYqCu+RMQe4L4BWj16KlhxXwvXpVN6ivfVwVIbgE=',NULL),(46,37,'5c/dRgl2viGY/wsnOclgTXuLosk3VW3Gu5HSFpbH22Q=','yJme6Z7+y/hGRuEG42IYBzc2j202ahjxnU92issMv/8AwjkIjpPOm8hBVegcZFKF','VxoNnybD0qsnOp5iFOewbFDzJhNWLQ7Hao7C1HyH9zc='),(47,38,'3yBEk05VLYxyLTWI98A55w==','D1FfU+KMkEAvj8XodF7tiLN337igkePy4yDbKk6Mdoc=','D5ssPxfo1HNHDgNn6XWMZQewykQlRyf5XafxgW0zgZk=');
/*!40000 ALTER TABLE `consultationbilans` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hopital`
--

DROP TABLE IF EXISTS `hopital`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hopital` (
  `idHopital` int NOT NULL AUTO_INCREMENT,
  `nomHopital` varchar(60) NOT NULL,
  `wilaya` int NOT NULL,
  PRIMARY KEY (`idHopital`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hopital`
--

LOCK TABLES `hopital` WRITE;
/*!40000 ALTER TABLE `hopital` DISABLE KEYS */;
INSERT INTO `hopital` VALUES (1,'Mustapha Pacha',16),(2,'CNMS',32),(3,'Khelil Amrane',6),(4,'Lakhdar Bouchama',42);
/*!40000 ALTER TABLE `hopital` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medecin`
--

DROP TABLE IF EXISTS `medecin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medecin` (
  `idMedecin` int NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `nomUser` varchar(40) NOT NULL,
  `mdp` varchar(255) NOT NULL,
  `datenaiss` date DEFAULT NULL,
  `idService` int NOT NULL,
  `idHopital` int NOT NULL,
  PRIMARY KEY (`idMedecin`),
  KEY `fk_medecin_service` (`idService`),
  KEY `fk_medecin_hopital` (`idHopital`),
  CONSTRAINT `fk_medecin_hopital` FOREIGN KEY (`idHopital`) REFERENCES `hopital` (`idHopital`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_medecin_service` FOREIGN KEY (`idService`) REFERENCES `service` (`idService`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medecin`
--

LOCK TABLES `medecin` WRITE;
/*!40000 ALTER TABLE `medecin` DISABLE KEYS */;
INSERT INTO `medecin` VALUES (1,'okba.amara@gmail.com','Okba','Amara','okba.amara','SQvOT+Qg1ZU0lX6UmJxl4w==','1964-12-09',1,1),(2,'malika.benhalima@gmail.com','Malika','Benhalima','malika.benhalima','azUDeRIjiF/0PhIudli3bA==','1960-12-08',3,1),(3,'nadir.mohamed@gmail.com','Mohamed','Nadir','nadir.mohamed','d/kSofAsracts96ae7THOQ==','1952-02-18',2,2),(4,'djoudi.nawel@gmail.com','Nawel','Djoudi','djoudi.nawel','eDbxg745yv+n0SDEhkHLig==','1970-02-16',4,2),(5,'bouhacein.fares@gmail.com','Fares','Bouhacein','fares.bouhacein','iEv3So0Mz2EzxQ1+qIS/Qw==','1972-12-17',7,3),(6,'meriem.tazir@gmail.com','Meriem','Tazir','meriem.tazir','1/KBzsAsaAY5NZz6jt1c9A==','1964-12-24',8,3),(7,'yamoun.rachid@gmail.com','Rachid','Yamoun','yamoun.rachid','LP8FUtifC/N9XJTE/tMp+g==','1960-12-10',5,4),(8,'boudjemaa.abdelkader@gmail.com','Abdelkader','Boudjemaa','boudjemaa.abdelkader','Q3LmqL/5f7ia9yKn4Pj4gQ==','1959-12-31',6,4),(9,'dahmani.karima@gmail.com','Karima','Dahmani','dahmani.karima','SQvOT+Qg1ZU0lX6UmJxl4w==','1980-01-16',1,1);
/*!40000 ALTER TABLE `medecin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ministere`
--

DROP TABLE IF EXISTS `ministere`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ministere` (
  `email` varchar(45) NOT NULL,
  `mdp` varchar(45) DEFAULT NULL,
  `datenaiss` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ministere`
--

LOCK TABLES `ministere` WRITE;
/*!40000 ALTER TABLE `ministere` DISABLE KEYS */;
INSERT INTO `ministere` VALUES ('ministere1@gmail.com','000','2000-01-01');
/*!40000 ALTER TABLE `ministere` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient` (
  `idPatient` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `tel1` varchar(25) NOT NULL,
  `tel2` varchar(25) DEFAULT NULL,
  `dateN` varchar(25) NOT NULL,
  `grpsanguin` enum('O-POS','O-NEG','A-POS','A-NEG','B-POS','B-NEG','AB-POS','AB-NEG') NOT NULL,
  `genre` enum('Homme','Femme') NOT NULL,
  `idHopital` int NOT NULL,
  `idService` int NOT NULL,
  `idMedecin` int NOT NULL,
  PRIMARY KEY (`idPatient`),
  KEY `fk_pat_hop` (`idHopital`),
  KEY `fk_pat_service` (`idService`),
  KEY `fk_pat_medecin` (`idMedecin`),
  CONSTRAINT `fk_pat_hop` FOREIGN KEY (`idHopital`) REFERENCES `hopital` (`idHopital`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_pat_medecin` FOREIGN KEY (`idMedecin`) REFERENCES `medecin` (`idMedecin`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_pat_service` FOREIGN KEY (`idService`) REFERENCES `service` (`idService`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (14,'Abbad','Farid',NULL,'0557896525','0775896582','1998-12-26','O-POS','Homme',1,1,1),(15,'Abizar','Kamel',NULL,'0778896525','0556892512','1999-02-26','O-POS','Homme',1,1,1),(16,'Arab','Redouane',NULL,'0667589652','0552568957','1999-02-28','A-NEG','Homme',1,1,1),(17,'Cherif','Lina',NULL,'0557898562','0557891122','2000-12-21','O-NEG','Femme',1,3,2),(18,'Medjadi','Nassima',NULL,'0557521841','0771202020','2000-01-11','AB-POS','Femme',1,3,2),(19,'Briane','Wacim',NULL,'0778958581','0545214521','2000-05-13','O-NEG','Homme',1,3,2),(20,'Asses','Meriem',NULL,'0771111112','0555555558','1998-06-21','O-NEG','Femme',3,7,5),(21,'Inourar','Hicham',NULL,'0668985869','0525635414','1998-02-12','A-POS','Homme',3,7,5),(22,'Marghuem','Salah Eddine',NULL,'0667898582','0554142563','1996-06-05','O-POS','Homme',3,7,5),(23,'Tamoum','Amira',NULL,'0554547896','0621458965','1975-12-11','O-POS','Femme',3,8,6),(24,'Zid','Nesrine',NULL,'0778965412','0789654123','1980-10-03','B-POS','Femme',3,8,6),(25,'Zellagui','Asma',NULL,'0557889654','0589654123','1965-12-25','O-POS','Femme',3,8,6),(26,'Abid','Imene','abd.imene@gmail.com','0773488652',NULL,'1987-12-12','O-POS','Femme',2,2,3),(27,'Atrouche','Hind','atrouche.hind@gmail.com','0557412365',NULL,'1996-10-02','O-POS','Femme',2,2,3),(28,'amine','ishak','amine.ishak@gmail.com','0667896589',NULL,'2000-05-05','O-POS','Homme',2,2,3),(31,'Zenchi','Sara','zenchi.sara@gmail.com','0778879858',NULL,'1970-10-11','A-POS','Femme',2,4,4),(32,'Bekkar','Maroua','bekkar.maroua@gmail.com','0554785478',NULL,'1945-10-12','O-NEG','Femme',2,4,4),(33,'Riche','Ikhlas','riche.ikhlas@gmail.com','0778985896',NULL,'1989-01-03','O-POS','Femme',2,4,4),(34,'Zenir','Yacine','zenir.yacine@gmail.com','0557789685',NULL,'2006-12-12','AB-POS','Homme',4,5,7),(35,'Aouchiche','Sarah','aouchiche.sarah@gmail.com','0778789885',NULL,'2005-01-01','O-POS','Femme',4,5,7),(36,'Abbas','Mounir','abas.mounir@gmail.com','0557896589',NULL,'2010-10-03','O-POS','Homme',4,5,7),(37,'Bezia','Manel','bezia.manel@gmail.com','0778987787',NULL,'1975-12-10','O-POS','Femme',4,6,8),(38,'Melouk','Selma','melouk.selma@gmail.com','0553212321',NULL,'1980-06-09','O-NEG','Femme',4,6,8),(39,'Chaib','Nazim','chakib.nazim@gmail.com','0578589858',NULL,'1964-05-02','O-NEG','Homme',4,6,8),(40,'Ladlani','Lydia',NULL,'0778788747','0757874785','1990-12-16','O-POS','Femme',1,1,1),(41,'Akil','Amine','akil.amine@gmail.com','0775245787',NULL,'2021-12-16','O-NEG','Homme',4,5,7),(42,'Mohammed','Amina',NULL,'0685425369','0574123698','2001-05-20','O-POS','Femme',1,1,9);
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `secretaire`
--

DROP TABLE IF EXISTS `secretaire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `secretaire` (
  `email` varchar(50) NOT NULL,
  `nomUser` varchar(40) NOT NULL,
  `mdp` varchar(255) NOT NULL,
  `datenaiss` date NOT NULL,
  `idService` int NOT NULL,
  `idHopital` int NOT NULL,
  PRIMARY KEY (`email`),
  KEY `fk_s_service` (`idService`),
  KEY `fk_s_hopital` (`idHopital`),
  CONSTRAINT `fk_s_hopital` FOREIGN KEY (`idHopital`) REFERENCES `hopital` (`idHopital`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_s_service` FOREIGN KEY (`idService`) REFERENCES `service` (`idService`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `secretaire`
--

LOCK TABLES `secretaire` WRITE;
/*!40000 ALTER TABLE `secretaire` DISABLE KEYS */;
INSERT INTO `secretaire` VALUES ('aa','aa','[B@2d833b8b','2021-12-28',1,1),('amel.foudil@gmail.com','amel.foudil','[B@7d9846bb','2001-12-12',5,4),('asma.chemem@gmail.com','asma.chemem','[B@14f26518','2000-12-18',6,4),('benabedmaissa23@gmail.com','maissa.benabed','[B@470f25a5','2000-12-22',2,2),('boukersi.yasmine@gmail.com','boukersi.yasmine','[B@1723c753','2000-12-17',8,3),('feriel.abdelbaki23@gmail.com','feriel.abdelbaki','[B@26630c32','2000-12-23',1,1),('kherroubi.kenza@gmail.com','kherroubi.kenza','[B@22c49bf0','2000-12-10',7,3),('racha.said25@gmail.com','racha.said','[B@2a375c7d','2000-11-09',3,1),('yacef.yasmina@gmail.com','yasmina.yacef','[B@7f72451c','2000-12-09',4,2);
/*!40000 ALTER TABLE `secretaire` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service` (
  `idService` int NOT NULL AUTO_INCREMENT,
  `nomService` varchar(40) NOT NULL,
  `idHopital` int NOT NULL,
  PRIMARY KEY (`idService`),
  KEY `fk_service_hopital` (`idHopital`),
  CONSTRAINT `fk_service_hopital` FOREIGN KEY (`idHopital`) REFERENCES `hopital` (`idHopital`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES (1,'Cardiologie',1),(2,'Cardiologie',2),(3,'Immunologie',1),(4,'Chirurgie Cardio-Vasculaire',2),(5,'Pédiatrie',4),(6,'Orthopédie',4),(7,'Cancérologie',3),(8,'Neurochirurgie',3);
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-04 20:37:24
