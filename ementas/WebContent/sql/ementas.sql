CREATE DATABASE ementas;

CREATE TABLE IF NOT EXISTS ementas.USUARIO(
  IDUSUARIO BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  NOME VARCHAR(60) NOT NULL,
  USUARIO VARCHAR(40) NOT NULL,
  SENHA VARCHAR(122) NOT NULL,
  ATIVO VARCHAR(1) NOT NULL
)ENGINE=INNODB;


CREATE TABLE IF NOT EXISTS ementas.PERFIL(
	IDPERFIL BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	DESCRICAO VARCHAR(30) NOT NULL,
	ATIVO VARCHAR(1) NOT NULL
)ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS ementas.REGRA(
    IDREGRA BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	NOME_REGRA VARCHAR(20) NOT NULL
)ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS ementas.USUARIO_PERFIL(
	IDUSUARIO BIGINT(20) NOT NULL,
	IDPERFIL BIGINT(20) NOT NULL
)ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS ementas.PERFIL_REGRA(
	IDPERFIL BIGINT(20) NOT NULL,
	IDREGRA BIGINT(20) NOT NULL
)ENGINE=INNODB;


ALTER TABLE PERFIL_REGRA ADD FOREIGN KEY (IDPERFIL) REFERENCES PERFIL(IDPERFIL);
ALTER TABLE PERFIL_REGRA ADD FOREIGN KEY (IDREGRA) REFERENCES REGRA(IDREGRA);


ALTER TABLE USUARIO_PERFIL ADD FOREIGN KEY (IDUSUARIO) REFERENCES USUARIO(IDUSUARIO);
ALTER TABLE USUARIO_PERFIL ADD FOREIGN KEY (IDPERFIL) REFERENCES PERFIL(IDPERFIL);



INSERT INTO USUARIO(nome, usuario, senha, ativo) VALUES('ADMINISTRADOR DO SISTEMA','admin','123456','1');

INSERT INTO PERFIL(descricao, ativo) VALUES('ADMINISTRADOR','1');
INSERT INTO PERFIL(descricao, ativo) VALUES('USUARIO','1');


INSERT INTO USUARIO_PERFIL VALUES(1,1);
INSERT INTO USUARIO_PERFIL VALUES(1,2);

INSERT INTO REGRA(nome_regra) VALUES('INSERIR');
INSERT INTO REGRA(nome_regra) VALUES('SALVAR');
INSERT INTO REGRA(nome_regra) VALUES('ELIMINAR');
INSERT INTO REGRA(nome_regra) VALUES('EDITAR');
INSERT INTO REGRA(nome_regra) VALUES('VISUALIZAR');

INSERT INTO PERFIL_REGRA VALUES(1,1);
INSERT INTO PERFIL_REGRA VALUES(1,2);
INSERT INTO PERFIL_REGRA VALUES(1,3);
INSERT INTO PERFIL_REGRA VALUES(1,4);
INSERT INTO PERFIL_REGRA VALUES(1,5);


-- -----------------------------------------------------
-- Table `ementas`.`curso`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `ementas`.`curso` (
  `id_curso` INT NOT NULL AUTO_INCREMENT ,
  `nome_curso` VARCHAR(45) NOT NULL ,
  `descricao` VARCHAR(150) NULL ,
  `ativo` VARCHAR(1) NULL ,
  PRIMARY KEY (`id_curso`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ementas`.`eixodeformacao`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `ementas`.`eixodeformacao` (
  `id_eixodeformacao` INT NOT NULL AUTO_INCREMENT ,
  `desc_eixo` VARCHAR(45) NOT NULL ,
   `ativo` VARCHAR(1) NULL ,
  PRIMARY KEY (`id_eixodeformacao`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ementas`.`disciplina`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `ementas`.`disciplina` (
  `id_disciplina` INT NOT NULL AUTO_INCREMENT ,
  `nome_disc` VARCHAR(45) NOT NULL ,
  `eixodeformacao_id_eixodeformacao` INT NOT NULL ,
   `ativo` VARCHAR(1) NULL ,
  PRIMARY KEY (`id_disciplina`) ,
  INDEX `fk_disciplina_eixodeformacao1_idx` (`eixodeformacao_id_eixodeformacao` ASC) ,
  CONSTRAINT `fk_disciplina_eixodeformacao1`
    FOREIGN KEY (`eixodeformacao_id_eixodeformacao` )
    REFERENCES `ementas`.`eixodeformacao` (`id_eixodeformacao` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE  TABLE IF NOT EXISTS `ementas`.`professor` (
  `id_professor` INT NOT NULL AUTO_INCREMENT ,
  `nome_prof` VARCHAR(45) NOT NULL ,
  `email_prof` VARCHAR(45) NOT NULL ,
  `ativo` VARCHAR(1) NULL ,
  PRIMARY KEY (`id_professor`) )
ENGINE = InnoDB;

CREATE  TABLE IF NOT EXISTS `ementas`.`disciplina_professor` (
  `disciplina_id_disciplina` INT NOT NULL AUTO_INCREMENT ,
  `professor_id_professor` INT NOT NULL ,
  PRIMARY KEY (`disciplina_id_disciplina`, `professor_id_professor`) ,
  INDEX `fk_disciplina/professor_professor1_idx` (`professor_id_professor` ASC) ,
  CONSTRAINT `fk_disciplina/professor_disciplina1`
    FOREIGN KEY (`disciplina_id_disciplina` )
    REFERENCES `ementas`.`disciplina` (`id_disciplina` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_disciplina/professor_professor1`
    FOREIGN KEY (`professor_id_professor` )
    REFERENCES `ementas`.`professor` (`id_professor` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE  TABLE IF NOT EXISTS `ementas`.`curso_disciplina` (
  `curso_id_curso` INT NOT NULL AUTO_INCREMENT ,
  `disciplina_id_disciplina` INT NOT NULL ,
  PRIMARY KEY (`curso_id_curso`, `disciplina_id_disciplina`) ,
  INDEX `fk_curso/disciplina_disciplina1_idx` (`disciplina_id_disciplina` ASC) ,
  CONSTRAINT `fk_curso/disciplina_curso1`
    FOREIGN KEY (`curso_id_curso` )
    REFERENCES `ementas`.`curso` (`id_curso` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_curso/disciplina_disciplina1`
    FOREIGN KEY (`disciplina_id_disciplina` )
    REFERENCES `ementas`.`disciplina` (`id_disciplina` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE  TABLE IF NOT EXISTS `ementas`.`planodeensino` (
  `id_planodeensino` INT NOT NULL AUTO_INCREMENT ,
  `curso_id_curso` INT NOT NULL ,
  `disciplina_id_disciplina` INT NOT NULL ,
  `disciplina_eixodeformacao_id_eixodeformacao` INT NOT NULL ,
  `professor_id_professor` INT NOT NULL ,
  `professor_id_professor1` INT NOT NULL ,
  `usuario_id_usuario` BIGINT(20) NOT NULL ,
  `ementa` VARCHAR(45) NOT NULL ,
  `cargahoraria` VARCHAR(45) NOT NULL ,
  `competencia1` VARCHAR(45) NOT NULL ,
  `competencia2` VARCHAR(45) NOT NULL ,
  `competencia3` VARCHAR(45) NOT NULL ,
  `competencia4` VARCHAR(45) NOT NULL ,
  `competencia5` VARCHAR(45) NOT NULL ,
  `conteudo_ementa` VARCHAR(150) NOT NULL ,
  `referenciabas1` VARCHAR(45) NOT NULL ,
  `referenciabas2` VARCHAR(45) NOT NULL ,
  `referenciabas3` VARCHAR(45) NOT NULL ,
  `referenciacompl1` VARCHAR(45) NOT NULL ,
  `referenciacompl2` VARCHAR(45) NOT NULL ,
  `ativo` varchar(1) NULL ,
  PRIMARY KEY (`id_planodeensino`) ,
  INDEX `fk_planodeensino_curso1_idx` (`curso_id_curso` ASC) ,
  INDEX `fk_planodeensino_disciplina1_idx` (`disciplina_id_disciplina` ASC) ,
  INDEX `fk_planodeensino_professor1_idx` (`professor_id_professor` ASC) ,
  INDEX `fk_planodeensino_usuario1_idx` (`usuario_id_usuario` ASC) ,
  INDEX `fk_planodeensino_professor2_idx` (`professor_id_professor1` ASC) ,
  INDEX `fk_planodeensino_dispk` (`disciplina_eixodeformacao_id_eixodeformacao` ASC) ,
  CONSTRAINT `fk_planodeensino_curso1`
    FOREIGN KEY (`curso_id_curso` )
    REFERENCES `ementas`.`curso` (`id_curso` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_planodeensino_disciplina1`
    FOREIGN KEY (`disciplina_id_disciplina` )
    REFERENCES `ementas`.`disciplina` (`id_disciplina` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_planodeensino_professor1`
    FOREIGN KEY (`professor_id_professor` )
    REFERENCES `ementas`.`professor` (`id_professor` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_planodeensino_usuario1`
    FOREIGN KEY (`usuario_id_usuario` )
    REFERENCES `ementas`.`usuario` (`idusuario` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_planodeensino_professor2`
    FOREIGN KEY (`professor_id_professor1` )
    REFERENCES `ementas`.`professor` (`id_professor` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_planodeensino_disciplina_eixodeformacao_ideixodeformacao`
    FOREIGN KEY (`disciplina_eixodeformacao_id_eixodeformacao` )
    REFERENCES `ementas`.`eixodeformacao` (`id_eixodeformacao` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

ALTER TABLE planodeensino ADD FOREIGN KEY (curso_id_curso) REFERENCES curso(id_curso);

ALTER TABLE planodeensino ADD FOREIGN KEY (disciplina_id_disciplina) REFERENCES disciplina(id_disciplina);

ALTER TABLE planodeensino ADD FOREIGN KEY (disciplina_eixodeformacao_id_eixodeformacao) REFERENCES eixodeformacao(id_eixodeformacao);

ALTER TABLE planodeensino ADD FOREIGN KEY (professor_id_professor) REFERENCES professor(id_professor);

ALTER TABLE planodeensino ADD FOREIGN KEY (professor_id_professor1) REFERENCES professor(id_professor);

ALTER TABLE planodeensino ADD FOREIGN KEY (usuario_id_usuario) REFERENCES usuario(idusuario);



