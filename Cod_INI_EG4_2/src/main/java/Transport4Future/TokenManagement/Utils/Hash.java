package Transport4Future.TokenManagement.Utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import Transport4Future.TokenManagement.Data.TokenRequest;
import Transport4Future.TokenManagement.Exception.TokenManagementException;

public class Hash {
	public String generateHashMD5(TokenRequest req) throws TokenManagementException {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new TokenManagementException("Error: no such hashing algorithm.");
		}
		
		String input =  "Stardust" + "-" + req.toString();
		
		md.update(input.getBytes(StandardCharsets.UTF_8));
		byte[] digest = md.digest();

		// Beware the hex length. If MD5 -> 32:"%032x", but for instance, in SHA-256 it should be "%064x" 
		String hex = String.format("%32x", new BigInteger(1, digest));
		return hex;
	}

	public String generateHashSHA256(String dataToSign) throws TokenManagementException {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new TokenManagementException("Error: no such hashing algorithm.");
		}

		md.update(dataToSign.getBytes(StandardCharsets.UTF_8));
		byte[] digest = md.digest();

		// Beware the hex length. If MD5 -> 32:"%032x", but for instance, in SHA-256 it should be "%064x"
		String signature = String.format("%064x", new BigInteger(1, digest));
		return signature;
	}


}
