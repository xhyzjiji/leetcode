package com.meituan.xhyzjiji.number.models.batchconsumption.listener;

import com.meituan.xhyzjiji.number.models.batchconsumption.Data;
import com.meituan.xhyzjiji.number.models.batchconsumption.listener.DataListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class BlockingBatchDataListener implements DataListener<Data> {

    private ThreadPoolExecutor threadPool;
    private volatile boolean shutdownRequest = false;

    public BlockingBatchDataListener() {
        this.threadPool = new ThreadPoolExecutor(2, Math.max(2, Runtime.getRuntime().availableProcessors()),
            10_000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100), new ThreadPoolExecutor.CallerRunsPolicy());
    }

    @Override public void startUp() {
        // do nothing
    }

    @Override public void shutdown() {
        shutdownRequest = true;
        threadPool.shutdown(); // 不用shutdownNow，会丢数据
    }

    private Runnable wrap(Data data) {
        return new Runnable() {
            @Override public void run() {
                System.out.println(Thread.currentThread().getName() + ":" + data.toString());
                try {
                    Thread.sleep(10); // simulate IO operations
                } catch (Throwable te) {

                }
            }
        };
    }
    private Runnable wrap(List<Data> manyData) {
        return new Runnable() {
            @Override public void run() {
                manyData.stream().forEach(d -> System.out.println(Thread.currentThread().getName() + ":" + d.toString()));
                try {
                    Thread.sleep(10); // simulate IO operations
                } catch (Throwable te) {

                }
            }
        };
    }

    @Override public void handle(Data data) throws Exception {
        Future future = threadPool.submit(wrap(data));
        while (shutdownRequest == false) {
            try {
                future.get(200, TimeUnit.MILLISECONDS);
            } catch (Throwable te) {
                te.printStackTrace();
            }
        }
    }

    @Override public void batchHandle(List<Data> data) throws Exception {
        Map<Integer, List<Data>> keyedByData = data.stream().collect(Collectors.groupingBy(Data::getKey));
        List<CompletableFuture> futures = new ArrayList<>();
        for (List<Data> d : keyedByData.values()) {
            CompletableFuture oneFuture = CompletableFuture.runAsync(wrap(d), threadPool);
            futures.add(oneFuture);
        }
        CompletableFuture mergedFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        while (shutdownRequest == false) {
            try {
                mergedFuture.get(200, TimeUnit.MILLISECONDS);
                return;
            } catch (Throwable te) {
//                te.printStackTrace();
            }
        }
    }
}
