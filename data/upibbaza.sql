-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: osa
-- ------------------------------------------------------
-- Server version	8.0.22
CREATE DATABASE osa;
USE osa;
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
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrator` (
  `id_korisnika` bigint NOT NULL,
  `email_korisnika` varchar(255) NOT NULL,
  `ime_korisnika` varchar(255) NOT NULL,
  `lozinka_korisnika` varchar(255) NOT NULL,
  `prezime_korisnika` varchar(255) NOT NULL,
  `vrsta_administratora` varchar(255) DEFAULT NULL,
  `id_klinike` bigint DEFAULT NULL,
  PRIMARY KEY (`id_korisnika`),
  UNIQUE KEY `UK_mewh7e4bafcsa7yhse1nqqbps` (`id_korisnika`),
  UNIQUE KEY `UK_t932b11hvi9fjxioeilcaiihh` (`email_korisnika`),
  KEY `FKjw0wg1hyqajkanw31s3minnk5` (`id_klinike`),
  CONSTRAINT `FKjw0wg1hyqajkanw31s3minnk5` FOREIGN KEY (`id_klinike`) REFERENCES `klinika` (`id_klinike`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (58,'admin1@gmail.com','admin1','$2a$10$pmcxDHJ/Ndu0yuoGG4lWH.Fd4/pED4utGz.v3c7N7P98JOvSodZZC','admin1','KLINICKI',2),(106,'admin2@gmail.com','admin2','$2a$10$pmcxDHJ/Ndu0yuoGG4lWH.Fd4/pED4utGz.v3c7N7P98JOvSodZZC','admin2','KLINICKOG_CENTRA',NULL),(142,'admin3@gmail.com','admin3','$2a$10$pmcxDHJ/Ndu0yuoGG4lWH.Fd4/pED4utGz.v3c7N7P98JOvSodZZC','adminic3','KLINICKI',3);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authority`
--

DROP TABLE IF EXISTS `authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authority` (
  `id_authority` bigint NOT NULL AUTO_INCREMENT,
  `ime_authority` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_authority`)
) ENGINE=InnoDB AUTO_INCREMENT=143 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authority`
--

LOCK TABLES `authority` WRITE;
/*!40000 ALTER TABLE `authority` DISABLE KEYS */;
INSERT INTO `authority` VALUES (1,'ROLE_ADMINISTRATOR'),(2,'ROLE_LEKAR'),(3,'ROLE_MEDICINSKA_SESTRA'),(4,'ROLE_PACIJENT'),(5,'ROLE_KLINICKI_ADMINISTRATOR'),(139,'2'),(140,'2'),(142,'5');
/*!40000 ALTER TABLE `authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cenovnik`
--

DROP TABLE IF EXISTS `cenovnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cenovnik` (
  `id_cenovnika` bigint NOT NULL AUTO_INCREMENT,
  `cena` double NOT NULL,
  `naziv` varchar(255) NOT NULL,
  `id_klinike` bigint NOT NULL,
  PRIMARY KEY (`id_cenovnika`),
  KEY `FKj6kkdi2ktsgnjlya7lgcar2q5` (`id_klinike`),
  CONSTRAINT `FKj6kkdi2ktsgnjlya7lgcar2q5` FOREIGN KEY (`id_klinike`) REFERENCES `klinika` (`id_klinike`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cenovnik`
--

LOCK TABLES `cenovnik` WRITE;
/*!40000 ALTER TABLE `cenovnik` DISABLE KEYS */;
INSERT INTO `cenovnik` VALUES (1,11205,'Lobotomija',2),(2,213321,'Lol',2),(3,420,'Microdosing',2),(4,1,'Usluga1',3),(5,2,'Usluga2',3);
/*!40000 ALTER TABLE `cenovnik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (279);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `klinika`
--

DROP TABLE IF EXISTS `klinika`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `klinika` (
  `id_klinike` bigint NOT NULL AUTO_INCREMENT,
  `adresa` varchar(255) NOT NULL,
  `naziv` varchar(255) NOT NULL,
  `opis` varchar(255) NOT NULL,
  PRIMARY KEY (`id_klinike`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `klinika`
--

LOCK TABLES `klinika` WRITE;
/*!40000 ALTER TABLE `klinika` DISABLE KEYS */;
INSERT INTO `klinika` VALUES (2,'Adresa Klinike 2','Klinika 2','Opis Klinike 2'),(3,'Adresa Klinike 3','Klinika 3','Opis Klinike 3');
/*!40000 ALTER TABLE `klinika` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `korisnici_authority`
--

DROP TABLE IF EXISTS `korisnici_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `korisnici_authority` (
  `id_korisnika` bigint NOT NULL,
  `id_authority` bigint NOT NULL,
  KEY `FK42ofvy7hkpso4pkw9w21b2rnw` (`id_authority`),
  CONSTRAINT `FK42ofvy7hkpso4pkw9w21b2rnw` FOREIGN KEY (`id_authority`) REFERENCES `authority` (`id_authority`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnici_authority`
--

LOCK TABLES `korisnici_authority` WRITE;
/*!40000 ALTER TABLE `korisnici_authority` DISABLE KEYS */;
INSERT INTO `korisnici_authority` VALUES (20,4),(21,4),(2,2),(1,2),(89,3),(95,4),(58,5),(106,1),(141,3),(142,5),(153,3),(214,4);
/*!40000 ALTER TABLE `korisnici_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lekar`
--

DROP TABLE IF EXISTS `lekar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lekar` (
  `id_korisnika` bigint NOT NULL,
  `email_korisnika` varchar(255) NOT NULL,
  `ime_korisnika` varchar(255) NOT NULL,
  `lozinka_korisnika` varchar(255) NOT NULL,
  `prezime_korisnika` varchar(255) NOT NULL,
  `id_klinike` bigint DEFAULT NULL,
  PRIMARY KEY (`id_korisnika`),
  UNIQUE KEY `UK_p6mk8s58re1xdq0lvx58hs9m7` (`id_korisnika`),
  UNIQUE KEY `UK_hvms9fxktpk3a60ylosaqmawb` (`email_korisnika`),
  KEY `FK_9nwmi4gceyrhaawx55hfktrqs` (`id_klinike`),
  CONSTRAINT `FK_9nwmi4gceyrhaawx55hfktrqs` FOREIGN KEY (`id_klinike`) REFERENCES `klinika` (`id_klinike`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lekar`
--

LOCK TABLES `lekar` WRITE;
/*!40000 ALTER TABLE `lekar` DISABLE KEYS */;
INSERT INTO `lekar` VALUES (1,'wlqmpek1996@gmail.com','doktor1','$2a$09$HnfpXtMclcOtJ4FEEGmAh.e2JAzl1/ge1Fyt1LDNKe5ZAczu7Qv2O','doktoric1',2),(2,'doktor2@gmail.com','doktor2','$2a$09$ZAnGFJeM4Scuq/gbfE9JIuMFt.Un3ShTIHvOWoBgKhDDWwtZgpURC','doktoric2',2),(139,'doktor3@gmail.com','doktor3','$09$HnfpXtMclcOtJ4FEEGmAh.e2JAzl1/ge1Fyt1LDNKe5ZAczu7Qv2O','doktoric3',3),(140,'doktor4@gmail.com','doktor4','$09$HnfpXtMclcOtJ4FEEGmAh.e2JAzl1/ge1Fyt1LDNKe5ZAczu7Qv2O','doktoric4',3);
/*!40000 ALTER TABLE `lekar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicinska_sestra`
--

DROP TABLE IF EXISTS `medicinska_sestra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medicinska_sestra` (
  `id_korisnika` bigint NOT NULL,
  `email_korisnika` varchar(255) NOT NULL,
  `ime_korisnika` varchar(255) NOT NULL,
  `lozinka_korisnika` varchar(255) NOT NULL,
  `prezime_korisnika` varchar(255) NOT NULL,
  `id_klinike` bigint DEFAULT NULL,
  PRIMARY KEY (`id_korisnika`),
  UNIQUE KEY `UK_cwx161erwwu2ji5xvid1ce5a3` (`id_korisnika`),
  UNIQUE KEY `UK_s0e9ajemisplhe5q6iii7m0le` (`email_korisnika`),
  KEY `FK_s9bxh1g06u7jjrhor48dycdty` (`id_klinike`),
  CONSTRAINT `FK_s9bxh1g06u7jjrhor48dycdty` FOREIGN KEY (`id_klinike`) REFERENCES `klinika` (`id_klinike`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicinska_sestra`
--

LOCK TABLES `medicinska_sestra` WRITE;
/*!40000 ALTER TABLE `medicinska_sestra` DISABLE KEYS */;
INSERT INTO `medicinska_sestra` VALUES (89,'isakboss07@gmail.com','Isak','$2a$10$27NBLXPn5Kjb62/kAwCseuj5Ck5Tq.EDCohX2oTsZXA/i98LYF3cy','Isakovic',2),(136,'sestra1@gmail.com','Sestra1','$2a$10$27NBLXPn5Kjb62/kAwCseuj5Ck5Tq.EDCohX2oTsZXA/i98LYF3cy','Sestra1',2),(141,'sestra3@gmail.com','Sestra3','$2a$10$27NBLXPn5Kjb62/kAwCseuj5Ck5Tq.EDCohX2oTsZXA/i98LYF3cy','Sestric3',3),(143,'sestra4@gmail.com','Sestra4','$2a$10$27NBLXPn5Kjb62/kAwCseuj5Ck5Tq.EDCohX2oTsZXA/i98LYF3cy','Sestric4',3);
/*!40000 ALTER TABLE `medicinska_sestra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ocene_doktora`
--

DROP TABLE IF EXISTS `ocene_doktora`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ocene_doktora` (
  `id_ocene_doktora` bigint NOT NULL AUTO_INCREMENT,
  `ocena` int DEFAULT NULL,
  `id_lekara` bigint DEFAULT NULL,
  `id_pacijenta` bigint DEFAULT NULL,
  PRIMARY KEY (`id_ocene_doktora`),
  KEY `FKo7f185rq2xr0x5gvbk0btfxvs` (`id_lekara`),
  KEY `FK3ca7rgcvggld6v0d0ub3p4gms` (`id_pacijenta`),
  CONSTRAINT `FK3ca7rgcvggld6v0d0ub3p4gms` FOREIGN KEY (`id_pacijenta`) REFERENCES `pacijent` (`id_korisnika`),
  CONSTRAINT `FKo7f185rq2xr0x5gvbk0btfxvs` FOREIGN KEY (`id_lekara`) REFERENCES `lekar` (`id_korisnika`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ocene_doktora`
--

LOCK TABLES `ocene_doktora` WRITE;
/*!40000 ALTER TABLE `ocene_doktora` DISABLE KEYS */;
/*!40000 ALTER TABLE `ocene_doktora` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ocene_klinike`
--

DROP TABLE IF EXISTS `ocene_klinike`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ocene_klinike` (
  `id_ocene_klinike` bigint NOT NULL AUTO_INCREMENT,
  `ocena` int DEFAULT NULL,
  `id_klinike` bigint DEFAULT NULL,
  `id_korisnika` bigint DEFAULT NULL,
  PRIMARY KEY (`id_ocene_klinike`),
  KEY `FKi3xl7m3gqsr53clybayunf6pd` (`id_klinike`),
  KEY `FKg0xw9pa9nisv496xyha8dj14d` (`id_korisnika`),
  CONSTRAINT `FKg0xw9pa9nisv496xyha8dj14d` FOREIGN KEY (`id_korisnika`) REFERENCES `pacijent` (`id_korisnika`),
  CONSTRAINT `FKi3xl7m3gqsr53clybayunf6pd` FOREIGN KEY (`id_klinike`) REFERENCES `klinika` (`id_klinike`)
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ocene_klinike`
--

LOCK TABLES `ocene_klinike` WRITE;
/*!40000 ALTER TABLE `ocene_klinike` DISABLE KEYS */;
INSERT INTO `ocene_klinike` VALUES (138,5,2,21);
/*!40000 ALTER TABLE `ocene_klinike` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pacijent`
--

DROP TABLE IF EXISTS `pacijent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pacijent` (
  `id_korisnika` bigint NOT NULL,
  `email_korisnika` varchar(255) NOT NULL,
  `ime_korisnika` varchar(255) NOT NULL,
  `lozinka_korisnika` varchar(255) NOT NULL,
  `prezime_korisnika` varchar(255) NOT NULL,
  `jbzo` varchar(255) NOT NULL,
  `status_korisnika` int NOT NULL,
  `id_zdravstvenog_kartona` bigint DEFAULT NULL,
  PRIMARY KEY (`id_korisnika`),
  UNIQUE KEY `UK_j9g66xmbqueioc9damiwo2k1y` (`id_korisnika`),
  UNIQUE KEY `UK_bjnuh006x6yq6mja4omsq6n` (`email_korisnika`),
  UNIQUE KEY `UK_4w7snss5jdjiny7ga0l1bbtpo` (`jbzo`),
  KEY `FKrlb4u632ttchbcgp3tmn4o5p7` (`id_zdravstvenog_kartona`),
  CONSTRAINT `FKrlb4u632ttchbcgp3tmn4o5p7` FOREIGN KEY (`id_zdravstvenog_kartona`) REFERENCES `zdravstveni_karton` (`id_zdravstvenog_kartona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pacijent`
--

LOCK TABLES `pacijent` WRITE;
/*!40000 ALTER TABLE `pacijent` DISABLE KEYS */;
INSERT INTO `pacijent` VALUES (20,'milosvlkuk@hotmail.com','Davor','$2a$10$fURYbf/ZtkEsCIf9ABh.U.W4hZEVjQxh1aWYq0PaSPTiYtoaPXN5S','Stefanovic','fru3TARW0lOskLF18i5KFECaESySiQMS2NZSCAHweAZo5nw8g1vTJDjAoP3SqZXqQKSz7lJEkzKpLnNOfSeHxByKmRiPKFrxBZm7IuizmJpO2+nzFt1JWEaldcVc6BERgudtOO52rZ4YNRA3ydnJv9RZu3ZFTUBx4B6aDaWV91E=',1,21),(21,'wlqmpek@hotmail.com','Isak Vujovic','$2a$10$ZOtAWqcwc3RdAy72sBPvhuq0e22N6ShdkP6yYaiK6HKDKclgdBnya','Nikolic','K6E9Zc1oCOTmbmXc/0grLinXBo3Zn5n76TlJMA093TYRMB6F9fge3i4kWQ0ZCuSyfBb6VzBw7al/zeYfjuS+lncQ3412mmJxZr2QepQgVj30GhGD13YIuS0OSwSD1kdrTlrKsrJpodEX9lqsoWLR9wzER7VtvvVk4Fx3lBnj3ao=',1,22),(75,'email@gmail.com','Natalija','$2a$10$nOYK.d/lDQkihHlNrTtOR.2LYKAxHkX6ZIlEI2x0BiEO05zakLbEm','Bajkic','JBZjoi6kwDnaX2ZWM4nxKhkl3FD0XKHBYbDts0lG48dbFN6MnhKxINfXPc9x0kiZT+7P1RPUH09NL+bd2SxlilthLAmkDfhz+96oss8WzjVzMYwSdATFZeWEi3dkqvbx4o3pgLOHisiCXG+lZ3izWgKmuhL4qEW+C8CRe9EMeI0=',1,23),(76,'mail@gmail.com','adsdad','$2a$10$GwQlDu5E3VBnuhEhdQAMluX5Qzteh8mwJ2MQ0LYP9h2.NLvS5VU2a','sdadsds','MwuQgHDitCFYrxf9EmmEP80zzqcPT2InGJdqQ+uOV3KSNTNXsChDt4NvU4hWQANuQaaA2hYI8XJ5rsUVp4JE5Eow7CQ5VkJcNdXsXQ1uGdJIFsoN6ZNqZ7e3xP8tLG2OEs+d0aQexzl2pskbic6PzalL8g80uWn3yeJGo7Qk1HU=',1,24),(95,'ADSDSA@VSDDC.COM','SDADS','$2a$10$pmcxDHJ/Ndu0yuoGG4lWH.Fd4/pED4utGz.v3c7N7P98JOvSodZZC','DASDSA','WNb9pIZNuWYhsRcTgJwyP5AOJCCx/Yx5LSH/NOVsoBm8b35zwYgneJybGToloxyR0qvSbYRT0tt2YanvHlp9EC2zcm2HWDKCUpO18zPC205Ac6hHy7g9zbCgpMmZxoRBrKLe7MhzbRJzEGEZIEaudN7Bw2Uf79UhUeTaVW9aQXU=',0,25),(214,'tomislav@gmail.com','Tomislav','$2a$10$OyWWjAC4blpY.dkcIjqeJ.hJpcM6TvVJGBJgKrs9/adHW0FdMcEEi','Tomislavic','f28wgaisCl1jn1uELdYH0cammtYn92o5gNnhhPgLOKqvHiKzzB2i48wxEapCsRTSwPuRyPex5f95RbHIUhW23Fh9In2X9wOwkeFw0cFziET9HIhfPinhgzBlO+K8VcGlh4xFt6nzvMcUc+acvKSG+9ywIw9FnOsScRilS5hJnpw=',1,26);
/*!40000 ALTER TABLE `pacijent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pacijent_link`
--

DROP TABLE IF EXISTS `pacijent_link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pacijent_link` (
  `email` varchar(255) NOT NULL,
  `datum_isteka` datetime(6) NOT NULL,
  `putanja` varchar(255) NOT NULL,
  `valid` bit(1) NOT NULL,
  PRIMARY KEY (`email`),
  UNIQUE KEY `UK_l1ld070lbwfjmiud2g94yfxq4` (`putanja`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pacijent_link`
--

LOCK TABLES `pacijent_link` WRITE;
/*!40000 ALTER TABLE `pacijent_link` DISABLE KEYS */;
/*!40000 ALTER TABLE `pacijent_link` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pregled`
--

DROP TABLE IF EXISTS `pregled`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pregled` (
  `id_pregleda` bigint NOT NULL AUTO_INCREMENT,
  `cena` double DEFAULT NULL,
  `dijagnoza` varchar(255) DEFAULT NULL,
  `kraj_termina` datetime(6) DEFAULT NULL,
  `opis` varchar(255) DEFAULT NULL,
  `pocetak_termina` datetime(6) DEFAULT NULL,
  `popust` int DEFAULT NULL,
  `id_klinike` bigint DEFAULT NULL,
  `id_lekara` bigint DEFAULT NULL,
  `id_medicinske_sestre` bigint DEFAULT NULL,
  `id_recepta` bigint DEFAULT NULL,
  `id_zdravstvenog_kartona` bigint DEFAULT NULL,
  PRIMARY KEY (`id_pregleda`),
  KEY `FK11p702sd6b5c6fnuer6bmu85u` (`id_klinike`),
  KEY `FK66y8vqnec0paevmxrfxtd27p6` (`id_lekara`),
  KEY `FKowc3beam5if6a01tbebay1knn` (`id_medicinske_sestre`),
  KEY `FKhclg2h6kvdpmyv3i88aosh757` (`id_recepta`),
  KEY `FK2hivov9uueaa0lb80i5nba8rx` (`id_zdravstvenog_kartona`),
  CONSTRAINT `FK11p702sd6b5c6fnuer6bmu85u` FOREIGN KEY (`id_klinike`) REFERENCES `klinika` (`id_klinike`),
  CONSTRAINT `FK2hivov9uueaa0lb80i5nba8rx` FOREIGN KEY (`id_zdravstvenog_kartona`) REFERENCES `zdravstveni_karton` (`id_zdravstvenog_kartona`),
  CONSTRAINT `FK66y8vqnec0paevmxrfxtd27p6` FOREIGN KEY (`id_lekara`) REFERENCES `lekar` (`id_korisnika`),
  CONSTRAINT `FKhclg2h6kvdpmyv3i88aosh757` FOREIGN KEY (`id_recepta`) REFERENCES `recept` (`id_recepta`),
  CONSTRAINT `FKowc3beam5if6a01tbebay1knn` FOREIGN KEY (`id_medicinske_sestre`) REFERENCES `medicinska_sestra` (`id_korisnika`)
) ENGINE=InnoDB AUTO_INCREMENT=218 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pregled`
--

LOCK TABLES `pregled` WRITE;
/*!40000 ALTER TABLE `pregled` DISABLE KEYS */;
INSERT INTO `pregled` VALUES (200,50,'opis','2021-06-22 18:00:00.000000','opis','2021-06-15 14:00:00.000000',50,2,1,89,NULL,23),(201,11205,NULL,'2021-07-22 14:21:42.814000',NULL,'2021-07-22 13:21:42.814000',0,2,1,136,NULL,NULL),(202,213321,NULL,'2021-07-22 14:34:41.891000',NULL,'2021-07-22 13:34:41.891000',0,2,1,89,NULL,NULL),(203,420,NULL,'2021-07-23 13:40:01.885000',NULL,'2021-07-23 13:30:01.885000',0,2,1,136,NULL,NULL),(204,11205,NULL,'2021-07-23 13:32:39.481000',NULL,'2021-07-23 13:31:39.481000',0,2,2,136,NULL,NULL),(205,1,NULL,'2021-07-24 00:41:08.666000',NULL,'2021-07-24 00:40:08.666000',0,3,140,141,NULL,NULL),(206,1,NULL,'2021-07-24 00:41:08.666000',NULL,'2021-07-24 00:40:08.666000',0,3,140,141,NULL,NULL),(207,2,NULL,'2021-07-24 00:41:45.222000',NULL,'2021-07-24 00:40:45.222000',0,3,140,141,NULL,22),(208,11205,NULL,'2021-07-24 16:06:46.127000',NULL,'2021-07-24 14:10:46.127000',0,2,1,89,NULL,22),(209,11205,NULL,'2021-08-01 12:59:57.370000',NULL,'2021-08-01 12:49:57.370000',0,2,1,89,NULL,NULL),(210,213321,NULL,'2021-08-23 13:13:06.343000',NULL,'2021-08-22 13:03:06.343000',0,2,1,89,NULL,22),(211,11205,'Covid','2021-08-05 12:30:58.918000','Ima Covid Mora da lezi.','2021-08-05 12:20:58.918000',5,2,1,89,NULL,22),(212,1,NULL,'2021-08-06 16:00:24.266000',NULL,'2021-08-06 15:00:24.266000',0,3,139,141,NULL,22),(213,2,NULL,'2021-08-06 16:01:07.024000',NULL,'2021-08-06 15:01:07.024000',0,3,140,143,NULL,22),(214,11205,NULL,'2021-09-18 12:29:08.625000',NULL,'2021-09-18 13:29:08.625000',0,2,1,89,NULL,NULL),(215,420,NULL,'2021-09-26 10:55:43.165000',NULL,'2021-09-18 13:29:43.165000',0,2,2,136,NULL,22),(216,2,NULL,'2021-09-18 10:30:42.399000',NULL,'2021-09-18 13:30:42.399000',0,3,139,141,NULL,NULL),(217,11205,NULL,'2021-09-26 19:07:58.590000',NULL,'2021-09-24 19:07:58.590000',0,2,1,89,NULL,NULL);
/*!40000 ALTER TABLE `pregled` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recept`
--

DROP TABLE IF EXISTS `recept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recept` (
  `id_recepta` bigint NOT NULL AUTO_INCREMENT,
  `overen` bit(1) NOT NULL,
  `opis_recepta` varchar(255) NOT NULL,
  `id_pregleda` bigint DEFAULT NULL,
  PRIMARY KEY (`id_recepta`),
  KEY `FKkh17yvslc36aoyk1rc6608x40` (`id_pregleda`),
  CONSTRAINT `FKkh17yvslc36aoyk1rc6608x40` FOREIGN KEY (`id_pregleda`) REFERENCES `pregled` (`id_pregleda`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recept`
--

LOCK TABLES `recept` WRITE;
/*!40000 ALTER TABLE `recept` DISABLE KEYS */;
/*!40000 ALTER TABLE `recept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refreshtoken`
--

DROP TABLE IF EXISTS `refreshtoken`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refreshtoken` (
  `id` bigint NOT NULL,
  `expiry_date` datetime(6) NOT NULL,
  `token` varchar(255) NOT NULL,
  `id_korisnika` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_or156wbneyk8noo4jstv55ii3` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refreshtoken`
--

LOCK TABLES `refreshtoken` WRITE;
/*!40000 ALTER TABLE `refreshtoken` DISABLE KEYS */;
/*!40000 ALTER TABLE `refreshtoken` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zdravstveni_karton`
--

DROP TABLE IF EXISTS `zdravstveni_karton`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zdravstveni_karton` (
  `id_zdravstvenog_kartona` bigint NOT NULL AUTO_INCREMENT,
  `alergije` varchar(255) DEFAULT NULL,
  `dioptrija` double DEFAULT NULL,
  `krvna_grupa` varchar(255) DEFAULT NULL,
  `tezina` int DEFAULT NULL,
  `visina` int DEFAULT NULL,
  PRIMARY KEY (`id_zdravstvenog_kartona`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zdravstveni_karton`
--

LOCK TABLES `zdravstveni_karton` WRITE;
/*!40000 ALTER TABLE `zdravstveni_karton` DISABLE KEYS */;
INSERT INTO `zdravstveni_karton` VALUES (21,NULL,0,'O+',125,195),(22,'Sunce, Grinje, Ambrozija, Voda',-3.75,'O-',99,180),(23,NULL,0,NULL,0,0),(24,NULL,0,NULL,0,0),(25,NULL,0,NULL,0,0),(26,NULL,0,NULL,0,0);
/*!40000 ALTER TABLE `zdravstveni_karton` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-25  1:08:47
