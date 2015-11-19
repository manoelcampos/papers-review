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
INSERT INTO `Field` VALUES (10,'Exp','Experiments',2,5),(11,'Tech','Technologies',2,5),(12,'OSS','Open Source',1,5),(13,'Conc','Concerns',2,5),(14,'Proact','Proactiveness',1,5),(15,'Prob','Problem Classification',2,5),(16,'VMSO','VM Selection Order',1,5),(18,'DHS','Destionation Host Selection',2,5),(19,'Imp','Implementation',2,5),(20,'Params','Parameters',2,5),(21,'VMAD','VM Allocation Dynamics',1,5),(22,'Arch','Architecture',1,5),(23,'Sol','Solution',1,5),(24,'AC','Algorithm Complexity',1,5);
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
) ENGINE=InnoDB AUTO_INCREMENT=184 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FieldOption`
--

LOCK TABLES `FieldOption` WRITE;
/*!40000 ALTER TABLE `FieldOption` DISABLE KEYS */;
INSERT INTO `FieldOption` VALUES (17,'Sim','Simulation',10),(18,'TB','Testbed',10),(19,'Trace','Real Trace Analysis',10),(20,'Math','Mathematical Analysis',10),(21,'Bench','Benchmark Testbeds',10),(22,'OSK','OpenStack',11),(23,'CS','CloudSim',11),(24,'CSC','CloudSched',11),(25,'CIS','Custom Implemented Simulator',11),(26,'NS2','Network Simulator 2',11),(27,'NS3','Network Simulator 3',11),(28,'PS','PeerSim',11),(29,'TPC-H','Transaction Processing Performance Council Benchmark (Query per Hour)',11),(30,'XCP','Xen Cloud Platform',11),(31,'NPB','NAS Parallel Benchmarks',11),(32,'N/I','Not Identified',12),(33,'Y','Yes',12),(34,'N','No',12),(35,'N/A','Not Applicable',12),(36,'Alg','Algorithms Presented',12),(37,'SLAM','SLA Managment',13),(38,'VMS','VM Selection (what VMs migrate)',13),(39,'VMP','VM Placement',13),(40,'VMMig','VM Migration',13),(41,'VMSh','VM Shuffle',13),(42,'VMRP','VM Resource Provisioning',13),(43,'SLAV','SLA Violations',13),(44,'QoS','Quality of Service',13),(45,'AV','Availability',13),(46,'IaaS','IaaS',13),(47,'PaaS','PaaS',13),(48,'SaaS','SaaS',13),(49,'$','Pricing',13),(50,'SC','Server Consolidation',13),(51,'-EC','Reduce Energy Consumption',13),(52,'-M','Reduce number of VM migrations',13),(53,'ASLAV','Avoid SLA violation',13),(54,'-NET','Reduce Network Traffic',13),(55,'-VMT','Reduce VM migration time',13),(56,'-DOWN','Reduce Service Downtime',13),(57,'LB','Load Balancing',13),(58,'+RU','Maximize Resource Usage',13),(59,'-CO2','Carbon Reduction',13),(60,'-$','Costs Reduction',13),(61,'BP','Burstiness Prediction',13),(62,'VMR','VM Reconfiguration',13),(63,'-VMMC','Reduce VM migration cost/overhead',13),(64,'-MS','Minimize Makespan',13),(65,'-T','Minimize Temperature',13),(66,'-FAT','Minimize File Access Time',13),(67,'FT','Fault Tolerance',13),(69,'N/I','Not Identified',14),(70,'R','Reactive',14),(71,'P','Predictive (Proactive)',14),(72,'M','Multiple',14),(78,'OP','Optimization Problem',15),(79,'TSP','Travelling Salesman Problem',15),(80,'CA','Combinatorial Analysis',15),(81,'BPP','Bin Packing Problem',15),(82,'MKP','Multidimensional Knapsack problem',15),(83,'VMSh','VM Sheduling',15),(84,'VMS','VM Selection (what VMs migrate)',15),(85,'QAP','Quadratic Assignment Problem',15),(86,'GPP','Graph Partitioning Problem',15),(87,'CSP','Constraint Satisfaction Problem',15),(88,'DAG-SPP','Directed Acyclic Graph Shortest Path Problem',15),(89,'N/I','Not Identified',16),(90,'N/S','Not Specified',16),(91,NULL,'CurrentVmAvailabiltity desc',16),(92,NULL,'VmBandwidthUsage desc',16),(93,'M','Multiple',16),(94,NULL,'Arrival order',16),(95,NULL,'SumOfResourceUsage desc',16),(96,NULL,'VM group with the higher sum of some resource usage',16),(97,NULL,'Resources Requirements desc',16),(98,NULL,'VM intercommunication traffic desc',16),(99,NULL,'Less loaded VM',16),(100,NULL,'More loaded VM',16),(101,NULL,'Must used resource desc',16),(102,'N/I','Not Identified',18),(103,'N/A','Not Applicable',18),(104,NULL,'minor abs(CurrentCpuUsage - PreDefinedCpuUsageThreshold)',18),(105,'M','Multiple',18),(106,NULL,'PmPowerEfficiency desc',18),(107,NULL,'TurnOffTime closer to JobCompletionTime',18),(108,NULL,'minor remaining resources',18),(109,'max #vm','max number of VMs',18),(110,NULL,'max cpu usage',18),(111,NULL,'Green Data Centers',18),(112,NULL,'Faster transmission rate (vm_ram/bw)',18),(113,NULL,'First PM that supports the VM capacity and extra resources reservation',18),(114,NULL,'Same previous hosted PM',18),(115,NULL,'Same host of parallelized app instances',18),(116,NULL,'Makespan desc',18),(117,NULL,'Intercommunicating VM host',18),(118,NULL,'More balanced resource usage',18),(119,NULL,'Worst Fit (Max Resource Remaining)',18),(120,NULL,'Resource usage closer to the max usage thresould',18),(121,NULL,'Minimize file access time',18),(122,NULL,'Host ID',18),(123,NULL,'Clustering Algorithms',19),(124,NULL,'Graph Partition',19),(125,NULL,'Annealing Algorithm',19),(126,'BFD','Best Fit Decreasing',19),(127,'M3SBP','Max-Min Multidimensional Stochastic Bin Packing',19),(128,NULL,'Greedy Algorithm',19),(129,'LIP','Linear Integer Program',19),(130,'GA','Genetic Algorithm',19),(131,NULL,'Max Matching Problem of Bipartite Graph (VM Placement)',19),(132,NULL,'Markov Chain',19),(133,NULL,'Queuing Theory',19),(134,'ISP','Interval Scheduling Problem',19),(135,NULL,'Fuzzy Logic',19),(136,NULL,'Pareto Efficiency',19),(137,NULL,'Ant Colony System',19),(138,NULL,'Bernoulli Trial',19),(139,NULL,'TOPSIS',19),(140,NULL,'MATLAB',19),(141,NULL,'Solver',19),(142,'SIP','Stochastic Integer Program',19),(143,NULL,'Dijkstra’s algorithm',19),(144,'LA','Linear Algebra',19),(145,'E','Energy',20),(146,'D','Delay',20),(147,'T','Traffic',20),(148,'TP','Throughput',20),(149,NULL,'RAM',20),(150,NULL,'CPU',20),(151,'I/O','I/O',20),(152,'BW','Bandwidth',20),(153,NULL,'Cost',20),(154,'RUS','Resource Usage',20),(155,'S','Storage',20),(156,'ASO','Any Single One',20),(157,'ST','Server Temperature',20),(158,'SLAR','SLA requirements',20),(159,NULL,'Generic',20),(160,'N/S','Not Specified',20),(161,'N/I','Not Identified',21),(162,'D','Dynamic',21),(163,'S','Static',21),(164,'S&D','Static and Dynamic',21),(165,'SS','Semi-static',21),(166,'N/I','Not Identified',22),(167,'CS','Client/Server',22),(168,'DCM','Distributed with Centralized Management',22),(169,'DDM','Distributed with Decentralized Management',22),(170,'C','Centralized',22),(171,'D','Distributed',22),(172,'N/I','Not Identified',23),(173,'AO','Almost Optimal',23),(174,'N/I','Not Identified',24),(175,NULL,'NP-hard',24),(176,NULL,'Multiple',24),(177,'NP-C','NP-complete',24),(178,'P','Polynomial',24),(179,'ES','Exponential Smoothing (Time Series Analysis)',19),(180,'MRU','Minor resource usage',16),(182,'DHS','Destination Host Selection',13),(183,'TMVM','Time to Migrate VMs',13);
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
) ENGINE=InnoDB AUTO_INCREMENT=155 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Paper`
--

LOCK TABLES `Paper` WRITE;
/*!40000 ALTER TABLE `Paper` DISABLE KEYS */;
INSERT INTO `Paper` VALUES (106,'','','Mohamed Amine Kaaouache and Sadok Bouamama','Kaaouache20151061','http://dx.doi.org/10.1016/j.procs.2015.08.151',2015,'\0','Solving bin Packing Problem with a Hybrid Genetic Algorithm for VM Placement in Cloud','http://www.sciencedirect.com/science/article/pii/S1877050915022784',1,7),(107,'','','Raja Wasim Ahmad and Abdullah Gani and Siti Hafizah Ab. Hamid and Muhammad Shiraz and Abdullah Yousafzai and Feng Xia','Ahmad2015','http://dx.doi.org/10.1016/j.jnca.2015.02.002',2015,'','A survey on virtual machine migration and server consolidation frameworks for cloud data centers ','http://www.sciencedirect.com/science/article/pii/S1084804515000284',2,7),(108,'','','Subhadra Bose Shaw and Anil Kumar Singh','Shaw2015','http://dx.doi.org/10.1016/j.compeleceng.2015.07.020',2015,'\0','Use of proactive and reactive hotspot detection technique to reduce the number of virtual machine migration and energy consumption in cloud data center ','http://www.sciencedirect.com/science/article/pii/S0045790615002748',2,7),(109,'','','Yongqiang Gao and Haibing Guan and Zhengwei Qi and Yang Hou and Liang Liu','Gao2013','http://dx.doi.org/10.1016/j.jcss.2013.02.004',2013,'\0','A multi-objective ant colony system algorithm for virtual machine placement in cloud computing ','http://www.sciencedirect.com/science/article/pii/S0022000013000627',1,7),(110,'\0','','Vincenzo De Maio and Radu Prodan and Shajulin Benedict and Gabor Kecskemeti','DeMaio2015','http://dx.doi.org/10.1016/j.future.2015.07.007',2015,'\0','Modelling energy consumption of network transfers and virtual machine migration ','http://www.sciencedirect.com/science/article/pii/S0167739X15002307',1,7),(111,'\0','\0','Shihong Zou and Xitao Wen and Kai Chen and Shan Huang and Yan Chen and Yongqiang Liu and Yong Xia and Chengchen Hu','Zou2014141','http://dx.doi.org/10.1016/j.comnet.2014.03.025',2014,'\0','VirtualKnotter: Online virtual machine shuffling for congestion resolving in virtualized datacenter ','http://www.sciencedirect.com/science/article/pii/S138912861400139X',NULL,7),(112,'\0','\0','Yangming Zhao and Yifan Huang and Kai Chen and Minlan Yu and Sheng Wang and DongSheng Li','Zhao2015109','http://dx.doi.org/10.1016/j.comnet.2014.12.014',2015,'\0','Joint VM placement and topology optimization for traffic scalability in dynamic datacenter networks ','http://www.sciencedirect.com/science/article/pii/S138912861400468X',NULL,7),(113,'\0','\0','Weiwei Fang and Xiangmin Liang and Shengxin Li and Luca Chiaraviglio and Naixue Xiong','Fang2013179','http://dx.doi.org/10.1016/j.comnet.2012.09.008',2013,'\0','VMPlanner: Optimizing virtual machine placement and traffic flow routing to reduce network power costs in cloud data centers ','http://www.sciencedirect.com/science/article/pii/S1389128612003301',NULL,7),(114,'\0','\0','Jian-kang DONG and Hong-bo WANG and Yang-yang LI and Shi-duan CHENG','DONG201462','http://dx.doi.org/10.1016/S1005-8885(14)60302-2',2014,'\0','Virtual machine placement optimizing to improve network performance in cloud data centers ','http://www.sciencedirect.com/science/article/pii/S1005888514603022',NULL,7),(115,'\0','\0','Renuga Kanagavelu and Bu-Sung Lee and Nguyen The Dat Le and Luke Ng Mingjie and Khin Mi Mi Aung','Kanagavelu20141','http://dx.doi.org/10.1016/j.comcom.2014.07.009',2014,'\0','Virtual machine placement with two-path traffic routing for reduced congestion in data center networks ','http://www.sciencedirect.com/science/article/pii/S0140366414002746',NULL,7),(116,'\0','\0','Hai Jin and Li Deng and Song Wu and Xuanhua Shi and Hanhua Chen and Xiaodong Pan','Jin201423','http://dx.doi.org/10.1016/j.future.2013.09.031',2014,'\0','MECOM: Live migration of virtual machines by adaptively compressing memory pages ','http://www.sciencedirect.com/science/article/pii/S0167739X13002100',NULL,7),(117,'\0','\0','Xin Li and Zhuzhong Qian and Sanglu Lu and Jie Wu','Li20131222','http://dx.doi.org/10.1016/j.mcm.2013.02.003',2013,'\0','Energy efficient virtual machine placement algorithm with balanced and improved resource utilization in a data center ','http://www.sciencedirect.com/science/article/pii/S0895717713000319',NULL,7),(118,'\0','\0','Zia ur Rehman and Omar Khadeer Hussain and Elizabeth Chang and Tharam Dillon','Rehman2015','http://dx.doi.org/10.1016/j.elerap.2015.08.002',2015,'\0','Decision-making framework for user-based inter-cloud service migration ','http://www.sciencedirect.com/science/article/pii/S1567422315000575',NULL,7),(119,'\0','\0','Qinghua Zheng and Rui Li and Xiuqi Li and Nazaraf Shah and Jianke Zhang and Feng Tian and Kuo-Ming Chao and Jia Li','Zheng2015','http://dx.doi.org/10.1016/j.future.2015.02.010',2015,'\0','Virtual machine consolidated placement based on multi-objective biogeography-based optimization ','http://www.sciencedirect.com/science/article/pii/S0167739X15000564',NULL,7),(120,'\0','\0','Deshi Ye and Jianhai Chen','Ye20131345','http://dx.doi.org/10.1016/j.future.2013.02.004',2013,'\0','Non-cooperative games on multidimensional resource allocation ','http://www.sciencedirect.com/science/article/pii/S0167739X13000320',NULL,7),(121,'\0','\0','S. Sohrabi and I. Moser','Sohrabi20152794','http://dx.doi.org/10.1016/j.procs.2015.05.436',2015,'\0','The Effects of Hotspot Detection and Virtual Machine Migration Policies on Energy Consumption and Service Levels in the Cloud ','http://www.sciencedirect.com/science/article/pii/S1877050915012442',NULL,7),(122,'\0','\0','Jenn-Wei Lin and Chien-Hung Chen and Chi-Yi Lin','Lin2014478','http://dx.doi.org/10.1016/j.future.2013.12.034',2014,'\0','Integrating QoS awareness with virtualization in cloud computing systems for delay-sensitive applications ','http://www.sciencedirect.com/science/article/pii/S0167739X13002987',NULL,7),(123,'\0','\0','Amir Rahimzadeh Ilkhechi and Ibrahim Korpeoglu and Özgür Ulusoy','Ilkhechi2015508','http://dx.doi.org/10.1016/j.comnet.2015.08.042',2015,'\0','Network-aware virtual machine placement in cloud data centers with multiple traffic-intensive components ','http://www.sciencedirect.com/science/article/pii/S1389128615003023',NULL,7),(124,'\0','\0','Saad Mustafa and Babar Nazir and Amir Hayat and Atta ur Rehman Khan and Sajjad A. Madani','Mustafa2015','http://dx.doi.org/10.1016/j.compeleceng.2015.07.021',2015,'\0','Resource management in cloud computing: Taxonomy, prospects, and challenges ','http://www.sciencedirect.com/science/article/pii/S004579061500275X',NULL,7),(125,'\0','\0','Bane Raman Raghunath and B. Annappa','Raghunath2015167','http://dx.doi.org/10.1016/j.procs.2015.06.019',2015,'\0','Virtual Machine Migration Triggering using Application Workload Prediction ','http://www.sciencedirect.com/science/article/pii/S1877050915013435',NULL,7),(126,'\0','\0','Xiaoying Wang and Zhihui Du and Yinong Chen and Mengqin Yang','Wang2015','http://dx.doi.org/10.1016/j.simpat.2015.01.005',2015,'\0','A green-aware virtual machine migration strategy for sustainable datacenter powered by renewable energy ','http://www.sciencedirect.com/science/article/pii/S1569190X15000155',NULL,7),(127,'\0','\0','Sujesha Sudevalayam and Purushottam Kulkarni','Sudevalayam20132627','http://dx.doi.org/10.1016/j.jss.2013.04.085',2013,'\0','Affinity-aware modeling of CPU usage with communicating virtual machines ','http://www.sciencedirect.com/science/article/pii/S0164121213001246',NULL,7),(128,'\0','\0','Fumio Machida and Dong Seong Kim and Kishor S. Trivedi','Machida2013212','http://dx.doi.org/10.1016/j.peva.2012.09.003',2013,'\0','Modeling and analysis of software rejuvenation in a server virtualized system with live VM migration ','http://www.sciencedirect.com/science/article/pii/S0166531612000934',NULL,7),(129,'\0','\0','Juliano Araujo Wickboldt and Rafael Pereira Esteves and Márcio Barbosa de Carvalho and Lisandro Zambenedetti Granville','Wickboldt201454','http://dx.doi.org/10.1016/j.comnet.2014.02.018',2014,'\0','Resource management in IaaS cloud platforms made flexible through programmability ','http://www.sciencedirect.com/science/article/pii/S138912861400084X',NULL,7),(130,'\0','\0','Mohan Raj Velayudhan Kumar and Shriram Raghunathan','VelayudhanKumar2015','http://dx.doi.org/10.1016/j.jcss.2015.07.005',2015,'\0','Heterogeneity and thermal aware adaptive heuristics for energy efficient consolidation of virtual machines in infrastructure clouds ','http://www.sciencedirect.com/science/article/pii/S002200001500080X',NULL,7),(131,'\0','\0','Aissan Dalvandi and Mohan Gurusamy and Kee Chaing Chua','Dalvandi2015249','http://dx.doi.org/10.1016/j.comnet.2015.06.017',2015,'\0','Power-efficient resource-guaranteed VM placement and routing for time-aware data center applications ','http://www.sciencedirect.com/science/article/pii/S1389128615002182',NULL,7),(132,'\0','\0','Narander Kumar and Swati Saxena','Kumar2015823','http://dx.doi.org/10.1016/j.procs.2015.03.163',2015,'\0','Migration Performance of Cloud Applications- A Quantitative Analysis ','http://www.sciencedirect.com/science/article/pii/S1877050915003993',NULL,7),(133,'\0','\0','Gang Sun and Dan Liao and Vishal Anand and Dongcheng Zhao and Hongfang Yu','Sun201674','http://dx.doi.org/10.1016/j.future.2015.09.005',2016,'\0','A new technique for efficient live migration of multiple virtual machines ','http://www.sciencedirect.com/science/article/pii/S0167739X15002848',NULL,7),(134,'\0','\0','Jun-jie TONG and Hai-hong E and Mei-na SONG and Jun-de SONG','TONG201440','http://dx.doi.org/10.1016/S1005-8885(14)60314-9',2014,'\0','Host load prediction in cloud based on classification methods ','http://www.sciencedirect.com/science/article/pii/S1005888514603149',NULL,7),(135,'\0','\0','Hai Jin and Wei Gao and Song Wu and Xuanhua Shi and Xiaoxin Wu and Fan Zhou','Jin20111088','http://dx.doi.org/10.1016/j.jnca.2010.06.013',2011,'\0','Optimizing the live migration of virtual machine by CPU scheduling ','http://www.sciencedirect.com/science/article/pii/S1084804510001116',NULL,7),(136,'\0','\0','Saurabh Kumar Garg and Adel Nadjaran Toosi and Srinivasa K. Gopalaiyengar and Rajkumar Buyya','Garg2014108','http://dx.doi.org/10.1016/j.jnca.2014.07.030',2014,'\0','SLA-based virtual machine management for heterogeneous workloads in a cloud datacenter ','http://www.sciencedirect.com/science/article/pii/S1084804514001787',NULL,7),(137,'\0','\0','L. Velasco and A. Asensio and J.Ll. Berral and E. Bonetto and F. Musumeci and V. López','Velasco2014142','http://dx.doi.org/10.1016/j.comcom.2014.03.004',2014,'\0','Elastic operations in federated datacenters for performance and cost optimization ','http://www.sciencedirect.com/science/article/pii/S0140366414000905',NULL,7),(138,'\0','\0','Francesco Palmieri and Ugo Fiore and Sergio Ricciardi and Aniello Castiglione','Palmieri2015','http://dx.doi.org/10.1016/j.future.2015.01.017',2015,'\0','GRASP-based resource re-optimization for effective big data access in federated clouds ','http://www.sciencedirect.com/science/article/pii/S0167739X15000345',NULL,7),(139,'\0','\0','Lu Lu and Xuanhua Shi and Hai Jin and Qiuyue Wang and Daxing Yuan and Song Wu','Lu201480','http://dx.doi.org/10.1016/j.future.2013.12.026',2014,'\0','Morpho: A decoupled MapReduce framework for elastic cloud computing ','http://www.sciencedirect.com/science/article/pii/S0167739X13002902',NULL,7),(140,'\0','\0','Gregory Katsaros and Josep Subirats and J. Oriol Fitó and Jordi Guitart and Pierre Gilet and Daniel Espling','Katsaros20132077','http://dx.doi.org/10.1016/j.future.2012.12.006',2013,'\0','A service framework for energy-aware monitoring and VM management in Clouds ','http://www.sciencedirect.com/science/article/pii/S0167739X12002269',NULL,7),(141,'\0','\0','Nikos Tziritas and Samee Ullah Khan and Cheng-Zhong Xu and Thanasis Loukopoulos and Spyros Lalis','Tziritas20131690','http://dx.doi.org/10.1016/j.jpdc.2013.07.020',2013,'\0','On minimizing the resource consumption of cloud applications using process migrations ','http://www.sciencedirect.com/science/article/pii/S0743731513001585',NULL,7),(142,'\0','\0','Tiago C. Ferreto and Marco A.S. Netto and Rodrigo N. Calheiros and César A.F. De Rose','Ferreto20111027','http://dx.doi.org/10.1016/j.future.2011.04.016',2011,'\0','Server consolidation with migration control for virtualized data centers ','http://www.sciencedirect.com/science/article/pii/S0167739X11000677',NULL,7),(143,'\0','\0','Xiaolin Xu and Hai Jin and Song Wu and Yihong Wang','Xu201575','http://dx.doi.org/10.1016/j.future.2014.10.004',2015,'\0','Rethink the storage of virtual machine images in clouds ','http://www.sciencedirect.com/science/article/pii/S0167739X14001885',NULL,7),(144,'\0','\0','Josep Subirats and Jordi Guitart','Subirats201570','http://dx.doi.org/10.1016/j.future.2014.11.008',2015,'\0','Assessing and forecasting energy efficiency on Cloud computing platforms ','http://www.sciencedirect.com/science/article/pii/S0167739X14002428',NULL,7),(145,'\0','\0','Gregory Katsaros and Pascal Stichler and Josep Subirats and Jordi Guitart','Katsaros2015','http://dx.doi.org/10.1016/j.future.2015.01.002',2015,'\0','Estimation and forecasting of ecological efficiency of virtual machines ','http://www.sciencedirect.com/science/article/pii/S0167739X15000035',NULL,7),(146,'\0','\0','Corentin Dupont and Fabien Hermenier and Thomas Schulze and Robert Basmadjian and Andrey Somov and Giovanni Giuliani','Dupont2015505','http://dx.doi.org/10.1016/j.adhoc.2014.11.003',2015,'\0','Plug4Green: A flexible energy-aware VM manager to fit data centre particularities ','http://www.sciencedirect.com/science/article/pii/S1570870514002376',NULL,7),(147,'\0','\0','Andreas Wolke and Boldbaatar Tsend-Ayush and Carl Pfeiffer and Martin Bichler','Wolke201583','http://dx.doi.org/10.1016/j.is.2015.03.003',2015,'\0','More than bin packing: Dynamic resource allocation strategies in cloud data centers ','http://www.sciencedirect.com/science/article/pii/S0306437915000472',NULL,7),(148,'\0','\0','Mario Macías and Jordi Guitart','Macías201419','http://dx.doi.org/10.1016/j.future.2014.03.004',2014,'\0','SLA negotiation and enforcement policies for revenue maximization and client classification in cloud providers ','http://www.sciencedirect.com/science/article/pii/S0167739X14000491',NULL,7),(149,'\0','\0','Boris Teabe and Alain Tchana and Daniel Hagimont','Teabe20151','http://dx.doi.org/10.1016/j.future.2015.05.013',2015,'\0','Enforcing CPU allocation in a heterogeneous IaaS ','http://www.sciencedirect.com/science/article/pii/S0167739X15001880',NULL,7),(150,'\0','\0','Wei Lin Guay and Sven-Arne Reinemo and Bjørn Dag Johnsen and Chien-Hua Yen and Tor Skeie and Olav Lysne and Ola Tørudbakken','Guay201539','http://dx.doi.org/10.1016/j.jpdc.2015.01.004',2015,'\0','Early experiences with live migration of SR-IOV enabled InfiniBand ','http://www.sciencedirect.com/science/article/pii/S0743731515000052',NULL,7),(151,'\0','\0','Yaozu Dong and Xiaowei Yang and Jianhui Li and Guangdeng Liao and Kun Tian and Haibing Guan','Dong20121471','http://dx.doi.org/10.1016/j.jpdc.2012.01.020',2012,'\0','High performance network virtualization with SR-IOV ','http://www.sciencedirect.com/science/article/pii/S0743731512000329',NULL,7),(152,'\0','\0','W. Lloyd and S. Pallickara and O. David and J. Lyon and M. Arabi and K. Rojas','Lloyd20131254','http://dx.doi.org/10.1016/j.future.2012.12.007',2013,'\0','Performance implications of multi-tier application deployments on Infrastructure-as-a-Service clouds: Towards performance modeling ','http://www.sciencedirect.com/science/article/pii/S0167739X12002270',NULL,7),(153,'\0','\0','Gursharan Singh and Sunny Behal and Monal Taneja','Singh201591','http://dx.doi.org/10.1016/j.procs.2015.07.373',2015,'\0','Advanced Memory Reusing Mechanism for Virtual Machines in Cloud Computing ','http://www.sciencedirect.com/science/article/pii/S187705091501902X',NULL,7),(154,'\0','\0','R. Jeyarani and N. Nagaveni and R. Vasanth Ram','Jeyarani2012811','http://dx.doi.org/10.1016/j.future.2011.06.002',2012,'\0','Design and implementation of adaptive power-aware virtual machine provisioner (APA-VMP) using swarm intelligence ','http://www.sciencedirect.com/science/article/pii/S0167739X11001130',NULL,7);
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
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PaperFieldAnswer`
--

LOCK TABLES `PaperFieldAnswer` WRITE;
/*!40000 ALTER TABLE `PaperFieldAnswer` DISABLE KEYS */;
INSERT INTO `PaperFieldAnswer` VALUES (63,NULL,15,81,106),(64,NULL,19,130,106),(65,NULL,19,126,106),(66,NULL,13,39,106),(67,NULL,12,36,106),(68,NULL,13,46,106),(69,NULL,13,51,106),(70,NULL,13,50,106),(71,NULL,13,60,106),(72,NULL,10,17,106),(73,NULL,20,156,106),(74,NULL,22,170,106),(75,NULL,23,173,106),(76,NULL,24,174,106),(77,NULL,12,35,107),(78,NULL,13,46,107),(79,NULL,13,50,107),(80,NULL,13,40,107),(81,NULL,10,19,108),(82,NULL,10,17,108),(83,NULL,11,23,108),(84,NULL,13,53,108),(85,NULL,13,46,108),(86,NULL,13,51,108),(87,NULL,13,52,108),(88,NULL,13,50,108),(89,NULL,14,72,108),(90,NULL,21,162,108),(91,NULL,19,179,108),(92,NULL,20,150,108),(93,NULL,16,180,108),(94,NULL,12,36,108),(95,NULL,13,43,108),(96,NULL,13,40,108),(97,NULL,22,170,108),(98,NULL,23,173,108),(99,NULL,24,174,108),(100,NULL,13,182,108),(101,NULL,13,183,108),(102,NULL,13,58,109),(103,NULL,13,51,109),(104,NULL,13,39,109),(105,NULL,19,137,109),(106,NULL,19,136,109),(107,NULL,23,173,109),(108,NULL,15,80,109),(109,NULL,15,78,109),(112,NULL,13,46,109),(113,NULL,13,50,109),(114,NULL,20,150,109),(115,NULL,20,149,109),(116,NULL,10,17,109),(117,NULL,14,70,109),(118,NULL,16,94,109),(119,NULL,21,163,109),(120,NULL,22,168,109);
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SearchSection`
--

LOCK TABLES `SearchSection` WRITE;
/*!40000 ALTER TABLE `SearchSection` DISABLE KEYS */;
INSERT INTO `SearchSection` VALUES (7,'2015-10-21','pub-date > 2007 and TITLE-ABSTR-KEY({VM Migration} or {Virtual Machine Migration}) or TITLE-ABSTR-KEY({VM Placement} or {Virtual Machine Placement}) 	#Only on journals (that include conference papers)',5,3);
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

-- Dump completed on 2015-10-22 20:49:32
