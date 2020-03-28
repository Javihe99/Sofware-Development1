package Transport4Future.TokenManagement;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class TokenTest {	
  
  private TokenManager myManager;
  private String jsonFilesFolder;
 
 public TokenTest() {
   jsonFilesFolder = System.getProperty("user.dir")+"/JSONFILE/FR1/";
   
   myManager = new TokenManager();
 }
 
 @DisplayName ("File is missing")
 @Test
 void FileIsMissingTest() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder+"jfksalkjCorrect.json";
   String expectedMessage = "Error: input file not found.";
   TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
     myManager.TokenRequestGeneration(FilePath);
   });
   assertEquals(expectedMessage,ex.getMessage());
   }
   

/* Test correcto*/
 @DisplayName("Correct Token Generation")
 @Test
 void CorrectTokenGenerationTest() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder + "Correct.json";
   String expectedToken = "9550500e9a6afe8f078f6217f9ecdabb";
   String obtainedToken = myManager.TokenRequestGeneration(FilePath);
   assertEquals (expectedToken, obtainedToken);
 }
 @DisplayName("Correct Token Generation")
 @Test
 void CorrectTokenGenerationTest1() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder + "Correct1.json";
   String expectedToken = "38394b8b32cc11bf795868dd3cc606b4";
   String obtainedToken = myManager.TokenRequestGeneration(FilePath);
   assertEquals (expectedToken, obtainedToken);
 }
 /*Un Test de formato incorrecto*/
 @DisplayName("Formato incorrecto")
 @Test
 void BlankTestfileTest() throws TokenManagementException{
	String FilePath = this.jsonFilesFolder + "FormatoIncorrecto.json";
	String expectedToken ="Formato JSON incorrecto";
	TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
	     myManager.TokenRequestGeneration(FilePath);
	   });
	
	assertEquals (expectedToken, ex.getMessage());
}
 
 /*Vamos a realizar comprobaciones en el email*/ 
 
 @DisplayName("No hay Email")
 @Test
 void NohayEmail() throws TokenManagementException{
	String FilePath = this.jsonFilesFolder + "NohayEmail.json";
	String expectedToken ="supportEmail no encontrado";
	TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
	     myManager.TokenRequestGeneration(FilePath);
	   });
	
	assertEquals (expectedToken, ex.getMessage());
 }
 
 /*El email debe seguir el siguiente formato (letras o numeros)@(Letras o numeros).(letras)*/
 @DisplayName("Email sin @")
 @Test
 void EmailSinArroba() throws TokenManagementException{
	String FilePath = this.jsonFilesFolder + "EmailSinArroba.json";
	String expectedToken ="Formato supportEmail incorrecto";
	TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
	     myManager.TokenRequestGeneration(FilePath);
	   });
	
	assertEquals (expectedToken, ex.getMessage());
 }
 
 @DisplayName("Email sin Dominio")
 @Test
 void EmailSinDominio() throws TokenManagementException{
	String FilePath = this.jsonFilesFolder + "EmailSinDominio.json";
	String expectedToken ="Formato supportEmail incorrecto";
	TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
	     myManager.TokenRequestGeneration(FilePath);
	   });
	
	assertEquals (expectedToken, ex.getMessage());
 }
 
 @DisplayName("Email sin nombre")
 @Test
 void EmailsinNombre() throws TokenManagementException{
	String FilePath = this.jsonFilesFolder + "EmailsinNombre.json";
	String expectedToken ="Formato supportEmail incorrecto";
	TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
	     myManager.TokenRequestGeneration(FilePath);
	   });
	
	assertEquals (expectedToken, ex.getMessage());
 }
 
 @DisplayName("Email sin punto")
 @Test
 void EmailSinPunto() throws TokenManagementException{
	String FilePath = this.jsonFilesFolder + "EmailSinPunto.json";
	String expectedToken ="Formato supportEmail incorrecto";
	TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
	     myManager.TokenRequestGeneration(FilePath);
	   });
	
	assertEquals (expectedToken, ex.getMessage());
 }
 @DisplayName("Email sin servidor")
 @Test
 void EmailSinServidor() throws TokenManagementException{
	String FilePath = this.jsonFilesFolder + "EmailSinServidor.json";
	String expectedToken ="Formato supportEmail incorrecto";
	TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
	     myManager.TokenRequestGeneration(FilePath);
	   });
	
	assertEquals (expectedToken, ex.getMessage());
 }
 
 @DisplayName("Email con simbolos raros")
 @Test
 void EmailSimbRaro() throws TokenManagementException{
	String FilePath = this.jsonFilesFolder + "EmailSimbRaro.json";
	String expectedToken ="Formato supportEmail incorrecto";
	TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
	     myManager.TokenRequestGeneration(FilePath);
	   });
	
	assertEquals (expectedToken, ex.getMessage());
 }
 
 @DisplayName("Email con Numero en el dominio")
 @Test
 void EmailNumDominio() throws TokenManagementException{
	String FilePath = this.jsonFilesFolder + "EmailNumDominio.json";
	String expectedToken ="Formato supportEmail incorrecto";
	TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
	     myManager.TokenRequestGeneration(FilePath);
	   });
	
	assertEquals (expectedToken, ex.getMessage());
 }
 @DisplayName("Email vacío")
 @Test
 void EmailVacio() throws TokenManagementException{
	String FilePath = this.jsonFilesFolder + "EmailVacio.json";
	String expectedToken ="Formato supportEmail incorrecto";
	TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
	     myManager.TokenRequestGeneration(FilePath);
	   });
	
	assertEquals (expectedToken, ex.getMessage());
 }
 
 
/*Vamos a realizar comprobaciones en el Serial Number*/
 
 @DisplayName("No hay Serial Number")
 @Test
 void NohaySerialNumber() throws TokenManagementException{
	String FilePath = this.jsonFilesFolder + "NohaySerialNumber.json";
	String expectedToken ="serialNumber no encontrado";
	TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
	     myManager.TokenRequestGeneration(FilePath);
	   });
	
	assertEquals (expectedToken, ex.getMessage());
 }
 
 /*Serial Number puede ser numeros y/o letras y/o barras(|)*/
 @DisplayName("Serial Number con caracteres raros")
 @Test
 void SNSimbRaro() throws TokenManagementException{
	String FilePath = this.jsonFilesFolder + "SNSimbRaro.json";
	String expectedToken ="Formato serialNumber incorrecto";
	TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
	     myManager.TokenRequestGeneration(FilePath);
	   });
	
	assertEquals (expectedToken, ex.getMessage());
 }
 @DisplayName("Serial number vacío")
 @Test
 void SNvacio() throws TokenManagementException{
	String FilePath = this.jsonFilesFolder + "SNvacio.json";
	String expectedToken ="Formato serialNumber incorrecto";
	TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
	     myManager.TokenRequestGeneration(FilePath);
	   });
	
	assertEquals (expectedToken, ex.getMessage());
 }
 @DisplayName("Serial number con espacio")
 @Test
 void SNConEspacio() throws TokenManagementException{
	String FilePath = this.jsonFilesFolder + "SNConEspacio.json";
	String expectedToken ="Formato serialNumber incorrecto";
	TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
	     myManager.TokenRequestGeneration(FilePath);
	   });
	
	assertEquals (expectedToken, ex.getMessage());
	
 }
/*Vamos a realizar comprobaciones en el macAdress*/
 
 @DisplayName("No hay macAdress")
 @Test
 void NohaymacAddress() throws TokenManagementException{
	String FilePath = this.jsonFilesFolder + "NohaymacAddress.json";
	String expectedToken ="macAddress no encontrado";
	TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
	     myManager.TokenRequestGeneration(FilePath);
	   });
	
	assertEquals (expectedToken, ex.getMessage());
 }
 
 /*El macAddress debe seguir el siguiente formato "XX:XX:XX:XX:XX:XX" siendo X un numero o letra*/
 @DisplayName("macAddress con puntos")
 @Test
 void macAddConPuntos() throws TokenManagementException{
	String FilePath = this.jsonFilesFolder + "macAddConPuntos.json";
	String expectedToken ="Formato macAddress incorrecto";
	TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
	     myManager.TokenRequestGeneration(FilePath);
	   });
	
	assertEquals (expectedToken, ex.getMessage());
 }
 
 @DisplayName("macAddress con espacio")
 @Test
 void macAddConEspacip() throws TokenManagementException{
	String FilePath = this.jsonFilesFolder + "macAddConEspacio.json";
	String expectedToken ="Formato macAddress incorrecto";
	TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
	     myManager.TokenRequestGeneration(FilePath);
	   });
	
	assertEquals (expectedToken, ex.getMessage());
 }
 
 @DisplayName("macAddress formado por un campo")
 @Test
 void macAddUnCampo() throws TokenManagementException{
	String FilePath = this.jsonFilesFolder + "macAddUnCampo.json";
	String expectedToken ="Formato macAddress incorrecto";
	TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
	     myManager.TokenRequestGeneration(FilePath);
	   });
	
	assertEquals (expectedToken, ex.getMessage());
 }
 @DisplayName("macAddress con simbolo Raro")
 @Test
 void macAddSimbRaro() throws TokenManagementException{
	String FilePath = this.jsonFilesFolder + "macAddSimbRaro.json";
	String expectedToken ="Formato macAddress incorrecto";
	TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
	     myManager.TokenRequestGeneration(FilePath);
	   });
	
	assertEquals (expectedToken, ex.getMessage());
 }
/*
 @DisplayName("Invalid Test Cases")
 @ParameterizedTest(name="{index} -with the input ''{0}'' error expected is ''{1}''")
 @CsvFileSource (resources="/invalidTestCasesrRequestGenerationTest.csv")
 void InvalidTest(String FilePath, String expectedMessage) throws TokenManagementException{
   TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class,()-> {
     myManager.TokenRequestGeneration(FilePath);
   });
   assertEquals (expectedMessage, ex.getMessage());
 }
 
 
 @DisplayName("Invalid Test Cases")
 @ParameterizedTest(name = "{index} - {2}")
 @CsvFileSource(resources = "/invalidTestCasesRequestGenerationTest.csv")
 void InvalidTestCases(String InputFilePath, String expectedMessage) {
   TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class,()-> {
     myManager.TokenRequestGeneration(InputFilePath);
   });
   assertEquals (expectedMessage, ex.getMessage());
 }
*/
 

  
  @BeforeAll
  static void SetUpBeforeClass() throws Exception {
  }

  @AfterAll
  static void TearDownAfterClass() throws Exception {
  }

  @BeforeEach
  void SetUp() throws Exception {
  }

  @AfterEach
  void TearDown() throws Exception {
  }

  /*@Test
  void Test() {
    fail("Not yet implemented");
  }*/



}