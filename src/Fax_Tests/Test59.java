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
* This test tests a fax deposit when the Send Email Confirmation is disabled
* -----------------
* Tests:
* 	 - At Fax-Out settings, check-off the Email Confirmation checkbox 
*    1. Deposit a fax
* 
* Results:
*	1. Only the body and result messages should be accepted on given timeout.
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test59 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;
  WebFuncs				webFuncs;

  // Default constructor for print the name of the used browser 
  public Test59(String browser) {
	  
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
	  String[] extraData = {"off"};
	  webFuncs.setConfiguration(59, "FaxOut - Email Confirmation", extraData);
  }
  
  @Test
  public void test1() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  
	  // Step 1 - Deposit a fax
	  testFuncs.myDebugPrinting("Step 1 - Deposit a fax");
	  dataMap.put("outputPath", testVars.getOutputDirPath() + "Test59.eml");
	  dataMap.put("fileNumber", "4");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
  }
  
  @Test
  public void test2() throws Exception {
	  
	  // Activate script with the needed configuration
	  testFuncs.myDebugPrinting("Activate script with the needed configuration");
	  String[] extraData = {"on"};
	  webFuncs.setConfiguration(59, "FaxOut - Email Confirmation", extraData);
  }

  @After
  public void tearDown() throws Exception {
	  
	  String verificationErrorString = verificationErrors.toString();
	  if (!"".equals(verificationErrorString)) {
		  
		fail("Error !!");
	  }
  }
}