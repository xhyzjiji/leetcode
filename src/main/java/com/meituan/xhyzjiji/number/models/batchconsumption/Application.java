package com.meituan.xhyzjiji.number.models.batchconsumption;

import com.meituan.xhyzjiji.number.models.batchconsumption.listener.BlockingBatchDataListener;
import com.meituan.xhyzjiji.number.models.batchconsumption.listener.DataListener;

public class Application {

    public static void main(String[] args) throws Exception {
        DataProducer dataProducer = new DataProducer();
        DataListener dataListener = new BlockingBatchDataListener(); // new StreamableDataListener();
        dataProducer.regiseterListener(dataListener);

        dataProducer.startUp();
        Thread.sleep(10 * 1000);
        dataProducer.shutdown();
    }

}
