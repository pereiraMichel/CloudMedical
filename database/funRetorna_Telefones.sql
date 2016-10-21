-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` FUNCTION `RETORNATELEFONES`(ID INT) RETURNS varchar(65) CHARSET utf8
BEGIN
	DECLARE Telefone varchar(65);

	SELECT t.telefone INTO Telefone FROM telefone t
	INNER JOIN tipotelefone tp ON t.codTipoTelefone = tp.idTipoTelefone
	WHERE t.codMedico = ID;

RETURN Telefone;
END