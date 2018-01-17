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
* This test tests a send of a fax with different attachment names.
* -----------------
* Tests:
*      1. Send a fax with attachment that has special characters in its name
*      2. Send a fax with attachment that has a long body
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
public class Test45 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;

  // Default constructor for print the name of the used browser 
  public Test45(String browser) {
	  
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
  public void Test45___Fax_with_different_attachment_names() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
		
	  // Step 1 - Send a fax with attachment that has special characters in its name
	  testFuncs.myDebugPrinting("Step 1 - Send a fax with attachment that has special characters in its name");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test45_1.txt");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  
	  // Step 2 - Send a fax with attachment that has a long body
	  testFuncs.myDebugPrinting("Step 2 - Send a fax with attachment that has a long body");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test45_2.txt");
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