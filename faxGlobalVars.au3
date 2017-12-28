
;~ +-----------------------------------------------------------------------------------------------------------+
;~ |                                                                                                           |
;~ | Author: 	  Nir Klieman																				   |
;~ | Date:		  13\3\16																					   |
;~ | Description: This file displays diffrent variables that the Fax-server AutoIT enviroment use	           |
;  |																										   |
;  +-----------------------------------------------------------------------------------------------------------+

$defPassword   = "Admin"														; Default password of the Fax-server system
$defUsername   = "Admin"														; Default username of the Fax-server system
$logsDirName   = "log"															; Logs directory name
$logActionPath = $logsDirName & "\faxServerActionLog.txt"						; Log of the tooltip that represents the actions of the enviroment
$searchPath	   = $logsDirName & "\faxServerSearchLogger.txt"					; Path for the txt file that diaplays the history of the web-page searches
$errorsPath	   = $logsDirName & "\faxErrorsLogger.txt"							; Path for the txt file that diaplays the errors of the enviroment

; Very-PDF variables
$veryPDFlogActionPath = $logsDirName & "\veryPDFActionLog.txt"	  					  ; Log of the tooltip that represents the actions of the Very-PDF
$veryErrorsPath		  = $logsDirName & "\veryPDFErrorsLogger.txt"  					  ; Path for the txt file that diaplays the errors of the Very-PDF enviroment
$veryBatchFilename 	  = "startOCR.bat"												  ; Name of the batch-file that activate the Very-PDF tool
$inputDirName		  = "input"														  ; Name of the input directory
$convertedDirName	  = "converted"													  ; Name of the converted directory
$globalTimeout		  = 5000														  ; Global timeout value

; Log level variables
$countDownLevel 	= 5
$searchLevel		= 4
$innerFunctionLevel = 3
$functionLevel		= 2
$testerLevel		= 1
$testDescLevel		= 0

; Constansts
$DETECT_MODE		= 0
$NO_DETECT_MODE		= 1