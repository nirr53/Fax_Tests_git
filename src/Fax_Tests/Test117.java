package Fax_Tests;

import static org.junit.Assert.fail;
import java.util.Arrays;
import java.util.Collection;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import Fax_Tests.GlobalFuncs;

/**
* ----------------
* This test tests the Logs of the System Watchdog and Fax Server Services
* -----------------
* Tests:
*    - Login to the web-Service
*    - For each service:
*    	1. Check the display options
*    	2. Change the log-levels
*       3. Download the current log
*       4. Check archive logs (if exists)   
*    
* Results:
*    For each service:
*    	1. All display options should work
*    	2. Every log-level should be changed.
*    	3. The log should be downloaded.
*    	4. The archive menu should be opened. Its logs should be able to be downloaded
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test117 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;
  WebFuncs				webFuncs;

  // Default constructor for print the name of the used browser 
  public Test117(String browser) {
	  
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
  public void Test117___Services_logs() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  
	  // Activate script with the needed configuration
	  testFuncs.myDebugPrinting("Login the web admin");
	  String[] extraData = {"System Watchdog", "Fax Server"};  

	  // Loop on all the services
	  for (String service : extraData) {
		  
		  testFuncs.myDebugPrinting("The current tested service - " + service, testVars.MAJOR);
		  String[] neededData = {service};
		  webFuncs.setConfiguration(117, "Services Logs", neededData);
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