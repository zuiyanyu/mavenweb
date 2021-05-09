package redis.redisAPI.redis_option.set_value;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.redisAPI.redisUtil.RedisUtil;

public class Redis_Set_Value {
    // set集合的应用  无重复数据 ，且数据无序. 有重复的会进行去重复
    @Test
    public void testSet() {
        Jedis redis = RedisUtil.getJedis() ;
        String key = "myset";

        if(redis.smembers(key)!=null) {
            redis.del(key);
        }
        redis.sadd(key, "桔子");
        redis.sadd(key, "桔子");
        redis.sadd(key,"桃");
        redis.sadd(key, "杏");
        redis.sadd(key,"杏");
        redis.sadd(key,"李");
        redis.sadd(key, "花生");
        System.out.println(redis.smembers(key)); //[桃, 李, 杏, 桔子, 花生]

        //移出set中的数据
        redis.srem(key, "花生");
        System.out.println(redis.smembers(key));//[李, 杏, 桔子, 桃]

        //判断是否是成员
        System.out.println(redis.sismember(key, "杏"));//true
        System.out.println(redis.sismember(key, "花生"));//false

        //获取set集合长度
        System.out.println(redis.scard(key)); //4

        //随机获取set集合中的一个值
        System.out.println(redis.srandmember(key)); //随机

        RedisUtil.close(redis);
    }
}
