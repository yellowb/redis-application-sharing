package adv_usage;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisStringCommands;
import javafx.scene.AmbientLight;

import java.util.concurrent.*;

/**
 * Sample for distributed Id generator
 */
public class DistributedIdGenerator {

    public static void main(String[] args) throws InterruptedException {
        RedisClient client = RedisClient.create("redis://localhost:6379");
        StatefulRedisConnection<String, String> connection = client.connect();
        RedisStringCommands<String, String> sync = connection.sync();

        // Multiple thread to obtain the id
        ExecutorService pool = Executors.newCachedThreadPool();
        pool.submit(new RequestThread(sync));
        pool.submit(new RequestThread(sync));
        pool.submit(new RequestThread(sync));

        pool.awaitTermination(5, TimeUnit.SECONDS);
        pool.shutdown();

        connection.close();
        client.shutdown();
    }

    static class RequestThread implements Runnable {

        private static final int AMOUNT = 100;

        private final RedisStringCommands<String, String> sync;

        public RequestThread(RedisStringCommands<String, String> sync) {
            this.sync = sync;
        }

        @Override
        public void run() {
            for (int i = 0; i < AMOUNT; i++) {
                System.out.println(Thread.currentThread().getName() + " got " + sync.incr("IdGenerator"));
            }
        }
    }

}
