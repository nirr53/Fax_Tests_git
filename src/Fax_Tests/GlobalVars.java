package Fax_Tests;

/**
* This class holds all the data which is used by the tests
* @author Nir Klieman
* @version 1.00
*/

public class GlobalVars {
	
	/**
	*  // General data 
	*  faxIp           - IP of the system
	*  faxPort         - Port for create the users 		          (I.e. 8081).
	*  faxServerName   - Fax domain name 			              (I.e fax.server).
	*  url  		   - Default url for access the system        (created by IP).
	*  defEmailAddress - Default email address for the fax-server (I.e kairat.zimat@audiocodes.com)
	*  downloadsPath   - Downloads path
	*  
	*  // Login data
	*  mainPageStr    - Main page welcome string                  
	*  sysUsername    - Default username for access the system.   (I.e Admin)
	*  sysPassword    - Default password for access the system.	  (I.e Admin)
	*  sysMainStr     - Default string in the welcome page.
	*  chromeDrvPath  - Chrome driver path.
	*  browsersList	  - Array for the used browsers				  (I.e Chrome)
	*  
	*  // Tshark data
	*  defTsharkPath    - Tshark full path (I.e "C:\\Program Files\\Wireshark\\tshark.exe"_
	*  defTsharkNAport  - Tshark NA port   (I.e 5067)
	*  
	*  // Logger data
	*  <MAJOR = 0, NORMAL, MINOR, DEBUG>  - int variables that display the log line in different levels.
	*
	*  // Fax Deposit data
	*  faxHeaders 	    - An array of strings that represent the names of fax parts.
	*  faxFailureHeader - The name of failure-deposit file. When detected at Error directory, the deposit process stops and return failure.
	*  rootDir			- The directory from which we start building the different folder paths.  
	*  
	*  // Fax OCR data
	*  faxOCRPath		- Default path for the OCR converter
	*/
	// General data
	private  String faxIp            = "10.21.10.45";
	private  String faxPort          = "8090";
	private  String faxServerName	 = "fax.server";
    private  String url  		     = faxIp + ":" + faxPort + "/";   
	private  String defEmailAddress	 = "kairat.zimat@audiocodes.com";
	private  String downloadsPath    = "";

	// Login to web data
    private  String mainPageStr		 = "Application Web Administration";
    private  String sysUsername      = "Admin";
	private  String sysPassword      = "Admin";
	private  String sysMainStr       = "Welcome To Application Web Administration";
	private  String chromeDrvPath    = "C:\\Users\\nirk\\Desktop\\Selenium\\chromedriver_win32_4\\chromedriver.exe";
	private  Object[][] browsersList = {{"Chrome"}};
	
	// Tshark data
	private String defTsharkPath	 = "C:\\Program Files\\Wireshark\\tshark.exe";
	private String defTsharkNAport	 = "5067";	
	
	// Logger levels
	int MAJOR  = 0;
	int NORMAL = 1;
	int MINOR  = 2;
	int DEBUG  = 3;
	
	// Fax Deposit data
	private String[] faxHeaders 	  = {"Send_Fax_Status", "Fax_Message_Body", "Send_Fax_Result"};
	private String   faxFailureHeader = "Failed_to_send_fax";
	private String   rootDir 		  = "";
	
	// Fax OCR data
	private String	 faxOCRPath		  = "C:\\Users\\nirk\\Desktop\\myEclipseProjects\\Fax_Tests\\verypdf.exe";
	
    /**
    *  Default constructor for provide interface
    */
    public GlobalVars() {
    	
    	rootDir 	  = System.getProperty("user.dir")  + "\\Emails_directory";
    	downloadsPath = "C:\\Users\\" + System.getProperty("user.name") + "\\Downloads";

	}
    
	/**
    *  Default method for return the url variable
    *  @return url of the system
    */
	public String getUrl()         {  return url;             }
	
    /**
    *  Default method for return the downloads path
    *  @return version
    */
	public String getDownloadsPath() { return  downloadsPath;  }
	
	/**
    *  Default method for return the mainPageStr variable
    *  @return main string of the system
    */
	public String getMainPageStr() { return mainPageStr;      }
	
    /**
    *  Default method for return the username variable
    *  @return username of the system for Admin
    */
	public String getSysUsername() { return sysUsername; 	   }
	
    /**
    *  Default method for return the password variable
    *  @return password of the system for Admin
    */
	public String getSysPassword() { return sysPassword;      }
	
    /**
    *  Default method for return the main-str variable
    *  @return sysStr of the system
    */
	public String getSysMainStr() { return sysMainStr;        }
	
    /**
    *  Default method for return the Chrome driver path
    *  @return chromeDrvPath of the system
    */
	public String getchromeDrvPath() { 	return chromeDrvPath; }
	
    /**
    *  Default method for return the default IP of the Fax server
    *  @return faxIpAdress
    */
	public String getFaxServerIpAddress() { return faxIp; 			}

    /**
    *  Default method for return the default IP of the Fax server
    *  @return port
    */
	public String getFaxServerIpPort()  { return faxPort; 		    }
	
    /**
    *  Default method for return the default Fax-server name of the Fax server
    *  @return faxServerName
    */
	public String getServerName()  { return faxServerName; 		    }

    /**
    *  Default method for return the used browsers in the current test
    *  @return browsersList
    */
	public Object[][] getBrowsers() 	 { return browsersList;   }
	
    /**
    *  Default method for return the names of the different fax-body parts
    *  @return faxHeaders[]
    */
	public String[] getFaxHeaders()     {	return faxHeaders;				}	
	
    /**
    *  Default method for return the default failure header
    *  @return port
    */
	public String getFaxFailureHeader() {	return faxFailureHeader;		}

    /**
    *  Default method for return the output source path
    *  @return path of the output based on local directory + <output> + server name
    */
	public String getOutputDirPath() {

		return (rootDir + "\\output_" + getServerName() + "\\");
	}
	
    /**
    *  Default method for return the root directory
    *  @return path of the output based on local directory + <output> + server name
    */
	public String getRootDir() { return rootDir; 							}

    /**
    *  Default method for return the Fax-OCR path
    *  @return path of the OCR converter
    */
	public String getOCRPath() { return faxOCRPath;						}
	
    /**
    *  Default method for return the default-email-address
    *  @return default email-address of the dax-server
    */
	public String getDefEmail() {return defEmailAddress;					}
	
    /**
    *  Default method for return the default-Tshark path
    *  @return default Tshark-path
    */
	public String getTsharkPath() {return defTsharkPath;					}

    /**
    *  Default method for return the default-Tshark NA port
    *  @return default Tshark-NA-port
    */
	public String getTsharkNaPort() { return defTsharkNAport; 				}
}