-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Client: 127.0.0.1
-- Généré le: Ven 25 Janvier 2013 à 23:53
-- Version du serveur: 5.5.27
-- Version de PHP: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `fotostep`
--

-- --------------------------------------------------------

--
-- Structure de la table `album`
--

CREATE TABLE IF NOT EXISTS `album` (
  `idalbum` int(11) NOT NULL AUTO_INCREMENT,
  `iduser` int(11) NOT NULL,
  `isdefault` tinyint(1) NOT NULL DEFAULT '0' COMMENT '1 si album par défaut de l''utilisateur, 0 sinon',
  `name` varchar(45) NOT NULL,
  `description` text,
  `authorization` enum('PUBLIC','FRIENDS','PRIVATE') NOT NULL DEFAULT 'PRIVATE',
  `coverimage` int(11) DEFAULT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`idalbum`),
  KEY `fk_user_idx` (`iduser`),
  KEY `fk_cover_image_idx` (`coverimage`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=17 ;

-- --------------------------------------------------------

--
-- Structure de la table `commentalbum`
--

CREATE TABLE IF NOT EXISTS `commentalbum` (
  `idcommentalbum` int(11) NOT NULL AUTO_INCREMENT,
  `idalbum` int(11) NOT NULL,
  `iduser` int(11) NOT NULL,
  `body` text NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`idcommentalbum`),
  KEY `fk_user_idx` (`iduser`),
  KEY `fk_album_idx` (`idalbum`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

-- --------------------------------------------------------

--
-- Structure de la table `commentpicture`
--

CREATE TABLE IF NOT EXISTS `commentpicture` (
  `idcommentpicture` int(11) NOT NULL AUTO_INCREMENT,
  `idpicture` int(11) NOT NULL,
  `iduser` int(11) NOT NULL,
  `body` text NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`idcommentpicture`),
  KEY `fk_user_idx` (`iduser`),
  KEY `fk_picture_idx` (`idpicture`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

-- --------------------------------------------------------

--
-- Structure de la table `friendship`
--

CREATE TABLE IF NOT EXISTS `friendship` (
  `user` int(11) NOT NULL,
  `friend` int(11) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`user`,`friend`),
  KEY `friend` (`friend`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `likealbum`
--

CREATE TABLE IF NOT EXISTS `likealbum` (
  `iduser` int(11) NOT NULL,
  `idalbum` int(11) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`iduser`,`idalbum`),
  KEY `fk_user_idx` (`iduser`),
  KEY `fk_album_idx` (`idalbum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `likepicture`
--

CREATE TABLE IF NOT EXISTS `likepicture` (
  `iduser` int(11) NOT NULL,
  `idpicture` int(11) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`iduser`,`idpicture`),
  KEY `fk_user_idx` (`iduser`),
  KEY `fk_picture_idx` (`idpicture`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `pendingfriendship`
--

CREATE TABLE IF NOT EXISTS `pendingfriendship` (
  `user` int(11) NOT NULL,
  `friend` int(11) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`user`,`friend`),
  KEY `friend` (`friend`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `picture`
--

CREATE TABLE IF NOT EXISTS `picture` (
  `idpicture` int(11) NOT NULL AUTO_INCREMENT,
  `idalbum` int(11) NOT NULL,
  `path` varchar(255) NOT NULL,
  `description` text,
  `tags` text,
  `width` int(11) NOT NULL,
  `height` int(11) NOT NULL,
  `format` enum('jpg','png') NOT NULL,
  `lgt` double NOT NULL DEFAULT '0' COMMENT 'Longitude',
  `lat` double NOT NULL DEFAULT '0' COMMENT 'Latitude',
  `date` date NOT NULL,
  PRIMARY KEY (`idpicture`),
  UNIQUE KEY `path_UNIQUE` (`path`),
  KEY `fk_album_idx` (`idalbum`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=18 ;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `password` varchar(32) NOT NULL,
  `enabled` enum('pending','accepted','deleted','banned') DEFAULT 'accepted',
  `registerdate` date NOT NULL,
  `updatedate` date NOT NULL,
  `avatar` int(11) DEFAULT NULL,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `birthdate` date DEFAULT NULL,
  `gender` enum('m','f') NOT NULL,
  `place` varchar(45) DEFAULT NULL,
  `twitterid` varchar(45) DEFAULT NULL COMMENT 'Le nom d''utilisateur twitter',
  `fbid` varchar(45) DEFAULT NULL COMMENT 'Le nom d''utilisateur facebook',
  PRIMARY KEY (`iduser`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  KEY `avatar` (`avatar`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=15 ;

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `album`
--
ALTER TABLE `album`
  ADD CONSTRAINT `album_ibfk_2` FOREIGN KEY (`coverimage`) REFERENCES `picture` (`idpicture`) ON DELETE SET NULL ON UPDATE SET NULL,
  ADD CONSTRAINT `album_ibfk_1` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `commentalbum`
--
ALTER TABLE `commentalbum`
  ADD CONSTRAINT `commentalbum_ibfk_2` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `commentalbum_ibfk_1` FOREIGN KEY (`idalbum`) REFERENCES `album` (`idalbum`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `commentpicture`
--
ALTER TABLE `commentpicture`
  ADD CONSTRAINT `commentpicture_ibfk_2` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `commentpicture_ibfk_1` FOREIGN KEY (`idpicture`) REFERENCES `picture` (`idpicture`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `friendship`
--
ALTER TABLE `friendship`
  ADD CONSTRAINT `friendship_ibfk_1` FOREIGN KEY (`user`) REFERENCES `user` (`iduser`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `friendship_ibfk_2` FOREIGN KEY (`friend`) REFERENCES `user` (`iduser`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `likealbum`
--
ALTER TABLE `likealbum`
  ADD CONSTRAINT `likealbum_ibfk_2` FOREIGN KEY (`idalbum`) REFERENCES `album` (`idalbum`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `likealbum_ibfk_1` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `likepicture`
--
ALTER TABLE `likepicture`
  ADD CONSTRAINT `likepicture_ibfk_2` FOREIGN KEY (`idpicture`) REFERENCES `picture` (`idpicture`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `likepicture_ibfk_1` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `pendingfriendship`
--
ALTER TABLE `pendingfriendship`
  ADD CONSTRAINT `pendingfriendship_ibfk_1` FOREIGN KEY (`user`) REFERENCES `user` (`iduser`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pendingfriendship_ibfk_2` FOREIGN KEY (`friend`) REFERENCES `user` (`iduser`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `picture`
--
ALTER TABLE `picture`
  ADD CONSTRAINT `picture_ibfk_1` FOREIGN KEY (`idalbum`) REFERENCES `album` (`idalbum`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_2` FOREIGN KEY (`avatar`) REFERENCES `picture` (`idpicture`) ON DELETE SET NULL ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
