package Transport4Future.TokenManagement.Data;

import Transport4Future.TokenManagement.Exception.TokenManagementException;

public class Fecha extends Attribute {

	
	Fecha(String Data) throws TokenManagementException{
		
		this.patternString= "(0[1-9]|[1-2][0-9]|3[0-1])-(0[1-9]|1[0-2])-[0-9]{4}\\s(2[0-3]|[01][0-9]):[0-5][0-9]:[0-5][0-9]";
		this.errorMessage="Error: invalid date data in JSON structure.";
		this.value=validatePattern(Data);
	}

}
