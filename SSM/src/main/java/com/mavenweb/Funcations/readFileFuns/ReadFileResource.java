package com.mavenweb.Funcations.readFileFuns;

import java.io.*;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;

/**
 * 文件读取： 读取工程中的不同目录下的文件使用的方式是不一样的。
 */
public class ReadFileResource {
    public static void main(String[] args) throws IOException {
        ///E:/software/maven/RepMaven/cglib/cglib-nodep/2.1_3/cglib-nodep-2.1_3.jar!/asm-license.txt
//        getResourceFilePath(Object.class, "/asm-license.txt");
//
//        getResourceFilePath(CurveDB.class, "/source_tips");
////
//        String classPath = getClassesPath(CurveDB.class);
//        System.out.println(classPath);
//
//        classPath = getResourceRootPath(Attribute.class);
//        System.out.println(classPath);
        getResource();
    }

    public static void getResource() throws IOException {
        Class clazz = ReadFileResource.class ;
        URL url = clazz.getResource("/asm-license.txt");
        String filePath = url.getPath();
        System.out.println("clazz.getResource资源文件所在的路径为："+filePath);
//        StringBuffer stringBuffer = reeadResourceFile(ReadFileResource.class,"asm-license.txt");
//          System.out.println(stringBuffer.toString());

    }

    /**
     * Java 获得Class的绝对路径方法
     *
     * 说明：
     *      1.问题： Java获得class文件的绝对路径：1.e.g. Foo.class => Foo.class.getResuorce("").getFile();
     *         该方法在eclipse中或未jar打包时，可以得到，但如果打包的话，会显示Null. （security domain问题）；
     *      2. 解决方案：Foo.clas.getProtectionDomain().getCodeSource().getLocation().getFile()来访问jar中Class文件；
     *
     * @param clazz  java 类的Class类
     * @return
     * /D:/software/java/ideaU/workspace/mavenweb/SSM/target/classes/
     * /E:/software/maven/RepMaven/cglib/cglib-nodep/2.1_3/cglib-nodep-2.1_3.jar
     */
    public static String getClassesPath(Class<?> clazz){
       // String path = clazz.getProtectionDomain().getCodeSource().getLocation().getPath();
        //1. 获取类的保护域
        ProtectionDomain domain = clazz.getProtectionDomain();
        if (domain == null) return null;

        //2. 获取该域的CodeSource
        CodeSource codeSource = domain.getCodeSource();
        if (codeSource == null) return null;

        //3. 获取CodeSource的资源路径
        URL location  = codeSource.getLocation();
        if(location == null) return null;

        //4. 得到这 URL文件名。
        String codeLocation = location.getFile();
        return codeLocation ;
    }
    /**
     * 获取一个资源文件的路径
     * 1.getResouce()方法的参数，你以class为出发点，结合相对路径的概念，就可以准确地定位资源文件了，
     * 至于它的根目录嘛，你用不同的IDE build出来是不同的位置下的，不过都是以顶层package作为根目录
     * 2.在Web应用中，有一个WEB-INF的目录，WEB-INF目录里面除了web.xml文件外，还有一个classes目录，
     * 它就是你这个WEB应用的package的顶层目录，也是所有.class的根目录“/”，假如clasaes目录下面有一个file.txt文件，
     * 它的相对路径就是"/file.txt"，如果相对路径不是以"/"开头，那么它就是相对于.class的路径。。
     *
     * @param clazz
     * @param fileName
     * @return
     * @throws FileNotFoundException
     */
    public static String getResourceFilePath(Class<?> clazz, String fileName) throws FileNotFoundException {
    URL resource = clazz.getResource(fileName);
    if(null ==resource){
        throw new FileNotFoundException(fileName) ;
    }
    String filePath = resource.getFile();
    System.out.println("资源文件所在的路径为："+filePath);
    return  filePath ;
}
    /**
     * 读取当前工程资源目录下的文件。
     * 说明：
     *   1. 文件可以是当前工程根目录下的资源文件；
     *   2. 文件可以是class类目录下的资源文件：比如：/com/mavenweb/Funcations/readFileFuns/file1.txt
     *   3. 文件可以是其他jar包根目录下的资源文件。
     *      a. 比如 加载 cglib-nodep-2.1_3.jar!/asm-license.txt ，fileName传参为：/asm-license.txt ;
     *      b. 但是如果其他 jar包在相同位置也有相同文件，那么就不一定读取哪一个jar包下的文件了。
     *      c. 如果当前工程在相同位置也有相同文件，那么就优先读取本工程下的这个文件。
     *
     *  结论：所以  Class.getResourceAsStream()只适合加载本工程下的资源文件； 但是加载其他jar包下的资源文件可能会有误；
     *
     * example:
     *       StringBuffer stringBuffer = reeadResourceFile(ReadFileResource.class,"file3.txt");
     *         System.out.println(stringBuffer.toString());
     *
     *         stringBuffer = reeadResourceFile(ReadFileResource.class,"/com/mavenweb/Funcations/readFileFuns/file1.txt");
     *         System.out.println(stringBuffer.toString());
     *
     *         stringBuffer = reeadResourceFile(ReadFileResource.class,"/file/file4.txt");
     *         System.out.println(stringBuffer.toString());
     *
     *         stringBuffer = reeadResourceFile(ReadFileResource.class,"/asm-license.txt");
     *         System.out.println(stringBuffer.toString());
     *
     * @param clazz      当前工程下的一个class类
     * @param fileName   要读取的文件
     * @return
     * @throws IOException
     */
    public static StringBuffer reeadResourceFile(Class<?> clazz, String fileName) throws IOException {
        //src 为根目录 ,对应打包的 classes 目录
         String tmpName = fileName;
         if( !fileName.startsWith("/")){
            tmpName = "/"+fileName ; //"asm-license.txt";
        }

        //获取资源文件所在的路径：比如 file:/E:/software/maven/RepMaven/cglib/cglib-nodep/2.1_3/cglib-nodep-2.1_3.jar!/asm-license.txt
        //getResourceFilePath(clazz,"asm-license.txt");
        getResourceFilePath(clazz,fileName);

        //读取资源文件所在的路径
        InputStream resourceAsStream = ReadFileResource.class.getResourceAsStream(tmpName);
        if(null ==resourceAsStream){
            throw new FileNotFoundException("file not exists: "+fileName) ;
        }
        InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line = "";

        StringBuffer buf = new StringBuffer() ;
        while ((line =bufferedReader.readLine())!=null){
            buf.append(line).append("\n") ;
        }
        return buf ;
    }

    /**
     * 获取项目的根路径
     *     读取 src/resource/file3.txt
     *     测试结果可以看到:
     *         1. src 是项目工程的根目录  / , 就是打包后的 class目录
     *         2. main.resource 和 main.java 目录下的文件都会打包到根目录下
     */
    public static  void readFileFromResourcePath(){
        //方式1
        URL resource = ReadFileResource.class.getResource("/");
        System.out.println(resource); //file:/D:/software/java/ideaU/workspace/mavenweb/SSM/target/classes/

        URL resource2 = ReadFileResource.class.getResource("/file3.txt");
        System.out.println(resource2);  //file:/D:/software/java/ideaU/workspace/mavenweb/SSM/target/classes/file3.txt

        //1获取项目的根路径
        String filePath = ReadFileResource.class.getResource("/").getFile();
        System.out.println("filePath: "+filePath); // filePath: /D:/software/java/ideaU/workspace/mavenweb/SSM/target/classes/

        //方式2
//        ReadFileResource.class.getResourceAsStream("/")
    }
}

