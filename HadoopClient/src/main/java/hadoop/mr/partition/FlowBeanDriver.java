package hadoop.mr.partition;

import hadoop.mr.WritableDemo.FlowBean;
import hadoop.mr.WritableDemo.FlowBeanMapper;
import hadoop.mr.WritableDemo.FlowBeanReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class FlowBeanDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        args = new String[]{"D:\\hadoop\\phoneDataIn","D:\\hadoop\\phoneDataout2"};
        //1. 设置配置环境 和 获取job任务
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);

        //TODO 设置分区  reduceTasks值 >= 分区数的个数，但是尽量相同
        job.setPartitionerClass(CustomPartitioner.class);
        job.setNumReduceTasks(5);

        //2. 设置driver 驱动
        job.setJarByClass(FlowBeanDriver.class);

        //3. 设置 mapper 和 reducer 的任务类
        job.setMapperClass(FlowBeanMapper.class);
        job.setReducerClass(FlowBeanReducer.class);

        //4. 设置 mapper 的输出 key, value类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);

        //5. 设置Reducer的输出key ,value类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);

        //6. 设置输入 输出路径
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        //7. 提交任务
        boolean result = job.waitForCompletion(true);
        //8. 退出   退出状态； 0-正常退出  1-异常退出
        System.exit(result?0:1);
    }
}
