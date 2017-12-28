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
* This test tests the Fax-result headers
* -----------------
* Tests:
*    1. Send a fax with empty headers
*    2. Send a fax with very long headers
* 
* Results:
*    In all cases:
*    	1. All the three parts of fax - status, body and result should be accepted on given timeout.
* 	 	2. All fax headers should be detected.
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test11 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;

  // Default constructor for print the name of the used browser 
  public Test11(String browser) {
	  
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
  public void Test11___Fax_result_headers() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
		
	  // Step 1 - Deposit a fax
	  testFuncs.myDebugPrinting("Step 1 -  Deposit a fax");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test11_1.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  String bodyMsg = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[1] + ".txt");  
	  testFuncs.myAssertTrue("Display-name header was not detected !!", bodyMsg.contains("Display Name in GUI"));  
	  testFuncs.myAssertTrue("From header was not detected !!"        , bodyMsg.contains("Fax to Mail service2"));
	  testFuncs.myAssertTrue("Subject header was not detected !!"	  , bodyMsg.contains("Fax_Message_Body"));
	  
	  // Step 2 - Deposit a fax
	  testFuncs.myDebugPrinting("Step 2 -  Deposit a fax");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test7_2.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  bodyMsg = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[1] + ".txt");  
	  testFuncs.myAssertTrue("Display-name header was not detected !!", bodyMsg.contains("Display Name in GUI2"));  
	  testFuncs.myAssertTrue("From header was not detected !!"        , bodyMsg.contains("Fax to Mail service2"));
	  testFuncs.myAssertTrue("Subject header was not detected !!"	  , bodyMsg.contains("Fax_Message_Body"));
		
	  // Step 3 - Deposit a fax
	  testFuncs.myDebugPrinting("Step 3 -  Deposit a fax");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test7_3.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  bodyMsg = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[1] + ".txt");  
	  testFuncs.myAssertTrue("Display-name header was not detected !!", bodyMsg.contains("12345678901234567890123456789012345678901234567890"));  
	  testFuncs.myAssertTrue("From header was not detected !!"        , bodyMsg.contains("Fax to Mail service2"));
	  testFuncs.myAssertTrue("Subject header was not detected !!"	  , bodyMsg.contains("Fax_Message_Body"));  
  }

  @After
  public void tearDown() throws Exception {
	  
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
    	
		fail("Error !!");
    }
  }
}