package com.meituan.xhyzjiji.number.models.batchconsumption;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.lang.math.RandomUtils;

public class DefaultDataFactory implements DataFactory<Data> {

    private final int range = 100;
    private final int maxBatchSize = 200;
    private Map<Integer, AtomicInteger> kvMaker;

    public DefaultDataFactory() {
        kvMaker = new HashMap<>((int)(range * 1.34));
        int i = 0;
        while (i < range) {
            kvMaker.put(i, new AtomicInteger(1));
            ++i;
        }
    }

    @Override public Data createOne() {
        int key = RandomUtils.nextInt(range);
        AtomicInteger value = kvMaker.get(key);
        return new Data(key, key + "-" + value.getAndIncrement());
    }

    @Override public List<Data> createMany() {
        int count = RandomUtils.nextInt(maxBatchSize);
        List<Data> manyData = new ArrayList<>();
        while (count > 0) {
            manyData.add(createOne());
            --count;
        }
        return manyData;
    }
}
