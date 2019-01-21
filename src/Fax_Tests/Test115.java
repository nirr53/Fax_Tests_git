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
* This test tests a fax-deposit to multiple-valid-targets number which smaller than Maximum Recipients number
* -----------------
* Tests:
*    1. Deposit a fax to multiple-valid-targets number which smaller than Maximum Recipients number
* 
* Results:
*    1. Failure message should be detected
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test115 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;
  WebFuncs				webFuncs;

  // Default constructor for print the name of the used browser 
  public Test115(String browser) {
	  
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
    testFuncs = new GlobalFuncs(testVars); 
    webFuncs  = new WebFuncs(testFuncs, testVars);
  }

  @Test
  public void test0() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  
	  // Activate script with the needed configuration
	  testFuncs.myDebugPrinting("Activate script with the needed configuration");
	  String[] extraData = {"3"};
	  webFuncs.setConfiguration(115, "Set Max recipients number", extraData);	
  }
  
  @Test
  public void test1() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
		
	  // Step 1 - Deposit a fax with less targets than max recipients number
	  testFuncs.myDebugPrinting("Step 1 - Deposit a fax with less targets than max recipients number");
	  dataMap.put("fileNumber"		 ,  "7");
	  dataMap.put("isMultipleTargets",  "1");
	  dataMap.put("outputPath"		 ,  testVars.getOutputDirPath() + "Test115.txt");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
 	  
	  // Check headers
	  testFuncs.myDebugPrinting("Check headers");
	  String resMsg = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[2] + ".txt");  
	  testFuncs.detectHeader(resMsg, "0545599607", "Succeeded to send");
	  testFuncs.detectHeader(resMsg, "0545599608", "Succeeded to send"); 
  }

  @After
  public void tearDown() throws Exception {
	  	  
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
    	
		fail("Error !!");
    }
  }
}