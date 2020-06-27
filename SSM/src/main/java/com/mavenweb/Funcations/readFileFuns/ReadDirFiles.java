package com.mavenweb.Funcations.readFileFuns;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 读取一个文件目录下的每个文件，并统计每个文件的行数
public class ReadDirFiles {
    public static void main(String args[]) throws IOException {
        String  dirPath = "D:\\software\\java\\ideaU\\workspace\\mavenweb\\SSM\\src\\main\\java\\com\\mavenweb\\service\\" ;

        List<Map> fileList = getFileList(dirPath,".java");

    }
    public static List<Map> getFileList(String dirPath ,String extendName){
        dirPath = "D:\\software\\java\\ideaU\\workspace\\mavenweb\\SSM\\src\\main\\java\\com\\mavenweb\\service\\" ;
        File filePath = new File(dirPath);

        System.out.println("=====================");
//        FilenameFilter filter = new FilenameFilter() {
//            @Override
//            public boolean accept(File dir, String name) {
////                System.out.println("[dir = "+ dir +"],[name =" + name+"]");
//                 if(null ==extendName ||name.endsWith(extendName)){
//                    return true ;
//                }else{
//                    return false ;
//                }
//            }
//        };
        FilenameFilter filter =(dir,name)->{
            if(null ==extendName ||name.endsWith(extendName)){
                return true ;
            }else{
                return false ;
             }
        } ;
        List<Map> codeList = new ArrayList<>() ;
        File[] files = filePath.listFiles(filter);
        for (File file : files) {
            Map map = new HashMap() ;
            try {
                String fileName = file.getName();
                //System.out.println(fileName + "     "+ getFileRow(file));
                map.put("模块/程序名称",fileName);
                map.put("代码行数", getFileRow(file));
                map.put("程序语言类型","java") ;
                map.put("走查工时","0.1");
                codeList.add(map);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(codeList !=null && codeList.size() >0){
            for (Map map : codeList) {
                System.out.println(map);
            }
        }
        return codeList ;
    }

    public static Integer getFileRow(File file) throws IOException {
        int count = 0 ;
        InputStream fileInputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String line ;
        while ((line=bufferedReader.readLine())!=null){
            count ++ ;
        }

        return count ;
    }
}
