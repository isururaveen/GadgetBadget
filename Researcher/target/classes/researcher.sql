-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 23, 2021 at 07:07 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `researcher`
--

-- --------------------------------------------------------

--
-- Table structure for table `researchers`
--

CREATE TABLE `researchers` (
  `ResearcherID` int(11) NOT NULL,
  `FirstName` varchar(30) NOT NULL,
  `LastName` varchar(30) NOT NULL,
  `Address` varchar(50) NOT NULL,
  `Email` varchar(40) NOT NULL,
  `Phone` varchar(10) NOT NULL,
  `UserName` varchar(40) NOT NULL,
  `Password` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `researchers`
--

INSERT INTO `researchers` (`ResearcherID`, `FirstName`, `LastName`, `Address`, `Email`, `Phone`, `UserName`, `Password`) VALUES
(5, 'chami', 'praboo', 'hettipola', 'chami@gmail.com', '0155234543', 'chAmika', '5555'),
(13, 'Sandaru', 'jayasekara', 'kurunegala', 'sandaru@gmail.com', '0779898909', 'jayasekara', '2w3e'),
(14, 'Isuru', 'Nirash', 'Bowatta', 'Nirash@gmail.com', '0776798981', 'IsuruNirash', 'qw2345'),
(17, 'naveen', 'sadamal', 'padiwela', 'navee@gmail.com', '0789909231', 'navee', '00op'),
(19, 'Imal', 'migara', 'colombo', 'miga@gmail.com', '0787656762', 'migara', '0op3');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `researchers`
--
ALTER TABLE `researchers`
  ADD PRIMARY KEY (`ResearcherID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `researchers`
--
ALTER TABLE `researchers`
  MODIFY `ResearcherID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
