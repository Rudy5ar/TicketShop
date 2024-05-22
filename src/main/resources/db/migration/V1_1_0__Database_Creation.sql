-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ticketShop1
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ticketShop1
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ticketShop1` DEFAULT CHARACTER SET utf8 ;
USE `ticketShop1` ;

-- -----------------------------------------------------
-- Table `ticketShop1`.`Location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ticketShop1`.`Location` (
                                                       `id` INT NOT NULL AUTO_INCREMENT,
                                                       `longitude` INT NOT NULL,
                                                       `latitude` INT NOT NULL,
                                                       `address` VARCHAR(100) NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ticketShop1`.`Manifestation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ticketShop1`.`Manifestation` (
                                                            `id` INT NOT NULL AUTO_INCREMENT,
                                                            `name` VARCHAR(45) NOT NULL,
    `type` VARCHAR(45) NOT NULL,
    `numOfSeats` INT NOT NULL,
    `date` DATE NOT NULL,
    `priceRegular` INT NOT NULL,
    `status` INT NOT NULL,
    `Location_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_Manifestation_Location_idx` (`Location_id` ASC) VISIBLE,
    CONSTRAINT `fk_Manifestation_Location`
    FOREIGN KEY (`Location_id`)
    REFERENCES `ticketShop1`.`Location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ticketShop1`.`Korisnik`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ticketShop1`.`Korisnik` (
                                                       `id` INT NOT NULL AUTO_INCREMENT,
                                                       `username` VARCHAR(45) NOT NULL,
    `password` VARCHAR(45) NOT NULL,
    `firstName` VARCHAR(45) NOT NULL,
    `lastName` VARCHAR(45) NOT NULL,
    `rewardPoints` VARCHAR(45) NULL,
    `userType` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ticketShop1`.`Karta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ticketShop1`.`Karta` (
                                                    `id` INT NOT NULL AUTO_INCREMENT,
                                                    `date` DATE NOT NULL,
                                                    `price` INT NOT NULL,
                                                    `status` INT NOT NULL,
                                                    `type` VARCHAR(45) NOT NULL,
    `Manifestation_id` INT NOT NULL,
    `Korisnik_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_Karta_Manifestation1_idx` (`Manifestation_id` ASC) VISIBLE,
    INDEX `fk_Karta_Korisnik1_idx` (`Korisnik_id` ASC) VISIBLE,
    CONSTRAINT `fk_Karta_Manifestation1`
    FOREIGN KEY (`Manifestation_id`)
    REFERENCES `ticketShop1`.`Manifestation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_Karta_Korisnik1`
    FOREIGN KEY (`Korisnik_id`)
    REFERENCES `ticketShop1`.`Korisnik` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
