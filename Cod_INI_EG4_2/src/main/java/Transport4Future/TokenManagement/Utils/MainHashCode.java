package Transport4Future.TokenManagement.Utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import Transport4Future.TokenManagement.Exception.TokenManagementException;

public abstract class MainHashCode implements IHash{
	
	protected String algorithm;
	protected String type;
	
	public MainHashCode() {
		super();
	}

	public String Hash(String input) throws TokenManagementException {
		
		MessageDigest md;
		try {
			md = MessageDigest.getInstance(this.algorithm);
		} catch (NoSuchAlgorithmException e) {
			throw new TokenManagementException("Error: no such hashing algorithm.");
		}
		
		md.update(input.getBytes(StandardCharsets.UTF_8));
		byte[] digest = md.digest();
	
		// Beware the hex length. If MD5 -> 32:"%032x", but for instance, in SHA-256 it should be "%064x" 
		String hex = String.format(this.type, new BigInteger(1, digest));
		return hex;
	}

}