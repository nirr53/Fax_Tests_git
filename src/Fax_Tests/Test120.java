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
* This test tests the display of Hebrew characters on fax
* -----------------
* Tests:
*    1. Deposit a fax
* 
* Results:
*    1. All the three parts of fax - status, body and result should be accepted on given timeout.
* 	 2. All the Hebrew characters should be displayed.
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test120 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;
  WebFuncs				webFuncs;

  // Default constructor for print the name of the used browser 
  public Test120(String browser) {
	  
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
  public void Test20___Hebrew_characters() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  String[] hebrewAlphabet = {"\'e0", "\'e1", "\'e2", "\'e3", "\'e4", "\'e5",
			  					 "\'e6", "\'e7", "\'e8", "\'e9", "\'eb", "\'ec",
			  					 "\'ee", "\'f0", "\'f1", "\'f2", "\'f4", "\'f6",
			  					 "\'f7", "\'f8", "\'f9", "\'fa", "\'f5", "\'f3",
			  					 "\'ef", "\'ed", "\'ea"};
	  int alphabetLength = hebrewAlphabet.length;
		
	  // Step 1 - Deposit a fax with Hebrew characters
	  testFuncs.myDebugPrinting("Step 1 - Deposit a fax with Hebrew characters");
	  dataMap.put("outputPath"		 ,  testVars.getOutputDirPath() + "Test120.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
 	  
	  // Check characters at status message
	  testFuncs.myDebugPrinting("Check characters at status message");
	  String statusMsg = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[0] + ".txt");  
	  for (int i = 0; i < alphabetLength; ++i) {
		  
		  testFuncs.myDebugPrinting("Search for character <" + hebrewAlphabet[i] + ">", enumsClass.logModes.MINOR);
		  testFuncs.myAssertTrue("The charcater <" + hebrewAlphabet[i] + "> was not detected !!", statusMsg.contains(hebrewAlphabet[i]));
	  }
	  
	  // Check characters at result message
	  testFuncs.myDebugPrinting("Check characters at result message");
	  String resMsg = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[2] + ".txt");  
	  for (int i = 0; i < alphabetLength; ++i) {
		  
		  testFuncs.myDebugPrinting("Search for character <" + hebrewAlphabet[i] + ">", enumsClass.logModes.MINOR);
		  testFuncs.myAssertTrue("The charcater <" + hebrewAlphabet[i] + "> was not detected !!", resMsg.contains(hebrewAlphabet[i]));
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