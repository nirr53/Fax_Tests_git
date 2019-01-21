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
* This test tests a deposit of Fax with very long sentences in his body
* -----------------
* Tests:
*    1. Deposit a fax with very long sentences in his body
* 
* Results:
*    1. All the three parts of fax - status, body and result should be accepted on given timeout.
* 	 2. All the headers should appear as they been defined.
* 	 3. The sentences should be parsed to logical sentences - if the start and end are seen then the paragraphs are well divided.
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test89 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;

  // Default constructor for print the name of the used browser 
  public Test89(String browser) {
	  
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
  public void tes0() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
		
	  // Step 1 - Deposit a fax with long sentences in  attachment that has multiple languages
	  testFuncs.myDebugPrinting("Step 1 - Deposit a fax with pdf attachment that has multiple languages");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test89.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap); 
	  
	  // Step 2 - Check fax
	  testFuncs.myDebugPrinting("Step 2 - Check fax");
	  testFuncs.activateFaxOCR(testVars.getOCRPath(), testVars.getRootDir(), "att_Fax_Message_Body");
	  String bodyMsg = testFuncs.readFile(testVars.getRootDir()  + "\\converted\\att_Fax_message_body.rtf");
	  testFuncs.myAssertTrue("Title was not detected !!", bodyMsg.contains("S- T- A- R- T") ||
			  											  bodyMsg.contains("S ~ T- A- R- T") ||
			  											  bodyMsg.contains("A- R-'T")); 
  }

  @After
  public void tearDown() throws Exception {
	  
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
    	
		fail("Error !!");
    }
  }
}