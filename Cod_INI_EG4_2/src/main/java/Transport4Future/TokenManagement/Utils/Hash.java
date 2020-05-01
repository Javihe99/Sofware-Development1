package Transport4Future.TokenManagement.Utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import Transport4Future.TokenManagement.Data.TokenRequest;
import Transport4Future.TokenManagement.Exception.TokenManagementException;

public class Hash {
	
	private static final String ERROR_ALGORITHM = "Error: no such hashing algorithm.";


	public String generateHashMD5(TokenRequest req) throws TokenManagementException {
		MessageDigest md;
		String key="Stardust-";
		String algorithm="MD5";
		String type="%032x";
		try {
			md = MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			throw new TokenManagementException(ERROR_ALGORITHM);
		}
		
		String input =  key + req.toString();
		
		md.update(input.getBytes(StandardCharsets.UTF_8));
		byte[] digest = md.digest();

		// Beware the hex length. If MD5 -> 32:"%032x", but for instance, in SHA-256 it should be "%064x" 
		String hex = String.format(type, new BigInteger(1, digest));
		return hex;
	}

	public String generateHashSHA256(String dataToSign) throws TokenManagementException {
		MessageDigest md;
		String algorithm="SHA-256";
		String type="%064x";
		try {
			md = MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			throw new TokenManagementException(ERROR_ALGORITHM);
		}

		md.update(dataToSign.getBytes(StandardCharsets.UTF_8));
		byte[] digest = md.digest();

		// Beware the hex length. If MD5 -> 32:"%032x", but for instance, in SHA-256 it should be "%064x"
		String signature = String.format(type, new BigInteger(1, digest));
		return signature;
	}


}
