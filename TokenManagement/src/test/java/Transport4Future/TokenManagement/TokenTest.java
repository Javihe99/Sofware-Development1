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
   
 

 @DisplayName("Invalid Test Cases")
 @ParameterizedTest(name="{index} -with the input ''{0}'' error expected is ''{1}''")
 @CsvFileSource (resources="/invalidTestCasesrRequestGenerationTest.csv")
 void InvalidTest(String FilePath, String expectedMessage) throws TokenManagementException{
   TokenManagementException ex = Assertions.assertThrows(TokenManagementException.class,()-> {
     myManager.TokenRequestGeneration(FilePath);
   });
   assertEquals (expectedMessage, ex.getMessage());
 }

 @DisplayName("Correct Token Generation")
 @Test
 void CorrectTokenGenerationTest() throws TokenManagementException{
   String FilePath = this.jsonFilesFolder + "Correct.json";
   String expectedToken = "5136a7fc64013259b958ebc707530c7e";
   String obtainedToken = myManager.TokenRequestGeneration(FilePath);
   assertEquals (expectedToken, obtainedToken);
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

  @Test
  void Test() {
    fail("Not yet implemented");
  }



}