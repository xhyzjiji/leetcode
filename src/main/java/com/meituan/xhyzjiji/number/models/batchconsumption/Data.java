package com.meituan.xhyzjiji.number.models.batchconsumption;

public class Data {

    private final int key;
    private final String value;

    public Data(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override public String toString() {
        return "Data{" +
            "key=" + key +
            ", value='" + value + '\'' +
            '}';
    }
}
