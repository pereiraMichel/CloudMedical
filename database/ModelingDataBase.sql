SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';


-- -----------------------------------------------------
-- Table `cloudmedical`.`tblusuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cloudmedical`.`tblusuarios` (
  `idUsuario` INT NOT NULL,
  `nomeUsuario` VARCHAR(65) NULL,
  `senha` LONGBLOB NULL,
  `email` VARCHAR(85) NULL,
  `dataCadastro` DATE NULL,
  `dataAlteracao` DATE NULL,
  PRIMARY KEY (`idUsuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cloudmedical`.`tipopagamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cloudmedical`.`tipopagamento` (
  `idTipoPagamento` INT NOT NULL,
  `nomePagamento` VARCHAR(65) NULL,
  PRIMARY KEY (`idTipoPagamento`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cloudmedical`.`perfil`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cloudmedical`.`perfil` (
  `idPerfil` INT NOT NULL,
  `nomePerfil` VARCHAR(85) NULL,
  `sigla` VARCHAR(5) NULL,
  PRIMARY KEY (`idPerfil`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cloudmedical`.`medicos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cloudmedical`.`medicos` (
  `idMedico` INT NOT NULL,
  `nomeMedico` VARCHAR(85) NULL,
  `crm` VARCHAR(45) NULL,
  `valor` DOUBLE NULL,
  `codUsuario` INT NOT NULL,
  `codTipoPagamento` INT NOT NULL,
  `codPerfil` INT NOT NULL,
  PRIMARY KEY (`idMedico`),
  INDEX `fk_medicos_tblusuarios_idx` (`codUsuario` ASC),
  INDEX `fk_medicos_tipopagamento1_idx` (`codTipoPagamento` ASC),
  INDEX `fk_medicos_perfil1_idx` (`codPerfil` ASC),
  CONSTRAINT `fk_medicos_tblusuarios`
    FOREIGN KEY (`codUsuario`)
    REFERENCES `cloudmedical`.`tblusuarios` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_medicos_tipopagamento1`
    FOREIGN KEY (`codTipoPagamento`)
    REFERENCES `cloudmedical`.`tipopagamento` (`idTipoPagamento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_medicos_perfil1`
    FOREIGN KEY (`codPerfil`)
    REFERENCES `cloudmedical`.`perfil` (`idPerfil`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cloudmedical`.`endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cloudmedical`.`endereco` (
  `idEndereco` INT NOT NULL,
  `nomeEndereco` VARCHAR(45) NULL,
  `numero` INT NULL,
  `complemento` VARCHAR(85) NULL,
  `bairro` VARCHAR(85) NULL,
  `cidade` VARCHAR(45) NULL,
  `estado` VARCHAR(2) NULL,
  `codMedico` INT NOT NULL,
  PRIMARY KEY (`idEndereco`),
  INDEX `fk_endereco_medicos1_idx` (`codMedico` ASC),
  CONSTRAINT `fk_endereco_medicos1`
    FOREIGN KEY (`codMedico`)
    REFERENCES `cloudmedical`.`medicos` (`idMedico`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cloudmedical`.`tipoTelefone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cloudmedical`.`tipoTelefone` (
  `idTipoTelefone` INT NOT NULL,
  `tipo` VARCHAR(45) NULL,
  `sigla` VARCHAR(45) NULL,
  PRIMARY KEY (`idTipoTelefone`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cloudmedical`.`telefone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cloudmedical`.`telefone` (
  `idTelefone` INT NOT NULL,
  `telefone` VARCHAR(65) NULL,
  `codProprietario` INT NULL,
  `codTipoTelefone` INT NOT NULL,
  `codPerfil` INT NOT NULL,
  PRIMARY KEY (`idTelefone`),
  INDEX `fk_telefone_tipoTelefone1_idx` (`codTipoTelefone` ASC),
  INDEX `fk_telefone_natureza1_idx` (`codPerfil` ASC),
  CONSTRAINT `fk_telefone_tipoTelefone1`
    FOREIGN KEY (`codTipoTelefone`)
    REFERENCES `cloudmedical`.`tipoTelefone` (`idTipoTelefone`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_telefone_natureza1`
    FOREIGN KEY (`codPerfil`)
    REFERENCES `cloudmedical`.`perfil` (`idPerfil`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cloudmedical`.`pagamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cloudmedical`.`pagamento` (
  `idPagamento` INT NOT NULL,
  `valorPago` DOUBLE NULL,
  `competencia` VARCHAR(45) NULL,
  `valorPendente` DOUBLE NULL,
  `dataPagamento` DATE NULL,
  `codFavorecido` INT NULL,
  `codPerfil` INT NOT NULL,
  PRIMARY KEY (`idPagamento`),
  INDEX `fk_pagamento_natureza1_idx` (`codPerfil` ASC),
  CONSTRAINT `fk_pagamento_natureza1`
    FOREIGN KEY (`codPerfil`)
    REFERENCES `cloudmedical`.`perfil` (`idPerfil`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cloudmedical`.`terceirizados`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cloudmedical`.`terceirizados` (
  `idTerceirizado` INT NOT NULL,
  `nomeTerceirizado` VARCHAR(85) NULL,
  `documento` VARCHAR(65) NULL,
  `servicos` VARCHAR(85) NULL,
  `contatos` VARCHAR(150) NULL,
  `valorPagto` DOUBLE NULL,
  `codTipoPagamento` INT NOT NULL,
  `codPerfil` INT NOT NULL,
  `codUsuario` INT NOT NULL,
  PRIMARY KEY (`idTerceirizado`),
  INDEX `fk_terceirizados_tipopagamento1_idx` (`codTipoPagamento` ASC),
  INDEX `fk_terceirizados_perfil1_idx` (`codPerfil` ASC),
  INDEX `fk_terceirizados_tblusuarios1_idx` (`codUsuario` ASC),
  CONSTRAINT `fk_terceirizados_tipopagamento1`
    FOREIGN KEY (`codTipoPagamento`)
    REFERENCES `cloudmedical`.`tipopagamento` (`idTipoPagamento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_terceirizados_perfil1`
    FOREIGN KEY (`codPerfil`)
    REFERENCES `cloudmedical`.`perfil` (`idPerfil`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_terceirizados_tblusuarios1`
    FOREIGN KEY (`codUsuario`)
    REFERENCES `cloudmedical`.`tblusuarios` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cloudmedical`.`informacoes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cloudmedical`.`informacoes` (
  `idInformacao` INT NOT NULL,
  `texto` VARCHAR(150) NULL,
  `codMedico` INT NOT NULL,
  PRIMARY KEY (`idInformacao`),
  INDEX `fk_informacoes_medicos1_idx` (`codMedico` ASC),
  CONSTRAINT `fk_informacoes_medicos1`
    FOREIGN KEY (`codMedico`)
    REFERENCES `cloudmedical`.`medicos` (`idMedico`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
