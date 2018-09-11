package adv_usage;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.api.sync.RedisStringCommands;

import java.util.concurrent.*;

public class Lock {

    public static void main(String[] args) throws InterruptedException {
        RedisClient client = RedisClient.create("redis://localhost:6379");
        StatefulRedisConnection<String, String> connection = client.connect();
        RedisCommands<String, String> sync = connection.sync();

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

        private final RedisCommands<String, String> sync;

        public RequestThread(RedisCommands<String, String> sync) {
            this.sync = sync;
        }

        @Override
        public void run() {
            if(sync.setnx("lock", "lock")) {
                sync.expire("lock", 5L);
                System.out.println(Thread.currentThread().getName() + " Got lock!");
            }
            else {
                System.out.println(Thread.currentThread().getName() + " Not Got lock!");
            }
        }
    }
}
