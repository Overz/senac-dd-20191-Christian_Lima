DROP SCHEMA `meubanco` ;
CREATE SCHEMA NOT EXISTS `meubanco` ;

CREATE TABLE `meubanco`.`usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(200) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  `idNivel` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE `meubanco`.`nivel` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

ALTER TABLE `meubanco`.`nivel` 
ADD CONSTRAINT `idNivel`
FOREIGN KEY (`id`)
REFERENCES `meubanco`.`usuario` (`idNivel`)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

INSERT INTO `meubanco`.`usuario` (`id`, `nome`, `email`, `senha`, `idNivel`) VALUES ('1', 'lucas', '1@1.com', '123456', '1');
INSERT INTO `meubanco`.`usuario` (`id`, `nome`, `email`, `senha`, `idNivel`) VALUES ('2', 'christian', '2@2.com', '123456', '1');
INSERT INTO `meubanco`.`usuario` (`id`, `nome`, `email`, `senha`, `idNivel`) VALUES ('3', 'carol', '3@3.com', '123456', '2');
INSERT INTO `meubanco`.`usuario` (`id`, `nome`, `email`, `senha`, `idNivel`) VALUES ('4', 'gabriel', '4@4.com', '123456', '2');
INSERT INTO `meubanco`.`usuario` (`id`, `nome`, `email`, `senha`, `idNivel`) VALUES ('5', 'guilherme', '5@5.com', '123456', '2');

INSERT INTO `meubanco`.`nivel` (`id`, `descricao`) VALUES ('1', 'Administrador');
INSERT INTO `meubanco`.`nivel` (`id`, `descricao`) VALUES ('2', 'Normal');
