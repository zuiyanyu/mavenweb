package hadoop.mr.WritableDemo;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

// 1 实现writable接口 和  Comparable 接口  ，  implements Writable,Comparable  可简写为：WritableComparable
public class FlowBean implements WritableComparable<FlowBean> {
    private long upFlow;     //上行流量
    private long downFlow;   //下行流量
    private long sumFlow;    //总流量


    //2  反序列化时，需要反射调用空参构造函数，所以必须有
    public FlowBean(){
        super();
    }
    //非空参构造
    public void setFlow(long upFlow, long downFlow) {
        this.upFlow = upFlow;
        this.downFlow = downFlow;
        this.sumFlow = upFlow + downFlow;
    }
    //非空参构造
    public void setFlow(String upFlow, String downFlow) {
        this.upFlow = Long.parseLong(upFlow);
        this.downFlow = Long.parseLong(downFlow);
        this.sumFlow = this.upFlow + this.downFlow;
    }
    //3  写序列化方法
    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(upFlow);
        dataOutput.writeLong(downFlow);
        dataOutput.writeLong(sumFlow);
    }
    //4 反序列化方法
    //5 反序列化方法读顺序必须和写序列化方法的写顺序必须一致
    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.upFlow  = dataInput.readLong();
        this.downFlow = dataInput.readLong();
        this.sumFlow = dataInput.readLong();
    }

    //6. 如果需要将自定义的bean放在key中传输，则还需要实现Comparable接口，因为MapReduce框中的Shuffle过程要求对key必须能排序。
    // 以防万一，最好给出
    @Override
    public int compareTo(FlowBean o) {
        // 倒序排列，从大到小
        return this.sumFlow > o.getSumFlow() ? -1 : 1;
    }
    // 7 编写toString方法，方便后续打印到文本
    @Override
    public String toString() {
        return upFlow + "\t" + downFlow + "\t" + sumFlow;
    }
   //8. 编写setter 和setter方法
    public long getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(long upFlow) {
        this.upFlow = upFlow;
    }

    public long getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(long downFlow) {
        this.downFlow = downFlow;
    }

    public long getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(long sumFlow) {
        this.sumFlow = sumFlow;
    }
}
