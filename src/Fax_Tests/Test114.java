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
* This test tests a fax-deposit to multiple-valid-targets number which bigger than Maximum Recipients number
* -----------------
* Tests:
*    1. Deposit a fax to multiple-valid-targets number which bigger than Maximum Recipients number
* 
* Results:
*    1. Failure message should be detected
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test114 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;
  WebFuncs				webFuncs;

  // Default constructor for print the name of the used browser 
  public Test114(String browser) {
	  
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
  public void Test114___Max_recipients_smaller_than_targets_number() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  
	  // Activate script with the needed configuration
	  testFuncs.myDebugPrinting("Activate script with the needed configuration");
	  String[] extraData = {"1"};
	  webFuncs.setConfiguration(114, "Set Max recipients number", extraData);	
		
	  // Step 1 - Deposit a fax with more targets than max recipients number
	  testFuncs.myDebugPrinting("Step 1 - Deposit a fax with more targets than max recipients number");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test114.txt");
	  dataMap.put("raiseError",  "0");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
 	  
	  // Check headers
	  String errorMsg = testFuncs.readFile(testVars.getRootDir()  + "\\error\\" +  testVars.getFaxFailureHeader() + ".txt");
	  testFuncs.myAssertTrue("To header was not detected !!", errorMsg.contains("0545599607"));  
  }

  @After
  public void tearDown() throws Exception {
	  	  
//	// Reset configuration
//	testFuncs.myDebugPrinting("Reset configuration");
//	String[] extraData = {"3"};
//	webFuncs.setConfiguration(114, "Set Max recipients number", extraData);	  
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
    	
		fail("Error !!");
    }
  }
}