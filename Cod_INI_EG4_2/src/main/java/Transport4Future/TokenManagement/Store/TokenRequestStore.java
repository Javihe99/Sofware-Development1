package Transport4Future.TokenManagement.Store;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Transport4Future.TokenManagement.Data.TokenRequest;
import Transport4Future.TokenManagement.Exception.TokenManagementException;

public class TokenRequestStore {

	private static final String STORE_PATH = System.getProperty("user.dir")+"/Store/tokenRequestsStore.json";

	public void storeTokenRequest(TokenRequest req, String hex)throws TokenManagementException {
		
		HashMap<String, TokenRequest> clonedMap = this.loadTokenRequestToMemory();
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
    
		try {
			fileWriter = new FileWriter(STORE_PATH);
	        fileWriter.write(jsonString);
	        fileWriter.close();
		} catch (IOException e) {
			throw new TokenManagementException("Error: Unable to save a new token in the internal licenses store");
		}
	}

	private HashMap<String, TokenRequest> loadTokenRequestToMemory() {
		//Generar un HashMap para guardar los objetos
		//Tengo que cargar el almacen de tokens request en memoria y añadir el nuevo si no existe
		HashMap<String, TokenRequest> clonedMap = null;
		try {
			Gson gson = new Gson();
			String jsonString;
			
			Object object = gson.fromJson(new FileReader(STORE_PATH), Object.class);
			jsonString = gson.toJson(object);	
	        Type type = new TypeToken<HashMap<String, TokenRequest>>(){}.getType();
	        clonedMap = gson.fromJson(jsonString, type);
		} catch (Exception e) {
			clonedMap=null;
		}
		return clonedMap;
	}

}
