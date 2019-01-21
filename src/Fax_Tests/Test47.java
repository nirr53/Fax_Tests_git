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
* This test tests a send of an email to fax in several modes
* -----------------
* Tests:
*    1. Send a fax with coded as Plain-text
*    2. Send a fax with coded as HTML-text
*    3. Send a fax with coded as HTML & Plain-text
* 
* Results:
* 	In all cases:
*    - All the three parts of fax - status, body and result should be accepted on given timeout.
* 	 - All the headers should appear as they been defined.
* 
*	1. The data should be taken from the plain part.
*	2. The data should be taken from the HTML part.
*	3. The data should be taken from the HTML part and NOT from the plain text part.
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test47 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;

  // Default constructor for print the name of the used browser 
  public Test47(String browser) {
	  
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
  }

  @Test
  public void test1() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
		
	  // Step 1 - Send a fax with coded as Plain-text
	  testFuncs.myDebugPrinting("Step 1 - Send a fax with coded as Plain-text");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test47_1.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  testFuncs.activateFaxOCR(testVars.getOCRPath(), testVars.getRootDir(), "att_Fax_Message_Body");
	  String bodyMsg = testFuncs.readFile(testVars.getRootDir()  + "\\converted\\att_Fax_message_body.rtf");
	  testFuncs.myAssertTrue("Title was not detected !!", bodyMsg.contains("Start"));
	  testFuncs.myAssertTrue("Title was not detected !!", bodyMsg.contains("End"));
  }
  
  @Test
  public void test2() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
  
  
	  // Step 2 - Send a fax with coded as HTML-text
	  testFuncs.myDebugPrinting("Step 2 - Send a fax with coded as HTML-text");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test47_2.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  testFuncs.activateFaxOCR(testVars.getOCRPath(), testVars.getRootDir(), "att_Fax_Message_Body");
	  String bodyMsg = testFuncs.readFile(testVars.getRootDir()  + "\\converted\\att_Fax_message_body.rtf");
	  testFuncs.myAssertTrue("Title was not detected !!", bodyMsg.contains("Start"));
	  testFuncs.myAssertTrue("Title was not detected !!", bodyMsg.contains("End"));
  }
  
  @Test
  public void test3() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  
	  // Step 3 - Send a fax with coded as HTML & Plain-text
	  testFuncs.myDebugPrinting("Step 3 - Send a fax with coded as HTML & Plain-text");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test47_3.txt");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  testFuncs.activateFaxOCR(testVars.getOCRPath(), testVars.getRootDir(), "att_Fax_Message_Body");
	  String bodyMsg = testFuncs.readFile(testVars.getRootDir()  + "\\converted\\att_Fax_message_body.rtf");
	  testFuncs.myAssertTrue("Title was not detected !!", bodyMsg.contains("Start") || bodyMsg.contains("Body1"));
	  testFuncs.myAssertTrue("Title was not detected !!", bodyMsg.contains("End")   || bodyMsg.contains("Body2"));
  }

  @After
  public void tearDown() throws Exception {
	  
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
    	
		fail("Error !!");
    }
  }
}