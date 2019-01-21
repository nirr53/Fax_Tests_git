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
* This test tests a send of a Fax with pdf attachments in different languages.
* -----------------
* Tests:
*    1. Send an fax with pdf attachment in Arabic.
*    2. Send an fax with pdf attachment in English
*    3. Send an fax with pdf attachment in Hebrew.
*    4. Send an fax with pdf attachment in Russian 
*    5. Send an fax with pdf attachment in Spanish
* 
* Results:
*    In all cases:
*    	1. All the three parts of fax - status, body and result should be accepted on given timeout.
* 	 	2. All fax headers should be detected.
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test60 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;
  WebFuncs				webFuncs;

  // Default constructor for print the name of the used browser 
  public Test60(String browser) {
	  
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
  public void test1() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  
	  // Step 1 - Deposit fax with pdf attachment in Arabic
	  testFuncs.myDebugPrinting("Step 1 - Deposit fax with pdf attachment in Arabic");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test60_1.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
  }
  
  @Test
  public void test2() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  
	  // Step 2 - Deposit fax with pdf attachment in English
	  testFuncs.myDebugPrinting("Step 2 - Deposit fax with pdf attachment in English");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test60_2.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
  }
  
  @Test
  public void test3() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
  
	  // Step 3 - Deposit fax with pdf attachment in Hebrew
	  testFuncs.myDebugPrinting("Step 3 - Deposit fax with pdf attachment in Hebrew");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test60_3.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
  }
  
  @Test
  public void test4() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  
	  // Step 4 - Deposit fax with pdf attachment in Russian
	  testFuncs.myDebugPrinting("Step 4 - Deposit fax with pdf attachment in Russian");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test60_4.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
  }
  
  @Test
  public void test5() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  
	  // Step 5 - Deposit fax with pdf attachment in Spanish
	  testFuncs.myDebugPrinting("Step 5 - Deposit fax with pdf attachment in Spanish");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test60_5.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
  }

  @After
  public void tearDown() throws Exception {
	  
	  String verificationErrorString = verificationErrors.toString();
	  if (!"".equals(verificationErrorString)) {
		  
		fail("Error !!");
	  }
  }
}