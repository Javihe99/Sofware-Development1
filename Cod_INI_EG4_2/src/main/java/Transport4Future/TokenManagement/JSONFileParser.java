package Transport4Future.TokenManagement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;

public class JSONFileParser {
	public JsonObject parseJSONFile(String InputFile) throws TokenManagementException {
		BufferedReader reader;
		String fileContents = "";
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

		JsonObject jsonLicense = null;
		try(StringReader sr = new StringReader(fileContents)) {
			jsonLicense = Json.createReader(sr).readObject();
		} catch(Exception e) {
			throw new TokenManagementException("Error: JSON object cannot be created due to incorrect representation");
		}
		return jsonLicense;
	}
	

}
