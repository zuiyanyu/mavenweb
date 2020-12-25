package com.LoadResource;

import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * 我们需要读取各种文件，各个目录下的文件
 */
public class LoaderResource {

    /**
     * TODO Thread.currentThread().getContextClassLoader().getResourceAsStream()
     * TODO 即 ClassLoader类的 getResourceAsStream(String name)
     * 1.可以加载相对于当前项目classpath目录下的文件 比如：SSM/target/classes/file1.txt
     *
     */
    @Test
    public void type01(){
        //1.使用相对于当前项目的classpath的相对路径来查找资源。
        String filePath ="file1.txt";
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(contextClassLoader);// sun.misc.Launcher$AppClassLoader@18b4aac2

        InputStream resourceAsStream = contextClassLoader.getResourceAsStream(filePath);
        printFile(resourceAsStream);//file1.txt:相对于classpath路径下的文件

    }

    /**
     * TODO  Class类的getResourceAsStream(name) 加载文件，返回InputStream流。
     * 1. 以"/" 开头，加载当前项目classpath目录下的资源文件。
     * 2. 不是以"/"开头，加载当前项目 Class<T> T.class所在目录下的资源那件。
     */
    @Test
    public void type02(){
        //不是以"/"开头，所以是加载相对于.class目录下的资源文件
        String filePath ="file1.txt";
        Class<LoaderResource> loaderResourceClass = LoaderResource.class;
        InputStream resourceAsStream = loaderResourceClass.getResourceAsStream(filePath);
        printFile(resourceAsStream);//file1.txt:相对于com/LoadResource/路径下的文件

    }

    /**
     * TODO ResourceBundle类的getBundle(String path) 国际化文件加载
     * 1.可以读取相对于当前项目classpath目录下的属性文件
     */
    @Test
    public void type03()   {
        //1.使用相对于当前项目的classpath的相对路径来查找资源。
        String filePath ="datasource";//基础名即文件名，不能带后缀名
        ResourceBundle bundle = PropertyResourceBundle.getBundle(filePath);
        String value = bundle.getString("mysql.driver");
        System.out.println(value); //com.mysql.jdbc.Driver
    }


    /**
     * TODO Class.getResource和ClassLoader.getResource的区别和底层原理
     * Class.getResource(String name) ：
     *      1. 如果name以 "/"开头，就到相对于classpath根目录(target/classes/)下找文件资源。
     *          等价于ClassLoader.getResource(String name)
     *      2. 如果name不以"/"开头，就到相对于Class代表的.class所在目录下找资源。
     *
     * ClassLoader.getResource(String name):到相对于classpath根目录(target/classes/)下找文件资源。
     */
    @Test
    public void type04(){
        //file:/D:/software/java/ideaU/workspace/mavenweb/SSM/target/classes/com/LoadResource/
        System.out.println(LoaderResource.class.getResource("/"));//到classpath目录(target/classes/)下找资源
        System.out.println(LoaderResource.class.getClassLoader().getResource(""));//到根目录下找资源
        System.out.println("-------------------------");

        /*TODO 没以"/"开头： 到LoaderResource.class的所在目录(target/classes/com/LoadResource/) 下寻找spring-mvc.xml
          返回结果：.class类路径下的spring-mvc.xml的URL路径
          file:/D:/software/java/ideaU/workspace/mavenweb/SSM/target/classes/com/LoadResource/spring-mvc.xml
         */
        System.out.println(LoaderResource.class.getResource("spring-mvc.xml"));
        /*TODO 以"/"开头：到classpath根目录(target/classes/)下寻找 spring-mvc.xml
         返回结果：classpath根目录下的spring-mvc.xml的URL路径
            file:/D:/software/java/ideaU/workspace/mavenweb/SSM/target/classes/spring-mvc.xml
         */
        System.out.println(LoaderResource.class.getResource("/spring-mvc.xml"));

        System.out.println();

        //file:/D:/software/java/ideaU/workspace/mavenweb/SSM/target/classes/spring-mvc.xml
        System.out.println(LoaderResource.class.getClassLoader().getResource("spring-mvc.xml"));
        //null  入参不以"/"开头，已经代表相对于classpath根目录下寻找资源
        System.out.println(LoaderResource.class.getClassLoader().getResource("/spring-mvc.xml"));
    }

    /**
     * ClassLoader实例比Class类对象多了一个获取资源文件目录的方法。
     * TODO ClassLoader的getResources(name) 获取所有的资源目录
     *     public Enumeration<URL> getResources(String name)
     *
     * 此方法用途：可以获取本项目以及依赖包相同目录的URL资源，然后加载每个URL下的资源进行处理，比如加载class类。
     */
    @Test
    public void type05() throws IOException {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();

        System.out.println("查找具有相同目录的资源:");
        //如果 入参name以 "/"结尾，name返回结果RUL也是以 "/"结尾；否则结果URL不会以 "/"结尾；
        Enumeration<URL> resources = contextClassLoader.getResources("com/LoadResource");
        while(resources.hasMoreElements()){
            URL url = resources.nextElement();
            System.out.println(url);
        }

        System.out.println("查找具有相同目录和文件名的资源:");
        resources = contextClassLoader.getResources("com/LoadResource/file1.txt");
        while(resources.hasMoreElements()){
            URL url = resources.nextElement();
            System.out.println(url);
        }
    }

    /**
     * TODO 使用URLClassLoader的findResource(name)进行资源查询.
     *       从classpath根目录下进行资源的查询，以URL的形式返回。
     *  TODO 等价于 ClassLoader的findResource(name)
     */
    @Test
    public void type06(){
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        URLClassLoader urlClassLoader = (URLClassLoader)contextClassLoader;
        /*
            从classpath根目录下进行资源的查询，以URL的形式返回。
            比如：
            返回结果：file:/D:/software/java/ideaU/workspace/mavenweb/SSM/target/classes/file1.txt
         */
        URL resource = urlClassLoader.findResource("file1.txt");
        System.out.println(resource);

    }


    @Test
    public void test(){
        Class<LoaderResource> loaderResourceClass = LoaderResource.class;
        ClassLoader classLoader = loaderResourceClass.getClassLoader();
        //sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println("系统类加载器："+classLoader);
        //sun.misc.Launcher$ExtClassLoader@4769b07b
        System.out.println("系统类加载器的父(扩展类加载器)："+classLoader.getParent());
        //null
        System.out.println("扩展类加载器的父(引导类加载器)：："+classLoader.getParent().getParent());


    }

    private void printFile(InputStream inputStream) {
        try(InputStreamReader inputStreamReader = new InputStreamReader(new BufferedInputStream(inputStream));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);)
        {
            String line ;
            while((line=bufferedReader.readLine()) !=null){
                System.out.println(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
     }

}
