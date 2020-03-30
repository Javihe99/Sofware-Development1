package Transport4Future.TokenManagement;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.stream.JsonParsingException;

import org.json.JSONException;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;

import com.google.gson.stream.JsonReader;

public class TokensStore {
	
	private List<Token> tokensList;
	private void Load () {
		  try
		  {
		    JsonReader reader = new JsonReader(new FileReader(System.getProperty("user.dir") + "/Store/tokenStore.json")); 
		    Gson gson = new Gson();
		    Token [] myArray = gson.fromJson(reader, Token[].class);
		    this.tokensList = new ArrayList<Token>();
		    for (Token token: myArray) {
		      this.tokensList.add(token);
		    }
		  }
		  catch (Exception ex)
		  {
		    this.tokensList = new ArrayList<Token>();
		  }
	
	
	}
	public void Add (Token newToken) throws TokenManagementException { 
		  this.Load();
		  if (Find(newToken.toString())==null) {
		    tokensList.add(newToken);
		    this.Save();
		}
	}

	private void Save () throws TokenManagementException {
		
	  Gson gson = new Gson();
	  String jsonString = gson.toJson(this.tokensList);
	  FileWriter fileWriter; 
	  try {
	    fileWriter = new FileWriter(System.getProperty("user.dir") + "/Store/tokenStore.Json"); 
	    fileWriter.write(jsonString);
	    fileWriter.close(); 
	  } catch (IOException e) {
	    throw new TokenManagementException("Error: Unable to save a new token in the internal licenses store");
	  }
	}
	
	public Token Find (String tokenToFind) {
		Token result=null;
		this.Load();
		for(Token token:this.tokensList) {

			if(token.getTokenValue().equals (tokenToFind)) {
				result=token;
			}
		}
		return result;
	}
}
