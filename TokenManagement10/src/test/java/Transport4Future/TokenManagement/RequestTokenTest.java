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
	 /* Caso de Prueba: correcto
		* Nodo/s del Árbol de Derivación: Todos los nodos no terminales
		* Tipo de Prueba: Valor normal
		* Técnica de prueba: Análisis Sintáctico
		* Resultado Esperado: True */
	 /**/
	 @DisplayName("Correct Token Generation")
	 @Test
	 void CorrectRequestTokenTest() throws TokenManagementException{
	   String FilePath = this.jsonFilesFolder + "Correct.json";
	   String expectedToken = "c841828006d918e6e685af7f98ee9c37afd5b254c8da9bc9c9151e695e6cef73";
	   String obtainedToken = myManager.RequestToken(FilePath);
	   assertEquals (expectedToken, obtainedToken);
	 }
	
	 
	 /* Caso de Prueba: No existe el fichero
		* Nodo/s del Árbol de Derivación: 1
		* Tipo de Prueba: Omisión
		* Técnica de prueba: Análisis Sintáctico
		* Resultado Esperado: Error: input file not found.*/
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
	 

	 
	 /* Caso de Prueba: No hay campo de Email
		* Nodo/s del Árbol de Derivación: 18,34,35,36,53,54,60,6162,63,64,76,77,78,79,80
		* Tipo de Prueba: Omisión
		* Técnica de prueba: Análisis Sintáctico
		* Resultado Esperado:Notification e-mail no encontrado*/
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
	 
	 
	 /* Caso de Prueba: Email sin @
		* Nodo/s del Árbol de Derivación: 61,77
		* Tipo de Prueba: Omisión
		* Técnica de prueba: Análisis Sintáctico
		* Resultado Esperado: Formato Notification e-mail incorrect*/
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

	 
	 /* Caso de Prueba: Email sin extensión
		* Nodo/s del Árbol de Derivación: 64,80
		* Tipo de Prueba: Omisión
		* Técnica de prueba: Análisis Sintáctico
		* Resultado Esperado: Formato Notification e-mail incorrect*/
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
	 
	 /* Caso de Prueba: Email sin nombre
		* Nodo/s del Árbol de Derivación: 60,76
		* Tipo de Prueba: Omisión
		* Técnica de prueba: Análisis Sintáctico
		* Resultado Esperado: Formato Notification e-mail incorrect*/
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
	
	 /* Caso de Prueba: Email sin punto
		* Nodo/s del Árbol de Derivación: 63,79
		* Tipo de Prueba: Omisión
		* Técnica de prueba: Análisis Sintáctico
		* Resultado Esperado: Formato Notification e-mail incorrect*/
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
	 
	 
	 /* Caso de Prueba: Email sin servidor
		* Nodo/s del Árbol de Derivación: 62,78
		* Tipo de Prueba: Omisión
		* Técnica de prueba: Análisis Sintáctico
		* Resultado Esperado: Formato Notification e-mail incorrect*/
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
	 
	 /* Caso de Prueba: Email con simolos raros
		* Nodo/s del Árbol de Derivación: 76
		* Tipo de Prueba: Modificación
		* Técnica de prueba: Análisis Sintáctico
		* Resultado Esperado: Formato Notification e-mail incorrect*/
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
	 
	 /* Caso de Prueba: Email con Numero en el dominio
		* Nodo/s del Árbol de Derivación: 64
		* Tipo de Prueba: Modificación
		* Técnica de prueba: Análisis Sintáctico
		* Resultado Esperado: Formato Notification e-mail incorrect*/
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
	 /* Caso de Prueba: Valor de Email Vacío
		* Nodo/s del Árbol de Derivación:61,62,63,64,76,77,78,79,80
		* Tipo de Prueba: Omisión
		* Técnica de prueba: Análisis Sintáctico
		* Resultado Esperado: Formato Notification e-mail incorrect*/

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
	 /* Caso de Prueba: Email sin Comillas
		* Nodo/s del Árbol de Derivación:34,53
		* Tipo de Prueba: Omisión
		* Técnica de prueba: Análisis Sintáctico
		* Resultado Esperado: Formato JSON incorrecto*/
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
	 

	 /* Caso de Prueba: No hay campo Token Request
		* Nodo/s del Árbol de Derivación: 24,45
		* Tipo de Prueba: Omisión
		* Técnica de prueba: Análisis Sintáctico
		* Resultado Esperado: Token request no encontrado*/
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
	 
	 /* Caso de Prueba: Token Request con símbolos raros
		* Nodo/s del Árbol de Derivación: 48
		* Tipo de Prueba: Modificación
		* Técnica de prueba: Análisis Sintáctico
		* Resultado Esperado: Formato Token Request incorrecto*/
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
	
	 /* Caso de Prueba: Token Request con símbolos raros
		* Nodo/s del Árbol de Derivación: 48
		* Tipo de Prueba: Modificación
		* Técnica de prueba: Análisis Sintáctico
		* Resultado Esperado: Formato Token Request incorrecto*/
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
	 
	 /* Caso de Prueba: Token Request con símbolos raros
		* Nodo/s del Árbol de Derivación: 48
		* Tipo de Prueba: Modificación
		* Técnica de prueba: Análisis Sintáctico
		* Resultado Esperado: Formato Token Request incorrecto*/
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
	
	 /* Caso de Prueba: Token Request con Mayúsculas
		* Nodo/s del Árbol de Derivación: 48
		* Tipo de Prueba: Modificación
		* Técnica de prueba: Análisis Sintáctico
		* Resultado Esperado: Formato Token Request incorrecto*/
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
	 
	 /* Caso de Prueba: Añadimos un espacio en el valor de Token Request
		* Nodo/s del Árbol de Derivación: 48
		* Tipo de Prueba: Modificación
		* Técnica de prueba: Análisis Sintáctico
		* Resultado Esperado: Formato Token Request incorrecto*/
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
	 
	 /* Caso de Prueba: No da error porque se ha duplicado el valor, y es sintáticamente correcto
		* Nodo/s del Árbol de Derivación: 28 y 48
		* Tipo de Prueba: Repetición
		* Técnica de prueba: Análisis Sintáctico
		* Resultado Esperado: True*/
	 @DisplayName("Duplicado nodo 28")
	 @Test
	 void TkDuplicadoValor() throws TokenManagementException{
		String FilePath = this.jsonFilesFolder + "TkDuplicadoValor.json";
		
		String expectedToken = "4c1d85c0e5de5ffdb75ab320270a23d8b74554867fcd68bfffe2c06bcbe4c66a";
		String obtainedToken = myManager.RequestToken(FilePath);
		   assertEquals (expectedToken, obtainedToken);
		
		
	 }
	 
	 /* Caso de Prueba: Falta Token Request
		* Nodo/s del Árbol de Derivación: 12,23,24,25,44,45,46
		* Tipo de Prueba: Omisión
		* Técnica de prueba: Análisis Sintáctico
		* Resultado Esperado: Formato JSON incorrecto*/
	 @DisplayName("Falta Nodo 12")
	 @Test
	 void TkFalta() throws TokenManagementException{
		String FilePath = this.jsonFilesFolder + "TkFalta.json";
		String expectedToken ="Formato JSON incorrecto";
		TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
		     myManager.RequestToken(FilePath);
		   });
		
		assertEquals (expectedToken, ex.getMessage());
	 }
	 
	 /* Caso de Prueba: Duplicamos una llave
		* Nodo/s del Árbol de Derivación: 2,5
		* Tipo de Prueba: Repetición
		* Técnica de prueba: Análisis Sintáctico
		* Resultado Esperado: Formato JSON incorrecto*/
	 @DisplayName("Nodo 2 duplicado")
	 @Test
	 void TkDuplicadoBracket() throws TokenManagementException{
		String FilePath = this.jsonFilesFolder + "TkDuplicadoBracket.json";
		String expectedToken ="Formato JSON incorrecto";
		TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
		     myManager.RequestToken(FilePath);
		   });
		
		assertEquals (expectedToken, ex.getMessage());
	 }
	 /* Caso de Prueba: Falta valor la igualdad en Token Request
		* Nodo/s del Árbol de Derivación: 13,26
		* Tipo de Prueba: Omisión
		* Técnica de prueba: Análisis Sintáctico
		* Resultado Esperado: Formato JSON incorrecto*/
	 @DisplayName("Falta Nodo 13")
	 @Test
	 void TkFaltaIgualdad() throws TokenManagementException{
		String FilePath = this.jsonFilesFolder + "TkFaltaIgualdad.json";
		String expectedToken ="Formato JSON incorrecto";
		TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
		     myManager.RequestToken(FilePath);
		   });
		
		assertEquals (expectedToken, ex.getMessage());
	 }
	 
	 /* Caso de Prueba: Falta valor en Token Request
		* Nodo/s del Árbol de Derivación: 28,48
		* Tipo de Prueba: Omisión
		* Técnica de prueba: Análisis Sintáctico
		* Resultado Esperado: Formato Token Request incorrecto*/
	 @DisplayName("Falta Nodo 28")
	 @Test
	 void TkFaltaValor() throws TokenManagementException{
		String FilePath = this.jsonFilesFolder + "TkFaltaValor.json";
		String expectedToken ="Formato Token Request incorrecto";
		TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
		     myManager.RequestToken(FilePath);
		   });
		
		assertEquals (expectedToken, ex.getMessage());
	 }
	 
	 /* Caso de Prueba: Correcto1
		* Nodo/s del Árbol de Derivación: Todos los nodos no terminales
		* Tipo de Prueba: Modificación
		* Técnica de prueba: Análisis Sintáctico
		* Resultado Esperado: True*/
	 @DisplayName("Correct1 Token Generation")
	 @Test
	 void CorrectRequestTokenTest1() throws TokenManagementException{
	   String FilePath = this.jsonFilesFolder + "Correct1.json";
	   String expectedToken = "b56f387dc92ee9527b20e651422af40eeaf678ef5481cb64f29dfc994a81f538";
	   String obtainedToken = myManager.RequestToken(FilePath);
	   assertEquals (expectedToken, obtainedToken);
	 }
	 
		
	 /* Caso de Prueba: Probar con un solo numero en el apartado de dia
		* Nodo/s del Árbol de Derivación: 65 y 81
		* Tipo de Prueba: Valor Normal
		* Técnica de prueba: Análisis Sintáctico
		* Resultado Esperado: True*/	
	 @DisplayName("Request Date format")
	 @Test
	 void CorrectRequestTokenTest2() throws TokenManagementException{
	   String FilePath = this.jsonFilesFolder + "RequestDateDiaImpar.json";
	   String expectedToken = "c841828006d918e6e685af7f98ee9c37afd5b254c8da9bc9c9151e695e6cef73";
	   String obtainedToken = myManager.RequestToken(FilePath);
	   assertEquals (expectedToken, obtainedToken);}
	 
	 
	 /* Caso de Prueba: Unión con guión en vez de barra en Request Date
		* Nodo/s del Árbol de Derivación: 66 68 112 114
		* Tipo de Prueba: Modificación
		* Técnica de prueba: Análisis Sintáctico
		* Resultado Esperado: Error:Request Date*/	
	 @DisplayName("Request Date format")
	 @Test
	 void RequestDateTest() throws TokenManagementException{
	   String FilePath = this.jsonFilesFolder + "RequestDateFormat.json";
	   String expectedToken = "Error:Request Date";
	   TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
		     myManager.RequestToken(FilePath);
		   });
		   assertEquals(expectedToken,ex.getMessage());}
	

	 
 /* Caso de Prueba: Omisión del time en Request Date falta la  parte de hora:minutos:segundo
	* Nodo/s del Árbol de Derivación: 70,71,72,73,74,75,85,86,87,88,89,90
	* Tipo de Prueba: Omisión
	* Técnica de prueba: Análisis Sintáctico
	* Resultado Esperado: Error:Request Date*/	 
	@DisplayName("Request Date sin time")
	@Test
	void RequestDateTest1() throws TokenManagementException{
	  String FilePath = this.jsonFilesFolder + "RequestDateNoneTime.json";
	  String expectedToken = "Error:Request Date";
	  TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
		     myManager.RequestToken(FilePath);
		   });
		   assertEquals(expectedToken,ex.getMessage());}
	
	
	
	/* Caso de Prueba: Omisión del campo de Request Date
	* Nodo/s del Árbol de Derivación: 9,10,19,20,21,22,37,38,39,40,41,42,43,55,56,57,58,59,65,66,67,
	 * 68,69,70,71,72,73,74,75,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98
	* Tipo de Prueba: Omisión
	* Técnica de prueba: Análisis Sintáctico
	* Resultado Esperado: Error: could not find Request Date*/
	@DisplayName("No Request Date")
	@Test
	void RequestDateNoneTest() throws TokenManagementException{
		  String FilePath = this.jsonFilesFolder + "RequestDateTestNone.json";
		  String expectedToken = "Error: could not find Request Date";
		  TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
			     myManager.RequestToken(FilePath);
			   });
			   assertEquals(expectedToken,ex.getMessage());}
	

	
	/* Caso de Prueba: Omisión del (:) en Request Date
	* Nodo/s del Árbol de Derivación:  21,40
	* Tipo de Prueba: Omisión
	* Técnica de prueba: Análisis Sintáctico
	* Resultado Esperado: Formato JSON incorrecto*/
	@DisplayName("Falta (:) en Request Date")
	@Test
	void RequestDateTest2() throws TokenManagementException{
		  String FilePath = this.jsonFilesFolder + "RequestDateNoneIgualdad.json";
		  String expectedToken = "Formato JSON incorrecto";
		  TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
			     myManager.RequestToken(FilePath);
			   });
			   assertEquals(expectedToken,ex.getMessage());}
	
	/* Caso de Prueba: Duplicado una comilla (") en Request Date
	* Nodo/s del Árbol de Derivación:  Nodos afectados:43 y 59
	* Tipo de Prueba: Repetición
	* Técnica de prueba: Análisis Sintáctico
	* Resultado Esperado: Formato JSON incorrecto*/
	@DisplayName("Duplicado Request Date")
	@Test
	void RequestDateTest3() throws TokenManagementException{
		  String FilePath = this.jsonFilesFolder + "RequestDateDuplicadoComilla.json";
		  String expectedToken = "Formato JSON incorrecto";
		  TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
			     myManager.RequestToken(FilePath);
			   });
			   assertEquals(expectedToken,ex.getMessage());}
	
	/* Caso de Prueba: Duplicado Toda la fecha de Request Date
	* Nodo/s del Árbol de Derivación: 42,65,66,67,68,69,70,71,72,73,74,75,81,82,
	* 83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99
	* Tipo de Prueba: Repetición
	* Técnica de prueba: Análisis Sintáctico
	* Resultado Esperado:True*/
	
	/*Al duplicar la fecha el algoritmo coge la fecha más reciente por eso no da error*/
	@DisplayName("Duplicado fecha Request Date")
	@Test
	void RequestDateTest4() throws TokenManagementException{
		  String FilePath = this.jsonFilesFolder + "RequestDateDuplicadoFecha.json";
		  String expectedToken = "c841828006d918e6e685af7f98ee9c37afd5b254c8da9bc9c9151e695e6cef73";
		  String obtainedToken = myManager.RequestToken(FilePath);
		  assertEquals (expectedToken, obtainedToken);
	}
	
	/* Caso de Prueba: Duplicado llave derecha
	* Nodo/s del Árbol de Derivación: 4,11
	* Tipo de Prueba: Repetición
	* Técnica de prueba: Análisis Sintáctico
	* Resultado Esperado: True*/
	
	/*Al duplicar la llave no da error*/
	@DisplayName("Duplicado llave derecha")
	@Test
	void DupllaveDerecha() throws TokenManagementException{
		  String FilePath = this.jsonFilesFolder + "DupllaveDerecha.json";
		  String expectedToken = "d023b676fb4c59b0973dcb7c7db7656c58432b8ffe9201914247cf330a5f3401";
		  String obtainedToken = myManager.RequestToken(FilePath);
		  assertEquals (expectedToken, obtainedToken);
	}
}
