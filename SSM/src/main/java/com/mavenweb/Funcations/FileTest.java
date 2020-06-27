package com.mavenweb.Funcations;

import java.io.File;
import java.io.IOException;

public class FileTest {
    public static void main(String[] args) {
        String file = "C:\\Users\\Administrator\\Desktop\\python\\a\\b\\error.txt" ;
        File f  = new File(file);
        int i = file.lastIndexOf("\\");
        int i1 = file.lastIndexOf("/");
        int i2 = file.lastIndexOf(File.separator);
        System.out.println("File.separator="+File.separator);
        System.out.println("i= "+i);
        System.out.println("i1= "+i1);
        System.out.println("i2= "+i2);

        String substring = file.substring(0,Math.max(i,i1));
        System.out.println(substring);
        System.out.println("f.exists():"+f.exists());
        System.out.println("f.isFile()："+f.isFile());
        System.out.println("f.isDirectory()："+f.isDirectory());

    }



    //创建文件
    public boolean createFile(String file){
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
    //创建目录
    public boolean mkdirs(String fileDir){
        File f  = new File(fileDir);
        if(!f.exists()){
                return f.mkdirs();
        }else {
            return true ;
        }
    }
}
