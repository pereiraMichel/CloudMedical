-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` FUNCTION `RETORNATPTELEFONES`(ID INT) RETURNS varchar(45) CHARSET utf8
BEGIN
	DECLARE TipoTelefone VARCHAR(45);

	SELECT tipo INTO TipoTelefone FROM tipotelefone WHERE idTipoTelefone = ID;

RETURN TipoTelefone;
END