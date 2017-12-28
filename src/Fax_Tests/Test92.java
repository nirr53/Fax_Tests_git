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
* This test tests Attachment-name with place-holders
* -----------------
* Tests:
* 	 - Change the Attachmant-name field 
*    1. Deposit a fax
* 
* Results:
*    1. All the three parts of fax - status, body and result should be accepted on given timeout.
*    2. The attachment name as it defined.
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test92 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;
  WebFuncs				webFuncs;

  // Default constructor for print the name of the used browser 
  public Test92(String browser) {
	  
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
  public void Test92___Attachment_name() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  String attname 	 = "attName_%PH_from%_%PH_to%";
	  String searchedAtt = "attName_039764000_0545599607";
	  
	  // Activate script with the needed configuration
	  testFuncs.myDebugPrinting("Activate script with the needed configuration");
	  String[] extraData = {attname};
	  webFuncs.setConfiguration(92, "General Settings - Attachment name", extraData);
		
	  // Step 1 - Deposit a fax
	  testFuncs.myDebugPrinting("Step 1 - Deposit a fax");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test92.eml");
	  dataMap.put("fileNumber", "3"); 
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap); 
	  testFuncs.myWait(20000);
	  
	  // Step 2 - Check fax
	  testFuncs.myDebugPrinting("Step 2 - Check fax");
	  String bodyPath = testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[1] + "_" + searchedAtt + ".pdf";
	  File f = new File(bodyPath);
	  if(f.exists() && !f.isDirectory()) { 
		  
		  testFuncs.myDebugPrinting(bodyPath + " was detected !!", testVars.MINOR);
		  testFuncs.myAssertTrue("Delete file failed !!",  f.delete());
	  } else {
		  
		  testFuncs.myFail(bodyPath + " was not detected !!");
	  }
	  String resultPath = testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[2] + "_" + searchedAtt + ".pdf";
	  File f2 = new File(resultPath);
	  if(f2.exists() && !f2.isDirectory()) { 
		  
		  testFuncs.myDebugPrinting(resultPath + " was detected !!", testVars.MINOR);
		  testFuncs.myAssertTrue("Delete file failed !!",  f2.delete());
	  } else {
		  
		  testFuncs.myFail(resultPath + " was not detected !!");
	  }  
  }

  @After
  public void tearDown() throws Exception {
	  
	String[] extraData = {"%PH_from%_%PH_to%"};
	webFuncs.setConfiguration(92, "General Settings - Attachment name", extraData);
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
    	
		fail("Error !!");
    }
  }
}