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
* This test tests the Fax ID Select Method 'From-Number-Settings'
* -----------------
* Tests:
* 	 - At Fax-In settings, set Fax-ID select method to be 'From-Number-Settings' 
*    1. Deposit a fax for a user that Not has Fax-ID
*    2. Deposit a fax for a user that has Fax-ID
* 
* Results:
*	1. All the three parts of fax - status, body and result should be accepted on given timeout.
*	   The Fax-ID header should be taken from LDAP.
*	2. All the three parts of fax - status, body and result should be accepted on given timeout.
*	   The Fax-ID header should be taken from the number-settings at the fax-Result txt.
*	   The Fax-ID header should be taken from the number-settings at the fax-Body pdf.
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test57 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;
  WebFuncs				webFuncs;

  // Default constructor for print the name of the used browser 
  public Test57(String browser) {
	  
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
	  String[] extraData = {"From Number Settings"};
	  webFuncs.setConfiguration(57, "FaxIn - Fax ID  - From Number Settings", extraData);
  }
  
  @Test
  public void test1() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  String bodyMsg;
	  
	  // Step 1 - Deposit a fax for a user that Not has Fax-ID
	  testFuncs.myDebugPrinting("Step 1 - Deposit a fax for a user that Not has Fax-ID");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test57_1.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  testFuncs.activateFaxOCR(testVars.getOCRPath(), testVars.getRootDir(), "att_Fax_Message_Body");
	  bodyMsg = testFuncs.readFile(testVars.getRootDir()  + "\\converted\\att_Fax_message_body.rtf");
	  testFuncs.myAssertTrue("Title was not detected !!", bodyMsg.contains("To: Nir Klieman Display") ||
			  											  bodyMsg.contains("To: Nit Klieman Display"));	  
  }
  
  @Test
  public void test2() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  String bodyMsg;
  
	  // Step 2 - Deposit a fax for a user that has Fax-ID
	  testFuncs.myDebugPrinting("Step 2 - Check the headers of body-part");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test57_2.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  bodyMsg = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[2] + ".txt");  
	  testFuncs.myAssertTrue("Header was not detected !!", bodyMsg.contains("FaxIn_FaxID"));	  
	  testFuncs.activateFaxOCR(testVars.getOCRPath(), testVars.getRootDir(), "att_Fax_Message_Body");
	  bodyMsg = testFuncs.readFile(testVars.getRootDir()  + "\\converted\\att_Fax_message_body.rtf");
	  testFuncs.myAssertTrue("Title was not detected !!", bodyMsg.contains("To: FaxIn_FaxID") ||
			  											  bodyMsg.contains("To: FaxIn FaxID") ||
			  											  bodyMsg.contains("To: FaxIn Fa{\\'d7}ID"));
  }

  @After
  public void tearDown() throws Exception {
	  
	  String verificationErrorString = verificationErrors.toString();
	  if (!"".equals(verificationErrorString)) {
		  
		fail("Error !!");
	  }
  }
}