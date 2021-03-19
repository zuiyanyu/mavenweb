package hadoop.mr.wordcount01;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 集群的资源，yarn集群模式
 */
public class WordCountDriver_yarn {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //设置提交任务的用户名
        System.setProperty("HADOOP_USER_NAME","hadoop");
        //1. 获取环境配置信息
        Configuration hadoopConfig = new Configuration();
        //执行要在yarn上运行
        hadoopConfig.set("mapreduce.framework.name","yarn");
        //指定NameNode的地址
        hadoopConfig.set("fs.defaultFS","hdfs://hadoop102:9000");
        //指定resourcemanager的地址
        hadoopConfig.set("yarn.resourcemanager.hostname","hadoop103");
        //如果true,可以提交一个windows client 到linux集群服务
        //submit an application from a Windows client to a Linux/Unix server
        hadoopConfig.set("mapreduce.app-submission.cross-platform","true");


         //2. 获取job任务，并将环境配置信息给job
        //这种获取方式已经过时
        Job job = Job.getInstance(hadoopConfig);

        //TODO  设置驱动位置(设置jar加载路径)，
        // 否则集群运行，报异常：java.lang.ClassNotFoundException: Class hadoop.mr.wordcount.WordcountMapper not found
//        job.setJarByClass(WordCountDriver_yarn.class); // local 本地运行  指定 Dirver的 classpath
        //TODO 提交到yarn集群运行，需要指定jar包的位置
        job.setJar("D:\\software\\java\\ideaU\\workspace\\mavenweb\\HadoopClient\\target\\HadoopClient.1.0-SNAPSHOT.jar");
        //3. 设置maptask任务的计算逻辑
        job.setMapperClass(WordcountMapper.class);

        //4  设置maptask任务的输出key类型，输出vlue类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //5. 设置reduceTask任务的计算逻辑
        job.setReducerClass(WordcountReducer.class);

        //6. 设置reduceTask任务的输出key类型，输出vlue类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //7. 设置job任务的数据来源(输入路径)
        String inputPath = args[0];
//        String inputPath = StringUtils.isEmpty(args[0])? "D:\\hadoop\\input":args[0];;
        FileInputFormat.setInputPaths(job,new Path(inputPath));

        //8. 设置job任务的数据处理结果落地路径(输出路径)   如果输出路径已经存在，则会报异常
        String outputPath = args[1]; //args[1];
//        String outputPath = StringUtils.isEmpty(args[1])? "D:\\hadoop\\output":args[1];
        FileOutputFormat.setOutputPath(job, new Path(outputPath));

        //9. 环境和任务都准备好了，就可以提交任务了。 任务完成之前dirver主程序要进行阻塞等待
        boolean result = job.waitForCompletion(true);
        //10. 关闭dirver  入参是退出状态；  0-正常退出， 1-异常退出
        System.exit(result ? 0 : 1);
    }

}
