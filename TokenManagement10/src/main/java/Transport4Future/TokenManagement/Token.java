package Transport4Future.TokenManagement;

import java.io.FileReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
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
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.stream.JsonParsingException;

import org.json.JSONException;

import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;

import com.google.gson.stream.JsonReader;

public class Token {
	private String alg;
	private String typ;
	private String device;
	private Date requestDate;
	private String notificationEmail;
	private long iat;
	private long exp;
	private String signature;
	private String tokenValue;
	
	public Token(String Device, Date RequestDate, String NotificationEmail) {
		this.alg= "HS256";
        this.typ = "PDS";
        this.device= Device;
        this.requestDate = RequestDate;
        this.notificationEmail = NotificationEmail;
        this.iat = System.currentTimeMillis();
        this.iat = 1583780309;
        this.exp = this.iat + 904800000l;
        this.signature=null;
        this.tokenValue=null;
        
	}
	
	public void setSignature(String x) {
		this.signature=x;
	}
	public String getSignature() {
		return this.signature;
	}
	public void setTokenValue(String x) {
		this.tokenValue=x;
	}
	public String getTokenValue() {
		return this.tokenValue;
	}


	public boolean isGranted (){
		System.out.println(this.iat);
			if (this.iat < System.currentTimeMillis()) {
				return true;
			}else {
			return false;
			}
	}
	public boolean isExpired (){
		System.out.println(this.exp+"$$$$$$$$$$$$$$$"+ System.currentTimeMillis());
			if (this.exp > System.currentTimeMillis()) {
				return false;
			}
			else {
				return true;
			}
	}
	
	public String toString() {
		return "Token [\n\\Token Request:" + this.device+ 
				 ",\n\t\\Notification e-mail:"+this.notificationEmail 
				 + ",\n\t\\Request Date:"+ this.requestDate+"\n]";
	}
	
			
	
	
}
