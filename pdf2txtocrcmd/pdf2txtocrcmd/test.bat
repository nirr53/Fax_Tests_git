md _test

call test-color-tif-to-searchablepdf.bat
call test-deskew-despeckle.bat

pdf2txtocr.exe test_multi_columns.pdf _test\_test_multi_columns.pdf.txt
pdf2txtocr.exe test_multi_columns.tif _test\_test_multi_columns.tif.txt
pdf2txtocr.exe -layout test_multi_columns.pdf _test\_test_multi_columns.pdf.layout.txt

pdf2txtocr.exe -ocr -ocrmode 1 test_multi_columns.pdf _test\_test_multi_columns_mode1.pdf
pdf2txtocr.exe -ocr -ocrmode 2 test_multi_columns.pdf _test\_test_multi_columns_mode2.pdf
pdf2txtocr.exe -ocr -ocrmode 3 test_multi_columns.pdf _test\_test_multi_columns_mode3.pdf
pdf2txtocr.exe -ocr -ocrmode 3 -ownerpwdout 123 -keylen 2 -encryption 3900 test_multi_columns.pdf _test\_test_multi_columns_mode3_encryption.pdf
pdf2txtocr.exe -ocr -outboxfile test_multi_columns.pdf _test\_test_outboxfile.txt

pdf2txtocr.exe -text "======================== This is the page %PageNumber% of %PageCount% ========================" test_text.pdf _test\_test_text.pdf.txt
pdf2txtocr.exe -layout -text "======================== This is the page %PageNumber% of %PageCount% ========================" test_text.pdf _test\_test_text.pdf.layout.txt

pdf2txtocr.exe -bitcount 1 test_color.tif _test\_test_color.tif.bitcount1.tif
pdf2txtocr.exe -rotate 45 test_color.tif _test\_test_color.tif.rotate45.tif
pdf2txtocr.exe -threshold 240 test_color.tif _test\_test_color.tif.threshold240.tif
pdf2txtocr.exe -threshold 0 test_color.tif _test\_test_color.tif.threshold.auto.tif
pdf2txtocr.exe -dither 0 test_color.tif _test\_test_color.tif.dither0.tif
pdf2txtocr.exe -dither 7 -resizewidth 200 -resizeheight 200 test_color.tif _test\_test_color.tif.dither7.resizewidth200.resizeheight200.tif
pdf2txtocr.exe -imageopt test_despeckle.tif _test\_test_despeckle.tif
pdf2txtocr.exe -imageopt test_skew.tif _test\_test_skew.tif
pdf2txtocr.exe -flip -mirror test_color.tif _test\_test_color.tif.flip.mirror.tif

pdf2txtocr.exe -bitcount 1 test_color_small.tif _test\_test_color_small.png.bitcount1.png
pdf2txtocr.exe -rotate 45 test_color_small.tif _test\_test_color_small.png.rotate45.png
pdf2txtocr.exe -threshold 240 test_color_small.tif _test\_test_color_small.png.threshold240.png
pdf2txtocr.exe -threshold 0 test_color_small.tif _test\_test_color_small.png.threshold.auto.png
pdf2txtocr.exe -dither 0 test_color_small.tif _test\_test_color_small.png.dither0.png
pdf2txtocr.exe -dither 7 -resizewidth 200 -resizeheight 200 test_color_small.tif _test\_test_color_small.png.dither7.resizewidth200.resizeheight200.png
pdf2txtocr.exe -threshold 0 test_color_small.tif _test\_test_color_small_imageopt.png
pdf2txtocr.exe -imageopt _test\_test_color_small_imageopt.png _test\_test_color_small_imageopt2.png
pdf2txtocr.exe -flip -mirror test_color_small.tif _test\_test_color_small.png.flip.mirror.png

pdf2txtocr.exe -ocr  -lang deu test_german.tif _test\_test_german.tif.txt
pdf2txtocr.exe test_german.pdf _test\_test_german.pdf.txt

pause