package com.mavenweb.Funcations;

import com.mavenweb.utils.FileUtil;

import java.io.*;

/**
 * PrintWriter 应用场景：当程序运行出现异常的时候，可以将异常信息打印到指定的文件
 */
public class PrintWriterTest {
    private static String errorFile = "C:\\Users\\Administrator\\Desktop\\python\\error.txt" ;
    private static PrintWriter errorWriter= null;
   static {
       FileUtil.createDirAndFile(errorFile) ;
        try {
            errorWriter = new PrintWriter(new FileOutputStream(errorFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)  {
        String infoFile = "C:\\Users\\Administrator\\Desktop\\python\\IO.txt" ;
        writeInfoToFile(infoFile);
    }

    /*
     * 创建输入流，将信息读到控制台
     */
    public static void readInfoToConsole(String file){
        try(  InputStream is = new FileInputStream(file);
              InputStreamReader isr = new InputStreamReader(is);
              BufferedReader br = new BufferedReader(isr);
              )
        {
            String info = null;
            info = br.readLine();
            while (info != null) {
                System.out.println(info);
                info = br.readLine();
            }
        } catch (FileNotFoundException e) {

            errorWriter.write("文件不存在："+e);
        } catch (IOException e) {
            errorWriter.println("IO异常："+e);
        }
    }
    /*
     * 创建输出流，将信息写入指定的文件中
     */
    public  static void  writeInfoToFile(String file){
        try ( OutputStream os = new FileOutputStream(file);
               PrintWriter pw = new PrintWriter(os);
              )
        {

            pw.write("小帅哥");
            pw.append(" 你真帅~");
            pw.println("我稀罕你");
            pw.write("我爱你");
            errorWriter.write("helllo");
            //输出流需要在读取之前关闭保存
          } catch (FileNotFoundException e) {
             errorWriter.println("文件不存在："+e);
             errorWriter.flush();
         } catch (IOException e) {
            e.printStackTrace();
            errorWriter.println("IO异常："+e);
            errorWriter.flush();
        }
    }
}
