package flum.interceptor;

import flum.util.LogUtils;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * 本项目中自定义了两个拦截器，分别是：ETL拦截器、日志类型区分拦截器。
 * ETL拦截器主要用于，过滤时间戳不合法和Json数据不完整的日志
 * 日志类型区分拦截器主要用于，将启动日志和事件日志区分开来，方便发往Kafka的不同Topic。
 */
public class LogETLInterceptor  implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        // 1 获取数据
        byte[] body = event.getBody();
        String log = new String(body, Charset.forName("UTF-8"));

        // 2 判断数据类型并向Header中赋值
        if (log.contains("start")) {
            //验证数据的有效性
            if (LogUtils.validateStart(log)){
                return event;
            }
        }else {
            //验证数据的有效性
            if (LogUtils.validateEvent(log)){
                return event;
            }
        }

        // 3 返回校验结果
        return null;
    }

    @Override
    public List<Event> intercept(List<Event> events) {
        ArrayList<Event> interceptors = new ArrayList<>();

        for (Event event : events) {
            Event intercept1 = intercept(event);

            if (intercept1 != null){
                interceptors.add(intercept1);
            }
        }

        return interceptors;
    }

    @Override
    public void close() {

    }

    // 建造者模式
    public static class Builder implements Interceptor.Builder{

        @Override
        public Interceptor build() {
            //这里可以从context中获取配置参数，传递给拦截器
            return new LogETLInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
