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
* This test tests the display of Russian characters on fax
* -----------------
* Tests:
*    1. Deposit a fax with small Russian letters
*    2. Deposit a fax with big Russian letters
* 
* Results:
* 	In all cases:
*    1. All the three parts of fax - status, body and result should be accepted on given timeout.
* 	 2. All the Russian characters should be displayed.
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test121 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;
  WebFuncs				webFuncs;

  // Default constructor for print the name of the used browser 
  public Test121(String browser) {
	  
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
  public void Test21___Russian_characters() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  String[] russianAlphabet = {"\'a8", "\'da", "\'df", "\'d8", "\'c5", "\'d0",
			  					  "\'d2", "\'db", "\'d3", "\'c8", "\'ce", "\'cf",
			  					  "\'de", "\'d9", "\'dd", "\'c0", "\'d1", "\'c4",
			  					  "\'d4", "\'c3", "\'d7", "\'c9", "\'ca", "\'cb",
			  					  "\'dc", "\'c6", "\'c7", "\'d5", "\'d6", "\'c2",
			  					  "\'c1", "\'cd", "\'cc"};
	  int alphabetLength = russianAlphabet.length;
		
	  // Step 1 - Deposit a fax with small Russian characters
	  testFuncs.myDebugPrinting("Step 1 - Deposit a fax with small Russian characters");
	  dataMap.put("outputPath"		 ,  testVars.getOutputDirPath() + "Test121_1.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
 	  
	  // Check characters at status message
	  testFuncs.myDebugPrinting("Check characters at status message");
	  String statusMsg = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[0] + ".txt");  
	  for (int i = 0; i < alphabetLength; ++i) {
		  
		  testFuncs.myDebugPrinting("Search for character <" + russianAlphabet[i] + ">", enumsClass.logModes.MINOR);
		  testFuncs.myAssertTrue("The charcater <" + russianAlphabet[i] + "> was not detected !!", statusMsg.contains(russianAlphabet[i]));
	  }
	  
	  // Check characters at result message
	  testFuncs.myDebugPrinting("Check characters at result message");
	  String resMsg = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[2] + ".txt");  
	  for (int i = 0; i < alphabetLength; ++i) {
		  
		  testFuncs.myDebugPrinting("Search for character <" + russianAlphabet[i] + ">", enumsClass.logModes.MINOR);
		  testFuncs.myAssertTrue("The charcater <" + russianAlphabet[i] + "> was not detected !!", resMsg.contains(russianAlphabet[i]));
	  }  
	  
	  String[] russianAlphabet2 = {"\'b8", "\'fa", "\'ff", "\'f8", "\'e5", "\'f0",
			  					   "\'f2", "\'fb", "\'f3", "\'e8", "\'ee", "\'ef",
			  					   "\'fe", "\'f9", "\'fd", "\'e0", "\'f1", "\'e4",
			  					   "\'f4", "\'e3", "\'f7", "\'e9", "\'ea", "\'eb",
			  					   "\'fc", "\'e6", "\'e7", "\'f5", "\'f6", "\'e2",
			  					   "\'e1", "\'ed", "\'ec"};
	  alphabetLength = russianAlphabet2.length;
	  
	  // Step 2 - Deposit a fax with big Russian characters
	  testFuncs.myDebugPrinting("Step 2 - Deposit a fax with big Russian characters");
	  dataMap.put("outputPath"		 ,  testVars.getOutputDirPath() + "Test121_2.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
 	  
	  // Check characters at status message
	  testFuncs.myDebugPrinting("Check characters at status message");
	  statusMsg = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[0] + ".txt");  
	  for (int i = 0; i < alphabetLength; ++i) {
		  
		  testFuncs.myDebugPrinting("Search for character <" + russianAlphabet2[i] + ">", enumsClass.logModes.MINOR);
		  testFuncs.myAssertTrue("The charcater <" + russianAlphabet2[i] + "> was not detected !!", statusMsg.contains(russianAlphabet2[i]));
	  }
	  
	  // Check characters at result message
	  testFuncs.myDebugPrinting("Check characters at result message");
	  resMsg = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[2] + ".txt");  
	  for (int i = 0; i < alphabetLength; ++i) {
		  
		  testFuncs.myDebugPrinting("Search for character <" + russianAlphabet2[i] + ">", enumsClass.logModes.MINOR);
		  testFuncs.myAssertTrue("The charcater <" + russianAlphabet2[i] + "> was not detected !!", resMsg.contains(russianAlphabet2[i]));
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