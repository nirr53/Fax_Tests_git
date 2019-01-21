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
* This test tests a send of fax with invalid attachment
* -----------------
* Tests:
*    1. Send a fax with invalid attachment that cannot be converted (I.e exe).
* 
* Results:
*    1. None of the three parts of fax - status, body and result should be accepted on given timeout.
*    2. A failure message should be detected.
* 
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test44 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;

  // Default constructor for print the name of the used browser 
  public Test44(String browser) {
	  
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
		
	  // Step 1 - Send a fax that the system cannot convert.
	  testFuncs.myDebugPrinting("Step 1 - Send a fax that the system cannot convert.");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test44.txt");
	  dataMap.put("raiseError",  "0");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
 	  
	  // Check headers
	  String errorMsg = testFuncs.readFile(testVars.getRootDir()  + "\\error\\" +  testVars.getFaxFailureHeader() + ".txt");
	  testFuncs.myAssertTrue("To header was not detected !!", errorMsg.contains("0545599607"));
  }

  @After
  public void tearDown() throws Exception {
	  
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
    	
		fail("Error !!");
    }
  }
}