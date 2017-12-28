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
* This test tests a send of a fax with xls attchment
* -----------------
* Tests:
*    1. Send a fax with one xls attachment
*    2. Send a fax with multiple xls attachments
*    3. Send a fax with one xlsx attachment
*    4. Send a fax with multiple xlsx attachments
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
public class Test24 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;

  // Default constructor for print the name of the used browser 
  public Test24(String browser) {
	  
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
  public void Test24___Fax_xls_attachment() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  dataMap.put("maxWaitTime",  "1500");
		
	  // Step 1 - Deposit fax with one xls attachment
	  testFuncs.myDebugPrinting("Step 1 -  Deposit fax with one xls attachment");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test24_1.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  testFuncs.activateFaxOCR(testVars.getOCRPath(), testVars.getRootDir(), "att_Fax_Message_Body");
	  String bodyMsg = testFuncs.readFile(testVars.getRootDir()  + "\\converted\\att_Fax_message_body.rtf");
	  testFuncs.myAssertTrue("Title was not detected !!", bodyMsg.contains("Start"));
	  testFuncs.myAssertTrue("Title was not detected !!", bodyMsg.contains("Here"));
  
	  // Step 2 - Deposit fax with multiple xls attachments
	  testFuncs.myDebugPrinting("Step 2 - Deposit fax with multiple xls attachments");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test24_2.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  testFuncs.activateFaxOCR(testVars.getOCRPath(), testVars.getRootDir(), "att_Fax_Message_Body");
	  bodyMsg = testFuncs.readFile(testVars.getRootDir()  + "\\converted\\att_Fax_message_body.rtf");
	  testFuncs.myAssertTrue("Title was not detected !!", bodyMsg.contains("Fax Contains: 5 page(s)"));
	  
	  // Step 3 - Deposit fax with one xlsx attachment
	  testFuncs.myDebugPrinting("Step 3 -  Deposit fax with one xlsx attachment");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test24_3.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  testFuncs.activateFaxOCR(testVars.getOCRPath(), testVars.getRootDir(), "att_Fax_Message_Body");
	  bodyMsg = testFuncs.readFile(testVars.getRootDir()  + "\\converted\\att_Fax_message_body.rtf");
	  testFuncs.myAssertTrue("Title was not detected !!", bodyMsg.contains("Start"));
	  testFuncs.myAssertTrue("Title was not detected !!", bodyMsg.contains("Here"));
  
	  // Step 4 - Deposit fax with multiple xlsx attachments
	  testFuncs.myDebugPrinting("Step 4 - Deposit fax with multiple xlsx attachments");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test24_4.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  testFuncs.activateFaxOCR(testVars.getOCRPath(), testVars.getRootDir(), "att_Fax_Message_Body");
	  bodyMsg = testFuncs.readFile(testVars.getRootDir()  + "\\converted\\att_Fax_message_body.rtf");
	  testFuncs.myAssertTrue("Title was not detected !!", bodyMsg.contains("Fax Contains: 5 page(s)"));
  }

  @After
  public void tearDown() throws Exception {
	  
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
    	
		fail("Error !!");
    }
  }
}