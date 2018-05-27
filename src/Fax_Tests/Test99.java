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
* This test tests a Multiple-sendings to valid and invalid users
* -----------------
* Tests:
*    1. Deposit a fax with multiple valid and invalid numbers as a targets
* 
* Results:
*    1. The following fax parts should arrive
*		- 1 status message
*		- 2 result messages (each one - 1 email with 1 pdf attachment)
*		- 2 result messages (each one - 1 email with 1 pdf attachment)
* 	 2. The error messages should appear at result message.
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test99 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;
  WebFuncs				webFuncs;

  // Default constructor for print the name of the used browser 
  public Test99(String browser) {
	  
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
    webFuncs  = new WebFuncs();
  }

  @Test
  public void Test99___Multiple_sendings_valid_and_invalid_users() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  String resMessage;
	  
	  // Activate script with the needed configuration
	  testFuncs.myDebugPrinting("Activate script with the needed configuration");
	  String[] extraData = {"3"};
	  webFuncs.setConfiguration(99, "Multiple sendings - invalid users", extraData);
		
	  // Step 1 - Deposit a fax with multiple valid and invalid users
	  testFuncs.myDebugPrinting("Step 1 - Deposit a fax with multiple valid and invalid users");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test99.eml");
	  dataMap.put("raiseError"		 ,  "0");
	  dataMap.put("fileNumber"		 ,  "7");
	  dataMap.put("isMultipleTargets",  "1");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap); 
	  
	  // Step 2 - Check fax
	  testFuncs.myDebugPrinting("Step 2 - Check fax");
	  resMessage = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[2] + ".txt");    
	  testFuncs.detectHeader(resMessage, "0545599607"	  , "Succeeded to send");  
	  testFuncs.detectHeader(resMessage, "0245599607"	  , "Reject");  
	  testFuncs.detectHeader(resMessage, "0345599607"	  , "Reject");  
	  testFuncs.detectHeader(resMessage, "To Fax Numbers:", "0245599607, 0345599607, 0545599607"); 
  }

  @After
  public void tearDown() throws Exception {
	  
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
    	
		fail("Error !!");
    }
  }
}