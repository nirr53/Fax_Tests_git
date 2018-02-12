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
* This test tests the user manuals of the system
* -----------------
* Tests:
*    - Login to the web-Service
*    1. Enter the user manuals menu and check its headers
*    2. Verify that all the manuals are downloaded successfully.
* 
* Results:
*    1. Menu should be displayed properly
*    2. All the menus should be downloaded successfully.
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test119 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;
  WebFuncs				webFuncs;

  // Default constructor for print the name of the used browser 
  public Test119(String browser) {
	  
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
  public void Test119___User_manualls_menu() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  
	  // Activate script with the needed configuration
	  testFuncs.myDebugPrinting("Login the web admin");
	  String[] extraData = {"/UserManuals/AudioCodes IVR Interdigit example guide.pdf",
			  				"/UserManuals/AudioCodes IVR and ACD for Lync example guide ver1.pdf",
			  				"/UserManuals/LTRT-28862 Fax Server and Auto Attendant Administrator's Guide v2.0.pdf",
			  				"/UserManuals/LTRT-28871 Fax Server and Auto Attendant IVR Installation Guide Ver. 1.2.pdf",
			  				"/UserManuals/License Installation Procedure Fax AA.pdf"};
	  webFuncs.setConfiguration(119, "User manuals", extraData);
  }

  @After
  public void tearDown() throws Exception {
	  
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
    	
		fail("Error !!");
    }
  }
}