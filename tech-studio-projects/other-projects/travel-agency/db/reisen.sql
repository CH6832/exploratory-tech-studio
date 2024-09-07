-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 08. Aug 2021 um 20:34
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
-- Datenbank: `reisebuero`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `reisen`
--

CREATE TABLE `reisen` (
  `id` int(20) NOT NULL,
  `bezeichnung` varchar(255) NOT NULL,
  `gesamtpreis` decimal(50,2) NOT NULL,
  `anreisedatum` date NOT NULL,
  `abreisedatum` date NOT NULL,
  `startflughafen` varchar(255) NOT NULL,
  `uebernachtungsort` varchar(255) NOT NULL,
  `hotelname` varchar(255) NOT NULL,
  `reiseart` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `reisen`
--

INSERT INTO `reisen` (`id`, `bezeichnung`, `gesamtpreis`, `anreisedatum`, `abreisedatum`, `startflughafen`, `uebernachtungsort`, `hotelname`, `reiseart`) VALUES
(1, 'Mitsis Norida Beach', '1544.89', '2021-08-18', '2021-08-25', 'Wien', 'Kardamena - Kos', 'Akti Palace Resort & Spa ', 'Pauschalreise'),
(2, 'Östliche Karibik Kreuzfahrt', '1919.75', '2022-01-01', '2022-01-08', 'Frankfurt', 'Costa Diadema', 'Costa Diadema - Innenkabine', 'Kreuzfahrt'),
(3, 'Nilkreuzfahrt mit der M/S Royal La Terrasse***** Superior', '621.00', '2021-09-09', '2021-09-16', 'Berlin', 'M/S Royal La Terrasse*****', 'Innenkabine - M/S Royal La Terrasse*****', 'Kreuzfahrt'),
(4, 'LABRANDA Suites Costa Adeje\r\n', '785.00', '2021-10-01', '2021-10-08', 'Salzburg', 'Playa de Fanabe', 'LABRANDA Suites Costa Adeje\r\n', 'Familienurlaub'),
(5, 'Pauschalreise Spanien - Balearnen', '686.99', '2021-08-15', '2021-08-22', 'München', 'Ibiza', 'The ibiza Twins****', 'Pauschalreise'),
(6, 'Artenschutzreise in das Kavango-Zambezi Naturschutzgebiet', '2489.90', '2021-08-24', '2021-08-31', 'Berlin Tegel', 'Namibia, Botswana und Zimbabwe', 'k.A.', 'Expeditionsreise'),
(7, 'Städtetrip Frankreich, Paris', '4567.00', '2021-10-15', '2021-10-29', 'München', 'Paris', 'Paris Placa Hotel*****', 'Städtereise'),
(8, 'Erlebnisreise Ialien', '2489.99', '2021-08-24', '2021-08-31', 'Wien', 'Sizilien', 'k.A.', 'Pauschalreise');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `reisen`
--
ALTER TABLE `reisen`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `reisen`
--
ALTER TABLE `reisen`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
