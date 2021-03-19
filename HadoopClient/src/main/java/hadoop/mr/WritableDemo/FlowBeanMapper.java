package hadoop.mr.WritableDemo;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 输入数据：见 phone_data.txt
 * 输入数据格式： \t 分割符
 * 7 	13560436666	120.196.100.99		1116		 954			200
 * id	手机号码		网络ip			上行流量  下行流量     网络状态码
 *
 * 输出格式：
 * 手机号码   {上行流量,下行流量 ,总流量 }
 */
public class FlowBeanMapper extends Mapper<LongWritable,Text,Text,FlowBean> {

    private Text phoneText = new Text() ;
    private FlowBean flowBean = new FlowBean() ;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //1. 得到一行数据
        String line_data = value.toString();

        //2. 解析数据
        String[] data_split = line_data.split("\t");

        //3. 处理数据
        String upFlow = data_split[data_split.length - 3] ;
        String dowFlow = data_split[data_split.length - 2];
        flowBean.setFlow(upFlow,dowFlow);

        //4. 写出数据
        phoneText.set(data_split[1]);
//        System.out.println(phoneText.toString() + "=>" + flowBean);
        context.write(phoneText,flowBean);

    }
}
