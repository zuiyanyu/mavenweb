package com.utils.excel;

import org.apache.poi.ss.usermodel.*;

public class AbstractExcelCommon {
   protected static CellStyle createHeadStyle(Workbook workbook){

       CellStyle style = createContentStyle(workbook);
       style.setFillForegroundColor((short) 13);
       return style;
   }
    protected static Sheet createSheet(Workbook workbook, String sheetName){
        Sheet sheet = workbook.createSheet(sheetName);
        sheet.setDefaultColumnWidth(24);
        return sheet ;
    }
    protected static CellStyle createContentStyle(Workbook workbook){
        short l = (short)1;

        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);

        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor((short) 9);
        style.setFillPattern(l);

        style.setBorderBottom(l);
        style.setBorderLeft(l);
        style.setBorderRight(l);
        style.setBorderTop(l);

        style.setAlignment(l);
        style.setFont(font);
        DataFormat dataFormat = workbook.createDataFormat();
        style.setDataFormat(dataFormat.getFormat("@"));
        return style ;
    }
}
