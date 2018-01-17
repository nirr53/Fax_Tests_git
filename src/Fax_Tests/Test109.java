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
* This test tests a deposit faxes with attachment names in different languages
* -----------------
* Tests:
*    1. Deposit a fax with attachment name in Hebrew
*    2. Deposit a fax with attachment name in Russian (part 1)
*    3. Deposit a fax with attachment name in Russian (part 2)
*    4. Deposit a fax with attachment name in Russian (part 3)
*    5. Deposit a fax with attachment name in Arabic
*    6. Deposit a fax with attachment name in Spanish
* 
* Results:
* 	In all cases
*    1. All the three parts of fax - status, body and result should be accepted on given timeout.
* 	 2. All the headers should appear as they been defined.
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test109 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;

  // Default constructor for print the name of the used browser 
  public Test109(String browser) {
	  
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
  public void Test108___Fax_with_attachment_that_has_special_characters() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
		
	  // Step 1 - Deposit a fax with attachment name in Hebrew
	  testFuncs.myDebugPrinting("Step 1 - Deposit a fax with attachment name in Hebrew");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test109_1.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap); 
	  
	  // Step 2 - Deposit a fax with attachment name in Russian (part 1)
	  testFuncs.myDebugPrinting("Step 2 - Deposit a fax with attachment name in Russian (part 1)");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test109_2.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap); 
	  
	  // Step 3 - Deposit a fax with attachment name in Russian (part 2)
	  testFuncs.myDebugPrinting("Step 3 - Deposit a fax with attachment name in Russian (part 2)");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test109_3.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap); 
	  
	  // Step 4 - Deposit a fax with attachment name in Russian (part 1)
	  testFuncs.myDebugPrinting("Step 4 - Deposit a fax with attachment name in Russian (part 3)");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test109_4.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap); 
	  
	  // Step 5 - Deposit a fax with attachment name in Arabic
	  testFuncs.myDebugPrinting("Step 5 - Deposit a fax with attachment name in Arabic");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test109_5.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap); 
	  
	  // Step 6 - Deposit a fax with attachment name in Spanish
	  testFuncs.myDebugPrinting("Step 6 - Deposit a fax with attachment name in Spanish");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test109_6.eml");
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