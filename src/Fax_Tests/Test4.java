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
* This test tests the fax-display-name header in Fax-status
* -----------------
* Tests:
*    1. Send a fax with very long Display-name header.
*    2. Send a fax without Display-name header.
* 
* Results:
*    In both cases:
*    	1. All the three parts of fax - status, body and result should be accepted on given timeout.
* 	 	2. Display-name should be detected as it sent.
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test4 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;

  // Default constructor for print the name of the used browser 
  public Test4(String browser) {
	  
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
  public void Test4___Fax_display_name_header_fax_status() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
		
	  // Step 1 - Deposit a fax with very long Display-name header
	  testFuncs.myDebugPrinting("Step 1 - Deposit a fax with very long Display-name header");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test4_1.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  String statusMsg = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[0] + ".txt");
	  testFuncs.myAssertTrue("Display-name header was not detedted !!\nstatusMsg -" + statusMsg, statusMsg.contains("Hello 12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"));
  
	  // Step 2 - Deposit a fax without Display-name header
	  testFuncs.myDebugPrinting("Step 2 - Deposit a fax without Display-name header");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test4_2.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  statusMsg = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[0] + ".txt");
	  testFuncs.myAssertTrue("Display-name header was not detedted !!\nstatusMsg -" + statusMsg, statusMsg.contains("Hello d"));
  }

  @After
  public void tearDown() throws Exception {
	  
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
    	
		fail("Error !!");
    }
  }
}