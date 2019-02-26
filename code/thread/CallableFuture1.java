package thread;

import java.util.concurrent.*;

/**
 * 多线程 Callable和Future的用法1——使用FutureTask类构建
 * Callable用于产生结果
 * Future用于获取结果
 *
 * @author wangchi
 * @since 2019年2月25日
 */
public class CallableFuture1 {

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        Callable<String> callable = () -> "Hello World!";
        FutureTask<String> future = new FutureTask<>(callable);
        new Thread(future).start();

        System.out.println(future.get(1, TimeUnit.MICROSECONDS));
    }
}
