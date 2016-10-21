-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` FUNCTION `EXCLUI_INFORMACAO`(id int) RETURNS tinyint(1)
BEGIN
	DECLARE RET boolean;

		DELETE FROM informacoes WHERE idInformacao = id;

	IF (SELECT COUNT(idInformacao) FROM informacoes WHERE idInformacao = id) = 0 THEN 
		SET RET = TRUE;
	ELSE 
		SET RET = FALSE;

	END IF;

RETURN RET;
END