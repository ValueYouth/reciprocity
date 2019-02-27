package thread;

/**
 * 奇数与偶数交替打印
 * 实现1：synchronized关键字实现
 *
 * @author wangchi
 * @since 2019年2月27日
 */
public class OddEvenPrint1 implements Runnable{

    private int number = 1;

    public static void main(String[] args) throws InterruptedException {
        OddEvenPrint1 oddEvenPrint1 = new OddEvenPrint1();
        Thread thread1 = new Thread(oddEvenPrint1, "线程1");
        Thread thread2 = new Thread(oddEvenPrint1, "线程2");

        thread1.start();
        Thread.sleep(100); // 线程2延迟开启,为了始终让线程1先拿到锁
        thread2.start();
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                notify();
                System.out.println(Thread.currentThread().getName() + ": " + number++);
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (number == 101){
                    notify();
                    break;
                }
            }
        }
    }


}
