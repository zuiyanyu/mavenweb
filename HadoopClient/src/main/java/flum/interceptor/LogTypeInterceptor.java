package flum.interceptor;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 本项目中自定义了两个拦截器，分别是：ETL拦截器、日志类型区分拦截器。
 * ETL拦截器主要用于，过滤时间戳不合法和Json数据不完整的日志
 * 日志类型区分拦截器主要用于，将启动日志和事件日志区分开来，方便发往Kafka的不同Topic。
 */
public class LogTypeInterceptor  implements Interceptor {
    @Override
    public void initialize() {

    }

    /**
     * TODO 当channel的选择器是 multiplexing 多路复用的时候，可以根据 key value进行channel的选择
     *  header : key -value  ,key = state , value =CA/AZ/NY
     *
     * #interceptor
     * a1.sources.r1.interceptors =  i1 i2
     * a1.sources.r1.interceptors.i1.type = flum.interceptor.LogETLInterceptor$Builder
     * a1.sources.r1.interceptors.i2.type = flum.interceptor.LogTypeInterceptor$Builder
     *
     *
     * @param event
     * @return
     */
    @Override
    public Event intercept(Event event) {

        // 区分日志类型：   body  header
        // 1 获取body数据
        byte[] body = event.getBody();
        String log = new String(body, Charset.forName("UTF-8"));

        // 2 获取header
        Map<String, String> headers = event.getHeaders();

        // 3 判断数据类型并向Header中赋值
        //TODO 当channel的选择器是 multiplexing 多路复用的时候，可以根据 key value进行channel的选择
        // agent_foo.sources.avro-AppSrv-source1.selector.type = multiplexing
        /*
            a1.sources.r1.selector.type = multiplexing
            a1.sources.r1.selector.header = topic
            a1.sources.r1.selector.mapping.topic_start = c1
            a1.sources.r1.selector.mapping.topic_event = c2
        */
        if (log.contains("start")) {
            headers.put("topic","topic_start");
        }else {
            headers.put("topic","topic_event");
        }

        return event;
    }

    @Override
    public List<Event> intercept(List<Event> events) {

        ArrayList<Event> interceptors = new ArrayList<>();

        for (Event event : events) {
            Event intercept1 = intercept(event);

            interceptors.add(intercept1);
        }

        return interceptors;
    }

    @Override
    public void close() {

    }

    // 建造者模式
    public static class Builder implements  Interceptor.Builder{

        @Override
        public Interceptor build() {
            //这里可以从context中获取配置参数，传递给拦截器
            return new LogTypeInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
