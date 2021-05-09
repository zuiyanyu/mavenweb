package redis.redisAPI;

import redis.clients.jedis.Jedis;

public class ConnectRedis_01 {
    //连接本地的redis服务
    public static void main(String[] args) {
        //1. 启动redis服务器
        Jedis redis = new Jedis("localhost",6379);
        System.out.println("redis服务端连接成功了！"+"ok");  //如果打印ok 就说明redis 连接成功了

        //2. redis连接成功后，看看客户端和redis服务端是否是通的。
        //如果服务端没有设置密码，就会报错：JedisDataException:  ERR Client sent AUTH, but no password is set
        redis.auth("root");  //如果登录密码，则提供登录密码。
        System.out.println("redis服务端连通成功了！"+redis.ping());  //返回值为pong ，就说明客户端和redis通了。

        //3. redis连接成功后就可以进行一些redis的操作了。
        //最后关闭redis的连接
        redis.close();
    }
}
