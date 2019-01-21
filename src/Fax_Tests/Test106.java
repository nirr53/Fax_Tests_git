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
* This test tests a deposit of different attachment types
* -----------------
* Tests:
*    1. Deposit a fax with attachment that has only numbers
*    2. Deposit a fax with very short attachment name.
*    3. Deposit a fax with attachment that has spaces   
* 
* Results:
* In all cases
*    1. All the three parts of fax - status, body and result should be accepted on given timeout.
* 	 2. All the headers should appear as they been defined.
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test106 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;

  // Default constructor for print the name of the used browser 
  public Test106(String browser) {
	  
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
  }

  @Test
  public void test1() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
		
	  // Step 1 - Deposit fax with attachment that has only numbers
	  testFuncs.myDebugPrinting("Step 1 - Deposit fax with attachment that has only numbers");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test106_1.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap); 
  }
  
  @Test
  public void test2() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  
	  // Step 2 - Deposit fax with very short attachment name
	  testFuncs.myDebugPrinting("Step 2 - Deposit fax with very short attachment name");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test106_2.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap); 
  }
  
  @Test
  public void test3() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  
	  // Step 3 - Deposit fax with attachment that has spaces
	  testFuncs.myDebugPrinting("Step 3 - Deposit fax with attachment that has spaces");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test106_3.eml");
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