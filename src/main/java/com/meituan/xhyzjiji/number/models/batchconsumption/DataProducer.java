package com.meituan.xhyzjiji.number.models.batchconsumption;

import com.meituan.xhyzjiji.number.models.batchconsumption.listener.DataListener;
import java.util.ArrayList;
import java.util.List;

public class DataProducer extends Thread implements LifeCycle {

    private List<DataListener<Data>> listeners;
    private DataFactory<Data> dataFactory;
    private volatile boolean shutdownRequest = false;

    public DataProducer() {
        this.listeners = new ArrayList<>();
        this.dataFactory = new DefaultDataFactory();
    }

    public void regiseterListener(DataListener dataListener) {
        listeners.add(dataListener);
    }

    public void registerListeners(DataListener... dataListeners) {
        for (DataListener dataListener : dataListeners) {
            listeners.add(dataListener);
        }
    }

    @Override public void run() {
        try {
            while (shutdownRequest == false) {
                List<Data> manyData = dataFactory.createMany();
                for (DataListener listener : listeners) {
                    listener.batchHandle(manyData);
                }
                Thread.sleep(1000);
            }
        } catch (Throwable te) {
            te.printStackTrace();
        }
    }

    @Override public void startUp() {
        super.start();
    }

    @Override public void shutdown() {
        shutdownRequest = true;
        listeners.stream().forEach(l -> l.shutdown());
    }

}
