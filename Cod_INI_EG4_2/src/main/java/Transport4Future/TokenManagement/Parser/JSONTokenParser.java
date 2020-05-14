package Transport4Future.TokenManagement.Parser;

import javax.json.JsonObject;

import Transport4Future.TokenManagement.Data.Token;
import Transport4Future.TokenManagement.Exception.TokenManagementException;

public class JSONTokenParser {
	public Token createRequestToken(String InputFile) throws TokenManagementException {
		Token myToken;
		JSONFileParser myFile = new JSONFileParser();
		JsonObject jsonLicense = myFile.parseJSONFile(InputFile);	
		String tokenRquest = "";
		String email = "";
		String date = "";		
		
		try {			
			tokenRquest = jsonLicense.getString("Token Request");
			email = jsonLicense.getString("Notification e-mail");
			date = jsonLicense.getString("Request Date");					
		} catch (Exception pe) {
			throw new TokenManagementException("Error: invalid input data in JSON structure.");
		}

	 
		return new Token (tokenRquest, date, email);
	}
}
