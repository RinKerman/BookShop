/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.35-log : Database - bookshop
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bookshop` /*!40100 DEFAULT CHARACTER SET gb2312 */;

USE `bookshop`;

/*Table structure for table `address` */

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `aId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uId` int(11) DEFAULT NULL COMMENT '用户id,外键',
  `address` varchar(80) DEFAULT NULL COMMENT '地址',
  `zipCode` char(6) DEFAULT NULL COMMENT '邮编',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`aId`),
  KEY `uId` (`uId`),
  CONSTRAINT `address_ibfk_1` FOREIGN KEY (`uId`) REFERENCES `user` (`uId`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=gb2312;

/*Data for the table `address` */

insert  into `address`(`aId`,`uId`,`address`,`zipCode`,`note`) values (2,2,'珠海北京理工大学珠海学院','123456',NULL),(3,2,'广东省珠海市香洲区北京理工大学珠海学院','518111',NULL),(4,2,'广东省珠海北理工','123456',NULL),(5,2,'广东省珠海横琴','123456',NULL),(6,2,'广东省广州白云山','1234',NULL),(7,3,'广东省珠海市北京理工大学珠海学院','123456',NULL),(8,10,'广东12','12312',NULL),(9,10,'广东省珠海市香洲区北京师范大学珠海分校','123456',NULL),(10,11,'广东省珠海市香洲区北京理工大学珠海学院','518111',NULL),(11,11,'广东省珠海市香洲区北京师范大学珠海分校','123456',NULL),(12,11,'广东省珠海市香洲区吉林大学珠海学院','658431',NULL),(13,3,'广东省珠海市斗门区都没去111','123456',NULL),(14,3,'广东省珠海市金湾区金湾区11','123456',NULL),(15,3,'北京市北京市东城区北京111','123456',NULL),(16,3,'北京市北京市西城区北京222','12346',NULL);

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `bId` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(60) DEFAULT NULL COMMENT '标题',
  `author` varchar(20) DEFAULT NULL COMMENT '作者',
  `press` varchar(30) DEFAULT NULL COMMENT '出版社',
  `introduction` varchar(1000) DEFAULT NULL COMMENT '简介',
  `addDate` date DEFAULT NULL COMMENT '添加日期',
  `picture` varchar(100) DEFAULT NULL COMMENT '图片相对路径',
  `price` float DEFAULT NULL COMMENT '价格',
  `salesAmount` int(11) DEFAULT NULL COMMENT '销量',
  `stockNumber` int(11) DEFAULT NULL COMMENT '库存',
  `tId` int(11) DEFAULT NULL COMMENT '图书类型,外键',
  `deleteFlag` int(11) DEFAULT '0' COMMENT '删除标志，1表示图书已删除',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`bId`),
  KEY `tid` (`tId`),
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`tId`) REFERENCES `booktype` (`tId`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=gb2312;

/*Data for the table `book` */

insert  into `book`(`bId`,`title`,`author`,`press`,`introduction`,`addDate`,`picture`,`price`,`salesAmount`,`stockNumber`,`tId`,`deleteFlag`,`note`) values (1,'厚黑学全集(足本典藏版)','李宗吾','中国友谊出版公司','李宗吾遍检诸子百家，读破二十四史，目睹人间冷暖，看穿宦海沉浮，终于发现历史的真谛：古之为英雄豪杰者，不过面厚心黑而已。','2017-04-01','pictures/1.jpg',20,11,89,1,0,NULL),(2,'中华人民共和国民法总则释义','李适时','法律出版社','《中华人民共和国民法总则》已由十二届全国人大五次会议通过，自2017年10月1日起施行。为了更好地宣传民法总则，使社会各界对民法总则内容有全面、准确的了解，保证民法总则的顺利实施，全国人大常委会法制工作委员会民法室的同志编写了这本《〈中华人民共和国民法总则〉释义》。本书对《中华人民共和国民法总则》进行了逐条释义，力求简明扼要、通俗易懂，便于准确把握立法原意。','2017-04-02','pictures/7.jpg',55,6,95,2,0,NULL),(3,'毛泽东年谱','中共中央文献研究室','中央文献出版社','这部年谱记述了一代伟人毛泽东一九四九年至一九七六的生平、经历和实践活动，反映了他对中国革命的丰功伟绩，特别是多侧面多角度地体现了他的科学思想体系，包括他的理论观点、战略思想、政策和策略以及关于思想方法和工作方法的论述等，展示了他的思想发展轨迹，反映了他作为中国共产党第一代领导集体的核心把马克思主义的普遍原理同中国革命的具体实践相结合的具体过程，并尽可能地表现他的胸怀、情操、气度和风貌。这部年谱从一个极其重要的方面反映了中国共产党领导新民主主义革命走过的艰难曲折的道路和光辉历程，走到取得全国胜利。','2017-04-03','pictures/2.jpg',273,3,99,3,0,NULL),(4,'淘宝、天猫电商运营百科全书','刘涛','电子工业出版社','学会推广：直通车，钻展，DMP圈定精准用户','2017-04-04','pictures/5.jpg',45,6,97,5,0,NULL),(5,'思维导图阅读法','胡雅茹','北京时代化文书局','我们每天都阅读很多信息，可是，你抓得到重点、记得牢这些信息吗？','2017-04-05','pictures/3.jpg',24.1,7,97,5,0,NULL),(6,'唐诗鉴赏辞典','俞平伯','上海辞书出版社','《唐诗鉴赏辞典》是国内文艺类鉴赏辞典的发轫之作。它首创的融文学赏析和工具书于一体的编写体例，已成为中国文学鉴赏辞典的固有模式。《唐诗鉴赏辞典》共收唐代190多位诗人诗作1100余篇，由萧涤非、程千帆、马茂元、周汝昌、周振甫、霍松林等古典文学专家撰写赏析文章。所收唐诗作品面广，各种艺术流派的诗篇兼收并蓄，较全面地展现了唐诗绚丽多彩的艺术风姿。全书约230万字。另配有唐代诗人年表、唐诗书目、诗体诗律等多种附录，是文学爱好者的首选读物。新一版对全书的版面、版式、字体、字号、封面进行重新设计，使版面更加赏心悦目。','2017-04-06','pictures/4.jpg',73.5,7,98,7,0,NULL),(7,'音乐表情术语字典','张宁和、罗吉兰','人民音乐出版社','《音乐表情术语字典》内容简介：我们演唱，演奏的根据，是乐谱。乐谱传达作家的乐思，有两方面：音符和表情术语。当作曲家创作的时候，他一方面把音符记在纸上，一方面也把音符所不能说明的，而他自己希望用某种表情、速度、音色来表演这首乐曲的想法，用文字写在乐潜上。','2017-04-07','pictures/6.jpg',7.9,21,85,7,0,NULL),(8,'尼采的启示','赵广明','社会科学文献出版社','尼采立足哲学，旨在一种未来哲学和基于未来哲学的信仰；尼采的哲学和宗教信仰皆在道德之中，一种超越善恶的道德，通过价值重估和创造所实现的道德。而尼采的哲学、宗教和道德，皆归于精神。“精神（derGeist）”概念是《查拉图斯特拉如是说》的开始，是《善与恶的彼岸》的终结，是尼采始终在悉心塑造的生命形象。精神是生命的根本和全部，意味着生命的状态和力量，精神的意义在于精神的自我创造与变形。','2017-05-07','pictures/nicai.jpg',46,5,97,1,0,NULL),(9,'中西文学与哲学宗教','高旭东','北京大学出版社','本书的上编和下编都设立了文学与哲学，文学与宗教的个案研究，上编以鲁迅与卡夫卡、加缪的关系，展开了文学与哲学的个案研究，下编以鲁迅与佛教，基督教的以及林语堂、巴金与基督教的关系，展开了文学与宗教的个案研究。','2017-05-07','pictures/zhongxi.jpg',14,4,48,1,0,NULL),(10,' 王阳明全集','王阳明','辽海出版社','《王阳明全集》是研究王阳明心学思想及王阳明一生最重要的著作，是儒家思想中**个性、**争议的代表作，同时也是一部现代人成功修身、强大个人内心的励志作品。','2017-05-04','pictures/wangyangming.jpg',59,4,7,1,1,NULL),(11,'周易全集','xx','北京大学出版社','周易全书/易经占卦解卦教学/全注全译图解易传八卦风水学快速入门哲学宗教/预测学/易传六爻算卦家居','2017-04-02','pictures/zhouyi.jpg',200,1,20,1,0,'11'),(12,'中国人缺少什么','周国平','上海人民出版社','三十年磨一剑的学术著作，周国平深度解读中国对西方哲学的引入，为当代中国人的生存现状开出一剂良药。 ','2017-05-02','pictures/zhouguoping.jpg',44,5,20,1,1,NULL),(13,'最美的生命1','丹真绒布','陕西师范大学出版社','丹真绒布讲述藏地不为人知的故事，带给你温暖、智慧和力量。 感恩所有美好，做一个欢喜的人。','2017-05-01','pictures/zuimeideshengming.jpg',29,5,10,1,0,NULL),(14,'最美的生命2','丹真绒布','陕西师范大学出版社','丹真绒布讲述藏地不为人知的故事，带给你温暖、智慧和力量。 感恩所有美好，做一个欢喜的人。','2017-05-01','pictures/zuimeideshengming.jpg',29,6,9,1,0,NULL),(15,'最美的生命3','丹真绒布','陕西师范大学出版社','丹真绒布讲述藏地不为人知的故事，带给你温暖、智慧和力量。 感恩所有美好，做一个欢喜的人。','2017-05-01','pictures/zuimeideshengming.jpg',29,5,10,1,0,NULL),(16,'最美的生命4','丹真绒布','陕西师范大学出版社','丹真绒布讲述藏地不为人知的故事，带给你温暖、智慧和力量。 感恩所有美好，做一个欢喜的人。','2017-05-01','pictures/zuimeideshengming.jpg',29,5,10,1,0,NULL),(17,'最美的生命5','丹真绒布','陕西师范大学出版社','丹真绒布讲述藏地不为人知的故事，带给你温暖、智慧和力量。 感恩所有美好，做一个欢喜的人。','2017-05-01','pictures/zuimeideshengming.jpg',29,5,10,1,0,NULL),(18,'美的历程','李泽厚','三联出版社','《美的历程》是中国美学的经典之作。凝聚了作者李泽厚先生多年研究。他把中国人古往今来对美的感觉玲珑剔透地展现在大家眼前，如斯感性，如斯亲切。其中提出了诸如原始远古艺术的“龙飞凤舞”，殷周青铜器艺术的“狞厉的美”，先秦理性精神的“儒道互补”，楚辞、汉赋、汉画像石之“浪漫主义”，“人的觉醒”的魏晋风度，六朝、唐、宁佛像雕塑，宋元山水绘画以及诗、词、曲各具审美三品类，明清时期小说、戏曲由浪漫而感伤而现实之变迁等等重要观念。','2017-02-05','pictures/meidelicheng.jpg',35,5,20,1,0,NULL);

/*Table structure for table `booktype` */

DROP TABLE IF EXISTS `booktype`;

CREATE TABLE `booktype` (
  `tId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `bType` varchar(40) DEFAULT NULL COMMENT '类型',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`tId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=gb2312;

/*Data for the table `booktype` */

insert  into `booktype`(`tId`,`bType`,`note`) values (1,'哲学宗教',NULL),(2,'政治法律',NULL),(3,'军事',NULL),(4,'经济',NULL),(5,'文化教育',NULL),(6,'文学',NULL),(7,'艺术',NULL),(8,'科教',NULL);

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `cid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` int(11) DEFAULT NULL COMMENT '用户id',
  `bid` int(11) DEFAULT NULL COMMENT '图书id',
  `message` varchar(500) DEFAULT NULL COMMENT '留言内容',
  PRIMARY KEY (`cid`),
  KEY `uid` (`uid`),
  KEY `bid` (`bid`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`uId`),
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`bid`) REFERENCES `book` (`bId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

/*Data for the table `comment` */

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `oId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `shipAddress` varchar(80) DEFAULT NULL COMMENT '收货地址',
  `receive` varchar(20) DEFAULT NULL COMMENT '收件人姓名',
  `orderDate` datetime DEFAULT NULL COMMENT '下单时间',
  `uId` int(11) DEFAULT NULL COMMENT '用户id',
  `osId` int(11) DEFAULT NULL COMMENT '订单状态',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`oId`),
  KEY `uId` (`uId`),
  KEY `osId` (`osId`),
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`uId`) REFERENCES `user` (`uId`),
  CONSTRAINT `order_ibfk_2` FOREIGN KEY (`osId`) REFERENCES `orderstate` (`osId`)
) ENGINE=InnoDB AUTO_INCREMENT=239 DEFAULT CHARSET=gb2312;

/*Data for the table `order` */

insert  into `order`(`oId`,`shipAddress`,`receive`,`orderDate`,`uId`,`osId`,`note`) values (235,'\n											广东省珠海市北京理工大学珠海学院\n										','\n											用户2\n				','2017-06-06 14:01:25',3,3,NULL),(238,NULL,NULL,'2017-06-11 00:31:39',3,1,NULL);

/*Table structure for table `orderdetail` */

DROP TABLE IF EXISTS `orderdetail`;

CREATE TABLE `orderdetail` (
  `odId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `oId` int(11) DEFAULT NULL COMMENT '订单id',
  `bId` int(11) DEFAULT NULL COMMENT '图书id',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  `bookPrice` float DEFAULT '0' COMMENT '购买当时的图书价格',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`odId`),
  KEY `uId` (`oId`),
  KEY `bId` (`bId`),
  CONSTRAINT `orderdetail_ibfk_2` FOREIGN KEY (`bId`) REFERENCES `book` (`bId`),
  CONSTRAINT `orderdetail_ibfk_3` FOREIGN KEY (`oId`) REFERENCES `order` (`oId`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=266 DEFAULT CHARSET=gb2312;

/*Data for the table `orderdetail` */

insert  into `orderdetail`(`odId`,`oId`,`bId`,`quantity`,`bookPrice`,`note`) values (259,235,8,1,46,NULL),(265,238,1,1,20,NULL);

/*Table structure for table `orderstate` */

DROP TABLE IF EXISTS `orderstate`;

CREATE TABLE `orderstate` (
  `osId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `orderState` varchar(20) DEFAULT NULL COMMENT '订单状态 未支付 已支付 已配送',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`osId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=gb2312;

/*Data for the table `orderstate` */

insert  into `orderstate`(`osId`,`orderState`,`note`) values (1,'未支付',NULL),(2,'已支付',NULL),(3,'已签收',NULL);

/*Table structure for table `recommend` */

DROP TABLE IF EXISTS `recommend`;

CREATE TABLE `recommend` (
  `rid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `bid` int(11) DEFAULT NULL COMMENT '图书id',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`rid`),
  KEY `bid` (`bid`),
  CONSTRAINT `recommend_ibfk_1` FOREIGN KEY (`bid`) REFERENCES `book` (`bId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=gb2312;

/*Data for the table `recommend` */

insert  into `recommend`(`rid`,`bid`,`note`) values (1,1,NULL),(2,2,NULL),(3,3,NULL),(4,4,NULL),(5,5,NULL),(6,6,NULL);

/*Table structure for table `shoppingcart` */

DROP TABLE IF EXISTS `shoppingcart`;

CREATE TABLE `shoppingcart` (
  `sId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uId` int(11) DEFAULT NULL COMMENT '用户id',
  `bId` int(11) DEFAULT NULL COMMENT '图书id',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`sId`),
  KEY `uId` (`uId`),
  KEY `bId` (`bId`),
  CONSTRAINT `shoppingcart_ibfk_1` FOREIGN KEY (`uId`) REFERENCES `user` (`uId`),
  CONSTRAINT `shoppingcart_ibfk_2` FOREIGN KEY (`bId`) REFERENCES `book` (`bId`)
) ENGINE=InnoDB AUTO_INCREMENT=294 DEFAULT CHARSET=gb2312;

/*Data for the table `shoppingcart` */

insert  into `shoppingcart`(`sId`,`uId`,`bId`,`quantity`,`note`) values (222,1,4,1,NULL),(248,2,10,1,NULL),(251,2,9,1,NULL),(264,10,8,1,NULL),(275,11,8,1,NULL),(276,11,9,2,NULL),(277,11,4,1,NULL),(278,11,1,1,NULL),(279,11,2,1,NULL),(284,3,12,1,NULL);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `uId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userName` varchar(12) DEFAULT NULL COMMENT '用户名 6-12',
  `password` varchar(20) DEFAULT NULL COMMENT '密码 6-20',
  `balance` float NOT NULL DEFAULT '1000' COMMENT '用户余额',
  `name` varchar(20) DEFAULT NULL COMMENT '真实姓名',
  `cellphone` varchar(11) DEFAULT NULL COMMENT '手机',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `email` varchar(20) DEFAULT NULL COMMENT '邮箱',
  `utId` int(11) DEFAULT NULL COMMENT '用户编号',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`uId`),
  KEY `utId` (`utId`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`utId`) REFERENCES `usertype` (`utId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=gb2312;

/*Data for the table `user` */

insert  into `user`(`uId`,`userName`,`password`,`balance`,`name`,`cellphone`,`birthday`,`sex`,`email`,`utId`,`note`) values (1,'admin','admin',100,NULL,NULL,NULL,NULL,NULL,1,NULL),(2,'user1','user',10000,'张三','13160679585',NULL,NULL,'user@qq.com',2,NULL),(3,'user2','user',9731,'用户2','13714702581','2017-06-05','男','user2@qq.com',2,NULL),(4,'user3','user',0,NULL,NULL,NULL,NULL,NULL,2,NULL),(5,'张三','user',0,NULL,NULL,NULL,NULL,NULL,2,NULL),(6,'李四','user',0,NULL,NULL,NULL,NULL,NULL,2,NULL),(7,'王五','user',0,NULL,NULL,NULL,NULL,NULL,2,NULL),(8,'赵六','user',0,NULL,NULL,NULL,NULL,NULL,2,NULL),(9,'小七','user',0,NULL,NULL,NULL,NULL,NULL,2,NULL),(10,'user4','user',76.3,'ni','312321313',NULL,NULL,NULL,2,NULL),(11,'user5','user',883.1,'小吴','12345678945',NULL,NULL,NULL,2,NULL);

/*Table structure for table `usertype` */

DROP TABLE IF EXISTS `usertype`;

CREATE TABLE `usertype` (
  `utId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uType` varchar(20) DEFAULT NULL COMMENT '用户类型',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`utId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gb2312;

/*Data for the table `usertype` */

insert  into `usertype`(`utId`,`uType`,`note`) values (1,'管理员',NULL),(2,'顾客',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
