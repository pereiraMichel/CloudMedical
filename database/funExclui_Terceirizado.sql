-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` FUNCTION `EXCLUI_TERCEIRIZADOS`(id int) RETURNS tinyint(1)
BEGIN

	DECLARE RET BOOLEAN;

		DELETE FROM terceirizados WHERE idTerceirizado = id;

	IF (SELECT COUNT(idTerceirizado) FROM terceirizados WHERE idTerceirizado = id) = 0 THEN 
		SET RET = TRUE;
	ELSE 
		SET RET = FALSE;

	END IF;


RETURN RET;
END