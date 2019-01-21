for %%F in (%CD%\*.pdf) do ..\pdf2txtocr.exe -table "%%F" "%%~dpnF.txt"

pause
