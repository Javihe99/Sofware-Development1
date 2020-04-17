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
	   String expectedToken = "WlROaU5qRmhPRE0yWkdRMVlUZ3paVGxsTVRrMVptWXdPV014TkRkak5EWTBaVGd3TkRnNU5ETm1OelpsTkRVMU9UTXdZalUzTVRKa01URmlZelJtTnc9PQ==";
	   String obtainedToken = myManager.RequestToken(FilePath);
	   assertEquals (expectedToken, obtainedToken);
	 }
	 
	 /*Afecta al nodo 1, porque hay un fallo en la lectura de un fichero */
	 @DisplayName("Fichero json inválido")
	 @Test
	 void InvJson() throws TokenManagementException{
	   String FilePath = this.jsonFilesFolder + "fgsdfgsdfg.json";
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
		
		String expectedToken = "Mzg1OGYyNmNjMmNiOThjODJiZTZlNmNkZTM3YjlmNTM5YWY1NjBiZGM1OWQzMzdhNDMxNDBhNmY1MDliNTc4NQ==";
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
	 
	
	
}
