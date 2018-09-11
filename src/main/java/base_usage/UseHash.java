package base_usage;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisHashCommands;

import java.util.*;

/**
 * Sample for set/get hash
 */
public class UseHash {

    public static void main(String[] args) {
        RedisClient client = RedisClient.create("redis://localhost:6379");
        StatefulRedisConnection<String, String> connection = client.connect();
        RedisHashCommands<String, String> sync = connection.sync();
        sync.hset("hash1", "name", "Tom");
        sync.hset("hash1", "age", "20");
        Map<String, String> hash1 = sync.hgetall("hash1");
        // expect {name=Tom, age=20}
        System.out.println(hash1.toString());
        connection.close();
        client.shutdown();
    }
}
