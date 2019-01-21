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
* This test tests From-Email-Address
* -----------------
* Tests:
* 	 - Change the From-Email-Address field 
*    1. Deposit a fax
* 
* Results:
*    1. All the three parts of fax - status, body and result should be accepted on given timeout.
*    2. The from-email-address should appear on the fax-status email.
*    3. The from-email-address should appear on the fax-body email.
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test90 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;
  WebFuncs				webFuncs;

  // Default constructor for print the name of the used browser 
  public Test90(String browser) {
	  
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
	  String[] extraData = {"nir.klieman@audiocodes.com"};
	  webFuncs.setConfiguration(90, "General Settings - Email Address", extraData);
  }
  
  @Test
  public void test1() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  String bodyMsg;
		
	  // Step 1 - Deposit a fax
	  testFuncs.myDebugPrinting("Step 1 - Deposit a fax");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test90.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap); 
	  
	  // Step 2 - Check fax
	  testFuncs.myDebugPrinting("Step 2 - Check fax");
	  bodyMsg = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[0] + ".txt");  
	  testFuncs.myAssertTrue("Header was not detected !!", bodyMsg.contains("nir.klieman@audiocodes.com"));  
	  bodyMsg = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[1] + ".txt");  
	  testFuncs.myAssertTrue("Header was not detected !!", bodyMsg.contains("nir.klieman@audiocodes.com"));  	  
  }

  @After
  public void tearDown() throws Exception {
	  
	String[] extraData = {testVars.getDefEmail()};
	webFuncs.setConfiguration(90, "General Settings - Email Address", extraData);  
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
    	
		fail("Error !!");
    }
  }
}