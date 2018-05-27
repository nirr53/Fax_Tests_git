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
* This test tests the timezone
* -----------------
* Tests:
*    - Deposit a fax
*    1. Check the timezone in the fax different parts (status, body & result)
*    2. Check the timezone in the "Sent faxes" menu and verify that it matches the time in the coverpage
*    3. Check the timezone in the "Received faxes" menu and verify that it matches the time in the coverpage
* 
* Results:
*    1. Fax should be deposited
*	 2. Timezone should match the selected option
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test127 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;
  WebFuncs				webFuncs;

  // Default constructor for print the name of the used browser 
  public Test127(String browser) {
	  
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
  public void Test27___Timezone() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  String stsMsg, bodyMsg, resMsg;
	  
	  // Get date format
	  testFuncs.myDebugPrinting("Get date format", testVars.MINOR);  
	  SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	  Calendar calendar  = Calendar.getInstance(); 
	  Calendar calendar1 = Calendar.getInstance();
	  Calendar calendar2 = Calendar.getInstance();
	  Calendar calendar3 = Calendar.getInstance();	  
	  calendar1.add(Calendar.MINUTE, 1);
	  calendar2.add(Calendar.MINUTE, 2);
	  calendar3.add(Calendar.MINUTE, 3);
	  testFuncs.myDebugPrinting("calendar - "  + sdf.format(calendar.getTime()), testVars.MINOR);
	  testFuncs.myDebugPrinting("calendar1 - " + sdf.format(calendar1.getTime()), testVars.MINOR);
	  testFuncs.myDebugPrinting("calendar2 - " + sdf.format(calendar2.getTime()), testVars.MINOR);
	  testFuncs.myDebugPrinting("calendar3 - " + sdf.format(calendar3.getTime()), testVars.MINOR);
	  		
	  // Step 1 - Deposit a fax
	  testFuncs.myDebugPrinting("Step 1 - Deposit a fax");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test127.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap); 
	  
	  // Read the fax parts and search for the Time header
	  testFuncs.myDebugPrinting("Read the fax parts and search for the Time header", testVars.MINOR);
	  stsMsg  = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[0] + ".txt");  
	  bodyMsg = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[1] + ".txt");  
	  resMsg  = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[2] + ".txt");   
	  testFuncs.myAssertTrue("Time does not display at file !!", stsMsg.contains(sdf.format(calendar.getTime()))    ||
			  													 stsMsg.contains(sdf.format(calendar1.getTime()))   ||
			  													 stsMsg.contains(sdf.format(calendar2.getTime()))   ||
			  													 stsMsg.contains(sdf.format(calendar3.getTime())));   
	  testFuncs.myAssertTrue("Time does not display at file !!", bodyMsg.contains(sdf.format(calendar.getTime()))   ||
			  													 bodyMsg.contains(sdf.format(calendar1.getTime()))  ||
			  													 bodyMsg.contains(sdf.format(calendar2.getTime()))  ||
			  													 bodyMsg.contains(sdf.format(calendar3.getTime())));   
	  testFuncs.myAssertTrue("Time does not display at file !!", resMsg.contains(sdf.format(calendar.getTime()))    ||
			  													 resMsg.contains(sdf.format(calendar1.getTime()))   ||
			  													 resMsg.contains(sdf.format(calendar2.getTime()))   ||
			  													 resMsg.contains(sdf.format(calendar3.getTime())));
	  
	  // Search for the timezone at the send/received faxes menus
	  testFuncs.myDebugPrinting("Search for the timezone at the send/received faxes menus");
	  String[] extraData = {sdf.format(calendar.getTime()),
			  				sdf.format(calendar1.getTime()),
			  				sdf.format(calendar2.getTime()),
			  				sdf.format(calendar3.getTime())};
	  testFuncs.myDebugPrinting("extraData[0] - " + extraData[0], testVars.MINOR);
	  testFuncs.myDebugPrinting("extraData[1] - " + extraData[1], testVars.MINOR);
	  testFuncs.myDebugPrinting("extraData[2] - " + extraData[2], testVars.MINOR);
	  testFuncs.myDebugPrinting("extraData[3] - " + extraData[3], testVars.MINOR);
	  webFuncs.setConfiguration(127, "Timezone", extraData);
  }

  @After
  public void tearDown() throws Exception {
	  	  
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
    	
		fail("Error !!");
    }
  }
}