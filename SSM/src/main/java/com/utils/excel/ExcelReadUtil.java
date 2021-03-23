package com.utils.excel;

import com.alibaba.fastjson.JSON;
import com.mavenweb.utils.PubFunUtil;
import com.utils.excel.domain.CellMeta;
import com.utils.excel.domain.RowMeta;
import com.utils.excel.domain.SheetMeta;
import com.utils.excel.domain.WorkBookMeta;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.*;

public class ExcelReadUtil extends AbstractExcelCommon {
    private static Logger LOGGER = LoggerFactory.getLogger(ExcelReadUtil.class);

    /**
     * 读取excel的时候，根据excel文件输入流获取 Workbook对象
     * @param inputStream
     * @return 使用inputStream文件流得到的 Workbook对象
     */
    public static Workbook getWorkbook(InputStream inputStream){
        Workbook workbook = null ;
        //================= 方式1 ============================
        //如果用的是 3.11 低 版本的org.apache.poi.poi-ooxml 和 org.apache.poi.poi ,使用如下逻辑
        if(!inputStream.markSupported()){
            // 获取一个网络流，这个网络流不允许读写头来回移动，也就不允许mark/rest机制
            inputStream = new PushbackInputStream(inputStream,8);
        }
        try {
            if(POIFSFileSystem.hasPOIFSHeader(inputStream)){
                workbook = new HSSFWorkbook(inputStream) ; //2003
            }else if(POIXMLDocument.hasOOXMLHeader(inputStream)){
                workbook = new XSSFWorkbook(inputStream);//2007
            }
            if(workbook == null){
                throw new RuntimeException("ExcelUtil.getWorkBook():failed to create workbook !") ;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //====================方式 2============================

//        // 升级版本后（比如3.17）提示POIXMLDocument.hasOOXMLHeader不是方法。
//        //如果用的是 3.17 高版本的org.apache.poi.poi-ooxml 和 org.apache.poi.poi ,使用如下逻辑
//        try {
//            workbook =  WorkbookFactory.create(inputStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InvalidFormatException e) {
//            e.printStackTrace();
//        }
//        if(workbook == null){
//            throw new RuntimeException("ExcelUtil.getWorkBook():failed to create workbook !") ;
//        }

        return workbook ;
    }
    public static WorkBookMeta readExcel(MultipartFile multipartFile, List<String> headers, Integer headerInRowNum, Integer dataInRowNum, Map paramMap) {
        WorkBookMeta workBookMeta = null ;
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            InputStream inputStream = multipartFile.getInputStream();
            //上下文共享数据传递
            Map contextMap = new HashMap<>() ;
            //设置文件名称
            contextMap.put("originalFilename",originalFilename);
            workBookMeta=  readExcel(inputStream,null,1,2,contextMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  workBookMeta ;
    }
    public static WorkBookMeta readExcel(InputStream inputStream, List<String> headers, Integer headerInRowNum, Integer dataInRowNum, Map paramMap){
        Workbook workbook = getWorkbook(inputStream);
        //解析Excel文件时候，上下文共享的数据存储区
        Map contextMap = new HashMap() ;
        contextMap.put("createTime",new Date());

        //工作簿的主键
        String workBookId = PubFunUtil.getStrUUID();
        contextMap.put("workBookId",workBookId);

        //工作簿的文件名称
        String workBookName = (String) paramMap.get("originalFilename");

        //工作簿的sheet数量
        int $sheetCount ;
        $sheetCount = workbook.getNumberOfSheets();

        //这里逻辑只操作第一个sheet页
        List<SheetMeta> sheetMetaList = new ArrayList<>() ;
        for(int sheetIndex=0;sheetIndex < $sheetCount; sheetIndex ++){
            contextMap.put("sheetIndex",sheetIndex +1);
            //获取第一个sheet
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            SheetMeta sheetMeta = readSheet(sheet, headers, headerInRowNum, dataInRowNum, contextMap);
            sheetMetaList.add(sheetMeta);
            break;
        }

        WorkBookMeta workBookMeta = new WorkBookMeta();
        workBookMeta.setWorkBookId(workBookId);
        workBookMeta.setWorkBookName(workBookName);
        workBookMeta.setBookSheetCount($sheetCount);
        workBookMeta.setCreateTime((Date) contextMap.get("createTime"));
        workBookMeta.setSheetMetaList(sheetMetaList);

        return workBookMeta;
    }

    /**
     *
     * @param sheet
     * @param headers
     * @param headerInRowNum  header所在行 从1开始
     * @param dataInRowNum    数据所在行   从1开始
     * @param contextMap
     * @return
     */
    public static SheetMeta readSheet(Sheet sheet,List<String> headers,Integer headerInRowNum,Integer dataInRowNum,Map contextMap){
        if(headerInRowNum == null){ headerInRowNum = 1; }
        if(dataInRowNum   == null){ dataInRowNum   = 2; }
        //sheet的主键
        String sheetId = PubFunUtil.getStrUUID();
        contextMap.put("sheetId",sheetId) ;
        //sheet所在的工作簿(工作簿主键)
        String workBookId = (String) contextMap.get("workBookId");
        //sheet页的名称
        String sheetName = sheet.getSheetName();
        //sheet在workBook中的索引号 从1 开始
        Integer sheetIndex = (Integer)contextMap.get("sheetIndex") ;
        //excel解析用到的header中文名称
        String headerJson =  JSON.toJSONString(headers);  ;
        //excel解析用到的header英文名称
        String colJson = null;
        //sheet中的数据行总数 从1 开始
        int $sheetRowCount = sheet.getLastRowNum()+1; ;
        //header所在的行的索引 从1开始
        int $headerRowIndex = headerInRowNum;
        //data所在行的索引     从1开始
        int $dataRowIndex = dataInRowNum ;
        //当前数据行对象
        Row $currentRow ;

        // 获取header所在的row对象
        $currentRow = sheet.getRow($headerRowIndex-1);
        //解析当前sheet页的header数据
        RowMeta headerRowMeta = readRow($currentRow, contextMap);
        //获取文件中的header头信息
        List<CellMeta> CellMetaList = headerRowMeta.getCellMetaList();

        //对头信息进行操作
        List<String> headersFromExcel = new ArrayList<>() ;
        for (CellMeta CellMeta : CellMetaList) {
            headersFromExcel.add((String)CellMeta.getCellValue());
        }

        if(null !=headers && headers.size()>0){
            //如果给定的header不为空，就以给定的headers为主，并对文件中的headers进行检查
            // checkHeader(headers,headersFromExcel) ;
        }else {
            //如果给定的header为空，就以文件中解析的header为主
            headers = headersFromExcel ;
            headerJson =  JSON.toJSONString(headers);
        }

        //解析当前sheet页的每一行数据
        List<RowMeta> RowMetaList = new ArrayList() ;
        for(int dataRowIndex = $dataRowIndex-1; dataRowIndex < $sheetRowCount; dataRowIndex ++){
            $currentRow = sheet.getRow(dataRowIndex);
            RowMeta RowMeta = readRow($currentRow,contextMap );
            RowMetaList.add(RowMeta);
        }

        //设置信息
        SheetMeta sheetMeta = new SheetMeta();

        sheetMeta.setSheetId(sheetId);
        sheetMeta.setWorkBookId(workBookId);
        sheetMeta.setSheetIndex(sheetIndex);
        sheetMeta.setSheetName(sheetName);
        sheetMeta.setSheetRowCount($sheetRowCount);
        sheetMeta.setHeaderJson(headerJson);
        sheetMeta.setColJson(colJson);
        sheetMeta.setHeaderInRowIndex($headerRowIndex);
        sheetMeta.setDataInRowIndex($dataRowIndex);
        sheetMeta.setRowMetaList(RowMetaList);
        sheetMeta.setHeaderList(headers);
        sheetMeta.setColList(null);
        sheetMeta.setCreatTime((Date)contextMap.get("creatTime"));

        return  sheetMeta ;
    }
    private static RowMeta readRow(Row row,Map contextMap){
        //row的主键
        String rowId = PubFunUtil.getStrUUID();
        contextMap.put("rowId",rowId);
        //row所在的sheet(sheet主键)
        String sheetId = (String) contextMap.get("sheetId");
        //row在sheet中的索引/行号  从1开始
        int rowIndex = row.getRowNum() +1;
        //row中单元格的数量
        int $cellCount ;
        //当前单元格对象
        Cell $currentCell ;

        //结果是从1开始，无需加1
        $cellCount = row.getLastCellNum() ;

        List<CellMeta> CellMetaList = new ArrayList<>() ;
        for(int index = 0; index < $cellCount; index ++ ){
            $currentCell = row.getCell(index);
            CellMeta CellMeta = readCell($currentCell, contextMap);
            CellMetaList.add(CellMeta);
        }

        //设置信息
        RowMeta RowMeta = new RowMeta();

        RowMeta.setRowId(rowId);
        RowMeta.setSheetId(sheetId);
        RowMeta.setRowIndex(rowIndex);
        RowMeta.setRowCellCount($cellCount);
        RowMeta.setCellMetaList(CellMetaList);
        RowMeta.setCreateTime((Date)contextMap.get("createTime"));

        return RowMeta ;
    }
    private static CellMeta readCell(Cell cell, Map contextMap){
        CellMeta CellMeta= new CellMeta();
        //单元格的主键
        String cellId = PubFunUtil.getStrUUID();
        //单元格所在的行（行主键）
        String rowId = (String)contextMap.get("rowId");
        //单元格所在的行号 从1 开始
        int rowIndex = cell.getRowIndex() +1;
        //当前单元格所在的列- 数值表示 比如：28
        int columnIndex = cell.getColumnIndex() +1;
        //当前单元格所在的列- 字符表示，比如：AB
        String columnName = switchColIndexToName(columnIndex);
        //单元格的值
        String cellValue = getCellValue(cell);

        //设置单元格的信息
        CellMeta.setCellId(cellId);
        CellMeta.setRowid(rowId);
        CellMeta.setRowIndex(rowIndex);
        CellMeta.setColumnIndex(columnIndex);
        CellMeta.setColumnName(columnName);
        CellMeta.setCellValue(cellValue);
        CellMeta.setCreateTime((Date)contextMap.get("createTime"));

        return CellMeta ;
    }

    /**
     * 读取cell的值
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell){
        //以文本的形式读取Cell中的内容。 防止 整数 20210102 被读取成 20210102.0的形式
        cell.setCellType( Cell.CELL_TYPE_STRING);

        int cellType = cell.getCellType();
        String cellValue = null ;
        switch (cellType){
            case Cell.CELL_TYPE_STRING:
                cellValue = cell.getStringCellValue();
                break ;
            case Cell.CELL_TYPE_NUMERIC :
                if (DateUtil.isCellDateFormatted(cell)) {
                    Date dateCellValue = cell.getDateCellValue();
                    if (null !=dateCellValue) {
                        try{
                            cellValue= com.utils.DateUtil.parseDateToStr(dateCellValue, "yyyy-MM-dd HH:mm:ss");
                        }catch (Exception e){
                            cellValue="";
                            LOGGER.error("ExcelUtil.getCellValue()解析日期异常：dateCellValue={}，pattern=yyyy-MM-dd HH:mm:ss",dateCellValue);
                        }
                    }else{
                        cellValue="";
                    }
                } else {
                    double numericCellValue = cell.getNumericCellValue();
                    cellValue = Double.toString(numericCellValue);
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                boolean booleanCellValue = cell.getBooleanCellValue();
                cellValue=Boolean.toString(booleanCellValue);
                break;
            case Cell.CELL_TYPE_FORMULA:
                cellValue= cell.getCellFormula();
                break;
            default:
                cellValue="" ;
                break;
        }
        return cellValue ;
    }
    /**
     * 将excel的列值装换为excel的列名
     * 思路：字符有26个，但是数组长度下标为 0-25 ，故26进制进行转换
     * @param columnIndex
     * @return
     */
    private static String switchColIndexToName(int columnIndex){
        String[] letterArray = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        StringBuilder colHeader = new StringBuilder();
        int a ;
        int b ;
        // 方式1：对columnIndex先转成26进制，然后再换成下标的26进制。 比如：28的26 进制是 12 ，表示AB但是，A在letterArray[0] 中,B在letterArray[1] 中， 所有12 换成下标应该是 01
        while(columnIndex > 0){
            a = columnIndex / 26 ;
            b = columnIndex % 26 ;
            columnIndex = a ;
            //索引值 =  columnIndex-1 ；
            colHeader.append(letterArray[b-1]);
        }
        //或者
        //方式2： 直接对索引进行26进制转换。这里26表示索引。 索引值从0开始的，所以 索引值 =  columnIndex-1 ；
//     while(columnIndex > 0){
//        a = (columnIndex-1) / 26 ;
//        b = (columnIndex-1) % 26 ;
//        columnIndex = a ;
//        colHeader.append(letterArray[b]);
//    }
        return  colHeader.reverse().toString() ;
    }
}
