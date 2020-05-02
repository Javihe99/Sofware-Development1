package Transport4Future.TokenManagement.Utils;

import Transport4Future.TokenManagement.Exception.TokenManagementException;

public class SHA256Hasher extends MainHashCode implements IHash {

	public SHA256Hasher() {
		this.algorithm="SHA-256";
		this.type="%064x";
	}

	@Override
	public String Hash(String text) throws TokenManagementException {
		return super.Hash(text);
	}


}
