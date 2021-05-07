package flum.sink.customSink;

import org.apache.flume.*;
import org.apache.flume.conf.Configurable;
import org.apache.flume.sink.AbstractSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * TODO 自定义MySink需要继承AbstractSink类并实现Configurable接口。
 * TODO 使用场景：读取Channel数据写入MySQL或者其他文件系统。
 *
 * 需求：使用flume接收数据，并在Sink端给每条数据添加前缀和后缀，输出到控制台。前后缀可在flume任务配置文件中配置。
 *
 * 1）打包
 *   将写好的代码打包，并放到flume的lib目录下。
 */
public class MySink extends AbstractSink implements Configurable {
    //创建Logger对象
    private static final Logger LOG = LoggerFactory.getLogger(AbstractSink.class);

    //前后缀可在flume任务配置文件中配置
    private String prefix;
    private String suffix;

    /**
     * 从Channel读取获取数据（event），这个方法将被循环调用。
     *
     * @return
     * @throws EventDeliveryException
     */
    @Override
    public Status process() throws EventDeliveryException {
        //声明返回值状态信息
        Status status;

        //获取当前Sink绑定的Channel
        Channel ch = getChannel();

        //获取事务
        Transaction txn = ch.getTransaction();

        //声明事件
        Event event;

        //开启事务
        txn.begin();

        //读取Channel中的事件，直到读取到事件结束循环
        while (true) {
            event = ch.take();
            if (event != null) {
                break;
            }
        }
        try {
            //处理事件（打印）
            LOG.info(prefix + new String(event.getBody()) + suffix);

            //事务提交
            txn.commit();
            status = Status.READY;
        } catch (Exception e) {

            //遇到异常，事务回滚
            txn.rollback();
            status = Status.BACKOFF;
        } finally {

            //关闭事务
            txn.close();
        }
        return status;
    }

    /**
     * 初始化context（读取配置文件内容）
     * @param context
     */
    @Override
    public void configure(Context context) {
        //读取配置文件内容，有默认值
        prefix = context.getString("prefix", "hello:");

        //读取配置文件内容，无默认值
        suffix = context.getString("suffix");
    }
    /**
     *
     * bin/flume-ng agent -c conf/ -f jobs/mysink/mysink.conf -n a1 -Dflume.root.logger=INFO,console
     *ncat hadoop102 44444
     *
     * mysink.conf 配置如下：
     * # Name the components on this agent
     * a1.sources = r1
     * a1.sinks = k1
     * a1.channels = c1
     *
     * # Describe/configure the source
     * a1.sources.r1.type = netcat
     * a1.sources.r1.bind = hadoop102
     * a1.sources.r1.port = 44444
     *
     * # Describe the sink
     * a1.sinks.k1.type = flum.sink.customSink.MySink
     * #a1.sinks.k1.prefix = hadoop:
     * a1.sinks.k1.suffix = :hadoop
     *
     * # Use a channel which buffers events in memory
     * a1.channels.c1.type = memory
     * a1.channels.c1.capacity = 1000
     * a1.channels.c1.transactionCapacity = 100
     *
     * # Bind the source and sink to the channel
     * a1.sources.r1.channels = c1
     * a1.sinks.k1.channel = c1
     */
}
