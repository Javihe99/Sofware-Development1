package Transport4Future.TokenManagement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class VerifyTokenTest {
	private TokenManager myManager;
	
	public VerifyTokenTest(){
		myManager =new TokenManager();
	}
	
	 @DisplayName("Verify is true, token found, is granted and is valid")
	 @Test
	 void CorrectVerifyTokenTest() throws TokenManagementException{
	  boolean expected =true;
	   String tokenToVerify = "38394b8b32cc11bf795868dd3cc606b4";
	  boolean obtained = myManager.VerifyToken(tokenToVerify);
	   assertEquals (expected, obtained);
	 }
	 
	 @DisplayName("token is not found")
	 @Test
	 void TokenWrongVerifyTest() throws TokenManagementException{
	  
	  String tokenToVerify = "38394b8b32cc11bf795868dd3cc606b4";
	  String expectedMessage = "Token is not found in TokenStore";
	  TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
		     myManager.TokenRequestGeneration(tokenToVerify);
		   });
		
		assertEquals (expectedMessage, ex.getMessage());
	 }
}
