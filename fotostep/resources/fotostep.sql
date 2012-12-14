-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.28 - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL version:             7.0.0.4053
-- Date/time:                    2012-12-14 09:54:43
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

-- Dumping database structure for fotostep
DROP DATABASE IF EXISTS `fotostep`;
CREATE DATABASE IF NOT EXISTS `fotostep` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `fotostep`;


-- Dumping structure for table fotostep.album
DROP TABLE IF EXISTS `album`;
CREATE TABLE IF NOT EXISTS `album` (
  `idAlbum` int(10) NOT NULL AUTO_INCREMENT,
  `idUser` int(10) NOT NULL,
  `name` varchar(64) NOT NULL,
  `description` varchar(255) NOT NULL,
  `perm` tinyint(10) NOT NULL,
  `idMainImage` int(10) DEFAULT NULL,
  PRIMARY KEY (`idAlbum`),
  KEY `FK_album_user` (`idUser`),
  KEY `FK_album_image` (`idMainImage`),
  CONSTRAINT `FK_album_image` FOREIGN KEY (`idMainImage`) REFERENCES `image` (`idImage`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_album_user` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table fotostep.album: ~0 rows (approximately)
DELETE FROM `album`;
/*!40000 ALTER TABLE `album` DISABLE KEYS */;
/*!40000 ALTER TABLE `album` ENABLE KEYS */;


-- Dumping structure for table fotostep.albumlikes
DROP TABLE IF EXISTS `albumlikes`;
CREATE TABLE IF NOT EXISTS `albumlikes` (
  `idUser` int(10) NOT NULL,
  `idAlbum` int(10) NOT NULL,
  PRIMARY KEY (`idUser`,`idAlbum`),
  KEY `FK__album` (`idAlbum`),
  CONSTRAINT `FK__album` FOREIGN KEY (`idAlbum`) REFERENCES `album` (`idAlbum`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK__user` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table fotostep.albumlikes: ~0 rows (approximately)
DELETE FROM `albumlikes`;
/*!40000 ALTER TABLE `albumlikes` DISABLE KEYS */;
/*!40000 ALTER TABLE `albumlikes` ENABLE KEYS */;


-- Dumping structure for table fotostep.commentalbum
DROP TABLE IF EXISTS `commentalbum`;
CREATE TABLE IF NOT EXISTS `commentalbum` (
  `idComment` int(10) NOT NULL AUTO_INCREMENT,
  `idAlbum` int(10) NOT NULL,
  `idUser` int(10) NOT NULL,
  `title` varchar(255) NOT NULL,
  `body` tinytext NOT NULL,
  `date` int(10) NOT NULL,
  PRIMARY KEY (`idComment`),
  KEY `idAlbum` (`idAlbum`),
  KEY `FK_commentalbum_user` (`idUser`),
  CONSTRAINT `commentalbum_ibfk_2` FOREIGN KEY (`idAlbum`) REFERENCES `album` (`idAlbum`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_commentalbum_user` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table fotostep.commentalbum: ~0 rows (approximately)
DELETE FROM `commentalbum`;
/*!40000 ALTER TABLE `commentalbum` DISABLE KEYS */;
/*!40000 ALTER TABLE `commentalbum` ENABLE KEYS */;


-- Dumping structure for table fotostep.commentimage
DROP TABLE IF EXISTS `commentimage`;
CREATE TABLE IF NOT EXISTS `commentimage` (
  `idComment` int(10) NOT NULL AUTO_INCREMENT,
  `idImage` int(10) NOT NULL,
  `idUser` int(10) NOT NULL,
  `title` varchar(50) NOT NULL,
  `body` tinytext NOT NULL,
  `date` int(10) NOT NULL,
  PRIMARY KEY (`idComment`),
  KEY `idImage` (`idImage`),
  KEY `FK_commentimage_user` (`idUser`),
  CONSTRAINT `commentimage_ibfk_2` FOREIGN KEY (`idImage`) REFERENCES `image` (`idImage`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_commentimage_user` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table fotostep.commentimage: ~0 rows (approximately)
DELETE FROM `commentimage`;
/*!40000 ALTER TABLE `commentimage` DISABLE KEYS */;
/*!40000 ALTER TABLE `commentimage` ENABLE KEYS */;


-- Dumping structure for table fotostep.image
DROP TABLE IF EXISTS `image`;
CREATE TABLE IF NOT EXISTS `image` (
  `idImage` int(10) NOT NULL AUTO_INCREMENT,
  `idAlbum` int(10) NOT NULL DEFAULT '0',
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `format` varchar(10) NOT NULL,
  `height` smallint(6) NOT NULL,
  `width` smallint(6) NOT NULL,
  `path` varchar(50) NOT NULL,
  PRIMARY KEY (`idImage`),
  KEY `FK_image_album` (`idAlbum`),
  CONSTRAINT `FK_image_album` FOREIGN KEY (`idAlbum`) REFERENCES `album` (`idAlbum`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table fotostep.image: ~0 rows (approximately)
DELETE FROM `image`;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
/*!40000 ALTER TABLE `image` ENABLE KEYS */;


-- Dumping structure for table fotostep.imagelikes
DROP TABLE IF EXISTS `imagelikes`;
CREATE TABLE IF NOT EXISTS `imagelikes` (
  `idUser` int(10) NOT NULL,
  `idImage` int(10) NOT NULL,
  PRIMARY KEY (`idUser`,`idImage`),
  KEY `FK_imagelikes_image` (`idImage`),
  CONSTRAINT `FK_imagelikes_image` FOREIGN KEY (`idImage`) REFERENCES `image` (`idImage`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_imagelikes_user` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table fotostep.imagelikes: ~0 rows (approximately)
DELETE FROM `imagelikes`;
/*!40000 ALTER TABLE `imagelikes` DISABLE KEYS */;
/*!40000 ALTER TABLE `imagelikes` ENABLE KEYS */;


-- Dumping structure for table fotostep.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `idUser` int(10) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table fotostep.user: ~0 rows (approximately)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


-- Dumping structure for table fotostep.userdata
DROP TABLE IF EXISTS `userdata`;
CREATE TABLE IF NOT EXISTS `userdata` (
  `idUser` int(10) NOT NULL,
  `firstname` varchar(50) NOT NULL,
  `foreName` varchar(50) NOT NULL,
  `birthDate` date DEFAULT NULL,
  `gender` bit(10) NOT NULL DEFAULT b'0' COMMENT '0 : H / 1 : F',
  PRIMARY KEY (`idUser`),
  CONSTRAINT `userdata_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table fotostep.userdata: ~0 rows (approximately)
DELETE FROM `userdata`;
/*!40000 ALTER TABLE `userdata` DISABLE KEYS */;
/*!40000 ALTER TABLE `userdata` ENABLE KEYS */;


-- Dumping structure for table fotostep.userfriendships
DROP TABLE IF EXISTS `userfriendships`;
CREATE TABLE IF NOT EXISTS `userfriendships` (
  `idUser1` int(10) NOT NULL,
  `idUser2` int(10) NOT NULL,
  PRIMARY KEY (`idUser1`,`idUser2`),
  KEY `idUser2` (`idUser2`),
  CONSTRAINT `userfriendships_ibfk_1` FOREIGN KEY (`idUser1`) REFERENCES `user` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userfriendships_ibfk_2` FOREIGN KEY (`idUser2`) REFERENCES `user` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table fotostep.userfriendships: ~0 rows (approximately)
DELETE FROM `userfriendships`;
/*!40000 ALTER TABLE `userfriendships` DISABLE KEYS */;
/*!40000 ALTER TABLE `userfriendships` ENABLE KEYS */;
/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
