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
* This test tests the fax Coverpage in different formats tests
* -----------------
* Tests:
*    1. Deposit fax with jpg coverpage
*    2. Deposit fax with bmp coverpage
* 
* Results:
* 	In all cases:
*    1. All the three parts of fax - status, body and result should be accepted on given timeout.
* 	 2. All the attachments should arrive.
* 	 3. 2 pages should be at the coverpage
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test76 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;
  WebFuncs				webFuncs;

  // Default constructor for print the name of the used browser 
  public Test76(String browser) {
	  
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
  public void test1() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  String bodyText;
	  
	  // Step 1 - Deposit a fax with jpg coverage
	  testFuncs.myDebugPrinting("Step 1 - Deposit a fax with jpg coverage");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test76_1.txt");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  bodyText = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[1] + ".txt");
	  testFuncs.detectHeader(bodyText, "Fax contains:", "3 page(s)");
  }
  
  @Test
  public void test2() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  String bodyText;
	  
	  // Step 2 - Deposit a fax with bmp coverage
	  testFuncs.myDebugPrinting("Step 2 - Deposit a fax with bmp coverage");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test76_3.txt");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  bodyText = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[1] + ".txt");
	  testFuncs.detectHeader(bodyText, "Fax contains:", "3 page(s)");
  }

  @After
  public void tearDown() throws Exception {
	  
	  String verificationErrorString = verificationErrors.toString();
	  if (!"".equals(verificationErrorString)) {
		  
		fail("Error !!");
	  }
  }
}