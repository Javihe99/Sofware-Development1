package Transport4Future.TokenManagement.Data;

import Transport4Future.TokenManagement.Exception.TokenManagementException;

public class SerialNumber extends Attribute {

	SerialNumber(String Data) throws TokenManagementException{
		
		this.patternString= "([A-Za-z0-9-]{1,})";
		this.errorMessage="Error: invalid String length for serial number.";
		this.value=validatePattern(Data);
	}
}
