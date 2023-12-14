-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.32-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for database_mart
CREATE DATABASE IF NOT EXISTS `database_mart` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `database_mart`;

-- Dumping structure for table database_mart.currency_dim
CREATE TABLE IF NOT EXISTS `currency_dim` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name_currency` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table database_mart.currency_dim: ~27 rows (approximately)
INSERT INTO `currency_dim` (`id`, `name_currency`) VALUES
	(1, 'EUR'),
	(2, 'JPY'),
	(3, 'GBP'),
	(4, 'CHF'),
	(5, 'AUD'),
	(6, 'CAD'),
	(7, 'SEK'),
	(8, 'NOK'),
	(9, 'DKK'),
	(10, 'RUB'),
	(11, 'NZD'),
	(12, 'HKD'),
	(13, 'SGD'),
	(14, 'MYR'),
	(15, 'THB'),
	(16, 'IDR'),
	(17, 'KRW'),
	(18, 'INR'),
	(19, 'TWD'),
	(20, 'CNY'),
	(21, 'KHR'),
	(22, 'LAK'),
	(23, 'MOP'),
	(24, 'TRY'),
	(25, 'BRL'),
	(26, 'PLN'),
	(27, 'AED');

-- Dumping structure for table database_mart.date_dim
CREATE TABLE IF NOT EXISTS `date_dim` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table database_mart.date_dim: ~0 rows (approximately)

-- Dumping structure for table database_mart.exchange_rate_mart
CREATE TABLE IF NOT EXISTS `exchange_rate_mart` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `date` datetime(6) DEFAULT NULL,
  `currency` text DEFAULT NULL,
  `name` text DEFAULT NULL,
  `exchange_rate` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=163 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table database_mart.exchange_rate_mart: ~162 rows (approximately)
INSERT INTO `exchange_rate_mart` (`id`, `date`, `currency`, `name`, `exchange_rate`) VALUES
	(1, '2023-12-02 00:00:00.000000', 'EUR', 'Đồng Euro', 26304.3),
	(2, '2023-12-02 00:00:00.000000', 'JPY', 'Yên Nhật', 162.58),
	(3, '2023-12-02 00:00:00.000000', 'GBP', 'Bảng Anh', 30402.99),
	(4, '2023-12-02 00:00:00.000000', 'CHF', 'Phơ răng Thuỵ Sĩ', 27288.6),
	(5, '2023-12-02 00:00:00.000000', 'AUD', 'Đô la Úc', 15878.23),
	(6, '2023-12-02 00:00:00.000000', 'CAD', 'Đô la Canada', 17642.76),
	(7, '2023-12-02 00:00:00.000000', 'SEK', 'Curon Thuỵ Điển', 2319.15),
	(8, '2023-12-02 00:00:00.000000', 'NOK', 'Curon Nauy', 2252.69),
	(9, '2023-12-02 00:00:00.000000', 'DKK', 'Curon Đan Mạch', 3529.33),
	(10, '2023-12-02 00:00:00.000000', 'RUB', 'Rúp Nga', 268.8),
	(11, '2023-12-02 00:00:00.000000', 'NZD', 'Đô la Newzealand', 14790.19),
	(12, '2023-12-02 00:00:00.000000', 'HKD', 'Đô la Hồng Công', 3066.52),
	(13, '2023-12-02 00:00:00.000000', 'SGD', 'Đô la Singapore', 17982.4),
	(14, '2023-12-02 00:00:00.000000', 'MYR', 'Ringít Malaysia', 5147.01),
	(15, '2023-12-02 00:00:00.000000', 'THB', 'Bath Thái', 688.94),
	(16, '2023-12-02 00:00:00.000000', 'IDR', 'Rupiah Inđônêsia', 1.56),
	(17, '2023-12-02 00:00:00.000000', 'KRW', 'Won Hàn Quốc', 18.58),
	(18, '2023-12-02 00:00:00.000000', 'INR', 'Rupee Ấn độ', 287.04),
	(19, '2023-12-02 00:00:00.000000', 'TWD', 'Đô la Đài Loan', 767.18),
	(20, '2023-12-02 00:00:00.000000', 'CNY', 'Nhân dân tệ Trung Quốc', 3357.62),
	(21, '2023-12-02 00:00:00.000000', 'KHR', 'Riêl Cămpuchia', 5.83),
	(22, '2023-12-02 00:00:00.000000', 'LAK', 'Kíp Lào', 1.16),
	(23, '2023-12-02 00:00:00.000000', 'MOP', 'Pataca Macao', 2977.22),
	(24, '2023-12-02 00:00:00.000000', 'TRY', 'Thổ Nhĩ Kỳ', 827.5),
	(25, '2023-12-02 00:00:00.000000', 'BRL', 'Real Brazil', 4909.66),
	(26, '2023-12-02 00:00:00.000000', 'PLN', 'Đồng Zloty Ba Lan', 6092.48),
	(27, '2023-12-02 00:00:00.000000', 'AED', 'Đồng UAE Dirham', 6512.25),
	(28, '2023-12-04 00:00:00.000000', 'EUR', 'Đồng Euro', 26304.3),
	(29, '2023-12-04 00:00:00.000000', 'JPY', 'Yên Nhật', 162.58),
	(30, '2023-12-04 00:00:00.000000', 'GBP', 'Bảng Anh', 30402.99),
	(31, '2023-12-04 00:00:00.000000', 'CHF', 'Phơ răng Thuỵ Sĩ', 27288.6),
	(32, '2023-12-04 00:00:00.000000', 'AUD', 'Đô la Úc', 15878.23),
	(33, '2023-12-04 00:00:00.000000', 'CAD', 'Đô la Canada', 17642.76),
	(34, '2023-12-04 00:00:00.000000', 'SEK', 'Curon Thuỵ Điển', 2319.15),
	(35, '2023-12-04 00:00:00.000000', 'NOK', 'Curon Nauy', 2252.69),
	(36, '2023-12-04 00:00:00.000000', 'DKK', 'Curon Đan Mạch', 3529.33),
	(37, '2023-12-04 00:00:00.000000', 'RUB', 'Rúp Nga', 268.8),
	(38, '2023-12-04 00:00:00.000000', 'NZD', 'Đô la Newzealand', 14790.19),
	(39, '2023-12-04 00:00:00.000000', 'HKD', 'Đô la Hồng Công', 3066.52),
	(40, '2023-12-04 00:00:00.000000', 'SGD', 'Đô la Singapore', 17982.4),
	(41, '2023-12-04 00:00:00.000000', 'MYR', 'Ringgit Malaysia', 5147.01),
	(42, '2023-12-04 00:00:00.000000', 'THB', 'Bath Thái', 688.94),
	(43, '2023-12-04 00:00:00.000000', 'IDR', 'Rupiah Inđônêsia', 1.56),
	(44, '2023-12-04 00:00:00.000000', 'KRW', 'Won Hàn Quốc', 18.58),
	(45, '2023-12-04 00:00:00.000000', 'INR', 'Rupee Ấn độ', 287.04),
	(46, '2023-12-04 00:00:00.000000', 'TWD', 'Đô la Đài Loan', 767.18),
	(47, '2023-12-04 00:00:00.000000', 'CNY', 'Nhân dân tệ Trung Quốc', 3357.62),
	(48, '2023-12-04 00:00:00.000000', 'KHR', 'Riêng Cămpuchia', 5.83),
	(49, '2023-12-04 00:00:00.000000', 'LAK', 'Kíp Lào', 1.16),
	(50, '2023-12-04 00:00:00.000000', 'MOP', 'Pataca Macao', 2977.22),
	(51, '2023-12-04 00:00:00.000000', 'TRY', 'Thổ Nhĩ Kỳ', 827.5),
	(52, '2023-12-04 00:00:00.000000', 'BRL', 'Real Brazin', 4909.66),
	(53, '2023-12-04 00:00:00.000000', 'PLN', 'Đồng Zloty Ba Lan', 6092.48),
	(54, '2023-12-04 00:00:00.000000', 'AED', 'Đồng UAE Dirham', 6512.25),
	(55, '2023-12-06 00:00:00.000000', 'EUR', 'Đồng Euro', 26304.3),
	(56, '2023-12-06 00:00:00.000000', 'JPY', 'Yên Nhật', 162.58),
	(57, '2023-12-06 00:00:00.000000', 'GBP', 'Bảng Anh', 30402.99),
	(58, '2023-12-06 00:00:00.000000', 'CHF', 'Phơ răng Thuỵ Sĩ', 27288.6),
	(59, '2023-12-06 00:00:00.000000', 'AUD', 'Đô la Úc', 15878.23),
	(60, '2023-12-06 00:00:00.000000', 'CAD', 'Đô la Canada', 17642.76),
	(61, '2023-12-06 00:00:00.000000', 'SEK', 'Curon Thuỵ Điển', 2319.15),
	(62, '2023-12-06 00:00:00.000000', 'NOK', 'Curon Nauy', 2252.69),
	(63, '2023-12-06 00:00:00.000000', 'DKK', 'Curon Đan Mạch', 3529.33),
	(64, '2023-12-06 00:00:00.000000', 'RUB', 'Rúp Nga', 268.8),
	(65, '2023-12-06 00:00:00.000000', 'NZD', 'Đô la Newzealand', 14790.19),
	(66, '2023-12-06 00:00:00.000000', 'HKD', 'Đô la Hồng Công', 3066.52),
	(67, '2023-12-06 00:00:00.000000', 'SGD', 'Đô la Singapore', 17982.4),
	(68, '2023-12-06 00:00:00.000000', 'MYR', 'Ringgit Malaysia', 5147.01),
	(69, '2023-12-06 00:00:00.000000', 'THB', 'Bath Thái', 688.94),
	(70, '2023-12-06 00:00:00.000000', 'IDR', 'Rupiah Inđônêsia', 1.56),
	(71, '2023-12-06 00:00:00.000000', 'KRW', 'Won Hàn Quốc', 18.58),
	(72, '2023-12-06 00:00:00.000000', 'INR', 'Rupee Ấn độ', 287.04),
	(73, '2023-12-06 00:00:00.000000', 'TWD', 'Đô la Đài Loan', 767.18),
	(74, '2023-12-06 00:00:00.000000', 'CNY', 'Nhân dân tệ Trung Quốc', 3357.62),
	(75, '2023-12-06 00:00:00.000000', 'KHR', 'Riêng Cămpuchia', 5.83),
	(76, '2023-12-06 00:00:00.000000', 'LAK', 'Kíp Lào', 1.16),
	(77, '2023-12-06 00:00:00.000000', 'MOP', 'Pataca Macao', 2977.22),
	(78, '2023-12-06 00:00:00.000000', 'TRY', 'Thổ Nhĩ Kỳ', 827.5),
	(79, '2023-12-06 00:00:00.000000', 'BRL', 'Real Brazin', 4909.66),
	(80, '2023-12-06 00:00:00.000000', 'PLN', 'Đồng Zloty Ba Lan', 6092.48),
	(81, '2023-12-06 00:00:00.000000', 'AED', 'Đồng UAE Dirham', 6512.25),
	(82, '2023-12-07 00:00:00.000000', 'EUR', 'Đồng Euro', 25847.92),
	(83, '2023-12-07 00:00:00.000000', 'JPY', 'Yên Nhật', 162.69),
	(84, '2023-12-07 00:00:00.000000', 'GBP', 'Bảng Anh', 30183.05),
	(85, '2023-12-07 00:00:00.000000', 'CHF', 'Phơ răng Thuỵ Sĩ', 27381.96),
	(86, '2023-12-07 00:00:00.000000', 'AUD', 'Đô la Úc', 15764.55),
	(87, '2023-12-07 00:00:00.000000', 'CAD', 'Đô la Canada', 17643.46),
	(88, '2023-12-07 00:00:00.000000', 'SEK', 'Curon Thuỵ Điển', 2286.71),
	(89, '2023-12-07 00:00:00.000000', 'NOK', 'Curon Nauy', 2193.19),
	(90, '2023-12-07 00:00:00.000000', 'DKK', 'Curon Đan Mạch', 3466.94),
	(91, '2023-12-07 00:00:00.000000', 'RUB', 'Rúp Nga', 258.56),
	(92, '2023-12-07 00:00:00.000000', 'NZD', 'Đô la Newzealand', 14753.82),
	(93, '2023-12-07 00:00:00.000000', 'HKD', 'Đô la Hồng Công', 3066.04),
	(94, '2023-12-07 00:00:00.000000', 'SGD', 'Đô la Singapore', 17871.21),
	(95, '2023-12-07 00:00:00.000000', 'MYR', 'Ringgit Malaysia', 5128.69),
	(96, '2023-12-07 00:00:00.000000', 'THB', 'Bath Thái', 681.78),
	(97, '2023-12-07 00:00:00.000000', 'IDR', 'Rupiah Inđônêsia', 1.54),
	(98, '2023-12-07 00:00:00.000000', 'KRW', 'Won Hàn Quốc', 18.24),
	(99, '2023-12-07 00:00:00.000000', 'INR', 'Rupee Ấn độ', 287.36),
	(100, '2023-12-07 00:00:00.000000', 'TWD', 'Đô la Đài Loan', 760.61),
	(101, '2023-12-07 00:00:00.000000', 'CNY', 'Nhân dân tệ Trung Quốc', 3346.09),
	(102, '2023-12-07 00:00:00.000000', 'KHR', 'Riêng Cămpuchia', 5.84),
	(103, '2023-12-07 00:00:00.000000', 'LAK', 'Kíp Lào', 1.16),
	(104, '2023-12-07 00:00:00.000000', 'MOP', 'Pataca Macao', 2976.76),
	(105, '2023-12-07 00:00:00.000000', 'TRY', 'Thổ Nhĩ Kỳ', 828.9),
	(106, '2023-12-07 00:00:00.000000', 'BRL', 'Real Brazin', 4859.99),
	(107, '2023-12-07 00:00:00.000000', 'PLN', 'Đồng Zloty Ba Lan', 5986.55),
	(108, '2023-12-07 00:00:00.000000', 'AED', 'Đồng UAE Dirham', 6523.14),
	(109, '2023-12-05 00:00:00.000000', 'EUR', 'Đồng Euro', 26847.92),
	(110, '2023-12-05 00:00:00.000000', 'JPY', 'Yên Nhật', 161.69),
	(111, '2023-12-05 00:00:00.000000', 'GBP', 'Bảng Anh', 30125.05),
	(112, '2023-12-05 00:00:00.000000', 'CHF', 'Phơ răng Thuỵ Sĩ', 26381.96),
	(113, '2023-12-05 00:00:00.000000', 'AUD', 'Đô la Úc', 15664.55),
	(114, '2023-12-05 00:00:00.000000', 'CAD', 'Đô la Canada', 17543.46),
	(115, '2023-12-05 00:00:00.000000', 'SEK', 'Curon Thuỵ Điển', 2186.71),
	(116, '2023-12-05 00:00:00.000000', 'NOK', 'Curon Nauy', 2153.19),
	(117, '2023-12-05 00:00:00.000000', 'DKK', 'Curon Đan Mạch', 3366.94),
	(118, '2023-12-05 00:00:00.000000', 'RUB', 'Rúp Nga', 268.56),
	(119, '2023-12-05 00:00:00.000000', 'NZD', 'Đô la Newzealand', 14353.82),
	(120, '2023-12-05 00:00:00.000000', 'HKD', 'Đô la Hồng Công', 3096.04),
	(121, '2023-12-05 00:00:00.000000', 'SGD', 'Đô la Singapore', 17971.21),
	(122, '2023-12-05 00:00:00.000000', 'MYR', 'Ringgit Malaysia', 5228.69),
	(123, '2023-12-05 00:00:00.000000', 'THB', 'Bath Thái', 686.78),
	(124, '2023-12-05 00:00:00.000000', 'IDR', 'Rupiah Inđônêsia', 1.55),
	(125, '2023-12-05 00:00:00.000000', 'KRW', 'Won Hàn Quốc', 18.74),
	(126, '2023-12-05 00:00:00.000000', 'INR', 'Rupee Ấn độ', 288.36),
	(127, '2023-12-05 00:00:00.000000', 'TWD', 'Đô la Đài Loan', 761.61),
	(128, '2023-12-05 00:00:00.000000', 'CNY', 'Nhân dân tệ Trung Quốc', 3366.09),
	(129, '2023-12-05 00:00:00.000000', 'KHR', 'Riêng Cămpuchia', 5.84),
	(130, '2023-12-05 00:00:00.000000', 'LAK', 'Kíp Lào', 1.16),
	(131, '2023-12-05 00:00:00.000000', 'MOP', 'Pataca Macao', 2977.76),
	(132, '2023-12-05 00:00:00.000000', 'TRY', 'Thổ Nhĩ Kỳ', 822.9),
	(133, '2023-12-05 00:00:00.000000', 'BRL', 'Real Brazin', 4852.99),
	(134, '2023-12-05 00:00:00.000000', 'PLN', 'Đồng Zloty Ba Lan', 5926.55),
	(135, '2023-12-05 00:00:00.000000', 'AED', 'Đồng UAE Dirham', 6543.14),
	(136, '2023-12-03 00:00:00.000000', 'EUR', 'Đồng Euro', 26847.92),
	(137, '2023-12-03 00:00:00.000000', 'JPY', 'Yên Nhật', 162.69),
	(138, '2023-12-03 00:00:00.000000', 'GBP', 'Bảng Anh', 30225.05),
	(139, '2023-12-03 00:00:00.000000', 'CHF', 'Phơ răng Thuỵ Sĩ', 26581.96),
	(140, '2023-12-03 00:00:00.000000', 'AUD', 'Đô la Úc', 15664.55),
	(141, '2023-12-03 00:00:00.000000', 'CAD', 'Đô la Canada', 17643.46),
	(142, '2023-12-03 00:00:00.000000', 'SEK', 'Curon Thuỵ Điển', 2286.71),
	(143, '2023-12-03 00:00:00.000000', 'NOK', 'Curon Nauy', 2153.19),
	(144, '2023-12-03 00:00:00.000000', 'DKK', 'Curon Đan Mạch', 3466.94),
	(145, '2023-12-03 00:00:00.000000', 'RUB', 'Rúp Nga', 269.56),
	(146, '2023-12-03 00:00:00.000000', 'NZD', 'Đô la Newzealand', 14323.82),
	(147, '2023-12-03 00:00:00.000000', 'HKD', 'Đô la Hồng Công', 3016.04),
	(148, '2023-12-03 00:00:00.000000', 'SGD', 'Đô la Singapore', 17171.21),
	(149, '2023-12-03 00:00:00.000000', 'MYR', 'Ringgit Malaysia', 5528.69),
	(150, '2023-12-03 00:00:00.000000', 'THB', 'Bath Thái', 626.78),
	(151, '2023-12-03 00:00:00.000000', 'IDR', 'Rupiah Inđônêsia', 1.55),
	(152, '2023-12-03 00:00:00.000000', 'KRW', 'Won Hàn Quốc', 18.84),
	(153, '2023-12-03 00:00:00.000000', 'INR', 'Rupee Ấn độ', 289.36),
	(154, '2023-12-03 00:00:00.000000', 'TWD', 'Đô la Đài Loan', 762.61),
	(155, '2023-12-03 00:00:00.000000', 'CNY', 'Nhân dân tệ Trung Quốc', 3386.09),
	(156, '2023-12-03 00:00:00.000000', 'KHR', 'Riêng Cămpuchia', 5.94),
	(157, '2023-12-03 00:00:00.000000', 'LAK', 'Kíp Lào', 1.56),
	(158, '2023-12-03 00:00:00.000000', 'MOP', 'Pataca Macao', 2979.76),
	(159, '2023-12-03 00:00:00.000000', 'TRY', 'Thổ Nhĩ Kỳ', 828.9),
	(160, '2023-12-03 00:00:00.000000', 'BRL', 'Real Brazin', 4855.99),
	(161, '2023-12-03 00:00:00.000000', 'PLN', 'Đồng Zloty Ba Lan', 5924.55),
	(162, '2023-12-03 00:00:00.000000', 'AED', 'Đồng UAE Dirham', 6541.14);

-- Dumping structure for table database_mart.name_dim
CREATE TABLE IF NOT EXISTS `name_dim` (
  `id` int(10) unsigned NOT NULL DEFAULT 0,
  `name` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table database_mart.name_dim: ~27 rows (approximately)
INSERT INTO `name_dim` (`id`, `name`) VALUES
	(1, 'Đồng Euro'),
	(2, 'Yên Nhật'),
	(3, 'Bảng Anh'),
	(4, 'Phơ răng Thuỵ Sĩ'),
	(5, 'Đô la Úc'),
	(6, 'Đô la Canada'),
	(7, 'Curon Thuỵ Điển'),
	(8, 'Curon Nauy'),
	(9, 'Curon Đan Mạch'),
	(10, 'Rúp Nga'),
	(11, 'Đô la Newzealand'),
	(12, 'Đô la Hồng Kông'),
	(13, 'Đô la Singapore'),
	(14, 'Ringít Malaysia'),
	(15, 'Bath Thái'),
	(16, 'Rupiah Inđônêsia'),
	(17, 'Won Hàn Quốc'),
	(18, 'Rupee Ấn độ'),
	(19, 'Đô la Đài Loan'),
	(20, 'Nhân dân tệ Trung Quốc'),
	(21, 'Riêl Cămpuchia'),
	(22, 'Kíp Lào'),
	(23, 'Pataca Macao'),
	(24, 'Thổ Nhĩ Kỳ'),
	(25, 'Real Brazin'),
	(26, 'Đồng Zloty Ba Lan'),
	(27, 'Đồng UAE Dirham');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
