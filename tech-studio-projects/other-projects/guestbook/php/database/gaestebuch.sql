-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 25. Sep 2021 um 17:34
-- Server-Version: 10.4.19-MariaDB
-- PHP-Version: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `gaestebuch`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `kommentare`
--

CREATE TABLE `kommentare` (
  `id` int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `vorname` varchar(255) NOT NULL,
  `nachname` varchar(255) NOT NULL,
  `kommentar` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `kommentare`
--

INSERT INTO `kommentare` (`id`, `date`, `vorname`, `nachname`, `kommentar`) VALUES
(91, '2021-08-14 11:09:42', 'Lara', 'Musterfrau ', ''),
(92, '2021-08-14 11:10:08', 'Lara', 'Musterfrau ', ''),
(93, '2021-08-14 11:10:42', 'Lara', 'Musterman', 'lorem ipsum das ist ein Kommentar. Das ist auch eine zweit ezEIle hier. as ist eine dritte Zeile hier und das ist ein vierte hier und noch viel viel mehr.'),
(94, '2021-08-14 11:11:39', 'Lara', 'Musterman', 'lorem ipsum das ist ein Kommentar. Das ist auch eine zweit ezEIle hier. as ist eine dritte Zeile hier und das ist ein vierte hier und noch viel viel mehr.');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `kommentare`
--
ALTER TABLE `kommentare`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `kommentare`
--
ALTER TABLE `kommentare`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=95;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
