md _test
pdf2txtocr.exe -ocrmode 4 test_color.tif _test\_test_color.pdf
pdf2txtocr.exe -ocr -ocrmode 4 -res 72 _test\_test_color.pdf _test\_test-pdf2pdf-grayscale.pdf
pdf2txtocr.exe -ocr -ocrmode 4 -res 72 -bitcount 24 _test\_test_color.pdf _test\_test-pdf2pdf-color.pdf
