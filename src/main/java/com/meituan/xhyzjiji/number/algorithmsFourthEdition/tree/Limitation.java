package com.meituan.xhyzjiji.number.algorithmsFourthEdition.tree;

public class Limitation {

    private int offset;
    private int limit;

    public Limitation(
        int offset,
        int limit
    ) {
        this.offset = offset;
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void decreaseOffset() {
        this.offset--;
    }

    public void decreaseLimit() {
        this.limit--;
    }
}
