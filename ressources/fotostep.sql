-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.28 - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL version:             7.0.0.4053
-- Date/time:                    2012-11-23 15:39:32
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

-- Dumping database structure for fotostep
CREATE DATABASE IF NOT EXISTS `fotostep` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `fotostep`;


-- Dumping structure for table fotostep.album
CREATE TABLE IF NOT EXISTS `album` (
  `idAlbum` int(10) NOT NULL AUTO_INCREMENT,
  `idUser` int(10) DEFAULT NULL,
  `name` int(10) DEFAULT NULL,
  `description` int(10) DEFAULT NULL,
  `perm` int(10) DEFAULT NULL,
  PRIMARY KEY (`idAlbum`),
  KEY `fk_user` (`idUser`),
  CONSTRAINT `fk_user` FOREIGN KEY (`idUser`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table fotostep.comment
CREATE TABLE IF NOT EXISTS `comment` (
  `idComment` int(10) NOT NULL AUTO_INCREMENT,
  `idUser` int(10) NOT NULL,
  `title` varchar(50) NOT NULL,
  `date` int(10) NOT NULL,
  `body` int(255) NOT NULL,
  PRIMARY KEY (`idComment`,`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table fotostep.commentalbum
CREATE TABLE IF NOT EXISTS `commentalbum` (
  `idComment` int(10) NOT NULL,
  `idAlbum` int(10) NOT NULL,
  PRIMARY KEY (`idComment`,`idAlbum`),
  KEY `fk_album` (`idAlbum`),
  CONSTRAINT `fk_comment` FOREIGN KEY (`idComment`) REFERENCES `comment` (`idComment`),
  CONSTRAINT `fk_album` FOREIGN KEY (`idAlbum`) REFERENCES `album` (`idAlbum`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table fotostep.commentimage
CREATE TABLE IF NOT EXISTS `commentimage` (
  `idComment` int(10) DEFAULT NULL,
  `idImage` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table fotostep.image
CREATE TABLE IF NOT EXISTS `image` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) NOT NULL,
  `format` varchar(10) NOT NULL,
  `height` smallint(6) NOT NULL,
  `width` smallint(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table fotostep.imagealbum
CREATE TABLE IF NOT EXISTS `imagealbum` (
  `idImage` int(10) NOT NULL,
  `idAlbum` int(10) NOT NULL,
  PRIMARY KEY (`idImage`,`idAlbum`),
  CONSTRAINT `fk_image` FOREIGN KEY (`idImage`) REFERENCES `image` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table fotostep.imagelikes
CREATE TABLE IF NOT EXISTS `imagelikes` (
  `idImage` int(10) NOT NULL DEFAULT '0',
  `idUser` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idImage`,`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table fotostep.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `login` int(10) NOT NULL,
  `password` varchar(50) NOT NULL,
  `nickname` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table fotostep.userdata
CREATE TABLE IF NOT EXISTS `userdata` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(50) NOT NULL,
  `foreName` varchar(50) NOT NULL,
  `birthDate` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table fotostep.userfriendships
CREATE TABLE IF NOT EXISTS `userfriendships` (
  `idUser1` int(10) DEFAULT NULL,
  `idUser2` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
