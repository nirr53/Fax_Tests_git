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
* This test tests a CC & Bcc tests
* -----------------
* Tests:
*    1. Deposit a fax with 'To' and 'Cc' that have the same address
*    2. Deposit a fax with 'To' and 'Cc' that have different address
*    3. Deposit a fax with 'To' and 'Bcc' that have the same address
* 
* Results:
* 	In all cases
*    1. All the three parts of fax - status, body and result should be accepted on given timeout.
* 	 2. All the headers should appear as they been defined.
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test110 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;

  // Default constructor for print the name of the used browser 
  public Test110(String browser) {
	  
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
	  String inputPath = testVars.getRootDir()  + "\\input\\";
	  testFuncs.myDebugPrinting("inputPath - " + inputPath, enumsClass.logModes.MINOR);
	  int fileNum = -1;
		
	  // Step 1 - Deposit a fax with similar To and Cc address 
	  testFuncs.myDebugPrinting("Step 1 - Deposit a fax with similar To and Cc address");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test110_1.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap); 
	  testFuncs.myWait(10000);
	  fileNum = testFuncs.countFilesNumber(inputPath);
	  testFuncs.myAssertTrue("The fax was sent several times! <" + fileNum + ">" , fileNum == 5);
  }
  
  @Test
  public void test2() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  String inputPath = testVars.getRootDir()  + "\\input\\";
	  testFuncs.myDebugPrinting("inputPath - " + inputPath, enumsClass.logModes.MINOR);
	  	  
	  // Step 2 - Deposit a fax with different To and Cc address 
	  testFuncs.myDebugPrinting("Step 2 - Deposit a fax with different To and Cc address ");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test110_2.eml");
	  dataMap.put("fileNumber"		 ,  "7");
	  dataMap.put("isMultipleTargets",  "1");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap); 
	  
	  // Check headers
	  testFuncs.myDebugPrinting("Check headers");
	  String resMsg = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[2] + ".txt");  
	  testFuncs.detectHeader(resMsg, "0545599607", "Succeeded to send");
	  testFuncs.detectHeader(resMsg, "0545599608", "Succeeded to send"); 
	  String statusMsg = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[0] + ".txt");  
	  testFuncs.detectHeader(statusMsg, "To:", "0545599607, 0545599608");  
  }
  
  @Test
  public void test3() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  String inputPath = testVars.getRootDir()  + "\\input\\";
	  testFuncs.myDebugPrinting("inputPath - " + inputPath, enumsClass.logModes.MINOR);
	  int fileNum = -1;
	  
	  // Step 3 - Deposit a fax with similar To and Bcc address
	  testFuncs.myDebugPrinting("Step 3 - Deposit a fax with similar To and Bcc address");
	  dataMap.put("outputPath"		 ,  testVars.getOutputDirPath() + "Test110_3.eml");
	  dataMap.put("fileNumber"		 ,  "5");
	  dataMap.put("isMultipleTargets",  "0");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap); 
	  testFuncs.myWait(10000);
	  fileNum = testFuncs.countFilesNumber(inputPath);
	  testFuncs.myAssertTrue("The fax was sent several times! <" + fileNum + ">" , fileNum == 5);
  }

  @After
  public void tearDown() throws Exception {
	  
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
    	
		fail("Error !!");
    }
  }
}