-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` FUNCTION `ALTERA_TELEFONE`(id int, tel varchar(65), codtp int, cod int) RETURNS tinyint(1)
BEGIN
	DECLARE RET BOOLEAN;

	UPDATE telefone SET telefone = tel, codTipoTelefone = cdtp, codMedico = cod WHERE idTelefone = id;

	IF (SELECT COUNT(idTelefone) FROM telefone WHERE idTelefone = id) > 0 THEN
		SET RET = TRUE;
	ELSE
		SET RET = FALSE;
	END IF;

RETURN RET;
END