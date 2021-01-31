package hadoop.mr.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class WordCountDriver_type01 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //1. 获取环境配置信息
        Configuration hadoopConfig = new Configuration();
         //2. 获取job任务，并将环境配置信息给job
        //这种获取方式已经过时
        Job job = Job.getInstance(hadoopConfig);
        job.setJobName("WordCountDriver_type01");

        //3. 设置maptask任务的计算逻辑
        job.setMapperClass(WordcountMapper.class);

        //4  设置maptask任务的输出key类型，输出vlue类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //5. 设置reduceTask任务的计算逻辑
        job.setReducerClass(WordcountReducer.class);

        //6. 设置reduceTask任务的输入vaue类型，输出key类型，输出vlue类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //7. 设置job任务的数据来源(输入路径)
        //String inputPath = args[0];
        String inputPath = "D:\\hadoop\\input";
        FileInputFormat.setInputPaths(job,new Path(inputPath));

        //8. 设置job任务的数据处理结果落地路径(输出路径)   如果输出路径已经存在，则会报异常
        //String outputPath = args[1]; //args[1];
        String outputPath = "D:\\hadoop\\output";
        FileOutputFormat.setOutputPath(job, new Path(outputPath));

        //9. 环境和任务都准备好了，就可以提交任务了。 任务完成之前dirver主程序要进行阻塞等待
        boolean result = job.waitForCompletion(true);
        //10. 关闭dirver  入参是退出状态；  0-正常退出， 1-异常退出
        System.exit(result ? 0 : 1);
    }

}
