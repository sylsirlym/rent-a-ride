-- Adminer 4.7.1 MySQL dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers` (
  `customerID` int(11) NOT NULL AUTO_INCREMENT,
  `fName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `otherNames` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `emailAddress` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `dateOfBirth` timestamp NOT NULL,
  `dateCreated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `dateModified` timestamp NULL DEFAULT NULL,
  `active` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`customerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `identificationTypes`;
CREATE TABLE `identificationTypes` (
  `identificationTypeID` int(11) NOT NULL AUTO_INCREMENT,
  `identificationTypeName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `dateCreated` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `dateModified` timestamp NULL DEFAULT NULL,
  `active` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`identificationTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `lendItemOwnerProfiles`;
CREATE TABLE `lendItemOwnerProfiles` (
  `lendItemOwnerProfileID` int(11) NOT NULL AUTO_INCREMENT,
  `customersID` int(11) NOT NULL,
  `mobileNumber` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `idNumber` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `dateCreated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `dateModified` timestamp NULL DEFAULT NULL,
  `active` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`lendItemOwnerProfileID`),
  KEY `customersID` (`customersID`),
  CONSTRAINT `lendItemOwnerProfiles_ibfk_1` FOREIGN KEY (`customersID`) REFERENCES `customers` (`customerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `lendItemTypes`;
CREATE TABLE `lendItemTypes` (
  `lendItemTypeID` int(11) NOT NULL AUTO_INCREMENT,
  `lendItemTypeName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `lendItemCost` float NOT NULL,
  `dateCreated` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `dateModified` timestamp NULL DEFAULT NULL,
  `active` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`lendItemTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `lendItems`;
CREATE TABLE `lendItems` (
  `lendItemID` int(11) NOT NULL AUTO_INCREMENT,
  `lendItemType` int(11) NOT NULL,
  `serialNumber` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `lendItemOwnerProfileID` int(11) NOT NULL,
  `dateCreated` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `dateModified` timestamp NULL DEFAULT NULL,
  `active` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`lendItemID`),
  KEY `lendItemType` (`lendItemType`),
  KEY `lendItemOwnerProfileID` (`lendItemOwnerProfileID`),
  CONSTRAINT `lendItems_ibfk_1` FOREIGN KEY (`lendItemType`) REFERENCES `lendItemTypes` (`lendItemTypeID`),
  CONSTRAINT `lendItems_ibfk_2` FOREIGN KEY (`lendItemOwnerProfileID`) REFERENCES `lendItemOwnerProfiles` (`lendItemOwnerProfileID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `lendTransactionStatus`;
CREATE TABLE `lendTransactionStatus` (
  `lendTransactionStatusID` int(11) NOT NULL AUTO_INCREMENT,
  `lendTransactionStatus` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`lendTransactionStatusID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `lendTransactions`;
CREATE TABLE `lendTransactions` (
  `lendTransactionID` int(11) NOT NULL AUTO_INCREMENT,
  `lendItemID` int(11) NOT NULL,
  `profileID` int(11) NOT NULL,
  `lendTransactionStatus` int(11) NOT NULL,
  `dateCreated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `dateModified` timestamp NULL DEFAULT NULL,
  `active` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`lendTransactionID`),
  KEY `lendItemID` (`lendItemID`),
  KEY `profileID` (`profileID`),
  KEY `lendTransactionStatus` (`lendTransactionStatus`),
  CONSTRAINT `lendTransactions_ibfk_1` FOREIGN KEY (`lendItemID`) REFERENCES `lendItems` (`lendItemID`),
  CONSTRAINT `lendTransactions_ibfk_2` FOREIGN KEY (`profileID`) REFERENCES `profiles` (`profileID`),
  CONSTRAINT `lendTransactions_ibfk_3` FOREIGN KEY (`lendTransactionStatus`) REFERENCES `lendTransactionStatus` (`lendTransactionStatusID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `payments`;
CREATE TABLE `payments` (
  `paymentID` int(11) NOT NULL AUTO_INCREMENT,
  `lendTransactionID` int(11) NOT NULL,
  `amount` float NOT NULL,
  `paymentRefNumber` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `dateCreated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `dateModified` timestamp NULL DEFAULT NULL,
  `active` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`paymentID`),
  KEY `lendTransactionID` (`lendTransactionID`),
  CONSTRAINT `payments_ibfk_1` FOREIGN KEY (`lendTransactionID`) REFERENCES `lendTransactions` (`lendTransactionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `pinStatus`;
CREATE TABLE `pinStatus` (
  `pinStatusID` int(11) NOT NULL AUTO_INCREMENT,
  `pinStatus` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `dateCreated` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `dateModified` datetime DEFAULT NULL,
  `active` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`pinStatusID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `profiles`;
CREATE TABLE `profiles` (
  `profileID` int(11) NOT NULL AUTO_INCREMENT,
  `customerID` int(11) NOT NULL,
  `identificationNumber` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `msisdn` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `identificationTypeID` int(11) NOT NULL,
  `pinStatus` int(11) NOT NULL,
  `pinHash` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `pinRetries` int(11) NOT NULL DEFAULT '0',
  `maxPinRetries` int(11) NOT NULL DEFAULT '6',
  `dateLastAccessed` datetime NOT NULL,
  `dateTempLocked` datetime DEFAULT NULL,
  `datePinChanged` datetime DEFAULT NULL,
  `dateCreated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `dateModified` timestamp NULL DEFAULT NULL,
  `active` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`profileID`),
  KEY `customersID` (`customerID`),
  KEY `identificationTypeID` (`identificationTypeID`),
  KEY `pinStatus` (`pinStatus`),
  CONSTRAINT `profiles_ibfk_1` FOREIGN KEY (`customerID`) REFERENCES `customers` (`customerID`),
  CONSTRAINT `profiles_ibfk_2` FOREIGN KEY (`identificationTypeID`) REFERENCES `identificationTypes` (`identificationTypeID`),
  CONSTRAINT `profiles_ibfk_3` FOREIGN KEY (`pinStatus`) REFERENCES `pinStatus` (`pinStatusID`),
  CONSTRAINT `profiles_ibfk_4` FOREIGN KEY (`pinStatus`) REFERENCES `pinStatus` (`pinStatusID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `responseTemplates`;
CREATE TABLE `responseTemplates` (
  `responseTemplateID` int(11) NOT NULL AUTO_INCREMENT,
  `statusCode` int(5) NOT NULL,
  `responseTemplate` varchar(500) NOT NULL,
  `dateCreated` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `dateModified` timestamp NULL DEFAULT NULL,
  `active` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`responseTemplateID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- 2020-02-26 08:18:08
