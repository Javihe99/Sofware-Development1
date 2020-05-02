package Transport4Future.TokenManagement.Utils;

import Transport4Future.TokenManagement.Data.TokenRequest;
import Transport4Future.TokenManagement.Exception.TokenManagementException;

public class MDHasher extends MainHashCode implements IHash {

	private String key="Stardust-";
	
	public MDHasher() {
		this.algorithm="MD5";
		this.type="%032x";
	}
	
	@Override
	public String Hash(String text) throws TokenManagementException {
		
		String input =  key + text;
		
		return super.Hash(input);
	}

} 
