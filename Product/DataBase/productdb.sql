-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 23, 2021 at 07:06 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `productdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `productID` int(11) NOT NULL,
  `productName` varchar(30) NOT NULL,
  `proCategory` varchar(30) NOT NULL,
  `proDesc` varchar(1000) NOT NULL,
  `proPrice` decimal(12,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`productID`, `productName`, `proCategory`, `proDesc`, `proPrice`) VALUES
(14, 'Connected soil monitor', 'Agro technology', 'A small monitor placed in soil that would tell you when a plant needs to be watered. The hardware would be connected to an app on your phone. Using the app, you’d simply set the plant type then place the monitor in the soil. ', '15000'),
(15, 'Smart stoplights', 'electronic', 'Stoplights with cameras on them so that they could intelligently switch from green to red (and vice versa) depending on surrounding traffic and road conditions.', '10000'),
(16, ' Self-watering plants', 'Household', 'Plant pots should have a reservoir of water. You should be able to fill that reservoir up, then set how much of that water you’d like to be automatically dispensed into the plant each day', '20000');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`productID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `productID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
