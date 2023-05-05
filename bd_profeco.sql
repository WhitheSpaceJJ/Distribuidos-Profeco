-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema bd_profeco
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bd_profeco
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bd_profeco` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `bd_profeco` ;

-- -----------------------------------------------------
-- Table `bd_profeco`.`multas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_profeco`.`multas` (
  `id_multas` INT NOT NULL AUTO_INCREMENT,
  `motivo` VARCHAR(500) NOT NULL,
  `costo` DOUBLE NOT NULL,
  `id_supermercados` INT NOT NULL,
  PRIMARY KEY (`id_multas`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bd_profeco`.`profeco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_profeco`.`profeco` (
  `id_profeco` INT NOT NULL AUTO_INCREMENT,
  `usuario` VARCHAR(150) NOT NULL,
  `contraseña` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`id_profeco`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
