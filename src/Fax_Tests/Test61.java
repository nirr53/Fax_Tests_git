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
* This test tests a fax deposit when the Display-Remote-ID is off
* -----------------
* Tests:
* 	 - At Fax-Out settings, check-off the Display-Remote-ID checkbox
*    1. Deposit a fax to user that has no Fax-ID.
*    2. Deposit a fax to user that has Fax-ID.
* 
* Results:
* 	In all cases:
*    1. All the three parts of fax - status, body and result should be accepted on given timeout.
* 	 2. The Remote-ID value should not be displayed.
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test61 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;
  WebFuncs				webFuncs;

  // Default constructor for print the name of the used browser 
  public Test61(String browser) {
	  
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
	  webFuncs.setConfiguration(61, "FaxOut - Display Remote-Id", extraData);	 
  }
  
  @Test
  public void test1() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  String resMsg;
	  
	  // Step 1 - Deposit a fax to user that has no Fax-ID
	  testFuncs.myDebugPrinting("Step 1 - Deposit a fax to user that has no Fax-ID");
	  dataMap.put("outputPath", testVars.getOutputDirPath() + "Test61_1.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap); 
	  resMsg = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[2] + ".txt");  
	  testFuncs.myAssertTrue("Header was detected !!", !resMsg.contains("Nir Klieman Display"));
  }
  
  @Test
  public void test2() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  String resMsg;
	  
	  // Step 2 - Deposit a fax to user that has Fax-ID
	  testFuncs.myDebugPrinting("Step 2 - Deposit a fax to user that has Fax-ID");
	  dataMap.put("outputPath", testVars.getOutputDirPath() + "Test61_2.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  resMsg = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[2] + ".txt");  
	  testFuncs.myAssertTrue("Header was detected !!", !resMsg.contains("FaxIn_FaxID"));  
  }
  
  @Test
  public void test3() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  
	  // Activate script with the needed configuration
	  testFuncs.myDebugPrinting("Activate script with the needed configuration");
	  String[] extraData = {"on"};
	  webFuncs.setConfiguration(61, "FaxOut - Display Remote-Id", extraData);	 
  } 

  @After
  public void tearDown() throws Exception {
	  
	  String verificationErrorString = verificationErrors.toString();
	  if (!"".equals(verificationErrorString)) {
		  
		fail("Error !!");
	  }
  }
}