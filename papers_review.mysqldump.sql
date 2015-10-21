-- MySQL dump 10.13  Distrib 5.6.19, for osx10.7 (i386)
--
-- Host: localhost    Database: papers_review
-- ------------------------------------------------------
-- Server version	5.6.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `EndUser`
--

DROP TABLE IF EXISTS `EndUser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EndUser` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ix_EnduserEmail` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EndUser`
--

LOCK TABLES `EndUser` WRITE;
/*!40000 ALTER TABLE `EndUser` DISABLE KEYS */;
INSERT INTO `EndUser` VALUES (1,'','manoelcampos@gmail.com','123','Manoel Campos');
/*!40000 ALTER TABLE `EndUser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Field`
--

DROP TABLE IF EXISTS `Field`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Field` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `abbreviation` varchar(10) DEFAULT NULL,
  `description` varchar(120) NOT NULL,
  `fieldType_id` bigint(20) NOT NULL,
  `project_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ix_FieldDescription` (`project_id`,`description`),
  UNIQUE KEY `ix_FieldAbbrev` (`project_id`,`abbreviation`),
  KEY `FK_eqa0btcqnvbtclctkjf0tvsw` (`fieldType_id`),
  CONSTRAINT `FK_eqa0btcqnvbtclctkjf0tvsw` FOREIGN KEY (`fieldType_id`) REFERENCES `FieldType` (`id`),
  CONSTRAINT `FK_q22a785cb1yeqrwdoftnxnwbj` FOREIGN KEY (`project_id`) REFERENCES `Project` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Field`
--

LOCK TABLES `Field` WRITE;
/*!40000 ALTER TABLE `Field` DISABLE KEYS */;
INSERT INTO `Field` VALUES (10,'Exp','Experiments',2,5),(11,'Tech','Technologies',2,5),(12,'OSS','Open Source',1,5),(13,'Conc','Concerns',2,5),(14,'Proact','Proactiveness',1,5),(15,'Prob','Problem Classification',1,5),(16,'VMSO','VM Selection Order',1,5),(18,'DHS','Destionation Host Selection',2,5),(19,'Imp','Implementation',2,5),(20,'Params','Parameters',2,5),(21,'VMAD','VM Allocation Dynamics',1,5),(22,'Arch','Architecture',1,5),(23,'Sol','Solution',1,5),(24,'AC','Algorithm Complexity',1,5);
/*!40000 ALTER TABLE `Field` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FieldOption`
--

DROP TABLE IF EXISTS `FieldOption`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FieldOption` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `abbreviation` varchar(20) DEFAULT NULL,
  `description` varchar(120) NOT NULL,
  `field_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ix_FieldOptionDesc` (`field_id`,`description`),
  UNIQUE KEY `ix_FieldOptionAbbrev` (`field_id`,`abbreviation`),
  CONSTRAINT `FK_cfcpp5ek6yt9i9qja3muymgwm` FOREIGN KEY (`field_id`) REFERENCES `Field` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=179 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FieldOption`
--

LOCK TABLES `FieldOption` WRITE;
/*!40000 ALTER TABLE `FieldOption` DISABLE KEYS */;
INSERT INTO `FieldOption` VALUES (17,'Sim','Simulation',10),(18,'TB','Testbed',10),(19,'Trace','Real Trace Analysis',10),(20,'Math','Mathematical Analysis',10),(21,'Bench','Benchmark Testbeds',10),(22,'OSK','OpenStack',11),(23,'CS','CloudSim',11),(24,'CSC','CloudSched',11),(25,'CIS','Custom Implemented Simulator',11),(26,'NS2','Network Simulator 2',11),(27,'NS3','Network Simulator 3',11),(28,'PS','PeerSim',11),(29,'TPC-H','Transaction Processing Performance Council Benchmark (Query per Hour)',11),(30,'XCP','Xen Cloud Platform',11),(31,'NPB','NAS Parallel Benchmarks',11),(32,'N/I','Not Identified',12),(33,'Y','Yes',12),(34,'N','No',12),(35,'N/A','Not Applicable',12),(36,'Alg','Algorithms Presented',12),(37,'SLAM','SLA Managment',13),(38,'VMS','VM Selection',13),(39,'VMP','VM Placement',13),(40,'VMMig','VM Migration',13),(41,'VMSh','VM Shuffle',13),(42,'VMRP','VM Resource Provisioning',13),(43,'SLAV','SLA Violations',13),(44,'QoS','Quality of Service',13),(45,'AV','Availability',13),(46,'IaaS','IaaS',13),(47,'PaaS','PaaS',13),(48,'SaaS','SaaS',13),(49,'$','Pricing',13),(50,'SC','Server Consolidation',13),(51,'-EC','Reduce Energy Consumption',13),(52,'-M','Reduce number of VM migrations',13),(53,'ASLAV','Avoid SLA violation',13),(54,'-NET','Reduce Network Traffic',13),(55,'-VMT','Reduce VM migration time',13),(56,'-DOWN','Reduce Service Downtime',13),(57,'LB','Load Balancing',13),(58,'+RU','Maximize Resource Usage',13),(59,'-CO2','Carbon Reduction',13),(60,'-$','Costs Reduction',13),(61,'BP','Burstiness Prediction',13),(62,'VMR','VM Reconfiguration',13),(63,'-VMMC','Reduce VM migration cost/overhead',13),(64,'-MS','Minimize Makespan',13),(65,'-T','Minimize Temperature',13),(66,'-FAT','Minimize File Access Time',13),(67,'FT','Fault Tolerance',13),(69,'N/I','Not Identified',14),(70,'R','Reactive',14),(71,'P','Predictive (Proactive)',14),(72,'M','Multiple',14),(78,'OP','Optimization Problem',15),(79,'TSP','Travelling Salesman Problem',15),(80,'CA','Combinatorial Analysis',15),(81,'BPP','Bin Packing Problem',15),(82,'MKP','Multidimensional Knapsack problem',15),(83,'VMSh','VM Sheduling',15),(84,'VMS','VM selection',15),(85,'QAP','Quadratic Assignment Problem',15),(86,'GPP','Graph Partitioning Problem',15),(87,'CSP','Constraint Satisfaction Problem',15),(88,'DAG-SPP','Directed Acyclic Graph Shortest Path Problem',15),(89,'N/I','Not Identified',16),(90,'N/S','Not Specified',16),(91,NULL,'CurrentVmAvailabiltity desc',16),(92,NULL,'VmBandwidthUsage desc',16),(93,'M','Multiple',16),(94,NULL,'Arrival order',16),(95,NULL,'SumOfResourceUsage desc',16),(96,NULL,'VM group with the higher sum of some resource usage',16),(97,NULL,'Resources Requirements desc',16),(98,NULL,'VM intercommunication traffic desc',16),(99,NULL,'Less loaded VM',16),(100,NULL,'More loaded VM',16),(101,NULL,'Must used resource desc',16),(102,'N/I','Not Identified',18),(103,'N/A','Not Applicable',18),(104,NULL,'minor abs(CurrentCpuUsage - PreDefinedCpuUsageThreshold)',18),(105,'M','Multiple',18),(106,NULL,'PmPowerEfficiency desc',18),(107,NULL,'TurnOffTime closer to JobCompletionTime',18),(108,NULL,'minor remaining resources',18),(109,'max #vm','max number of VMs',18),(110,NULL,'max cpu usage',18),(111,NULL,'Green Data Centers',18),(112,NULL,'Faster transmission rate (vm_ram/bw)',18),(113,NULL,'First PM that supports the VM capacity and extra resources reservation',18),(114,NULL,'Same previous hosted PM',18),(115,NULL,'Same host of parallelized app instances',18),(116,NULL,'Makespan desc',18),(117,NULL,'Intercommunicating VM host',18),(118,NULL,'More balanced resource usage',18),(119,NULL,'Worst Fit (Max Resource Remaining)',18),(120,NULL,'Resource usage closer to the max usage thresould',18),(121,NULL,'Minimize file access time',18),(122,NULL,'Host ID',18),(123,NULL,'Clustering Algorithms',19),(124,NULL,'Graph Partition',19),(125,NULL,'Annealing Algorithm',19),(126,'BFD','Best Fit Decreasing',19),(127,'M3SBP','Max-Min Multidimensional Stochastic Bin Packing',19),(128,NULL,'Greedy Algorithm',19),(129,'LIP','Linear Integer Program',19),(130,'GA','Genetic Algorithm',19),(131,NULL,'Max Matching Problem of Bipartite Graph (VM Placement)',19),(132,NULL,'Markov Chain',19),(133,NULL,'Queuing Theory',19),(134,'ISP','Interval Scheduling Problem',19),(135,NULL,'Fuzzy Logic',19),(136,NULL,'Pareto Efficiency',19),(137,NULL,'Ant Colony System',19),(138,NULL,'Bernoulli Trial',19),(139,NULL,'TOPSIS',19),(140,NULL,'MATLAB',19),(141,NULL,'Solver',19),(142,'SIP','Stochastic Integer Program',19),(143,NULL,'Dijkstra’s algorithm',19),(144,'LA','Linear Algebra',19),(145,'E','Energy',20),(146,'D','Delay',20),(147,'T','Traffic',20),(148,'TP','Throughput',20),(149,NULL,'RAM',20),(150,NULL,'CPU',20),(151,NULL,'I/O',20),(152,'BW','Bandwidth',20),(153,NULL,'Cost',20),(154,'RUS','Resource Usage Sum',20),(155,'S','Storage',20),(156,'ASO','Any Single One',20),(157,'ST','Server Temperature',20),(158,'SLAR','SLA requirements',20),(159,NULL,'Generic',20),(160,'N/S','Not Specified',20),(161,'N/I','Not Identified',21),(162,'D','Dynamic',21),(163,'S','Static',21),(164,'S&D','Static and Dynamic',21),(165,'SS','Semi-static',21),(166,'N/I','Not Identified',22),(167,'CS','Client/Server',22),(168,'DCM','Distributed with Centralized Management',22),(169,'DDM','Distributed with Decentralized Management',22),(170,'C','Centralized',22),(171,'D','Distributed',22),(172,'N/I','Not Identified',23),(173,'AO','Almost Optimal',23),(174,'N/I','Not Identified',24),(175,NULL,'NP-hard',24),(176,NULL,'Multiple',24),(177,'NP-C','NP-complete',24),(178,'P','Polynomial',24);
/*!40000 ALTER TABLE `FieldOption` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FieldType`
--

DROP TABLE IF EXISTS `FieldType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FieldType` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `abbreviation` varchar(2) DEFAULT NULL,
  `description` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ix_FieldTypeDesc` (`description`),
  UNIQUE KEY `ix_FieldTypeAbbrev` (`abbreviation`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FieldType`
--

LOCK TABLES `FieldType` WRITE;
/*!40000 ALTER TABLE `FieldType` DISABLE KEYS */;
INSERT INTO `FieldType` VALUES (1,'O','Objective'),(2,'M','Multiple Choice'),(3,'S','Subjective');
/*!40000 ALTER TABLE `FieldType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Paper`
--

DROP TABLE IF EXISTS `Paper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Paper` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `acceptedOnExtractionPhase` bit(1) NOT NULL,
  `acceptedOnSelectionPhase` bit(1) NOT NULL,
  `authors` varchar(400) NOT NULL,
  `citationKey` varchar(50) NOT NULL,
  `doi` varchar(120) DEFAULT NULL,
  `publicationYear` int(11) NOT NULL,
  `survey` bit(1) NOT NULL,
  `title` varchar(240) NOT NULL,
  `url` varchar(300) DEFAULT NULL,
  `paperType_id` bigint(20) DEFAULT NULL,
  `searchSection_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ix_PaperTitle` (`searchSection_id`,`title`),
  UNIQUE KEY `ix_PaperCitationKey` (`searchSection_id`,`citationKey`),
  KEY `FK_7u2qcmsxhgco373offjfe7njc` (`paperType_id`),
  CONSTRAINT `FK_7u2qcmsxhgco373offjfe7njc` FOREIGN KEY (`paperType_id`) REFERENCES `PaperType` (`id`),
  CONSTRAINT `FK_nqs4kycnvfs948k2x0alhs130` FOREIGN KEY (`searchSection_id`) REFERENCES `SearchSection` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Paper`
--

LOCK TABLES `Paper` WRITE;
/*!40000 ALTER TABLE `Paper` DISABLE KEYS */;
/*!40000 ALTER TABLE `Paper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PaperFieldAnswer`
--

DROP TABLE IF EXISTS `PaperFieldAnswer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PaperFieldAnswer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `subjectiveAnswer` varchar(120) DEFAULT NULL,
  `field_id` int(11) NOT NULL,
  `fieldOption_id` bigint(20) DEFAULT NULL,
  `paper_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ix_PaperFieldAnswerOption` (`paper_id`,`field_id`,`fieldOption_id`),
  UNIQUE KEY `ix_PaperFieldAnswerSubjectAnswer` (`paper_id`,`field_id`,`subjectiveAnswer`),
  KEY `FK_m47ib0wu9msyhqd3v5ncuaduo` (`field_id`),
  KEY `FK_8xlwvrk8tihndn9epybfb77fp` (`fieldOption_id`),
  CONSTRAINT `FK_8xlwvrk8tihndn9epybfb77fp` FOREIGN KEY (`fieldOption_id`) REFERENCES `FieldOption` (`id`),
  CONSTRAINT `FK_m47ib0wu9msyhqd3v5ncuaduo` FOREIGN KEY (`field_id`) REFERENCES `Field` (`id`),
  CONSTRAINT `FK_of08j19jard0x2ly1y5yko0d2` FOREIGN KEY (`paper_id`) REFERENCES `Paper` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PaperFieldAnswer`
--

LOCK TABLES `PaperFieldAnswer` WRITE;
/*!40000 ALTER TABLE `PaperFieldAnswer` DISABLE KEYS */;
/*!40000 ALTER TABLE `PaperFieldAnswer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PaperType`
--

DROP TABLE IF EXISTS `PaperType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PaperType` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `abbreviation` varchar(2) DEFAULT NULL,
  `description` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ix_PaperTypeDesc` (`description`),
  UNIQUE KEY `ix_PaperTypeAbbrev` (`abbreviation`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PaperType`
--

LOCK TABLES `PaperType` WRITE;
/*!40000 ALTER TABLE `PaperType` DISABLE KEYS */;
INSERT INTO `PaperType` VALUES (1,'C','Conference'),(2,'J','Journal'),(3,'M','Master Thesis'),(4,'P','PhD Thesis'),(5,'T','Technical Report');
/*!40000 ALTER TABLE `PaperType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Project`
--

DROP TABLE IF EXISTS `Project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(100) NOT NULL,
  `endUser_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ix_Project` (`description`,`endUser_id`),
  KEY `FK_k9bsjyft5u30r2qd88aqdvepy` (`endUser_id`),
  CONSTRAINT `FK_k9bsjyft5u30r2qd88aqdvepy` FOREIGN KEY (`endUser_id`) REFERENCES `Enduser` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Project`
--

LOCK TABLES `Project` WRITE;
/*!40000 ALTER TABLE `Project` DISABLE KEYS */;
INSERT INTO `Project` VALUES (5,'VM Placement Survey',1);
/*!40000 ALTER TABLE `Project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Repository`
--

DROP TABLE IF EXISTS `Repository`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Repository` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ix_Repository` (`description`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Repository`
--

LOCK TABLES `Repository` WRITE;
/*!40000 ALTER TABLE `Repository` DISABLE KEYS */;
INSERT INTO `Repository` VALUES (2,'ACM'),(1,'IEEE'),(3,'Science Direct'),(4,'Springer');
/*!40000 ALTER TABLE `Repository` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SearchSection`
--

DROP TABLE IF EXISTS `SearchSection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SearchSection` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `searchDate` date NOT NULL,
  `searchString` varchar(200) NOT NULL,
  `project_id` bigint(20) NOT NULL,
  `repository_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cqlhnwhxhftlnnueuac7hb122` (`project_id`),
  KEY `FK_bi55ltuuk0infwje3ksdxvf2u` (`repository_id`),
  CONSTRAINT `FK_bi55ltuuk0infwje3ksdxvf2u` FOREIGN KEY (`repository_id`) REFERENCES `Repository` (`id`),
  CONSTRAINT `FK_cqlhnwhxhftlnnueuac7hb122` FOREIGN KEY (`project_id`) REFERENCES `Project` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SearchSection`
--

LOCK TABLES `SearchSection` WRITE;
/*!40000 ALTER TABLE `SearchSection` DISABLE KEYS */;
/*!40000 ALTER TABLE `SearchSection` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-21  9:56:57
