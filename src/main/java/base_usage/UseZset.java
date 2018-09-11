package base_usage;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisSortedSetCommands;

import java.util.*;

/**
 * Sample for set/get zset.
 */
public class UseZset {
    public static void main(String[] args) {
        RedisClient client = RedisClient.create("redis://localhost:6379");
        StatefulRedisConnection<String, String> connection = client.connect();
        RedisSortedSetCommands<String, String> sync = connection.sync();
        sync.zadd("zset1", 80, "el1");
        sync.zadd("zset1", 60, "el2");
        sync.zadd("zset1", 100, "el3");
        List<String> zset1 = sync.zrange("zset1",0, -1);
        // expect el2,el1,el3
        System.out.println(zset1.toString());
        connection.close();
        client.shutdown();
    }
}
