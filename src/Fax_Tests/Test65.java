package Fax_Tests;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
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
* This test tests the Archive fax mechanism
* -----------------
* Tests:
*    1. Uncheck the Archive checkbox and send some faxes
*    2. Check the Archive checkbox and send some faxes
* 
* Results:
*    1. All the three parts of fax - status, body and result should be accepted on given timeout.
* 	 	The ability to save new faxes should not be available.
*    2. All the three parts of fax - status, body and result should be accepted on given timeout.
* 	 	The ability to save new faxes should be available.
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test65 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;
  WebFuncs				webFuncs;

  // Default constructor for print the name of the used browser 
  public Test65(String browser) {
	  
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
  public void Test64___Email_to_fax_with_macro_attachment() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  
//	  // Activate script with the needed configuration
//	  testFuncs.myDebugPrinting("Activate script with the needed configuration");
//	  String[] extraData = {"disabled"};
//	  webFuncs.setConfiguration(65, "Archive Fax mechanism - archive is disabled", extraData);
//	  
//	  // Get date format
//	  testFuncs.myDebugPrinting("Get date format", testVars.MINOR);  
//	  SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
//	  Calendar calendar  = Calendar.getInstance(); 
//	  Calendar calendar1 = Calendar.getInstance();
//	  Calendar calendar2 = Calendar.getInstance();
//	  Calendar calendar3 = Calendar.getInstance();	  
//	  calendar1.add(Calendar.MINUTE, 1);
//	  calendar2.add(Calendar.MINUTE, 2);
//	  calendar3.add(Calendar.MINUTE, 3);
//	  testFuncs.myDebugPrinting("calendar - "  + sdf.format(calendar.getTime()), testVars.MINOR);
//	  testFuncs.myDebugPrinting("calendar1 - " + sdf.format(calendar1.getTime()), testVars.MINOR);
//	  testFuncs.myDebugPrinting("calendar2 - " + sdf.format(calendar2.getTime()), testVars.MINOR);
//	  testFuncs.myDebugPrinting("calendar3 - " + sdf.format(calendar3.getTime()), testVars.MINOR);
	  
	  // Step 1 - Deposit fax and check archive
	  testFuncs.myDebugPrinting("Step 1 - Deposit fax and check archive");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test1.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
//	  String[] extraData2 = {"check_disabled"};
//	  webFuncs.setConfiguration(65, "Archive Fax mechanism - check that archive is disabled", extraData2);
	  
//	  // Activate script with the needed configuration
//	  testFuncs.myDebugPrinting("Activate script with the needed configuration");
//	  String[] extraData2 = {"1"};
//	  webFuncs.setConfiguration(65, "Archive Fax mechanism - archive is enabled", extraData2);
//	  
//	  // Step 2 - Deposit fax
//	  testFuncs.myDebugPrinting("Step 2 - Deposit fax");
//	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test1.eml");
//	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
  }

  @After
  public void tearDown() throws Exception {
	  
	  String verificationErrorString = verificationErrors.toString();
	  if (!"".equals(verificationErrorString)) {
		  
		fail("Error !!");
	  }
  }
}