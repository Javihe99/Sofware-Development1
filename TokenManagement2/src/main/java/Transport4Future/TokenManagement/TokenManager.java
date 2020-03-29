package Transport4Future.TokenManagement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

import java.text.SimpleDateFormat;

import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonObject;


public class TokenManager {

	public String TokenRequestGeneration(String path) throws TokenManagementException {
		TokenRequest Token;
		String myToken;

		Token = ReadTokenRequestFromJSON(path);
		myToken = CodeHashMD5(Token);
		return myToken;
	}

	private String CodeHashMD5(TokenRequest mytoken) throws TokenManagementException {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new TokenManagementException("Error: no such hashing algorithm.");
		}

		String input = "Stardust" + "-" + mytoken.toString();
		md.update(input.getBytes(StandardCharsets.UTF_8));
		byte[] digest = md.digest();

		String hex = String.format("%32x", new BigInteger(1, digest));

		return hex;
	}

	public TokenRequest ReadTokenRequestFromJSON(String path) throws TokenManagementException {

		TokenRequest req = null;

		String fileContents = "";

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(path));
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

		JsonObject jsonLicense;
		String deviceName;
		String typeOfDevice;
		String driverVersion;

		String supportEmail;
		String serialNumber;
		String macAddress;

		/*
		 * Lanzamos una excepción desde un punto de vista del formato de JSON, esté todo
		 * correcto
		 */
		try {

			jsonLicense = Json.createReader(new StringReader(fileContents)).readObject();

		} catch (JsonException e) {

			throw new TokenManagementException("Formato JSON incorrecto");
		}

		

		try {
			deviceName = jsonLicense.getString("Device Name");
		} catch (Exception e) {
			throw new TokenManagementException("Error: Device Name not found");
		}

		if (deviceName.length() > 20 || deviceName.length() < 1) {
			throw new TokenManagementException("Error: Device Name size is not correct");
		}

		try {
			typeOfDevice = jsonLicense.getString("Type of Device");
		} catch (Exception e) {
			throw new TokenManagementException("Error: type Of Device not found");
		}

		if (!(typeOfDevice.equals("Sensor") || typeOfDevice.equals("Actuator"))) {
			throw new TokenManagementException("Error: type of device is not correct");
		}

		try {
			driverVersion = jsonLicense.getString("Driver Version");

		} catch (Exception e) {
			throw new TokenManagementException("Error: Driver Version not found");
		}

		if (driverVersion.length() < 2 || driverVersion.length() > 25 || driverVersion.indexOf(".") == -1) {
			throw new TokenManagementException("Error: driver version structure not correct");
		}

		try {
			String[] aux = driverVersion.split("\\.");
			for (int i = 0; i < aux.length; i++) {
				Integer.parseInt(aux[i]);
			}

		} catch (Exception e) {

			throw new TokenManagementException("Error: driver version is not digital");
		}

		/* Lanzamos una excepción si supportEmail no tiene el formato correcto */
		try {
			supportEmail = jsonLicense.getString("Support e-mail");
		} catch (Exception e) {
			throw new TokenManagementException("supportEmail no encontrado");
		}
		if (supportEmail.matches("\\w+@\\w+\\.[A-Za-z]+$")) {
		} else {
			throw new TokenManagementException("Formato supportEmail incorrecto");
		}

		/* Lanzamos una excepción si SerialNumber no tiene el formato correcto */
		try {
			serialNumber = jsonLicense.getString("Serial Number");
		} catch (Exception e) {
			throw new TokenManagementException("serialNumber no encontrado");
		}
		if (serialNumber.matches("^[\\w\\-]+$")) {
		} else {
			throw new TokenManagementException("Formato serialNumber incorrecto");
		}

		/* Lanzamos una excepción si macAddress no tiene el formato correcto */
		try {
			macAddress = jsonLicense.getString("MAC Address");
		} catch (Exception e) {
			throw new TokenManagementException("macAddress no encontrado");
		}
		if (macAddress.matches("^([A-Z0-9]{2}:){5}[A-Z0-9]{2}$")) {
		} else {
			throw new TokenManagementException("Formato macAddress incorrecto");
		}

		// Date requestDate = df.parse(jsonLicense.getString("Request Date"));
		req = new TokenRequest(deviceName, typeOfDevice, driverVersion, supportEmail, serialNumber, macAddress);

		return req;

	}
	public String CodeHash256(Token myToken) throws TokenManagementException { 
		MessageDigest md;
		try {
		  md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
		  throw new TokenManagementException("Error: no such hashing algorithm.");
		}
		String input = "Stardust" + "-" + myToken.toString();

		md.update(input.getBytes(StandardCharsets.UTF_8)); 
		byte[] digest = md.digest();

		// Beware the hex length. If MD5 -> 32:"%032x", but for instance, in SHA-256 it should be "%064x"
		String hex = String.format("%64x", new BigInteger(1, digest));
		
		return encodeString(hex);                                  
		}
	private String encodeString(String stringToEncode) throws TokenManagementException{ 

	String encodedURL;

	try {
	  encodedURL = Base64.getUrlEncoder().encodeToString(stringToEncode.getBytes()); 
	}catch (Exception ex)
	{
	  throw new TokenManagementException("Error encoding 64URL.");
	}

	  return encodedURL;
	}
	public String RequestToken(String InputFile) throws TokenManagementException {
		Token myToken = null;
		String fileContents = "";
		BufferedReader reader;

		String tokenRequest;
		String date = "";
		String email;

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

		JsonObject jsonLicense;
		try {

			jsonLicense = Json.createReader(new StringReader(fileContents)).readObject();

		} catch (JsonException e) {

			throw new TokenManagementException("Formato JSON incorrecto");
		}

		try {
			tokenRequest = jsonLicense.getString("Token Request");
		} catch (Exception e) {
			throw new TokenManagementException("Token request no encontrado");
		}
		if (tokenRequest.matches("^[a-z0-9]+$")) {
		} else {
			throw new TokenManagementException("Formato Token Request incorrecto");
		}
		
		try {
			email = jsonLicense.getString("Notification e-mail");
		} catch (Exception e) {
			throw new TokenManagementException("Notification e-mail no encontrado");
		}
		if (email.matches("\\w+@\\w+\\.[A-Za-z]+$")) {
		} else {
			throw new TokenManagementException("Formato Notification e-mail incorrecto");
		}
		
		try {
			date = jsonLicense.getString("Request Date");
		}catch(Exception e) {
			throw new TokenManagementException("Error: could not find Request Date");
		}
		
	/*	if(!date.matches("^([0-2][0-9]|3[0-1])(\\/)(0[1-9]|1[0-2])(\\/)\\2(\\d{4})(\\s)(0[0-9]|1[0-2])(\\:)([0-5][0-9])(\\:)([0-5][0-9])$")) {
			throw new TokenManagementException("Error:Request Date form");
		}*/
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date1;
		
		try {
			 date1 = formatter.parse(date);
		} catch (Exception e) {
			throw new TokenManagementException("Error:Request Date");
		} 
				
 

		myToken = new Token(tokenRequest, date1, email);
		String result=CodeHash256(myToken);
		myToken.setSignature(result);
		result=encodeString(result);
		
		
		TokensStore myStore = new TokensStore(); 
		myStore.Add(myToken);
		 
		return result;
	}
	
	public boolean VerifyToken (String Token) throws TokenManagementException{ 
		TokensStore myStore = new TokensStore ();
		boolean result = false;

		Token tokenFound = myStore.Find(Token );

		if (tokenFound !=null){
			result = isValid(tokenFound);
		}
		return result;
		}
	private boolean isValid (Token tokenFound ){
		if (!tokenFound.isExpired () && tokenFound.isGranted()){
		return true;
		}else {
			return false;
		}
	}

}
