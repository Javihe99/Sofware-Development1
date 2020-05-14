package Transport4Future.TokenManagement.Store;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import Transport4Future.TokenManagement.TokenManager;
import Transport4Future.TokenManagement.Data.Token;
import Transport4Future.TokenManagement.Exception.TokenManagementException;

public class TokensStore  {

	static final String STORE_PATH = System.getProperty("user.dir") + "/Store/tokenStore.json";
	
	List<Token> tokensList;
	
	private static TokensStore myTokenStore;
	
	private TokensStore() {
		this.Load();
	}

	public static TokensStore getSingleton() {
		if(myTokenStore == null) {
			myTokenStore = new TokensStore();
		}
		
		return myTokenStore;
	}
	
	@Override
	public TokenManager clone() {
		try {
			throw new CloneNotSupportedException();
		}catch(CloneNotSupportedException ex) {
			System.out.println("Token Manager cannot be cloned");
		}
		
		return null;
	}
	
	

	private void Load () {
		try
		{
			JsonReader reader = new JsonReader(new FileReader(STORE_PATH));
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
	protected void Save() throws TokenManagementException {
		Gson gson = new Gson();
		String jsonString = gson.toJson(this.tokensList);
	    FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(STORE_PATH);
	        fileWriter.write(jsonString);
	        fileWriter.close();
		} catch (IOException e) {
			throw new TokenManagementException("Error: Unable to save a new token in the internal licenses store");
		}
	}
	
	public void Add (Token newToken) throws TokenManagementException {

		if (Find(newToken.getTokenValue())==null) {
			tokensList.add(newToken);
			this.Save();
		}
	}
	
	public Token Find (String tokenToFind) {
		Token result = null;
	    for (Token token : this.tokensList) {
	        if (token.getTokenValue().equals(tokenToFind)) {
	        	return token;
	        }
	    }
		return result;
	}
}
