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
* This test tests a create of two GWs with the same IP but different port
* -----------------
* Tests:
*    1. Login to the web-Service
*    2. Create two GWs with same IP but different port
*    3. Delete the created GWs
* 
* Results:
*    1. Login should end successfully
*    2. The two GWs should be created successfully.
*    3. The two GWs should be deleted successfully.
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test105 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;
  WebFuncs				webFuncs;

  // Default constructor for print the name of the used browser 
  public Test105(String browser) {
	  
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
  public void Test105___Two_Gws_samw_IP_differnt_port() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  
	  // Test outgoing-rule actions
	  testFuncs.myDebugPrinting("Test outgoing-rule actions");
	  String id = testFuncs.getId();
	  String[] extraData = {"Gw" + id, testFuncs.getId(), testFuncs.getId(), "Description of Gateway" + id};
	  webFuncs.setConfiguration(105, "Test GW", extraData);
  }  
  
  @After
  public void tearDown() throws Exception {
	  
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
    	
		fail("Error !!");
    }
  }
}