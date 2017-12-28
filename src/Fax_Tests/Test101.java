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
* This test tests a create, edit and delete of a Fax-In user in the Web-Admin service
* -----------------
* Tests:
*    1. Login to the web-Service
*    2. Create, edit and delete a fax-In user
* 
* Results:
*    1. Login should end successfully
*    2. All actions on fax-In user should end successfully.
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test101 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;
  WebFuncs				webFuncs;

  // Default constructor for print the name of the used browser 
  public Test101(String browser) {
	  
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
  public void Test101___Fax_in_actions() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  
	  // Test fax-In actions
	  testFuncs.myDebugPrinting("Test fax-In actions");
	  String id = testFuncs.getId();
	  String[] extraData = {id, id + "@" + id + ".com", "myDisplayName" + id, "myFaxIDName" + id};
	  webFuncs.setConfiguration(101, "Test fax-In actions", extraData);
  }

  @After
  public void tearDown() throws Exception {
	  
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
    	
		fail("Error !!");
    }
  }
}