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
* This test tests cover-page attributes
* -----------------
* Tests:
*    1. Send a fax with normal subject and attributes
*    2. Send a fax with empty subject and attributes
*    3. Send a fax with very-long subject and attributes
* 
* Results:
*    1. All the three parts of fax - status, body and result should be accepted on given timeout.
* 	 2. All the headers should appear as they been defined.
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test9 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;
  WebFuncs				webFuncs;

  // Default constructor for print the name of the used browser 
  public Test9(String browser) {
	  
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
  public void Test9___Fax_cover_page_attributes() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  String bodyMsg;
	  
	  // Activate script with the needed configuration
	  testFuncs.myDebugPrinting("Activate script with the needed configuration");
	  String[] extraData = {"From Number Settings"};
	  webFuncs.setConfiguration(9, "FaxOut - Fax ID - from user settings", extraData);
		
	  // Step 1 - Deposit a fax with normal subject and attributes
	  testFuncs.myDebugPrinting("Step 1 -Deposit a fax with normal subject and attributes");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test9_1.txt");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  testFuncs.activateFaxOCR(testVars.getOCRPath(), testVars.getRootDir(), "att_Fax_Message_Body");
	  bodyMsg = testFuncs.readFile(testVars.getRootDir()  + "\\converted\\att_Fax_message_body.rtf");
	  testFuncs.myAssertTrue("Title was not detected !!", bodyMsg.contains("Subject'Test\\_9 I subject") || bodyMsg.contains("Subject:Test\\_9\\_1 subject"));
	  testFuncs.myAssertTrue("Title was not detected !!", bodyMsg.contains("From: \"Test 9t string\"")   || bodyMsg.contains("Test 91 string"));
	  testFuncs.myAssertTrue("Title was not detected !!", bodyMsg.contains("Fax to"));
	  
	  // Step 2 - Deposit a fax with empty subject and attributes
	  testFuncs.myDebugPrinting("Step 1 -Deposit a fax with empty subject and attributes");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test9_2.txt");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  testFuncs.activateFaxOCR(testVars.getOCRPath(), testVars.getRootDir(), "att_Fax_Message_Body");
	  bodyMsg = testFuncs.readFile(testVars.getRootDir()  + "\\converted\\att_Fax_message_body.rtf");
	  testFuncs.myAssertTrue("Title was not detected !!", bodyMsg.contains("Subject:\\par"));
	  testFuncs.myAssertTrue("Title was not detected !!", bodyMsg.contains("From:\\par"));

	  // Step 3 - Deposit a fax with very-long subject and attributes
	  testFuncs.myDebugPrinting("Step 3 - Deposit a fax with very-long subject and attributes");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test9_3.txt");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  testFuncs.activateFaxOCR(testVars.getOCRPath(), testVars.getRootDir(), "att_Fax_Message_Body");
	  bodyMsg = testFuncs.readFile(testVars.getRootDir()  + "\\converted\\att_Fax_message_body.rtf");
	  testFuncs.myAssertTrue("Title was not detected !!", bodyMsg.contains("To: 12345678901234567890"));
  }

  @After
  public void tearDown() throws Exception {
	  
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
    	
		fail("Error !!");
    }
  }
}