-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` FUNCTION `INSERE_MEDICO`(id INT, nome VARCHAR(85), Crm VARCHAR (45), tipoPgto VARCHAR(45), valor double, codUser int) RETURNS tinyint(1)
BEGIN

DECLARE RET boolean;

INSERT INTO medicos (idMedico, nomeMedico, crm, tipoPagamento, valor, codUsuario)
VALUES (id, nome, Crm, tipoPgto, valor, codUser);

IF (SELECT COUNT(idMedico) FROM medicos WHERE idMedico = id) > 0 THEN 
	SET RET = TRUE;
ELSE 
	SET RET = FALSE;
END IF;


RETURN RET;
END