package Transport4Future.TokenManagement.Utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import Transport4Future.TokenManagement.Data.TokenRequest;
import Transport4Future.TokenManagement.Exception.TokenManagementException;

public class Hash {
	private static final String INPUT_MD5 = "Stardust-";
	private static final String TYPE_64 = "%064x";
	private static final String TYPE_32 = "%32x";
	private static final String ERROR_ALGORITHM = "Error: no such hashing algorithm.";
	private static final String SHA = "SHA-256";
	private static final String MD5 = "MD5";

	public String generateHashMD5(TokenRequest req) throws TokenManagementException {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance(MD5);
		} catch (NoSuchAlgorithmException e) {
			throw new TokenManagementException(ERROR_ALGORITHM);
		}
		
		String input =  INPUT_MD5 + req.toString();
		
		md.update(input.getBytes(StandardCharsets.UTF_8));
		byte[] digest = md.digest();

		// Beware the hex length. If MD5 -> 32:"%032x", but for instance, in SHA-256 it should be "%064x" 
		String hex = String.format(TYPE_32, new BigInteger(1, digest));
		return hex;
	}

	public String generateHashSHA256(String dataToSign) throws TokenManagementException {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance(SHA);
		} catch (NoSuchAlgorithmException e) {
			throw new TokenManagementException(ERROR_ALGORITHM);
		}

		md.update(dataToSign.getBytes(StandardCharsets.UTF_8));
		byte[] digest = md.digest();

		// Beware the hex length. If MD5 -> 32:"%032x", but for instance, in SHA-256 it should be "%064x"
		String signature = String.format(TYPE_64, new BigInteger(1, digest));
		return signature;
	}


}
