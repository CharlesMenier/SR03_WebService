-- phpMyAdmin SQL Dump
-- version 4.5.4.1
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Dim 12 Juin 2016 à 21:55
-- Version du serveur :  5.7.11
-- Version de PHP :  5.6.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `sr03_03`
--

-- --------------------------------------------------------

--
-- Structure de la table `adress`
--

CREATE TABLE `adress` (
  `adr_id` int(11) NOT NULL,
  `adr_rue` varchar(128) NOT NULL,
  `adr_ville` varchar(128) NOT NULL,
  `adr_codepostal` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `adress`
--

INSERT INTO `adress` (`adr_id`, `adr_rue`, `adr_ville`, `adr_codepostal`) VALUES
(1, '6 rue exemple', 'compiègne', '60200'),
(2, 'rue', 'ville', '60200'),
(3, 'rue verte', 'paris', '75030'),
(4, 'rue verte', 'paris', '75030'),
(5, 'rue charles ménier', 'paris', '75000'),
(6, 'rue de enfer', 'nice', '60000');

-- --------------------------------------------------------

--
-- Structure de la table `announce`
--

CREATE TABLE `announce` (
  `anc_id` int(11) NOT NULL,
  `anc_name` varchar(128) NOT NULL,
  `anc_categoryId` int(11) NOT NULL,
  `anc_adressId` int(11) NOT NULL,
  `anc_phone` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `announce`
--

INSERT INTO `announce` (`anc_id`, `anc_name`, `anc_categoryId`, `anc_adressId`, `anc_phone`) VALUES
(1, 'zefezf', 4, 3, '66165');

-- --------------------------------------------------------

--
-- Structure de la table `category`
--

CREATE TABLE `category` (
  `ctg_id` int(11) NOT NULL,
  `ctg_name` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `category`
--

INSERT INTO `category` (`ctg_id`, `ctg_name`) VALUES
(4, 'Moto'),
(6, 'Boulon'),
(7, 'Outil');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `adress`
--
ALTER TABLE `adress`
  ADD PRIMARY KEY (`adr_id`);

--
-- Index pour la table `announce`
--
ALTER TABLE `announce`
  ADD PRIMARY KEY (`anc_id`),
  ADD KEY `anc_categoryId` (`anc_categoryId`) USING BTREE,
  ADD KEY `anc_adressId` (`anc_adressId`) USING BTREE;

--
-- Index pour la table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`ctg_id`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `adress`
--
ALTER TABLE `adress`
  MODIFY `adr_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT pour la table `announce`
--
ALTER TABLE `announce`
  MODIFY `anc_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `category`
--
ALTER TABLE `category`
  MODIFY `ctg_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `announce`
--
ALTER TABLE `announce`
  ADD CONSTRAINT `fk_announce_adress` FOREIGN KEY (`anc_adressId`) REFERENCES `adress` (`adr_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_announce_category` FOREIGN KEY (`anc_categoryId`) REFERENCES `category` (`ctg_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
