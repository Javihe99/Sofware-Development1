package Transport4Future.TokenManagement;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Token2Test {

  TokenManager myManager;
  
  
  @DisplayName("File is missing")
  
  @Test
  void FileIsMissingTest() throws TokenManagementException{
  String FilePath=this.jsonFilesFolder + "kadfadsfladfk.kson";
  String exepctedMessage ="Error: input file not found.";
  TokenManagementException ex=Assertions.assertThrows(TokenManagementException).class, () ->{
    
  }});
  }
  
  }
  
  
  
  @BeforeAll
  static void setUpBeforeClass() throws Exception {
  }

  @AfterAll
  static void tearDownAfterClass() throws Exception {
  }

  @BeforeEach
  void setUp() throws Exception {
  }

  @AfterEach
  void tearDown() throws Exception {
  }

  @Test
  void test() {
    fail("Not yet implemented");
  }

}
