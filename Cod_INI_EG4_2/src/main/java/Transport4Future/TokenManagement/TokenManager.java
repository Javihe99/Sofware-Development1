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

import java.lang.reflect.Type;


public class TokenManager implements ITokenManagement {

	public String TokenRequestGeneration (String InputFile) throws TokenManagementException{

		JSONFileParser myFile = new JSONFileParser();
		JsonObject jsonLicense = myFile.parseJSONFile(InputFile);	
		TokenRequest req = createTokenRequest(jsonLicense);
		String hex = generateHashMD5(req);
		HashMap<String, TokenRequest> clonedMap = loadTokenRequestToMemory();
        storeTokenRequest(req, hex, clonedMap);
		//Devolver el hash
		return hex;
	}

	private void storeTokenRequest(TokenRequest req, String hex, HashMap<String, TokenRequest> clonedMap)
			throws TokenManagementException {
		if (clonedMap==null) {
        	clonedMap = new HashMap<String, TokenRequest>();
        	clonedMap.put (hex, req);	        	
        }
        else if (!clonedMap.containsKey(hex)){
        	clonedMap.put (hex, req);
        }

		Gson gson = new Gson();
		// Guardar el Tokens Requests Store actualizado
		String jsonString = gson.toJson(clonedMap);
        FileWriter fileWriter;
    	String storePath = System.getProperty("user.dir") + "/Store/tokenRequestsStore.json";
		try {
			fileWriter = new FileWriter(storePath);
	        fileWriter.write(jsonString);
	        fileWriter.close();
		} catch (IOException e) {
			throw new TokenManagementException("Error: Unable to save a new token in the internal licenses store");
		}
	}

	private HashMap<String, TokenRequest> loadTokenRequestToMemory() {
		//Generar un HashMap para guardar los objetos

		HashMap<String, TokenRequest> clonedMap;

		//Tengo que cargar el almacen de tokens request en memoria y añadir el nuevo si no existe
		try {
			Gson gson = new Gson();
			String jsonString;
			String storePath = System.getProperty("user.dir") + "/Store/tokenRequestsStore.json";
			
			Object object = gson.fromJson(new FileReader(storePath), Object.class);
			jsonString = gson.toJson(object);	
	        Type type = new TypeToken<HashMap<String, TokenRequest>>(){}.getType();
	        clonedMap = gson.fromJson(jsonString, type);
		} catch (Exception e) {
			clonedMap=null;
		}
		return clonedMap;
	}

	private String generateHashMD5(TokenRequest req) throws TokenManagementException {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new TokenManagementException("Error: no such hashing algorithm.");
		}
		
		String input =  "Stardust" + "-" + req.toString();
		
		md.update(input.getBytes(StandardCharsets.UTF_8));
		byte[] digest = md.digest();

		// Beware the hex length. If MD5 -> 32:"%032x", but for instance, in SHA-256 it should be "%064x" 
		String hex = String.format("%32x", new BigInteger(1, digest));
		return hex;
	}

	private String generateHashSHA256(String dataToSign) throws TokenManagementException {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new TokenManagementException("Error: no such hashing algorithm.");
		}

		md.update(dataToSign.getBytes(StandardCharsets.UTF_8));
		byte[] digest = md.digest();

		// Beware the hex length. If MD5 -> 32:"%032x", but for instance, in SHA-256 it should be "%064x"
		String signature = String.format("%064x", new BigInteger(1, digest));
		return signature;
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

		req = new TokenRequest(deviceName, typeOfDevice, driverVersion, supportEMail, serialNumber, macAddress);
		return req;
	}

	
	
	
	public String RequestToken (String InputFile) throws TokenManagementException{

		JSONFileParser myFile = new JSONFileParser();
		JsonObject jsonLicense = myFile.parseJSONFile(InputFile);	
		Token myToken = createRequestToken(jsonLicense);

		
		String dataToSign =myToken.getHeader() + myToken.getPayload();
		String signature = generateHashSHA256(dataToSign);

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

		myToken = new Token (tokenRquest, date, email);
		return myToken;
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
			result = isValid(tokenFound);
		}
		return result;
	}
}