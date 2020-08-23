package com.mavenweb.utils;

import com.mavenweb.domain.UploadFile;
import com.mavenweb.service.UploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * //        System.out.println(OnlyTest.class.getResource(""));//获取class类所在的文件目录
 * //        System.out.println(Float.class.getResource(""));
 * //        System.out.println(OnlyTest.class.getResource("/"));
 * //        System.out.println(OnlyTest.class.getResource("OnlyTest.class"));
 * //        System.out.println(OnlyTest.class.getResource("/OnlyTest2.class"));
 */
@Component
public class FileUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);
     //************************文件上传下载工具**********************
    private static UploadFileService uploadFileService ;

    //解决静态属性不能进行注入的问题
    @Resource
    public void setUploadFileService(UploadFileService uploadFileService){
        FileUtil.uploadFileService = uploadFileService ;
    }
    private static void commingLog(String methodName){
        LOGGER.info("=== comming into com.mavenweb.utils.FileUtil.{}()",methodName);
    }
    public static String transUrl(String urlPath){
         return  urlPath.replaceAll("\\\\", "/");
    }
    /**
     * 给下载的文件进行命名
     * @param response
     * @param fileName
     * @return
     */
    public static HttpServletResponse setDownFileName(HttpServletResponse response, String fileName){
        try {
            fileName = URLEncoder.encode(fileName,"UTF-8");
            response.addHeader("Content-Disposition","attachment;filename=\""+fileName+"\"");
            //response.setContentType("application/octet-stream;charset=UTF-8");
            response.setContentType("multipart/form-data;charset=UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return response ;
    }

    public static Map downloadFile(String fileOriginName,String fileStoredName,String fileStoredPath,Map map,HttpServletResponse response ){
        commingLog("downloadFile");
        LOGGER.info("入参：fileOriginName={},fileStoredName={},fileStoredPath={},map={}", fileOriginName,fileStoredName,fileStoredPath,map);
        Map retMap = new HashMap() ;

        retMap.put("fileOriginName",fileOriginName);
        retMap.put("fileStoredName",fileStoredName);
        retMap.put("fileStoredPath",fileStoredPath);
        retMap.put("map",map);

        //
        String tmpFileStoredPath = transUrl(fileStoredPath);
        if(! tmpFileStoredPath.endsWith("/")){
            tmpFileStoredPath = tmpFileStoredPath +"/" ;
        }
        String filePathWithName =   tmpFileStoredPath + fileStoredName ;
        LOGGER.info("filePathWithName={}",filePathWithName);
        retMap.put("filePathWithName",filePathWithName);

        /* ****************=====开始下载文件======****************** */
        File file = new File(filePathWithName);
        if(!file.exists()){
            retMap.put("status",Constants.STATUS_FAILURE);
            retMap.put("msg","文件不存在! file not found!");
            return retMap;
        }

        //设置文件名
        HttpServletResponse httpServletResponse = setDownFileName( response,fileOriginName);
         //读取服务器上的文件进行下载到本地
        try (
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpServletResponse.getOutputStream());
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        ) {
            byte[] buff = new byte[1024];
            int read = -1 ;
             while ((read = bufferedInputStream.read(buff, 0, buff.length))!=-1){
                 //读取多少就写多少
                bufferedOutputStream.write(buff,0, read);
            }
            retMap.put("status",Constants.STATUS_SUCCESS);
            retMap.put("msg","文件下载成功");
        } catch (Exception e) {
        }

        return retMap ;
    }
    /**
     *  文件上传
     * @param multipartFile  要上传的文件
     * @param fileId1         文件信息存储主键1
     * @param fileId2         文件信息存储主键2
     * @param fileUploadPath 文件要保存的路径
     * @param fileFromTab     上传的文件被哪张表用
     * @param paramsMap       文件上传非必要的其他参数
     *
     * @return
     */
    public static Map uploadFile(MultipartFile multipartFile, String fileId1, String fileId2,String fileUploadPath, String fileFromTab,Map paramsMap){
        commingLog("uploadFile");
        LOGGER.info("入参:multipartFile={},fileId1={},fileId2={},fileUploadPath={},tabName={},paramsMap={}"
                     ,multipartFile, fileId1, fileId2, fileUploadPath, fileFromTab, paramsMap);
        Map retMap =new HashMap<>();
        try {
            // 检查入参的合法性
            uploadFileCheckParams(multipartFile, fileId1, fileId2, fileUploadPath, fileFromTab,paramsMap);
            retMap.put("fileFromTab",fileFromTab);

            //获取文件原始名称
            String fileOriginName = multipartFile.getOriginalFilename();
            retMap.put("fileOriginName",fileOriginName) ;

            //文件扩展名
            String fileExtendName = fileOriginName.substring(fileOriginName.lastIndexOf(".")+1);
            retMap.put("fileExtendName",fileExtendName) ;

            //文件上传名
            String fileStoredName =DateUtil.parseDateToStr(new Date(),"yyyyMMddHHmmss")+"_"+ fileOriginName ;
            retMap.put("fileStoredName",fileStoredName) ;

            //文件上传全路径：文件上传路径 + 文件上传名
            String fileStoredPath = transUrl(fileUploadPath);
            if(! fileStoredPath.endsWith("/")){
                fileStoredPath = fileStoredPath +"/" ;
            }
            retMap.put("fileStoredPath",fileStoredPath) ;

            boolean mkdir = FileUtil.mkdirs(fileStoredPath);
            if(!mkdir){
                retMap.put("msg","fileStoredPath 路径不存在，并且创建失败！");
            }
            String filePathWithName =   fileStoredPath + fileStoredName ;
            retMap.put("filePathWithName",filePathWithName) ;

            //开始上传文件
            LOGGER.info("开始上传文件...");
            multipartFile.transferTo(new File(filePathWithName));
            LOGGER.info("文件上传成功！");
            retMap.put("msg","文件上传成功！") ;

            return retMap;
        } catch (Exception e) {
            retMap.put("msg","文件上传失败，失败原因为："+e.getMessage());
            return retMap ;
        }
    }






    private static Integer insertOneUploadedFile(Map retMap,Map paramsMap){
        UploadFile uploadFile = new UploadFile();

        uploadFile.setFileId1((String) retMap.get("fileId1"));
        uploadFile.setFileId2((String) retMap.get("fileId2"));

        uploadFile.setFileOriginName((String) retMap.get("fileOriginName"));
        uploadFile.setFileExtendName((String)retMap.get("fileExtendName"));
        uploadFile.setFileStoredName((String) retMap.get("fileStoredName"));
        uploadFile.setFileStoredPath((String)retMap.get("fileStorePath"));
        uploadFile.setFileFromTab((String)retMap.get("fileFromTab"));
        uploadFile.setCreateTime(new Date());

        return uploadFileService.insertOneUploadedFile(uploadFile);
     }

    /**
     *  文件上传 的参数合法性校验
     * @param multipartFile  要上传的文件
     * @param fileId1         文件信息存储主键1
     * @param fileId2         文件信息存储主键2
     * @param fileUploadPath 文件要保存的路径
     * @param paramsMap       文件上传非必要的其他参数
     *
     * @return
     */
    private static void uploadFileCheckParams(MultipartFile multipartFile, String fileId1, String fileId2,String fileUploadPath, String tabName,Map paramsMap) throws Exception{
            try{
                Assert.notNull(multipartFile,"文件接收失败[multipartFile is null]");
                Assert.isTrue(!StringUtils.isEmpty(fileId1),"文件主键 fileId1 不能为空");
                Assert.isTrue(!StringUtils.isEmpty(fileId2),"文件主键 fileId2 不能为空");
                Assert.isTrue(!StringUtils.isEmpty(fileUploadPath),"文件上传路径[fileUploadPath]不能为空");
                Assert.isTrue(!StringUtils.isEmpty(tabName),"文件出处表[tabName]不能为空");
            }catch (Exception e){
                LOGGER.error("文件上传失败：{}",e);
                e.printStackTrace();
                throw e ;
            }
    }

    public static boolean mkdirs(String dirPath){
        Assert.isTrue(!StringUtils.isEmpty(dirPath),"文件路径 dirPath 不能为空");
        dirPath = transUrl(dirPath);
        File file = new File(dirPath);

        //文件不存在，进行创建
        if(!file.exists()){
           return file.mkdirs();
        }
        // 文件已存在无需创建
        return true ;
    }
    public static boolean createNewFile(String filePath) {
        filePath = transUrl(filePath);
        File file = new File(filePath);
        if(!file.exists()){
            try {
                return file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true ;

    }
// **************************************************************
    /**
     * 获取路径的父目录
     * 示例：输入a/b/c/ 返回 a/b  ；输入a/b/c/d.txt  返回 a/b/c
     * @param file  文件路径
     * @return
     */
    public static String  getParDir(String  file) {
        File f  = new File(file);

      //去除路径后面的 路径符号
        if(file.endsWith("/")){
            file = file.substring(0,file.lastIndexOf("/"));
        }else if(file.endsWith("\\")){
            file = file.substring(0,file.lastIndexOf("\\"));
        }

        //获取父目录

        int i = file.lastIndexOf("\\");
        int i1 = file.lastIndexOf("/");
        int i2 = file.lastIndexOf(File.separator);

        int indexOf= Math.max(i2,Math.max(i,i1)) ;
        return file.substring(0,indexOf);
    }

//    /**
//     * 创建文件
//     * @param file  文件路径 C:\Users\Administrator\Desktop\a\b\ab.txt
//     * @return
//     */
    public static boolean createDirAndFile(String file){
        //创建文件目录
        String parDir = getParDir(file);
        boolean mkdirs = mkdirs(parDir);

        //创建文件目录下的文件
        if(mkdirs){
            return  createNewFile(file) ;
        }
        return false ;
    }
//    /**
//     * 创建文件
//     * @param file  文件路径 C:\Users\Administrator\Desktop\a\b\ab.txt
//     * @return
//     */
//    public static boolean createFile(String file){
//        //创建文件目录下的文件
//        File f  = new File(file);
//        if(!f.exists()){
//            try {
//                return f.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//                return  false ;
//            }
//        }else {
//            return true ;
//        }
//    }
//
//    /**
//     * 创建目录
//     * @param fileDir  文件目录 比如：C:\Users\Administrator\Desktop\python\a\b
//     * @return
//     */
//    public static boolean mkdirs(String fileDir){
//        File f  = new File(fileDir);
//        if(!f.exists()){
//            return f.mkdirs();
//        }else {
//            return true ;
//        }
//    }

}
