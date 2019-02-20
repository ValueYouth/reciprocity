package thread;

/**
 * 现在有T1、T2、T3三个线程，你怎样保证T2在T1执行完后执行，T3在T2执行完后执行
 * 实现1：实现接口Runnable
 *
 * @author wangchi
 * @since 2019年2月19日
 */
public class RunnableJoin {

    public static void main(String[] args) {
        final Thread t1 = new Thread(() -> System.out.println("t1"));

        final Thread t2 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2");
        });

        final Thread t3 = new Thread(() -> {
            try {
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t3");
        });

        t3.start();
        t1.start();
        t2.start();
    }

}
