package base_usage;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisStringCommands;

/**
 * Test the connection to redis works
 */
public class EnsureConnection {
    public static void main(String[] args) {
        RedisClient client = RedisClient.create("redis://localhost:6379");
        StatefulRedisConnection<String, String> connection = client.connect();
        RedisStringCommands sync = connection.sync();
        sync.set("key1", "value1");
        Object value = sync.get("key1");
        System.out.println(value.toString());
    }
}
