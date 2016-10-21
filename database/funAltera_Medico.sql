-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` FUNCTION `ALTERA_MEDICO`(id INT, nome VARCHAR(85), CRM VARCHAR(45), pagto INT, valorPagto DOUBLE, codigo INT) RETURNS tinyint(1)
BEGIN

DECLARE RET boolean;

UPDATE medicos SET nomeMedico = nome, crm = CRM, tipoPagamento = pagto, valor = valorPagto, codUsuario = codigo WHERE idMedico = id; 

IF (SELECT COUNT(idMedico) FROM medicos WHERE idMedico = id) > 0 THEN 
	SET RET = TRUE;
ELSE 
	SET RET = FALSE;
END IF;



RETURN TRUE;
END