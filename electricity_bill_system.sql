-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 20, 2025 at 05:31 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `electricity_bill_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE `bill` (
  `meterNo` varchar(20) DEFAULT NULL,
  `month` varchar(20) DEFAULT NULL,
  `unit` varchar(20) DEFAULT NULL,
  `total_bill` varchar(20) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bill`
--

INSERT INTO `bill` (`meterNo`, `month`, `unit`, `total_bill`, `status`) VALUES
('341855', 'January', '12', '266', 'Not Paid'),
('341855', 'February', '22', '366', 'Not Paid'),
('978472', 'March', '70', '846', 'Not Paid');

-- --------------------------------------------------------

--
-- Table structure for table `meter_info`
--

CREATE TABLE `meter_info` (
  `meter_number` varchar(30) DEFAULT NULL,
  `meter_location` varchar(30) DEFAULT NULL,
  `meter_type` varchar(30) DEFAULT NULL,
  `phase_code` varchar(30) DEFAULT NULL,
  `bill_type` varchar(30) DEFAULT NULL,
  `days` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `meter_info`
--

INSERT INTO `meter_info` (`meter_number`, `meter_location`, `meter_type`, `phase_code`, `bill_type`, `days`) VALUES
('341855', 'Outside', 'Electric Meter', '001', 'Normal', '30'),
('978472', 'Outside', 'Solar Meter', '002', 'Industrial', '30');

-- --------------------------------------------------------

--
-- Table structure for table `new_customer`
--

CREATE TABLE `new_customer` (
  `name` varchar(30) DEFAULT NULL,
  `meterNo` varchar(20) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `state` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `phoneNo` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `new_customer`
--

INSERT INTO `new_customer` (`name`, `meterNo`, `address`, `city`, `state`, `email`, `phoneNo`) VALUES
('ishan', '341855', 'text', 'text', 'text', 'text@gmail.com', 'text'),
('ishan2', '978472', 'text2', 'text2', 'text2', 'text2@gmail.com', 'text2');

-- --------------------------------------------------------

--
-- Table structure for table `signup`
--

CREATE TABLE `signup` (
  `meter_no` varchar(20) DEFAULT NULL,
  `userName` varchar(20) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `userType` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `signup`
--

INSERT INTO `signup` (`meter_no`, `userName`, `name`, `password`, `userType`) VALUES
('01', 'admin', 'ishan', 'ishan123', 'Admin'),
('341855', 'text1', 'ishan', 'text1', 'Customer'),
('978472', '', 'ishan2', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `tax`
--

CREATE TABLE `tax` (
  `cost_per_unit` varchar(20) DEFAULT NULL,
  `meter_rent` varchar(20) DEFAULT NULL,
  `service_charge` varchar(20) DEFAULT NULL,
  `service_tax` varchar(20) DEFAULT NULL,
  `vat_tax` varchar(20) DEFAULT NULL,
  `fixed_tax` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tax`
--

INSERT INTO `tax` (`cost_per_unit`, `meter_rent`, `service_charge`, `service_tax`, `vat_tax`, `fixed_tax`) VALUES
('10', '45', '20', '58', '5', '18');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
