package com.utils.sourceUtils;

import org.apache.ibatis.io.DefaultVFS;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class PathUtils {
    private static final Log log = LogFactory.getLog(PathUtils.class);
    /**
     *  查找每个依赖jar包中 相同目录存在的资源文件路径
     *  file:/D:/software/java/ideaU/workspace/mavenweb/SSM/target/classes/com/mybatis_stu/mapper
     * @throws IOException
     */
    @Test
    public void getResourceList_01() throws IOException {
        String path ="com/mybatis_stu/mapper".replace(".","/") ;
        System.out.println("path= "+path); //com/mybatis_stu/mapper

        System.out.println("------------------------");
        //查找每个依赖jar包中 相同目录存在的资源文件路径
        Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(path);
        while (resources.hasMoreElements()){
            URL url = resources.nextElement();
            //file:/D:/software/java/ideaU/workspace/mavenweb/SSM/target/classes/com/mybatis_stu/mapper
            System.out.println(url);
            break;
        }
        System.out.println("------------------------");
    }

    /**
     * 查找每个依赖jar包中 相同目录存在的资源文件路径
     *
     * jar:file:/D:/software/java/jdk/jdk1.8/jdk/jre/lib/deploy.jar!/com
     * jar:file:/D:/software/java/jdk/jdk1.8/jdk/jre/lib/javaws.jar!/com
     * jar:file:/D:/software/java/jdk/jdk1.8/jdk/jre/lib/plugin.jar!/com
     * file:/D:/software/java/ideaU/workspace/mavenweb/SSM/target/test-classes/com
     * file:/D:/software/java/ideaU/workspace/mavenweb/SSM/target/classes/com
     */
    @Test
    public void getResourceList_02() throws IOException {
        String path ="org.apache.ibatis.builder".replace(".","/") ;
        System.out.println(path); //   org/apache/ibatis/builder

        //查找每个依赖jar包中 相同目录存在的资源文件路径
        Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(path);
        while (resources.hasMoreElements()){
            //System.out.println("=================start=================================");
            URL url = resources.nextElement();
            //jar:file:/D:/software/maven/RepMaven/org/mybatis/mybatis/3.2.6/mybatis-3.2.6.jar!/org/apache/ibatis/builder
            System.out.println(url);
//            System.out.println(url.getFile());
//
//            url = new URL(url.getFile());
//            System.out.println(url);
//            System.out.println(url.toExternalForm());

//            StringBuilder jarUrl = new StringBuilder(url.toExternalForm());
//            int index = jarUrl.lastIndexOf(".jar");
//            if (index >= 0) {
//                System.out.println(jarUrl);
//                jarUrl.setLength(index + 4); // .jar的四个长度
//                System.out.println("Extracted JAR URL: " + jarUrl.toString()); // file:/D:/software/java/ideaU/ideaIU-2018.1.4/lib/idea_rt.jar
//            }
          //  break;
        }
    }
    /**
     * 非jar项目工程
     * 读取某个文件路径下的所有文件名
     * OrderdetailMapper.xml
     * OrdersMapper.class
     * OrdersMapper.xml
     * UserMapper.class
     */
    @Test
    public void readFileName() throws IOException {
        String path ="file:/D:/software/java/ideaU/workspace/mavenweb/SSM/target/classes/com/mybatis_stu/mapper";
        URL url = new URL(path);
        String packagePath = "com.mybatis_stu.mapper" ;

        InputStream inputStream = url.openStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        for(String fileName;(fileName=bufferedReader.readLine())!=null;){
            System.out.println(fileName); //获取文件名
            System.out.println(packagePath + "." + fileName); //获取全限定类名
        }
    }

    /**
     * 读取某个jar包中 某个路径下 的各个文件项目路径
     *
     * /org/apache/ibatis/builder/xml/XMLConfigBuilder.class
     * /org/apache/ibatis/builder/xml/mybatis-3-config.dtd
     * /org/apache/ibatis/builder/xml/XMLMapperEntityResolver.class
     * /org/apache/ibatis/builder/xml/XMLIncludeTransformer.class
     * /org/apache/ibatis/builder/xml/mybatis-3-mapper.dtd
     * /org/apache/ibatis/builder/xml/XMLStatementBuilder.class
     * /org/apache/ibatis/builder/xml/XMLMapperBuilder.class
     * @throws IOException
     */
    @Test
    public void readFileNameFromJar() throws IOException {
        String filePath = "org.apache.ibatis.builder.xml".replace(".","/")  ;
        filePath = "/"+filePath ;
        String jarPath = "file:/D:/software/maven/RepMaven/org/mybatis/mybatis/3.2.6/mybatis-3.2.6.jar" ;

        //打开资源
        URL jarUrl = new URL(jarPath);
        InputStream is = jarUrl.openStream();
        JarInputStream jarInputStream = new JarInputStream(is);

        //JarEntry nextJarEntry = jarInputStream.getNextJarEntry();
        List<String> resources2 = new ArrayList<String>();
        for (JarEntry entry; (entry = jarInputStream.getNextJarEntry()) != null;) {
            if (!entry.isDirectory()) {
                // Add leading slash if it's missing
                String name = entry.getName();
                if (!name.startsWith("/"))
                    name = "/" + name;

               // System.out.println(name);
                // Check file name
                if (name.startsWith(filePath)) {
                    System.out.println(name);
                    resources2.add(name.substring(1)); // Trim leading slash
                }
            }
        }
    }
    /** The magic header that indicates a JAR (ZIP) file. */
    private static final byte[] JAR_MAGIC = { 'P', 'K', 3, 4 };
    protected boolean isJar(URL url) {
        return isJar(url, new byte[JAR_MAGIC.length]);
    }
    /**
     * 判断给定的URL是否是jar文件资源路径
     * Returns true if the resource located at the given URL is a JAR file.
     *
     * @param url The URL of the resource to test.
     * @param buffer A buffer into which the first few bytes of the resource are read. The buffer
     *            must be at least the size of {@link #JAR_MAGIC}. (The same buffer may be reused
     *            for multiple calls as an optimization.)
     */
    protected boolean isJar(URL url, byte[] buffer) {
        InputStream is = null;
        try {
            is = url.openStream();
            is.read(buffer, 0, JAR_MAGIC.length);
            if (Arrays.equals(buffer, JAR_MAGIC)) {
                log.debug("Found JAR: " + url);
                return true;
            }
        } catch (Exception e) {
            // Failure to read the stream means this is not a JAR
        } finally {
            try {
                is.close();
            } catch (Exception e) {
            }
        }

        return false;
    }


    //mybatis源码中使用的地方
    @Test
    public void a() throws IOException {
        String path ="com.mybatis_stu.domain" ;
        DefaultVFS defaultVFS = new DefaultVFS();
        List<String> list = defaultVFS.list(path);

        for (String s : list) {
            System.out.println(s);
        }
    }
}
