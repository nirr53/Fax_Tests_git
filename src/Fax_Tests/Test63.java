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
* This test tests a fax deposit when no outgoing rule matches.
* -----------------
* Tests:
*   1. Deposit an email to fax, when the target is not exists at the Fax-In table.
*	2. Deposit an email to fax when its domain exists on the fax-out table, but the address not.
*	3. Deposit an email to fax when there is no matching rule at fax-out routing (the 'from' address is valid).
*   4  Deposit a good fax when there is a fax in the retry-mechanism
* 
* Results:
* 	1. Fax should not be deposited - Only the status & result message (of failure) should appear
*	   Fax headers at the failure message should be correct (The failure reason - 'reject' should appear).
*	2. Only the body message should be accepted.
*	3. Fax should not be deposited - Only Status message and Failure message should be received.
*	4. Faxes should be deposited successfully while faxes still on the retry mechanism.
*
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test63 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;
  WebFuncs				webFuncs;

  // Default constructor for print the name of the used browser 
  public Test63(String browser) {
	  
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
  public void Test63___Fax_to_non_outgoing_rule_matches() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  String errMsg;
	  String faxFailed = testVars.getFaxFailureHeader();
	  
//	  // Step 1 - Deposit an email to fax, when the target is not exists at the Fax-In table
//	  testFuncs.myDebugPrinting("Step 1 - Deposit an email to fax, when the target is not exists at the Fax-In table");
//	  dataMap.put("outputPath", testVars.getOutputDirPath() + "Test63_1.eml");
//	  dataMap.put("fileNumber",  "3");
//	  dataMap.put("maxWaitTime", "600");
//	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
//	  String bodyMsg = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[2] + ".txt");  
//	  testFuncs.myAssertTrue("Display-name header was not detected !!", bodyMsg.contains("Reject"));  

//	  // Step 2 - Deposit a fax when domain exists on the fax-out table, but the address not
//	  testFuncs.myDebugPrinting("Step 2 - Deposit a fax when domain exists on the fax-out table, but the address not");
//	  dataMap.put("outputPath", testVars.getOutputDirPath() + "Test63_2.eml");
//	  dataMap.put("fileNumber",  "2");
//	  dataMap.put("maxWaitTime", "600");
//	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap); 
//	  
//	  // Set variables
//	  String []faxHeaders   = testVars.getFaxHeaders();
//	  String inputPath      =  testVars.getRootDir()  + "\\input\\";
//	  String defSuffix      = ".txt";
//	  String attSuffix      = ".pdf";
//	  String attPrefix      = "att_";
//	  String withoutIcon    = "withoutIcon_";
//	  String pathStatus     = inputPath + faxHeaders[0] + defSuffix;
//	  String pathStatus2    = inputPath + withoutIcon   + faxHeaders[0] + defSuffix;	
//	  String pathResult     = inputPath + faxHeaders[2] + defSuffix;
//	  String pathResult2    = inputPath + withoutIcon   + faxHeaders[2] + defSuffix;
//	  String pathResultAtt  = inputPath + attPrefix     + faxHeaders[2] + attSuffix;
//	  String pathResultAtt2 = inputPath + attPrefix     + withoutIcon   + faxHeaders[2] + attSuffix;
//
//	  // Check that files are not found
//	  File f1 = new File(pathStatus), f11 = new File(pathStatus2); 
//	  testFuncs.myAssertTrue("Status file was detected !!"			 , !f1.exists() && !f11.exists());  
//	  File f3  = new File(pathResult), f31 = new File(pathResult2);
//	  testFuncs.myAssertTrue("Result txt file was detected !!"		 , !f3.exists() && !f31.exists()); 
//	  File f5  = new File(pathResultAtt), f51 = new File(pathResultAtt2);
//	  testFuncs.myAssertTrue("Result attachment file was detected !!", !f5.exists() && !f51.exists()); 

	  // Step 3 - Deposit an email to fax when there is no matching rule at fax-out routing (the 'from' address is valid)
	  testFuncs.myDebugPrinting("Step 3 - Deposit an email to fax when there is no matching rule at fax-out routing (the 'from' address is valid)");
	  dataMap.put("outputPath", testVars.getOutputDirPath() + "Test63_3.eml");
	  dataMap.put("raiseError",  "0");
	  dataMap.put("fileNumber",  "5");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  errMsg = testFuncs.readFile(testVars.getRootDir()  + "\\error\\" + faxFailed + ".txt");  
	  testFuncs.myAssertTrue("To header was not detected !!", errMsg.contains("Reject"));

	  // Step 4 - Verify that message still can be sent and received when failed message is waiting for retry
	  testFuncs.myDebugPrinting("Step 4 - Verify that message still can be sent and received when failed message is waiting for retry");
	  dataMap.put("outputPath", testVars.getOutputDirPath() + "Test63_3.eml");
	  dataMap.put("maxWaitTime", "10");
	  dataMap.put("raiseError", "0");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  
	  // Deposit a good fax while the problematic fax in retries mechanism
	  testFuncs.myDebugPrinting("Deposit a good fax while the problematic fax in retries mechanism", testVars.NORMAL);
	  dataMap.put("outputPath", testVars.getOutputDirPath() + "Test1.eml");
	  dataMap.put("maxWaitTime", "300");
	  dataMap.put("raiseError" , "1");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  
	  // Waits for the failure message from the first fax
	  testFuncs.myDebugPrinting("Waits for the failure message from the first fax", testVars.NORMAL);
	  
	  // Check for Error message
	  String emailsMainDir = testVars.getRootDir();	
	  String errorFile 	   = emailsMainDir  + "\\error\\"      + faxFailed + ".txt";
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
				  testFuncs.myDebugPrinting("Keep waiting for error file .. (" + counter + " minutes passed)", testVars.MINOR);
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