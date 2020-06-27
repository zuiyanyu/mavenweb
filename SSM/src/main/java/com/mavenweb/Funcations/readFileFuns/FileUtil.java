package com.mavenweb.Funcations.readFileFuns;

import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileUtil {
    public static void main(String[] args) {
        String file = "C:\\Users\\Administrator\\Desktop\\python\\a\\b\\ab.txt" ;

        System.out.println(getParDir(file));
        boolean file1 = createFile(file);
        System.out.println(file1);
    }

    public static String convertPath(String path) throws FileNotFoundException {
        if(StringUtils.isEmpty(path)){
            throw new FileNotFoundException("文件路径不能传空参：path ["+path+"]") ;
        }
        path = path.replaceAll("\\\\","/");
        return  path ;
    }

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
    /**
     * 创建文件
     * @param file  文件路径 C:\Users\Administrator\Desktop\a\b\ab.txt
     * @return
     */
    public static boolean createDirAndFile(String file){
        //创建文件目录
        String parDir = getParDir(file);
        boolean mkdirs = mkdirs(parDir);

        //创建文件目录下的文件
        if(mkdirs){
            return  createFile(file) ;
        }
        return false ;
    }
    /**
     * 创建文件
     * @param file  文件路径 C:\Users\Administrator\Desktop\a\b\ab.txt
     * @return
     */
    public static boolean createFile(String file){
        //创建文件目录下的文件
        File f  = new File(file);
        if(!f.exists()){
            try {
                return f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return  false ;
            }
        }else {
            return true ;
        }
    }

    /**
     * 创建目录
     * @param fileDir  文件目录 比如：C:\Users\Administrator\Desktop\python\a\b
     * @return
     */
    public static boolean mkdirs(String fileDir){
        File f  = new File(fileDir);
        if(!f.exists()){
            return f.mkdirs();
        }else {
            return true ;
        }
    }
}
