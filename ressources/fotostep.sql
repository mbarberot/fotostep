-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Serveur: localhost
-- Généré le : Jeu 06 Décembre 2012 à 12:11
-- Version du serveur: 5.5.8
-- Version de PHP: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


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
  `idAlbum` int(10) NOT NULL AUTO_INCREMENT,
  `idUser` int(10) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `perm` int(10) DEFAULT NULL,
  `coverImage` int(10) DEFAULT NULL,
  PRIMARY KEY (`idAlbum`),
  KEY `fk_user` (`idUser`),
  KEY `coverImage` (`coverImage`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Contenu de la table `album`
--


-- --------------------------------------------------------

--
-- Structure de la table `albumlikes`
--

CREATE TABLE IF NOT EXISTS `albumlikes` (
  `idAlbum` int(10) NOT NULL,
  `idUser` int(10) NOT NULL,
  PRIMARY KEY (`idAlbum`,`idUser`),
  KEY `idUser` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `albumlikes`
--


-- --------------------------------------------------------

--
-- Structure de la table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `idComment` int(10) NOT NULL AUTO_INCREMENT,
  `idUser` int(10) NOT NULL,
  `title` varchar(50) NOT NULL,
  `date` date NOT NULL,
  `body` varchar(255) NOT NULL,
  PRIMARY KEY (`idComment`),
  KEY `idUser` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Contenu de la table `comment`
--


-- --------------------------------------------------------

--
-- Structure de la table `commentalbum`
--

CREATE TABLE IF NOT EXISTS `commentalbum` (
  `idComment` int(10) NOT NULL,
  `idAlbum` int(10) NOT NULL,
  PRIMARY KEY (`idComment`,`idAlbum`),
  KEY `idAlbum` (`idAlbum`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `commentalbum`
--


-- --------------------------------------------------------

--
-- Structure de la table `commentimage`
--

CREATE TABLE IF NOT EXISTS `commentimage` (
  `idComment` int(10) NOT NULL DEFAULT '0',
  `idImage` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idComment`,`idImage`),
  KEY `idImage` (`idImage`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `commentimage`
--


-- --------------------------------------------------------

--
-- Structure de la table `image`
--

CREATE TABLE IF NOT EXISTS `image` (
  `idImage` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) NOT NULL,
  `format` varchar(10) NOT NULL,
  `height` smallint(6) NOT NULL,
  `width` smallint(6) NOT NULL,
  PRIMARY KEY (`idImage`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Contenu de la table `image`
--


-- --------------------------------------------------------

--
-- Structure de la table `imagealbum`
--

CREATE TABLE IF NOT EXISTS `imagealbum` (
  `idImage` int(10) NOT NULL,
  `idAlbum` int(10) NOT NULL,
  PRIMARY KEY (`idImage`,`idAlbum`),
  KEY `idAlbum` (`idAlbum`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `imagealbum`
--


-- --------------------------------------------------------

--
-- Structure de la table `imagelikes`
--

CREATE TABLE IF NOT EXISTS `imagelikes` (
  `idImage` int(10) NOT NULL DEFAULT '0',
  `idUser` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idImage`,`idUser`),
  KEY `idUser` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `imagelikes`
--


-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `idUser` int(10) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `nickname` varchar(50) NOT NULL,
  PRIMARY KEY (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Contenu de la table `user`
--


-- --------------------------------------------------------

--
-- Structure de la table `userdata`
--

CREATE TABLE IF NOT EXISTS `userdata` (
  `idUser` int(10) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(50) NOT NULL,
  `foreName` varchar(50) NOT NULL,
  `birthDate` date NOT NULL,
  `gender` int(10) NOT NULL,
  PRIMARY KEY (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Contenu de la table `userdata`
--


-- --------------------------------------------------------

--
-- Structure de la table `userfriendships`
--

CREATE TABLE IF NOT EXISTS `userfriendships` (
  `idUser1` int(10) NOT NULL DEFAULT '0',
  `idUser2` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idUser1`,`idUser2`),
  KEY `idUser2` (`idUser2`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `userfriendships`
--


--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `album`
--
ALTER TABLE `album`
  ADD CONSTRAINT `album_ibfk_6` FOREIGN KEY (`coverImage`) REFERENCES `image` (`idImage`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `album_ibfk_5` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Contraintes pour la table `albumlikes`
--
ALTER TABLE `albumlikes`
  ADD CONSTRAINT `albumlikes_ibfk_2` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `albumlikes_ibfk_1` FOREIGN KEY (`idAlbum`) REFERENCES `album` (`idAlbum`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Contraintes pour la table `commentalbum`
--
ALTER TABLE `commentalbum`
  ADD CONSTRAINT `commentalbum_ibfk_6` FOREIGN KEY (`idAlbum`) REFERENCES `album` (`idAlbum`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `commentalbum_ibfk_5` FOREIGN KEY (`idComment`) REFERENCES `comment` (`idComment`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `commentimage`
--
ALTER TABLE `commentimage`
  ADD CONSTRAINT `commentimage_ibfk_6` FOREIGN KEY (`idImage`) REFERENCES `image` (`idImage`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `commentimage_ibfk_5` FOREIGN KEY (`idComment`) REFERENCES `comment` (`idComment`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `imagealbum`
--
ALTER TABLE `imagealbum`
  ADD CONSTRAINT `imagealbum_ibfk_4` FOREIGN KEY (`idAlbum`) REFERENCES `album` (`idAlbum`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `imagealbum_ibfk_3` FOREIGN KEY (`idImage`) REFERENCES `image` (`idImage`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `imagelikes`
--
ALTER TABLE `imagelikes`
  ADD CONSTRAINT `imagelikes_ibfk_6` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `imagelikes_ibfk_5` FOREIGN KEY (`idImage`) REFERENCES `image` (`idImage`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `userdata`
--
ALTER TABLE `userdata`
  ADD CONSTRAINT `userdata_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `userfriendships`
--
ALTER TABLE `userfriendships`
  ADD CONSTRAINT `userfriendships_ibfk_6` FOREIGN KEY (`idUser2`) REFERENCES `user` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `userfriendships_ibfk_5` FOREIGN KEY (`idUser1`) REFERENCES `user` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE;
