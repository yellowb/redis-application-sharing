package base_usage;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisListCommands;
import io.lettuce.core.api.sync.RedisSetCommands;

import java.util.*;

public class UseSet {
    public static void main(String[] args) {
        RedisClient client = RedisClient.create("redis://localhost:6379");
        StatefulRedisConnection<String, String> connection = client.connect();
        RedisSetCommands<String, String> sync = connection.sync();
        sync.sadd("set1", "el1", "el1", "el2");
        Set<String> set1 = sync.smembers("set1");
        System.out.println(set1.toString());
        connection.close();
        client.shutdown();
    }
}
