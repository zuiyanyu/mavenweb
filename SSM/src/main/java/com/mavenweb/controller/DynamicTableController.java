package com.mavenweb.controller;

import com.github.pagehelper.StringUtil;
import com.mavenweb.mapper.DynamicTabMapper;
import com.mavenweb.service.DynamicTabService;
import com.mavenweb.utils.JsonResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

@RequestMapping("dynamicTable")
@RestController
public class DynamicTableController {
    private static Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    @Resource
    private DynamicTabService dynamicTabService ;
    @Resource
    private DynamicTabMapper dynamicTabMapper ;

    /**
     * 动态向某张表插入数据，txt文件或者csv文件
     * 编码注意：1.文件内容要以UTF8编码，2.如果文件是有BOM格式的UTF8编码，则文件首行会出现 \ufeff,需要进行处理。
     * @param multipartFile  表数据文件
     * @param appendPattern  是否是追加模式 true-在原始表数据后追加新数据；false-新数据覆盖原始数据。
     * @param insertCountOnce 每次插入的数据量(批量插入数据) 默认值为5000(即每5000条数据进行一次插入操作)
     *                        每批次插入的数据量阈值 = 2000,20000，insertCountOnce 三者中的中间值。
     * @return
     */
    @RequestMapping(value="dynamicAddData",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public JsonResp dynamicAddData(@RequestParam(value="file")MultipartFile multipartFile,
                                   @RequestParam(value="pattern",defaultValue = "True")Boolean appendPattern,
                                  @RequestParam(value="insertCountOnce",required = false,defaultValue = "5000")Integer insertCountOnce)
    {

        Assert.isTrue(multipartFile !=null,"没有上传文件！请选择上传的txt或者csv文件！");
        LOGGER.info("入参：insertCountOnce={},originalFilename={},appendPattern={}",insertCountOnce,multipartFile.getOriginalFilename(),appendPattern);
        //要返回的数据记录
        Map retMap = new HashMap() ;

        //1.判断文件命名格式是否符合
        String originalFilename = multipartFile.getOriginalFilename();
        Assert.isTrue(originalFilename !=null && (originalFilename.toLowerCase().endsWith(".txt")||originalFilename.toLowerCase().endsWith(".csv")),
                "只接收txt或者csv文件！");

        //2.分批保存，为了进行批量保存数据，防止频繁连接数据库  取中间值
        insertCountOnce = Math.max(2000,Math.min(insertCountOnce,20000));

        //3.准备数据，并开始进行插入操作
        //记录已经插入的数据量
        int insertedDataCount = 0 ;
        //记录已经插入的批次数
        int batchNum = 0 ;
        //性能分析：开始时间
        Long startTime = System.currentTimeMillis();
        try(
                InputStream inputStream = multipartFile.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        ){

            //3.1 获取第一行数据：表名和字段名所在的行，格式为 “表名:字段名”  比如： tabName:field1,tabName:field2 ...
            String headerLine = bufferedReader.readLine() ;
            if(StringUtil.isEmpty(headerLine)){
                return JsonResp.failure("文件第一行为表字段信息，不能为空！格式为 表名:字段名 ");
            }
            headerLine = headerLine.replace("\ufeff","");
            String[] tabNameAndFields = headerLine.split(",");

            //解析要操作的表名和字段名
            String tabName = null ;
            List<String> fieldNameList = new ArrayList();
            for (String tabNameAndField : tabNameAndFields) {
                if(StringUtil.isEmpty(tabNameAndField)){
                    return JsonResp.failure("文件第一行为表字段信息的列有空值！请检查！");
                }
                if(!tabNameAndField.contains(":")){
                    //1)只有字段名，没有表名
                    fieldNameList.add(tabNameAndField);
                    continue;
                }
                //2) 解析 “表名:字段名”
                String[] tabName_field = tabNameAndField.split(":");
                //获取表名 以遇到的第一个表名为操作表
                if(tabName==null){
                    tabName =tabName_field[0];
                }
                //添加字段名
                fieldNameList.add(tabName_field[1]);
            }
            if(tabName ==null){
                return JsonResp.failure("文件第一行为表字段信息，但没有一个字段配置表名！格式为 表名:字段名 ");
            }

            //3.2 获取数据并开始插入数据
            if(!appendPattern){
                int deletedCount = dynamicTabMapper.deleteAllTabData(tabName);
                LOGGER.info("清空表【{}】数据量为：{}",tabName,deletedCount);
            }

            int fieldSize = fieldNameList.size();//开始向操作表中插入字段数据
            int willInsertDataCount = 0 ;//当前批次要插入的数据量
            int lineNum = 1 ; ////记录当前操作的是第几行
            List<List> dataList = new ArrayList<>();
            String dataLine = null;//当前行的数据
            while((dataLine = bufferedReader.readLine())!=null){
                lineNum ++;
                String[] dataSplit = dataLine.split(",");
                if(dataSplit.length != fieldSize){
                    return JsonResp.failure("第【"+lineNum+"】行的数据个数为{"+dataSplit.length+"}，和表字段的个数{"+fieldSize+"}不匹配，请检查！");
                }
                //收集每一行的数据，收集到insertCountOnce阈值的时候，会进行插入操作
                dataList.add(Arrays.asList(dataSplit));
                willInsertDataCount++;

                //收集的数据量达到了insertCountOnce阈值，进行插入操作
                if(willInsertDataCount == insertCountOnce){
                    LOGGER.info("第{}批次要插入的数据量为{}",batchNum++,willInsertDataCount);
                    insertedDataCount += dynamicTabService.dynamicAddData(lineNum,tabName, fieldNameList, dataList);
                    //情空数据，开始下一批次数据的插入
                    dataList.clear();
                    willInsertDataCount =0 ;
                }
            }

            //最后一批没有达到阈值的数据进行插入
            if(willInsertDataCount>0){
                LOGGER.info("第{}批次要插入的数据量为{}",batchNum++,willInsertDataCount);
                insertedDataCount += dynamicTabService.dynamicAddData(lineNum,tabName, fieldNameList, dataList);
                dataList=null;//加快垃圾回收
            }
            //性能分析：开始时间
            Long endTime = System.currentTimeMillis();

            //4. 返回运行结果
            retMap.put("总数据行数(不含字段信息)",lineNum-1);
            retMap.put("总共插入的数据量",insertedDataCount);
            retMap.put("插入的批次数",batchNum);
            retMap.put("每批次数插入的数据量阈值",insertCountOnce);
            retMap.put("最后一批插入的数据量",willInsertDataCount);
            retMap.put("上传数据用时(ms)",(endTime-startTime)/1000);
            return JsonResp.sucess(retMap) ;

        } catch (Exception e){
            return JsonResp.failure("插入数据异常，异常原因为："+e.getMessage());
        }
    }

    /**
     * 动态执行sql
     * @param pageNum
     * @param pageSize
     * @param selectSql
     * @return
     */
    @RequestMapping(value="dynamicRunSql",method = RequestMethod.POST)
    public JsonResp dynamicRunSql(@RequestParam(value = "pageNum",defaultValue = "0") Integer pageNum,
                                   @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                   @RequestParam(value = "selectSql",required = true) String selectSql)
    {
        try{
            LOGGER.info("入参为：pageNum={},pageSize={},selectSql={}",pageNum,pageSize,selectSql);
            if(StringUtil.isEmpty(selectSql)){
                return JsonResp.failure("执行SQL不能为空");
            }
            //执行查询sql
            Map<String, Object> dataInfoMap = dynamicTabService.runSelectSql(pageNum, pageSize, selectSql);
            return JsonResp.sucess(dataInfoMap);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResp.failure("动态执行sql失败",e.getMessage());
        }
     }
}
