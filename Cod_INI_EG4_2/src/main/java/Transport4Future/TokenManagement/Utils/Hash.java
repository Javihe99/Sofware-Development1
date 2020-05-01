package Transport4Future.TokenManagement.Utils;

import Transport4Future.TokenManagement.Data.TokenRequest;
import Transport4Future.TokenManagement.Exception.TokenManagementException;

public class Hash extends MainHashCode {
	
	


	public String generateHashMD5(TokenRequest req) throws TokenManagementException {
		
		String key="Stardust-";
		String algorithm="MD5";
		String type="%032x";
		String input =  key + req.toString();
		
		return generateHash(algorithm, type, input);
	}

	
	public String generateHashSHA256(String dataToSign) throws TokenManagementException {
		
		String algorithm="SHA-256";
		String type="%064x";
		
		return generateHash(algorithm, type, dataToSign);
	}

}
