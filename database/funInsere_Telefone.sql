-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` FUNCTION `INSERE_TELEFONE`(id int, tel varchar(65), codtp int, cod int) RETURNS tinyint(1)
BEGIN
	DECLARE RET BOOLEAN;

	INSERT INTO telefone (idTelefone, telefone, codTipoTelefone, codMedico)
	VALUES (id, tel, cdtp, cod);

	IF (SELECT COUNT(idTelefone) FROM telefone WHERE idTelefone = id) > 0 THEN
		SET RET = TRUE;
	ELSE
		SET RET = FALSE;

	END IF;

RETURN RET;
END