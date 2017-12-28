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
* This test tests the Fax Result normal headers
* -----------------
* Tests:
*    1. Send a fax
* 
* Results:
*    1. All the three parts of fax - status, body and result should be accepted on given timeout.
*    2. All Fax result headers should be detected.
* 
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test10 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;

  // Default constructor for print the name of the used browser 
  public Test10(String browser) {
	  
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
  public void Test10___Fax_result_headers() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
		
	  // Step 1 - Deposit a fax with attachment that has a CR in its name
	  testFuncs.myDebugPrinting("Step 1 - Deposit a fax with attachment that has a CR in its name");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test10.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  String resultMsg = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[2] + ".txt");
	  
	  // Check headers
	  testFuncs.myAssertTrue("From header was not detected !!"   , resultMsg.contains("Mail To Fax Service <kairat.zimat@audiocodes.com")); 
	  testFuncs.myAssertTrue("To header was not detected !!"     , resultMsg.contains("Nir Klieman")); 
	  testFuncs.myAssertTrue("Subject header was not detected !!", resultMsg.contains(testVars.getFaxHeaders()[2])); 
	  testFuncs.myAssertTrue("Hello header was not detected !!"  , resultMsg.contains("Nir Klieman"));   
	  testFuncs.myAssertTrue("Subject header was not detected !!", resultMsg.contains("Test Fax Result normal headers")); 
  }

  @After
  public void tearDown() throws Exception {
	  
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
    	
		fail("Error !!");
    }
  }
}