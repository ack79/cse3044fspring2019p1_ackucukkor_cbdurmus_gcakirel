# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.5.5-10.1.34-MariaDB)
# Database: libra
# Generation Time: 2019-05-10 15:09:26 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table advert
# ------------------------------------------------------------

DROP TABLE IF EXISTS `advert`;

CREATE TABLE `advert` (
  `advert_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `book_id` int(11) unsigned NOT NULL,
  `longitude` double NOT NULL,
  `latitude` double NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_active` tinyint(1) NOT NULL DEFAULT '0',
  `advert_desc` text COLLATE utf8_turkish_ci,
  PRIMARY KEY (`advert_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;



# Dump of table book
# ------------------------------------------------------------

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `book_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `book_name` varchar(255) COLLATE utf8_turkish_ci NOT NULL DEFAULT '',
  `book_author` varchar(255) COLLATE utf8_turkish_ci NOT NULL DEFAULT '',
  `book_desc` text COLLATE utf8_turkish_ci,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` int(11) unsigned NOT NULL,
  `img` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  PRIMARY KEY (`book_id`),
  CONSTRAINT `user_has_book` FOREIGN KEY (`book_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;



# Dump of table friend
# ------------------------------------------------------------

DROP TABLE IF EXISTS `friend`;

CREATE TABLE `friend` (
  `second_user_id` int(11) unsigned NOT NULL,
  `first_user_id` int(11) unsigned NOT NULL,
  KEY `user_has_friend` (`second_user_id`),
  CONSTRAINT `user_has_friend` FOREIGN KEY (`second_user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `user_has_friend2` FOREIGN KEY (`second_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

LOCK TABLES `friend` WRITE;
/*!40000 ALTER TABLE `friend` DISABLE KEYS */;

INSERT INTO `friend` (`second_user_id`, `first_user_id`)
VALUES
	(17,1),
	(18,1),
	(18,17),
	(19,17);

/*!40000 ALTER TABLE `friend` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table message
# ------------------------------------------------------------

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `message_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `sender_user_id` int(11) unsigned NOT NULL,
  `advert_id` int(11) unsigned NOT NULL,
  `message_text` text COLLATE utf8_turkish_ci NOT NULL,
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;



# Dump of table post
# ------------------------------------------------------------

DROP TABLE IF EXISTS `post`;

CREATE TABLE `post` (
  `post_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned NOT NULL,
  `post_text` varchar(240) COLLATE utf8_turkish_ci NOT NULL DEFAULT '',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`post_id`),
  CONSTRAINT `user_has_post` FOREIGN KEY (`post_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;

INSERT INTO `post` (`post_id`, `user_id`, `post_text`, `created_date`)
VALUES
	(1,1,'bu kitap çok güzellllllll','2019-05-10 18:08:20');

/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(30) COLLATE utf8_turkish_ci NOT NULL DEFAULT '',
  `password` varchar(16) COLLATE utf8_turkish_ci NOT NULL DEFAULT '',
  `user_bio` varchar(240) COLLATE utf8_turkish_ci DEFAULT '',
  `user_img` varchar(255) COLLATE utf8_turkish_ci DEFAULT NULL,
  `reg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`user_id`, `username`, `password`, `user_bio`, `user_img`, `reg_date`)
VALUES
	(1,'mokokoo','123456',NULL,NULL,'2019-05-10 16:04:29'),
	(17,'ack','adam',NULL,NULL,'2019-05-10 17:25:00'),
	(18,'can berk','adam',NULL,NULL,'2019-05-10 17:25:06'),
	(19,'gözgöz','adam',NULL,NULL,'2019-05-10 17:25:10');

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
