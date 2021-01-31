package hadoop.mr.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 用户编写的程序分成三个部分：Mapper、Reducer和Driver。
 * 5.1 Mapper阶段：
 * 1) 用户自定义的Mapper要继承自己的父类。
 * 2）Mapper的输入数据是KV对的形式（KV的类型可以自定义）
 * 3）Mapper中的作业逻辑写在map()方法中。
 * 4）Mapper的输出数据是KV对的形式（KV的类型可以自定义）
 * 5）map()方法（MapTask进程）对每一个<K,V>调用一次。
 * Mapper<KEYIN, VALUEIN, KEYOUT, VALUEOUT>
 *
 *
 */

/*  需求：在给定的文本文件中统计输出每一个单词出现的总次数
   TODO 读取文本中的每一行到Mapper进行处理，
   1. map接收输入的数据：KEYIN为每一行数据的偏移量，VALUEIN 为每一行的文本数据
   2. map()进行数据分析,输出的KEYOUT为单词文本，输出的VALUEOUT为 1
   3. MapperReduce有自己的类型序列化机制

 */
public class WordcountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    /**
     * 读取文本中的每一行进行处理
     * TODO InputFormat会将文件的内容读取，让后将每一行处理成(数据偏移量，文本数据) 传递给mapper()进行处理。
     * TODO map()输入的数据：
     * hadoop ,hello
     * lisi,lisi
     * hadoop
     * TODO map()输出的数据
     * （hadoop ，1）
     * （hello ，1）
     * （lisi ，1）
     * （lisi ，1）
     * （hadoop ，1）
     *
     * @param key     某一行文本的偏移量
     * @param value   某一行文本的值
     * @param context  贯穿整个MapperReduce任务的上下文环境
     * @throws IOException
     * @throws InterruptedException
     */
    private Text wordText = new Text();
    private IntWritable one = new IntWritable(1);
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //1. 获取一行数据  输入的value值样例: hadoop ,hello
        String line = value.toString();

        // 2. 解析输入的一行文本内容，对其进行拆分。
        String[] split = line.split(",");

        //3. 将拆分的每一个单词，以(word,1)的形式输出出去
        for (String word : split) {
            //数据从上下文环境中来，我们处理完之后，写出到上下文环境中，reduce()处理的时候，会从上下文环境取map输出的结果数据。
            wordText.set(word);
            context.write(wordText,one);
        }
        // TODO 将word设置到Text中,然后再写入的context中的原因：  (个人理解)
        // 1)我们向context写入的不是word或者Text对象，而是word被序列化之后的一行数据。
        // 2）将word放到Text，是因为Text里有hadoop提供的对字符串进行序列化的轻量机制。
        //     那么word对象被序列化，可用的序列化机制有很多，比如word对应的java序列化机制，但是java的序列化机制太重，
        //     所以使用hadoop自己提供的一套对String类型的序列化机制，这套机制就在Text类型中封装着。
        // 3) 向context写入wordText，实际上是向context写入被wordText序列化的word .

        //TODO 为什么写入的数据要被序列化呢？  (个人理解)
        //1)hadoop是处理大数据的，数据量肯定很大，如果每一行数据都以对象的形式在内存中存在，那么内存肯定会爆掉。

        //TODO 为什么向context中写入的数据一定要用轻量级的序列化机制呢？ (个人理解)
        // 1）网络传输的效率原因:
        //    map()写出的序列化数据要被传输到reduce()中进行处理，但是reduce()的任务节点在其他的NodeManager上运行着
        //    那么将待处理的数据给reduce()所在节点，必然要经过网络传输。
        //    如果使用java的重序列化机制，那么序列化的结果除了数据本省，还有数据类型信息：比如本类类型，父类类型等大量冗余的数据，必定
        //    会降低网络数据的传输速度，增大网络传输失败的可能性。
        // 2) 反序列化的效率原因：
        //    数据被序列化，必然在其他地方进行处理的时候，要被反序列化成对象才能被处理，比如reduce()要对数据进行处理。
        //    如果出了数据，还要反序列化很多其他冗余数，必定降低程序性能，
        // 3）综上两点：我们需要使用hadoop提供的轻量级序列化机制进行序列化数据。


    }
}
