package redis.redisAPI.redis_option.string_value;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;

public class Redis_String_Value {
    //1. 启动redis服务器
    public static final Jedis redis = new Jedis("localhost",6379);
    {
        System.out.println("redis服务端连接成功了！"+"ok");  //如果打印ok 就说明redis 连接成功了

        //2. redis连接成功后，看看客户端和redis服务端是否是通的。
        redis.auth("root");  //如果登录密码，则提供登录密码。
        System.out.println("redis服务端连通成功了！"+redis.ping());  //返回值为pong ，就说明客户端和redis通了。
        //3. redis连接成功后就可以进行一些redis的操作了。

        //最后关闭redis的连接
        //redis.close();
    }

    //设置key
    @Test
    public void test01(){
        //设置String类型的value
        redis.set("userName","zhangsan");
        redis.mset("userName","zhangsan","age","20","note","备注");

        String userName = redis.get("userName");
        System.out.println(userName);

        List<String> mget = redis.mget("userName", "age", "note");
        System.out.println(mget);


        //最后关闭redis的连接
        redis.close();
    }
    //设置key
    @Test
    public void test02(){
        //设置String类型的value
        redis.set("userName","zhangsan");
        redis.del("userName");

        redis.incr("count");
        redis.incrBy("count",2L) ;
        String count = redis.get("count");
        System.out.println("count = " + count);

        //最后关闭redis的连接
        redis.close();
    }



}
