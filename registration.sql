-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 13, 2019 at 08:39 AM
-- Server version: 10.1.9-MariaDB
-- PHP Version: 7.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `client`
--

-- --------------------------------------------------------

--
-- Table structure for table `registration`
--

CREATE TABLE `registration` (
  `name` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `date` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `phone` int(50) NOT NULL,
  `gender` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `registration`
--

INSERT INTO `registration` (`name`, `address`, `date`, `email`, `username`, `password`, `phone`, `gender`) VALUES
('rojan', 'ktm', '01-01-1948', 'abc@gmail.com', 'Username:', 'Password: ', 999999999, 'Male'),
('rs', 'birgunj', '01-01-1948', 'rs@abc.com', 'Username:', 'Password: ', 9999999, 'Male'),
('abc', 'abc', '01-01-1948', 'jnajdnajsd', 'Username', 'Password ', 999999, 'Male'),
('new', 'ktm', '01-01-1948', 'new', 'new', 'new', 99999, 'Male'),
('nabin', 'Kalanki', '01-01-1953', 'n@asdasd', 'nabin', 'nabin', 999999, 'Male'),
('ranjit', 'kathmandu', '01-01-1948', 'rakfjs', 'ran', 'ran', 99999, 'Male'),
('sabin', 'ktm', '01-01-1948', 'sabin@gmail.com', 'sabin123', 'sabin123', 9999, 'Male'),
('binish', 'kalimati', '01-01-1948', 'binish@gmail.com', 'binish', 'binish', 9999999, 'Male');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
