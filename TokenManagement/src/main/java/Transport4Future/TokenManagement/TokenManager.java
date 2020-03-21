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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.json.Json;
import javax.json.JsonObject;

public class TokenManager {

	public String TokenRequestGeneration(String path) throws TokenManagementException{
	    TokenRequest Token;
	    String myToken;
	    
	    Token = ReadTokenRequestFromJSON(path);
	    myToken = CodeHashMD5(Token);
	    return myToken;
	  }
	  
	  

	  private String CodeHashMD5(TokenRequest mytoken)throws TokenManagementException {
	    MessageDigest md;
	    try {
	      md = MessageDigest.getInstance("MD5");
	    } catch (NoSuchAlgorithmException e) {
	      throw new TokenManagementException("Error: no such hashing algorithm.");
	    }
	    
	    String input ="Stardust"+"-"+mytoken.toString();
	    md.update(input.getBytes(StandardCharsets.UTF_8)); 
	    byte[] digest=md.digest();
	    
	    String hex =String.format("%32x",new BigInteger(1,digest));
	    
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

	    JsonObject jsonLicense = Json.createReader(new StringReader(fileContents)).readObject();

	    DateFormat df = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
	   
	    try {
	      String deviceName = jsonLicense.getString("Device Name");
	      String typeOfDevice = jsonLicense.getString("Type of Device");
	      String driverVersion = jsonLicense.getString("Driver Version");
	      String supportEmail = jsonLicense.getString("Support e-mail");
	      String serialNumber = jsonLicense.getString("Serial Number");
	      String macAddress = jsonLicense.getString("MAC Address");
	      System.out.print(macAddress);
	      //Date requestDate = df.parse(jsonLicense.getString("Request Date"));
	      req = new TokenRequest(deviceName,typeOfDevice,driverVersion,supportEmail, serialNumber, macAddress);
	    } catch (Exception pe) {
	      throw new TokenManagementException("Error: invalid input data in JSON structure.");
	    }

	    return req;
	  }
}
