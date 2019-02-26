package thread;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 多线程 Callable和Future的用法2
 * 通过ExecutorService的submit方法执行Callable，并返回Future
 *
 * @author wangchi
 * @since 2019年2月25日
 */
public class CallableFuture2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<Integer> future = threadPool.submit(() -> new Random(35).nextInt(100));

        Thread.sleep(5000); // 其他业务逻辑
        System.out.println(future.get());

        // 停止线程池
        threadPool.shutdown();
    }
}
