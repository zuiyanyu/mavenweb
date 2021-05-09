package redis.redisAPI.redis_option.commands;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.redisAPI.redisUtil.RedisUtil;

import java.util.Set;

public class Redis_keys {
    @Test
    //得到所有的key
    public void testKeys() {
        Jedis redis = RedisUtil.getJedis() ;
        Set<String> keyList = redis.keys("*");
        for(String key:keyList) {
            System.out.println(key);
        }
        //判断某个key是否存在
        System.out.println(redis.exists("age"));
        RedisUtil.close(redis);
    }
}
