package hadoop.mr.wordcount01;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * Reducer阶段
 * 1）用户定义的Reducer要继承自己的父类
 * 2）Reducer的输出数据类型对应Mapper的输出数据类型，也是KV.
 * 3) Reducer的业务逻辑写在reduce()方法中。
 * 4) reduce() (ReduceTask进程）对每一组相同k的<K,V>组调用一次reduce()方法。
 */

/**
 * TODO
 * Reducerr<KEYIN, VALUEIN, KEYOUT, VALUEOUT>
 *  KEYIN:    map()输出的key的类型
 *  VALUEIN:  map()输出的value的类型
 *  KEYOUT:   reduce()要落地到HDFS文件的key
 *  VALUEOUT: reduce()要落地到HDFS文件的内容
 */
public class WordcountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    /**
     * 收集map()处理的结果，然后进行汇总处理，最后进行处理结果的落盘
     * TODO reduce() (ReduceTask进程）对每一组相同k的<K,V>组调用一次reduce()方法。
     *
     * TODO 输入的数据：
     * hadoop ，1
     * hadoop ，1
     * lisi,1
     * lisi,1
     * hello,1
     *
     * TODO 输出数据
     * hadoop,2
     * lisi,2
     * hello,1
     *
     * TODO  reduce()接收数据和遍历处理数据的原理
     * TODO 1. 输入的数据是被序列化的数据，并且数据key相同的数据都会紧挨着。
     * TODO 2. 指针指向第一行，reduce会使用指针逐渐下移，进行迭代遍历数据。
     * TODO 3. 每次遍历，指针下移读取一行，对这行数据进行反序列化成对象
     * TODO 4. 取出对象的key,然后判断这行这行数据的key和上一行数据的key是否相同
     * TODO     4.1 如果相同，不调用reduce()，只是将这行数据使用迭代器进行返回，然后对这行数据进行处理
     * TODO     4.2 如果不同，则重新调用reduce(), 并将这行数据的key,和value传递给reduce()方法。
     * TODO     4.3 所以，reduce()输入的key,可能对应多个value, 这就是为什么我们参数用values表示，而不是用value的原因。
     *         如此循环，进行处理所有数据
     * @param key      map()输出的key的类型
     * @param values   map()输出的value的类型
     * @param context  任务上下文环境
     * @throws IOException
     * @throws InterruptedException
     */
    private IntWritable sumText = new IntWritable();
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        //1. 获取key对应的多个value
        Iterator<IntWritable> iterator = values.iterator();

        //2. 求和
        int sum = 0 ;
        while(iterator.hasNext()){
            IntWritable wordCount = iterator.next();
            //对单词相同的数量进行累加
           sum +=  wordCount.get();
        }
        sumText.set(sum);
        //3. 向hdfs落地数据 (具体实现文件落地的是OutputFormat对象)
        context.write(key,sumText);
    }
}


