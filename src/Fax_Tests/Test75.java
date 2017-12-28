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
* This test tests a send of Multiple-valid-targets messages
* -----------------
* Tests:
*    1. Deposit a Fax with Multiple-valid-targets
* 
* Results:
*  	 1. 7 messages should be received:
*		- 1 status message
*		- 4 body messages (2 emails with 2 pdf attachments)
*		- 2 result messages (1 email with 1 pdf attachment)
*	 2. Success sendings should appear
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test75 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;
  WebFuncs				webFuncs;

  // Default constructor for print the name of the used browser 
  public Test75(String browser) {
	  
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
  public void Test74___Fax_with_odg_attachment() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  
	  // Step 1 - Deposit a Fax with one odg attachment
	  testFuncs.myDebugPrinting("Step 1 - Deposit a Fax with one odg attachment");
	  dataMap.put("outputPath"		 ,  testVars.getOutputDirPath() + "Test75.eml");
	  dataMap.put("fileNumber"		 ,  "7");
	  dataMap.put("isMultipleTargets",  "1"); 
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  
	  // Step 2 - Check result message
	  testFuncs.myDebugPrinting("Step 2 - Check result message");
	  String bodyText = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[2] + ".txt");  
	  testFuncs.detectHeader(bodyText, "0545599607", "Succeeded to send");
	  testFuncs.detectHeader(bodyText, "0545599608", "Succeeded to send");
  }

  @After
  public void tearDown() throws Exception {
	  
	  String verificationErrorString = verificationErrors.toString();
	  if (!"".equals(verificationErrorString)) {
		  
		fail("Error !!");
	  }
  }
}