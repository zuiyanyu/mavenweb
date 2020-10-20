package com.mavenweb.utils.excel;

import com.alibaba.fastjson.JSON;
import com.mavenweb.utils.PubFunUtil;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * excel操作依赖包：
*
*         <dependency>
*             <groupId>org.apache.poi</groupId>
*             <artifactId>poi</artifactId>
*             <version>3.11</version>    低版本
*             <version>3.17</version>    高版本
*         </dependency>
*         <dependency>
*             <groupId>org.apache.poi</groupId>
*             <artifactId>poi-ooxml</artifactId>
*             <version>3.11</version>    低版本
*             <version>3.17</version>    高版本
*         </dependency>
*/
public class ExcelUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * 给导出的excel文件进行命名
     * @param fileName
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    public static HttpServletResponse setDownFileName(String fileName,HttpServletResponse response) throws UnsupportedEncodingException {
        response.reset();
        if(StringUtils.isEmpty(fileName)){
            fileName= "excel.xls";
        }
        if((fileName.endsWith(".xls")||fileName.endsWith(".xlsx"))){
            fileName = URLEncoder.encode(fileName,"UTF-8");
        }else{
            fileName= URLEncoder.encode(fileName+".xls","UTF-8");
        }
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition","attachment;filename="+fileName);

        return response ;
    }

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
    public static WorkBookInfo readExcel(MultipartFile multipartFile, List<String> headers, Integer headerInRowNum, Integer dataInRowNum, Map paramMap) {
        WorkBookInfo workBookInfo = null ;
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            InputStream inputStream = multipartFile.getInputStream();
           //上下文共享数据传递
            Map contextMap = new HashMap<>() ;
            //设置文件名称
            contextMap.put("originalFilename",originalFilename);
            workBookInfo=  ExcelUtil.readExcel(inputStream,null,1,2,contextMap);
         } catch (IOException e) {
            e.printStackTrace();
        }
        return  workBookInfo ;
    }

        public static WorkBookInfo readExcel(InputStream inputStream,List<String> headers,Integer headerInRowNum,Integer dataInRowNum,Map paramMap){
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
        List<SheetInfo> sheetInfoList = new ArrayList<>() ;
        for(int sheetIndex=0;sheetIndex < $sheetCount; sheetIndex ++){
            contextMap.put("sheetIndex",sheetIndex +1);
            //获取第一个sheet
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            SheetInfo sheetInfo = readSheet(sheet, headers, headerInRowNum, dataInRowNum, contextMap);
            sheetInfoList.add(sheetInfo);
            break;
        }

        WorkBookInfo workBookInfo = new WorkBookInfo();
        workBookInfo.setWorkBookId(workBookId);
        workBookInfo.setWorkBookName(workBookName);
        workBookInfo.setBookSheetCount($sheetCount);
        workBookInfo.setCreateTime((Date) contextMap.get("createTime"));
        workBookInfo.setSheetInfoList(sheetInfoList);

        return workBookInfo;
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
    public static SheetInfo readSheet(Sheet sheet,List<String> headers,Integer headerInRowNum,Integer dataInRowNum,Map contextMap){
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
        RowInfo headerRowInfo = readRow($currentRow, contextMap);
        //获取文件中的header头信息
        List<CellInfo> cellInfoList = headerRowInfo.getCellInfoList();

        //对头信息进行操作
        List<String> headersFromExcel = new ArrayList<>() ;
        for (CellInfo cellInfo : cellInfoList) {
            headersFromExcel.add((String)cellInfo.getCellValue());
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
        List<RowInfo> rowInfoList = new ArrayList() ;
        for(int dataRowIndex = $dataRowIndex-1; dataRowIndex < $sheetRowCount; dataRowIndex ++){
            $currentRow = sheet.getRow(dataRowIndex);
            RowInfo rowInfo = readRow($currentRow,contextMap );
            rowInfoList.add(rowInfo);
        }

        //设置信息
        SheetInfo sheetInfo = new SheetInfo();

        sheetInfo.setSheetId(sheetId);
        sheetInfo.setWorkBookId(workBookId);
        sheetInfo.setSheetIndex(sheetIndex);
        sheetInfo.setSheetName(sheetName);
        sheetInfo.setSheetRowCount($sheetRowCount);
        sheetInfo.setHeaderJson(headerJson);
        sheetInfo.setColJson(colJson);
        sheetInfo.setHeaderInRowIndex($headerRowIndex);
        sheetInfo.setDataInRowIndex($dataRowIndex);
        sheetInfo.setRowInfoList(rowInfoList);
        sheetInfo.setHeaderList(headers);
        sheetInfo.setColList(null);
        sheetInfo.setCreatTime((Date)contextMap.get("creatTime"));

        return  sheetInfo ;
    }
    private static RowInfo readRow(Row row,Map contextMap){
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

        List<CellInfo> cellInfoList = new ArrayList<>() ;
        for(int index = 0; index < $cellCount; index ++ ){
            $currentCell = row.getCell(index);
            CellInfo cellInfo = readCell($currentCell, contextMap);
            cellInfoList.add(cellInfo);
        }

        //设置信息
        RowInfo   rowInfo = new RowInfo();

        rowInfo.setRowId(rowId);
        rowInfo.setSheetId(sheetId);
        rowInfo.setRowIndex(rowIndex);
        rowInfo.setRowCellCount($cellCount);
        rowInfo.setCellInfoList(cellInfoList);
        rowInfo.setCreateTime((Date)contextMap.get("createTime"));

        return rowInfo ;
    }

    private static CellInfo readCell(Cell cell,Map contextMap){
        CellInfo    cellInfo= new CellInfo();
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
        cellInfo.setCellId(cellId);
        cellInfo.setRowid(rowId);
        cellInfo.setRowIndex(rowIndex);
        cellInfo.setColumnIndex(columnIndex);
        cellInfo.setColumnName(columnName);
        cellInfo.setCellValue(cellValue);
        cellInfo.setCreateTime((Date)contextMap.get("createTime"));

        return cellInfo ;
    }

    /**
     * 将excel的列值装换为excel的列名
     * 思路：字符有26个，但是数组长度下标为 0-25 ，故26进制进行转换
     * @param columnIndex
     * @return
     */
    public static String switchColIndexToName(int columnIndex){
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
    /**
     * 读取cell的值
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell){
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
                            cellValue= com.mavenweb.utils.DateUtil.parseDateToStr(dateCellValue, "yyyy-MM-dd HH:mm:ss");
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
}
