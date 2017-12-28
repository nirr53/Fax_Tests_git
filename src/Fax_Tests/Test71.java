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
* This test tests a send of an Fax with odt attachment.
* -----------------
* Tests:
*    1. Deposit a Fax with one odt attachment.
*    2. Deposit a Fax with multiple odt attachments.
* 
* Results:
* 	In all cases:
*    1. All the three parts of fax - status, body and result should be accepted on given timeout.
* 	 2. All the attachments should arrive.
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test71 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;
  WebFuncs				webFuncs;

  // Default constructor for print the name of the used browser 
  public Test71(String browser) {
	  
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
    webFuncs  = new WebFuncs();
  }

  @Test
  public void Test71___Fax_with_odt_attachment() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  
	  // Step 1 - Deposit a Fax with one odt attachment
	  testFuncs.myDebugPrinting("Step 1 - Deposit a Fax with one odt attachment");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test71_1.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  
	  // Step 2 - Deposit a Fax with multiple odt attachments
	  testFuncs.myDebugPrinting("Step 2 - Deposit a Fax with multiple odt attachments");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test71_2.eml");
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