package concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import org.testng.annotations.Test;

public class AwaitAfterSignal {

    @Test
    public void awaitAfterSignal() throws Exception {
        final ReentrantLock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " sig");
                    condition.signalAll();
                } finally {
                    lock.unlock();
                }
                countDownLatch.countDown();
            }
        };
        t1.start();
        countDownLatch.await();

        Thread t2 = new Thread("t2") {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " await");
                    condition.await();
                } catch (InterruptedException ie) {
                    System.out.println("ie");
                } finally {
                    lock.unlock();
                }
            }
        };
        t2.start();

        t1.join();
        t2.join();
    }

}
