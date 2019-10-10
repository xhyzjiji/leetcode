package com.meituan.xhyzjiji.number.concurrent;

import java.util.concurrent.FutureTask;
import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class ZeroEvenOdd {

    /*private volatile int n;

    private Semaphore zero = new Semaphore(1);
    private Semaphore even = new Semaphore(0);
    private Semaphore odd = new Semaphore(0);
    private volatile int num = 1;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        while (n > 0) {
            zero.acquire();
            if (n > 0) {
                printNumber.accept(0);
                if ((num & 0x01) == 0) {
                    even.release();
                } else {
                    odd.release();
                }
            }
        }
        odd.release();
        even.release();
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        while (n > 0) {
            even.acquire();
            if (n > 0) {
                printNumber.accept(num);
                num++;
                n--;
                zero.release();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        while (n > 0) {
            odd.acquire();
            if (n > 0) {
                printNumber.accept(num);
                num++;
                n--;
                zero.release();
            }
        }
    }*/

    private int n;

    private Semaphore zero = new Semaphore(1);
    private Semaphore even = new Semaphore(0);
    private Semaphore odd = new Semaphore(0);

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            zero.acquire();
            printNumber.accept(0);
            if ((i & 0x01) == 0) {
                odd.release();
            } else {
                even.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i+=2) {
            even.acquire();
            printNumber.accept(i);
            zero.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i+=2) {
            odd.acquire();
            printNumber.accept(i);
            zero.release();
        }
    }

    public static void main(String[] args) {

    }

    public static void test(ZeroEvenOdd solution) {

    }

}
