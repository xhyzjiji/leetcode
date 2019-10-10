package com.meituan.xhyzjiji.number.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Semaphore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrintByTurns {

    /**
     * 我们提供一个类：
     *
     * class FooBar {
     *   public void foo() {
     *     for (int i = 0; i < n; i++) {
     *       print("foo");
     *     }
     *   }
     *
     *   public void bar() {
     *     for (int i = 0; i < n; i++) {
     *       print("bar");
     *     }
     *   }
     * }
     * 两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。
     *
     * 请设计修改程序，以确保 "foobar" 被输出 n 次。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: n = 1
     * 输出: "foobar"
     * 解释: 这里有两个线程被异步启动。其中一个调用 foo() 方法, 另一个调用 bar() 方法，"foobar" 将被输出一次。
     * 示例 2:
     *
     * 输入: n = 2
     * 输出: "foobarfoobar"
     * 解释: "foobar" 将被输出两次。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/print-foobar-alternately
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    private static final Logger log = LoggerFactory.getLogger(PrintByTurns.class);

    public static void main(String[] args) throws Exception {
        PrintByTurns solution = new PrintByTurns(2);

        test(solution);
    }

    public static void test(final PrintByTurns solution) throws Exception {
        FutureTask<Void> t1 = new FutureTask<Void>(new Callable<Void>() {
            public Void call() throws Exception {
                solution.foo(new Runnable() {
                    public void run() {
                        System.out.print("foo");
                    }
                });
                return null;
            }
        });
        FutureTask<Void> t2 = new FutureTask<Void>(new Callable<Void>() {
            public Void call() throws Exception {
                solution.bar(new Runnable() {
                    public void run() {
                        System.out.print("bar");
                    }
                });
                return null;
            }
        });

        Thread tt1 = new Thread(t1);
        Thread tt2 = new Thread(t2);
        tt1.start();
        tt2.start();

        tt1.join();
        tt2.join();
    }

    private int n;
    private Semaphore s1 = new Semaphore(1);
    private Semaphore s2 = new Semaphore(0);

    public PrintByTurns(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            s1.acquire();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            s2.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            s2.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            s1.release();
        }
    }

}
