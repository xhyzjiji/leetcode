package com.meituan.xhyzjiji.number.models.batchconsumption.listener;

import com.meituan.xhyzjiji.number.models.batchconsumption.LifeCycle;
import java.util.List;

public interface DataListener<E> extends LifeCycle {

    void handle(E data) throws Exception;

    void batchHandle(List<E> data) throws Exception;

}
