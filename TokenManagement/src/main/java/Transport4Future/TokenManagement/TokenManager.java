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
	    /*JsonObject jsonLicense;
	    try {
	    	 jsonLicense = Json.createReader(new StringReader(fileContents)).readObject();
	    }catch (NullPointerException e) {
	    	 throw new TokenManagementException("Error: input file could not be read.");
	    }*/
	    
	   
	    if(fileContents.length()>1) {
	    	
	    	   JsonObject jsonLicense;
	    	   String deviceName;
	    	   String typeOfDevice;
	    	   String driverVersion;
	    	   try {

	    	   jsonLicense = Json.createReader(new StringReader(fileContents)).readObject();
	    	   }catch(Exception e){
	    		   throw new TokenManagementException("Error: invalid input data in JSON structure.");
	    	   }
		       DateFormat df = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
		       System.out.println("--------------------before-------------------------------------");
		       
		
	    	try {
	    		 deviceName = jsonLicense.getString("Device Name");
	    	}catch(Exception e) {
	    		throw new TokenManagementException("Error: Device Name not found");
	    	}
	    	
	    	if(deviceName.length()>20 || deviceName.length()<1) {
	    		throw new TokenManagementException("Error: Device Name size is not correct");
	    	}
	    		
	    	try {
	    		typeOfDevice = jsonLicense.getString("Type of Device");
	    	}catch(Exception e) {
	    		throw new TokenManagementException("Error: type Of Device not found");
	    	}
	    	
	    	if(!(typeOfDevice.equals("Sensor")||typeOfDevice.equals("Actuator"))) {
	    		throw new TokenManagementException("Error: type of device is not correct");
	    	}
	    	
	    	try {
	    		  driverVersion = jsonLicense.getString("Driver Version");

	    	}catch(Exception e) {
	    		throw new TokenManagementException("Error: Driver Version not found");
	    	}
	    	
	    	if(driverVersion.length()<2 ||driverVersion.length()>25 ||driverVersion.indexOf(".")==-1) {
	    		throw new TokenManagementException("Error: driver version structure not correct");
	    	}
	    	
	    	try{
	    		String [] aux = driverVersion.split("\\.");
	    		for(int i = 0; i<aux.length; i++) {
	    			Integer.parseInt(aux[i]);
	    		}
	    		
	    	} catch(Exception e){
	    		System.out.println("***************************************************************");
	    		throw new TokenManagementException("Error: driver version is not digital");
	    	}
	    		
	    	
	    	
		    try {
		    
		     
		      
		      String supportEmail = jsonLicense.getString("Support e-mail");
		      String serialNumber = jsonLicense.getString("Serial Number");
		      String macAddress = jsonLicense.getString("MAC Address");
		      System.out.print(macAddress);
		      //Date requestDate = df.parse(jsonLicense.getString("Request Date"));
		      req = new TokenRequest(deviceName,typeOfDevice,driverVersion,supportEmail, serialNumber, macAddress);
		    } catch (Exception pe) {
		      throw new TokenManagementException("Error: invalid input data in JSON structure.");
		    }
		    System.out.println("--------------------if1-------------------------------------");
		    return req;
	    	   
	    }
	    System.out.println("--------------------hola1-------------------------------------");
	    throw new TokenManagementException("Error: input file could not be read.");
	  
	  }
}
