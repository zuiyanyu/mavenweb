package com.LoadResource;

import com.github.pagehelper.StringUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 * 需求描述：加载整个项目或者依赖包中相同目录下class的资源
 * 第一步：搜索相同目录的的URL.
 * 第二步：读取非jar包URL目录下的Class文件名称
 * 第三步：读取jar包URL目录下的Class文件名称
 */
public class LoaderClassUtil {
    Logger log = LoggerFactory.getLogger(LoaderClassUtil.class);
    /**
     *第一步：搜索相同目录的的URL资源.
     *  //入参 String classPackage  ,出参 Enumeration<URL>  resources
     * 示例：
     * classPackage = "com/LoadResource/"
     * file:/D:/software/java/ideaU/workspace/mavenweb/SSM/target/classes/com/LoadResource/willLoadedClass/
     * file:/D:/software/java/ideaU/workspace/mavenweb/app-java/target/classes/com/LoadResource/willLoadedClass/
     *
     * classPackage = "com/LoadResource/willLoadedClass/Common.class"
     * file:/D:/software/java/ideaU/workspace/mavenweb/SSM/target/classes/com/LoadResource/willLoadedClass/Common.class
     * file:/D:/software/java/ideaU/workspace/mavenweb/app-java/target/classes/com/LoadResource/willLoadedClass/Common.class
     */
    @Test
    public void listURL() throws IOException {
        //入参 包名
        String classPackage = "com.LoadResource.willLoadedClass" ;
        classPackage = "com.mchange.v2.c3p0" ;
        classPackage = classPackage.replaceAll("\\.","/");
        System.out.println(classPackage);
        //classPackage = "com/LoadResource/";
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();

        System.out.println("查找具有相同目录的资源:");
        //如果 入参name以 "/"结尾，name返回结果RUL也是以 "/"结尾；否则结果URL不会以 "/"结尾；
        Enumeration<URL> resources = contextClassLoader.getResources(classPackage);
        while(resources.hasMoreElements()){
            URL url = resources.nextElement();
            System.out.println(url);
        }
    }
    //第二步：读取非jar包URL目录下的Class文件名称
    @Test
    public void ListClassName() throws MalformedURLException {
        //入参1：包名称  ，入参2：URL
        String classPackage ="com.LoadResource.willLoadedClass";
        String urlName = "file:/D:/software/java/ideaU/workspace/mavenweb/SSM/target/classes/com/LoadResource/willLoadedClass/";
        URL url = new URL(urlName);
        System.out.println("URL = "+ url);

        List<String> classNames =new ArrayList<>() ;
        try(InputStream inputStream = url.openStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));
            )
        {
            for(String fileName;(fileName=bufferedReader.readLine())!=null;){
                //筛选我们要操作的文件：.class 文件  我们简单获取一级目录下的.class文件，如果多级目录，可递归调用
                if(fileName.endsWith(".class")){
                    if(!StringUtil.isEmpty(fileName)){
                        classNames.add(classPackage+"."+fileName);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("要加载的类："+classNames);
    }

    //第三步：读取jar包URL目录下的所有以classPackage开头的.class类全路径名
    @Test
    public void getClassFromJar() throws IOException {
        String classPackage ="com.mchange.v2.c3p0.impl";
        String urlName = "file:/D:/software/java/ideaU/workspace/mavenweb/SSM/target/classes/com/LoadResource/willLoadedClass/";
        urlName= "file:/D:/software/maven/RepMaven/com/mchange/c3p0/0.9.5.2/c3p0-0.9.5.2.jar";
        URL jarUrl = new URL(urlName);

        InputStream is = jarUrl.openStream();
        JarInputStream jarInputStream = new JarInputStream(is);
        System.out.println(jarInputStream);
//        JarEntry entry1 = jarInputStream.getNextJarEntry();
//        System.out.println(entry1);

        List<String> jarClassList = new ArrayList<String>();
        String path = classPackage.replaceAll("\\.","/");
        if (!path.endsWith("/"))
            path = path + "/";

        for (JarEntry entry; (entry = jarInputStream.getNextJarEntry()) != null;) {
            if (entry.getName().startsWith(path) && !entry.isDirectory()) {
                // Add leading slash if it's missing
                String name = entry.getName();
                if (name.startsWith(path)) {
                    jarClassList.add(name); // Trim leading slash
                }
            }
        }
        for (String name : jarClassList) {
            System.out.println(name.replaceAll("/","."));
        }

    }
    /**
     * 入参：URL
     * 判断URL是否是jar包中的资源
     */
    @Test
    public void findJarForResource() throws IOException {
        String a = "com/mchange/v2/c3p0/impl/ThreadLocalQuerylessTestRunner.class";
        String s = a.replaceAll("/", ".");
        System.out.println(s);
        //实际逻辑参考  mybatis的 DefaultVFS.java的 protected URL findJarForResource(URL url) 方法。
    }
}
