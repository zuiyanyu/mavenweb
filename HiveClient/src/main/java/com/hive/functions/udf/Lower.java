package com.hive.functions.udf;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * 1）Hive 自带了一些函数，比如：max/min等，但是数量有限，自己可以通过自定义UDF来方便的扩展。
 * 2）当Hive提供的内置函数无法满足你的业务处理需要时，此时就可以考虑使用用户自定义函数（UDF：user-defined function）。
 * 3）根据用户自定义函数类别分为以下三种：
 * 	（1）UDF（User-Defined-Function）
 * 		一进一出
 * 	（2）UDAF（User-Defined Aggregation(聚集) Function）
 * 		聚集函数，多进一出
 * 		类似于：count/max/min
 * 	（3）UDTF（User-Defined Table-Generating(生成) Functions）
 * 		一进多出
 * 		如lateral view explode()
 * 4）官方文档地址
 * https://cwiki.apache.org/confluence/display/Hive/HivePlugins
 * 5）编程步骤：
 * 	（1）继承org.apache.hadoop.hive.ql.UDF
 * 	（2）需要实现evaluate函数；evaluate函数支持重载；
 * 	（3）在hive的命令行窗口创建函数
 * 		a）添加jar
 * add jar linux_jar_path
 * 		b）创建function
 * create [temporary] function [dbname.]function_name AS class_name;
 *
 * create temporary function mylower as "com.hadoop.hive.Lower";
 *
 * 	（4）在hive的命令行窗口删除函数
 * Drop [temporary] function [if exists] [dbname.]function_name;
 * 6）注意事项
 * 	（1）UDF必须要有返回类型，可以返回null，但是返回类型不能为void；
 *
 * 	==============================
 * 实例：	比如自定义UDF函数
 * 2．导入依赖
 * <dependencies>
 * <dependency>
 * <groupId>org.apache.hive</groupId>
 * <artifactId>hive-exec</artifactId>
 * <version>1.2.1</version>
 * </dependency>
 * </dependencies>
 *
 * 4．打成jar包上传到服务器/opt/module/jars/udf.jar
 *
 * 5．将jar包添加到hive的classpath
 * hive (default)> add jar /opt/module/datas/udf.jar;
 *
 * 6．创建临时函数与开发好的java class关联
 * hive (default)> create temporary function mylower as "com.hive.functions.udf.Lower";
 *
 * 7．即可在hql中使用自定义的函数strip
 * hive (default)> select ename, mylower(ename) lowername from emp;
 */
public class Lower extends UDF {
    public String evaluate (final String s) {
        if (s == null) {
            return null;
        }
        return s.toLowerCase();
    }
}
