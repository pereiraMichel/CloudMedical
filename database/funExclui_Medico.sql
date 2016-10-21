-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` FUNCTION `EXCLUI_MEDICO`(id INT) RETURNS tinyint(1)
BEGIN
DECLARE RET BOOLEAN;
DELETE FROM medicos WHERE idMedico = id;

IF(SELECT COUNT(idMedico) FROM medicos WHERE idMedico = id) = 0 THEN
	SET RET = TRUE;
ELSE
	SET RET = FALSE;

END IF;


RETURN RET;
END