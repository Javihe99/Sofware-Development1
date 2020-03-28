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
   
 

 /*@DisplayName("Invalid Test Cases")
 @ParameterizedTest(name="{index} -with the input ''{0}'' error expected is ''{1}''")
 @CsvFileSource (resources="/invalidTestCasesrRequestGenerationTest.csv")
 void InvalidTest(String FilePath, String expectedMessage) throws TokenManagementException{
   TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class,()-> {
     myManager.TokenRequestGeneration(FilePath);
   });
   assertEquals (expectedMessage, ex.getMessage());
 }*/
 
 @DisplayName("Blank file test")
 @Test
 void BlankTestfileTest() throws TokenManagementException{
	String FilePath = this.jsonFilesFolder + "BlankFile.json";
	String expectedToken = "Error: input file could not be read.";
	TokenManagementException obtainedToken = Assertions.assertThrows(TokenManagementException.class,()-> {
		myManager.TokenRequestGeneration(FilePath);
	   });
			
	assertEquals (expectedToken, obtainedToken.getMessage());
 }


 @DisplayName("Correct Token Generation")
 @Test
 void CorrectTokenGenerationTest() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder + "Correct.json";
   String expectedToken = "9550500e9a6afe8f078f6217f9ecdabb";
   String obtainedToken = myManager.TokenRequestGeneration(FilePath);
   assertEquals (expectedToken, obtainedToken);
 }


 @DisplayName("None Right Bracket")
 @Test
 void NoneRightBracket() throws TokenManagementException{
	String FilePath = this.jsonFilesFolder + "NoneRightBracket.json";
	String expectedToken ="Error: invalid input data in JSON structure.";
	TokenManagementException obtainedToken = Assertions.assertThrows(TokenManagementException.class,()-> {
		myManager.TokenRequestGeneration(FilePath);
	   });
	assertEquals (expectedToken, obtainedToken.getMessage());
 }
 
 /*TEST DE DEVICE NAME*/
 
 @DisplayName("None Device Name")
 @Test
 void NoneDeviceName() throws TokenManagementException{
	String FilePath = this.jsonFilesFolder + "NoneDeviceName.json";
	String expectedToken ="Error: Device Name not found";
	TokenManagementException obtainedToken = Assertions.assertThrows(TokenManagementException.class,()-> {
		myManager.TokenRequestGeneration(FilePath);
	});
	assertEquals (expectedToken, obtainedToken.getMessage());
 }
 
 @DisplayName("Valor límite(1) en Divice Name")
 @Test
 void DiviceNameSize1() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder + "DiviceNameSize1.json";
   String expectedToken = "687187f23f5759fc4b83e1335c2afc66";
   String obtainedToken = myManager.TokenRequestGeneration(FilePath);
   assertEquals (expectedToken, obtainedToken);
 }
 
 @DisplayName("Valor límite(20) en Divice Name")
 @Test
 void DiviceNameSize20() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder + "DiviceNameSize20.json";
   String expectedToken = "ec8d3dcffca48ca9a95a57e2e8398d16";
   String obtainedToken = myManager.TokenRequestGeneration(FilePath);
   assertEquals (expectedToken, obtainedToken);
 }

 @DisplayName("Tamaño incorrecto (0) en Divice Name")
 @Test
 void DiviceNameSize0() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder + "DiviceNameSize0.json";
   String expectedToken = "Error: Device Name size is not correct";
   TokenManagementException obtainedToken = Assertions.assertThrows(TokenManagementException.class,()-> {
		myManager.TokenRequestGeneration(FilePath);
	});
	assertEquals (expectedToken, obtainedToken.getMessage());
 }

 @DisplayName("Tamaño incorrecto (21) en Divice Name")
 @Test
 void DiviceNameSize21() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder + "DiviceNameSize21.json";
   String expectedToken = "Error: Device Name size is not correct";
   TokenManagementException obtainedToken = Assertions.assertThrows(TokenManagementException.class,()-> {
		myManager.TokenRequestGeneration(FilePath);
	});
	assertEquals (expectedToken, obtainedToken.getMessage());
 }
 
 /*TEST DE TYPE OF DEVICE*/
 
 @DisplayName("No existe Type Of Divice")
 @Test
 void NoneTypeOfDivice() throws TokenManagementException{
	String FilePath = this.jsonFilesFolder + "NoneTypeOfDevice.json";
	String expectedToken ="Error: type Of Device not found";
	TokenManagementException obtainedToken = Assertions.assertThrows(TokenManagementException.class,()-> {
		myManager.TokenRequestGeneration(FilePath);
	});
	assertEquals (expectedToken, obtainedToken.getMessage());
 }
 
 @DisplayName("Valor de type of device correcto(Sensor)")
 @Test
 void TypeOfDeviceS() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder + "Correct.json";
   String expectedToken = "9550500e9a6afe8f078f6217f9ecdabb";
   String obtainedToken = myManager.TokenRequestGeneration(FilePath);
   assertEquals (expectedToken, obtainedToken);
 }
 
 @DisplayName("Valor de type of device correcto(Actuator)")
 @Test
 void TypeOfDeviceA() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder + "TypeOfDeviceA.json";
   String expectedToken = "c14aa3000379857ead10707c7fcf7c49";
   String obtainedToken = myManager.TokenRequestGeneration(FilePath);
   assertEquals (expectedToken, obtainedToken);
 }
 
 @DisplayName("Valor de type of device incorrecto(Error)")
 @Test
 void TypeOfDeviceE() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder + "TypeOfDeviceError.json";
   String expectedToken = "Error: type of device is not correct";
   TokenManagementException obtainedToken = Assertions.assertThrows(TokenManagementException.class,()-> {
		myManager.TokenRequestGeneration(FilePath);
	});
	assertEquals (expectedToken, obtainedToken.getMessage());
 }
 

 @DisplayName("Valor de type of device incorrecto(null)")
 @Test
 void TypeOfDeviceN() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder + "TypeOfDeviceNull.json";
   String expectedToken = "Error: type of device is not correct";
   TokenManagementException obtainedToken = Assertions.assertThrows(TokenManagementException.class,()-> {
		myManager.TokenRequestGeneration(FilePath);
	});
	assertEquals (expectedToken, obtainedToken.getMessage());
 }
 

 /*TEST CON DRIVER VERSION*/
 
 @DisplayName("La entrada de Drive Version debe ser valores numéricos y no lo es")
 @Test
 void NoneDriverVersion() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder + "NoneDriverVersion.json";
   String expectedToken = "Error: Driver Version not found";
   TokenManagementException obtainedToken = Assertions.assertThrows(TokenManagementException.class,()-> {
		myManager.TokenRequestGeneration(FilePath);
	});
	assertEquals (expectedToken, obtainedToken.getMessage());
 }
 
 
 /*La entrada debe ser valores numéricos y puntos*/
 @DisplayName("La entrada de Drive Version debe ser valores numéricos y no lo es")
 @Test
 void DriverVersionND() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder + "DriverVersionNoDigital.json";
   String expectedToken = "Error: driver version is not digital";
   TokenManagementException obtainedToken = Assertions.assertThrows(TokenManagementException.class,()-> {
		myManager.TokenRequestGeneration(FilePath);
	});
	assertEquals (expectedToken, obtainedToken.getMessage());
 }
 

 @DisplayName("La entrada de Driver Version debe ser valores numéricos")
 @Test
 void DriverVersionN() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder + "Correct.json";
   String expectedToken = "9550500e9a6afe8f078f6217f9ecdabb";
   String obtainedToken = myManager.TokenRequestGeneration(FilePath);
   assertEquals (expectedToken, obtainedToken);
 }
 
 /*La longitud de Driver Version no debe sobrepasar de 25*/
 @DisplayName("Longitud mayor 25 Driver Version, debe dar error")
 @Test
 void DriverVersionSize() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder + "DiviceNameSize21.json";
   String expectedToken = "Error: Device Name size is not correct";
   TokenManagementException obtainedToken = Assertions.assertThrows(TokenManagementException.class,()-> {
		myManager.TokenRequestGeneration(FilePath);
	});
	assertEquals (expectedToken, obtainedToken.getMessage());
 }
 
 @DisplayName("Solo existe un punto sin dígitos")
 @Test
 void DriverVersionPoint() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder + "DiviceNameSize1.json";
   String expectedToken = "Error: driver version structure not correct";
   TokenManagementException obtainedToken = Assertions.assertThrows(TokenManagementException.class,()-> {
		myManager.TokenRequestGeneration(FilePath);
	});
	assertEquals (expectedToken, obtainedToken.getMessage());
 }
 
 @DisplayName("No existe punto")
 @Test
 void DriverVersionNonePoint() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder + "TypeOfDeviceA.json";
   String expectedToken = "Error: driver version structure not correct";
   TokenManagementException obtainedToken = Assertions.assertThrows(TokenManagementException.class,()-> {
		myManager.TokenRequestGeneration(FilePath);
	});
	assertEquals (expectedToken, obtainedToken.getMessage());
 }
 
 @DisplayName("Valor límite: entrada de 1número y 1 punto")
 @Test
 void DriverVersion() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder + "DriverVersion.json";
   String expectedToken = " 33947ecae44207f7d651ef1c829fb62";
   String obtainedToken = myManager.TokenRequestGeneration(FilePath);
   assertEquals (expectedToken, obtainedToken);
 }
 
 
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

  /*
  @Test
  void Test() {
    fail("Not yet implemented");
  }*/



}