package Fax_Tests;

import static org.junit.Assert.fail;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import Fax_Tests.GlobalFuncs;

/**
* ----------------
* This test tests a create, edit and delete of an outgoing-rule in the Web-Admin service
* -----------------
* Tests:
*    1. Login to the web-Service
*    2. Create, edit and delete an outgoing-rule
* 
* Results:
*    1. Login should end successfully
*    2. All actions on outgoing-rule should end successfully.
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test104 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;
  WebFuncs				webFuncs;
  Random				rand;

  // Default constructor for print the name of the used browser 
  public Test104(String browser) {
	  
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
	rand 	  = new Random();
  }

  @Test
  public void Test104___Outgoing_rule_actions() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  
	  // Test outgoing-rule actions
	  testFuncs.myDebugPrinting("Test outgoing-rule actions");
	  int  from = rand.nextInt(8) + 1;
	  String id  = testFuncs.getId();
	  String[] extraData = {"OutgoingRuleName" + id, id, String.valueOf(from), String.valueOf(from + 1)};
	  webFuncs.setConfiguration(104, "Test GW", extraData);
  }  
  
  @After
  public void tearDown() throws Exception {
	  
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
    	
		fail("Error !!");
    }
  }
}