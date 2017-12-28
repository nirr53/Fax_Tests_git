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
* This test tests the Fax ID Select Method 'System-Fax-ID'
* -----------------
* Tests:
* 	 - At Fax-In settings, set Fax-ID select method to be 'System-Fax-ID' 
*    1. Send a fax
* 
* Results:
*	1. All the three parts of fax - status, body and result should be accepted on given timeout.
*	2. The Fax-ID header should be taken from user settings.
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test56 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;
  WebFuncs				webFuncs;

  // Default constructor for print the name of the used browser 
  public Test56(String browser) {
	  
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
  public void Test56___FaxId_from_System_Fax_ID() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  String FaxId = "Audiocodes Fax ID33";
	  
	  // Activate script with the needed configuration
	  testFuncs.myDebugPrinting("Activate script with the needed configuration");
	  String[] extraData = {"System Fax ID", FaxId};
	  webFuncs.setConfiguration(56, "FaxIn - Fax ID  - System Fax ID", extraData);	  
	  
	  // Step 1 - Send a fax
	  testFuncs.myDebugPrinting("Step 1 - Send a fax");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test56.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  
	  // Step 2 - Check the headers of body-part
	  testFuncs.myDebugPrinting("Step 2 - Check the headers of body-part");
	  testFuncs.activateFaxOCR(testVars.getOCRPath(), testVars.getRootDir(), "att_Fax_Message_Body");
	  String bodyMsg = testFuncs.readFile(testVars.getRootDir()  + "\\converted\\att_Fax_message_body.rtf");
	  testFuncs.myAssertTrue("Title was not detected !!", bodyMsg.contains(FaxId));
  }

  @After
  public void tearDown() throws Exception {
	  
	  String verificationErrorString = verificationErrors.toString();
	  if (!"".equals(verificationErrorString)) {
		  
		fail("Error !!");
	  }
  }
}