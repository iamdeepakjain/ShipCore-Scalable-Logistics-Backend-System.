-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: courier_db
-- ------------------------------------------------------
-- Server version	8.0.45

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
-- Table structure for table `packages`
--

DROP TABLE IF EXISTS `packages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `packages` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `delivery_address` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `item_name` varchar(255) DEFAULT NULL,
  `pickup_address` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `weight` double NOT NULL,
  `delivery_partner_id` bigint DEFAULT NULL,
  `destination_hub_id` bigint DEFAULT NULL,
  `pickup_partner_id` bigint DEFAULT NULL,
  `source_hub_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdptc3dc6ndj88vjoykjhxkfxw` (`delivery_partner_id`),
  KEY `FKgdis7ehmipdly3wc4fsw4l8t7` (`destination_hub_id`),
  KEY `FKi73n38hvp71xj1us58avxd51t` (`pickup_partner_id`),
  KEY `FKqnvj91ksby8bbo8769ijtmam5` (`source_hub_id`),
  KEY `FKcj3syi9lcukrlsh6o4llk0t8f` (`user_id`),
  CONSTRAINT `FKcj3syi9lcukrlsh6o4llk0t8f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKdptc3dc6ndj88vjoykjhxkfxw` FOREIGN KEY (`delivery_partner_id`) REFERENCES `partner` (`id`),
  CONSTRAINT `FKgdis7ehmipdly3wc4fsw4l8t7` FOREIGN KEY (`destination_hub_id`) REFERENCES `hub` (`id`),
  CONSTRAINT `FKi73n38hvp71xj1us58avxd51t` FOREIGN KEY (`pickup_partner_id`) REFERENCES `partner` (`id`),
  CONSTRAINT `FKqnvj91ksby8bbo8769ijtmam5` FOREIGN KEY (`source_hub_id`) REFERENCES `hub` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `packages`
--

LOCK TABLES `packages` WRITE;
/*!40000 ALTER TABLE `packages` DISABLE KEYS */;
INSERT INTO `packages` VALUES (1,'2026-04-08 01:30:54.604147','Delhi','Electronics','Laptop','Jaipur','CANCELLED',2.5,1,1,1,1,1);
/*!40000 ALTER TABLE `packages` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-08 16:18:46
