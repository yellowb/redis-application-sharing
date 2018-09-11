package adv_usage;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisStringCommands;

/**
 * Sample for auto expired key
 */
public class AutoExpired {

    public static void main(String[] args) throws InterruptedException {
        RedisClient client = RedisClient.create("redis://localhost:6379");
        StatefulRedisConnection<String, String> connection = client.connect();
        RedisStringCommands<String, String> sync = connection.sync();
        sync.setex("autoExpiredKey", 5, "Hi");

        for(int i = 0; i < 6; i++) {
            System.out.println(sync.get("autoExpiredKey"));
            Thread.sleep(1000L);
        }
    }
}
