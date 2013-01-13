-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Serveur: localhost
-- Généré le : Dim 13 Janvier 2013 à 11:36
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

--
-- Contenu de la table `album`
--

INSERT INTO `album` (`idalbum`, `iduser`, `isdefault`, `name`, `description`, `authorization`, `coverimage`, `date`) VALUES
(1, 10, 0, 'Dora chez les ours', 'Un album plein de petits enfants.', 'PUBLIC', 1, '2013-01-11'),
(8, 10, 1, 'Album par défaut', 'Album par défaut où sont stockées vos photos', 'PRIVATE', NULL, '2013-01-11'),
(9, 11, 1, 'Album par défaut', 'Album par défaut où sont stockées vos photos', 'PRIVATE', NULL, '2013-01-11');

--
-- Contenu de la table `commentalbum`
--

INSERT INTO `commentalbum` (`idcommentalbum`, `idalbum`, `iduser`, `body`, `date`) VALUES
(1, 1, 11, 'Hmm j''envie un peu Dora :p', '2013-01-11');

--
-- Contenu de la table `commentpicture`
--

INSERT INTO `commentpicture` (`idcommentpicture`, `idpicture`, `iduser`, `body`, `date`) VALUES
(1, 1, 11, 'Un bien joli ours comme je les aime !', '2013-01-11');

--
-- Contenu de la table `likealbum`
--

INSERT INTO `likealbum` (`iduser`, `idalbum`, `date`) VALUES
(11, 1, '2013-01-13');

--
-- Contenu de la table `likepicture`
--

INSERT INTO `likepicture` (`iduser`, `idpicture`, `date`) VALUES
(11, 1, '2013-01-12');

--
-- Contenu de la table `picture`
--

INSERT INTO `picture` (`idpicture`, `idalbum`, `path`, `description`, `tags`, `width`, `height`, `format`, `coord`, `date`) VALUES
(1, 1, 'http://img.dooyoo.co.uk/GB_EN/orig/0/8/2/9/8/829843.jpg', NULL, NULL, 700, 973, 'jpg', NULL, '2013-01-10');

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`iduser`, `login`, `password`, `enabled`, `registerdate`, `avatar`, `firstname`, `lastname`, `birthdate`, `gender`, `place`, `twitterid`, `fbid`) VALUES
(10, 'john.doe@lambda.com', 'b5e42f49ab3acf8f6c2ccf99f604a17f', 'accepted', '2013-01-11', NULL, 'John', 'Doe', NULL, 'm', NULL, NULL, NULL),
(11, 'jane.doe@lambda.com', '1afe69ac482181b35995b918a77d4d32', 'accepted', '2013-01-11', NULL, 'Jane', 'Doe', NULL, 'f', NULL, NULL, NULL);

--
-- Contenu de la table `userfriendship`
--

INSERT INTO `userfriendship` (`iduser1`, `iduser2`, `date`) VALUES
(10, 11, '2013-01-11'),
(11, 10, '2013-01-12');
