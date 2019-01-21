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
* This test tests the Add symbols checkbox
* -----------------
* Tests:
*    1. Deposit a fax When the 'Add symbols' checkbox is checked
*    2. Deposit a fax When the 'Add symbols' checkbox is unchecked
* 
* Results:
* 	1. All the three parts of fax - status, body and result should be accepted on given timeout.
*	   Symbols should be added to the email headers.
* 	2. All the three parts of fax - status, body and result should be accepted on given timeout.
*	   Symbols should NOT be added to the email headers.
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test111 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;
  WebFuncs				webFuncs;

  // Default constructor for print the name of the used browser 
  public Test111(String browser) {
	  
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
	  String[] extraData = {"1"};
	  webFuncs.setConfiguration(111, "Add symbols checkbox", extraData);	
  }
  
  @Test
  public void test1() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	   
	  // Step 1 - Deposit a fax
	  testFuncs.myDebugPrinting("Step 1 - Deposit a fax");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test111.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap); 
  }
  
  @Test
  public void test2() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
  
	  // Activate script with the needed configuration
	  testFuncs.myDebugPrinting("Activate script with the needed configuration");
	  String[] extraData2 = {"0"};
	  webFuncs.setConfiguration(111, "Add symbols checkbox", extraData2);
  }
  
  @Test
  public void test3() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
   
	  // Step 2 - Deposit a fax
	  testFuncs.myDebugPrinting("Step 2 - Deposit a fax");
	  dataMap.put("maxWaitTime",  "700");
	  dataMap.put("raiseError" ,  "0");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap); 
	  
	  // Set file names
	  String dirPrefix 	  = testVars.getRootDir()  + "\\input\\";	
	  File fileName1 = new File(dirPrefix + "withoutIcon_Send_Fax_Status.txt");
	  File fileName2 = new File(dirPrefix + "withoutIcon_Fax_Message_Body.txt");
	  File fileName3 = new File(dirPrefix + "att_withoutIcon_Fax_Message_Body.pdf");
	  File fileName4 = new File(dirPrefix + "withoutIcon_Send_Fax_Result.txt");
	  File fileName5 = new File(dirPrefix + "att_withoutIcon_Send_Fax_Result.pdf");
	  testFuncs.myAssertTrue("File <" + fileName1.getName() + "> is missing !", fileName1.exists() && !fileName1.isDirectory());
	  testFuncs.myAssertTrue("File <" + fileName2.getName() + "> is missing !", fileName2.exists() && !fileName2.isDirectory());
	  testFuncs.myAssertTrue("File <" + fileName3.getName() + "> is missing !", fileName3.exists() && !fileName3.isDirectory());
	  testFuncs.myAssertTrue("File <" + fileName4.getName() + "> is missing !", fileName4.exists() && !fileName4.isDirectory());
	  testFuncs.myAssertTrue("File <" + fileName5.getName() + "> is missing !", fileName5.exists() && !fileName5.isDirectory());
  }
  
  @Test
  public void test4() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());

	  // Activate script with the needed configuration
	  testFuncs.myDebugPrinting("Activate script with the needed configuration");
	  String[] extraData = {"1"};
	  webFuncs.setConfiguration(111, "Add symbols checkbox", extraData);	 	
  }

  @After
  public void tearDown() throws Exception {
	  
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
    	
		fail("Error !!");
    }
  }
}