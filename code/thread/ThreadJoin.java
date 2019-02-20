package thread;

/**
 * 现在有T1、T2、T3三个线程，你怎样保证T2在T1执行完后执行，T3在T2执行完后执行
 * 实现1：继承类Thread
 *
 * @author wangchi
 * @since 2019年2月19日
 */
public class ThreadJoin extends Thread {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new MyThread("线程1");
        Thread t2 = new MyThread("线程2");
        Thread t3 = new MyThread("线程3");

        t1.start();
        t1.join();
        System.out.println("t1 is alive " + t1.isAlive());

        t2.start();
        t2.join();
        System.out.println("t2 is alive " + t2.isAlive());

        t3.start();
        t3.join();
        System.out.println("t3 is alive " + t3.isAlive());
    }


    static class MyThread extends Thread {

        MyThread(String name) {
            setName(name);
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
