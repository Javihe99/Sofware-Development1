package Transport4Future.TokenManagement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Pattern;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.stream.JsonParsingException;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;

import Transport4Future.TokenManagement.Data.Token;
import Transport4Future.TokenManagement.Data.TokenRequest;
import Transport4Future.TokenManagement.Exception.TokenManagementException;
import Transport4Future.TokenManagement.Parser.JSONFileParser;
import Transport4Future.TokenManagement.Store.TokenRequestStore;
import Transport4Future.TokenManagement.Store.TokensStore;
import Transport4Future.TokenManagement.Utils.Hash;

import java.lang.reflect.Type;


public class TokenManager implements ITokenManagement {

	public String TokenRequestGeneration (String InputFile) throws TokenManagementException{

		JSONFileParser myFile = new JSONFileParser();
		JsonObject jsonLicense = myFile.parseJSONFile(InputFile);	
		TokenRequest req = createTokenRequest(jsonLicense);
		Hash myHash = new Hash();
		String hex = myHash.generateHashMD5(req);
		TokenRequestStore myStore = new TokenRequestStore();
        myStore.storeTokenRequest(req, hex);
		//Devolver el hash
		return hex;
	}

	
	private TokenRequest createTokenRequest(JsonObject jsonLicense) throws TokenManagementException {
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

	
	public String RequestToken (String InputFile) throws TokenManagementException{

		JSONFileParser myFile = new JSONFileParser();
		JsonObject jsonLicense = myFile.parseJSONFile(InputFile);	
		Token myToken = createRequestToken(jsonLicense);

		
		String dataToSign =myToken.getHeader() + myToken.getPayload();
		Hash myHash = new Hash();
		String signature = myHash.generateHashSHA256(dataToSign);

		myToken.setSignature(signature);
		
		String stringToEncode = myToken.getHeader() + myToken.getPayload() + myToken.getSignature();
		String encodedString = Base64.getUrlEncoder().encodeToString(stringToEncode.getBytes());
		myToken.setTokenValue(encodedString);
		
		TokensStore myStore = new TokensStore ();
		myStore.Add(myToken);
		
		return myToken.getTokenValue();
	}

	
	private Token createRequestToken(JsonObject jsonLicense) throws TokenManagementException {
		Token myToken;
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

	private boolean isValid (Token tokenFound) {
		if ((!tokenFound.isExpired()) && (tokenFound.isGranted())){
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean VerifyToken (String Token) throws TokenManagementException{
		boolean result = false;
		TokensStore myStore = new TokensStore ();
		
		Token tokenFound = myStore.Find(Token);

		if (tokenFound!=null){
			return isValid(tokenFound);
		}
		return result;
	}
}