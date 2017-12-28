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
* This test tests a send of a fax with different subjects.
* -----------------
* Tests:
*      1. Send a fax with special chracters subject
*      2. Send a fax with empty subject
*      3. Send a fax with numbers subject
*      4. Send a fax with very long subject
* 
* Results:
* 	In all cases:
*    1. All the three parts of fax - status, body and result should be accepted on given timeout.
* 	 2. All the headers should appear as they been defined.
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test46 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;

  // Default constructor for print the name of the used browser 
  public Test46(String browser) {
	  
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
  public void Test46___Fax_with_different_subjects() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
		
	  // Step 1 - Send a fax with special charecters subject
	  testFuncs.myDebugPrinting("Step 1 - Send a fax with special charecters subject");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test46_1.txt");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  
	  // Step 2 - Send a fax with  with empty subject
	  testFuncs.myDebugPrinting("Step 2 - Send a fax with  with empty subject");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test46_2.txt");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  
	  // Step 3 - Send a fax with with numbers subject
	  testFuncs.myDebugPrinting("Step 3 - Send a fax with with numbers subject");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test46_3.txt");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  
	  // Step 4 - Send a fax with very long subject
	  testFuncs.myDebugPrinting("Step 4 - Send a fax with very long subject");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test46_4.txt");
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