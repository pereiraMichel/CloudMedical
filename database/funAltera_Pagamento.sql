-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` FUNCTION `ALTERA_PAGAMENTO`(id int, valor double, comp varchar(45), valorp double, cod int, dataP date, codT int) RETURNS tinyint(1)
BEGIN
	DECLARE RET BOOLEAN;

	UPDATE pagamento SET valorPagto = valor, competencia = comp, valorPendente = valorp, codMedico = cod, dataPagamento = dataP, codTerceirizado = codT WHERE idPagamento = id;

	IF(SELECT COUNT(idPagamento) FROM pagamento WHERE idPagamento = id) > 0 THEN
		SET RET = TRUE;
	ELSE
		SET RET = FALSE;

	END IF;


RETURN RET;
END