-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.24-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.0.0.6468
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for database_staging
CREATE DATABASE IF NOT EXISTS `database_staging` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `database_staging`;

-- Dumping structure for table database_staging.exchange_rate
CREATE TABLE IF NOT EXISTS `exchange_rate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `currency` text DEFAULT NULL,
  `name` text DEFAULT NULL,
  `exchange_rate` double DEFAULT NULL,
  `dt_change` date NOT NULL DEFAULT current_timestamp(),
  `dt_expired` date DEFAULT '9999-12-31',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table database_staging.exchange_rate: ~27 rows (approximately)
INSERT INTO `exchange_rate` (`id`, `date`, `currency`, `name`, `exchange_rate`, `dt_change`, `dt_expired`) VALUES
	(1, '2023-02-12', 'EUR', 'Đồng Euro', 26304.3, '2023-12-02', '9999-12-31'),
	(2, '2023-02-12', 'JPY', 'Yên Nhật', 162.58, '2023-12-02', '9999-12-31'),
	(3, '2023-02-12', 'GBP', 'Bảng Anh', 30402.99, '2023-12-02', '9999-12-31'),
	(4, '2023-02-12', 'CHF', 'Phơ răng Thuỵ Sĩ', 27288.6, '2023-12-02', '9999-12-31'),
	(5, '2023-02-12', 'AUD', 'Đô la Úc', 15878.23, '2023-12-02', '9999-12-31'),
	(6, '2023-02-12', 'CAD', 'Đô la Canada', 17642.76, '2023-12-02', '9999-12-31'),
	(7, '2023-02-12', 'SEK', 'Curon Thuỵ Điển', 2319.15, '2023-12-02', '9999-12-31'),
	(8, '2023-02-12', 'NOK', 'Curon Nauy', 2252.69, '2023-12-02', '9999-12-31'),
	(9, '2023-02-12', 'DKK', 'Curon Đan Mạch', 3529.33, '2023-12-02', '9999-12-31'),
	(10, '2023-02-12', 'RUB', 'Rúp Nga', 268.8, '2023-12-02', '9999-12-31'),
	(11, '2023-02-12', 'NZD', 'Đô la Newzealand', 14790.19, '2023-12-02', '9999-12-31'),
	(12, '2023-02-12', 'HKD', 'Đô la Hồng Công', 3066.52, '2023-12-02', '9999-12-31'),
	(13, '2023-02-12', 'SGD', 'Đô la Singapore', 17982.4, '2023-12-02', '9999-12-31'),
	(14, '2023-02-12', 'MYR', 'Ringít Malaysia', 5147.01, '2023-12-02', '9999-12-31'),
	(15, '2023-02-12', 'THB', 'Bath Thái', 688.94, '2023-12-02', '9999-12-31'),
	(16, '2023-02-12', 'IDR', 'Rupiah Inđônêsia', 1.56, '2023-12-02', '9999-12-31'),
	(17, '2023-02-12', 'KRW', 'Won Hàn Quốc', 18.58, '2023-12-02', '9999-12-31'),
	(18, '2023-02-12', 'INR', 'Rupee Ấn độ', 287.04, '2023-12-02', '9999-12-31'),
	(19, '2023-02-12', 'TWD', 'Đô la Đài Loan', 767.18, '2023-12-02', '9999-12-31'),
	(20, '2023-02-12', 'CNY', 'Nhân dân tệ Trung Quốc', 3357.62, '2023-12-02', '9999-12-31'),
	(21, '2023-02-12', 'KHR', 'Riêl Cămpuchia', 5.83, '2023-12-02', '9999-12-31'),
	(22, '2023-02-12', 'LAK', 'Kíp Lào', 1.16, '2023-12-02', '9999-12-31'),
	(23, '2023-02-12', 'MOP', 'Pataca Macao', 2977.22, '2023-12-02', '9999-12-31'),
	(24, '2023-02-12', 'TRY', 'Thổ Nhĩ Kỳ', 827.5, '2023-12-02', '9999-12-31'),
	(25, '2023-02-12', 'BRL', 'Real Brazil', 4909.66, '2023-12-02', '9999-12-31'),
	(26, '2023-02-12', 'PLN', 'Đồng Zloty Ba Lan', 6092.48, '2023-12-02', '9999-12-31'),
	(27, '2023-02-12', 'AED', 'Đồng UAE Dirham', 6512.25, '2023-12-02', '9999-12-31');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
