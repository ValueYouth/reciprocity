package thread;

/**
 * 奇数与偶数交替打印
 * 实现2：通过volatile关键字实现
 *
 * @author wangchi
 * @since 2019年2月27日
 */
@SuppressWarnings("NonAtomicOperationOnVolatileField")
public class OddEvenPrint2 {

    private static volatile int number = 0;
    private static volatile boolean isPrintEven;

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            while (number < 100) {
                if (!isPrintEven && ++number % 2 != 0) {
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + number);
                    isPrintEven = true;
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            while (number < 100) {
                if (isPrintEven && ++number % 2 == 0) {
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + number);
                    isPrintEven = false;
                }
            }
        });

        thread1.start();
        thread2.start();
    }

}
