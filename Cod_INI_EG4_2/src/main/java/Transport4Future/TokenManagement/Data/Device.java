package Transport4Future.TokenManagement.Data;

import Transport4Future.TokenManagement.Exception.TokenManagementException;

public class Device extends Attribute {

	
	Device(String Data) throws TokenManagementException{
		
		this.patternString= "([A-Fa-f0-9]{32})";
		this.errorMessage="Error: invalid Device in token request.";
		this.value=validatePattern(Data);
	}

}
