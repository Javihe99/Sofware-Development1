package Transport4Future.TokenManagement.Parser;

import java.util.HashMap;

import javax.json.JsonObject;

import Transport4Future.TokenManagement.Data.Token;
import Transport4Future.TokenManagement.Data.TokenRequest;
import Transport4Future.TokenManagement.Exception.TokenManagementException;

public class JSONTokenRequestParser {
	public HashMap<String, String> createTokenRequest(String InputFile) throws TokenManagementException {
		JSONFileParser myFile = new JSONFileParser();
		JsonObject jsonLicense = myFile.parseJSONFile(InputFile);	
		
		HashMap<String, String> myMap = new HashMap<String, String> ();
		String deviceName = "Device Name";
		String typeOfDevice = "Type of Device";
		String driverVersion = "Driver Version";
		String supportEMail = "Support e-mail";
		String serialNumber = "Serial Number";
		String macAddress = "MAC Address";			


		
		try {			
			myMap.put(deviceName, jsonLicense.getString("Device Name"));
			myMap.put(typeOfDevice, jsonLicense.getString("Type of Device"));
			myMap.put(driverVersion, jsonLicense.getString("Driver Version"));
			myMap.put(supportEMail, jsonLicense.getString("Support e-mail"));
			myMap.put(serialNumber, jsonLicense.getString("Serial Number"));
			myMap.put(macAddress, jsonLicense.getString("MAC Address"));
			
		} catch (Exception pe) {
			throw new TokenManagementException("Error: invalid input data in JSON structure.");
		}
		return myMap;
}
}