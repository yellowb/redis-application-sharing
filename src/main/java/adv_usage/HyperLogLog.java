package adv_usage;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;


public class HyperLogLog {
    public static void main(String[] args) {
        RedisClient client = RedisClient.create("redis://localhost:6379");
        StatefulRedisConnection<String, String> connection = client.connect();
        RedisCommands<String, String> sync = connection.sync();

        final int size = 10000;
        for (int i = 0; i < size; i++) {
            String element = "EL" + i;
            sync.pfadd("hyperLogLog", element);
            // one more again, should remove duplicated
            sync.pfadd("hyperLogLog", element);
        }

        System.out.println("Accurate: " + size);
        System.out.println("HyperLogLog: " + sync.pfcount("hyperLogLog"));
        System.out.println("Error rate: " + (double)(sync.pfcount("hyperLogLog") - size)/size);

        sync.del("hyperLogLog");
    }
}
