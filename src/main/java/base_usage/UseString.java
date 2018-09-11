package base_usage;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisStringCommands;

/**
 * Sample for set/get string.
 */
public class UseString {
    public static void main(String[] args) {
        RedisClient client = RedisClient.create("redis://localhost:6379");
        StatefulRedisConnection<String, String> connection = client.connect();
        RedisStringCommands<String, String> sync = connection.sync();
        sync.set("key", "value");
        Object value = sync.get("key");
        // expect "value"
        System.out.println(value.toString());
        connection.close();
        client.shutdown();
    }
}
