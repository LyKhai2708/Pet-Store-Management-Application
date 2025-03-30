-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: dbms
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `CustomerCCCD` varchar(30) NOT NULL,
  `CustomerName` varchar(50) NOT NULL,
  `DateOfBirth` date NOT NULL,
  `Cus_Phone` varchar(15) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `SocialMedia` varchar(200) NOT NULL,
  `Gender` varchar(200) NOT NULL,
  `Nation` varchar(200) NOT NULL,
  PRIMARY KEY (`CustomerCCCD`),
  UNIQUE KEY `Email` (`Email`),
  CONSTRAINT `check_customer_email` CHECK (regexp_like(`Email`,_utf8mb4'[a-zA-Z0-9.*_]+@gmail.com')),
  CONSTRAINT `check_customer_media` CHECK (regexp_like(`SocialMedia`,_utf8mb4'^https://www.facebook.com/[a-zA-Z0-9.*]+/$')),
  CONSTRAINT `check_customer_phone` CHECK (regexp_like(`Cus_Phone`,_utf8mb4'^0[0-9]{8,10}')),
  CONSTRAINT `customer_chk_1` CHECK (regexp_like(`gender`,_utf8mb4'(Male|Female)'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('09220307654','Ryu Min-seok','2002-10-14','0123456789','champion@gmail.com','Busan','https://www.facebook.com/keriakorea/','Male','Korea'),('09220401334','Eva Brown','1995-03-10','01122334455','eva.brown@gmail.com','789 Pine St, Village','https://www.facebook.com/evabrown/','Female','UK'),('09220402634','Nguyen Kim Trong','2005-08-06','0987654321','trong@gmail.com','TPHCM','https://www.facebook.com/trong/','Male','Viet Nam'),('09220678593','Nguyen Van B','2005-11-23','0892223856','nguyenvanb@gmail.com','Quan 7 TPHCM','https://www.facebook.com/nguyenvanb/','Male','Canada');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detail_invoice`
--

DROP TABLE IF EXISTS `detail_invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detail_invoice` (
  `InvoiceID` varchar(20) NOT NULL,
  `Product_ID` varchar(5) DEFAULT NULL,
  `Pet_ID` varchar(5) DEFAULT NULL,
  `Amount` int NOT NULL,
  `Price` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detail_invoice`
--

LOCK TABLES `detail_invoice` WRITE;
/*!40000 ALTER TABLE `detail_invoice` DISABLE KEYS */;
INSERT INTO `detail_invoice` VALUES ('B1`','IT_2',NULL,3,450000),('B1`',NULL,'10',1,199),('B2','IT_1',NULL,3,750000),('B2',NULL,'1',1,10800),('B3','IT_2',NULL,3,450000),('B3',NULL,'10',1,199),('B1','IT_2',NULL,3,450000),('B1',NULL,'10',1,199),('B1','IT_1',NULL,3,750000),('B6','IT_1',NULL,3,750000),('B6',NULL,'10',1,199),('B7','IT_2',NULL,3,450000),('B7','IT_3',NULL,2,800000),('B8','IT_1',NULL,3,750000),('B8','IT_2',NULL,2,300000),('B9','IT_2',NULL,2,300000),('B9','IT_1',NULL,1,250000),('B11','IT_2',NULL,2,300000),('B12','IT_2',NULL,3,450000),('B12','IT_3',NULL,5,2000000),('B12','IT_1',NULL,2,500000),('B13','IT_1',NULL,3,750000),('B13',NULL,'3',1,2);
/*!40000 ALTER TABLE `detail_invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice` (
  `Invoice_ID` varchar(20) NOT NULL,
  `Staff_ID` varchar(3) DEFAULT NULL,
  `CustomerCCCD` varchar(30) NOT NULL,
  `SellDay` date NOT NULL,
  `Total_Price` float NOT NULL,
  PRIMARY KEY (`Invoice_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES ('B1','ST1','09220307654','2023-11-25',1200200),('B1`','ST1','09220307654','2023-11-25',450199),('B11','ST1','09220307654','2023-11-10',300000),('B12','ST3','09220401334','2023-11-26',2950000),('B13','ST1','09220307654','2023-11-23',750002),('B2','ST1','09220401334','2023-11-25',760800),('B3','ST1','09220401334','2023-11-17',450199),('B6','AMD','09220307654','2023-11-25',750199),('B7','ST1','09220307654','2023-11-25',1250000),('B8','ST1','09220307654','2023-11-25',1050000),('B9','ST1','09220307654','2023-11-25',550000);
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pet`
--

DROP TABLE IF EXISTS `pet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pet` (
  `Pet_ID` varchar(5) NOT NULL,
  `Pet_Type` varchar(50) NOT NULL,
  `Pet_Breed` varchar(50) NOT NULL,
  `Pet_Name` varchar(50) NOT NULL,
  `Color` varchar(20) NOT NULL,
  `Age` int NOT NULL,
  `Weight` float NOT NULL,
  `Picture` varchar(255) NOT NULL,
  `Gender` varchar(10) NOT NULL,
  `Shots` varchar(200) NOT NULL,
  `Flush` varchar(200) NOT NULL,
  `Health` varchar(50) NOT NULL,
  `Price` decimal(15,2) NOT NULL,
  PRIMARY KEY (`Pet_ID`),
  CONSTRAINT `pet_chk_1` CHECK ((`age` > 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pet`
--

LOCK TABLES `pet` WRITE;
/*!40000 ALTER TABLE `pet` DISABLE KEYS */;
INSERT INTO `pet` VALUES ('1','Horse','Camarillo','Jolly Jumper','White',8,300,'C:\\Users\\84896\\Documents\\NetBeansProjects\\DBMS\\src\\icons\\Jolly Jumper.png','Male','Yes','Yes','Healthy',10800.00),('2','Cat','British Longhair Cat','Lion','White',3,4,'C:\\Users\\84896\\Documents\\NetBeansProjects\\DBMS\\src\\icons\\catt.png','Male','No','No','Healthy',200.00),('4','Dog','Husky','Mickey','Black and White',1,2,'C:\\Users\\84896\\Documents\\NetBeansProjects\\DBMS\\src\\icons\\husky.png','Female','Yes','No','Eye problem',249.00),('5','Bird','Parrot','Jack Sparrow','Orange and Blue',3,4,'C:\\Users\\84896\\Documents\\NetBeansProjects\\DBMS\\src\\icons\\parrot.png','Male','No','Yes','Healthy',199.00),('6','Rabbit','English Spot','Hopper','Brown and White',2,3,'C:\\Users\\84896\\Documents\\NetBeansProjects\\DBMS\\src\\icons\\rabbit.png','Female','No','Yes','Healthy',99.00),('7','Snake','Rough Green Snake','Syther','Green',2,1,'C:\\Users\\84896\\Documents\\NetBeansProjects\\DBMS\\src\\icons\\snake.png','Female','No','No','Healthy',139.00),('8','Rabbit','New Zeland Rabbit','Bonnie','White',3,3,'C:\\Users\\84896\\Documents\\NetBeansProjects\\DBMS\\src\\icons\\whiterabbit.png','Male','No','No','Healthy',99.00),('9','Cat','Exotic','Kingen','White and Yellow',5,20,'C:\\Users\\84896\\Documents\\NetBeansProjects\\DBMS\\src\\icons\\royalcat.png','Male','No','No','Healthy',499.00),('C1','Cat','British shorthair','Naixo','Black and White',8,5,'C:\\Users\\84896\\Documents\\NetBeansProjects\\DBMS\\src\\icons\\likecat.png','Male','Yes','Yes','Healthy',30.00);
/*!40000 ALTER TABLE `pet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `petcategory`
--

DROP TABLE IF EXISTS `petcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `petcategory` (
  `Pet_Type` varchar(50) NOT NULL,
  `Amount` int NOT NULL,
  PRIMARY KEY (`Pet_Type`),
  CONSTRAINT `petcategory_chk_1` CHECK ((`amount` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `petcategory`
--

LOCK TABLES `petcategory` WRITE;
/*!40000 ALTER TABLE `petcategory` DISABLE KEYS */;
INSERT INTO `petcategory` VALUES ('Bird',1),('Cat',2),('Dog',1),('Horse',1),('Mouse',1),('Rabit',2),('Snake',1);
/*!40000 ALTER TABLE `petcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `petproduct`
--

DROP TABLE IF EXISTS `petproduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `petproduct` (
  `Product_ID` varchar(5) NOT NULL,
  `Product_Name` varchar(50) NOT NULL,
  `Price_issue` decimal(15,2) NOT NULL,
  `Price_sell` decimal(15,2) NOT NULL,
  `Stock` int DEFAULT NULL,
  `Receipt_Day` date NOT NULL,
  PRIMARY KEY (`Product_ID`),
  CONSTRAINT `petproduct_chk_1` CHECK ((`stock` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `petproduct`
--

LOCK TABLES `petproduct` WRITE;
/*!40000 ALTER TABLE `petproduct` DISABLE KEYS */;
INSERT INTO `petproduct` VALUES ('IT_1','Dog Food',200000.00,20.00,88,'2023-11-15'),('IT_2','Cat Toy',100000.00,200.00,38,'2023-11-15'),('IT_3','Bird Cage',300000.00,50.00,13,'2023-11-15');
/*!40000 ALTER TABLE `petproduct` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `ROLE_ID` int NOT NULL,
  `ROLE_NAME` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Admin'),(2,'Staff');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `Staff_ID` varchar(3) NOT NULL,
  `Staff_name` varchar(20) NOT NULL,
  `Nation` varchar(30) NOT NULL,
  `Phone` varchar(11) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `Staff_Media` varchar(200) NOT NULL,
  PRIMARY KEY (`Staff_ID`),
  UNIQUE KEY `Email` (`Email`),
  CONSTRAINT `check_media` CHECK (regexp_like(`Staff_Media`,_utf8mb4'^https://www.facebook.com/[a-zA-Z0-9.*]+/$')),
  CONSTRAINT `check_staff_email` CHECK (regexp_like(`Email`,_utf8mb4'[a-zA-Z0-9.*_]+@gmail.com')),
  CONSTRAINT `check_staff_phone` CHECK (regexp_like(`Phone`,_utf8mb4'^0[0-9]{8,10}'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES ('AMD','Lee Sang-hyeok','Korea','0999999999','Faker@gmail.com','https://www.facebook.com/fakerkorea/'),('ST1','Jack','Viet Nam','09667756733','jack@gmail.com','https://www.facebook.com/jack.phuongtuan1204/'),('ST2','Jane Smith','Canada','0987654321','jane.smith@gmail.com','https://www.facebook.com/janesmith/'),('ST3','Bray','Viet Nam','09765874911','baothanhthien@gmail.com','https://www.facebook.com/bao.tran.376695/');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff_login`
--

DROP TABLE IF EXISTS `staff_login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff_login` (
  `STAFF_id` varchar(3) DEFAULT NULL,
  `USERNAME` varchar(255) NOT NULL,
  `PASS` varchar(255) NOT NULL,
  `ROLE_ID` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff_login`
--

LOCK TABLES `staff_login` WRITE;
/*!40000 ALTER TABLE `staff_login` DISABLE KEYS */;
INSERT INTO `staff_login` VALUES ('AMD','admin','pass123',1),('ST2','staff2','password456',2),('ST1','staff1','password456',2),('ST3','staff3','pass890',2);
/*!40000 ALTER TABLE `staff_login` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-27 15:21:08
