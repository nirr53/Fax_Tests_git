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
* This test tests a send of a fax with signature in his body.
* -----------------
* Tests:
*      1. Send a fax with normal-signature in his body should be deposited successfully.
*      2. Send a fax with telephone-signature in his body should be deposited successfully.
*      3. Fax with url-signature in his body should be deposited successfully.
*      4. Fax with image-signature in his body should be deposited successfully.
*      5. Fax with complex signature (image, url and phone) in his body should be deposited successfully.
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
public class Test39 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;

  // Default constructor for print the name of the used browser 
  public Test39(String browser) {
	  
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
		
	  // Step 1 - Deposit fax with normal-signature in his body
	  testFuncs.myDebugPrinting("Step 1 - Deposit fax with normal-signature in his body");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test39_1.txt");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
  }
  
  @Test
  public void test2() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
  
	  // Step 2 - Deposit fax  with telephone-signature in his body
	  testFuncs.myDebugPrinting("Step 2 - Deposit fax  with telephone-signature in his body");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test39_2.txt");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
  }
  
  @Test
  public void test3() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
  
	  // Step 3 - Deposit fax  with url-signature in his body
	  testFuncs.myDebugPrinting("Step 3 - Deposit fax  with url-signature in his body");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test39_3.txt");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
  }
  
  @Test
  public void test4() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
  
	  // Step 4 - Deposit fax  with image-signature in his body
	  testFuncs.myDebugPrinting("Step 4 - Deposit fax  with image-signature in his body");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test39_4.txt");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
  }
  
  @Test
  public void test5() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  
	  // Step 5 - Deposit fax  with  complex signature (image, url and phone) in his body
	  testFuncs.myDebugPrinting("Step 5 - Deposit fax  with  complex signature (image, url and phone) in his body");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test39_5.txt");
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