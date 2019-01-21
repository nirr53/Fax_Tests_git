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
* This test tests the Fax-ID Select Method (System-Fax-ID) with longest default cli
* -----------------
* Tests:
* 	 - Set the Fax-ID Select method to be 'System-Fax-ID'
* 	 - Set the Fax-ID to be 'Simple_text2'
*    - Set the default CLI to be '12345678901234567890'
*    1. Send a fax.
* 
* Results:
*    1. All the three parts of fax - status, body and result should be accepted on given timeout.
* 	    The Fax-ID header should be 'Simple_text2'.
* 	    The default CLI should be: '12345678901234567890'.
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test86 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;
  WebFuncs				webFuncs;

  // Default constructor for print the name of the used browser 
  public Test86(String browser) {
	  
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
	  String faxId  = "Simple_text2";
	  String defCli = "1234567890123456789";
	  
	  // Activate script with the needed configuration
	  testFuncs.myDebugPrinting("Activate script with the needed configuration");
	  String[] extraData = {"System Fax ID", faxId, defCli};
	  webFuncs.setConfiguration(86, "Fax Id with longest_default cli", extraData);
  }
  
  @Test
  public void test1() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  String faxId  = "Simple_text2";
	  String defCli = "1234567890123456789";
		
	  // Step 1 - Deposit a fax
	  testFuncs.myDebugPrinting("Step 1 - Deposit a fax");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test86.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap); 
	  
	  // Step 2 - Check Fax
	  testFuncs.myDebugPrinting("Step 2 - Check Fax");
	  String bodyMsg = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[1] + ".txt");  
	  testFuncs.detectHeader(bodyMsg, "Fax from:", faxId);  
	  testFuncs.detectHeader(bodyMsg, "Fax from:", defCli);  
  }

  @After
  public void tearDown() throws Exception {
	  
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
    	
		fail("Error !!");
    }
  }
}