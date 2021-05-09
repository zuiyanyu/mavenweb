package redis.redisAPI.redisUtil;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
    //final防止别人对redis进行修改
    private static JedisPool pool ;

    //redis的服务的ip地址和端口号
    private final static String IP = "localhost" ;
    private final static int PORT = 6379 ;

    //设置redis连接（而不是向redis连接池取redis）的超时时间为10秒
    private final static int TIMOUT = 10000 ;

    // redis密码。  config  set  requirepass  root  //将密码设置为root
    private final static String AUTH = "root" ;


    static { //配置信息不需要每次加载，只需要这个类加载的时候进行加载一次就行了。
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(1024);//设置最大连接数
        config.setMaxIdle(200); // 最大空闲实例。(最大有几个redis实例在空闲着不用)
        config.setMaxWaitMillis(10000); //等待连接池给连接的最大时间。单位毫秒。-1表示等待永不过时。
        config.setTestOnBorrow(true);//borrow()借出一个实例前，是否提前进行validate（redis可用的验证）操作。这样借出去的redis肯定可用。
        pool = new JedisPool(config,IP,PORT,TIMOUT,AUTH) ; // (配置，IP地址，端口号，等redis给连接的超时时间，密码)

    }
    //得到redis的连接
    public static Jedis getJedis(){
        if(pool == null){
            return null ;
        }
        return pool.getResource();
    }

    //关闭redis连接， final防止别人对redis进行修改
    public static void close(final Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
    //设置key
    @Test
    public void test(){
        Jedis redis = RedisUtil.getJedis();
        //设置String类型的value
        redis.set("userName","zhangsan");
        String userName = redis.get("userName");
        System.out.println(userName);

        redis.incr("count");
        redis.incrBy("count",2L) ;
        String count = redis.get("count");
        System.out.println("count = " + count);

        //最后关闭redis的连接
        redis.close();
    }
}
