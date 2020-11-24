package com.utils.excel;

import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class ExcelExportUtil extends AbstractExcelCommon {
    private static Logger LOGGER = LoggerFactory.getLogger(ExcelExportUtil.class);

    /**
     * 可以导出复合sheet页的excel
     * 可以导出 list形式的数据；
     * 可以导出PoJo 字段注解形式的数据。
     * @param excelFileName
     * @param sheetDatas
     * @param response
     * @throws Exception
     */
    public static void exportComplexExcel(String excelFileName, List<SheetData> sheetDatas, HttpServletResponse response) throws Exception {
        //设置导出的文件名
        setExcelFileName(excelFileName, response);

        ServletOutputStream outputStream = response.getOutputStream();
        Workbook workbook = new HSSFWorkbook();

        for (SheetData sheetData : sheetDatas) {
            fillDatasToSheet(workbook,sheetData);
        }
        workbook.write(outputStream);
        IOUtils.closeQuietly(outputStream);
    }

    private static void fillDatasToSheet(Workbook workbook,SheetData sheetData) {
        if("list".equalsIgnoreCase(sheetData.getExportType())){
            fillListDatasToSheet(workbook,sheetData);
        }else if("class".equalsIgnoreCase(sheetData.getExportType())){
            fillClassDatasToSheet(workbook,sheetData);
        }
    }

    private static void fillClassDatasToSheet(Workbook workbook, SheetData sheetData) {
        Sheet sheet = createSheet(workbook,sheetData.getSheetName());
        int headerAt =0;
        int dataAt = headerAt +1;

         //数据对象对应的pojo类
        Class<T> cls = sheetData.getClassPojo();
        //数据对象
        List<T> classDatas = sheetData.getClassDatas();


        //1 . 从 pojo 的字段注解中获取 excel 文件内容信息 和 对应的字段信息
        List<String> headers = new ArrayList<>();
        List<String> cols = new ArrayList<>() ;

        Field[] fields = cls.getDeclaredFields();
        for (int colAt = 0; colAt < fields.length ; colAt++) {
            Field field = fields[colAt];
            ExcelColumn column = field.getAnnotation(ExcelColumn.class);
            if(column !=null){
                //文件内容头信息名
                headers.add(column.name());
                //字段名 用来形成 getter
                cols.add(field.getName());
            }
        }

        //2.填充头信息  文件头信息默认为第一行
        CellStyle headStyle = createHeadStyle(workbook) ;
        Row headerRow = sheet.createRow(headerAt);
        setHeaderDatas(headerRow,headers,headStyle);

        //3.填充excel内容信息
        CellStyle contentStyle =createContentStyle(workbook) ;
        for (T dataObj : classDatas) {
            int colAt = 0 ;
            Row row = sheet.createRow(dataAt++);

            //将这次获取的数据填充到 当前行
            for (String fieldName : cols) {
                Cell cell = row.createCell(colAt++);
                cell.setCellStyle(contentStyle);
                cell.setCellValue(getField(dataObj,fieldName));
            }
        }

    }

    /**
     * 通过反射，根据数据对象的字段名对应的方法来获取字段值
     * @param dataObj
     * @param fieldName
     * @return
     */
    private static String getField(T dataObj, String fieldName) {
        String methodName = "get"+ StringUtils.capitalize(fieldName) ;
        String fieldValue = "" ;
        try {
            Class<? extends T> aClass = dataObj.getClass();
            Method method = aClass.getMethod(methodName);
            Object fieldV = method.invoke(dataObj);
            if(null != fieldV){
                fieldValue = fieldV.toString() ;
            }
        } catch (NoSuchMethodException e) {
            LOGGER.error(e.getMessage(),e);
        } catch (IllegalAccessException e) {
            LOGGER.error(e.getMessage(),e);
        } catch (InvocationTargetException e) {
            LOGGER.error(e.getMessage(),e);
        }

        return fieldValue;
    }

    /**
     * 设置 excel文件内容的头信息
     * @param headerRow
     * @param headers
     * @param headStyle
     */
    private static void setHeaderDatas( Row headerRow,List<String> headers,CellStyle headStyle){

        Iterator<String> i$1 = headers.iterator();
        int colAt = 0 ;
        while(i$1.hasNext()){
            String header = i$1.next();
            Cell cell = headerRow.createCell(colAt++);
            cell.setCellStyle(headStyle);
            if(!StringUtils.isEmpty(header)){
                header = header.replaceAll("<[.[^<]]>", "") ;
                cell.setCellValue(header);
            }
        }
    }

    private static void fillListDatasToSheet(Workbook workbook, SheetData sheetData) {

        List<String> listHeaders = sheetData.getListHeaders();
        List<String> listCols = sheetData.getListCols();
        List<Map<String, Object>>  listDatas = sheetData.getListDatas();

        Sheet sheet = createSheet(workbook, sheetData.getSheetName());
        int headerAt = 0 ;
        int dataAt = headerAt +1 ;

        //设置头信息
        CellStyle headStyle = createHeadStyle(workbook);
        Row headerRow = sheet.createRow(headerAt);
        setHeaderDatas(headerRow,listHeaders,headStyle);

        //设置数据信息
        CellStyle contentStyle = createContentStyle(workbook);
        for (Map<String, Object> dataMap : listDatas) {
            Row dataRow = sheet.createRow(dataAt++) ;

            int colAt = 0 ;
            for (String colData : listCols) {
                Cell cell = dataRow.createCell(colAt++);
                cell.setCellStyle(contentStyle);
                cell.setCellValue((String)dataMap.get(colData));
            }
        }

    }

    /**
     * 给导出的excel文件进行命名
     * @param fileName
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    public static HttpServletResponse setExcelFileName(String fileName, HttpServletResponse response) throws UnsupportedEncodingException {
        response.reset();

        if(StringUtils.isEmpty(fileName)){
            fileName= "excel.xls";
        }
        if(!(fileName.endsWith(".xls")||!fileName.endsWith(".xlsx"))){
            fileName =  fileName+".xls" ;
        }
        fileName = URLEncoder.encode(fileName,"UTF-8");
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition","attachment;filename="+fileName);

        return response ;
    }
    public static class SheetData<T> {
        private String sheetName ;
        private String exportType;

        //根据list 方式导出数据
        private List<String> listHeaders ;
        private List<String> listCols ;
        private List<Map<String,Object>> listDatas ;

        //根据class 进行数据导出
        private Class<T> classPojo ;
        private List<T> classDatas ;


        public SheetData(String sheetName,List<String> listHeaders,List<String> listCols,List<Map<String,Object>> listDatas){
            this.exportType = "list" ;
            this.sheetName = sheetName ;
            this.listHeaders = listHeaders ;
            this.listCols = listCols ;
            this.listDatas = listDatas ;
        }
        public SheetData(String sheetName,Class<T> classPojo ,List<T> classDatas ){
            this.exportType = "class" ;
            this.sheetName = sheetName ;
            this.classPojo = classPojo ;
            this.classDatas = classDatas ;
        }

        @Override
        public String toString() {
            return "SheetData{" +
                    "sheetName='" + sheetName + '\'' +
                    ", exportType='" + exportType + '\'' +
                    ", listHeaders=" + listHeaders +
                    ", listCols=" + listCols +
                    ", listDatas=" + listDatas +
                    ", classPojo=" + classPojo +
                    ", classDatas=" + classDatas +
                    '}';
        }

        public String getSheetName() {
            return sheetName;
        }

        public void setSheetName(String sheetName) {
            this.sheetName = sheetName;
        }

        public String getExportType() {
            return exportType;
        }

        public void setExportType(String exportType) {
            this.exportType = exportType;
        }

        public List<String> getListHeaders() {
            return listHeaders;
        }

        public void setListHeaders(List<String> listHeaders) {
            this.listHeaders = listHeaders;
        }

        public List<String> getListCols() {
            return listCols;
        }

        public void setListCols(List<String> listCols) {
            this.listCols = listCols;
        }

        public List<Map<String, Object>> getListDatas() {
            return listDatas;
        }

        public void setListDatas(List<Map<String, Object>> listDatas) {
            this.listDatas = listDatas;
        }

        public Class<T> getClassPojo() {
            return classPojo;
        }

        public void setClassPojo(Class<T> classPojo) {
            this.classPojo = classPojo;
        }

        public List<T> getClassDatas() {
            return classDatas;
        }

        public void setClassDatas(List<T> classDatas) {
            this.classDatas = classDatas;
        }
    }
}
