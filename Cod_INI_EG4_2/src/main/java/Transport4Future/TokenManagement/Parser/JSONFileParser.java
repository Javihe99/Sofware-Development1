package Transport4Future.TokenManagement.Parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;

import Transport4Future.TokenManagement.Exception.TokenManagementException;
import Transport4Future.TokenManagement.Utils.TextReader;

public class JSONFileParser {
	public JsonObject parseJSONFile(String InputFile) throws TokenManagementException {
		TextReader myReader=new TextReader();
		String fileContents = myReader.getFile(InputFile);

		return parseJsonFromString(fileContents);
	}

	private JsonObject parseJsonFromString(String fileContents) throws TokenManagementException {
		JsonObject jsonLicense = null;
		try(StringReader sr = new StringReader(fileContents)) {
			jsonLicense = Json.createReader(sr).readObject();
		} catch(Exception e) {
			throw new TokenManagementException("Error: JSON object cannot be created due to incorrect representation");
		}
		return jsonLicense;
	}

	
	

}
