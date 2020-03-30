package Transport4Future.TokenManagement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RequestTokenTest {
	private TokenManager myManager;
	  private String jsonFilesFolder;
	 
	 public RequestTokenTest() {
	   jsonFilesFolder = System.getProperty("user.dir")+"/JSONFILE/FR2/";
	   
	   myManager = new TokenManager();
	 }
	 /*Todos los nodos no terminales*/
	 @DisplayName("Correct Token Generation")
	 @Test
	 void CorrectRequestTokenTest() throws TokenManagementException{
	   String FilePath = this.jsonFilesFolder + "Correct.json";
	   String expectedToken = "c841828006d918e6e685af7f98ee9c37afd5b254c8da9bc9c9151e695e6cef73";
	   String obtainedToken = myManager.RequestToken(FilePath);
	   assertEquals (expectedToken, obtainedToken);
	 }
	 /*Afecta al nodo 1, puesto que el fichero no existe*/
	 @DisplayName("Fichero json inválido")
	 @Test
	 void InvJson() throws TokenManagementException{
	   String FilePath = this.jsonFilesFolder + "asdfadsf.json";
	   String expectedToken = "Error: input file not found.";
		TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
		     myManager.RequestToken(FilePath);
		   });
		
		assertEquals (expectedToken, ex.getMessage());
	 }
	 
	 /*Quitamos nodo 18, afecta a 18,34,35,36,53,54,60,6162,63,64,76,77,78,79,80*/ 
	 
	 @DisplayName("No hay Email")
	 @Test
	 void NohayEmail() throws TokenManagementException{
		String FilePath = this.jsonFilesFolder + "NohayEmail.json";
		String expectedToken ="Notification e-mail no encontrado";
		TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
		     myManager.RequestToken(FilePath);
		   });
		
		assertEquals (expectedToken, ex.getMessage());
	 }
	 
	 /*Quitamos el nodo 61, afecta 61,77 */
	 @DisplayName("Email sin @")
	 @Test
	 void EmailSinArroba() throws TokenManagementException{
		String FilePath = this.jsonFilesFolder + "EmailSinArroba.json";
		String expectedToken ="Formato Notification e-mail incorrecto";
		TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
		     myManager.RequestToken(FilePath);
		   });
		
		assertEquals (expectedToken, ex.getMessage());
	 }
	 /*Quitamos el nodo 64, afecta 64,80 */

	 @DisplayName("Email sin extension")
	 @Test
	 void EmailSinExt() throws TokenManagementException{
		String FilePath = this.jsonFilesFolder + "EmailSinExt.json";
		String expectedToken ="Formato Notification e-mail incorrecto";
		TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
		     myManager.RequestToken(FilePath);
		   });
		
		assertEquals (expectedToken, ex.getMessage());
	 }
	 /*Quitamos el nodo 60, afecta 60,76 */

	 @DisplayName("Email sin nombre")
	 @Test
	 void EmailsinNombre() throws TokenManagementException{
		String FilePath = this.jsonFilesFolder + "EmailsinNombre.json";
		String expectedToken ="Formato Notification e-mail incorrecto";
		TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
		     myManager.RequestToken(FilePath);
		   });
		
		assertEquals (expectedToken, ex.getMessage());
	 }
	 /*Quitamos el nodo 63, afecta 79 */
	 @DisplayName("Email sin punto")
	 @Test
	 void EmailSinPunto() throws TokenManagementException{
		String FilePath = this.jsonFilesFolder + "EmailSinPunto.json";
		String expectedToken ="Formato Notification e-mail incorrecto";
		TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
		     myManager.RequestToken(FilePath);
		   });
		
		assertEquals (expectedToken, ex.getMessage());
	 }
	 
	 /*Quitamos el nodo 62, afecta 62,78 */

	 @DisplayName("Email sin servidor")
	 @Test
	 void EmailSinServidor() throws TokenManagementException{
		String FilePath = this.jsonFilesFolder + "EmailSinDominio.json";
		String expectedToken ="Formato Notification e-mail incorrecto";
		TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
		     myManager.RequestToken(FilePath);
		   });
		
		assertEquals (expectedToken, ex.getMessage());
	 }
	 /*Afecta al nodo 76*/
	 @DisplayName("Email con simbolos raros")
	 @Test
	 void EmailSimbRaro() throws TokenManagementException{
		String FilePath = this.jsonFilesFolder + "EmailSimbRaro.json";
		String expectedToken ="Formato Notification e-mail incorrecto";
		TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
		     myManager.RequestToken(FilePath);
		   });
		
		assertEquals (expectedToken, ex.getMessage());
	 }
	 /*Afecta al nodo 64*/
	 @DisplayName("Email con Numero en el dominio")
	 @Test
	 void EmailNumDominio() throws TokenManagementException{
		String FilePath = this.jsonFilesFolder + "EmailNumDominio.json";
		String expectedToken ="Formato Notification e-mail incorrecto";
		TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
		     myManager.RequestToken(FilePath);
		   });
		
		assertEquals (expectedToken, ex.getMessage());
	 }
	 /*Quitamos el nodo 35, afecta 61,62,63,64,76,77,78,79,80 */
	 @DisplayName("Email vacío")
	 @Test
	 void EmailVacio() throws TokenManagementException{
		String FilePath = this.jsonFilesFolder + "EmailVacio.json";
		String expectedToken ="Formato Notification e-mail incorrecto";
		TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
		     myManager.RequestToken(FilePath);
		   });
		
		assertEquals (expectedToken, ex.getMessage());
	 }
	 
	 /*Quitamos el nodo 34, afecta 34,53 */
	 @DisplayName("Email sin Comillas")
	 @Test
	 void EmailSinComillas() throws TokenManagementException{
		String FilePath = this.jsonFilesFolder + "EmailSinComillas.json";
		String expectedToken ="Formato JSON incorrecto";
		TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
		     myManager.RequestToken(FilePath);
		   });
		
		assertEquals (expectedToken, ex.getMessage());
	 }
	 
	 /*Quitamos el nodo 24, afecta 24,45 */
	 @DisplayName("Token Request no está")
	 @Test
	 void TkNoatrib() throws TokenManagementException{
		String FilePath = this.jsonFilesFolder + "NohayTk.json";
		String expectedToken ="Token request no encontrado";
		TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
		     myManager.RequestToken(FilePath);
		   });
		
		assertEquals (expectedToken, ex.getMessage());
	 }
	 /*Afecta al nodo 48*/
	 @DisplayName("Token Request con símbolos raros")
	 @Test
	 void TkSimbRaro() throws TokenManagementException{
		String FilePath = this.jsonFilesFolder + "TkSimbRaro.json";
		String expectedToken ="Formato Token Request incorrecto";
		TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
		     myManager.RequestToken(FilePath);
		   });
		
		assertEquals (expectedToken, ex.getMessage());
	 }
	 /*Afecta al nodo 48*/
	 @DisplayName("Token Request con símbolos raros")
	 @Test
	 void TkSimbRaro1() throws TokenManagementException{
		String FilePath = this.jsonFilesFolder + "TkSimbRaro1.json";
		String expectedToken ="Formato Token Request incorrecto";
		TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
		     myManager.RequestToken(FilePath);
		   });
		
		assertEquals (expectedToken, ex.getMessage());
	 }
	 
	 /*Afecta al nodo 48*/
	 @DisplayName("Token Request con símbolos raros")
	 @Test
	 void TkSimbRaro2() throws TokenManagementException{
		String FilePath = this.jsonFilesFolder + "TkSimbRaro2.json";
		String expectedToken ="Formato Token Request incorrecto";
		TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
		     myManager.RequestToken(FilePath);
		   });
		
		assertEquals (expectedToken, ex.getMessage());
	 }
	 /*Afecta al nodo 48*/
	 @DisplayName("Token Request con Mayúsculas")
	 @Test
	 void TkMayus() throws TokenManagementException{
		String FilePath = this.jsonFilesFolder + "TkMayus.json";
		String expectedToken ="Formato Token Request incorrecto";
		TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
		     myManager.RequestToken(FilePath);
		   });
		
		assertEquals (expectedToken, ex.getMessage());
	 }
	 
	 /*Afecta al nodo 48*/
	 @DisplayName("Token Request con espacio")
	 @Test
	 void Tkespacio() throws TokenManagementException{
		String FilePath = this.jsonFilesFolder + "Tkespacio.json";
		String expectedToken ="Formato Token Request incorrecto";
		TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
		     myManager.RequestToken(FilePath);
		   });
		
		assertEquals (expectedToken, ex.getMessage());
	 }
	 
	 @DisplayName("Duplicado nodo 28")
	 /*Afecta al nodo 28 y 48*/
	 @Test
	 void TkDuplicadoValor() throws TokenManagementException{
		String FilePath = this.jsonFilesFolder + "TkDuplicadoValor.json";
		
		String expectedToken = "4c1d85c0e5de5ffdb75ab320270a23d8b74554867fcd68bfffe2c06bcbe4c66a";
		String obtainedToken = myManager.RequestToken(FilePath);
		   assertEquals (expectedToken, obtainedToken);
		
		
	 }
	 @DisplayName("Falta Nodo 12")
	 /*Afecta a los nodos: 12,23,24,25,44,45,46*/
	 @Test
	 void TkFalta() throws TokenManagementException{
		String FilePath = this.jsonFilesFolder + "TkFalta.json";
		String expectedToken ="Formato JSON incorrecto";
		TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
		     myManager.RequestToken(FilePath);
		   });
		
		assertEquals (expectedToken, ex.getMessage());
	 }
	 @DisplayName("Nodo 2 duplicado")
	 /*Afecta a los nodos: 2,5*/
	 @Test
	 void TkDuplicadoBracket() throws TokenManagementException{
		String FilePath = this.jsonFilesFolder + "TkDuplicadoBracket.json";
		String expectedToken ="Formato JSON incorrecto";
		TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
		     myManager.RequestToken(FilePath);
		   });
		
		assertEquals (expectedToken, ex.getMessage());
	 }
	 
	 @DisplayName("Falta Nodo 13")
	 /*Afecta a los nodos:13,26*/
	 @Test
	 void TkFaltaIgualdad() throws TokenManagementException{
		String FilePath = this.jsonFilesFolder + "TkFaltaIgualdad.json";
		String expectedToken ="Formato JSON incorrecto";
		TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
		     myManager.RequestToken(FilePath);
		   });
		
		assertEquals (expectedToken, ex.getMessage());
	 }
	 
	 @DisplayName("Falta Nodo 28")
	 /*Afecta a los nodos: 28,48*/
	 @Test
	 void TkFaltaValor() throws TokenManagementException{
		String FilePath = this.jsonFilesFolder + "TkFaltaValor.json";
		String expectedToken ="Formato Token Request incorrecto";
		TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
		     myManager.RequestToken(FilePath);
		   });
		
		assertEquals (expectedToken, ex.getMessage());
	 }
	 
	/*Todos los nodos terminales*/
	 @DisplayName("Correct1 Token Generation")
	 @Test
	 void CorrectRequestTokenTest1() throws TokenManagementException{
	   String FilePath = this.jsonFilesFolder + "Correct1.json";
	   String expectedToken = "b56f387dc92ee9527b20e651422af40eeaf678ef5481cb64f29dfc994a81f538";
	   String obtainedToken = myManager.RequestToken(FilePath);
	   assertEquals (expectedToken, obtainedToken);
	 }
	 
		/*Probar con un solo numero en el apartado de dia. Nodo: 65 y 81*/
	 @DisplayName("Request Date format")
	 @Test
	 void CorrectRequestTokenTest2() throws TokenManagementException{
	   String FilePath = this.jsonFilesFolder + "RequestDateDiaImpar.json";
	   String expectedToken = "c841828006d918e6e685af7f98ee9c37afd5b254c8da9bc9c9151e695e6cef73";
	   String obtainedToken = myManager.RequestToken(FilePath);
	   assertEquals (expectedToken, obtainedToken);}
	 
	 /*Unión con guión en vez de barra en Request Date. Nodos afectados: 66 68 112 114*/
	 @DisplayName("Request Date format")
	 @Test
	 void RequestDateTest() throws TokenManagementException{
	   String FilePath = this.jsonFilesFolder + "RequestDateFormat.json";
	   String expectedToken = "Error:Request Date";
	   TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
		     myManager.RequestToken(FilePath);
		   });
		   assertEquals(expectedToken,ex.getMessage());}
	
	 /*Falta la  parte de hora:minutos:segundo. Nodos afectados: 70,71,72,73,74,75,85,86,87,88,89,90  */
	@DisplayName("Request Date sin time")
	@Test
	void RequestDateTest1() throws TokenManagementException{
	  String FilePath = this.jsonFilesFolder + "RequestDateNoneTime.json";
	  String expectedToken = "Error:Request Date";
	  TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
		     myManager.RequestToken(FilePath);
		   });
		   assertEquals(expectedToken,ex.getMessage());}
	
	/*Omisión del campo de Request Date y de la última coma. 
	 * Nodos afectados:9,10,19,20,21,22,37,38,39,40,41,42,43,55,56,57,58,59,65,66,67,
	 * 68,69,70,71,72,73,74,75,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98*/
	@DisplayName("No Request Date")
	@Test
	void RequestDateNoneTest() throws TokenManagementException{
		  String FilePath = this.jsonFilesFolder + "RequestDateTestNone.json";
		  String expectedToken = "Error: could not find Request Date";
		  TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
			     myManager.RequestToken(FilePath);
			   });
			   assertEquals(expectedToken,ex.getMessage());}
	
	/*Omisión de : del campo de Request Date
	 * Nodos afectados: 21,40*/
	@DisplayName("No : en Request Date")
	@Test
	void RequestDateTest2() throws TokenManagementException{
		  String FilePath = this.jsonFilesFolder + "RequestDateNoneIgualdad.json";
		  String expectedToken = "Formato JSON incorrecto";
		  TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
			     myManager.RequestToken(FilePath);
			   });
			   assertEquals(expectedToken,ex.getMessage());}
	
	/*Dublicado de " en campo de Request Date
	 * Nodos afectados:43 y 59*/
	@DisplayName("Duplicado Request Date")
	@Test
	void RequestDateTest3() throws TokenManagementException{
		  String FilePath = this.jsonFilesFolder + "RequestDateDuplicadoComilla.json";
		  String expectedToken = "Formato JSON incorrecto";
		  TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
			     myManager.RequestToken(FilePath);
			   });
			   assertEquals(expectedToken,ex.getMessage());}
	
}
