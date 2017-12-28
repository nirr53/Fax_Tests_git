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
* This test tests the Fax-body pages number header
* -----------------
* Tests:
*    1. Send a fax with 3 pages
*    2. Send a fax with 5 pages
*    3. Send a fax with 26 pages
* 
* Results:
*    In all cases:
*    	1. All the three parts of fax - status, body and result should be accepted on given timeout.
* 	 	2. Page number should be displayed correctly.
* 
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test6 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;

  // Default constructor for print the name of the used browser 
  public Test6(String browser) {
	  
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
  }

  @Test
  public void Test6___Fax_pages_tests() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
		
	  // Step 1 - Deposit a fax with 3 pages
	  testFuncs.myDebugPrinting("Step 1 - Deposit a fax with 3 pages");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test6_1.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  String statusMsg = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[1] + ".txt");
	  testFuncs.myAssertTrue("Page number is not correct !!\nstatusMsg -" + statusMsg, statusMsg.contains("3 page(s)"));
  
	  // Step 2 - Deposit a fax with 5 pages
	  testFuncs.myDebugPrinting("Step 2 - Deposit a fax with 5 pages");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test6_2.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  statusMsg = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[1] + ".txt");
	  testFuncs.myAssertTrue("Page number is not correct !!\nstatusMsg -" + statusMsg, statusMsg.contains("5 page(s)"));

	  // Step 3 - Deposit a fax with 26 pages
	  testFuncs.myDebugPrinting("Step 3 - Deposit a fax with 26 pages");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test6_3.eml");
	  dataMap.put("maxWaitTime", "2000");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  statusMsg = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[1] + ".txt");
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