package Fax_Tests;

import static org.junit.Assert.fail;

import java.io.File;
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
* This test tests a Change of multiple Destinations subject Format
* -----------------
* Tests:
*    1. Deposit a fax for multiple valid targets
*    2. Deposit a fax for multiple invalid targets
* 
* Results:
* 	In all cases
* 	1. success.txt should appear at Input directory.
*	2. The header should be according to the definition.
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test112 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;
  WebFuncs				webFuncs;

  // Default constructor for print the name of the used browser 
  public Test112(String browser) {
	  
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
  public void Test112___Multi_Destinations_Format() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();

//	  // Activate script with the needed configuration
//	  testFuncs.myDebugPrinting("Activate script with the needed configuration");
//	  String[] extraData = {"FaxSubject Fax to multiple destinations results111"};
//	  webFuncs.setConfiguration(112, "Change multiple Destinations subject Format", extraData);  
	  
	  // Step 1 - Deposit a fax for multiple valid targets
	  testFuncs.myDebugPrinting("Step 1 - Deposit a fax for multiple valid targets");
	  dataMap.put("outputPath"		 ,  testVars.getOutputDirPath() + "Test113_1.eml");
	  dataMap.put("fileNumber"		 ,  "7");
	  dataMap.put("isMultipleTargets",  "1");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap); 

	  // Verify that success.txt is displayed
	  testFuncs.myDebugPrinting("Verify that success.txt is displayed", testVars.MINOR);  
	  File successFilename = new File(testVars.getRootDir()  + "\\input\\success.txt");
	  testFuncs.myAssertTrue("File <" + successFilename.getName() + "> is missing !", successFilename.exists() && !successFilename.isDirectory());
	   
//	  // Step 2 - Deposit a fax for multiple invalid targets
//	  testFuncs.myDebugPrinting("Step 2 - Deposit a fax for multiple invalid targets");
//	  dataMap.put("fileNumber"		 ,  "3");
//	  dataMap.put("isMultipleTargets",  "0");
//	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap); 
//	  
//	  // Verify that success.txt is displayed
//	  testFuncs.myDebugPrinting("Verify that success.txt is displayed", testVars.MINOR);  
//	  testFuncs.myAssertTrue("File <" + successFilename.getName() + "> is missing !", successFilename.exists() && !successFilename.isDirectory());	  
  }

  @After
  public void tearDown() throws Exception {
	  
//	String[] extraData = {"1"};
//	webFuncs.setConfiguration(111, "Add symbols checkbox", extraData);	  
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
    	
		fail("Error !!");
    }
  }
}