-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: vehicle_db2
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `state_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_llidyp77h6xkeokpbmoy710d4` (`name`),
  KEY `FKfhcwj895okdb83utvpvft0bsk` (`state_id`),
  CONSTRAINT `FKfhcwj895okdb83utvpvft0bsk` FOREIGN KEY (`state_id`) REFERENCES `state` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56652 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'San Diego',1),(2,'Sedgwick',2),(3,'Kitsap',3),(4,'Snohomish',3),(5,'Walla Walla',3),(7,'Island',3),(9,'Thurston',3),(11,'Skagit',3),(15,'King',3),(20,'Whitman',3),(29,'Yakima',3),(45,'Stafford',47),(51,'Chelan',3),(64,'New London',66),(66,'Denton',67),(67,'Pierce',3),(68,'Riverside',1),(69,'Stevens',3),(75,'Spokane',3),(77,'Grant',3),(99,'Clark',3),(128,'Whatcom',3),(153,'Lewis',3),(172,'Mason',3),(188,'Kittitas',3),(201,'Grays Harbor',3),(206,'Okanogan',3),(217,'Franklin',3),(248,'Benton',3),(255,'San Juan',3),(289,'Jefferson',3),(325,'Skamania',3),(416,'Klickitat',3),(526,'Asotin',3),(551,'Cowlitz',3),(561,'Arapahoe',565),(624,'Chesapeake',47),(741,'Clallam',3),(1844,'Virginia Beach',47),(1864,'Norfolk',47),(2144,'Salt Lake',2146),(2413,'Pend Oreille',3),(2508,'Douglas',3),(2902,'Bell',67),(3079,'Solano',1),(3400,'Pacific',3),(3521,'Fairfax',47),(4154,'Carteret',4154),(4222,'Sacramento',1),(4440,'Adams',3),(4591,'Anne Arundel',4593),(4731,'Wahkiakum',3),(5178,'St. Clair',5180),(6295,'Hoke',4154),(6702,'Columbia',3),(6873,'Santa Clara',1),(7127,'District of Columbia',7127),(7318,'Kern',1),(7589,'El Paso',67),(8965,'Danville',47),(9149,'Lincoln',3),(9514,'Ferry',3),(9640,'Loudoun',47),(11196,'Bexar',67),(13562,'Prince George\'s',4593),(14317,'Frederick',4593),(15001,'Alexandria',47),(15172,'Los Angeles',1),(15483,'Rockdale',15484),(15802,'Marin',1),(16272,'Suffolk',47),(16316,'Plaquemines',16318),(16355,'Fairbanks North Star',16357),(16852,'St. Mary\'s',4593),(17300,'St. Louis',17301),(17898,'Montgomery',4593),(18197,'Galveston',67),(18211,'Louisa',47),(21324,'Cook',5180),(22480,'Garfield',3),(23721,'Calvert',4593),(24109,'Platte',17301),(25531,'Contra Costa',1),(26646,'Placer',1),(27129,'Sarasota',27128),(27936,'Orange',1),(28192,'Onslow',4154),(30589,'Maricopa',30590),(36503,'Madison',5180),(38569,'Elmore',38573),(45289,'Wichita',67),(50645,'Oldham',50645),(52214,'Monterey',1),(53505,'DeKalb',15484),(55714,'San Mateo',1);
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-07 17:45:28
