package com.meituan.xhyzjiji.number.tester;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CompletableFutureTest {

    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, Math.max(2, Runtime.getRuntime().availableProcessors()),
            10_000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100), new ThreadPoolExecutor.CallerRunsPolicy());
        System.out.println(System.currentTimeMillis() + " start");
        CompletableFuture f1 = CompletableFuture.runAsync(new Runnable() {
            @Override public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ie) {

                }
            }
        });
        CompletableFuture f2 = CompletableFuture.runAsync(new Runnable() {
            @Override public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ie) {
                    // ...
                }
            }
        });
        CompletableFuture mergedF = CompletableFuture.allOf(f1, f2); // f1.thenCombine(f2, (r1, r2) -> null);
        System.out.println("step into join");
        mergedF.get();
        System.out.println(System.currentTimeMillis() + " done");
        threadPool.shutdown();
    }

}
