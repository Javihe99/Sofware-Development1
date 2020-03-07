package Transport4Future.TokenManagement;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TokenTest {
  
   private TokenManager myManager;
   private String jsonFilesFolder;
  
  public TokenTest() {
    jsonFilesFolder = System.getProperty("user.dir")+"/JSONFiles/TokenRequest/";
    
    myManager = new TokenManager();
  }
  
  @DisplayName ("File is missing")
  @Test
  void FileIsMissingTest() throws TokenManagementException{
    String FilePath = this.jsonFilesFolder+"jfksalkjCorrect.json";
    String expectedMessage = "Erro: input file not found.";
    TokenManagementException ex= Assertions.assertThrows(TokenManagementException.class,()-> {
      myManager.TokenRequestGeneration(FilePath);
    });
    assertEquals(expectedMessage,ex.getMessage());
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