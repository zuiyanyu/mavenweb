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
 * 连接本地的资源，local模式
 */
public class WordCountDriver_local {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        args = new String[] {"D:\\hadoop\\input","D:\\hadoop\\output3"};
        //1. 获取环境配置信息
        Configuration hadoopConfig = new Configuration();
        hadoopConfig.set("mapreduce.job.reduces","3");
         //2. 获取job任务，并将环境配置信息给job
        //这种获取方式已经过时
        Job job = Job.getInstance(hadoopConfig);

        //TODO  设置驱动位置(设置jar加载路径)，
        // 否则集群运行，报异常：java.lang.ClassNotFoundException: Class hadoop.mr.wordcount.WordcountMapper not found
        job.setJarByClass(WordCountDriver_local.class);

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
