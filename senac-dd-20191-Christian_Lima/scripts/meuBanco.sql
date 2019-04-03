CREATE SCHEMA `meubanco` ;

CREATE TABLE `meubanco`.`usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(200) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  `idNivel` INT NOT NULL,
  PRIMARY KEY (`id`));

  CREATE TABLE `meubanco`.`nivel` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));
