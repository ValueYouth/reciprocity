package concurrent;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class SimpleLock extends AbstractQueuedSynchronizer {
    private static final long serialVersionUID = -7316320116933187634L;

    private SimpleLock() {}

    public static void main(String[] args) throws InterruptedException {
        final SimpleLock lock = new SimpleLock();
        lock.lock();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " acquire the lock!");
                lock.unlock();
            }).start();
            Thread.sleep(100);
        }

        System.out.println("main thread unlock...");
        lock.unlock();
    }

    protected boolean tryAcquire(int unused) {
        if (compareAndSetState(0, 1)) {
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        return false;
    }

    protected boolean tryRelease(int unused) {
        setExclusiveOwnerThread(null);
        setState(0);
        return true;
    }

    public void lock() {
        acquire(1);
    }

    public boolean tryLock() {
        return tryAcquire(1);
    }

    public void unlock() {
        release(1);
    }

    public boolean isLocked() {
        return isHeldExclusively();
    }
}