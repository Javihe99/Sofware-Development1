package Transport4Future.TokenManagement.Data;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Transport4Future.TokenManagement.Data.Attribute.Device;
import Transport4Future.TokenManagement.Data.Attribute.Email;
import Transport4Future.TokenManagement.Data.Attribute.Fecha;
import Transport4Future.TokenManagement.Exception.TokenManagementException;

public class Token {
	private static final String STORE_PATH = System.getProperty("user.dir")+"/Store/tokenRequestsStore.json";
	
	private String alg;
	private String typ;
	private Device device;
	private Fecha requestDate;
	private Email notificationEmail;
	private long iat;
	private long exp;
	private String signature;
	private String tokenValue;
	
	public Token (String Device, String RequestDate, String NotificationEmail) throws TokenManagementException {
		this.alg = "HS256";
		this.typ = "PDS";
		this.device = new Device(Device);
		this.requestDate = new Fecha(RequestDate);
		this.notificationEmail = new Email(NotificationEmail);
		this.iat = 1584523340892l;
		if ((this.device.getValue().startsWith("5"))){
			this.exp = this.iat + 604800000l;
		}
		else {
			this.exp = this.iat + 65604800000l;
		}
		this.signature = null;
		this.tokenValue = null;
		this.checkTokenRequestInformationFormat();
	}
	
private void checkTokenRequestInformationFormat() throws TokenManagementException {
        
		
		//Generar un HashMap para guardar los objetos
		Gson gson = new Gson();
		String jsonString;
		HashMap<String, TokenRequest> clonedMap = null;
		String storePath = STORE_PATH;

		//Cargar el almacen de tokens request en memoria y añadir el nuevo si no existe
		try {
			Object object = gson.fromJson(new FileReader(storePath), Object.class);
			jsonString = gson.toJson(object);	
	        Type type = new TypeToken<HashMap<String, TokenRequest>>(){}.getType();
	        clonedMap = gson.fromJson(jsonString, type);
		} catch (Exception e) {
			throw new TokenManagementException("Error: unable to recover Token Requests Store.");	
		}
        if (clonedMap==null) {
			throw new TokenManagementException("Error: Token Request Not Previously Registered");	        	
        }
        else if (!clonedMap.containsKey(this.getDevice())){
			throw new TokenManagementException("Error: Token Request Not Previously Registered");	        	
        }
	}

public void encodeToken(Token myToken) {
	String stringToEncode = myToken.getHeader() + myToken.getPayload() + myToken.getSignature();
	String encodedString = Base64.getUrlEncoder().encodeToString(stringToEncode.getBytes());
	myToken.setTokenValue(encodedString);
}

public boolean isValid (Token tokenFound) {
	if ((!tokenFound.isExpired()) && (tokenFound.isGranted())){
		return true;
	}
	else {
		return false;
	}
}
	
	public String getDevice() {
		return device.getValue();
	}

	public String getRequestDate() {
		return requestDate.getValue();
	}

	public String getNotificationEmail() {
		return notificationEmail.getValue();
	}
	
	public boolean isGranted () {
		if (this.iat < System.currentTimeMillis()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isExpired () {
		if (this.exp > System.currentTimeMillis()) {
			return false;
		}
		else {
			return true;
		}
	}

	public String getHeader () {
		return	"Alg=" + this.alg + "\\n Typ=" + this.typ + "\\n";
	}
	
	public String getPayload () {
		Date iatDate = new Date(this.iat);
		Date expDate = new Date(this.exp);
		
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		
		return	"Dev=" + this.getDevice()
				+ "\\n iat=" + df.format(iatDate)
				+ "\\n exp=" + df.format(expDate);
	}
	
	public void setSignature(String value) {
		this.signature = value;
	}

	public String getSignature() {
		return this.signature;
	}
	
	public void setTokenValue(String value) {
		this.tokenValue = value;
	}
	
	public String getTokenValue() {
		return this.tokenValue;
	}	
}
