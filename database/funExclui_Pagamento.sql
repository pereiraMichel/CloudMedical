-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` FUNCTION `EXCLUI_PAGAMENTO`(id int) RETURNS tinyint(1)
BEGIN

	DECLARE RET BOOLEAN;

	DELETE FROM pagamento WHERE idPagamento = id;

	IF(SELECT COUNT(idPagamento) FROM pagamento WHERE idPagamento = id) = 0 THEN
		SET RET = TRUE;
	ELSE
		SET RET = FALSE;

	END IF;

RETURN RET;
END