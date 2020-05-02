package Transport4Future.TokenManagement.Data.Attribute;

import java.util.regex.Pattern;

import Transport4Future.TokenManagement.Exception.TokenManagementException;

public abstract class Attribute {
	protected String patternString;
	protected String errorMessage;
	protected String value;
	
	protected   String validatePattern(String Data)throws TokenManagementException {
		Pattern pattern;
		pattern = Pattern.compile(this.patternString);
		if (!pattern.matcher(Data).matches()) {
			throw new TokenManagementException(this.errorMessage);	
		}
		return Data;
	}
	
	public String getValue() {
		return value;
	}
}
