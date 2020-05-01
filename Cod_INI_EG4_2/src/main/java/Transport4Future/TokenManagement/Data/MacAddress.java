package Transport4Future.TokenManagement.Data;

import Transport4Future.TokenManagement.Exception.TokenManagementException;

public class MacAddress extends Attribute {

	MacAddress(String Data) throws TokenManagementException{
		
		this.patternString= "([a-fA-F0-9]{2}[:-]){5}[a-fA-F0-9]{2}$";
		this.errorMessage="Error: invalid MAC Address data in JSON structure.";
		this.value=validatePattern(Data);
	}
}
