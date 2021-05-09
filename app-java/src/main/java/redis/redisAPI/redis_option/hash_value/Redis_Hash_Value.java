package redis.redisAPI.redis_option.hash_value;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.redisAPI.redisUtil.RedisUtil;

import java.util.HashMap;
import java.util.Map;

public class Redis_Hash_Value {

    //hash 的应用
    @Test
    public void hmset() {
        Jedis redis = RedisUtil.getJedis();

        Map<String,String> userInfo = new HashMap<String,String>();
        userInfo.put("username","zhangsan");
        userInfo.put("age","12");
        userInfo.put("school", "henan");

        redis.hmset("USERINFO", userInfo);

        //遍历 hash
        Map<String,String> map = redis.hgetAll("USERINFO");
        for(Map.Entry<String,String> entry:map.entrySet()) {
            System.out.println(entry.getKey() +":"+entry.getValue());
        }

        RedisUtil.close(redis);
    }
    @Test
    public void hdel(){
        Jedis redis = RedisUtil.getJedis();
        //去除hash中某个值
        redis.hdel("USERINFO", "age");
        System.out.println(redis.hget("USERINFO", "age")); // null

        Map<String,String> map2 = redis.hgetAll("USERINFO");
        for(Map.Entry<String,String> entry:map2.entrySet()) {
            System.out.println(entry.getKey() +":"+entry.getValue());
        }

        RedisUtil.close(redis);
    }


    //String的应用
    static void testString() {
        Jedis redis = RedisUtil.getJedis();  //得到redis连接

        if(redis.get("name2")!=null) {
            redis.del("name2");
        }
        redis.set("name2","zhangsan");
        System.out.println(redis.get("name2"));

        RedisUtil.close(redis);  //每次获取redis连接，使用后要记得关闭。实质上等同redis.close();
    }

}
