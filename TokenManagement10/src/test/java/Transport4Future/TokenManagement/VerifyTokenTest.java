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
	
	/* Caso de Prueba: Prueba1: fecha de emision y expiración mayor que el actual
	* Técnica de prueba: Prueba que en el bucle entra una vez
	* Resultado Esperado: false
	*/ 
	
	 @DisplayName("Verify is false, token found, is not granted and is not valid")
	 @Test
	 void CorrectVerifyTokenTest() throws TokenManagementException{
	  boolean expected =false;
	   String tokenToVerify = "abhbda5dopiwr6592tije88T1";
	  boolean obtained = myManager.VerifyToken(tokenToVerify);
	   assertEquals (expected, obtained);
	 }
	
	 /* Caso de Prueba: Prueba2: fecha de emision menor que el actual pero la de expiración es mayor.
		* Técnica de prueba: Entra dos veces en el bucle
		* Resultado Esperado: true
		*/ 
	 @DisplayName("Verify is true, token found, is granted and is valid")
	 @Test
	 void CorrectVerifyTokenTest1() throws TokenManagementException{
	  boolean expected =true;
	   String tokenToVerify = "Mjg1MzQ4ZDhkYzZlZGY2OTI4Y2YwMGZlMDM2MTAzYzk0ODA0MDg4ODVkZGI1ZjgzMzBlMzBlMWRlMTAzMTJlZg==";
	  boolean obtained = myManager.VerifyToken(tokenToVerify);
	   assertEquals (expected, obtained);
	 }
	 
	 /* Caso de Prueba:Prueba3: ha expirado y fecha de emisión mayor a la actual.
		* Técnica de prueba: Entra cuatro veces en el bucle
		* Resultado Esperado: false
		*/ 
	 @DisplayName("Verify is false, token found, is not granted and is not valid")
	 @Test
	 void CorrectVerifyTokenTest2() throws TokenManagementException{
	  boolean expected =false;
	   String tokenToVerify = "558DAJbda5dopiwr92tije99T3";
	  boolean obtained = myManager.VerifyToken(tokenToVerify);
	   assertEquals (expected, obtained);
	 }
	 
	 
	 /* Caso de Prueba:Prueba4: ha expirado y fecha de emisión menor a la actual.
		* Técnica de prueba: Entra varias veces en el bucle
		* Resultado Esperado: false
		*/ 
	 @DisplayName("Verify is false, token found, is granted and is not valid")
	 @Test
	 void CorrectVerifyTokenTest3() throws TokenManagementException{
	  boolean expected =false;
	   String tokenToVerify = "669DAJbda5doTqwr92tije99T4";
	  boolean obtained = myManager.VerifyToken(tokenToVerify);
	   assertEquals (expected, obtained);
	 }
	 
	 /* Caso de Prueba: Prueba5: no existe ese token
		* Técnica de prueba: No entra por el bucle
		* Resultado Esperado: false
		*/ 
	 
	 @DisplayName("token is not found")
	 @Test
	 void TokenWrongVerifyTest() throws TokenManagementException{
	  
	  String tokenToVerify = "38394b8b32cc11bf795868dd3cc606b4";
	  String expectedMessage = "Error: Token not found";
	  TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
		     myManager.VerifyToken(tokenToVerify);
		   });
		
		assertEquals (expectedMessage, ex.getMessage());
	 }
}
