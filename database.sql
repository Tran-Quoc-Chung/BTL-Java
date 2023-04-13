-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: pharma_swing
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` int NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `position` varchar(45) NOT NULL,
  `namestaff` varchar(45) NOT NULL,
  `idstaff` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'testlogin','123456','Staff','Nguyễn A',1),(2,'Admin','123456','Admin','Admin',2);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `drugslist`
--

DROP TABLE IF EXISTS `drugslist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `drugslist` (
  `code` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `price` int NOT NULL,
  `idpk` int NOT NULL,
  PRIMARY KEY (`code`),
  KEY `FK_drugslist_pharmalv2` (`idpk`),
  CONSTRAINT `FK_drugslist_pharmalv2` FOREIGN KEY (`idpk`) REFERENCES `pharmalv2` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drugslist`
--

LOCK TABLES `drugslist` WRITE;
/*!40000 ALTER TABLE `drugslist` DISABLE KEYS */;
INSERT INTO `drugslist` VALUES (1001,'Penicillin G (benzylpenicillin)',2000,1),(1002,'Penicillin V (phenoxymethylpenicillin)',3000,1),(1003,'Amoxicillin',5000,1),(1004,'Ampicillin',15000,1),(1005,'Oxacillin',5000,1),(1006,'Cloxacillin',6000,1),(1007,'Methicillin',1000,1),(1008,'Nafcillin',25000,1),(1009,'Dicloxacillin',8000,1),(1010,'Cefaclor',7000,2),(1011,'Cefazolin',26000,2),(1012,'Cefepime',31000,2),(1013,'Cefixime',6000,2),(1014,'Cefotaxime',4000,2),(1015,'Cefoxitin',48000,2),(1016,'Cefprozil',23000,2),(1017,'Ceftazidime',5000,2),(1018,'Ceftriaxone',9000,2),(1019,'Cefuroxime',14000,2),(1020,'Azithromycin',3000,3),(1021,'Clarithromycin',5000,3),(1022,'Erythromycin',8000,3),(1023,'Fidaxomicin',7000,3),(1024,'Roxithromycin',12000,3),(1025,'Demeclocycline',8000,4),(1026,'Doxycycline',5000,4),(1027,'Minocycline',22000,4),(1028,'Tetracycline',6000,4),(1029,'Amikacin',2000,5),(1030,'Gentamicin',8000,5),(1031,'Kanamycin',7000,5),(1032,'Neomycin',62000,5),(1033,'Streptomycin',12000,5),(1034,'Aspirin',32000,6),(1035,'Ibuprofen',4000,6),(1036,'Naproxen',7000,6),(1037,'Diclofenac',2000,6),(1038,'Celecoxib',47000,6),(1039,'Prednisone',14000,7),(1040,'Hydrocortisone',5000,7),(1041,'Dexamethasone',7000,7),(1042,'Methylprednisolone',9000,7),(1043,'Betamethasone',6000,7),(1044,'Anakinra',50000,8),(1045,'Canakinumab',2000,8),(1046,'Rilonacept',40000,8),(1047,'Tocilizumab',20000,8),(1048,'Tofacitinib',8000,9),(1049,'Baricitinib',15000,9),(1050,'Upadacitinib',17000,9),(1051,'Methotrexate',3000,10),(1052,'Sulfasalazine',8000,10),(1053,'Leflunomide',7000,10),(1054,'Hydroxychloroquine',9000,10),(1055,'Chloroquine',1000,10),(1056,'Infliximab',2000,11),(1057,'Adalimumab',11000,11),(1058,'Etanercept',21000,11),(1059,'Golimumab',7000,11),(1060,'Abatacept',9000,11),(1061,'Colchicine',5000,12),(1062,'Corticosteroids',15000,12),(1063,'Probenecid',17000,12),(1064,'Allopurinol',13000,12),(1065,'Febuxostat',2000,12),(1066,'Tylenol ',5000,13),(1067,'Panadol ',6000,13),(1068,'Calpol ',9000,13),(1069,'Dafalgan ',31000,13),(1070,'Acamol ',2000,13),(1071,'Ecotrin',4000,14),(1072,'Anacin',5000,14),(1073,'Ascriptin',92000,14),(1074,'Aspro',3000,14),(1075,'Advil',5000,15),(1076,'Motrin',2000,15),(1077,'Nuprin',21000,15),(1078,'Caldolor',9000,15),(1079,'Pedea',3000,15),(1080,'Tramadol',14000,16),(1081,'Codeine',18000,16),(1082,'Oxycodone',10000,16),(1083,'Hydrocodone',8000,16),(1084,'Acetaminophen',7000,17),(1085,'Muscle relaxants',2000,17),(1086,'Topical analgesics',6000,17),(1087,'Opioids ',19000,17),(1088,'Dicyclomine',2000,18),(1089,'Methylcellulose',4000,18),(1090,'Budesonide',1000,18),(1091,'Diazepam',21000,18),(1092,'Lorazepam',25000,18),(1093,'Famotidine',9000,19),(1094,'Vedolizumab',5000,19),(1095,'Infliximab',5000,19),(1096,'Adalimumab',1000,19),(1097,'Certolizumab',2000,19),(1098,'Mesalamine',3000,19),(1099,'Rolaids',7000,20),(1100,'Gaviscon',5000,20),(1101,'Alka-Seltzer',32000,20),(1102,'Maalox',7000,20),(1103,'Tums',2000,20),(1104,'Zantac',1000,21),(1105,'Pepcid',4000,21),(1107,'Tagamet',16000,21),(1108,'Axid',17000,21),(1109,'Interferon ',4000,22),(1110,'Ribavirin',7000,22),(1111,'Sofosbuvir',2000,22),(1112,'Ledipasvir',2000,22),(1113,'Daclatasvir',3000,22),(1114,'Simeprevir',15000,22),(1115,'Metformin',6000,23),(1116,'Pioglitazone',8000,23),(1117,'Statin ',9000,23),(1118,'Orlistat',4000,23),(1119,'Silymarin',15000,23),(1120,'Prednisone',19000,23),(1121,'Disulfiram',1000,24),(1122,'Naltrexone',5000,24),(1123,'Acamprosate',80000,24),(1124,'Prednisone',20000,24),(1125,'Pentoxifylline',1000,24),(1126,'Colchicine',21000,24),(1128,'Ibuprofen',5000,25),(1129,'Prednisone',7000,25),(1130,'Acyclovir',1000,25),(1131,'Interferon-alpha',2000,25),(1132,'Paracetamol',5000,25),(1134,'Acyclovir',6000,26),(1135,'Valacyclovir',4000,26),(1136,'Famciclovir',7000,26),(1137,'Ibuprofen',22000,26),(1138,'Paracetamol',3000,26),(1139,'Prednisone',4000,26),(1140,'Nitrogliserin',1000,27),(1141,'Beta-blocker',25000,27),(1142,'Calcium channel blocker',3000,27),(1143,'Aspirin',9000,27),(1144,'Morphine',8000,27),(1145,'Nitrat',2000,28),(1146,'Beta-blocker',16000,28),(1147,'Calcium channel blocker',2000,28),(1148,'ACE inhibitor',4000,28),(1149,'Digoxin',8000,28),(1150,'Ivabradine',17000,28),(1151,'Ivabradine',7000,29),(1152,'Digoxin',15000,29),(1153,'warfarin',1700,29),(1155,'dabigatran',21000,29),(1156,'verapamil',8000,29),(1157,'diltiazem',1000,29),(1158,'Amiodarone',2000,30),(1159,'Sotalol',4000,30),(1160,'Propafenone',25000,30),(1161,'Flecainide',12000,30),(1162,'Dronedarone',5000,30),(1163,'Verapamil',7000,30),(1164,'Adenosine',1000,30),(1165,'enalapril',3000,31),(1166,'lisinopril',7000,31),(1167,'losartan',8000,31),(1168,'valsartan',2000,31),(1169,'metoprolol',52000,31),(1170,'amlodipine',8000,31),(1171,'Legalon',61000,32),(1172,'Essentiale forte',25000,32),(1173,'Hepatonic',21000,32),(1174,'Hepatobil',15000,32),(1175,'Berliver',11000,32),(1176,'Karsil',33000,32),(1177,'Đậu đen',15000,33),(1178,'Nấm linh chi',2000,33),(1179,'Hoài sơn',65000,33),(1180,'Thổ phục linh',80000,33),(1181,'Glucosamine',78000,34),(1182,'Chondroitin',150000,34),(1183,'MSM',230000,34),(1184,'Sâm',90000,34),(1185,'Ginkgo biloba',80000,35),(1186,'Bacopa monnier',45000,36),(1187,'Vinpocetine',25000,36),(1188,'Piracetam',36000,36),(1189,'Modafinil',67000,36),(1190,'Centrum',58000,37),(1191,'Berocca',59000,37),(1192,'One-A-Day',21000,37),(1193,'GNC Mega Men',25000,37),(1194,'Rainbow Light Men\'s One',23000,37),(1195,'MegaFood Men\'s One Daily',25000,37),(1196,'Đông trùng hạ thảo',120000,38),(1197,'Nhục thung dung',230000,38),(1198,'Nhục đậu khấu',33000,38),(1199,'Đương quy',250000,38),(1200,'Thiên niên kiện',25000,38),(1201,'Huyết giác',120000,38),(1202,'Cát cánh',280000,38);
/*!40000 ALTER TABLE `drugslist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu` (
  `idmenu` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `amount` int NOT NULL,
  `price` int NOT NULL,
  `iddrugs` int NOT NULL,
  `stt` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`idmenu`)
) ENGINE=InnoDB AUTO_INCREMENT=222 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pharma`
--

DROP TABLE IF EXISTS `pharma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pharma` (
  `id` int NOT NULL,
  `Name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pharma`
--

LOCK TABLES `pharma` WRITE;
/*!40000 ALTER TABLE `pharma` DISABLE KEYS */;
INSERT INTO `pharma` VALUES (1,'Kháng sinh'),(2,'Kháng viêm'),(3,'Giảm đau, hạ sốt'),(4,'Dạ dày'),(5,'Gan,thận'),(6,'Tim mạch'),(7,'Thuốc bổ tổng hợp'),(8,'Thực phẩm chức năng');
/*!40000 ALTER TABLE `pharma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pharmalv2`
--

DROP TABLE IF EXISTS `pharmalv2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pharmalv2` (
  `id` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `idpk` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_pharmalv2_pharma` (`idpk`),
  CONSTRAINT `FK_pharmalv2_pharma` FOREIGN KEY (`idpk`) REFERENCES `pharma` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pharmalv2`
--

LOCK TABLES `pharmalv2` WRITE;
/*!40000 ALTER TABLE `pharmalv2` DISABLE KEYS */;
INSERT INTO `pharmalv2` VALUES (1,'Kháng sinh Penicillin',1),(2,'Kháng sinh Cephalosporin',1),(3,'Kháng sinh Macrolide',1),(4,'Kháng sinh Tetracycline',1),(5,'Kháng sinh Aminoglycoside',1),(6,'Kháng viêm không steroid',2),(7,'Kháng viêm steroid',2),(8,'Kháng viêm ức chế interleukin',2),(9,'Kháng viêm ức chế Janus kinase',2),(10,'Kháng viêm ổn định tế bào',2),(11,'Kháng viêm sinh học',2),(12,'Kháng viêm bệnh gút',2),(13,'Hạ sốt Paracetamol',3),(14,'Hạ sốt Aspirin',3),(15,'Hạ sốt Ibuprofen',3),(16,'Giảm đau thoái hóa khớp',3),(17,'Giảm đau đau cơ',3),(18,'Viêm dạ dày ',4),(19,'Viêm dạ dày kháng viêm',4),(20,'Đau dạ dày antacid',4),(21,'Đau dạ dày H2 blocker',4),(22,'Viêm gan A, B, C',5),(23,'Gan nhiễm mỡ',5),(24,'Viêm gan do rượu',5),(25,'Viêm gan virus Epstein-Barr',5),(26,'Viêm gan do virus herpes',5),(27,'Đau tim mạch cấp tính',6),(28,'Đau tim mạch mãn tính',6),(29,'Bệnh lý van tim',6),(30,'Rối loạn nhịp tim',6),(31,'Tăng huyết áp (cao huyết áp)',6),(32,'Thuốc bổ gan',7),(33,'Thuốc bổ thận',7),(34,'Thuốc bổ khớp',7),(35,'Thuốc bổ não',7),(36,'Thuốc bổ mắt',7),(37,'Thuốc bổ cơ thể',7),(38,'Thuốc bổ huyết',7);
/*!40000 ALTER TABLE `pharmalv2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voucher`
--

DROP TABLE IF EXISTS `voucher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `voucher` (
  `codevoucher` varchar(45) NOT NULL,
  `idvoucher` int NOT NULL,
  `startdate` date NOT NULL,
  `enddate` date NOT NULL,
  `discount` float NOT NULL,
  PRIMARY KEY (`idvoucher`),
  UNIQUE KEY `codevoucher_UNIQUE` (`codevoucher`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voucher`
--

LOCK TABLES `voucher` WRITE;
/*!40000 ALTER TABLE `voucher` DISABLE KEYS */;
INSERT INTO `voucher` VALUES ('abc123',1,'2022-01-01','2024-01-01',0.5),('abcd1',2,'2022-01-01','2023-01-02',0.3),('a1234',3,'2021-01-01','2021-03-03',0.5);
/*!40000 ALTER TABLE `voucher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-13 13:26:41
