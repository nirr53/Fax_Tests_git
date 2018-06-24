package Fax_Tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GlobalTshark {
	
	private GlobalFuncs testFuncs;
	private GlobalVars  testVars;
	private String 		tsharkPath;
	
	// Default constructor
	public  GlobalTshark() {
		
		testVars   = new GlobalVars();
	    testFuncs  = new GlobalFuncs(); 
	    tsharkPath = testVars.getTsharkPath();
	}
	
	/**
	* Start a capture by a given parameters
	* @param maxPackNum	- Max packets number (I.e '3')
	* @param filter		- Given filter		 (I.e '-f port 5067')
	* @param outputPath	- Output path		 (I.e 'output.txt')
	*/
	public void StartCapture(String maxPackNum, String filter, String outputPath) throws IOException {
		
		// Set variables
		testFuncs.myDebugPrinting("tsharkPath - " + tsharkPath, enumsClass.logModes.MINOR);	
		testFuncs.myDebugPrinting("maxPackNum - " + maxPackNum, enumsClass.logModes.MINOR);	
		testFuncs.myDebugPrinting("filter - "     + filter    , enumsClass.logModes.MINOR);	
		testFuncs.myDebugPrinting("outputPath - " + outputPath, enumsClass.logModes.MINOR);	

		// Activate Tshark capture
		testFuncs.myDebugPrinting(" Activate Tshark capture", enumsClass.logModes.MINOR);	
		Process process = new ProcessBuilder(tsharkPath, "-c " + maxPackNum, filter, "-w " + outputPath).start();
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));	    
		String line; 
		while ((line = br.readLine()) != null) {
	  
			testFuncs.myDebugPrinting(line, enumsClass.logModes.MINOR);	
		}
	}
}