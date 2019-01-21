package Fax_Tests;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import Fax_Tests.enumsClass.*;

public class WebFuncs {
	
	// General data
	private GlobalFuncs testFuncs;
	private GlobalVars  testVars;
	private WebDriver 	driver;
	
	// Management paths
	private final String MENAGMENT_SECTION 		   = "//*[@id='tab_names']/tbody/tr/td[2]/table/tbody/tr[1]/td[2]/a";
	private final String SYSTEM_SETTINGS_SECTION   = "//*[@id='plus_minus_6']";
	private final String GENERAL_SETTINGS_SECTION  = "//*[@id='c6.1']";	
	private final String FAX_IN_SETTINGS_SECTION   = "//*[@id='c6.3']";	
	private final String FAX_OUT_SETTINGS_SECTION  = "//*[@id='c6.4']";	
	private final String FAX_IN 				   = "//*[@id='plus_minus_7']";
	private final String FAX_IN_NUMBERS 		   = "//*[@id='c7.1']";
	private final String FAX_OUT 				   = "//*[@id='plus_minus_8']";
	private final String FAX_OUT_NUMBERS 		   = "//*[@id='c8.1']";
	private final String GATEWAYS 		   		   = "//*[@id='c8.3']";
	private final String OUTGOING_RULES 		   = "//*[@id='c8.4']";
	
	// Status and Diagnostics paths
	private final String STTS_DIGTCS_SECTION 	   = "//*[@id='tab_names']/tbody/tr/td[2]/table/tbody/tr[1]/td[3]/a";
	private final String LOGS 		   	   	   	   = "//*[@id='plus_minus_6']";
	private final String APPLICATION_LOGS  		   = "//*[@id='c6.1']";	
	private final String CALL_LOGS 		   	   	   = "//*[@id='plus_minus_7']";
	private final String RECEIVED_FAXES 		   = "//*[@id='c7.1']";
	private final String SENT_FAXES 		   	   = "//*[@id='c7.2']";
	private final String USER_MANUALLS 		   	   = "//*[@id='plus_minus_9']";
	private final String ADMIN_USER_MANUALLS 	   = "//*[@id='c9.1']";
	
	// Default constructor
	public WebFuncs(GlobalFuncs testFuncs, GlobalVars testVars) {
		
		this.testFuncs = testFuncs;
		this.testVars  = testVars;
	}
	
	// Main Switch-Case function
	public String[] getPaths(menuNames menuName) {	
		
		String[] paths = {"", "", "", ""};
		switch (menuName) {
		
			// System Settings menu 
			case FAX_IN_SETTINGS:
	        	paths[0] = MENAGMENT_SECTION;          	
	        	paths[1] = SYSTEM_SETTINGS_SECTION;
	        	paths[2] = FAX_IN_SETTINGS_SECTION;
	        	break;        	
			case FAX_IN_SETTINGS_OPEN:
	        	paths[0] = FAX_IN_SETTINGS_SECTION;
	        	break;	
			case FAX_OUT_SETTINGS:
            	paths[0] = MENAGMENT_SECTION;          	
            	paths[1] = SYSTEM_SETTINGS_SECTION;
            	paths[2] = FAX_OUT_SETTINGS_SECTION;
            	break;          	
			case GENERAL_SETTINGS:
            	paths[0] = MENAGMENT_SECTION;          	
            	paths[1] = SYSTEM_SETTINGS_SECTION;
            	paths[2] = GENERAL_SETTINGS_SECTION;
            	break;
			case GENERAL_SETTINGS_OPEN:
            	paths[0] = GENERAL_SETTINGS_SECTION;
            	break;
			case MNG_GENERAL_SECTION:
            	paths[0] = MENAGMENT_SECTION;
            	paths[1] = GENERAL_SETTINGS_SECTION;
            	break;
            	
            // Fax-In menu
			case FAX_IN_NUMBERS:
            	paths[0] = MENAGMENT_SECTION;          	
            	paths[1] = FAX_IN;
            	paths[2] = FAX_IN_NUMBERS;
            	break;	
			case FAX_IN_NUMBERS_OPEN:
            	paths[0] = FAX_IN_NUMBERS;
            	break;	
            	
            // Fax-Out menu
    		case FAX_OUT_NUMBERS:
    			paths[0] = MENAGMENT_SECTION;          	
    			paths[1] = FAX_OUT;
    			paths[2] = FAX_OUT_NUMBERS;	
    			break;
    		case FAX_OUT_NUMBERS_OPEN:
    			paths[0] = FAX_OUT_NUMBERS;	
    			break;
        	case GATEWAYS:
        		paths[0] = MENAGMENT_SECTION;          	
        		paths[1] = FAX_OUT;
        		paths[2] = GATEWAYS;	
        		break;	
        	case OUTGOING_RULES:
    			paths[0] = MENAGMENT_SECTION;          	
    			paths[1] = FAX_OUT;
    			paths[2] = OUTGOING_RULES;	
    			break;   			
    			
    		// Status & diagnostics	
        	case APPLICATION_LOGS:
    			paths[0] = STTS_DIGTCS_SECTION;  
    			paths[1] = LOGS;    
    			paths[2] = APPLICATION_LOGS;          	
        		break; 		     		
        	case APPLICATION_LOGS_OPEN:
    			paths[0] = APPLICATION_LOGS;          	
        		break;
        	case RECEIVED_FAXES:
    			paths[0] = STTS_DIGTCS_SECTION;          	
    			paths[1] = CALL_LOGS;
    			paths[2] = RECEIVED_FAXES;	
        		break;
        	case SENT_FAXES:
    			paths[0] = STTS_DIGTCS_SECTION;          	
    			paths[1] = CALL_LOGS;
    			paths[2] = SENT_FAXES;	
        		break;
        	case SENT_FAXES_OPEN:
    			paths[0] = SENT_FAXES;	
        		break;
        	case ADMIN_USER_MANUALS:
    			paths[0] = STTS_DIGTCS_SECTION;  
    			paths[1] = USER_MANUALLS;    
    			paths[2] = ADMIN_USER_MANUALLS;          	
        		break;  
			}
		
		return paths;  
	}
	  
	/**
	*  Main function that set a configuration on the Fax-server
	*  @param stepNumber  - given step-number
	*  @param description - given step-description
	*  @param extraData   - Extra data array for data which is needed for the test
	*  @throws UnsupportedEncodingException 
	*  @throws UnknownHostException 
	*/
	public void setConfiguration(int stepNumber, String description, String[] extraData) throws UnsupportedEncodingException, UnknownHostException {
			
		// Create a driver and login the site
		testFuncs.myDebugPrinting("Description - " + description, enumsClass.logModes.NORMAL);
		driver = defineUsedBrowser();
		login(driver, testVars.getSysUsername(), testVars.getSysPassword(), testVars.getSysMainStr()); 
		
		// Activate the needed configuration
		switch (stepNumber) {
		
			case 9:
	    		testFuncs.myDebugPrinting("Test <" + stepNumber + "> block:", enumsClass.logModes.MINOR);
				enterMenu(driver, enumsClass.menuNames.FAX_OUT_SETTINGS, "Add Cover Page");
				setFaxOutFaxId(extraData[0]);
				submitPage(driver);
				enterMenu(driver, enumsClass.menuNames.FAX_IN_SETTINGS_OPEN, "Default Email");
				setFaxInFaxId(extraData[0]);
				submitPage(driver);
				break;
				
			case 48:
			case 49:
			case 78:
	    		testFuncs.myDebugPrinting("Test <" + stepNumber + "> block:", enumsClass.logModes.MINOR);
				enterMenu(driver, enumsClass.menuNames.FAX_OUT_SETTINGS, "Add Cover Page");
				checkCoverPage(extraData[0].equals("1"));
				submitPage(driver);
				break;
				
			case 54:
	    		testFuncs.myDebugPrinting("Test <" + stepNumber + "> block:", enumsClass.logModes.MINOR);
				enterMenu(driver, enumsClass.menuNames.FAX_OUT_SETTINGS, "Add Cover Page");
				setFaxOutFaxId(extraData[0]);
				submitPage(driver);
				break;
				
			case 55:
	    		testFuncs.myDebugPrinting("Test <" + stepNumber + "> block:", enumsClass.logModes.MINOR);
				enterMenu(driver, enumsClass.menuNames.FAX_OUT_SETTINGS, "Add Cover Page");
				setFaxOutFaxId(extraData[0]);
	    		testFuncs.myDebugPrinting("Fax-ID - "  + extraData[1], enumsClass.logModes.MINOR);
				mySendKeys(By.xpath("//*[@id='fax_id']"), extraData[1], 2000);
				submitPage(driver);
				break;
				
			case 56:
	    		testFuncs.myDebugPrinting("Test <" + stepNumber + "> block:", enumsClass.logModes.MINOR);
				enterMenu(driver, enumsClass.menuNames.FAX_IN_SETTINGS, "Default Email");
				setFaxInFaxId(extraData[0]);
	    		testFuncs.myDebugPrinting("Fax-ID - "  + extraData[1], enumsClass.logModes.MINOR);
				mySendKeys(By.id("fax_id"), extraData[1], 9000);			
				submitPage(driver);
				break;
				
			case 57:
			case 58:
	    		testFuncs.myDebugPrinting("Test <" + stepNumber + "> block:", enumsClass.logModes.MINOR);
				enterMenu(driver, enumsClass.menuNames.FAX_IN_SETTINGS, "Default Email");
				setFaxInFaxId(extraData[0]);
				submitPage(driver);
				break;
				
			case 59:
	    		testFuncs.myDebugPrinting("Test <" + stepNumber + "> block:", enumsClass.logModes.MINOR);
				enterMenu(driver, enumsClass.menuNames.FAX_OUT_SETTINGS, "Add Cover Page");
				checkEmailConfirmation(extraData[0].equals("off"));
				submitPage(driver);
				break;
				
			case 61:
	    		testFuncs.myDebugPrinting("Test <" + stepNumber + "> block:", enumsClass.logModes.MINOR);
				enterMenu(driver, enumsClass.menuNames.FAX_OUT_SETTINGS, "Add Cover Page");
				checkDisplayRemoteId(extraData[0].equals("off"));
				submitPage(driver);
				break;
				
			case 65:
	    		testFuncs.myDebugPrinting("Test <" + stepNumber + "> block:", enumsClass.logModes.MINOR);
	    		
	    		if (extraData[0].equals("disabled") || extraData[0].equals("enabled")) {
	    			
					enterMenu(driver, enumsClass.menuNames.GENERAL_SETTINGS, "From Email Address");
					checkArchiveCheckbox(extraData[0].equals("disabled"));
					submitPage(driver);
				    driver.switchTo().defaultContent();
	    		} else if (extraData[0].equals("check_disabled")) {
	    			
					enterMenu(driver, enumsClass.menuNames.SENT_FAXES, "From Email");	
	    		}
//				enterMenu(driver, enumsClass.menuNames.FAX_OUT_SETTINGS, "Add Cover Page");
//				checkDisplayRemoteId(extraData[0].equals("off"));
//				submitPage(driver);
				break;
				
			case 82:
			case 83:
			case 84:
			case 86:
			case 87:
	    		testFuncs.myDebugPrinting("Test <" + stepNumber + "> block:", enumsClass.logModes.MINOR);
				enterMenu(driver, enumsClass.menuNames.FAX_OUT_SETTINGS, "Add Cover Page");
				setFaxOutFaxId(extraData[0]);
	    		testFuncs.myDebugPrinting("Fax-ID - "       + extraData[1], enumsClass.logModes.MINOR);
				mySendKeys(By.xpath("//*[@id='fax_id']"), extraData[1], 2000);
	    		testFuncs.myDebugPrinting("Default CLI - "  + extraData[2], enumsClass.logModes.MINOR);
				mySendKeys(By.xpath("//*[@id='default_cli']"), extraData[2], 2000);
				submitPage(driver);
				break;
				
			case 90:
	    		testFuncs.myDebugPrinting("Test <" + stepNumber + "> block:", enumsClass.logModes.MINOR);
				enterMenu(driver, enumsClass.menuNames.GENERAL_SETTINGS, "From Email Address");
	    		testFuncs.myDebugPrinting("Email - " + extraData[0], enumsClass.logModes.MINOR);
	    	    driver.switchTo().frame(1);
				mySendKeys(By.xpath("//*[@id='email']"), extraData[0], 10000);
				submitPage(driver);
			    driver.switchTo().defaultContent();
				break;
				
			case 91:
			case 92:
			case 93:
			case 94:
	    		testFuncs.myDebugPrinting("Test <" + stepNumber + "> block:", enumsClass.logModes.MINOR);
				enterMenu(driver, enumsClass.menuNames.FAX_OUT_SETTINGS, "Add Cover Page");
				setFaxOutFaxId(extraData[1]);
				submitPage(driver);
				enterMenu(driver, enumsClass.menuNames.GENERAL_SETTINGS_OPEN, "From Email Address");
	    		testFuncs.myDebugPrinting("Attachment name - " + extraData[0], enumsClass.logModes.MINOR);
	    	    driver.switchTo().frame(1);
	    	    testFuncs.myWait(2000);    	    
	    	    driver.findElement(By.id("att_name")).clear();;    		
	    	    testFuncs.myWait(3000); 	    	    
	    	    driver.findElement(By.id("att_name")).sendKeys(extraData[0]);    		
	    	    testFuncs.myWait(3000); 	  
				submitPage(driver);
			    driver.switchTo().defaultContent();
				break;
				
			case 97:
			case 98:
			case 99:
			case 114:
			case 115:
	    		testFuncs.myDebugPrinting("Test <" + stepNumber + "> block:", enumsClass.logModes.MINOR);
				enterMenu(driver, enumsClass.menuNames.FAX_OUT_SETTINGS, "Add Cover Page");
	    		testFuncs.myDebugPrinting("Max Fax Recipients - " + extraData[0], enumsClass.logModes.MINOR);
	    	    driver.switchTo().frame(1);
				mySendKeys(By.xpath("//*[@id='max_rec']"), extraData[0], 2000);
				submitPage(driver);
			    driver.switchTo().defaultContent();
				break;
				
			case 100:
	    		testFuncs.myDebugPrinting("Test <" + stepNumber + "> block:", enumsClass.logModes.MINOR);
				break;
				
			case 101:
	    		testFuncs.myDebugPrinting("Test <" + stepNumber + "> block:", enumsClass.logModes.MINOR);
				enterMenu(driver, enumsClass.menuNames.FAX_IN_NUMBERS, "Sort By");
				createFaxInUser(extraData[0], extraData[1], extraData[2], extraData[3]);
				String newfaxInNumber 		= testFuncs.getId();
				String newfaxInDisplayName  = "new" + extraData[1];
				String newfaxInEmail	    = "new" + extraData[2];
				String newfaxInFaxId 		= "new" + extraData[3];
				editFaxInUser(extraData[0], newfaxInNumber, newfaxInDisplayName, newfaxInEmail, newfaxInFaxId);
				enterMenu(driver, enumsClass.menuNames.FAX_IN_NUMBERS_OPEN, "Sort By");
			    driver.switchTo().frame(1);
				deleteFaxInUser(newfaxInNumber, newfaxInDisplayName);
				break;
				
			case 102:
	    		testFuncs.myDebugPrinting("Test <" + stepNumber + "> block:", enumsClass.logModes.MINOR);
				enterMenu(driver, enumsClass.menuNames.FAX_OUT_NUMBERS, "Sort By");
				createFaxOutUser(extraData[0], extraData[1], extraData[2], extraData[3]);
				String newfaxOutNumber 		= testFuncs.getId();
				String newfaxOutEmail 		= "new" + extraData[1];
				String newfaxOutDisplayName	= "new" + extraData[2];
				String newfaxOutFaxId 		= "new" + extraData[3];
				editFaxOutUser(extraData[0], newfaxOutNumber, newfaxOutEmail, newfaxOutDisplayName, newfaxOutFaxId);
				enterMenu(driver, enumsClass.menuNames.FAX_OUT_NUMBERS_OPEN, "Sort By");
			    driver.switchTo().frame(1);
			    deleteFaxOutUser(newfaxOutNumber, newfaxOutDisplayName);
				break;
				
			case 103:
	    		testFuncs.myDebugPrinting("Test <" + stepNumber + "> block:", enumsClass.logModes.MINOR);
				enterMenu(driver, enumsClass.menuNames.GATEWAYS, "Port");
				createGW(extraData[0], extraData[1], extraData[2], extraData[3]);
				String newGwName 		= testFuncs.getId();
				String newGwIP 		 	= testFuncs.getId();
				String newPort 			= testFuncs.getId();
				String newGwDescription	= "New description:" + newGwName;
				editGW(extraData[0], newGwName, newGwIP, newPort, newGwDescription);
			    deleteGW(newGwName, newGwDescription);
				break;
				
			case 104:
	    		testFuncs.myDebugPrinting("Test <" + stepNumber + "> block:", enumsClass.logModes.MINOR);
				enterMenu(driver, enumsClass.menuNames.OUTGOING_RULES, "Number Starts With");
				createOutgoingRule(extraData[0], extraData[1], extraData[2], extraData[3]);
				String newId = testFuncs.getId();
				String newOutgoingRuleName	 = "OutgoingRuleName" + newId;
				String newOutgoingRulePrefix = newId;
				String newOutgoingRuleFrom 	 = newId;
				String newOutgoingRuleTo	 = String.valueOf(Integer.valueOf(newId) + 1);
				editOutgoingRule(extraData[0], newOutgoingRuleName, newOutgoingRulePrefix, newOutgoingRuleFrom, newOutgoingRuleTo);
			    deleteOutgoingRule(newOutgoingRuleName, newOutgoingRulePrefix);
				break;
				
			case 105:
	    		testFuncs.myDebugPrinting("Test <" + stepNumber + "> block:", enumsClass.logModes.MINOR);
				enterMenu(driver, enumsClass.menuNames.GATEWAYS, "Port");
				createGW(extraData[0]		 , extraData[1], 	   extraData[2], extraData[3]);
				String newName = "Gw" 					  + testFuncs.getId();
				String newDesc = "Description of Gateway" + testFuncs.getId();
				createGW(newName, extraData[1], "12" + extraData[2], newDesc);
			    deleteGW(extraData[0], extraData[3]);
			    driver.switchTo().defaultContent();
			    deleteGW(newName	 , extraData[3]);
				break;
				
			case 111:
	    		testFuncs.myDebugPrinting("Test <" + stepNumber + "> block:", enumsClass.logModes.MINOR);
				enterMenu(driver, enumsClass.menuNames.GENERAL_SETTINGS, "From Email Address");
				checkAddSymbols(extraData[0].equals("1"));
				submitPage(driver);
			    driver.switchTo().defaultContent();
				break;
				
			case 112:
	    		testFuncs.myDebugPrinting("Test <" + stepNumber + "> block:", enumsClass.logModes.MINOR);
				enterMenu(driver, enumsClass.menuNames.FAX_OUT_SETTINGS, "Add Cover Page");
			    driver.switchTo().frame(1);
				mySendKeys(By.xpath("//*[@id='send_multi']")		, extraData[0], 4000);	
				submitPage(driver);
			    driver.switchTo().defaultContent();		
				break;
				
			case 116:
			case 117:
			case 118:
	    		testFuncs.myDebugPrinting("Test <" + stepNumber + "> block:", enumsClass.logModes.MINOR);
				enterMenu(driver, enumsClass.menuNames.APPLICATION_LOGS, "Application Logs");
			    driver.switchTo().frame(1);
	    		
	    		// Enter the menu
    			String filePrefix   = "";
    			String logForSearch = "";
	    		switch (enumsClass.appLogsMenuNames.valueOf(extraData[0])) {
	    		
		    		case FAX_IN_SERVICE:
		    			myClick(By.xpath("//*[@id='trunkTBL']/table[1]/tbody/tr/td/table/tbody/tr[2]/td[3]/a"), 7000);
		    			searchStr("f2e.log");
		    			filePrefix = "service.";
		    			logForSearch = " Info ";
		    			break;
		    		case FAX_OUT_SERVICE:
		    			myClick(By.xpath("//*[@id='trunkTBL']/table[1]/tbody/tr/td/table/tbody/tr[3]/td[3]/a"), 7000);
		    			searchStr("m2fhm.log");
		    			filePrefix = "emailToFax.service.";
		    			break;
		    		case AUTO_ATTENDANT_SERVICE:
		    			myClick(By.xpath("//*[@id='trunkTBL']/table[1]/tbody/tr/td/table/tbody/tr[4]/td[3]/a"), 7000);   			  
		    			if (driver.findElement(By.tagName("body")).getText().contains("No log files.")) {
		    				  	  
		    				testFuncs.myDebugPrinting("No log lines were detected !!",  enumsClass.logModes.MINOR);
		    				return;
		    			}     			
		    			break;			
		    		case SYSTEM_WATCHDOG:
		    			myClick(By.xpath("//*[@id='trunkTBL']/table[1]/tbody/tr/td/table/tbody/tr[5]/td[3]/a"), 7000);
		    			searchStr("f2mw.log");
		    			filePrefix = "faxToEmail.Watchdog.";
		    			break;
		    		case FAX_SERVER:
		    			myClick(By.xpath("//*[@id='trunkTBL']/table[1]/tbody/tr/td/table/tbody/tr[6]/td[3]/a"), 7000);
		    			searchStr("FaxServer_");
		    			filePrefix = "Fax Server.";		
		    		    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		    		    Date date     = new Date();
		    			logForSearch = dateFormat.format(date) + ",";	
		    			break;
		    		case WEB_ADMIN:
		    			myClick(By.xpath("//*[@id='trunkTBL']/table[1]/tbody/tr/td/table/tbody/tr[8]/td[3]/a"), 7000);
		    			searchStr("log.txt");
		    			filePrefix = "web.admin.";
		    			logForSearch = Inet4Address.getLocalHost().getHostAddress();
		    			break;
		    		case ACTIVITY:
		    			myClick(By.xpath("//*[@id='trunkTBL']/table[1]/tbody/tr/td/table/tbody/tr[9]/td[3]/a"), 7000);
		    			searchStr("log.txt");
		    			filePrefix = "activity.";
		    		    DateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
		    		    Date date3     = new Date();
		    			logForSearch = dateFormat3.format(date3);
		    			break;
		    		case BACKUP:
		    			myClick(By.xpath("//*[@id='trunkTBL']/table[1]/tbody/tr/td/table/tbody/tr[10]/td[3]/a"), 7000);
		    			searchStr("log.txt");
		    			filePrefix = "faxToEmail.Backup.";
		    		    DateFormat dateFormat4 = new SimpleDateFormat("yyyy-MM-dd");
		    		    Date date4     = new Date();
		    			logForSearch = dateFormat4.format(date4);
		    			break;
	    		}
	    		
	    		// Check Display options, log-level and download current log
	    		testFuncs.myDebugPrinting("Check Display options, log-level and download current log", enumsClass.logModes.MINOR);	
	    		checkDisplayOptions(driver, logForSearch);
	    		checkLogLevel(driver, extraData[0]);
	    		downloadCurrLog(filePrefix);
	    		checkArchive(driver, extraData[0], filePrefix);
	    		
	    		// Return to service menu
	    		testFuncs.myDebugPrinting("Return to service menu", enumsClass.logModes.MINOR);    		
			    driver.switchTo().defaultContent();
				myClick(By.xpath("//*[@id='back_img']"), 5000);
				break;
				
			case 119:
	    		testFuncs.myDebugPrinting("Test <" + stepNumber + "> block:", enumsClass.logModes.MINOR);
				enterMenu(driver, enumsClass.menuNames.ADMIN_USER_MANUALS, "User Manuals");
			    driver.switchTo().frame(1);
	    		testFuncs.myDebugPrinting("Test <" + stepNumber + "> block:", enumsClass.logModes.MINOR);	
	    		verifyStrByXpath(driver, "//*[@id='trunkTBL']/table/tbody/tr[1]/td", "User Manuals");
	    		
	    		// Check for pdf icons
	    		for (int i = 2; i < 7; ++i) {
	    			
		    		WebElement elm = driver.findElement(By.xpath("//*[@id='trunkTBL']/table/tbody/tr[" + i + "]/td[2]/a/img"));
		    		testFuncs.myAssertTrue("PDF icon was not detected !!", elm.getAttribute("src").contains("pdf_icon.gif"));
	    		}
	    		
	    		// Download each of the manuals
	    		testFuncs.myDebugPrinting("Download each of the manuals", enumsClass.logModes.MINOR);	  	      
	    		String winHandleBefore = driver.getWindowHandle();
	    		for (int i = 2; i < 7; ++i) {
	    			
	    			myClick(By.xpath("//*[@id='trunkTBL']/table/tbody/tr["+ i + "]/td[2]/a"), 20000);
	    			java.util.Set<java.lang.String> windowHandles = driver.getWindowHandles(); 	      
	    			for (String window : windowHandles) {
	    				    	    
	    				  if(!window.equals(winHandleBefore)) {
	    				        	   		  
	    					  driver.switchTo().window(window); 
	    					  String decoded = URLDecoder.decode(driver.getCurrentUrl(), "UTF-8");
	    					  testFuncs.myDebugPrinting("decoded - " + decoded, enumsClass.logModes.MINOR);	  	      
	    					  testFuncs.myAssertTrue("Guide was not opened properly !! <" + decoded + ">", decoded.contains(extraData[i -2]));
	    					  driver.close();  
	    				  }
	    				  driver.switchTo().window(winHandleBefore);  
	    			  }
				    driver.switchTo().frame(1);
	    		}
				break;
				
			case 125:
	    		testFuncs.myDebugPrinting("Test <" + stepNumber + "> block:", enumsClass.logModes.MINOR);
				enterMenu(driver, enumsClass.menuNames.GENERAL_SETTINGS, "From Email Address");
	    		testFuncs.myDebugPrinting("Data format - " + extraData[0], enumsClass.logModes.MINOR);
	    	    driver.switchTo().frame(1);				
				testFuncs.myWait(3000);
				new Select(driver.findElement(By.xpath("//*[@id='datetime_format']"))).selectByValue(extraData[0]);
				testFuncs.myWait(3000);
				submitPage(driver);
			    driver.switchTo().defaultContent();
				break;
				
			case 126:
	    		testFuncs.myDebugPrinting("Test <" + stepNumber + "> block:", enumsClass.logModes.MINOR);
				enterMenu(driver, enumsClass.menuNames.FAX_OUT_SETTINGS, "Add Cover Page");
				setFaxOutFaxId(extraData[0]);
				submitPage(driver);
				enterMenu(driver, enumsClass.menuNames.FAX_IN_SETTINGS_OPEN, "Default Email");
				setFaxInFaxId(extraData[0]);
				submitPage(driver);
				break;
				
			case 127:
	    		testFuncs.myDebugPrinting("Test <" + stepNumber + "> block:", enumsClass.logModes.MINOR);
				enterMenu(driver, enumsClass.menuNames.RECEIVED_FAXES, "From (CLI)");	  
	    	    driver.switchTo().frame(1);
				String bodyText     = driver.findElement(By.tagName("body")).getText();
				testFuncs.myAssertTrue("None of the Timezones <" + extraData[0] + "> was not detected !!\nbodyText - " + bodyText, bodyText.contains(extraData[0]) ||																								     		   bodyText.contains(extraData[3]));
	    	    driver.switchTo().defaultContent();
				enterMenu(driver, enumsClass.menuNames.SENT_FAXES_OPEN, "From Email");	
	    	    driver.switchTo().frame(1);
				bodyText     = driver.findElement(By.tagName("body")).getText();
				testFuncs.myAssertTrue("None of the Timezones <" + extraData[0] + "> was not detected !!\nbodyText - " + bodyText, bodyText.contains(extraData[0]) ||
		  		   																										   bodyText.contains(extraData[3]));			     		   
				break;
				
			case 129:
	    		testFuncs.myDebugPrinting("Test <" + stepNumber + "> block:", enumsClass.logModes.MINOR);
				enterMenu(driver, enumsClass.menuNames.APPLICATION_LOGS, "Application Logs");
			    driver.switchTo().frame(1);   
			    
			    // Download file
	    		testFuncs.myDebugPrinting("The searched prefix is <" + extraData[0] + ">", enumsClass.logModes.MINOR);
			    myClick(By.xpath("//*[@id='trunkTBL']/table[2]/tbody/tr/td/a"), 20000);
				testFuncs.myAssertTrue("File was not downloaded successfully !!", testFuncs.findFilesByGivenPrefix(testVars.getDownloadsPath(), extraData[0]));
				
				// Delete file
				testFuncs.deleteFilesByPrefix(testVars.getDownloadsPath(), extraData[0]);
			    break;
			    
			case 130:
	    		testFuncs.myDebugPrinting("Test <" + stepNumber + "> block:", enumsClass.logModes.MINOR);
				enterMenu(driver, enumsClass.menuNames.APPLICATION_LOGS, "Application Logs");
			    driver.switchTo().frame(1);  
			    
			    // Enter menu and search files
	    		testFuncs.myDebugPrinting("Enter menu and search files:", enumsClass.logModes.NORMAL);
    			myClick(By.xpath("//*[@id='trunkTBL']/table[1]/tbody/tr/td/table/tbody/tr[7]/td[3]/a"), 7000);
    			for (int i = 0; i < (extraData.length - 1); ++i) {
    						
    				searchStr(extraData[i]);   
    			}		
    			checkFaxEngineDisplayOptions(extraData);   			
    		    driver.switchTo().defaultContent(); 
    			enterMenu(driver, enumsClass.menuNames.APPLICATION_LOGS_OPEN, "Application Logs");
    		    driver.switchTo().frame(1);	
    			checkFaxEngineDownloadLog(extraData);
    			break;
			    
			default:   
				driver.quit();
				testFuncs.myFail("Step Number <" + stepNumber + "> is not recognized !!");		
		}
		
		// Close the driver
		driver.quit();		
	}
	
	/**
	* Check Fax engine services download files option
	* @param driver    - given driver
	* @param extraData - array of services names at Fax engine section
	*/	
	private void checkFaxEngineDownloadLog(String[] extraData) {
		
		// For each service download the log
		testFuncs.myDebugPrinting("For each service download the log", enumsClass.logModes.NORMAL);
		String fileName = extraData[extraData.length-1];
		testFuncs.myDebugPrinting("fileName - " + fileName, enumsClass.logModes.MINOR);
		for (int i = 2; i < (extraData.length + 1); ++i) {
			
			// Download file
    		testFuncs.myDebugPrinting("Download file <" + (i) + ">", enumsClass.logModes.NORMAL);
			myClick(By.xpath("//*[@id='trunkTBL']/table/tbody/tr/td/table/tbody/tr[" + i + "]/td[3]/a"), 90000);
			testFuncs.myAssertTrue("File was not downloaded successfully !!", testFuncs.findFilesByGivenPrefix(testVars.getDownloadsPath(), fileName));
			
			// Delete file
    		testFuncs.myDebugPrinting("Delete file", enumsClass.logModes.MINOR);
			testFuncs.deleteFilesByPrefix(testVars.getDownloadsPath(), fileName);    				
		    driver.switchTo().defaultContent(); 
			enterMenu(driver, enumsClass.menuNames.APPLICATION_LOGS_OPEN, "Application Logs");
		    driver.switchTo().frame(1);  
			myClick(By.xpath("//*[@id='trunkTBL']/table[1]/tbody/tr/td/table/tbody/tr[7]/td[3]/a"), 15000);			
		} 		
	}

	/**
	* Check Display options of Fax engine
	* @param driver - given driver
	* @param extraData - array of services names at Fax enfine section
	*/
	private void checkFaxEngineDisplayOptions(String[] extraData) {
			
		// For each service check the display options
		testFuncs.myDebugPrinting("For each service check the display options", enumsClass.logModes.NORMAL);
		  	String[] disOptions = {"Hide log lines"		   , "Show last 10 log lines",
		  						   "Show last 20 log lines", "Show last 30 log lines",
		  						   "Show last 40 log lines", "Show last 50 log lines",
			       			   	   "Show last 100 log lines"};
		for (int i = 0; i < (extraData.length - 1); ++i) {
			
			int disNum = i;
			if (i > 4) { disNum += 1;}	
			testFuncs.myDebugPrinting("Set service <" + disNum + ">", enumsClass.logModes.MINOR);
			Select dispOptionsSelect = new Select(driver.findElement(By.xpath("//*[@id='loglines" + disNum + "']")));
 			for (int j = 0; j < disOptions.length; ++j) {
				
				testFuncs.myDebugPrinting("Set the option <" + disOptions[j] + ">", enumsClass.logModes.DEBUG);
				dispOptionsSelect.selectByIndex(j);
				testFuncs.myWait(10000);
			}	
		}	
	}

	/**
	* Check if Archive menu exits and if so check it
	* @param driver     - given driver
	* @param service    - name of current service
	* @param archPrefix - name of archive prefix
	*/
	private void checkArchive(WebDriver driver, String service, String archPrefix) {
		
		// Check if Archive exists
		testFuncs.myDebugPrinting("Check if Archive exists", enumsClass.logModes.MINOR);
		String bodyText     = driver.findElement(By.tagName("body")).getText();	  
		if (bodyText.contains("Archive Files")) {
			
			// Enter Archive menu
    		testFuncs.myDebugPrinting("Enter Archive menu", enumsClass.logModes.MINOR);
			myClick(By.xpath("//*[@id='trunkTBL']/table/tbody/tr[1]/td/table/tbody/tr[2]/td[3]/a"), 20000);
    		searchStr(service + " Archive Files");
    		
    		// Download one of the files
    		bodyText     = driver.findElement(By.tagName("body")).getText();	  
    		if (!bodyText.contains("Activity Archive Files") && !bodyText.contains("Fax Server Archive Files")) {
    					
	    		testFuncs.myDebugPrinting("Download one of the files", enumsClass.logModes.MINOR);
				myClick(By.xpath("//*[@id='trunkTBL']/table/tbody/tr/td/table/tbody/tr[2]/td[3]/a"), 10000);		
				testFuncs.myAssertTrue("File was not downloaded successfully !!", testFuncs.findFilesByGivenPrefix(testVars.getDownloadsPath(), archPrefix));
    		}
    		
			// Delete file
    		testFuncs.myDebugPrinting("Delete file", enumsClass.logModes.MINOR);
			testFuncs.deleteFilesByPrefix(testVars.getDownloadsPath(), archPrefix);
			
			// Return to service menu
    		testFuncs.myDebugPrinting("Return to service menu", enumsClass.logModes.MINOR);
			driver.switchTo().defaultContent();
			myClick(By.xpath("//*[@id='back_img']"), 5000);
		    driver.switchTo().frame(1);
		} else {
			
    		testFuncs.myDebugPrinting("Archive menu was not detected !!", enumsClass.logModes.MINOR);
		}
	}

	/**
	* Download current log
	* @param filePrefix - prefix for current log file
	*/
	private void downloadCurrLog(String filePrefix) {

	    String fileName  = filePrefix;
	    
	    // Download file
		testFuncs.myDebugPrinting("Download <" + fileName + ">", enumsClass.logModes.MINOR);
		if (driver.findElement(By.tagName("body")).getText().contains("Archive Files")) {
			
			myClick(By.xpath("//*[@id='trunkTBL']/table/tbody/tr[1]/td/table/tbody/tr[3]/td[3]/a"), 5000);
		} else {
			
			myClick(By.xpath("//*[@id='trunkTBL']/table/tbody/tr[1]/td/table/tbody/tr[2]/td[3]/a"), 5000);
		}
		testFuncs.myAssertTrue("File was not downloaded successfully !!", testFuncs.findFilesByGivenPrefix(testVars.getDownloadsPath(), fileName));
		
		// Delete file
		testFuncs.deleteFilesByPrefix(testVars.getDownloadsPath(), fileName);
	}

	/**
	* Check Log level options at given Service Logs menu
	* @param driver  - given driver
	* @param service - name of current service
	*/
	private void checkLogLevel(WebDriver driver, String service) {
		
		// Log level change is not available
		String bodyText     = driver.findElement(By.tagName("body")).getText();
		if (!bodyText.contains("Log Level")) {
			
			return;
		}
		
	  	// Change the log-level
		Select logLevelSelect = new Select(driver.findElement(By.xpath("//*[@id='loglevel']")));
		for (int i = 0; i < logLevelSelect.getAllSelectedOptions().size(); ++i) {
			
			logLevelSelect.selectByIndex(i);
			testFuncs.myWait(4000);
			myClick(By.xpath("//*[@id='trunkTBL']/table/tbody/tr[2]/td/table/tbody/tr/td[2]/a"), 5000);
    		verifyStrByXpath(driver, "//*[@id='jqi_state_state0']/div[1]/table/tbody/tr[1]/th", "Change " + service + " Log Level");
    		String level = logLevelSelect.getAllSelectedOptions().get(0).getText();
			testFuncs.myDebugPrinting("Set the option <" + level + ">", enumsClass.logModes.MINOR);
    		verifyStrByXpath(driver, "//*[@id='promt_div_id']", "Are you sure you want to change the " + service + " log level to " + level + " ?");
			myClick(By.xpath("//*[@id='jqi_state0_buttonYes']"), 5000);
			verifyStrByXpath(driver, "//*[@id='jqi_state_state0']/div[1]/table/tbody/tr[1]/th", "Change " + service + " Log Level");	
    		verifyStrByXpath(driver, "//*[@id='promt_div_id']", " updated successfully.");
			myClick(By.xpath("//*[@id='jqi_state0_buttonOk']"), 5000);
		}
	}

	/**
	* Check Display options at given Service Logs menu
	* @param driver - given driver
	* @param logTxt - string of displayed logs for search
	*/
	private void checkDisplayOptions(WebDriver 	driver, String logTxt) {
		
		Select dispOptionsSelect = new Select(driver.findElement(By.xpath("//*[@id='loglines']")));
	  	String[] disOptions = {"Hide log lines",
	  						   "Show last 10 log lines",
	  						   "Show last 20 log lines",
	  						   "Show last 30 log lines",
	  						   "Show last 40 log lines",
	  						   "Show last 50 log lines",
  						       "Show last 100 log lines"};

		for (int i = 0; i < disOptions.length; ++i) {
			
			testFuncs.myDebugPrinting("Set the option <" + disOptions[i] + ">", enumsClass.logModes.MINOR);
			dispOptionsSelect.selectByIndex(i);
			testFuncs.myWait(10000);
			
			// Search for displayed logs if display is NOT 'Hide log lines'
			if (i > 0) {
				
				searchStr(logTxt);
			}
			
	        if (!dispOptionsSelect.getAllSelectedOptions().get(0).getText().equals(disOptions[i])) {
	        	
	        	testFuncs.myFail("The Value " + disOptions[i] + " was not detected !! (" + dispOptionsSelect.getAllSelectedOptions().get(0).getText() + ")");
	        }
		}
	}

	/**
	* Delete an existing Outgoing Rule
	* @param newOutgoingruleName   - New Outgoing Rule name
	* @param newOutgoingRulePrefix - New Outgoing Rule prefix
	*/
	private void deleteOutgoingRule(String newOutgoingruleName, String newOutgoingRulePrefix) {
		
		// Get idx for delete
		testFuncs.myDebugPrinting("Get idx for delete", enumsClass.logModes.MINOR);
		int idx = getGwIdx(newOutgoingruleName);
		myClick(By.xpath("//*[@id='trunkTBL']/table[2]/tbody/tr[" + idx + "]/td[9]/a"), 5000);		
		verifyStrByXpath(driver, "//*[@id='jqi_state_state0']/div[1]/table/tbody/tr[1]/th", "Delete Outgoing Rule");
		verifyStrByXpath(driver, "//*[@id='promt_div_id']"								  , "Are you sure you want to delete the outgoing rule - " + newOutgoingruleName + " ?");
		myClick(By.xpath("//*[@id='jqi_state0_buttonDelete']"), 10000);
			
		// Verify delete
		testFuncs.myDebugPrinting("Verify delete of <" + newOutgoingruleName + ">", enumsClass.logModes.MINOR);
		String bodyText     = driver.findElement(By.tagName("body")).getText();
		testFuncs.myAssertTrue("Delete fails <" + newOutgoingruleName      + "> is still detected !!", !bodyText.contains(newOutgoingruleName));
		testFuncs.myAssertTrue("Delete fails <" + newOutgoingRulePrefix + "> is still detected !!"	 , !bodyText.contains(newOutgoingRulePrefix));
	}
		
	/**
	* Edit an existing Outgoing Rule
	* @param oldOutgoingRuleName   - Old Outgoing Rule name
	* @param newOutgoingRuleName   - New Outgoing Rule name header
	* @param newOutgoingRulePrefix - New Outgoing Rule Prefix header
	* @param newOutgoingRuleFrom   - New Outgoing Rule From header 
	* @param newOutgoingRuleTo 	   - New Outgoing Rule To header
	*/
	private void editOutgoingRule(String oldOutgoingRuleName, String newOutgoingRuleName, String newOutgoingRulePrefix,	String newOutgoingRuleFrom, String newOutgoingRuleTo) {
	
		// Get idx for edit
		testFuncs.myDebugPrinting("Get idx for edit", enumsClass.logModes.MINOR);
		int idx = getGwIdx(oldOutgoingRuleName);
		myClick(By.xpath("//*[@id='trunkTBL']/table[2]/tbody/tr[" + idx + "]/td[8]/a"), 5000);	
		verifyStrByXpath(driver, "//*[@id='trunkTBL']/table/tbody/tr[1]/td", "Modify Outgoing Rule - " + oldOutgoingRuleName);

		// Fill data
		testFuncs.myDebugPrinting("Fill data", enumsClass.logModes.MINOR);
		mySendKeys(By.xpath("//*[@id='name']"), newOutgoingRuleName, 2000);
		mySendKeys(By.xpath("//*[@id='min']") , newOutgoingRuleFrom, 2000);
		mySendKeys(By.xpath("//*[@id='max']") , newOutgoingRuleTo	, 2000);
		mySendKeys(By.xpath("//*[@id='trunkTBL']/table/tbody/tr[8]/td/table/tbody/tr/td[2]/input"), newOutgoingRulePrefix  , 2000);

		
		// Submit page and verify
		testFuncs.myDebugPrinting("Submit page and verify", enumsClass.logModes.MINOR);
		driver.switchTo().defaultContent();
		myClick(By.xpath("//*[@id='submit_img']"), 7000);
	    driver.switchTo().frame(1);
		searchStr(newOutgoingRuleName + " " + newOutgoingRulePrefix);
		driver.switchTo().defaultContent();	
	}

	/**
	* Create an Outgoing rule
	* @param outgoingRuleName	- New Outgoing rule name
	* @param outgoingRulePrefix	- New Outgoing rule prefix
	* @param outgoingRuleFrom   - New Outgoing rule From value
	* @param outgoingRuleTo		- New Outgoing rule To value
	*/
	private void createOutgoingRule(String outgoingRuleName, String outgoingRulePrefix, String outgoingRuleFrom, String outgoingRuleTo) {
		
		// Enter Add-GW number menu
		testFuncs.myDebugPrinting("Enter Add-GW number menu", enumsClass.logModes.MINOR);
	    driver.switchTo().frame(1);
		myClick(By.xpath("//*[@id='trunkTBL']/table[1]/tbody/tr/td/a"), 5000);
		verifyStrByXpath(driver, "//*[@id='trunkTBL']/table/tbody/tr[1]/td", "Add New Outgoing Rule");
		
		// Fill data
		testFuncs.myDebugPrinting("Fill data", enumsClass.logModes.MINOR);
		testFuncs.myDebugPrinting("outgoingRuleName - "   + outgoingRuleName  , enumsClass.logModes.MINOR);
		testFuncs.myDebugPrinting("outgoingRuleFrom - "   + outgoingRuleFrom  , enumsClass.logModes.MINOR);
		testFuncs.myDebugPrinting("outgoingRuleTo - "     + outgoingRuleTo    , enumsClass.logModes.MINOR);
		testFuncs.myDebugPrinting("outgoingRulePrefix - " + outgoingRulePrefix, enumsClass.logModes.MINOR);
		mySendKeys(By.xpath("//*[@id='name']"), outgoingRuleName, 2000);
		mySendKeys(By.xpath("//*[@id='min']") , outgoingRuleFrom, 2000);
		mySendKeys(By.xpath("//*[@id='max']") , outgoingRuleTo	, 2000);
		mySendKeys(By.xpath("//*[@id='trunkTBL']/table/tbody/tr[8]/td/table/tbody/tr/td[2]/input")		 , outgoingRulePrefix  , 2000);
		
		// Submit page and verify
		testFuncs.myDebugPrinting("Submit page and verify", enumsClass.logModes.MINOR);
		driver.switchTo().defaultContent();
		myClick(By.xpath("//*[@id='submit_img']"), 7000);
	    driver.switchTo().frame(1);
		searchStr(outgoingRuleName);
		driver.switchTo().defaultContent();	
	}

	/**
	* Delete an existing GW
	* @param newGwName	  	  - New GW name
	* @param newGwDescription - New GW description
	*/
	private void deleteGW(String newGwName, String newGwDescription) {
		
		// Get idx for delete
		testFuncs.myDebugPrinting("Get idx for delete", enumsClass.logModes.MINOR);
		int idx = getGwIdx(newGwName);
		myClick(By.xpath("//*[@id='trunkTBL']/table[2]/tbody/tr[" + idx + "]/td[7]/a"), 5000);		
		verifyStrByXpath(driver, "//*[@id='jqi_state_state0']/div[1]/table/tbody/tr[1]/th", "Delete Gateway");
		verifyStrByXpath(driver, "//*[@id='promt_div_id']"								  , "Are you sure you want to delete the gateway - " + newGwName + " ?");
		myClick(By.xpath("//*[@id='jqi_state0_buttonDelete']"), 10000);
			
		// Verify delete
		testFuncs.myDebugPrinting("Verify delete of <" + newGwName + ">", enumsClass.logModes.MINOR);
		String bodyText     = driver.findElement(By.tagName("body")).getText();
		testFuncs.myAssertTrue("Delete fails <" + newGwName      + "> is still detected !!"  , !bodyText.contains(newGwName));
		testFuncs.myAssertTrue("Delete fails <" + newGwDescription + "> is still detected !!", !bodyText.contains(newGwDescription));
	}

	/**
	* Edit an existing GW
	* @param oldGwName	  	  - Old GW name
	* @param newGwName	  	  - New GW name
	* @param newGwIP 		  - New GW ip
	* @param newPort		  - New GW port
	* @param newGwDescription - New GW description
	*/
	private void editGW(String oldGwName, String newGwName, String newGwIP, String newPort, String newGwDescription) {
		
		// Get idx for edit
		testFuncs.myDebugPrinting("Get idx for edit", enumsClass.logModes.MINOR);
		int idx = getGwIdx(oldGwName);
		myClick(By.xpath("//*[@id='trunkTBL']/table[2]/tbody/tr[" + idx + "]/td[6]/a"), 5000);
		verifyStrByXpath(driver, "//*[@id='trunkTBL']/table/tbody/tr[1]/td/span", "Modify Gateway - " + oldGwName);
		
		// Fill data
		testFuncs.myDebugPrinting("Fill data", enumsClass.logModes.MINOR);
		mySendKeys(By.xpath("//*[@id='name']")	 	 , newGwName	   , 2000);
		mySendKeys(By.xpath("//*[@id='ip']")		 , newGwIP  	   , 2000);
		mySendKeys(By.xpath("//*[@id='port']")	 	 , newPort		   , 2000);
		mySendKeys(By.xpath("//*[@id='description']"), newGwDescription, 2000);
		
		// Submit page and verify
		testFuncs.myDebugPrinting("Submit page and verify", enumsClass.logModes.MINOR);
		driver.switchTo().defaultContent();
		myClick(By.xpath("//*[@id='submit_img']"), 7000);
	    driver.switchTo().frame(1);
		searchStr(newGwName);
		driver.switchTo().defaultContent();	
	}
	
	/**
	* Get an index for a record
	* @param recName - Record name
	* @throws IOException 
	*/
	private int getGwIdx(String recName) {
		
		// Get idx
		testFuncs.myDebugPrinting("Get idx", enumsClass.logModes.MINOR); 
	    driver.switchTo().frame(1);
		for (int recIdx = 2; ; ++recIdx) {
				
			testFuncs.myDebugPrinting("recIdx - " 	 + recIdx   , enumsClass.logModes.DEBUG);
		
			// Check if xpath is displayed, if not return error
			try {
				
			   driver.findElement(By.xpath("//*[@id='trunkTBL']/table[2]/tbody/tr[" + recIdx + "]/td[2]"));
			} catch (NoSuchElementException e) {
				
				testFuncs.myDebugPrinting("//*[@id='trunkTBL']/table[2]/tbody/tr[" + recIdx + "]/td[2]", enumsClass.logModes.DEBUG);
				testFuncs.myFail("Record was not detected !!");
			}

			// Get record name
			String tempRecord = driver.findElement(By.xpath("//*[@id='trunkTBL']/table[2]/tbody/tr[" + recIdx + "]/td[2]")).getText();
			
			// If record was detected, return the current recIdx
			if (tempRecord.contains(recName)) {
				
				testFuncs.myDebugPrinting(recName + " was detected !! " + recIdx + " is returned", enumsClass.logModes.DEBUG);
				return recIdx; 
			}
		}	  
	}

	/**
	* Create a new GW
	* @param gwName	- New GW name
	* @param gwIp	- New GW ip address
	* @param gwPort - New GW port
	* @param gwDesc	- New GW description
	*/
	private void createGW(String gwName, String gwIp, String gwPort, String gwDesc) {
		
		// Enter Add-GW number menu
		testFuncs.myDebugPrinting("Enter Add-GW number menu", enumsClass.logModes.MINOR);
	    driver.switchTo().frame(1);
		myClick(By.xpath("//*[@id='trunkTBL']/table[1]/tbody/tr/td/a"), 5000);
		verifyStrByXpath(driver, "//*[@id='trunkTBL']/table/tbody/tr[1]/td", "Add New Gateway");
		
		// Fill data
		testFuncs.myDebugPrinting("Fill data", enumsClass.logModes.MINOR);
		mySendKeys(By.xpath("//*[@id='name']")	 	 , gwName, 2000);
		mySendKeys(By.xpath("//*[@id='ip']")		 , gwIp  , 2000);
		mySendKeys(By.xpath("//*[@id='port']")	 	 , gwPort, 2000);
		mySendKeys(By.xpath("//*[@id='description']"), gwDesc, 2000);
		
		// Submit page and verify
		testFuncs.myDebugPrinting("Submit page and verify", enumsClass.logModes.MINOR);
		driver.switchTo().defaultContent();
		myClick(By.xpath("//*[@id='submit_img']"), 7000);
	    driver.switchTo().frame(1);
		searchStr(gwName);
		driver.switchTo().defaultContent();
	}

	/**
	* Edit an existing Fax-Out user
	* @param faxOutNumber	  	  - Old Fax Out phone number
	* @param newfaxoutNumber	  - New Fax Out phone number
	* @param newfaxOutDisplayName - New Fax Out display name
	* @param newfaxOutEmail		  - New Fax Out email address
	* @param newfaxOutFaxId		  - New Fax Out Fax-Id
	*/
	private void editFaxOutUser(String faxOutNumber, String newfaxoutNumber, String newfaxOutDisplayName, String newfaxOutEmail, String newfaxOutFaxId) {
		
		// Get idx for edit
		testFuncs.myDebugPrinting("Get idx for edit", enumsClass.logModes.MINOR);
		int idx = getIdx(faxOutNumber);
		myClick(By.xpath("//*[@id='results']/tbody/tr[" + idx + "]/td[5]/a/img"), 5000);
		verifyStrByXpath(driver, "//*[@id='trunkTBL']/table/tbody/tr[1]/td/span", "Modify User");

		// Fill data
		testFuncs.myDebugPrinting("Fill data", enumsClass.logModes.MINOR);
		mySendKeys(By.xpath("//*[@id='number']")	 , newfaxoutNumber	   , 2000);
		mySendKeys(By.xpath("//*[@id='email']")		 , newfaxOutDisplayName, 2000);
		mySendKeys(By.xpath("//*[@id='displayname']"), newfaxOutEmail	   , 2000);
		mySendKeys(By.xpath("//*[@id='faxname']")	 , newfaxOutFaxId	   , 2000);
				
		// Submit page and verify
		testFuncs.myDebugPrinting("Submit page and verify edit", enumsClass.logModes.MINOR);
		driver.switchTo().defaultContent();
		myClick(By.xpath("//*[@id='submit_img']"), 7000);	
		verifyRec(newfaxoutNumber);
	}
	
	/**
	* Delete a Fax-Out user
	* @param faxOutNumber	   - Fax Out phone number
	* @param faxOutDisplayName - Fax Out display name
	*/
	private void deleteFaxOutUser(String faxOutNumber, String faxOutDisplayName) {
		
		// Get idx for delete
		testFuncs.myDebugPrinting("Get idx for delete", enumsClass.logModes.MINOR);
		int idx = getIdx(faxOutNumber);
		myClick(By.xpath("//*[@id='results']/tbody/tr[" + idx + "]/td[6]/a/img"), 5000);	
		verifyStrByXpath(driver, "//*[@id='jqi_state_state0']/div[1]/table/tbody/tr[1]/th", "Delete User");
		verifyStrByXpath(driver, "//*[@id='promt_div_id']"								  , "Are you sure you want to delete the user: " + faxOutDisplayName + "?");
		myClick(By.xpath("//*[@id='jqi_state0_buttonDelete']"), 10000);
			
		// Verify delete
		testFuncs.myDebugPrinting("Verify delete of <" + faxOutNumber + ">", enumsClass.logModes.MINOR);
		String bodyText     = driver.findElement(By.tagName("body")).getText();
		testFuncs.myAssertTrue("Delete fails <" + faxOutNumber      + "> is still detected !!", !bodyText.contains(faxOutNumber));
		testFuncs.myAssertTrue("Delete fails <" + faxOutDisplayName + "> is still detected !!", !bodyText.contains(faxOutDisplayName));	
	}
	
	/**
	* Create a Fax-Out user
	* @param faxOutNumber	   - Fax Out phone number
	* @param faxOutEmail	   - Fax Out email address
	* @param faxOutDisplayName - Fax Out display name
	* @param faxOutFaxId	   - Fax Out Fax-Id
	*/
	private void createFaxOutUser(String faxOutNumber, String faxOutEmail, String faxOutDisplayName, String faxOutFaxId) {

		// Enter Add-Fax-Out number menu
		testFuncs.myDebugPrinting("Enter Add-Fax-Out number menu", enumsClass.logModes.MINOR);
	    driver.switchTo().frame(1);
		myClick(By.xpath("//*[@id='addBtn']"), 5000);
		verifyStrByXpath(driver, "//*[@id='trunkTBL']/table/tbody/tr[1]/td/span", "Add New User");
		
		// Fill data
		testFuncs.myDebugPrinting("Fill data", enumsClass.logModes.MINOR);
		mySendKeys(By.xpath("//*[@id='number']")	 , faxOutNumber	    , 2000);
		mySendKeys(By.xpath("//*[@id='displayname']"), faxOutDisplayName, 2000);
		mySendKeys(By.xpath("//*[@id='email']")	 	 , faxOutEmail		, 2000);
		mySendKeys(By.xpath("//*[@id='faxname']")	 , faxOutFaxId	    , 2000);
		
		// Submit page and verify
		testFuncs.myDebugPrinting("Submit page and verify", enumsClass.logModes.MINOR);
		driver.switchTo().defaultContent();
		myClick(By.xpath("//*[@id='submit_img']"), 7000);	
		verifyRec(faxOutNumber);
	}	
	
	/**
	* Edit an existing Fax-In user
	* @param faxInNumber	  	 - Old Fax in phone number
	* @param newfaxInNumber	  	 - New Fax in phone number
	* @param newfaxInEmail		 - New Fax In email address
	* @param newfaxInDisplayName - New Fax in display name
	* @param newfaxInFaxId		 - New Fax In Fax-Id
	*/
	private void editFaxInUser(String faxInNumber, String newfaxInNumber, String newfaxInEmail, String newfaxInDisplayName, String newfaxInFaxId) {
		
		// Get idx for edit
		testFuncs.myDebugPrinting("Get idx for edit", enumsClass.logModes.MINOR);
		int idx = getIdx(faxInNumber);
		myClick(By.xpath("//*[@id='results']/tbody/tr[" + idx + "]/td[6]/a/img"), 5000);
		verifyStrByXpath(driver, "//*[@id='trunkTBL']/table/tbody/tr[1]/td/span", "Modify Number : " + faxInNumber);

		// Fill data
		testFuncs.myDebugPrinting("Fill data", enumsClass.logModes.MINOR);
		mySendKeys(By.xpath("//*[@id='NUMBER']")	 ,newfaxInNumber	 , 2000);
		mySendKeys(By.xpath("//*[@id='DISPLAYNAME']"),newfaxInDisplayName, 2000);
		mySendKeys(By.xpath("//*[@id='EMAIL']")	 	 ,newfaxInEmail 	 , 2000);
		mySendKeys(By.xpath("//*[@id='FAXNAME']")	 ,newfaxInFaxId	     , 2000);
				
		// Submit page and verify
		testFuncs.myDebugPrinting("Submit page and verify edit", enumsClass.logModes.MINOR);
		driver.switchTo().defaultContent();
		myClick(By.xpath("//*[@id='submit_img']"), 7000);	
		verifyRec(newfaxInNumber);
	}

	/**
	* Get an index for a record
	* @param recName - Record name
	* @throws IOException 
	*/
	private int getIdx(String recName) {
		
		// Get idx
		testFuncs.myDebugPrinting("Get idx", enumsClass.logModes.MINOR); 
	    int recNum = Integer.valueOf(driver.findElement(By.xpath("//*[@id='shownumber']/b[2]")).getText());
		testFuncs.myDebugPrinting("recNum - " + recNum, enumsClass.logModes.DEBUG);
		int loopNum = (recNum / 20) + 1;
		testFuncs.myDebugPrinting("loopNum - " + loopNum, enumsClass.logModes.DEBUG);
		
		int recIdx = 1, currLoopNum = 1, allRecIdx = 1;
		for (allRecIdx = 1; allRecIdx < recNum; ++allRecIdx) {
				
			testFuncs.myDebugPrinting("allRecIdx - " + allRecIdx, enumsClass.logModes.DEBUG);
			testFuncs.myDebugPrinting("recIdx - " 	 + recIdx   , enumsClass.logModes.DEBUG);
		
			// Get record data
			String tempRecord = driver.findElement(By.xpath("//*[@id='results']/tbody/tr[" + recIdx + "]/td[1]")).getText();
			
			// If record was detected, return the current recIdx
			if (tempRecord.contains(recName)) {
				
				testFuncs.myDebugPrinting(recName + " was detected !! " + recIdx + " is returned", enumsClass.logModes.DEBUG);
				return recIdx; 
			} 
			
			// Record was not found, increase counters
			else {
		
				// If the current record is the last record in the menu, reset recIdx for next menu
				if (recIdx ==  20) {
					
					testFuncs.myDebugPrinting("reset recIdx", enumsClass.logModes.DEBUG);
					recIdx = 1;
					
					// If the current menu is not the last menu, move to next menu
					if (currLoopNum < loopNum) {
						
						testFuncs.myDebugPrinting("move to next menu (currLoopNum - " + currLoopNum + " loopNum - " + loopNum + ")", enumsClass.logModes.DEBUG);
						myClick(By.xpath("//*[@id='navigator']/a[3]"), 5000);
						currLoopNum++;
					}	
				} 
				
				// The current record is not the last, therefore increase recIdx.
				else {
					
					testFuncs.myDebugPrinting("increase recIdx", enumsClass.logModes.DEBUG);
					recIdx++;
				}
			}
		}
		
		// If allRecIdx == recNum and no values was returned, it means that the record was not detected
		if (allRecIdx == recNum) {
			
			testFuncs.myFail(recName + " was not detected !!");
		}
	  
		return -1; 
	}
	
	/**
	* Check / uncheck Archive checkbox
	*/
	private void checkArchiveCheckbox(boolean isUncheckArchive) {
		
		testFuncs.myDebugPrinting(isUncheckArchive?"true":"false", enumsClass.logModes.MINOR);
	    driver.switchTo().frame(1);
	    if (isUncheckArchive) {
	    	
	    	if (driver.findElement(By.xpath("//*[@id='archive']")).isSelected()) {
			
	    		testFuncs.myDebugPrinting("Checkbox was checked", enumsClass.logModes.MINOR);
	    		myClick(By.xpath("//*[@id='archive']"), 2000);
	    	}
		} else {
			
	    	if (!driver.findElement(By.xpath("//*[@id='archive']")).isSelected()) {
				
	    		testFuncs.myDebugPrinting("Checkbox was un-checked", enumsClass.logModes.MINOR);
	    		myClick(By.xpath("//*[@id='archive']"), 2000);
	    	}
		}
	}
	
	/**
	* Delete a Fax-In user
	* @param faxInNumber	  - Fax in phone number
	* @param faxInDisplayName - Fax in display name
	*/
	private void deleteFaxInUser(String faxInNumber, String faxInDisplayName) {
		
		// Get idx for delete
		testFuncs.myDebugPrinting("Get idx for delete", enumsClass.logModes.MINOR);
		int idx = getIdx(faxInNumber);
		myClick(By.xpath("//*[@id='results']/tbody/tr[" + idx + "]/td[7]/a/img"), 5000);
		verifyStrByXpath(driver, "//*[@id='jqi_state_state0']/div[1]/table/tbody/tr[1]/th", "Delete Number");
		verifyStrByXpath(driver, "//*[@id='promt_div_id']"								  , "Are you sure you want to delete the number: " + faxInNumber + "?");
		myClick(By.xpath("//*[@id='jqi_state0_buttonDelete']"), 10000);
	
		// Verify delete
		testFuncs.myDebugPrinting("Verify delete", enumsClass.logModes.MINOR);
		String bodyText     = driver.findElement(By.tagName("body")).getText();
		testFuncs.myAssertTrue("Delete fails <" + faxInNumber      + "> is still detected !!", !bodyText.contains(faxInNumber));
		testFuncs.myAssertTrue("Delete fails <" + faxInDisplayName + "> is still detected !!", !bodyText.contains(faxInDisplayName));	
	}

	/**
	* Create a Fax-In user
	* @param faxInNumber	  - Fax in phone number
	* @param faxInDisplayName - Fax in display name
	* @param faxInEmail		  - Fax In email address
	* @param faxInFaxId		  - Fax In Fax-Id
	*/
	private void createFaxInUser(String faxInNumber, String faxInDisplayName, String faxInEmail, String faxInFaxId) {

		// Enter Add-Fax-In number menu
		testFuncs.myDebugPrinting("Enter Add-Fax-In number menu", enumsClass.logModes.MINOR);
	    driver.switchTo().frame(1);
		myClick(By.xpath("//*[@id='addBtn']"), 5000);
		verifyStrByXpath(driver, "//*[@id='trunkTBL']/table/tbody/tr[1]/td/span", "Add Number");
		
		// Fill data
		testFuncs.myDebugPrinting("Fill data", enumsClass.logModes.MINOR);
		mySendKeys(By.xpath("//*[@id='NUMBER']")	 , faxInNumber	    , 2000);
		mySendKeys(By.xpath("//*[@id='DISPLAYNAME']"), faxInEmail		, 2000);
		mySendKeys(By.xpath("//*[@id='EMAIL']")	 	 , faxInDisplayName , 2000);
		mySendKeys(By.xpath("//*[@id='FAXNAME']")	 , faxInFaxId	    , 2000);
		
		// Submit page and verify
		testFuncs.myDebugPrinting("Submit page and verify", enumsClass.logModes.MINOR);
		driver.switchTo().defaultContent();
		myClick(By.xpath("//*[@id='submit_img']"), 7000);	
		verifyRec(faxInNumber);
	}
	
	private void verifyRec(String record) {
		
	    driver.switchTo().frame(1);   
	    int recNum = Integer.valueOf(driver.findElement(By.xpath("//*[@id='shownumber']/b[2]")).getText());
		testFuncs.myDebugPrinting("recNum - " + recNum, enumsClass.logModes.DEBUG);
		int loopNum = (recNum / 20) + 1;
		testFuncs.myDebugPrinting("loopNum - " + loopNum, enumsClass.logModes.DEBUG);
		 
		// Loop on all menus
		String bodyText; 
		int i;
		for (i = 0; i < loopNum; ++i) {
			
		    bodyText     = driver.findElement(By.tagName("body")).getText();     
		    if (bodyText.contains(record)) { 
		    	
				testFuncs.myDebugPrinting(record + " was detected !!", enumsClass.logModes.MINOR);
				break;
			    
		    } else {
		    	
		    	if (i < (loopNum - 1)) {
		    		myClick(By.xpath("//*[@id='navigator']/a[3]"), 5000);
		    	}
		    }		
		}
		if (i == loopNum) {
			
			testFuncs.myFail(record + " was not detcetd !!");
		}
	}

	/**
	* Check / uncheck Display remote Id
	*/
	private void checkDisplayRemoteId(boolean isUncheckDisplayRemoteId) {
		
		testFuncs.myDebugPrinting(isUncheckDisplayRemoteId?"true":"false", enumsClass.logModes.MINOR);
	    driver.switchTo().frame(1);
	    if (isUncheckDisplayRemoteId) {
	    	
	    	if (driver.findElement(By.xpath("//*[@id='remote']")).isSelected()) {
			
	    		testFuncs.myDebugPrinting("Checkbox was checked", enumsClass.logModes.MINOR);
	    		myClick(By.xpath("//*[@id='remote']"), 2000);
	    	}
		} else {
			
	    	if (!driver.findElement(By.xpath("//*[@id='remote']")).isSelected()) {
				
	    		testFuncs.myDebugPrinting("Checkbox was un-checked", enumsClass.logModes.MINOR);
	    		myClick(By.xpath("//*[@id='remote']"), 2000);
	    	}
		}
	}
	
	/**
	* Check / uncheck Add symbols checkbox
	*/	
	private void checkAddSymbols(boolean isAddSymbolsCheckbox) {
		
		testFuncs.myDebugPrinting(isAddSymbolsCheckbox?"true":"false", enumsClass.logModes.MINOR);
	    driver.switchTo().frame(1);
	    if (isAddSymbolsCheckbox) {
	    	
	    	if (!driver.findElement(By.xpath("//*[@id='add_symbol']")).isSelected()) {
			
	    		testFuncs.myDebugPrinting("Checkbox was unchecked", enumsClass.logModes.MINOR);
	    		myClick(By.xpath("//*[@id='add_symbol']"), 2000);
	    	}
		} else {
			
	    	if (driver.findElement(By.xpath("//*[@id='add_symbol']")).isSelected()) {
				
	    		testFuncs.myDebugPrinting("Checkbox was checked", enumsClass.logModes.MINOR);
	    		myClick(By.xpath("//*[@id='add_symbol']"), 2000);
	    	}
		}
	}

	/**
	* Check / uncheck Email confirmation checkbox
	*/	
	private void checkEmailConfirmation(boolean isUncheckEmailConfCheckbox) {
		
		testFuncs.myDebugPrinting(isUncheckEmailConfCheckbox?"true":"false", enumsClass.logModes.MINOR);
	    driver.switchTo().frame(1);
	    if (isUncheckEmailConfCheckbox) {
	    	
	    	if (driver.findElement(By.xpath("//*[@id='send_prev']")).isSelected()) {
			
	    		testFuncs.myDebugPrinting("Checkbox was checked", enumsClass.logModes.MINOR);
	    		myClick(By.xpath("//*[@id='send_prev']"), 2000);
	    	}
		} else {
			
	    	if (!driver.findElement(By.xpath("//*[@id='send_prev']")).isSelected()) {
				
	    		testFuncs.myDebugPrinting("Checkbox was un-checked", enumsClass.logModes.MINOR);
	    		myClick(By.xpath("//*[@id='send_prev']"), 2000);
	    	}
		}
	}

	/**
	* Select Fax-In Fax-Id method
	*/
	private void setFaxInFaxId(String option) {

		testFuncs.myDebugPrinting("setFaxInFaxId option -  " + option, enumsClass.logModes.MINOR);
	    driver.switchTo().frame(1);
	    new Select (driver.findElement(By.xpath("//*[@id='method_id']"))).selectByVisibleText(option);
	    testFuncs.myWait(10000);
	}
	
	/**
	* Select Fax-Out Fax-Id method
	*/
	private void setFaxOutFaxId(String option) {
		
		testFuncs.myDebugPrinting("setFaxOutFaxId option -  " + option, enumsClass.logModes.MINOR);
	    driver.switchTo().frame(1);
	    new Select (driver.findElement(By.xpath("//*[@id='method_id']"))).selectByVisibleText(option);
	    testFuncs.myWait(3000);
	}

	/**
	* Check / Uncheck Add-coverpage checkbox at Fax-Out-Settings
	*/
	private void checkCoverPage(boolean isCBChecked) {
		
		testFuncs.myDebugPrinting(isCBChecked?"true":"false", enumsClass.logModes.MINOR);
	    driver.switchTo().frame(1);
	    if (isCBChecked) {
	    	
	    	if (driver.findElement(By.xpath("//*[@id='add_cp']")).isSelected()) {
			
	    		testFuncs.myDebugPrinting("Checkbox was checked", enumsClass.logModes.MINOR);
	    		myClick(By.xpath("//*[@id='add_cp']"), 2000);
	    	}
		} else {
			
	    	if (!driver.findElement(By.xpath("//*[@id='add_cp']")).isSelected()) {
				
	    		testFuncs.myDebugPrinting("Checkbox was un-checked", enumsClass.logModes.MINOR);
	    		myClick(By.xpath("//*[@id='add_cp']"), 2000);
	    	}
		}
	}

	/**
	* Submit page
	* @return driver - driver object (Failure if usedBrowser is not a valid browser name)
	*/
	private void submitPage(WebDriver driver) {
		
		driver.switchTo().defaultContent();
		myClick(By.xpath("//*[@id='submit_img']"), 7000);
	    driver.switchTo().frame(1);
		verifyStrByXpath(driver, "//*[@id='ui-id-1']", "Restart Required");		
		myClick(By.xpath("//*[@id='restartBtn']"), 7000);
		verifyStrByXpath(driver, "//*[@id='status_Fax_Receiver']", "Restarting service. Please wait ...");
		waitTillString(driver, "The service successfully restarted", 60000);
		myClick(By.xpath("//*[@id='okBtn']"), 2000);
		driver.switchTo().defaultContent();
	}

	/**
	* Set a driver according to a given browser name
	* @param  usedBrowser - given browser name (Chrome, FireFox or IE)
	* @return driver      - driver object (Failure if usedBrowser is not a valid browser name)
	*/
	public WebDriver defineUsedBrowser() {
		
		ChromeOptions options = new ChromeOptions();
	    System.setProperty("webdriver.chrome.driver", testVars.getchromeDrvPath());
		options.addArguments("--start-maximized");
		return new ChromeDriver(options);
	}

	/**
	*  login method
	*  @param driver   - given driver for make all tasks
	*  @param username - given string for system
	*  @param password - given password for the system
	*  @param mainStr  - given string for verify good access
	*/
	private void login(WebDriver driver, String username, String password, String mainStr) {
		  
		String title = driver.getTitle();
		testFuncs.myDebugPrinting("title - "             + title            , enumsClass.logModes.MINOR);  	 
		testFuncs.myDebugPrinting("testVars.getUrl() - " + testVars.getUrl(),enumsClass.logModes.MINOR);
		driver.get("http://" + testVars.getUrl());		
		testFuncs.myWait(3000);
		searchStr(testVars.getMainPageStr());  
		testFuncs.myDebugPrinting("username - " + username ,enumsClass.logModes.MINOR);
		testFuncs.myDebugPrinting("password - " + password ,enumsClass.logModes.MINOR);
		mySendKeys(By.xpath("//*[@id='Table5']/tbody/tr[3]/td[2]/input"), username, 1500);
		mySendKeys(By.xpath("//*[@id='Table5']/tbody/tr[4]/td[2]/input"), password, 1500);    	    
		myClick(By.xpath("//*[@id='Submit1']"), 3000);
	      
		// Verify good access
		String txt = driver.findElement(By.tagName("body")).getText();
		testFuncs.myAssertTrue("Login fails ! (mainStr - " + mainStr + " was not detected !!)\ntxt - " + txt, txt.contains(mainStr));  
	}

	/**
	*  Click on given element by given xpath and waits a given timeout
	*  @param byType  - given By element (By xpath, name or id)
	*  @param timeout - given timeout value (Integer)
	*/  
	public void myClick(By byType, int timeout) {
		  
      driver.findElement(byType).click();
	  testFuncs.myWait(timeout);  
	}
	  
	/**
	*  Send a string to a given element using given parameters
	*  @param byType  - given By element (By xpath, name or id)
	*  @param currUsr - given string to send
	*  @param timeout - given timeout (Integer)
	*/  
	private void mySendKeys(By byType, String currUsr, int timeOut) {
		  
	  driver.findElement(byType).clear();
	  testFuncs.myWait(500);  
	  driver.findElement(byType).sendKeys(currUsr);
	  testFuncs.myWait(timeOut);  
	}
	  
	/**
	*  Verify string in page based on read the whole page
	*  @param strName - given string for detect
	*/  
	private void searchStr(String strName) {
		  
	  String bodyText     = driver.findElement(By.tagName("body")).getText();
	  if (bodyText.contains(strName)) {
		  
		  testFuncs.myDebugPrinting("<" + strName + "> was detected !!",  enumsClass.logModes.MINOR);
	  } else {
		  
		  testFuncs.myFail("<" + strName + "> was not detected !! \nbodyText - " + bodyText);
	  }  
	}
	  
	/**
	*  Enter a menu
	*  @param driver       - given driver for make all tasks
	*  @param menuName 	   - given menu name for the paths function
	*  @param verifyHeader - string for verify that enter the menu succeeded
	*/
	public void enterMenu(WebDriver	driver, menuNames menuName, String verifyHeader) {
		  
		testFuncs.myDebugPrinting("enterMenu  - " +  menuName, enumsClass.logModes.NORMAL);
		String paths[] = getPaths(menuName);
		int length = paths.length;
	    driver.switchTo().defaultContent();
		for (int i = 0; i < length; ++i) {
		  
			  if (paths[i].isEmpty()) {
				  
				  break;
			  }
			  testFuncs.myDebugPrinting("paths[" + i + "] - " +  paths[i], enumsClass.logModes.MINOR);
			  markElemet(driver, driver.findElement(By.xpath(paths[i])));
			  testFuncs.myWait(500);
		      driver.findElement(By.xpath(paths[i])).click();
		      testFuncs.myWait(3000);		  
		}
	    driver.switchTo().frame(1);
		String title = driver.findElement(By.tagName("body")).getText();
		if (!verifyHeader.isEmpty()) {	
			
			if (!title.contains(verifyHeader)) {
				
				driver.quit();
				testFuncs.myFail("Title was not found (" + title + ")");
			}
		}  
		driver.switchTo().defaultContent();
	    testFuncs.myWait(5000);
		testFuncs.myDebugPrinting("enterMenu  - " +  menuName + " ended successfully !!", enumsClass.logModes.NORMAL);
    }
	
	/**
	*  Verify string  method by xpath
	*  @param driver    A given driver
	*  @param elemXpath A given element xpath name
	*  @param strName   A given string for detect
	*/
	public void verifyStrByXpath(WebDriver 	driver, String elemXpath, String strName) {
		 
		markElemet(driver, driver.findElement(By.xpath(elemXpath)));
		String txt =driver.findElement(By.xpath(elemXpath)).getText();
		if (!txt.contains(strName)) {				
			
			testFuncs.myFail("The string <" + strName + "> was not detected !! (current string - <" + txt +">");
			driver.quit();	
		}	  
	}
	  
	/**
	*  Highlight given element
	*  @param driver  - given driver
	*  @param element - given element
	*/
	public void markElemet(WebDriver 	driver, WebElement element) {
		
		try {
			
			((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid yellow'", element);
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e1) {}
	   ((JavascriptExecutor)driver).executeScript("arguments[0].style.border=''", element);  
	}
	
	/**
	*  Wait till the given string not displayed on the screen
	*  @param driver     - given driver
	*  @param string     - given string that indicate if we should stop the loop
	*  @param maxTimeout - given max timeout value at ms values
	*/ 
	public void waitTillString(WebDriver driver, String string, int maxValue) {
		
		String bodyText = "";
		int idx         = 0;
		while (true) {
			
	      bodyText = driver.findElement(By.tagName("body")).getText();
	      if (!bodyText.contains(string)) {
	    	  
	    	  if (idx >= maxValue) {	 
	    	  
	    		  driver.quit();
	    		  testFuncs.myFail("String <" + string +"> was not detecetd after " + (maxValue/1000) + " seconds !");
	    		  break;
	    	  }  else {
	    		  
	        	  idx += 1000;
		    	  testFuncs.myDebugPrinting(string + " is still not detected after " + (idx/1000) + " seconds", enumsClass.logModes.MINOR);
		    	  testFuncs.myWait(1000);	  
	    	  }
	      } else {
	    	  
	    	  testFuncs.myDebugPrinting(string + " was detected !!", enumsClass.logModes.MINOR);
	    	  break;
	      }
		}
	}
}