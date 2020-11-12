package com.utils.sourceUtils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class PathUtil {
private static final Logger LOGGER = LoggerFactory.getLogger(PathUtil.class) ;
    /**
     *  查找每个依赖jar包中 相同目录存在的资源文件路径
     *  file:/D:/software/java/ideaU/workspace/mavenweb/SSM/target/classes/com/mybatis_stu/mapper
     * @throws IOException
     */

    /**
     * 查找每个依赖jar包中 相同目录存在的资源文件路径
     *
     * @param resourcePath  资源文件的路径 比如：/com/a/b/c/d
     * @return Enumeration<URL> 每个依赖jar包中,具有相同资源路径的 URL集合。
     *          比如(具有相同的资源路径：/com/a/b/c/d )：
     *          file:/D:/software/java/ideaU/workspace/mavenweb/SSM/target/classes/com/a/b/c/d
     *          jar:file:/D:/software/maven/RepMaven/org/mybatis/mybatis/3.2.6/mybatis-3.2.6.jar!/com/a/b/c/d
     * @throws IOException
     */
    public static List<URL> getResourceList(String resourcePath) throws IOException {
        String path = resourcePath.replace(".","/")  ;
        LOGGER.info("path={}",path);

        //查找每个依赖jar包中 相同目录存在的资源文件路径
        Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(path);

        List<URL> urlList = new ArrayList<>() ;
        while (resources.hasMoreElements()){
            URL url = resources.nextElement();//file:/D:/software/java/ideaU/workspace/mavenweb/SSM/target/classes/com/mybatis_stu/mapper
            urlList.add(url);
        }
        return urlList ;
    }

//    /**
//     * 获取当前项目的
//     * @param resourcePath
//     * @return
//     */
//    public static URL getResource(String resourcePath){
//
//    }

}
