package com.meituan.xhyzjiji.number.tester;

import java.util.concurrent.CyclicBarrier;
import org.testng.reporters.TestHTMLReporter;

public class BarrierActionTest {

    public static void main(String[] args) throws Exception {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(
            2,
            new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10000);
                    } catch (Exception e) {

                    }
                }
            }
        );

        TestThread t1 = new TestThread("t1", cyclicBarrier);
        TestThread t2 = new TestThread("t2", cyclicBarrier);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    static class TestThread extends Thread {
        private CyclicBarrier barrier;

        public TestThread(String name, CyclicBarrier barrier) {
            super(name);
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread() + " : " + i);
                }
                barrier.await();
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread() + " : " + i+10);
                }
            } catch (Exception e) {

            }
        }
    }

}
