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
* This test tests the display of the Date format
* -----------------
* Tests:
*    1. Deposit an email when the date format is HH:mm dd/MM/yyyy
*    2. Deposit an email when the date format is HH:mm MM/dd/yyyy
* 
* Results:
*   In both cases:
*   	1. Fax should be deposited
*		2. Date format should match the selected option
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test125 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;
  WebFuncs				webFuncs;

  // Default constructor for print the name of the used browser 
  public Test125(String browser) {
	  
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
  public void Test25___Data_format() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  Map<String, String> dataMap = new HashMap<String, String>();
	  String bodyMsg;
	  
	  // Activate script with the needed configuration
	  testFuncs.myDebugPrinting("Activate script with the needed configuration");
	  String[] extraData = {"HH:mm dd/MM/yyyy"};
	  webFuncs.setConfiguration(125, "Data format - dd/MM/yyyy", extraData);
	  
	  // Get date format
	  testFuncs.myDebugPrinting("Get date format", enumsClass.logModes.MINOR);  
	  SimpleDateFormat sdf = new SimpleDateFormat(extraData[0]);
	  Calendar calendar  = Calendar.getInstance(); 
	  Calendar calendar1 = Calendar.getInstance();
	  Calendar calendar2 = Calendar.getInstance();
	  Calendar calendar3 = Calendar.getInstance();	  
	  calendar1.add(Calendar.MINUTE, 1);
	  calendar2.add(Calendar.MINUTE, 2);
	  calendar3.add(Calendar.MINUTE, 3);
	  String currDate  = sdf.format(calendar.getTime());
	  		
	  // Step 1 - Deposit a fax
	  testFuncs.myDebugPrinting("Step 1 - Deposit a fax");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test125_1.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  
	  // Check Data format at Body and result messages
	  testFuncs.myDebugPrinting("Check Data format at Body and result messages", enumsClass.logModes.MINOR);
	  bodyMsg = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[1] + ".txt");  
	  testFuncs.myAssertTrue("Date <" + currDate + "> was not detected !!", bodyMsg.contains(sdf.format(calendar.getTime()))   ||
			  																bodyMsg.contains(sdf.format(calendar1.getTime()))  ||
			  																bodyMsg.contains(sdf.format(calendar2.getTime()))  ||
			  																bodyMsg.contains(sdf.format(calendar3.getTime())));  
	  bodyMsg = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[2] + ".txt");  
	  testFuncs.myAssertTrue("Date <" + currDate + "> was not detected !!", bodyMsg.contains(sdf.format(calendar.getTime()))   ||
																			bodyMsg.contains(sdf.format(calendar1.getTime()))  ||
																			bodyMsg.contains(sdf.format(calendar2.getTime()))  ||
																			bodyMsg.contains(sdf.format(calendar3.getTime())));  
	  
	  // Activate script with the needed configuration
	  testFuncs.myDebugPrinting("Activate script with the needed configuration");
	  String[] extraData2 = {"HH:mm MM/dd/yyyy"};
	  webFuncs.setConfiguration(125, "Data format - HH:mm MM/dd/yyyy", extraData2);
	  
	  // Get date format
	  testFuncs.myDebugPrinting("Get date format", enumsClass.logModes.MINOR);  
	  SimpleDateFormat sdf2 = new SimpleDateFormat(extraData2[0]);
	  Calendar calendar0  = Calendar.getInstance(); 
	  Calendar calendar11 = Calendar.getInstance();
	  Calendar calendar21 = Calendar.getInstance();
	  Calendar calendar31 = Calendar.getInstance();	  
	  calendar11.add(Calendar.MINUTE, 1);
	  calendar21.add(Calendar.MINUTE, 2);
	  calendar31.add(Calendar.MINUTE, 3);
	  String currDate2  = sdf2.format(calendar0.getTime());
	  		
	  // Step 2 - Deposit a fax
	  testFuncs.myDebugPrinting("Step 2 - Deposit a fax");
	  dataMap.put("outputPath",  testVars.getOutputDirPath() + "Test125_2.eml");
	  testFuncs.depositFax(testVars.getFaxHeaders(), dataMap);
	  
	  // Check Data format at Body and result messages
	  testFuncs.myDebugPrinting("Check Data format at Body and result messages", enumsClass.logModes.MINOR);
	  bodyMsg = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[1] + ".txt");  
	  testFuncs.myAssertTrue("Date <" + currDate2 + "> was not detected !!", bodyMsg.contains(sdf2.format(calendar0.getTime()))  ||
			  																bodyMsg.contains(sdf2.format(calendar11.getTime()))  ||
			  																bodyMsg.contains(sdf2.format(calendar21.getTime()))  ||
			  																bodyMsg.contains(sdf2.format(calendar31.getTime())));  
	  bodyMsg = testFuncs.readFile(testVars.getRootDir()  + "\\input\\" + testVars.getFaxHeaders()[2] + ".txt");  
	  testFuncs.myAssertTrue("Date <" + currDate2 + "> was not detected !!", bodyMsg.contains(sdf2.format(calendar0.getTime()))  ||
																			bodyMsg.contains(sdf2.format(calendar11.getTime()))  ||
																			bodyMsg.contains(sdf2.format(calendar21.getTime()))  ||
																			bodyMsg.contains(sdf2.format(calendar31.getTime())));
  }

  @After
  public void tearDown() throws Exception {
	  	  
	// Restore old configuration  
	String[] extraData = {"HH:mm dd/MM/yyyy"};
	webFuncs.setConfiguration(125, "Data format - dd/MM/yyyy", extraData);
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
    	
		fail("Error !!");
    }
  }
}