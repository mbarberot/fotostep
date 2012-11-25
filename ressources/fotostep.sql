-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Serveur: localhost
-- Généré le : Dim 25 Novembre 2012 à 15:52
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
  `name` int(10) DEFAULT NULL,
  `description` int(10) DEFAULT NULL,
  `perm` int(10) DEFAULT NULL,
  PRIMARY KEY (`idAlbum`),
  KEY `fk_user` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Contenu de la table `album`
--


-- --------------------------------------------------------

--
-- Structure de la table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `idComment` int(10) NOT NULL AUTO_INCREMENT,
  `idUser` int(10) NOT NULL,
  `title` varchar(50) NOT NULL,
  `date` int(10) NOT NULL,
  `body` int(255) NOT NULL,
  PRIMARY KEY (`idComment`)
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
  PRIMARY KEY (`idComment`,`idAlbum`)
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
  PRIMARY KEY (`idComment`,`idImage`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `commentimage`
--


-- --------------------------------------------------------

--
-- Structure de la table `image`
--

CREATE TABLE IF NOT EXISTS `image` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) NOT NULL,
  `format` varchar(10) NOT NULL,
  `height` smallint(6) NOT NULL,
  `width` smallint(6) NOT NULL,
  PRIMARY KEY (`id`)
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
  PRIMARY KEY (`idImage`,`idAlbum`)
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
  PRIMARY KEY (`idImage`,`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `imagelikes`
--


-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `login` int(10) NOT NULL,
  `password` varchar(50) NOT NULL,
  `nickname` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Contenu de la table `user`
--


-- --------------------------------------------------------

--
-- Structure de la table `userdata`
--

CREATE TABLE IF NOT EXISTS `userdata` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(50) NOT NULL,
  `foreName` varchar(50) NOT NULL,
  `birthDate` int(10) NOT NULL,
  PRIMARY KEY (`id`)
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
  PRIMARY KEY (`idUser1`,`idUser2`)
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
  ADD CONSTRAINT `fk_user` FOREIGN KEY (`idUser`) REFERENCES `user` (`id`);
