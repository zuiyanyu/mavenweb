package com.spring_stu.spring_utils;

import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;

//路径资源解析器
public class PathMatchingResourcePatternResolverTest  {
   private final PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();

    @Test
    public void getResources() throws IOException {
        //获取当前项目中"com/spring_stu/dao/" 类路径下的所有资源文件(.class类文件或者类目录)
        //比如 ：file [D:\software\java\ideaU\workspace\mavenweb\SSM\target\classes\com\spring_stu\dao\AccountDao.class] （class文件）
        //       file [D:\software\java\ideaU\workspace\mavenweb\SSM\target\classes\com\spring_stu\dao\daoImpl] (类文件所在目录)
        String classPattern01 ="classpath:" + "com/spring_stu/dao/*";

        //获取当前项目中"com/spring_stu/dao/" 类路径下的所有.class结尾的资源文件
        String classPattern02 ="classpath:" + "com/spring_stu/dao/" + "*.class";

        // 获取当前项目中"com/spring_stu/dao/" 目录下的一级文件目录中的.class结尾的资源文件，
        // 但是不会获取"com/spring_stu/dao/" 这个目录下的.class资源文件。
        //TODO "*" 只能代表一层文件目录，表示目录名称任意。
        /*
            示例：获取"com/spring_stu/dao/" 这个目录下,daoImpl和test2目录下的类文件资源。
            file [D:\software\java\ideaU\workspace\mavenweb\SSM\target\classes\com\spring_stu\dao\daoImpl\JNDIBaseDAOImpl.class]
            file [D:\software\java\ideaU\workspace\mavenweb\SSM\target\classes\com\spring_stu\dao\test2\Hellow.class]
         */
        String classPattern03 ="classpath*:" + "com/spring_stu/dao/*/" + "*.class";

        //"**" 匹配多层级的目录
        //file [D:\software\java\ideaU\workspace\mavenweb\SSM\target\classes\com\spring_stu\dao\daoImpl\test\Hellow.class]
        String classPattern04 ="classpath*:" + "com/spring_stu/**/test/" + "*.class";

        //获取com/spring_stu/dao/ ，以及impl/ 等子目录下的所有.class文件资源
        String classPattern05 ="classpath*:" + "com/spring_stu/dao/**/" + "*.class";

        //打印资源的资源路径(绝对路径)
        Resource[] resources = patternResolver.getResources(classPattern05);
        for (Resource resource : resources) {
            System.out.println(resource);
        }
    }
}
