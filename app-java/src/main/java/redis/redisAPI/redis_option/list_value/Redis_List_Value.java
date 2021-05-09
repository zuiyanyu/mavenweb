package redis.redisAPI.redis_option.list_value;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.SortingParams;
import redis.redisAPI.redisUtil.RedisUtil;

public class Redis_List_Value {
    //List集合的应用
    @Test
    public void lpush() {
        Jedis redis = RedisUtil.getJedis();

        String key = "xxx";
        if(redis.lrange(key, 0, -1)!= null) {
            redis.del(key);
        }
        redis.lpush(key, "猫");
        redis.lpush(key, "狗");
        redis.lpush(key, "猪");
        redis.lpush(key, "兔");
        System.out.println(redis.lrange(key, 0, -1));
        RedisUtil.close(redis);
    }
    @Test
    // sort对list 中数值进行排序。
    public void sort() {
        Jedis redis = RedisUtil.getJedis();

        String key = "dd";
        if(redis.lrange(key, 0, -1)!= null) {
            redis.del(key);
        }
        redis.rpush(key, "3");
        redis.rpush(key, "5");
        redis.rpush(key, "4");
        redis.rpush(key, "8");
        System.out.println(redis.lrange(key, 0, -1)); //[3, 5, 4, 8]

        //sort 进行升序排序。 不会对数据库进行排序，而是将排序后的list进行返回，不影响数据库。进行升序排序
        redis.sort(key);
        System.out.println(redis.lrange(key, 0, -1)); //[3, 5, 4, 8]
        System.out.println(redis.sort(key)); //[3, 4, 5, 8]  进行升序排序

        //如果要进行降序排序呢？
        SortingParams param = new SortingParams();  //设置排序方式
        System.out.println(redis.sort(key,param.desc())); //[8, 5, 4, 3]

        RedisUtil.close(redis);
    }
}
