package com.meituan.xhyzjiji.number.models.batchconsumption.listener;

import com.meituan.xhyzjiji.number.models.batchconsumption.Data;
import com.meituan.xhyzjiji.number.models.batchconsumption.LifeCycle;
import com.meituan.xhyzjiji.number.models.batchconsumption.listener.DataListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class StreamableDataListener implements DataListener<Data>, LifeCycle {

    private final int parallelism = normalize(Runtime.getRuntime().availableProcessors());
    private final AtomicInteger TID = new AtomicInteger(1);
    private volatile boolean shutdownRequest = false;
    private ThreadGroup threadGroup;
    private Map<Integer, BlockingQueue<Data>> threadQueue;

    public static int normalize(int num) {
        int normalizedBufferSize = num;
        normalizedBufferSize--;
        normalizedBufferSize |= normalizedBufferSize >>> 1;
        normalizedBufferSize |= normalizedBufferSize >>> 2;
        normalizedBufferSize |= normalizedBufferSize >>> 4;
        normalizedBufferSize |= normalizedBufferSize >>> 8;
        normalizedBufferSize |= normalizedBufferSize >>> 16;
        normalizedBufferSize++;

        return normalizedBufferSize;
    }

    @Override public void startUp() {

    }

    @Override public void shutdown() {
        shutdownRequest = true;
        threadQueue.clear();
    }

    public StreamableDataListener() {
        this.threadGroup = new ThreadGroup("StreamableDataListenerGroup");
        threadGroup.setDaemon(true);

        this.threadQueue = new HashMap<>();
        for (int i = 0; i < parallelism; ++i) {
            final BlockingQueue<Data> queue = new ArrayBlockingQueue<>(100);
            Thread thread = new Thread(threadGroup, new Runnable() {
                @Override public void run() {
                    while (shutdownRequest == false) {
                        try {
                            Data data = queue.poll(200, TimeUnit.MILLISECONDS);
                            if (Objects.nonNull(data)) {
                                System.out.println(Thread.currentThread().getName() + ":" + data.toString());
                                try {
                                    Thread.sleep(10); // simulate IO operations
                                } catch (Throwable te) {

                                }
                            }
                        } catch (Throwable te) {
                            te.printStackTrace();
                        }
                    }
                    queue.clear();
                }
            }, "StreamableDataListener-" + TID.getAndIncrement());
            thread.start();
            threadQueue.put(i, queue);
        }
    }

    @Override public void handle(Data data) throws Exception {
        int mask = parallelism - 1;
        int hashId = data.getKey() & mask;
        BlockingQueue<Data> queue = threadQueue.get(hashId);
        queue.put(data);
    }

    @Override public void batchHandle(List<Data> data) throws Exception {
        data.stream().forEach(d -> {
            try {
                handle(d);
            } catch (Throwable te) {
                te.printStackTrace();
            }
        });
    }
}
