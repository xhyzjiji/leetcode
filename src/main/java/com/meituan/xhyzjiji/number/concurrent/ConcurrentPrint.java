package com.meituan.xhyzjiji.number.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConcurrentPrint {

    /**
     * 我们提供了一个类：
     *
     * public class Foo {
     *   public void one() { print("one"); }
     *   public void two() { print("two"); }
     *   public void three() { print("three"); }
     * }
     * 三个不同的线程将会共用一个 Foo 实例。
     *
     * 线程 A 将会调用 one() 方法
     * 线程 B 将会调用 two() 方法
     * 线程 C 将会调用 three() 方法
     * 请设计修改程序，以确保 two() 方法在 one() 方法之后被执行，three() 方法在 two() 方法之后被执行。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: [1,2,3]
     * 输出: "onetwothree"
     * 解释:
     * 有三个线程会被异步启动。
     * 输入 [1,2,3] 表示线程 A 将会调用 one() 方法，线程 B 将会调用 two() 方法，线程 C 将会调用 three() 方法。
     * 正确的输出是 "onetwothree"。
     * 示例 2:
     *
     * 输入: [1,3,2]
     * 输出: "onetwothree"
     * 解释:
     * 输入 [1,3,2] 表示线程 A 将会调用 one() 方法，线程 B 将会调用 three() 方法，线程 C 将会调用 two() 方法。
     * 正确的输出是 "onetwothree"。
     *  
     *
     * 注意:
     *
     * 尽管输入中的数字似乎暗示了顺序，但是我们并不保证线程在操作系统中的调度顺序。
     *
     * 你看到的输入格式主要是为了确保测试的全面性。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/print-in-order
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    private static final Logger log = LoggerFactory.getLogger(ConcurrentPrint.class);

    public static void main(String[] args) {
        ConcurrentPrint solution = new ConcurrentPrint();

        solution.testCase(new int[] {1, 2, 3});
        solution.testCase(new int[] {2, 3, 1});
    }

    public void testCase(int[] orders) {
        List<FutureTask> tasks = new ArrayList<FutureTask>();
        FutureTask<Void> task1 = new FutureTask<Void>(new Callable<Void>() {
            public Void call() {
                try {
                    ConcurrentPrint.this.first(new Runnable() {
                        public void run() {
                            System.out.println("one");
                        }
                    });
                } catch (InterruptedException ie) {
                    log.info("{} ie", Thread.currentThread().getName());
                }
                return null;
            }
        });
        FutureTask<Void> task2 = new FutureTask<Void>(new Callable<Void>() {
            public Void call() {
                try {
                    ConcurrentPrint.this.second(new Runnable() {
                        public void run() {
                            System.out.println("two");
                        }
                    });
                } catch (InterruptedException ie) {
                    log.info("{} ie", Thread.currentThread().getName());
                }
                return null;
            }
        });
        FutureTask<Void> task3 = new FutureTask<Void>(new Callable<Void>() {
            public Void call() {
                try {
                    ConcurrentPrint.this.third(new Runnable() {
                        public void run() {
                            System.out.println("three");
                        }
                    });
                } catch (InterruptedException ie) {
                    log.info("{} ie", Thread.currentThread().getName());
                }
                return null;
            }
        });
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        List<Thread> ts = new ArrayList<Thread>();
        for (int i = 0; i < orders.length; i++) {
            ts.add(new Thread(tasks.get(orders[i] - 1)));
        }
        int counter = 1;
        for (Thread t : ts) {
            t.start();
            t.setName("t" + counter++);
        }
        for (Thread t : ts) {
            try {
                t.join();
            } catch (InterruptedException ie) {
                log.info("join t catch ie");
            }
        }
    }

    private ReentrantLock lock1 = new ReentrantLock();
    private Condition block1 = lock1.newCondition();
    private ReentrantLock lock2 = new ReentrantLock();
    private Condition block2 = lock2.newCondition();
    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outp`uts "first". Do not change or remove this line.
        printFirst.run();
        lock1.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " sig b1");
            block1.signalAll();
        } finally {
            lock1.unlock();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        // printSecond.run() outputs "second". Do not change or remove this line.
        lock1.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " wait b1");
            block1.await();
            printSecond.run();
        } finally {
            lock1.unlock();
        }

        lock2.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " sig b2");
            block2.signalAll();
        } finally {
            lock2.unlock();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        // printThird.run() outputs "third". Do not change or remove this line.
        lock2.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " wait b2");
            block2.await();
            printThird.run();
        } finally {
            lock2.unlock();
        }
    }

    // 12ms
//    private volatile boolean block1 = true;
//    private volatile boolean block2 = true;
//    public void first(Runnable printFirst) throws InterruptedException {
//        // printFirst.run() outputs "first". Do not change or remove this line.
//        printFirst.run();
//        block1 = false;
//    }
//
//    public void second(Runnable printSecond) throws InterruptedException {
//        while (block1) {
//            Thread.yield();
//        }
//        // printSecond.run() outputs "second". Do not change or remove this line.
//        printSecond.run();
//        block2 = false;
//    }
//
//    public void third(Runnable printThird) throws InterruptedException {
//        while (block2) {
//            Thread.yield();
//        }
//        // printThird.run() outputs "third". Do not change or remove this line.
//        printThird.run();
//    }


//    12ms
//    private Semaphore s1 = new Semaphore(0);
//    private Semaphore s2 = new Semaphore(0);
//    public void first(Runnable printFirst) throws InterruptedException {
//
//        // printFirst.run() outputs "first". Do not change or remove this line.
//        printFirst.run();
//        s1.release();
//    }
//
//    public void second(Runnable printSecond) throws InterruptedException {
//        s1.acquire();
//        // printSecond.run() outputs "second". Do not change or remove this line.
//        printSecond.run();
//        s2.release();
//    }
//
//    public void third(Runnable printThird) throws InterruptedException {
//        s2.acquire();
//        // printThird.run() outputs "third". Do not change or remove this line.
//        printThird.run();
//    }

}
