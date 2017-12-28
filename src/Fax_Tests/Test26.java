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
* This test tests a send of a fax with different office attachments
* -----------------
* Tests:
*    1. Send a fax with several office types (docx, xlsx, pptx)
*    2. Send a fax with several office-97 types (doc, xls, ppt)
* 
* Results:
* 	In all cases:
*    1. All the three parts of fax - status, body and result should be accepted on given timeout.
* 	 2. All the headers should appear as they been defined.
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test26 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;

  // Default constructor for print the name of the used browser 
  public Test26(String browser) {
	  
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
  }

  @Test
  public void Test26___Different_office_attachments() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  dataMap.put("maxWaitTime",  "1500");
		
	  // Step 1 - Deposit fax with office attachments (docx, xlsx, pptx)
	  testFuncs.myDebugPrinting("Step 1 - Deposit fax with office attachments (docx, xlsx, pptx)");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test26_1.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  testFuncs.activateFaxOCR(testVars.getOCRPath(), testVars.getRootDir(), "att_Fax_Message_Body");
	  String bodyMsg = testFuncs.readFile(testVars.getRootDir()  + "\\converted\\att_Fax_message_body.rtf");
	  testFuncs.myAssertTrue("Title was not detected !!", bodyMsg.contains("Start"));
	  testFuncs.myAssertTrue("Title was not detected !!", bodyMsg.contains("End"));
	  testFuncs.myAssertTrue("Title was not detected !!", bodyMsg.contains("here"));
  
	  // Step 2 - Deposit fax with office-97 attachments (doc, xls, ppt)
	  testFuncs.myDebugPrinting("Step 2 - Deposit fax with office-97 attachments (doc, xls, ppt)");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test26_2.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  testFuncs.activateFaxOCR(testVars.getOCRPath(), testVars.getRootDir(), "att_Fax_Message_Body");
	  bodyMsg = testFuncs.readFile(testVars.getRootDir()  + "\\converted\\att_Fax_message_body.rtf");
	  testFuncs.myAssertTrue("Title was not detected !!", bodyMsg.contains("Start"));
	  testFuncs.myAssertTrue("Title was not detected !!", bodyMsg.contains("End"));
	  testFuncs.myAssertTrue("Title was not detected !!", bodyMsg.contains("here"));	  
  }

  @After
  public void tearDown() throws Exception {
	  
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
    	
		fail("Error !!");
    }
  }
}