package com.hive.ETL.mr.videoETL;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

/**
 * 原始操作：
 * Job job = Job.getInstance(new Configuration());
 *
 *         job.setJarByClass(ETLDriver.class);
 *
 *         job.setMapperClass(ETLMapper.class);
 *         job.setNumReduceTasks(0);
 *
 *         job.setMapOutputKeyClass(Text.class);
 *         job.setMapOutputValueClass(NullWritable.class);
 *
 *         FileInputFormat.setInputPaths(job, new Path(args[0]));
 *         FileOutputFormat.setOutputPath(job, new Path(args[1]));
 *
 *         boolean b = job.waitForCompletion(true);
 *         System.exit(b ? 0 : 1);
 */
public class ETLDriver implements Tool {
    Configuration conf ;


    @Override
    public int run(String[] args) throws Exception {
        //System.out.println("args2 = " + Arrays.toString(args));

        //1. 获取job
        conf = this.getConf() ;
        //2. 设置数据的输入和输出路径
        conf.set("inpath", args[0]);
        conf.set("outpath", args[1]);

        Job job = Job.getInstance(conf);

        //3.dirver类
        job.setJarByClass(ETLDriver.class);

        //4. 设置mapper相关信息
        job.setMapperClass(ETLMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);

        //5.设置Reducer - 禁用reducer
        job.setNumReduceTasks(0);

        //6. 设置输入输出路径
        this.initJobInputPath(job);
        this.initJobOutputPath(job);

        //7.准备启动任务
        boolean b = job.waitForCompletion(true);
        return b?0:1;
    }
    private void initJobOutputPath(Job job) throws IOException {
        Configuration conf = job.getConfiguration();
        String outPathString = conf.get("outpath");
        System.out.println("文件输出路径为： "+ outPathString);

        FileSystem fs = FileSystem.get(conf);

        Path outPath = new Path(outPathString);
        if(fs.exists(outPath)){
            fs.delete(outPath, true);
        }

        FileOutputFormat.setOutputPath(job, outPath);

    }
    private void initJobInputPath(Job job) throws IOException {
        Configuration conf = job.getConfiguration();
        String inPathString = conf.get("inpath");

        FileSystem fs = FileSystem.get(conf);
        System.out.println("文件输入路径为： "+inPathString);
        Path inPath = new Path(inPathString);
        if(fs.exists(inPath)){
            FileInputFormat.addInputPath(job, inPath);
        }else{
            throw new RuntimeException("HDFS中该文件目录不存在：" + inPathString);
        }
    }
    @Override
    public void setConf(Configuration configuration) {
        this.conf = configuration ;
    }

    @Override
    public Configuration getConf() {
        return conf;
    }

    /**
     * 执行ETL
     * 将项目打jar包，传到linux服务器上。
     *
     * 运行jar包
     * $ hadoop jar /opt/module/hadoop-2.7.2/mydatas/etl.jar \      jar包
     * com.hive.ETL.mr.videoETL.ETLDriver \   要运行的类  会调用里面的main() 函数
     * /logs/hive/hivedatas/video/input  \    输入参数1  hdfs路径
     * /logs/hive/hivedatas/video/videoETLData 输入参数2 hdfs路径
     * @param args
     */
    public static void main(String[] args) {
        //System.out.println("args = " +  Arrays.toString(args));
        try {
            int resultCode = ToolRunner.run(new ETLDriver(), args);
            if(resultCode == 0){
                System.out.println("Success!");
            }else{
                System.out.println("Fail!");
            }
            System.exit(resultCode);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
