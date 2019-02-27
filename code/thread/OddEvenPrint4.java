package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 奇数与偶数交替打印
 * 实现4：通过CAS实现
 *
 * @author wangchi
 * @since 2019年2月27日
 */
public class OddEvenPrint4 {

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    private static int number = 0;

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            while (number < 100) {
                if (0 != number++ % 2) {
                    print(number);
                }
            }

        });

        Thread thread2 = new Thread(() -> {
            while (number < 100) {
                if (0 == number++ % 2) {
                    print(number);
                }
            }
        });

        thread1.start();
        thread2.start();
    }


    private static void print(int number) {
        try {
            lock.lock();
            condition.signal();
            System.out.println(Thread.currentThread().getName() + ": " + number);
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
