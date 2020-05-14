package Transport4Future.TokenManagement.Parser;

import javax.json.JsonObject;

import Transport4Future.TokenManagement.Data.Token;
import Transport4Future.TokenManagement.Data.TokenRequest;
import Transport4Future.TokenManagement.Exception.TokenManagementException;

public class JSONTokenRequestParser {
	public TokenRequest createTokenRequest(String InputFile) throws TokenManagementException {
		JSONFileParser myFile = new JSONFileParser();
		JsonObject jsonLicense = myFile.parseJSONFile(InputFile);	
		TokenRequest req;
		String deviceName = "";
		String typeOfDevice = "";
		String driverVersion = "";
		String supportEMail = "";
		String serialNumber = "";
		String macAddress = "";			


		
		try {			
			deviceName = jsonLicense.getString("Device Name");
			typeOfDevice = jsonLicense.getString("Type of Device");
			driverVersion = jsonLicense.getString("Driver Version");
			supportEMail = jsonLicense.getString("Support e-mail");
			serialNumber = jsonLicense.getString("Serial Number");
			macAddress = jsonLicense.getString("MAC Address");			
		} catch (Exception pe) {
			throw new TokenManagementException("Error: invalid input data in JSON structure.");
		}
		return new TokenRequest(deviceName, typeOfDevice, driverVersion, supportEMail, serialNumber, macAddress);
}
}