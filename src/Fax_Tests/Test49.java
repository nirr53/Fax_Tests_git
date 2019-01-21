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
* This test tests a send of an email to fax when cover-page checkbox is NOT checked
* -----------------
* Tests:
* 	 - Uncheck the 'Add coverpage' checkbox 
*    1. Send a fax with no body and no attachment
*    2. Send a fax with no body and attachment
*    3. Send a fax with body and attachment
*    - Check the coverpage checkbox
* 
* Results:
*	1. Email to fax without body or attachment should NOT be deposited successfully.
*	   Failed message should be received.
*	2. Email to fax without body but with attachment should be deposited successfully.
*	   Only the attachment should reach the target (1 page).
*	3. Email to fax with body & attachment should be deposited successfully.
*	   Only the body & the attachment should reach the target (2 pages).
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test49 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;
  WebFuncs				webFuncs;

  // Default constructor for print the name of the used browser 
  public Test49(String browser) {
	  
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
	  webFuncs.setConfiguration(49, "Email to fax tests when cover-page checkbox is NOT checked", extraData);
  }
  
  @Test
  public void test1() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
  
	  // Step 1 - Send a fax with no body and no attachment
	  testFuncs.myDebugPrinting("Step 1 - Send a fax with no body and no attachment");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test49_1.txt");
	  dataMap.put("raiseError",  "0");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  String errorMsg = testFuncs.readFile(testVars.getRootDir()  + "\\error\\" +  testVars.getFaxFailureHeader() + ".txt");
	  testFuncs.myAssertTrue("To header was not detected !!", errorMsg.contains("0545599607"));
  }
  
  @Test
  public void test2() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  
	  // Step 2 - Send a fax with no body and attachment
	  testFuncs.myDebugPrinting("Step 2 - Send a fax with no body and attachment");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test49_2.txt");
	  dataMap.put("raiseError",  "1");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  String bodyMsg = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[1] + ".txt");  
	  testFuncs.myAssertTrue("Title was not detected !!", bodyMsg.contains("1 page(s)"));
  }
  
  @Test
  public void test3() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  
	  // Step 3 - Send a fax with body and attachment
	  testFuncs.myDebugPrinting("Step 3 - Send a fax with body and attachment");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test49_3.txt");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  String bodyMsg = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[1] + ".txt");  
	  testFuncs.myAssertTrue("Title was not detected !!", bodyMsg.contains("2 page(s)"));
  }
  
  @Test
  public void test4() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  
	  // Activate script with the needed configuration
	  testFuncs.myDebugPrinting("Activate script with the needed configuration");
	  String[] extraData = {"0"};
	  webFuncs.setConfiguration(49, "Activate the cover-page again", extraData);
  }

  @After
  public void tearDown() throws Exception {
	  
	  testFuncs.myDebugPrinting("Activate the cover-page again");
	  String verificationErrorString = verificationErrors.toString();
	  if (!"".equals(verificationErrorString)) {
		  
		fail("Error !!");
	  }
  }
}