-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` FUNCTION `ALTERA_TERCEIRIZADOS`(id int, nome varchar(85), doc varchar(65), serv varchar(85), cont varchar(150), pagto double, ctp int) RETURNS tinyint(1)
BEGIN
	DECLARE RET BOOLEAN;

	UPDATE terceirizados SET nomeTerceirizado = nome, documento = doc, servicos = serv, contatos = cont, valorPagto = pagto, codTipoPagamento = ctp
	WHERE idTerceirizado = id;

	IF(SELECT COUNT(idTerceirizado) FROM terceirizados WHERE idTerceirizado = id) > 0 THEN
		SET RET = TRUE;
	ELSE
		SET RET = FALSE;

	END IF;

RETURN RET;
END