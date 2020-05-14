package Transport4Future.TokenManagement.Parser;

import java.util.HashMap;

import javax.json.JsonObject;

import Transport4Future.TokenManagement.Data.Token;
import Transport4Future.TokenManagement.Exception.TokenManagementException;

public class JSONTokenParser {
	
	public HashMap<String, String> createRequestToken(String InputFile) throws TokenManagementException {
		Token myToken;
		JSONFileParser myFile = new JSONFileParser();
		JsonObject jsonLicense = myFile.parseJSONFile(InputFile);	
		
		HashMap<String, String> myMap = new HashMap<String, String> ();
		String tokenRequest = "Token Request";
		String email = "Notification e-mail";
		String date = "Request Date";		
		
		try {			
			myMap.put(tokenRequest, jsonLicense.getString("Token Request"));
			myMap.put(email, jsonLicense.getString("Notification e-mail"));
			myMap.put(date,jsonLicense.getString("Request Date"));					
		} catch (Exception pe) {
			throw new TokenManagementException("Error: invalid input data in JSON structure.");
		}
		
	 
		return myMap;
	}
}
