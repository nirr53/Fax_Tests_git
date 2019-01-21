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
* This test tests a NA retries number test
* -----------------
* Tests:
*    1. Send a fax to GW which is simulate NA mode, in which it sends back INVITE messages via 
* 
* Results:
*    1. The number of INVITE packets that should be detected should be equal to 7 * <retryAttempts>
*	 2. Failed-message should be detected.
*	 3. The headers of the failed-message should be sent properly.
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test28 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;
  GlobalTshark			testTshark;

  // Default constructor for print the name of the used browser 
  public Test28(String browser) {
	  
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
	  	
	testVars   = new GlobalVars();
    testFuncs  = new GlobalFuncs(testVars); 
    testTshark = new GlobalTshark(testFuncs, testVars.getTsharkPath());
  }

  @Test
  public void test1() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();		
	  
	  // Test configuration test
	  testFuncs.myDebugPrinting("Test configuration test");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test28.txt");
	  dataMap.put("maxWaitTime",  "1");
	  dataMap.put("raiseError",   "0");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  
	  // Set capture filter
	  testFuncs.myDebugPrinting("Set capture filter");
//	  String capMaxNum	= "21";
//	  String filter		= "-f port " + testVars.getTsharkNaPort();
//	  String outputName = "Test28.txt";
	  
	  // Step 1 - Capture trace
//	  testFuncs.myDebugPrinting("Step 1 -Capture trace");
//	  testTshark.StartCapture(capMaxNum, filter, outputName);
	  
	  // Step 2 - Check headers of the failure message
	  testFuncs.myDebugPrinting("Step 2 - Check headers of the failure message");
	  testFuncs.myWait(740000);
	  testFuncs.myDebugPrinting("Wait ends");
	  String errorMsg = testFuncs.readFile(testVars.getRootDir()  + "\\error\\" +  testVars.getFaxFailureHeader() + ".txt");
	  testFuncs.detectHeader(errorMsg, "To Fax Numbers:", "0565599612");
	  testFuncs.detectHeader(errorMsg, "Subject:"       , "Test 28 - NA retries number test");
	  testFuncs.detectHeader(errorMsg, "0565599612"     , "Other FMS error");  
  }

  @After
  public void tearDown() throws Exception {
	  
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
    	
		fail("Error !!");
    }
  }
}