package Transport4Future.TokenManagement.Data;

import Transport4Future.TokenManagement.Exception.TokenManagementException;

public class DeviceName extends Attribute {
	
	DeviceName(String Data) throws TokenManagementException{
		
		this.patternString= "([A-Za-z0-9-\\s]{1,20})";
		this.errorMessage="Error: invalid String length for device name.";
		this.value=validatePattern(Data);
	}
	
}