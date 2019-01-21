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
* This test tests a send of a FaxFax coverpage when coverpage is not checked
* -----------------
* Tests:
* 	 - Uncheck the 'Add coverpage' checkbox 
*    1. Send a fax with coverpage attachment when coverpage is not checked
*    2. Send a fax without coverpage attachment when coverpage is not checked
* 	 - Check the 'Add coverpage' checkbox
* 
* Results:
*    1. All the three parts of fax - status, body and result should be accepted on given timeout.
* 	 	coverpage is DO displayed.
* 	 2. All the three parts of fax - status, body and result should be accepted on given timeout.
* 		Only the body is displayed.
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test78 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;
  WebFuncs				webFuncs;

  // Default constructor for print the name of the used browser 
  public Test78(String browser) {
	  
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
	  webFuncs.setConfiguration(78, "Email to fax tests when cover-page checkbox is NOT checked", extraData);
  }
  
  @Test
  public void test1() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  String bodyMsg;
  
	  // Step 1 - Deposit a fax with coverpage attachment when coverpage is not checked
	  testFuncs.myDebugPrinting("Step 1 - Deposit a fax with coverpage attachment when coverpage is not checked");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test78_1.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  bodyMsg = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[1] + ".txt");   
	  testFuncs.detectHeader(bodyMsg, "Fax contains:", "2 page(s)");
  }
  
  @Test
  public void test2() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  String bodyMsg;
	  
	  // Step 2 - Deposit a fax coverpage without coverpage attachment when coverpage is not checked
	  testFuncs.myDebugPrinting("Step 2 - Deposit a fax coverpage without coverpage attachment when coverpage is not checked");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test78_2.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  bodyMsg = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[1] + ".txt");   
	  testFuncs.detectHeader(bodyMsg, "Fax contains:", "1 page(s)");
  }
  
  @Test
  public void test3() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  
	  // Activate script with the needed configuration
	  testFuncs.myDebugPrinting("Activate script with the needed configuration");
	  String[] extraData = {"0"};	
	  webFuncs.setConfiguration(78, "Activate the cover-page again", extraData);  
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