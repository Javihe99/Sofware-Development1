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
import Transport4Future.TokenManagement.Parser.JSONTokenParser;
import Transport4Future.TokenManagement.Parser.JSONTokenRequestParser;
import Transport4Future.TokenManagement.Store.TokenRequestStore;
import Transport4Future.TokenManagement.Store.TokensStore;

import Transport4Future.TokenManagement.Utils.MDHasher;
import Transport4Future.TokenManagement.Utils.SHA256Hasher;

import java.lang.reflect.Type;


public class TokenManager implements ITokenManagement {

	private static TokenManager manager;
	
	private TokenManager() {
		
	}

	public static TokenManager getSingleton() {
		if(manager == null) {
			manager = new TokenManager();
		}
		
		return manager;
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
	
	public String TokenRequestGeneration (String InputFile) throws TokenManagementException{

		TokenRequest req= new TokenRequest(InputFile);
		return req.getHash();
	}
	


	
	public String RequestToken (String InputFile) throws TokenManagementException{


		Token myToken=  new Token (InputFile);
		
		String dataToSign =myToken.getHeader() + myToken.getPayload();
		SHA256Hasher myHash = new SHA256Hasher();
		String signature = myHash.Hash(dataToSign);

		myToken.setSignature(signature);
		
		myToken.encodeToken(myToken);
		
		saveTokenInStore(myToken);
		
		return myToken.getTokenValue();
	}

	public void saveTokenInStore(Token myToken) throws TokenManagementException {
		TokensStore myStore = TokensStore.getSingleton();
		myStore.Add(myToken);
	}




	
	public boolean VerifyToken (String Token) throws TokenManagementException{
		boolean result = false;
		TokensStore myStore = TokensStore.getSingleton();
		
		Token tokenFound = myStore.Find(Token);

		if (tokenFound!=null){
			return tokenFound.isValid(tokenFound);
		}
		return result;
	}
}