-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` FUNCTION `INSERE_PAGAMENTO`(id int, valor double, comp varchar(45), valorp double, cod int, dataP date, codT int) RETURNS tinyint(1)
BEGIN
	DECLARE RET BOOLEAN;

	INSERT INTO pagamento (idPagamento, valorPago, competencia, valorPendente, codMedico, dataPagamento, codTerceirizado)
	VALUES (id, valor, comp, valorp, cod, dataP, codT);

	IF (SELECT COUNT(idPagamento) FROM pagamento WHERE idPagamento = id) > 0 THEN 
		SET RET = TRUE;
	ELSE 
		SET RET = FALSE;
	END IF;


RETURN RET;
END