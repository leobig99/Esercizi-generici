-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generato il: 02 mar, 2012 at 07:51 
-- Versione MySQL: 5.5.8
-- Versione PHP: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `dblibrerie`
--
CREATE DATABASE `dblibrerie` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `dblibrerie`;

-- --------------------------------------------------------

--
-- Struttura della tabella `autore`
--

CREATE TABLE IF NOT EXISTS `autore` (
  `CodiceAutore` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Cognome` varchar(50) NOT NULL,
  `Nome` varchar(50) NOT NULL,
  PRIMARY KEY (`CodiceAutore`),
  KEY `Cognome` (`Cognome`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dump dei dati per la tabella `autore`
--

INSERT INTO `autore` (`CodiceAutore`, `Cognome`, `Nome`) VALUES
(1, 'Saviano', 'Roberto'),
(2, 'Grisham', 'John'),
(3, 'Corona', 'Mauro'),
(4, 'Coelho', 'Paulo'),
(5, 'Terzani', 'Tiziano'),
(6, 'Horstmann', 'Cay S.'),
(7, 'Cornell', 'Gary');

-- --------------------------------------------------------

--
-- Struttura della tabella `genere`
--

CREATE TABLE IF NOT EXISTS `genere` (
  `CodiceGenere` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Descrizione` varchar(50) NOT NULL,
  PRIMARY KEY (`CodiceGenere`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dump dei dati per la tabella `genere`
--

INSERT INTO `genere` (`CodiceGenere`, `Descrizione`) VALUES
(1, 'Letteratura Europea'),
(2, 'Letteratura Italiana'),
(3, 'Poesie'),
(4, 'Viaggi'),
(5, 'Gialli'),
(6, 'Romanzo'),
(7, 'Storico'),
(8, 'Informatica');

-- --------------------------------------------------------

--
-- Struttura della tabella `hascritto`
--

CREATE TABLE IF NOT EXISTS `hascritto` (
  `CodiceLibro` int(11) NOT NULL,
  `CodiceAutore` int(11) NOT NULL,
  `ProgressivoAutore` int(11) NOT NULL DEFAULT '1',
  KEY `CodiceLibro` (`CodiceLibro`,`CodiceAutore`),
  KEY `indHaScrittoAutore` (`CodiceAutore`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `hascritto`
--

INSERT INTO `hascritto` (`CodiceLibro`, `CodiceAutore`, `ProgressivoAutore`) VALUES
(1, 1, 1),
(2, 2, 1),
(3, 2, 1),
(4, 2, 1),
(5, 2, 1),
(6, 3, 1),
(7, 3, 1),
(8, 3, 1),
(9, 3, 1),
(10, 5, 1),
(11, 4, 1),
(12, 4, 1),
(13, 6, 1),
(13, 7, 2);

-- --------------------------------------------------------

--
-- Struttura della tabella `libreria`
--

CREATE TABLE IF NOT EXISTS `libreria` (
  `CodiceLibreria` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(50) NOT NULL,
  `Citta` varchar(50) NOT NULL,
  `Via` varchar(20) NOT NULL,
  `Numero` varchar(10) NOT NULL,
  `Provincia` varchar(5) NOT NULL,
  `Telefono` varchar(16) NOT NULL,
  `Email` varchar(50) NOT NULL,
  PRIMARY KEY (`CodiceLibreria`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dump dei dati per la tabella `libreria`
--

INSERT INTO `libreria` (`CodiceLibreria`, `Nome`, `Citta`, `Via`, `Numero`, `Provincia`, `Telefono`, `Email`) VALUES
(1, 'Moderna', 'Mestre', 'Piazza Ferretto', '1', 'Ve', '', ''),
(2, 'Il Libro', 'Favaro Veneto', 'Centrale', '1', 'Ve', '', ''),
(3, 'Feltrinelli', 'Mestre', 'Fapanni', '3', 'Ve', '', '');

-- --------------------------------------------------------

--
-- Struttura della tabella `libro`
--

CREATE TABLE IF NOT EXISTS `libro` (
  `CodiceLibro` int(11) NOT NULL AUTO_INCREMENT,
  `Titolo` varchar(50) NOT NULL,
  `CodiceGenere` int(11) NOT NULL,
  `Editore` varchar(50) NOT NULL,
  `Prezzo` decimal(7,3) NOT NULL,
  PRIMARY KEY (`CodiceLibro`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Dump dei dati per la tabella `libro`
--

INSERT INTO `libro` (`CodiceLibro`, `Titolo`, `CodiceGenere`, `Editore`, `Prezzo`) VALUES
(1, 'Gomorra', 6, 'Mondadori', 21.500),
(2, 'La giuria', 5, 'Mondadori', 8.800),
(3, 'Il Testamento', 5, 'Mondadori', 8.800),
(4, 'Il Cliente', 5, 'Mondadori', 8.800),
(5, 'Il Socio', 5, 'Mondadori', 8.800),
(6, 'Storie di neve', 2, 'Mondadori', 22.000),
(7, 'Le voci del bosco', 2, 'Biblioteca dell''Immagine', 11.000),
(8, 'Finche'' il cuculo canta', 2, 'Biblioteca dell''Immagine', 11.360),
(9, 'I Fantasmi di pietra', 2, 'Mondadori', 17.000),
(10, 'Un indovino mi disse', 4, 'Longanesi', 25.000),
(11, 'L''alchimista', 6, 'Bompiani', 18.000),
(12, 'Il Cammino di Santiago', 6, 'Bompiani', 18.000),
(13, 'Java - Tecniche avanzate', 8, 'Pearson-Printice Hall', 62.000);

-- --------------------------------------------------------

--
-- Struttura della tabella `scorta`
--

CREATE TABLE IF NOT EXISTS `scorta` (
  `CodiceLibro` int(11) NOT NULL,
  `CodiceLibreria` int(11) NOT NULL,
  `Copie` int(11) NOT NULL,
  PRIMARY KEY (`CodiceLibro`,`CodiceLibreria`),
  KEY `indLibreriaScorta` (`CodiceLibreria`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `scorta`
--

INSERT INTO `scorta` (`CodiceLibro`, `CodiceLibreria`, `Copie`) VALUES
(1, 1, 0),
(1, 2, 0),
(1, 3, 20),
(8, 2, 0),
(8, 3, 0),
(10, 1, 1),
(10, 2, 0),
(10, 3, 3),
(12, 1, 2),
(12, 2, 1),
(12, 3, 2),
(13, 1, 0),
(13, 2, 2),
(13, 3, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `vendita`
--

CREATE TABLE IF NOT EXISTS `vendita` (
  `Progressivo` int(11) NOT NULL AUTO_INCREMENT,
  `CodiceLibro` int(11) NOT NULL,
  `CodiceLibreria` int(11) NOT NULL,
  `Copie` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`Progressivo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dump dei dati per la tabella `vendita`
--

INSERT INTO `vendita` (`Progressivo`, `CodiceLibro`, `CodiceLibreria`, `Copie`) VALUES
(1, 13, 3, 6),
(2, 13, 2, 15),
(3, 1, 3, 150),
(4, 1, 1, 220);
