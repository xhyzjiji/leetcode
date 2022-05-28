package com.meituan.xhyzjiji.number.models.batchconsumption;

import java.util.List;

public interface DataFactory<E> {

    E createOne();

    List<E> createMany();

}
