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
* This test tests the Attachment name problem when attachment-name is %PH_from%_%PH_to%
* -----------------
* Tests:
*    1. Deposit an email with pdf attachment to user X when the attachment-name is %PH_from%_%PH_to%
*    2. Deposit an email with pdf attachment to the same user when the attachment-name is %PH_from%_%PH_to%
*    3. Deposit an email with pdf attachment to the same user when the attachment-name is %PH_from%_%PH_to%
* 
* Results:
*   In all cases:
*   	1. Fax should be deposited
*		2. Date format should match the selected option
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test126 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;
  WebFuncs				webFuncs;

  // Default constructor for print the name of the used browser 
  public Test126(String browser) {
	  
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
	  String[] extraData = {"From Number Settings", "From Number Settings"};
	  webFuncs.setConfiguration(126, "Attachment name", extraData);
  }
  
  @Test
  public void test1() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  String bodyMsg;
	  		
	  // Step 1 - Deposit a fax
	  testFuncs.myDebugPrinting("Step 1 - Deposit a fax");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test126_1.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap); 
	  
	  // Check headers via Fax OCR converter
	  testFuncs.myDebugPrinting("Check headers via Fax OCR converter", enumsClass.logModes.MINOR);
	  testFuncs.activateFaxOCR(testVars.getOCRPath(), testVars.getRootDir(), "att_Fax_Message_Body");
	  bodyMsg = testFuncs.readFile(testVars.getRootDir()  + "\\converted\\att_Fax_message_body.rtf");
	  testFuncs.myAssertTrue("Title was not detected !! <" + bodyMsg + ">", bodyMsg.contains("Start"));
  }
  
  @Test
  public void test2() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  String bodyMsg;
  
	  // Step 2 - Deposit a fax
	  testFuncs.myDebugPrinting("Step 2 - Deposit a fax");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test126_2.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap); 
	  
	  // Check headers via Fax OCR converter
	  testFuncs.myDebugPrinting("Check headers via Fax OCR converter", enumsClass.logModes.MINOR);
	  testFuncs.activateFaxOCR(testVars.getOCRPath(), testVars.getRootDir(), "att_Fax_Message_Body");
	  bodyMsg = testFuncs.readFile(testVars.getRootDir()  + "\\converted\\att_Fax_message_body.rtf");
	  testFuncs.myAssertTrue("Title was not detected !! <" + bodyMsg + ">", bodyMsg.contains("Body"));
  }
  
  @Test
  public void test3() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  String bodyMsg;
   
	  // Step 3 - Deposit a fax
	  testFuncs.myDebugPrinting("Step 3 - Deposit a fax");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test126_3.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap); 
	  
	  // Check headers via Fax OCR converter
	  testFuncs.myDebugPrinting("Check headers via Fax OCR converter", enumsClass.logModes.MINOR);
	  testFuncs.activateFaxOCR(testVars.getOCRPath(), testVars.getRootDir(), "att_Fax_Message_Body");
	  bodyMsg = testFuncs.readFile(testVars.getRootDir()  + "\\converted\\att_Fax_message_body.rtf");
	  testFuncs.myAssertTrue("Title was not detected !! <" + bodyMsg + ">", bodyMsg.contains("End"));
  }

  @After
  public void tearDown() throws Exception {
	  	  
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
    	
		fail("Error !!");
    }
  }
}