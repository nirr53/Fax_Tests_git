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
* This test tests a deposit of Different Fax-In numbers
* -----------------
* Tests:
*    1. Send a fax to Fax-In user with 1800 prefix.
*    2. Send a fax to Fax-In user with international prefix.
*    3. send a fax to Fax-In user with 000 prefix.
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
public class Test79 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;

  // Default constructor for print the name of the used browser 
  public Test79(String browser) {
	  
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
		
	  // Step 1 - Send a fax to Fax-In user with 1800 prefix
	  testFuncs.myDebugPrinting("Step 1 - Send a fax to Fax-In user with 1800 prefix");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test79_1.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
  }
  
  @Test
  public void test2() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  
	  // Step 2 -  Send a fax to Fax-In user with international prefix
	  testFuncs.myDebugPrinting("Step 2 -  Send a fax to Fax-In user with international prefix");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test79_2.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
  }
  
  @Test
  public void test3() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  
	  // Step 3 -  Send a fax to Fax-In user with 000 prefix.
	  testFuncs.myDebugPrinting("Step 3 -  Send a fax to Fax-In user with 000 prefix.");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test79_3.eml");
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