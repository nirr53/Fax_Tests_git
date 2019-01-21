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
* This test tests the Fax-Engine logs
* -----------------
* Tests:
*    - Login to the web-Service
*    - Enter the logs menu and Fax-Engine menu
*    1. Enter menu and search files
*    2. For each service check all display options
*    3. For each service download its log
* 
* Results:
*    1. All the headers should be detected
*    2. All display option should work.
*    3. All downloads should work
*  
* @author Nir Klieman
* @version 1.00
*/

@RunWith(Parameterized.class)
public class Test130 {
	
  private StringBuffer  verificationErrors = new StringBuffer();
  GlobalVars 			testVars;
  GlobalFuncs			testFuncs;
  WebFuncs				webFuncs;

  // Default constructor for print the name of the used browser 
  public Test130(String browser) {
	  
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
    testFuncs = new GlobalFuncs(testVars); 
    webFuncs  = new WebFuncs(testFuncs, testVars);
  }

  @Test
  public void test1() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  
	  // Test service
	  testFuncs.myDebugPrinting("Test service");
	  String[] extraData = {"osip_log.txt"};
	  webFuncs.setConfiguration(130, "Check Fax enfine Logs", extraData);
  }
  
  @Test
  public void test2() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  
	  // Test service
	  testFuncs.myDebugPrinting("Test service");
	  String[] extraData = {"OTF_ADMIN_LOG_1.txt"};
	  webFuncs.setConfiguration(130, "Check Fax enfine Logs", extraData);
  }
  
  @Test
  public void test3() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  
	  // Test service
	  testFuncs.myDebugPrinting("Test service");
	  String[] extraData = {"OTF_GROUP_MGR_LOG_1.txt"};
	  webFuncs.setConfiguration(130, "Check Fax enfine Logs", extraData);
  }
  
  @Test
  public void test4() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  
	  // Test service
	  testFuncs.myDebugPrinting("Test service");
	  String[] extraData = {"OTF_RSM_SIP_BOARDMANAGER_LOG_1.txt"};
	  webFuncs.setConfiguration(130, "Check Fax enfine Logs", extraData);
  }
  
  @Test
  public void test5() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  
	  // Test service
	  testFuncs.myDebugPrinting("Test service");
	  String[] extraData = {"OTF_KERNEL_LOG_1.txt"};
	  webFuncs.setConfiguration(130, "Check Fax enfine Logs", extraData);
  }
  
  @Test
  public void test6() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  
	  // Test service
	  testFuncs.myDebugPrinting("Test service");
	  String[] extraData = {"OTF_RSM_SIP_FAXRECEIVER_LOG_1.txt"};
	  webFuncs.setConfiguration(130, "Check Fax enfine Logs", extraData);
  }
  
  @Test
  public void test7() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  
	  // Test service
	  testFuncs.myDebugPrinting("Test service");
	  String[] extraData = {"OTF_RSM_SIP_FAXSENDER_LOG_1.txt"};
	  webFuncs.setConfiguration(130, "Check Fax enfine Logs", extraData);
  }
  
  @Test
  public void test8() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  
	  // Test service
	  testFuncs.myDebugPrinting("Test service");
	  String[] extraData = {"OTF_SCR_LOG_1.txt"};
	  webFuncs.setConfiguration(130, "Check Fax enfine Logs", extraData);
  }
  
  @Test
  public void test9() throws Exception {
	  
	  Log.startTestCase(this.getClass().getName());
	  
	  // Test service
	  testFuncs.myDebugPrinting("Test service");
	  String[] extraData = {"Fax Engine."};
	  webFuncs.setConfiguration(130, "Check Fax enfine Logs", extraData);
  }
  

  @After
  public void tearDown() throws Exception {
	  
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
    	
		fail("Error !!");
    }
  }
}