package base_usage;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisListCommands;

import java.util.*;

/**
 * Sample for set/get list.
 */
public class UseList {
    public static void main(String[] args) {
        RedisClient client = RedisClient.create("redis://localhost:6379");
        StatefulRedisConnection<String, String> connection = client.connect();
        RedisListCommands<String, String> sync = connection.sync();
        sync.lpush("list1", "el1", "el2", "el3");
        List<String> list1 = sync.lrange("list1", 0, -1);
        // expect el1,el2,el3
        System.out.println(list1.toString());
        connection.close();
        client.shutdown();
    }
}
