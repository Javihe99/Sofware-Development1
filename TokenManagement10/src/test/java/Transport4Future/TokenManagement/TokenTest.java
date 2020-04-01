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
 
 /* Caso de Prueba: "No existe archivo"
 * Clase de Equivalencia o Valor Límite Asociado: <Valor>
 * Técnica de prueba: <Clases de Equivalencia | Valor Límite>
 * Resultado Esperado: No existe el archivo JSON
 */ 
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
 /*Test correcto*/
 @DisplayName("Correct Token Generation")
 @Test
 void CorrectTokenGenerationTest1() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder + "Correct1.json";
   String expectedToken = "38394b8b32cc11bf795868dd3cc606b4";
   String obtainedToken = myManager.TokenRequestGeneration(FilePath);
   assertEquals (expectedToken, obtainedToken);
 }
 
 
 /*Vamos a realizar comprobaciones en el email*/ 

 /*No existe el campo Email*/
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
 
 /*Email sin que no lleva @*/
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
 
 /*Email que no presenta Dominio*/
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
 
 /*Email que no tiene nombre*/
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
 
 /*Email que no dispone puntos*/
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
 
 /*Email que no tiene escrito el servidor*/
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
 
 /*Email que tiene el nombre con simbolos raros*/
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
 
 /*Email con números en el dominio*/
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
 
 /*El valor del Email es vacío*/
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
 
 /*No hay el campo Serial Number*/
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
 
 /*Serial Number cuyo contenido no sigue el formato*/
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
 
 /*El valor del Serial number es vacío*/
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
 
 /*Serial Number con un espacio*/
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
 
 /*No existe el campo macAdress*/
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
 
 /*macAddress que tiene espacio*/
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
 
 /*macAddress que solo tiene un elemento*/
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
 /*macaddress con simbolos que no están permitidos*/
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

 /*Un fichero json vacío*/
 @DisplayName("Blank file test")
 @Test
 void BlankTestfileTest() throws TokenManagementException{
	String FilePath = this.jsonFilesFolder + "BlankFile.json";
	String expectedToken = "Error: input file empty.";
	TokenManagementException obtainedToken = Assertions.assertThrows(TokenManagementException.class,()-> {
		myManager.TokenRequestGeneration(FilePath);
	   });
			
	assertEquals (expectedToken, obtainedToken.getMessage());
 }




 /*TEST DE DEVICE NAME*/
 /*No existe el campo de Device Name*/	
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
 /*Pruebas con valores límites en Device Name: con longitud 1*/
 @DisplayName("Valor límite(1) en Divice Name")
 @Test
 void DiviceNameSize1() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder + "DiviceNameSize1.json";
   String expectedToken = "57ee370a63223e0e50ef518c7991c6f7";
   String obtainedToken = myManager.TokenRequestGeneration(FilePath);
   assertEquals (expectedToken, obtainedToken);
 }

 /*Pruebas con valores límites en Device Name: con longitud 20*/
 @DisplayName("Valor límite(20) en Divice Name")
 @Test
 void DiviceNameSize20() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder + "DiviceNameSize20.json";
   String expectedToken = "ec8d3dcffca48ca9a95a57e2e8398d16";
   String obtainedToken = myManager.TokenRequestGeneration(FilePath);
   assertEquals (expectedToken, obtainedToken);
 }

 /*Pruebas con valores fuera de los límites en Device Name: con longitud 0*/
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
 /*Pruebas con valores fuera de los límites en Device Name: con longitud 21*/
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
 /*No existe el campo de Type Of Device*/
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
 /*Dominio correcto en Type Of Device : Sensor*/
 @DisplayName("Valor de type of device correcto(Sensor)")
 @Test
 void TypeOfDeviceS() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder + "Correct.json";
   String expectedToken = "9550500e9a6afe8f078f6217f9ecdabb";
   String obtainedToken = myManager.TokenRequestGeneration(FilePath);
   assertEquals (expectedToken, obtainedToken);
 }
 /*Dominio correcto en Type Of Device : Actuator*/
 @DisplayName("Valor de type of device correcto(Actuator)")
 @Test
 void TypeOfDeviceA() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder + "TypeOfDeviceA.json";
   String expectedToken = "c14aa3000379857ead10707c7fcf7c49";
   String obtainedToken = myManager.TokenRequestGeneration(FilePath);
   assertEquals (expectedToken, obtainedToken);
 }
 
 /*Dominio incorrecto en Type Of Device : Error*/
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

 
 /*El valor de Type Of Device es null*/
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
 
 /*No existe el campo Driver Version*/
 @DisplayName("No existe Driver Version")
 @Test
 void NoneDriverVersion() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder + "NoneDriverVersion.json";
   String expectedToken = "Error: Driver Version not found";
   TokenManagementException obtainedToken = Assertions.assertThrows(TokenManagementException.class,()-> {
		myManager.TokenRequestGeneration(FilePath);
	});
	assertEquals (expectedToken, obtainedToken.getMessage());
 }
 
 
 /*Valor no numéricos (letras) en Driver Version, por lo que debe dar error*/
 @DisplayName("Existe letras en Driver Version")
 @Test
 void DriverVersionND() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder + "DriverVersionNoDigital.json";
   String expectedToken = "Error: driver version is not digital";
   TokenManagementException obtainedToken = Assertions.assertThrows(TokenManagementException.class,()-> {
		myManager.TokenRequestGeneration(FilePath);
	});
	assertEquals (expectedToken, obtainedToken.getMessage());
 }
 
/*Entrada con números y puntos en Driver Version, como debe ser*/
 @DisplayName("Correct Driver Version")
 @Test
 void DriverVersionN() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder + "Correct.json";
   String expectedToken = "9550500e9a6afe8f078f6217f9ecdabb";
   String obtainedToken = myManager.TokenRequestGeneration(FilePath);
   assertEquals (expectedToken, obtainedToken);
 }
 
 /*La longitud de Driver Version no debe sobrepasar de 25 y en este caso lo está sobrepasando*/
 @DisplayName("Longitud mayor 25 Driver Version")
 @Test
 void DriverVersionSize() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder + "DiviceNameSize21.json";
   String expectedToken = "Error: Device Name size is not correct";
   TokenManagementException obtainedToken = Assertions.assertThrows(TokenManagementException.class,()-> {
		myManager.TokenRequestGeneration(FilePath);
	});
	assertEquals (expectedToken, obtainedToken.getMessage());
 }
 /*Lo correcto sería dígitos junto con puntos, pero en este caso no existe dígitos.*/
 @DisplayName("Solo existe un punto sin dígitos")
 @Test
 void DriverVersionPoint() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder + "DriverVersionNoDigit.json";
   String expectedToken = "Error: driver version structure not correct";
   TokenManagementException obtainedToken = Assertions.assertThrows(TokenManagementException.class,()-> {
		myManager.TokenRequestGeneration(FilePath);
	});
	assertEquals (expectedToken, obtainedToken.getMessage());
 }
 /*Igual que la prueba anterior, da el problema al no existir puntos (debe haber al menos 1)*/
 @DisplayName("No existe punto")
 @Test
 void DriverVersionNonePoint() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder + "DriverVersionNoPoint.json";
   String expectedToken = "Error: driver version structure not correct";
   TokenManagementException obtainedToken = Assertions.assertThrows(TokenManagementException.class,()-> {
		myManager.TokenRequestGeneration(FilePath);
	});
	assertEquals (expectedToken, obtainedToken.getMessage());
 }
 
 /*Valor límite con un solo punto y un dígito*/
 @DisplayName("Valor límite: entrada de 1número y 1 punto")
 @Test
 void DriverVersion() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder + "DiviceNameSize1.json";
   String expectedToken = "57ee370a63223e0e50ef518c7991c6f7";
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
=======
  /*@Test
>>>>>>> branch 'EG3' of https://pds.sel.inf.uc3m.es/mcarrero/g50.t6.eg
  void Test() {
    fail("Not yet implemented");
  }*/



}