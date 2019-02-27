package thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 奇数与偶数交替打印
 * 实现3：通过CAS实现
 *
 * @author wangchi
 * @since 2019年2月27日
 */
@SuppressWarnings("Duplicates")
public class OddEvenPrint3 {

    private static AtomicInteger number = new AtomicInteger();

    private static volatile boolean isPrintEven;

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            while (number.get() < 100) {
                if (!isPrintEven && 0 != number.incrementAndGet() % 2) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + number.get());
                    isPrintEven = true;
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            while (number.get() < 100) {
                if (isPrintEven && (0 == number.get() || 0 == number.incrementAndGet() % 2)) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + number.get());
                    isPrintEven = false;
                }
            }
        });

        thread1.start();
        thread2.start();
    }


}
