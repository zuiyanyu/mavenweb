package flum.source.customSource;

import org.apache.flume.Context;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.PollableSource;
import org.apache.flume.conf.Configurable;
import org.apache.flume.event.SimpleEvent;
import org.apache.flume.source.AbstractSource;

import java.util.HashMap;

/**
 *
 *TODO 自定义MySource需要继承AbstractSource类并实现Configurable和PollableSource接口。
 * <dependency>
 *         <groupId>org.apache.flume</groupId>
 *         <artifactId>flume-ng-core</artifactId>
 *         <version>1.7.0</version>
 * </dependency>
 *
 * TODO 使用场景：process()中读取MySQL数据或者其他文件系统。
 *
 * 1）打包
 * 将写好的代码打包，并放到flume的lib目录下。
 *
 */
public class MySource extends AbstractSource implements Configurable, PollableSource {
    //定义配置文件将来要读取的字段
    private Long delay;
    private String field;

    /**
     * flume 后台线程会定时执行这个process方法。
     * 这个方法应该从外部获取一些数据，写入到 channel.
     * @return
     * @throws EventDeliveryException
     */
    @Override
    public Status process() throws EventDeliveryException {
        try {
            //创建事件头信息
            HashMap<String, String> hearderMap = new HashMap<>();
            //创建事件
            SimpleEvent event = new SimpleEvent();
            //循环封装事件
            for (int i = 0; i < 5; i++) {
                //给事件设置头信息
                event.setHeaders(hearderMap);

                //给事件设置内容
                event.setBody((field +"-"+ i).getBytes());

                //将事件写入channel  channel进行事件的收集
                getChannelProcessor().processEvent(event);

                //事件间写出延时时长-自定义  控制向channel发送数据的速率
                Thread.sleep(delay);
            }
            //如果消费速度小于数据的生产速度
            //控制channel中的数据被消费的速率 每向channel写入一批数据后，就等待一段时间，让sink进行消费
            Thread.sleep(delay*3);
        } catch (Exception e) {
            e.printStackTrace();
            return Status.BACKOFF;
        }

        return Status.READY;
    }

    @Override
    public long getBackOffSleepIncrement() {
        return 0;
    }

    @Override
    public long getMaxBackOffSleepInterval() {
        return 0;
    }

    //初始化配置信息
    @Override
    public void configure(Context context) {
        delay = context.getLong("delay");
        field = context.getString("field", "Hello!");  // 如果field 没设置，则默认使用 Hello！这个值代替。
    }
    /**
     * 2）配置文件
     * # Name the components on this agent
     * a1.sources = r1
     * a1.sinks = k1
     * a1.channels = c1
     *
     * # Describe/configure the source
     * a1.sources.r1.type = flum.source.customSource.MySource
     * a1.sources.r1.delay = 1000
     * #a1.sources.r1.field = hadoop
     *
     * # Describe the sink
     * a1.sinks.k1.type = logger
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
