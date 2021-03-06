package com.mavenweb.controller;

import com.mavenweb.utils.Constants;
import com.mavenweb.utils.FileUtil;
import com.mavenweb.utils.JsonResp;
import com.mavenweb.utils.PubFunUtil;
import com.utils.excel.ExcelReadUtil;
import com.utils.excel.domain.WorkBookMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/fileController")
public class FileController {
private static Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    @RequestMapping(value = "/uploadFile" ,method=RequestMethod.POST)
    public JsonResp uploadFileTmp(@RequestParam("file") MultipartFile multipartFile, HttpServletResponse response){
        try{
            Map map = FileUtil.uploadFile(multipartFile,PubFunUtil.getStrUUID(),PubFunUtil.getStrUUID(),Constants.UPLOAD_FILE_PATH,"mtf",null);
            return JsonResp.sucess(map);
       }catch (Exception e ){
           return JsonResp.failure("文件上传失败："+e.getMessage());
       }
    }

    /**
     * 文件下载
     * @param fileOriginName
     * @param fileStoredName
     * @param fileStoredPath
     * @param response
     * @return
     */
    @RequestMapping(value = "/downloadFile" ,method=RequestMethod.GET)
    public JsonResp downloadFile(String fileOriginName,String fileStoredName,String fileStoredPath,HttpServletResponse response){

        Map map = FileUtil.downloadFile(fileOriginName, fileStoredName, fileStoredPath, null, response);
        return JsonResp.sucess(map);
    }

    @RequestMapping(value = "/readExcel" ,method=RequestMethod.POST)
    public JsonResp readExcel(@RequestParam("file") MultipartFile multipartFile, HttpServletResponse response){
        try {
            WorkBookMeta workBookInfo = ExcelReadUtil.readExcel(multipartFile, null, 1, 2, null);
            return JsonResp.sucess(workBookInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResp.failure("excel解析失败，失败原因为："+e.getMessage()) ;
        }
    }


}
