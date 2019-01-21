package Fax_Tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import Fax_Tests.enumsClass.logModes;
import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
* This class holds all the functions which been used by the tests
* @author Nir Klieman
* @version 1.00
*/
public class GlobalFuncs {
	
	  GlobalVars 		   testVars;
	  @SuppressWarnings("unused")
	  private StringBuffer verificationErrors = new StringBuffer();
	  private static final Logger logger = LogManager.getLogger();

	  /**
	  *  Default constructor
	  */
	  public GlobalFuncs(GlobalVars testVars) {
		  
		  this.testVars = testVars;
	  }
	  
	  /**
	  *  Verify string in page based on read the whole page
	  *  @param driver  - given driver
	  *  @param strName - given string for detect
	  */
	  public void searchStr(WebDriver 	driver, String strName) {
		  
		  String bodyText     = driver.findElement(By.tagName("body")).getText();
		  if (bodyText.contains(strName)) {
			  
			  myDebugPrinting("<" + strName + "> was detected !!",  enumsClass.logModes.MINOR);
		  } else {
			  
			  myFail("<" + strName + "> was not detected !! \nbodyText - " + bodyText);
		  }
	  }
	  
	  /**
	  *  Find files in a given directory by a given prefix
	  *  @param dir - given directory path
	  *  @param prefix - a given prefix
	  *  @return true if files were found
	  */
	  public boolean findFilesByGivenPrefix(String dir, String prefix) {
	    	
			myDebugPrinting("dir    - " + dir   ,  enumsClass.logModes.MINOR);
			myDebugPrinting("prefix - " + prefix,  enumsClass.logModes.MINOR);
	    	File[] dirFiles = new File(dir).listFiles();
	    	int filesNum = dirFiles.length;
	    	for (int i = 0; i < filesNum; i++) {
	    		
	    	    if (dirFiles[i].getName().startsWith(prefix, 0)) {
	    			
	    	    	myDebugPrinting("Find a file ! (" + dirFiles[i].getName() + ")",  enumsClass.logModes.MINOR);
	    	        return true;
	    	    }
	    	}
	    	
	    	return false;
	  }
	  
	  /**
	  *  Delete all files in directory by given prefix
	  *  @param dir    - given directory path
	  *  @param prefix - given prefix
	  */
	  public void deleteFilesByPrefix(String dir, String prefix) {
	    	
		myDebugPrinting("dir    - " + dir   ,  enumsClass.logModes.MINOR);
    	File[] dirFiles = new File(dir).listFiles();
    	int filesNum = dirFiles.length;
    	for (int i = 0; i < filesNum; i++) {
    		
    		if (!prefix.isEmpty()) {
    			
    			myDebugPrinting("prefix - " + prefix,  enumsClass.logModes.DEBUG);
	    	    if (dirFiles[i].getName().startsWith(prefix, 0)) {
	    	    	
	    			myDebugPrinting("Delete file - " + dirFiles[i].getName(),  enumsClass.logModes.DEBUG);
	    	        new File(dir + "\\" + dirFiles[i].getName()).delete();
	    		    myWait(2000);    
	    	    }	    	    
    		} else {
    			
    			myDebugPrinting("prefix  is empty, delete all files on dir - " + dir,  enumsClass.logModes.DEBUG);		
    			myDebugPrinting("Delete file - " + dirFiles[i].getName(),  enumsClass.logModes.DEBUG);
    	        new File(dir + "\\" + dirFiles[i].getName()).delete();
    		    myWait(2000);	
    		}
    	}	
	    myWait(1000);
	  }
	  
	  /**
	  *  read file method
	  *  @param  path    - given path for file to read
	  *  @return content - string of readed file
	  */
	  String readFile(String path) {
		  
		    String content = null;
		    File file = new File(path);
		    FileReader reader = null;
		    try {
		    	
		        reader = new FileReader(file);
		        char[] chars = new char[(int) file.length()];
		        reader.read(chars);
		        content = new String(chars);
		        reader.close();
		    } catch (IOException e) {
		    } finally {
		    	
		        if(reader !=null) {
		        	
		        	try {
		        		
		        		reader.close();
		        	} catch (IOException e) {}
		        }
		    }
		    
			myWait(3000);
		    return content;
	  }
	  
	  /**
	  *  Verify xpath contains a string
	  *  @param driver   - given driver
	  *  @param elemName - given element xpath
	  *  @param strName  - given string for detect
	  */
	  public void verifyStrByXpathContains(WebDriver 	driver, String xpath, String strName) {
	  	  
		  if (driver.findElement(By.xpath(xpath)).getText().contains(strName)) {
		  } else {
			  
			  myFail (strName + " was not detected !!");
		  }
	  }
	  
	  /**
	  * Return the number of lines in given file. Used to calculate number of imports.
	  * @param filePath - given file path
	  * @return lines   - number of lines on given file
	  * @throws IOException 
	  */
	  public int readFileLines(String filePath, Boolean isHeader) throws IOException {
		
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		int lines = 0;
		while (reader.readLine() != null) {
			lines++;
		}
		reader.close();
		if (lines < 1) {
			myFail("The given file is empty -" + filePath);
		}
		myDebugPrinting("filePath - "  + filePath, enumsClass.logModes.MINOR);
		if (isHeader) {
			
			myDebugPrinting("isHeader - TRUE, lines - " + (lines - 1)   , enumsClass.logModes.MINOR);
			return (lines - 1);
		}
		
		return lines;
	  }
	 
	  /**
	  *  Print a given string to the console with default level of MAJOR
	  *  @param str - A given string to print
	  */
      public void myDebugPrinting(String str) {
			
		logger.info(" " + str);
	  }
      
	  /**
	  *  Print a given error string and declares the test as a myFailure
	  *  @param str - A given error string
	  */
      public void myFail(String str) {
			
		logger.error(str);
		fail(str);
	  }
	
	  /**
	  *  Sleep for a given time
	  *  @param sleepValue - given sleep factor
	  */
	  public void myWait(int sleepValue) {
			
	    try {
	    	
			TimeUnit.MILLISECONDS.sleep(sleepValue);		
		} catch (InterruptedException e1) {
		}	
	  }
	
	  /**
	  *  Create a unique Id based on current time
	  *  @return - unique id based on current time 
	  */
	  public String getId() {
		
	    // set id
	    DateFormat dateFormat = new SimpleDateFormat("HH_mm_ssdd");
	    Date date     = new Date();
	    String id     = dateFormat.format(date);
	    id = id.replaceAll("_", "");
		myDebugPrinting("Id is:" + id, enumsClass.logModes.MAJOR);
		
	    return id;
	  }	  
	  
	  /**
	  *  Print a given string to the console
	  *  @param str   - given string to print
	  *  @param level - given print level (MAJOR, NORMAL, MINOR, DEBUG)
	  */
	  public void myDebugPrinting(String str, logModes level) {
		    
		  logger.info(level.getLevel() + str);
	  }

	/**
	  *  Wrap assertTrue with logger
	  *  @param errorStr  - error message for display at the logger
	  *  @param condition - condition for mark if the assert succeeded or not
	  */
	  public void myAssertTrue(String errorStr, Boolean condition) {
		  
		  if (!condition) {
			  myFail(errorStr);  
		  }
	  }
	  	  
	  /**
	  *  Deposit a fax and check its arrival
	  *  @param faxHeaders  - array of the fax-parts names
	  *  @param dataMap     - Map of different variables.
	  */
	  public void depositFax(String[] faxHeaders, Map<String, String> dataMap) {
		
		myDebugPrinting( "Fax deposit started..", enumsClass.logModes.NORMAL);
		
		// Set default data
		int maxWaitTime = 1300;
		if (dataMap.containsKey("maxWaitTime")) {
			
			maxWaitTime = Integer.valueOf(dataMap.get("maxWaitTime"));
			myDebugPrinting("Non default max-wait-time is defined - " +  maxWaitTime, enumsClass.logModes.MINOR);		
		}
		int fileNumber = 5;
		if (dataMap.containsKey("fileNumber")) {
			
			fileNumber = Integer.valueOf(dataMap.get("fileNumber"));
			myDebugPrinting("Non default fileNumber is defined - " +  fileNumber, enumsClass.logModes.MINOR);		
		}
		int raiseError = 1;
		if (dataMap.containsKey("raiseError")) {
			
			raiseError = Integer.valueOf(dataMap.get("raiseError"));
			myDebugPrinting("Non default raiseError is defined - " +  raiseError, enumsClass.logModes.MINOR);		
		}
		int isMultipleTargets = 0;
		if (dataMap.containsKey("isMultipleTargets")) {
			
			isMultipleTargets = Integer.valueOf(dataMap.get("isMultipleTargets"));
			myDebugPrinting("Non default isMultipleTargets is defined - " +  isMultipleTargets, enumsClass.logModes.MINOR);		
		}
				
		// Get global data
		String emailsMainDir 	  = testVars.getRootDir();	
		String faxServerIpAddress = testVars.getFaxServerIpAddress();
		String faxServerIpPort    = testVars.getFaxServerIpPort();
		String faxFailed 		  = testVars.getFaxFailureHeader();	
		
		// Set files path
		String outputPath    = "";
		String inputPath     = emailsMainDir  + "\\input\\";
		String errorPath     = emailsMainDir  + "\\error\\";
		String convPath      = emailsMainDir  + "\\converted\\";
		String errorFile 	 = errorPath      + faxFailed + ".txt";
		String errorFileAtt  = emailsMainDir  + "\\error\\att\\" + faxFailed + ".pdf";
		if (dataMap.containsKey("outputPath")) {
			
			outputPath = dataMap.get("outputPath");		
			myDebugPrinting("outputPath - " + outputPath, enumsClass.logModes.MINOR);			
			File f = new File(outputPath);
			myAssertTrue("Output path is not exists !!", f.exists() && !f.isDirectory());
		} else {
			
			myFail("Output is missing !");
		}

		// Flags
		int isBodyTxtFound = 0;
		int isBodyPdfFound = 0;
		int sum2 		   = 0;
		int realMaxTime    = maxWaitTime * 5;
		int timeIdx 	   = 0;
		
		// Files variables
		int[] isFileDetected  = {0, 0, 0, 0, 0};
		String attPrefix      = "att_";
		String attSuffix      = ".pdf";
		String defSuffix      = ".txt";
		String withoutIcon    = "withoutIcon_";
		String pathStatus     = inputPath + faxHeaders[0] + defSuffix;
		String pathBody       = inputPath + faxHeaders[1] + defSuffix;
		String pathResult     = inputPath + faxHeaders[2] + defSuffix;
		String pathBodyAtt    = inputPath + attPrefix     + faxHeaders[1] + attSuffix;
		String pathResultAtt  = inputPath + attPrefix     + faxHeaders[2] + attSuffix;
		String pathStatus2    = inputPath + withoutIcon   + faxHeaders[0] + defSuffix;
		String pathBody2      = inputPath + withoutIcon   + faxHeaders[1] + defSuffix;
		String pathResult2    = inputPath + withoutIcon   + faxHeaders[2] + defSuffix;
		String pathBodyAtt2   = inputPath + attPrefix     + withoutIcon   + faxHeaders[1] + attSuffix;
		String pathResultAtt2 = inputPath + attPrefix     + withoutIcon   + faxHeaders[2] + attSuffix;
		myDebugPrinting("The searched files are:" + pathStatus  + "\n" +
													pathBody    + "\n" +
													pathResult  + "\n" +
													pathBodyAtt + "\n" +
													pathResultAtt, enumsClass.logModes.MINOR);		
		
		// Close all command-line instances (if exists)
		myDebugPrinting("Close all command-line instances (if exists)", enumsClass.logModes.MINOR);		
		try {
			
	        Runtime.getRuntime().exec("taskkill /f /im cmd.exe") ;
		} catch (IOException e) {
			
			myFail(e.toString());
		}
		
		// Close all acrobat instances (if exists)
		myDebugPrinting("Close all acrobat instances (if exists)", enumsClass.logModes.MINOR);		
		try {
		    Process child = Runtime.getRuntime().exec("cmd /c start cmd.exe");
		    OutputStream out = child.getOutputStream();
		    out.write("taskkill /f /t /im AcroRd32.exe".getBytes());
		    out.flush();
		    out.close();
		} catch (IOException e) {
			
			myFail(e.toString());
		}	
		
		// Delete all the files from the previous try.
		myDebugPrinting("Delete all the files from the previous try", enumsClass.logModes.MINOR);		
		deleteFilesByPrefix(inputPath , "");
		deleteFilesByPrefix(errorPath , "");
		deleteFilesByPrefix(convPath  , "");

		// Deposit a fax using the Fax-injector tool
		myDebugPrinting("Deposit a fax using the Fax-injector tool", enumsClass.logModes.MINOR);			
		myDebugPrinting("SMTP_INj/smtpinjct /h " + faxServerIpAddress + " /p " + faxServerIpPort + " /t 30 /if " + outputPath + " /w /af /ar /as /ax", enumsClass.logModes.MINOR);			
		try{    
		    Process p = Runtime.getRuntime().exec(System.getProperty("user.dir") + "//SMTP_INj//smtpinjct /h " + faxServerIpAddress + " /p " + faxServerIpPort + " /t 30 /if " + outputPath + " /w /af /ar /as /ax");
		    p.waitFor();

		} catch( IOException ex )          {
		} catch( InterruptedException ex ) {
		}

		// Wait for files to arrive.
		// Once all the files are been detected, inform the user and break.
		// If the max-time is reached and not all the files were detected, inform about error.
		// At any-time, if error message arrive, inform about error.
		while (true) {
			
			// Since we loop 5 times for second, only every 50th loop should be printed.
			if ((timeIdx % 50) == 0) {
				
				myDebugPrinting((timeIdx / 5) + " seconds passed. (maxWaitTime - " + maxWaitTime + ")", enumsClass.logModes.MINOR);
			}

			// Check for Status message
			File f1 = new File(pathStatus), f11 = new File(pathStatus2);
			if((f1.exists() && !f1.isDirectory()) || (f11.exists() && !f11.isDirectory())) { 
				
				if (isFileDetected[0] == 0) {
					
					myDebugPrinting(pathStatus + " was detected !! (sum2 - " + sum2 + ")", enumsClass.logModes.MINOR);
					if (fileNumber == 4) {
						
						myFail("Status message was received when the confirmation is off");
					}
					isFileDetected[0] = 1;
				}
			}
			
			// Check for Body message
			File f2  = new File(pathBody), f21 = new File(pathBody2);
			if((f2.exists() && !f2.isDirectory()) || (f21.exists() && !f21.isDirectory())) { 
				
				if (isFileDetected[1] == 0) {
					
					myDebugPrinting(pathBody + " was detected !! (sum2 - " + sum2 + ")", enumsClass.logModes.MINOR);
					isFileDetected[1] = 1;
				}
			}
			
			// Check for Result message
			File f3  = new File(pathResult), f31 = new File(pathResult2);
			if((f3.exists() && !f3.isDirectory()) || (f31.exists() && !f31.isDirectory())) { 
				
				if (isFileDetected[2] == 0) {
					
					myDebugPrinting(pathResult + " was detected !! (sum2 - " + sum2 + ")", enumsClass.logModes.MINOR);
					isFileDetected[2] = 1;
				}
			}
			
			// Check for Body-attachment message
			File f4  = new File(pathBodyAtt), f41 = new File(pathBodyAtt2);
			if((f4.exists() && !f4.isDirectory()) || (f41.exists() && !f41.isDirectory())) { 
				
				if (isFileDetected[3] == 0) {
					
					myDebugPrinting(pathBodyAtt + " was detected !! (sum2 - " + sum2 + ")", enumsClass.logModes.MINOR);
					isFileDetected[3] = 1;
				}
			}
			
			// Check for Result-attachment message
			File f5  = new File(pathResultAtt), f51 = new File(pathResultAtt2);
			if((f5.exists() && !f5.isDirectory()) || (f51.exists() && !f51.isDirectory())) { 
				
				if (isFileDetected[4] == 0) {
					
					myDebugPrinting(pathResultAtt + " was detected !! (sum2 - " + sum2 + ")", enumsClass.logModes.MINOR);
					isFileDetected[4] = 1;
				}
			}
			
			// Check for Error message
			File fError  = new File(errorFile), fErrorFileAtt = new File(errorFileAtt);
			if((fError.exists() && !fError.isDirectory()) || (fErrorFileAtt.exists() && !fErrorFileAtt.isDirectory())) { 
				
				if (raiseError == 1) {
					
					myFail("Failed message was detected");
				} else			 {
					
					myDebugPrinting( "Failed message was detected but raiseError set to  0 ....", enumsClass.logModes.NORMAL);
					break;
				}		
			}
			
			// Check if all files were detected
			sum2 = 0;
			for (int j = 0; j < 5; ++j) {
				
				sum2 += isFileDetected[j];	
			}
			sum2 += isBodyTxtFound + isBodyPdfFound;
			if (sum2 == fileNumber) {
				
				myDebugPrinting("All files were detected! (sum2 - " + sum2 + " inputFilesNumber - " + fileNumber, enumsClass.logModes.NORMAL);
				break;
				
			} else if (timeIdx > realMaxTime) {
				
				String names[] = {"status", "body-txt", "result-txt", "body-pdf", "result-pdf"};
				for (int k = 0; k < 5; ++k) {
					if (isFileDetected[k] == 0) {
						
						myDebugPrinting(names[k] + "  was not detected..", enumsClass.logModes.NORMAL);
					}
				}
				if (raiseError == 1) {
					
					myFail("Waiting-time was ended, and not all the files were detected..");
				} else {
					
					myDebugPrinting("Waiting-time was ended, and not all the files were detected..");
					break;
				}	
				
			} else {
				
				timeIdx = timeIdx + 1;
			}
			
			// Check if body-message is already been found. If so - light a flag.
			if (isMultipleTargets == 1) {
				if (isFileDetected[1] == 1 && isBodyTxtFound == 0) {
					
					isBodyTxtFound    = 1;
					isFileDetected[1] = 0;
					deleteFilesByPrefix(inputPath , pathBody);
				}
				if (isFileDetected[3] == 1 && isBodyPdfFound == 0) {
					
					isBodyPdfFound 	  = 1;
					isFileDetected[3] = 0;
					deleteFilesByPrefix(inputPath , pathBodyAtt);
				}
			}
			
			myWait(200);
		}
		
		
		myDebugPrinting( "Fax deposit ended....", enumsClass.logModes.NORMAL);
	
	  }
	  
	  /**
	  *  Activate Fax OCR
	  *  @param ocrPath 	 - Fax OCR path
	  *  @param rootDir 	 - Root of the emails directory
	  *  @param	convFileName - Name of the file that should be converted
	  *  @throws IOException 
	  */
	  public void activateFaxOCR(String ocrPath, String rootDir, String convFileName) throws IOException {
		  
		  // Activate the Fax-OCR
		  myDebugPrinting("Activate the Fax-OCR", enumsClass.logModes.MINOR);
		  myDebugPrinting("ocrPath - " + ocrPath, enumsClass.logModes.MINOR);  
		  Process process = new ProcessBuilder(ocrPath, rootDir, convFileName).start();
		  BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));	    
		  String line; 
		  while ((line = br.readLine()) != null) {
   	
			  myDebugPrinting(line, enumsClass.logModes.MINOR);    
		  }
	  }
	  
	  /**
	  *  Return files number in given path
	  *  @param path - path of directory we want to count the files number in
	  *  @return files number
	  */
	  public int countFilesNumber(String path) {
		  
		  myDebugPrinting("path - " + path, enumsClass.logModes.MINOR);
		  int fileNum = new File(path).listFiles().length;
		  myDebugPrinting("fileNum - " + fileNum, enumsClass.logModes.MINOR);
		  return fileNum;
	  }
	  
	  /**
	  *  Detected a specific header in a specific location
	  *  @param txt 	 - Fax OCR path
	  *  @param rootDir 	 - Root of the emails directory
	  *  @param	convFileName - Name of the file that should be converted
	  *  @throws IOException 
	  */
	  public void detectHeader(String txt, String pivotStr, String searchedStr) {
		
		int pivot = txt.indexOf(pivotStr);
		if (pivot != -1) {
			
			myAssertTrue("searchedStr <" + searchedStr + "> was not detected from index <" + pivot + "> !! \ntxt - " + txt,
					     txt.indexOf(searchedStr, pivot) != -1);	
		} else {
			
			myFail("pivotStr <" + pivotStr + "> was not detected !! \ntxt - " + txt);
		}
	  }
}