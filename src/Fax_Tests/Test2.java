package Fax_Tests;

import static org.junit.Assert.fail;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import Fax_Tests.GlobalFuncs;

/**
* ----------------
* This test tests the Fax-status normal headers
* -----------------
* Tests:
*    1. Send a fax.
* 
* Results:
*    1. All the three parts of fax - status, body and result should be accepted on given timeout.
* 	 2. Subject header should be detected.
* 	 3. To header should be detected.
* 	 4. Display-Name header should be detected.
* 	 5. Time header should be detected.
* 
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test2 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;

  // Default constructor for print the name of the used browser 
  public Test2(String browser) {
	  
  }
  
  //Define each browser as a parameter
  @SuppressWarnings("rawtypes")
  @Parameters(name="{0}")
  public static Collection data() {
	  
    return Arrays.asList(new GlobalVars().getBrowsers());
  }
  
  @BeforeClass 
  public static void setting_SystemProperties() {
	  
      System.out.println("System Properties seting Key value.");
  }  
  
  @Before
  public void setUp() throws Exception {
	  	
	testVars  = new GlobalVars();
    testFuncs = new GlobalFuncs(); 
  }

  @Test
  public void Test2___Fax_status_headers() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
		
	  // Step 1 - Deposit a fax
	  testFuncs.myDebugPrinting("Step 1 - Deposit a fax");
	  Map<String, String> dataMap = new HashMap<String, String>();
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test2.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	    
	  // Step 2 - check Fax-Status headers
	  testFuncs.myDebugPrinting("Step 2 - check Fax-Status headers");
	  String path = testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[0] + ".txt";		  
	  testFuncs.myDebugPrinting("path - " + path, enumsClass.logModes.MINOR);  
	  String statusMsg = testFuncs.readFile(path);
	  testFuncs.myAssertTrue("Subject header was not detedted !!", statusMsg.contains("Test 2 - Test Fax status"));
	  testFuncs.myAssertTrue("To header was not detected !!"     , statusMsg.contains("0545599607"));
	  testFuncs.myAssertTrue("Display-name header was not detected !!"     , statusMsg.contains("Hello \"Nir Klieman_1\""));  
  }

  @After
  public void tearDown() throws Exception {
	  
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
    	
		fail("Error !!");
    }
  }
}