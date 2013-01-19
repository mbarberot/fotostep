-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Serveur: localhost
-- Généré le : Ven 18 Janvier 2013 à 16:33
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
(11, 12, 1, 'Album par défaut', 'Album par défaut où sont stockées vos photos', 'PRIVATE', NULL, '2013-01-18'),
(12, 13, 1, 'Album par défaut', 'Album par défaut où sont stockées vos photos', 'PRIVATE', NULL, '2013-01-18'),
(13, 13, 0, 'J''aime les wookies', 'Mon album de wookies, sexy, poilus, viriles et dénudés !', 'FRIENDS', NULL, '2013-01-18');

--
-- Contenu de la table `commentalbum`
--

INSERT INTO `commentalbum` (`idcommentalbum`, `idalbum`, `iduser`, `body`, `date`) VALUES
(2, 13, 12, 'C''est le côté peluche ou le côté viril que tu apprécies, Jane ?', '2013-01-18');

--
-- Contenu de la table `commentpicture`
--

INSERT INTO `commentpicture` (`idcommentpicture`, `idpicture`, `iduser`, `body`, `date`) VALUES
(2, 4, 12, 'Quel magnifique animal !', '2013-01-18');

--
-- Contenu de la table `likealbum`
--

INSERT INTO `likealbum` (`iduser`, `idalbum`, `date`) VALUES
(12, 13, '2013-01-18');

--
-- Contenu de la table `likepicture`
--

INSERT INTO `likepicture` (`iduser`, `idpicture`, `date`) VALUES
(12, 4, '2013-01-18');

--
-- Contenu de la table `picture`
--

INSERT INTO `picture` (`idpicture`, `idalbum`, `path`, `description`, `tags`, `width`, `height`, `format`, `coord`, `date`) VALUES
(4, 13, '/home/kawa/fotosteppictures/user13/Chewbacca_-_SWGTCG_1358521563063', 'Mon premier wookie, il était trop mignon.', 'wookie, star, wars, poilu', 2986, 2391, 'jpg', NULL, '2013-01-18');

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`iduser`, `login`, `password`, `enabled`, `registerdate`, `updatedate`, `avatar`, `firstname`, `lastname`, `birthdate`, `gender`, `place`, `twitterid`, `fbid`) VALUES
(12, 'john.doe@lambda.com', 'b5e42f49ab3acf8f6c2ccf99f604a17f', 'accepted', '2013-01-18', '2013-01-18', 'http://tinyurl.com/b7et3mt', 'John', 'Doe', NULL, 'm', NULL, NULL, NULL),
(13, 'jane.doe@lambda.com', '1afe69ac482181b35995b918a77d4d32', 'accepted', '2013-01-17', '2013-01-18', 'http://tinyurl.com/alc9m72', 'Jane', 'Doe', NULL, 'f', NULL, NULL, NULL);

--
-- Contenu de la table `userfriendship`
--

INSERT INTO `userfriendship` (`iduser1`, `iduser2`, `date`) VALUES
(12, 13, '2013-01-17'),
(13, 12, '2013-01-18');
