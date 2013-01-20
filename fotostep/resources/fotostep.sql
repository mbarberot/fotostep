-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Sam 19 Janvier 2013 à 23:18
-- Version du serveur: 5.5.24-log
-- Version de PHP: 5.3.13

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=12 ;

--
-- Contenu de la table `album`
--

INSERT INTO `album` (`idalbum`, `iduser`, `isdefault`, `name`, `description`, `authorization`, `coverimage`, `date`) VALUES
(11, 12, 1, 'Album par défaut', 'Album par défaut où sont stockées vos photos', 'PRIVATE', NULL, '2013-01-19');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `picture`
--

INSERT INTO `picture` (`idpicture`, `idalbum`, `path`, `description`, `tags`, `width`, `height`, `format`, `lgt`, `lat`, `date`) VALUES
(4, 11, '\\fotosteppictures\\user12\\_DSC0111_1358617675987', 'Pas de description', '', 3872, 2592, 'jpg', 0, 0, '2013-01-19');

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
  `avatar` varchar(100) DEFAULT NULL,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `birthdate` date DEFAULT NULL,
  `gender` enum('m','f') NOT NULL,
  `place` varchar(45) DEFAULT NULL,
  `twitterid` varchar(45) DEFAULT NULL COMMENT 'Le nom d''utilisateur twitter',
  `fbid` varchar(45) DEFAULT NULL COMMENT 'Le nom d''utilisateur facebook',
  PRIMARY KEY (`iduser`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=13 ;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`iduser`, `login`, `password`, `enabled`, `registerdate`, `updatedate`, `avatar`, `firstname`, `lastname`, `birthdate`, `gender`, `place`, `twitterid`, `fbid`) VALUES
(12, 'joanracenet@gmail.com', '998322b81d0ac4549ebe7f9e39ba2f05', 'accepted', '2013-01-19', '2013-01-19', NULL, 'Joan', 'Racenet', NULL, 'm', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `userfriendship`
--

CREATE TABLE IF NOT EXISTS `userfriendship` (
  `iduser1` int(11) NOT NULL,
  `iduser2` int(11) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`iduser1`,`iduser2`),
  KEY `fk_user1_idx` (`iduser1`),
  KEY `fk_user2_idx` (`iduser2`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `album`
--
ALTER TABLE `album`
  ADD CONSTRAINT `fk_album_cover_picture` FOREIGN KEY (`coverimage`) REFERENCES `picture` (`idpicture`) ON DELETE SET NULL ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_album_user` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `commentalbum`
--
ALTER TABLE `commentalbum`
  ADD CONSTRAINT `fk_commentalbum_album` FOREIGN KEY (`idalbum`) REFERENCES `album` (`idalbum`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_commentalbum_user` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `commentpicture`
--
ALTER TABLE `commentpicture`
  ADD CONSTRAINT `fk_commentpicture_picture` FOREIGN KEY (`idpicture`) REFERENCES `picture` (`idpicture`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_commentpicture_user` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `likealbum`
--
ALTER TABLE `likealbum`
  ADD CONSTRAINT `fk_likealbum_album` FOREIGN KEY (`idalbum`) REFERENCES `album` (`idalbum`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_likealbum_user` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `likepicture`
--
ALTER TABLE `likepicture`
  ADD CONSTRAINT `fk_like_picture_picture` FOREIGN KEY (`idpicture`) REFERENCES `picture` (`idpicture`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_like_picture_user` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `picture`
--
ALTER TABLE `picture`
  ADD CONSTRAINT `fk_picture_album` FOREIGN KEY (`idalbum`) REFERENCES `album` (`idalbum`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `userfriendship`
--
ALTER TABLE `userfriendship`
  ADD CONSTRAINT `fk_friendships_user1` FOREIGN KEY (`iduser1`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_friendships_user2` FOREIGN KEY (`iduser2`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
