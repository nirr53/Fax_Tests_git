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
* This test tests a send of an Email to fax with special characters and office attachment
* -----------------
* Tests:
*    1. Send an Email to fax with special characters and xlsx attachment should be deposited successfully.
*    2. Send an Email to fax with special characters and xls  attachment should be deposited successfully.
*    3. Send an Email to fax with special characters and docx attachment should be deposited successfully.
*    4. Send an Email to fax with special characters and doc  attachment should be deposited successfully.
*    5. Send an Email to fax with special characters and pptx attachment should be deposited successfully.
*    6. Send an Email to fax with special characters and ppt  attachment should be deposited successfully.
*    7. Send an Email to fax with special characters and txt  attachment should be deposited successfully.
*    8. Send an Email to fax with special characters and rtf  attachment should be deposited successfully.
*    9. Send an Email to fax with special characters and pdf  attachment should be deposited successfully.
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
public class Test67 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;
  WebFuncs				webFuncs;

  // Default constructor for print the name of the used browser 
  public Test67(String browser) {
	  
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
	  
	  // Step 1 - Deposit an Email to fax with special characters and xlsx attachment
	  testFuncs.myDebugPrinting("Step 1 - Deposit an Email to fax with special characters and xlsx attachment");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test67_1.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
  }
  
  @Test
  public void test2() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
  
	  // Step 2 - Deposit an Email to fax with special characters and xls attachment
	  testFuncs.myDebugPrinting("Step 2 - Deposit an Email to fax with special characters and xls attachment");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test67_2.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
  }
  
  @Test
  public void test3() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  
	  // Step 3 - Deposit an Email to fax with special characters and docx attachment
	  testFuncs.myDebugPrinting("Step 3 - Deposit an Email to fax with special characters and docx attachment");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test67_3.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
  }
  
  @Test
  public void test4() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  
	  // Step 4 - Deposit an Email to fax with special characters and doc attachment
	  testFuncs.myDebugPrinting("Step 4 - Deposit an Email to fax with special characters and doc attachment");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test67_4.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
  }
  
  @Test
  public void test5() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  
	  // Step 5 - Deposit an Email to fax with special characters and pptx attachment
	  testFuncs.myDebugPrinting("Step 5 - Deposit an Email to fax with special characters and pptx attachment");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test67_5.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
  }
  
  @Test
  public void test6() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  
	  // Step 6 - Deposit an Email to fax with special characters and ppt attachment
	  testFuncs.myDebugPrinting("Step 6 - Deposit an Email to fax with special characters and ppt attachment");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test67_6.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
  }
  
  @Test
  public void test7() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  
	  // Step 7 - Deposit an Email to fax with special characters and txt attachment
	  testFuncs.myDebugPrinting("Step 7 - Deposit an Email to fax with special characters and txt attachment");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test67_7.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
  }
  
  @Test
  public void test8() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  
	  // Step 8 - Deposit an Email to fax with special characters and rtf attachment
	  testFuncs.myDebugPrinting("Step 8 - Deposit an Email to fax with special characters and rtf attachment");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test67_8.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
  }
  
  @Test
  public void test9() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  
	  // Step 9 - Deposit an Email to fax with special characters and pdf attachment
	  testFuncs.myDebugPrinting("Step 8 - Deposit an Email to fax with special characters and pdf attachment");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test67_9.eml");
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