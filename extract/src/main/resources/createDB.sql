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


-- Dumping database structure for database_control
CREATE DATABASE IF NOT EXISTS `database_control` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `database_control`;

-- Dumping structure for table database_control.config
CREATE TABLE IF NOT EXISTS `config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(1000) DEFAULT NULL,
  `status` text DEFAULT NULL,
  `flag` int(11) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `source_path` varchar(1000) DEFAULT NULL,
  `location` varchar(1000) DEFAULT NULL,
  `format` varchar(255) DEFAULT NULL,
  `separator` varchar(255) DEFAULT NULL,
  `columns_name_staging_temp` text DEFAULT NULL,
  `type_columns_staging_temp` text DEFAULT NULL,
  `database_name_staging` text DEFAULT NULL,
  `database_name_warehouse` text DEFAULT NULL,
  `database_name_datamart` text DEFAULT NULL,
  `server_name` text DEFAULT NULL,
  `port` text DEFAULT NULL,
  `username` text DEFAULT NULL,
  `password` text DEFAULT NULL,
  `destination` varchar(1000) DEFAULT NULL,
  `date_run` date DEFAULT NULL,
  `create_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `create_by` text DEFAULT NULL,
  `dt_update` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp(),
  `update_by` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table database_control.config: ~1 rows (approximately)
INSERT INTO `config` (`id`, `name`, `status`, `flag`, `description`, `source_path`, `location`, `format`, `separator`, `columns_name_staging_temp`, `type_columns_staging_temp`, `database_name_staging`, `database_name_warehouse`, `database_name_datamart`, `server_name`, `port`, `username`, `password`, `destination`, `date_run`, `create_at`, `create_by`, `dt_update`, `update_by`) VALUES
	(1, 'Tỷ giá', 'Default', 1, 'Là config dùng cho trang web https://www.sbv.gov.vn/webcenter/portal/m/menu/trangchu/tg', 'https://www.sbv.gov.vn/webcenter/portal/m/menu/trangchu/tg', 'D:// datawarehouse/data', 'xlsx', '\\t', '(date, currency, name, exchange_rate)', '(text, text, text, text)', 'Database_Staging', 'Database_Warehouse', 'Database_DataMart', '127.0.0.1', '3306', 'root', '123456789', 'exchange_rate_temp', '2023-01-21', '2023-12-01 14:56:03', 'An', '2023-12-01 14:57:55', 'An');

-- Dumping structure for table database_control.log
CREATE TABLE IF NOT EXISTS `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `config_id` int(11) DEFAULT NULL,
  `name` varchar(1000) DEFAULT NULL,
  `row_count` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `data_range_from` varchar(255) DEFAULT NULL,
  `data_range_to` varchar(255) DEFAULT NULL,
  `message` text DEFAULT NULL,
  `create_at` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `config_id` (`config_id`),
  CONSTRAINT `log_ibfk_1` FOREIGN KEY (`config_id`) REFERENCES `config` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table database_control.log: ~0 rows (approximately)

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
