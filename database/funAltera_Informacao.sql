-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` FUNCTION `ALTERA_INFORMACAO`(id int, textoId varchar(150), cod int) RETURNS tinyint(1)
BEGIN
	DECLARE RET boolean;
	UPDATE informacoes SET texto = textoId, codMedico = cod WHERE idMedico = id;

	IF (SELECT COUNT(idInformacao) FROM informacoes WHERE idInformacoes = id) > 0 THEN 
		SET RET = TRUE;
	ELSE 
		SET RET = FALSE;
	END IF;

RETURN RET;
END