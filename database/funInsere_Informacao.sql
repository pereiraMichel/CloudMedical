-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` FUNCTION `INSERE_INFORMACAO`(id int, valor varchar(150), cod int) RETURNS tinyint(1)
BEGIN

	DECLARE RET boolean;

	INSERT INTO informacoes (idInformacao, texto, codMedico)
	VALUES (id, valor, cod);

	IF (SELECT COUNT(idInformacao) FROM informacao WHERE idInformacao = id) > 0 THEN 
		SET RET = TRUE;
	ELSE 
		SET RET = FALSE;
	END IF;


RETURN RET;
END