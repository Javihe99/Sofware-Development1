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
	
	
	
	private void checkInitialTokenInformationFormat(TokenRequest Request) throws TokenManagementException {

		//before to that I have to check that all the fields are correct
		checkDeviceName(Request.getDeviceName());
		checkSerialNumber(Request.getSerialNumber());
		checkDriveVersion(Request.getDriverVersion());
		checkEmailPattern(Request.getSupportEMail());
		checkTypeOfDevice(Request.getTypeOfDevice());
		checkMacPattern(Request.getMacAddress());
	
		
		
	}
	private void checkDeviceName(String Request)throws TokenManagementException {
		// Length check for device name
				if (Request.length() < 1 || Request.length() > 20) {
					throw new TokenManagementException("Error: invalid String length for device name.");	
				}
	}
	private void checkSerialNumber(String Request)throws TokenManagementException {
		// Length check for serial number
				Pattern serialNumberPattern = Pattern.compile("([A-Za-z0-9-]{1,})");
				if  (!serialNumberPattern.matcher(Request).matches()) {
					throw new TokenManagementException("Error: invalid String length for serial number.");	
				}	
	
	}
	private void checkDriveVersion(String Request)throws TokenManagementException {
		// Length check for driver version
				Pattern driverPattern = Pattern.compile("([a-zA-Z0-9]{1}[A-Za-z0-9\\.]{0,24})");
				if ((Request.length() < 1) || (Request.length() > 25) || (!driverPattern.matcher(Request).matches())) {
					throw new TokenManagementException("Error: invalid String length for driver version.");	
				}
	
	}
	private void checkTypeOfDevice(String Request)throws TokenManagementException {

		// Length check for license request
		if (!(Request.equalsIgnoreCase("Sensor") || Request.equalsIgnoreCase("Actuator"))) {
			throw new TokenManagementException("Error: invalid type of sensor.");	
		} 
		
	}
	private void checkMacPattern(String Request)throws TokenManagementException {

		Pattern macPattern = Pattern.compile("([a-fA-F0-9]{2}[:-]){5}[a-fA-F0-9]{2}$");
		if (!macPattern.matcher(Request).matches()) {
			throw new TokenManagementException("Error: invalid MAC Address data in JSON structure.");	
		}
		
	}
	
	private byte[] prepareMD5(String req)  throws TokenManagementException{
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new TokenManagementException("Error: no such hashing algorithm.");
		}
		
		String input =  "Stardust" + "-" + req;
		
		md.update(input.getBytes(StandardCharsets.UTF_8));
	
		return md.digest();
		
		
	}
	
	
	public String TokenRequestGeneration (String InputFile) throws TokenManagementException{
		String fileContents = "";

		//BufferedReader reader;
		fileContents = bufferReaderToFileContents(InputFile, fileContents);
		
		JsonObject jsonLicense = transformFileContentsIntoJSONobject(fileContents);
		
		
		TokenRequest req=addTokenRequest(jsonLicense);
		
		checkInitialTokenInformationFormat(req);
	
		byte[] digest = prepareMD5(req.toString());

		// Beware the hex length. If MD5 -> 32:"%032x", but for instance, in SHA-256 it should be "%064x" 
		String hex = String.format("%32x", new BigInteger(1, digest));
		
		//Generar un HashMap para guardar los objetos
		Gson gson = new Gson();
		String jsonString;
		HashMap<String, TokenRequest> clonedMap;
		String storePath = System.getProperty("user.dir") + "/Store/tokenRequestsStore.json";

		//Tengo que cargar el almacen de tokens request en memoria y añadir el nuevo si no existe
		try {
			Object object = gson.fromJson(new FileReader(storePath), Object.class);
			jsonString = gson.toJson(object);	
	        Type type = new TypeToken<HashMap<String, TokenRequest>>(){}.getType();
	        clonedMap = gson.fromJson(jsonString, type);
		} catch (Exception e) {
			clonedMap=null;
		}
	
        if (clonedMap==null) {
        	clonedMap = new HashMap ();
        	clonedMap.put (hex, req);	        	
        }
        else if (!clonedMap.containsKey(hex)){
        	clonedMap.put (hex, req);
        }

		
		// Guardar el Tokens Requests Store actualizado
		jsonString = gson.toJson(clonedMap);
        FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(storePath);
	        fileWriter.write(jsonString);
	        fileWriter.close();
		} catch (IOException e) {
			throw new TokenManagementException("Error: Unable to save a new token in the internal licenses store");
		}

		//Devolver el hash
		return hex;
	}
	private TokenRequest addTokenRequest(JsonObject jsonLicense)throws TokenManagementException {
		
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
	
	
	private void checkTokenRequestInformationFormat(Token TokenToVerify) throws TokenManagementException {
        
		checkDeviceLength(TokenToVerify.getDevice());
			
		checkEmailPattern(TokenToVerify.getNotificationEmail());

		checkRequestDatePattern(TokenToVerify.getRequestDate());

		chargeTokenRequestStore(TokenToVerify);
	}

	private void chargeTokenRequestStore(Token TokenToVerify) throws TokenManagementException {
		//Generar un HashMap para guardar los objetos
		Gson gson = new Gson();
		HashMap<String, TokenRequest> clonedMap = null;
		String storePath = System.getProperty("user.dir") + "/Store/tokenRequestsStore.json";
		clonedMap = recoverTokenRequestStore(gson, clonedMap, storePath);
        notExistsClonedMap(TokenToVerify, clonedMap);
	}

	private void notExistsClonedMap(Token TokenToVerify, HashMap<String, TokenRequest> clonedMap)throws TokenManagementException {
		if (clonedMap==null) {
			throw new TokenManagementException("Error: Token Request Not Previously Registered");	        	
        }
        else if (!clonedMap.containsKey(TokenToVerify.getDevice())){
			throw new TokenManagementException("Error: Token Request Not Previously Registered");	        	
        }
	}

	private HashMap<String, TokenRequest> recoverTokenRequestStore(Gson gson, HashMap<String, TokenRequest> clonedMap,String storePath) throws TokenManagementException {
		String jsonString;
		try {
			Object object = gson.fromJson(new FileReader(storePath), Object.class);
			jsonString = gson.toJson(object);	
	        Type type = new TypeToken<HashMap<String, TokenRequest>>(){}.getType();
	        clonedMap = gson.fromJson(jsonString, type);
		} catch (Exception e) {
			clonedMap=null;
			throw new TokenManagementException("Error: unable to recover Token Requests Store.");	
		}
		return clonedMap;
	}

	private void checkRequestDatePattern(String TokenToVerify) throws TokenManagementException {
		Pattern datePattern = Pattern.compile("(0[1-9]|[1-2][0-9]|3[0-1])-(0[1-9]|1[0-2])-[0-9]{4}\\s(2[0-3]|[01][0-9]):[0-5][0-9]:[0-5][0-9]");
		if (!datePattern.matcher(TokenToVerify).matches()) {
			throw new TokenManagementException("Error: invalid date data in JSON structure.");	
		}
	}

	private void checkEmailPattern(String TokenToVerify) throws TokenManagementException {
		Pattern mailPattern = Pattern.compile("(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*:(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)(?:,\\s*(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*))*)?;\\s*)");
		if (!mailPattern.matcher(TokenToVerify).matches()) {
			throw new TokenManagementException("Error: invalid E-mail data in JSON structure.");	
		}
	}

	private void checkDeviceLength(String TokenToVerify) throws TokenManagementException {
		Pattern devicePattern = Pattern.compile("([A-Fa-f0-9]{32})");
		if  (!devicePattern.matcher(TokenToVerify).matches()) {
			throw new TokenManagementException("Error: invalid Device in token request.");	
		}
	}
	
	public String RequestToken (String InputFile) throws TokenManagementException{
		Token myToken = null;
		String fileContents = "";
		
		fileContents = bufferReaderToFileContents(InputFile, fileContents);

		JsonObject jsonLicense = transformFileContentsIntoJSONobject(fileContents);
		
		myToken = myTokenGeneration(jsonLicense);
		checkTokenRequestInformationFormat(myToken);
		encodeMyToken (myToken);
		saveTokenStore(myToken);
		return myToken.getTokenValue();
	}

	private void saveTokenStore(Token myToken) throws TokenManagementException {
		TokensStore myStore = new TokensStore ();
		myStore.Add(myToken);
	}
	
	private byte[] prepareToCode(Token myToken)  throws TokenManagementException{
		String dataToSign =getDataToSign(myToken);
		MessageDigest md = defineHashFunction();
		md.update(dataToSign.getBytes(StandardCharsets.UTF_8));
		return md.digest();
		
		
	}
	
	
	public void encodeMyToken (Token myToken) throws TokenManagementException{
		
		
		byte[] digest = prepareToCode(myToken);

		// Beware the hex length. If MD5 -> 32:"%032x", but for instance, in SHA-256 it should be "%064x"
		String signature = String.format("%064x", new BigInteger(1, digest));
		myToken.setSignature(signature);
		
		String stringToEncode = myToken.getHeader() + myToken.getPayload() + myToken.getSignature();
		String encodedString = Base64.getUrlEncoder().encodeToString(stringToEncode.getBytes());
		myToken.setTokenValue(encodedString);
	
	}

	private String getDataToSign(Token myToken) {
		return myToken.getHeader() + myToken.getPayload();
	}

	private MessageDigest defineHashFunction() throws TokenManagementException {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new TokenManagementException("Error: no such hashing algorithm.");
		}
		return md;
	}

	private Token myTokenGeneration(JsonObject jsonLicense)
			throws TokenManagementException {
		String tokenRquest = "";
		String email = "";
		String date = "";	
		Token myToken;
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

	private JsonObject transformFileContentsIntoJSONobject(String fileContents) throws TokenManagementException {
		JsonObject jsonLicense = null;
		try {
			jsonLicense = Json.createReader(new StringReader(fileContents)).readObject();	
		}catch (JsonParsingException ex) {
			throw new TokenManagementException("Error: JSON object cannot be created due to incorrect representation");
		}
		return jsonLicense;
	}

	private String bufferReaderToFileContents(String InputFile, String fileContents)
			throws TokenManagementException {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(InputFile));
		} catch (FileNotFoundException e) {
			throw new TokenManagementException("Error: input file not found.");
		}
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				fileContents += line;
			}
		} catch (IOException e) {
			throw new TokenManagementException("Error: input file could not be accessed.");
		}
		try {
			reader.close();
		} catch (IOException e) {
			throw new TokenManagementException("Error: input file could not be closed.");
		}
		return fileContents;
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

		result = existToken(result, tokenFound);
		return result;
	}

	private boolean existToken(boolean result, Token tokenFound) {
		if (tokenFound!=null){
			result = isValid(tokenFound);
		}
		return result;
	}
}