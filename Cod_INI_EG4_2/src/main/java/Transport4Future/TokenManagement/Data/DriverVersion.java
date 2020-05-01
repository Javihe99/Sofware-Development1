package Transport4Future.TokenManagement.Data;

import Transport4Future.TokenManagement.Exception.TokenManagementException;

public class DriverVersion extends Attribute {
	
	DriverVersion(String Data) throws TokenManagementException{
		
		this.patternString= "([a-zA-Z0-9]{1}[A-Za-z0-9\\.]{1,25})";
		this.errorMessage="Error: invalid String length for driver version.";
		this.value=validatePattern(Data);
	}
}
