-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` FUNCTION `INSERE_TERCEIRIZADOS`(id int, nome varchar(85), doc varchar(65), serv varchar(85), cont varchar(150), pagto double, ctp int) RETURNS tinyint(1)
BEGIN
	DECLARE RET BOOLEAN;

	INSERT INTO terceirizados (idTerceirizado, nomeTerceirizado, documento, servicos, contatos, valorPagto, codTipoPagamento)
	VALUES (id, nome, doc, serv, cont, pagto, ctp);

	IF(SELECT COUNT(idTerceirizado) FROM terceirizados WHERE idTerceirizado = id) > 0 THEN
		SET RET = TRUE;
	ELSE
		SET RET = FALSE;

	END IF;

RETURN RET;
END