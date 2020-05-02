package Transport4Future.TokenManagement.Data.Attribute;

import Transport4Future.TokenManagement.Exception.TokenManagementException;

public class TypeOfDevice extends Attribute {

	public TypeOfDevice(String Value) throws TokenManagementException{
		
		this.value=this.validate(Value);
	}
	
	private String validate(String Value)throws TokenManagementException{
		
		if (!(Value.equalsIgnoreCase("Sensor") || Value.equalsIgnoreCase("Actuator"))) {
			throw new TokenManagementException("Error: invalid type of sensor.");	
		} 
		return Value;
	}
}
