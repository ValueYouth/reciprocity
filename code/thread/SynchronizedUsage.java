package thread;

/**
 * synchronized关键字用法
 *
 * @author wangchi
 * @since 2019年2月27日
 */
public class SynchronizedUsage implements Runnable {

    private static int count;


    @Override
    public synchronized void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + count++);
        }
    }
}
