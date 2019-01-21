package Fax_Tests;

import static org.junit.Assert.fail;

import java.io.File;
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
* This test tests a fax deposit to non existing user
* -----------------
* Tests:
*    1. Deposit a fax to non existing user
*    2. Deposit a fax to non existing user and verify that message still can be sent and received when failed message
*       is waiting for retry
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
public class Test62 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;
  WebFuncs				webFuncs;

  // Default constructor for print the name of the used browser 
  public Test62(String browser) {
	  
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
  public void test1() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  String errMsg;
	  String faxFailed = testVars.getFaxFailureHeader();
	  
	  // Step 1 - Deposit a fax to non existing user
	  testFuncs.myDebugPrinting("Step 1 - Deposit a fax to user that has no Fax-ID");
	  dataMap.put("outputPath", testVars.getOutputDirPath() + "Test62.eml");
	  dataMap.put("raiseError",  "0");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  errMsg = testFuncs.readFile(testVars.getRootDir()  + "\\error\\" + faxFailed + ".txt");  
	  testFuncs.myAssertTrue("To header was not detected !!", errMsg.contains("Reject"));
  }
  
  @Test
  public void test2() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  String faxFailed = testVars.getFaxFailureHeader();
  
	  // Step 2 - Deposit a fax to non existing user and verify that message still can be sent and received when failed message is waiting for retry
	  testFuncs.myDebugPrinting("Step 2 - Deposit a fax to non existing user and verify that message still can be sent and received when failed message is waiting for retry");
	  dataMap.put("outputPath", testVars.getOutputDirPath() + "Test62.eml");
	  dataMap.put("maxWaitTime", "10");
	  dataMap.put("raiseError", "0");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  
	  // Deposit a good fax while the problematic fax in retries mechanism
	  testFuncs.myDebugPrinting("Deposit a good fax while the problematic fax in retries mechanism", enumsClass.logModes.NORMAL);
	  dataMap.put("outputPath", testVars.getOutputDirPath() + "Test1.eml");
	  dataMap.put("maxWaitTime", "300");
	  dataMap.put("raiseError" , "1");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  
	  // Waits for the failure message from the first fax
	  testFuncs.myDebugPrinting("Waits for the failure message from the first fax", enumsClass.logModes.NORMAL);
	  
	  // Check for Error message
	  String emailsMainDir = testVars.getRootDir();	
	  String errorFile 	 = emailsMainDir  + "\\error\\"      + faxFailed + ".txt";
	  String errorFileAtt  = emailsMainDir  + "\\error\\att\\" + faxFailed + ".pdf";
	  File fError  = new File(errorFile), fErrorFileAtt = new File(errorFileAtt);
	  int counter = 0;
	  while (true) {
			
		  if((fError.exists() && !fError.isDirectory()) || (fErrorFileAtt.exists() && !fErrorFileAtt.isDirectory())) {

			  break;
		  } else {
			  
			  if (counter > 15) {
			  
				  testFuncs.myFail("15 minutes passed and error-file was not detected !!");
			  } else {
				  
				  counter++;
				  testFuncs.myDebugPrinting("Keep waiting for error file .. (" + counter + " minutes passed)", enumsClass.logModes.MINOR);
				  testFuncs.myWait(60000);
			  }
		  }
	  }
  }

  @After
  public void tearDown() throws Exception {
	 
	  String verificationErrorString = verificationErrors.toString();
	  if (!"".equals(verificationErrorString)) {
		  
		fail("Error !!");
	  }
  }
}