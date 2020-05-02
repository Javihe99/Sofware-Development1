package Transport4Future.TokenManagement.Data.Attribute;

import Transport4Future.TokenManagement.Exception.TokenManagementException;

public class SerialNumber extends Attribute {

	public SerialNumber(String Data) throws TokenManagementException{
		
		this.patternString= "([A-Za-z0-9-]{1,})";
		this.errorMessage="Error: invalid String length for serial number.";
		this.value=validatePattern(Data);
	}
}
