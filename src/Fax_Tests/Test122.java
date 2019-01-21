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
* This test tests the display of Arabic characters on fax
* -----------------
* Tests:
*    1. Deposit a fax with Arabic letters
* 
* Results:
*    1. All the three parts of fax - status, body and result should be accepted on given timeout.
* 	 2. All the Arabic characters should be displayed.
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test122 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;
  WebFuncs				webFuncs;

  // Default constructor for print the name of the used browser 
  public Test122(String browser) {
	  
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
	  String[] arabicAlphabet = {"\'c7", "\'c8", "\'ca", "\'cb", "\'cc", "\'cd",
			  					 "\'ce", "\'cf", "\'d0", "\'d1", "\'d2", "\'d3",
			  					 "\'d4", "\'d5", "\'d6", "\'d8", "\'d9", "\'da",
			  					 "\'db", "\'dd", "\'de", "\'df", "\'e1", "\'e3",
			  					 "\'e4", "\'e5", "\'e6", "\'ed", "\'c1", "\'c2",
			  					 "\'3f", "\'c3", "\'c5", "\'c9", "\'c4", "\'c6",
			  					 "\'ec", "\'39", "\'38", "\'37", "\'36"};
	  int alphabetLength = arabicAlphabet.length;
		
	  // Step 1 - Deposit a fax with Arabic characters
	  testFuncs.myDebugPrinting("Step 1 - Deposit a fax with Arabic characters");
	  dataMap.put("outputPath"		 ,  testVars.getOutputDirPath() + "Test122.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
 	  
	  // Check characters at status message
	  testFuncs.myDebugPrinting("Check characters at status message");
	  String statusMsg = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[0] + ".txt");  
	  for (int i = 0; i < alphabetLength; ++i) {
		  
		  testFuncs.myDebugPrinting("Search for character <" + arabicAlphabet[i] + ">", enumsClass.logModes.MINOR);
		  testFuncs.myAssertTrue("The charcater <" + arabicAlphabet[i] + "> was not detected !!", statusMsg.contains(arabicAlphabet[i]));
	  }
	  
	  // Check characters at result message
	  testFuncs.myDebugPrinting("Check characters at result message");
	  String resMsg = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[2] + ".txt");  
	  for (int i = 0; i < alphabetLength; ++i) {
		  
		  testFuncs.myDebugPrinting("Search for character <" + arabicAlphabet[i] + ">", enumsClass.logModes.MINOR);
		  testFuncs.myAssertTrue("The charcater <" + arabicAlphabet[i] + "> was not detected !!", resMsg.contains(arabicAlphabet[i]));
	  } 
  }

  @After
  public void tearDown() throws Exception {
	  	  
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
    	
		fail("Error !!");
    }
  }
}