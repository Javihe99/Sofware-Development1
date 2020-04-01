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
 * Clase de Equivalencia o Valor Límite Asociado: CE-RF1-NV-02
 * Técnica de prueba: Clases de equivalencia
 * Resultado Esperado: Error: input file not found.
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
 /* Caso de Prueba: correcto
  * Clase de Equivalencia o Valor Límite Asociado: CE-RF1-V-01, CE-RF1-V-03,CE-RF1-V-42,CE-RF1-V-43,CE-RF1-V-44...CE-RF1-V-53
  * Técnica de prueba: Clases de equivalencia
  * Resultado Esperado: Correcto, el token
  */ 
 @DisplayName("Correct Token Generation")
 @Test
 void CorrectTokenGenerationTest() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder + "Correct.json";
   String expectedToken = "9550500e9a6afe8f078f6217f9ecdabb";
   String obtainedToken = myManager.TokenRequestGeneration(FilePath);
   assertEquals (expectedToken, obtainedToken);
 }
 /*Test correcto*/
 /* Caso de Prueba: correcto
  * Clase de Equivalencia o Valor Límite Asociado: CE-RF1-V-01, CE-RF1-V-03,CE-RF1-V-42,CE-RF1-V-43,CE-RF1-V-44...CE-RF1-V-53
  * Técnica de prueba: Clases de equivalencia
  * Resultado Esperado: Correcto, el token
  */ 
 @DisplayName("Correct Token Generation")
 @Test
 void CorrectTokenGenerationTest1() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder + "Correct1.json";
   String expectedToken = "38394b8b32cc11bf795868dd3cc606b4";
   String obtainedToken = myManager.TokenRequestGeneration(FilePath);
   assertEquals (expectedToken, obtainedToken);
 }
 
 
 /*Vamos a realizar comprobaciones en el email*/ 

 
 /* Caso de Prueba: No existe el campo Email
  * Clase de Equivalencia o Valor Límite Asociado: CE-RF1-V-01, CE-RF1-V-03,CE-RF1-NV-07
  * Técnica de prueba: Clases de equivalencia
  * Resultado Esperado: supportEmail no encontrado
  */ 
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
 

 
 /* Caso de Prueba: Email sin que no lleva @
  * Clase de Equivalencia o Valor Límite Asociado: CE-RF1-V-01, CE-RF1-V-03,CE-RF1-NV-12,VL-RF1-NV-29
  * Técnica de prueba: Valor Límite
  * Resultado Esperado: Formato supportEmail incorrecto
  */ 
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
 
 
 /* Caso de Prueba: Email que no presenta Dominio
  * Clase de Equivalencia o Valor Límite Asociado: CE-RF1-V-01, CE-RF1-V-03,CE-RF1-NV-12,VL-RF1-NV-30
  * Técnica de prueba: Valor Límite
  * Resultado Esperado: Formato supportEmail incorrecto
  */ 
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
 
 
 
 /* Caso de Prueba: Email que no tiene nombre
  * Clase de Equivalencia o Valor Límite Asociado: CE-RF1-V-01, CE-RF1-V-03,CE-RF1-NV-12,VL-RF1-NV-31
  * Técnica de prueba: Valor Límite
  * Resultado Esperado: Formato supportEmail incorrecto
  */ 
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
 
 
 /* Caso de Prueba: Email que no dispone puntos
  * Clase de Equivalencia o Valor Límite Asociado: CE-RF1-V-01, CE-RF1-V-03,CE-RF1-NV-12,VL-RF1-NV-31
  * Técnica de prueba: Valor Límite
  * Resultado Esperado: Formato supportEmail incorrecto
  */ 
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
 
 
 /* Caso de Prueba: Email que no tiene escrito servidor 
  * Clase de Equivalencia o Valor Límite Asociado: CE-RF1-V-01, CE-RF1-V-03,CE-RF1-NV-12,VL-RF1-NV-32
  * Técnica de prueba: Valor Límite
  * Resultado Esperado: Formato supportEmail incorrecto
  */ 
 
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
 
 
 /* Caso de Prueba: Email con simbolos raros
  * Clase de Equivalencia o Valor Límite Asociado: CE-RF1-V-01, CE-RF1-V-03,CE-RF1-NV-12,VL-RF1-NV-33
  * Técnica de prueba: Valor Límite
  * Resultado Esperado: Formato supportEmail incorrecto
  */ 
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
 
 
 /* Caso de Prueba: Email con numeros en el dominio
  * Clase de Equivalencia o Valor Límite Asociado: CE-RF1-V-01, CE-RF1-V-03,CE-RF1-NV-12,VL-RF1-NV-34
  * Técnica de prueba: Valor Límite
  * Resultado Esperado: Formato supportEmail incorrecto
  */ 
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
 
 
 /* Caso de Prueba: El valor de Email es vacio
  * Clase de Equivalencia o Valor Límite Asociado: CE-RF1-V-01, CE-RF1-V-03,CE-RF1-NV-12,VL-RF1-NV-35
  * Técnica de prueba: Clase de Equivalencia
  * Resultado Esperado: Formato supportEmail incorrecto
  */ 
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
 
 /* Caso de Prueba: No existe el campo Serial Number
  * Clase de Equivalencia o Valor Límite Asociado: CE-RF1-V-01, CE-RF1-V-03,CE-RF1-NV-08
  * Técnica de prueba: Clases de equivalencia
  * Resultado Esperado: serialNumber no encontrado
  */ 
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
 
 /* Caso de Prueba: Serial Number con simbolos raros
  * Clase de Equivalencia o Valor Límite Asociado: CE-RF1-V-01, CE-RF1-V-03,CE-RF1-NV-13, VL-RF1-NV-36.5
  * Técnica de prueba: Valor Límite
  * Resultado Esperado: Formato serialNumber incorrecto
  */ 
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
 
 /* Caso de Prueba: Serial Number con valor vacío
  * Clase de Equivalencia o Valor Límite Asociado: CE-RF1-V-01, CE-RF1-V-03,CE-RF1-NV-13, VL-RF1-NV-36
  * Técnica de prueba: Valor Límite
  * Resultado Esperado: Formato serialNumber incorrecto
  */ 

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
 
 /* Caso de Prueba: Serial Number con un espacio
  * Clase de Equivalencia o Valor Límite Asociado: CE-RF1-V-01, CE-RF1-V-03,CE-RF1-NV-13, VL-RF1-NV-37
  * Técnica de prueba: Valor Límite
  * Resultado Esperado: Formato serialNumber incorrecto
  */ 
 
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
 
 /* Caso de Prueba: No existe el campo macAddress
  * Clase de Equivalencia o Valor Límite Asociado: CE-RF1-V-01, CE-RF1-V-03,CE-RF1-NV-09
  * Técnica de prueba: Clases de equivalencia
  * Resultado Esperado: macAddress no encontrado
  */ 
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
 
 /* Caso de Prueba: MacAddress con puntos
  * Clase de Equivalencia o Valor Límite Asociado: CE-RF1-V-01, CE-RF1-V-03,CE-RF1-NV-14, VL-RF1-NV-38
  * Técnica de prueba: Valor Límite
  * Resultado Esperado: Formato macAddress incorrecto
  */ 
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
 /* Caso de Prueba: MacAddress con espacio
  * Clase de Equivalencia o Valor Límite Asociado: CE-RF1-V-01, CE-RF1-V-03,CE-RF1-NV-14, VL-RF1-NV-39
  * Técnica de prueba: Valor Límite
  * Resultado Esperado: Formato macAddress incorrecto
  */ 
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
 /* Caso de Prueba: MacAddress con un solo elemento
  * Clase de Equivalencia o Valor Límite Asociado: CE-RF1-V-01, CE-RF1-V-03,CE-RF1-NV-14, VL-RF1-NV-40
  * Técnica de prueba: Valor Límite
  * Resultado Esperado: Formato macAddress incorrecto
  */ 
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
 /* Caso de Prueba: MacAddress con simbolos raros
  * Clase de Equivalencia o Valor Límite Asociado: CE-RF1-V-01, CE-RF1-V-03,CE-RF1-NV-14, VL-RF1-NV-41
  * Técnica de prueba: Valor Límite
  * Resultado Esperado: Formato macAddress incorrecto
  */ 
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
 /* Caso de Prueba:Fichero json vacío
 * Clase de Equivalencia o Valor Límite Asociado: CE-RF1-NV-00
 * Técnica de prueba: Clase de equivalencia
 * Resultado Esperado: "Error: input file empty."
 */

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

 /* Caso de Prueba:No existe el campo de Device Name
 * Clase de Equivalencia o Valor Límite Asociado: CE-RF1-NV-05
 * Técnica de prueba: Clase de equivalencia
 * Resultado Esperado: "Error: Device Name not found"
 */
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
 
 /* Caso de Prueba: Pruebas con valores límites en Device Name: con longitud 1
 * Clase de Equivalencia o Valor Límite Asociado: VL-RF1-V-15
 * Técnica de prueba: Valor limite
 * Resultado Esperado: Resultado codificado. 
 */

 @DisplayName("Valor límite(1) en Device Name")
 @Test
 void DiviceNameSize1() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder + "DiviceNameSize1.json";
   String expectedToken = "57ee370a63223e0e50ef518c7991c6f7";
   String obtainedToken = myManager.TokenRequestGeneration(FilePath);
   assertEquals (expectedToken, obtainedToken);
 }

 /* Caso de Prueba: Pruebas con valores límites en Device Name: con longitud 20
 * Clase de Equivalencia o Valor Límite Asociado: VL-RF1-V-16
 * Técnica de prueba: Valor limite
 * Resultado Esperado: Resultado codificado. 
 */
 @DisplayName("Valor límite(20) en Device Name")
 @Test
 void DiviceNameSize20() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder + "DiviceNameSize20.json";
   String expectedToken = "ec8d3dcffca48ca9a95a57e2e8398d16";
   String obtainedToken = myManager.TokenRequestGeneration(FilePath);
   assertEquals (expectedToken, obtainedToken);
 }

 /* Caso de Prueba: Pruebas con valores límites en Device Name: con longitud 0
 * Clase de Equivalencia o Valor Límite Asociado: VL-RF1-V-17
 * Técnica de prueba: Valor limite
 * Resultado Esperado: Error: Device Name size is not correct
 */
 @DisplayName("Tamaño incorrecto (0) en Device Name")
 @Test
 void DiviceNameSize0() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder + "DiviceNameSize0.json";
   String expectedToken = "Error: Device Name size is not correct";
   TokenManagementException obtainedToken = Assertions.assertThrows(TokenManagementException.class,()-> {
		myManager.TokenRequestGeneration(FilePath);
	});
	assertEquals (expectedToken, obtainedToken.getMessage());
 }

 /* Caso de Prueba: Pruebas con valores límites en Device Name: con longitud 21
 * Clase de Equivalencia o Valor Límite Asociado: VL-RF1-V-18
 * Técnica de prueba: Valor limite
 * Resultado Esperado: Error: Device Name size is not correct
 */
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
 
 /* Caso de Prueba:No existe el campo de Type Of Device
 * Clase de Equivalencia o Valor Límite Asociado: CE-RF1-NV-06
 * Técnica de prueba: Clase de equivalencia
 * Resultado Esperado: "Error: type Of Device not found"
 */
 @DisplayName("No existe Type Of Device")
 @Test
 void NoneTypeOfDivice() throws TokenManagementException{
	String FilePath = this.jsonFilesFolder + "NoneTypeOfDevice.json";
	String expectedToken ="Error: type Of Device not found";
	TokenManagementException obtainedToken = Assertions.assertThrows(TokenManagementException.class,()-> {
		myManager.TokenRequestGeneration(FilePath);
	});
	assertEquals (expectedToken, obtainedToken.getMessage());
 }
 
 /* Caso de Prueba: Dominio correcto en Type Of Device : Sensor
  * Clase de Equivalencia o Valor Límite Asociado: VL-RF1-V-19
  * Técnica de prueba: Valor límite
  * Resultado Esperado: Resultado codificado
  */
 
 @DisplayName("Valor de type of device correcto(Sensor)")
 @Test
 void TypeOfDeviceS() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder + "Correct.json";
   String expectedToken = "9550500e9a6afe8f078f6217f9ecdabb";
   String obtainedToken = myManager.TokenRequestGeneration(FilePath);
   assertEquals (expectedToken, obtainedToken);
 }
 
 /* Caso de Prueba: Dominio correcto en Type Of Device : Actuator
  * Clase de Equivalencia o Valor Límite Asociado: VL-RF1-V-20
  * Técnica de prueba: Valor límite
  * Resultado Esperado: Resultado codificado
  */
 @DisplayName("Valor de type of device correcto(Actuator)")
 @Test
 void TypeOfDeviceA() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder + "TypeOfDeviceA.json";
   String expectedToken = "c14aa3000379857ead10707c7fcf7c49";
   String obtainedToken = myManager.TokenRequestGeneration(FilePath);
   assertEquals (expectedToken, obtainedToken);
 }
 
 /* Caso de Prueba: Dominio incorrecto en Type Of Device
  * Clase de Equivalencia o Valor Límite Asociado: VL-RF1-NV-21
  * Técnica de prueba: Valor límite
  * Resultado Esperado: "Error: type of device is not correct"
  */
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

 /* Caso de Prueba: Dominio incorrecto en Type Of Device
  * Clase de Equivalencia o Valor Límite Asociado: CE-RF1-NV-22
  * Técnica de prueba:Clase de equivalencia
  * Resultado Esperado: "Error: type of device is not correct"
  */
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
 
 /* Caso de Prueba:No existe el campo de Driver Version
 * Clase de Equivalencia o Valor Límite Asociado: CE-RF1-NV-06
 * Técnica de prueba: Clase de equivalencia
 * Resultado Esperado: "Error: Driver Version not found"
 */
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
 
 /* Caso de Prueba: Valor no numéricos (letras) en Driver Version
 * Clase de Equivalencia o Valor Límite Asociado: CE-RF1-NV-23
 * Técnica de prueba: Clase de equivalencia
 * Resultado Esperado: "Error: driver version is not digital"
 */
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
 
 /* Caso de Prueba: Entrada con números y puntos en Driver Version
 * Clase de Equivalencia o Valor Límite Asociado: CE-RF1-V-24
 * Técnica de prueba: Clase de equivalencia
 * Resultado Esperado: Salida codificada
 */
 
 @DisplayName("Correct Driver Version")
 @Test
 void DriverVersionN() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder + "Correct.json";
   String expectedToken = "9550500e9a6afe8f078f6217f9ecdabb";
   String obtainedToken = myManager.TokenRequestGeneration(FilePath);
   assertEquals (expectedToken, obtainedToken);
 }
 
 /* Caso de Prueba: Longitud de Driver Version superior de 25
 * Clase de Equivalencia o Valor Límite Asociado: VL-RF1-NV-25
 * Técnica de prueba: Valor límite
 * Resultado Esperado: "Error: Device Name size is not correct"
 */ 
 @DisplayName("Longitud mayor 25 Driver Version")
 @Test
 void DriverVersionSize() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder + "DriverVersionMayor25.json";
   String expectedToken = "Error: driver version structure not correct";
   TokenManagementException obtainedToken = Assertions.assertThrows(TokenManagementException.class,()-> {
		myManager.TokenRequestGeneration(FilePath);
	});
	assertEquals (expectedToken, obtainedToken.getMessage());
 }
 
 /* Caso de Prueba: No existe dígitos
 * Clase de Equivalencia o Valor Límite Asociado: CE-RF1-NV-26
 * Técnica de prueba: Clase de equivalencia
 * Resultado Esperado: "Error: driver version structure not correct"
 */
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
 
 /* Caso de Prueba: No existe puntos
 * Clase de Equivalencia o Valor Límite Asociado: CE-RF1-NV-27
 * Técnica de prueba: Clase de equivalencia
 * Resultado Esperado: "Error: driver version structure not correct"
 */

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
 
 /* Caso de Prueba:  Valor límite con un solo punto y un dígito
 * Clase de Equivalencia o Valor Límite Asociado: VL-RF1-V-28
 * Técnica de prueba: Valor limite
 * Resultado Esperado: Respuesta codificada
 */

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