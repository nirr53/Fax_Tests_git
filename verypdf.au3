#include <Constants.au3>
#include <IE.au3>
#include <Array.au3>
#include <Date.au3>
#include <File.au3>
#include <faxGlobalVars.au3>

#RequireAdmin

#cs ----------------------------------------------------------------------------

 AutoIt Version: 3.3.12.0
 Author:         Nir Klieman
 Date:			 3\6\15

 Script Function:
	Activate the VeryPD OCR tool.

#ce ----------------------------------------------------------------------------
;--------------------------------------------------------------------------------------------------
Func exitWithError($errorString = "")

   FileDelete($errorsPath)
   _FileWriteLog($veryErrorsPath, $errorString)
   Exit 1

EndFunc
;--------------------------------------------------------------------------------------------------
Func myToolTip($txt, $xPos = 150, $yPos = 150, $level = 1, $delay = 500)

   Local $target = " "
   For $i = 1 To $level Step 1
	  $target = $target & @TAB
   Next

   Local $color = ""
   ; Set color to the text according given $level
   If $level = 1 Then
	  $color = "+"
   ElseIf $level = 2 Then
	  $color = ">"
   ElseIf $level = 3 Then
	  $color = "-"
   EndIf

   ToolTip(@LF & $txt & @LF & "---------------------------------------" , $xPos, $yPos)
   ConsoleWrite($color & $target &  _NowTime() & "  " & $txt & @TAB & @TAB & @LF)
   _FileWriteLog($veryPDFlogActionPath, $target & $txt)
   Sleep($delay)
EndFunc
;--------------------------------------------------------------------------------------------------

; Set variables
$arg_num	  = $CmdLine[0]
$foldersPath  = $CmdLine[1]
$filesname    = $CmdLine[2]
$inLocation  = $foldersPath & "\" & $inputDirName     & "\" & $filesname & ".pdf"
$outLocation = $foldersPath & "\" & $convertedDirName & "\" & $filesname & ".rtf"
;~ $inLocation  = $filesname & ".pdf"
;~ $outLocation = $filesname & ".rtf"
$inLocation  = StringReplace($inLocation , "\\", "\")
$outLocation = StringReplace($outLocation, "\\", "\")

; Delete the previous converting (if exists)
myToolTip("1. Delete previous convert")
FileDelete($outLocation)

; Run the program
myToolTip("2. Run the OCR converter .. ")
Local $iReturn = Run($veryBatchFilename, @ScriptDir, @SW_MAXIMIZE)
myToolTip("2.1 $iReturn  - " & $iReturn & "           ")
If  $iReturn = 0 Then
   exitWithError("2.2 Very-PDF was not created successfully !!")
EndIf
Sleep(2500)

; Get hWnd
$hWnd = WinGetHandle("[CLASS:#32770]")
myToolTip("2.3 $hWnd  - " & $hWnd & "           ")
Sleep(1500)

; Set output options
myToolTip("3. Set output options .. ")
ControlClick($hWnd, "", "[ID:1034]")
ControlSend($hWnd, "" , "[ID:1034]", "8")
ControlSend($hWnd, "" , "[ID:1034]", "{ENTER}")
Sleep(1500)

; Set RTF output
myToolTip("4. Set RTF format .. ")
ControlClick($hWnd, "", "[CLASSNN:Button5]")
Sleep(1500)

; Disable display after converting
myToolTip("5. Disable display after converting .. ")
ControlClick($hWnd, "", "[ID:1013]")
Sleep(1500)

; Add PDF for convert
myToolTip("6. Add PDF for convert .. ")
ControlClick($hWnd, "", "[CLASSNN:Button1]")
Sleep(2000)
ControlSend($hWnd, "", "", $inLocation)
Sleep(1500)
ControlSend($hWnd, "", "", "{ENTER}")
Sleep(1500)

; Add target for convert
myToolTip("7. Add target for convert .. ")
ControlClick($hWnd, "", "[CLASSNN:Button9]")
Sleep(2000)
ControlSend($hWnd, "", "", $outLocation)
Sleep(1500)
ControlSend($hWnd, "", "", "{ENTER}")
Sleep(1500)

; Wait for the converted file
$Timeout = 0
myToolTip("Search path - " & $foldersPath & "\" & $convertedDirName & "\" & $filesname & ".rtf")
while 1 = 1
   If FileExists($foldersPath & "\" & $convertedDirName & "\" & $filesname & ".rtf") Then
	  myToolTip("8.1 File convert was ended successfully !!")
	  ExitLoop

   Else
	  $Timeout += 1
	  myToolTip("8.2 Keep waiting for the file.. " & $Timeout & " seconds passed ..")
	  If $Timeout > 240 Then
		 WinClose($hWnd)
		 exitWithError("8.3 Time-out was passed and no file was found...")

	  EndIf

   EndIf

WEnd
Sleep(1500)
myToolTip("9. Convert was ended successfully !!")
WinClose($hWnd)
Exit 0