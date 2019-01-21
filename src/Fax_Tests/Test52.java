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
* This test tests a send of an Russian email with different attachments
* -----------------
* Tests:
*    1. Send an Russian fax with xlsx attachment
*    2. Send an Russian fax with xls  attachment
*    3. Send an Russian fax with docx attachment
*    4. Send an Russian fax with doc  attachment
*    5. Send an Russian fax with pptx attachment
*    6. Send an Russian fax with ppt  attachment
*    7. Send an Russian fax with txt  attachment
*    8. Send an Russian fax with rtf  attachment
*    9. Send an Russian fax with pdf  attachment
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
public class Test52 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;
  WebFuncs				webFuncs;

  // Default constructor for print the name of the used browser 
  public Test52(String browser) {
	  
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
	  
	  // Step 1 - Send an Russian fax with xlsx attachment
	  testFuncs.myDebugPrinting("Step 1 - Send an Russian fax with xlsx attachment");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test52_1.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
  }
  
  @Test
  public void test2() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  
	  // Step 2 - Send an Russian fax with xls attachment
	  testFuncs.myDebugPrinting("Step 2 - Send an Russian fax with xls attachment");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test52_2.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
  }
  
  @Test
  public void test3() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  
	  // Step 3 - Send an Russian fax with docx attachment
	  testFuncs.myDebugPrinting("Step 3 - Send an Russian fax with docx attachment");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test52_3.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
  }
  
  @Test
  public void test4() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  
	  // Step 4 - Send an Russian fax with doc attachment
	  testFuncs.myDebugPrinting("Step 4 - Send an Russian fax with doc attachment");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test52_4.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
  }
  
  @Test
  public void test5() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  
	  // Step 5 - Send an Russian fax with pptx attachment
	  testFuncs.myDebugPrinting("Step 5 - Send an Russian fax with pptx attachment");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test52_5.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);  
  }
  
  @Test
  public void test6() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  
	  // Step 6 - Send an Russian fax with ppt attachment
	  testFuncs.myDebugPrinting("Step 6 - Send an Russian fax with ppt attachment");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test52_6.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
  }
  
  @Test
  public void test7() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  
	  // Step 7 - Send an Russian fax with rtf attachment
	  testFuncs.myDebugPrinting("Step 7 - Send an Russian fax with rtf attachment");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test52_7.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
  }
  
  @Test
  public void test8() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  
	  // Step 8 - Send an Russian fax with txt attachment
	  testFuncs.myDebugPrinting("Step 8 - Send an Russian fax with txt attachment");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test52_8.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
  }
  
  @Test
  public void test9() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  
	  // Step 9 - Send an Russian fax with pdf attachment
	  testFuncs.myDebugPrinting("Step 9 - Send an Russian fax with pdf attachment");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test52_9.eml");
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