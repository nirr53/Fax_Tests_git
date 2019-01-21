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
* This test tests a send of an Email to fax with very long / big attachment
* -----------------
* Tests:
*    1. Deposit an Email to fax with very-big attachments (~10MB)
*    2. Deposit an Email to fax with very-long attachment (26 pages)
* 
* Results:
* 	In all cases:
*    1. All the three parts of fax - status, body and result should be accepted on given timeout.
* 	 2. All the attachments should arrive.
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test70 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;
  WebFuncs				webFuncs;

  // Default constructor for print the name of the used browser 
  public Test70(String browser) {
	  
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
	  dataMap.put("maxWaitTime", "2500");
	  
	  // Step 1 - Deposit an Email to fax with very-big attachments (~10MB)
	  testFuncs.myDebugPrinting("Step 1 - Deposit an Email to fax with very-big attachments (~10MB)");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test70_1.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  String statusMsg = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[1] + ".txt");
	  testFuncs.myAssertTrue("Page number is not correct !!\nstatusMsg -" + statusMsg, statusMsg.contains("6 page(s)"));
  }
  
  @Test
  public void test2() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  dataMap.put("maxWaitTime", "2500");
  
	  // Step 2 - Deposit an Email to fax with very-long attachment (26 pages)
	  testFuncs.myDebugPrinting("Step 2 - Deposit an Email to fax with very-long attachment (26 pages)");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test70_2.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  String statusMsg = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[1] + ".txt");
	  testFuncs.myAssertTrue("Page number is not correct !!\nstatusMsg -" + statusMsg, statusMsg.contains("26 page(s)"));
  }

  @After
  public void tearDown() throws Exception {
	  
	  String verificationErrorString = verificationErrors.toString();
	  if (!"".equals(verificationErrorString)) {
		  
		fail("Error !!");
	  }
  }
}