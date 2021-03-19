package hadoop.mr.WritableDemo;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 期望输出数据格式
 * 13560436666 		1116		      954 			2070
 * 手机号码		    上行流量        下行流量		总流量
 */
public class FlowBeanReducer extends Reducer<Text,FlowBean,Text,FlowBean> {
    private FlowBean currentPhoneFlowBean = new FlowBean();

    @Override
    protected void reduce(Text phone , Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
        //读取手机号 对应的每一条流量数据，然后进行聚合
        Long sumUpFlow =0L ;
        Long sumDownFow=0L ;
        for (FlowBean flowBean : values) {
           // System.out.println(phone + "=>" + flowBean);
            sumUpFlow += flowBean.getUpFlow();
            sumDownFow += flowBean.getDownFlow();
        }
        currentPhoneFlowBean.setFlow(sumUpFlow,sumDownFow);

        //输出数据
        context.write(phone,currentPhoneFlowBean);
    }
}
