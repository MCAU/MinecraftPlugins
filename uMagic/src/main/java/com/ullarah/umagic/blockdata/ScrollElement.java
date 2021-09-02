package com.ullarah.umagic.blockdata;

import java.util.List;

public class ScrollElement<N> {

    private final List<N> list;
    private final int size;

    public ScrollElement(List<N> list) {
        this.list = list;
        this.size = list.size();
    }

    public N scrollItem(N current, int delta) {
        int idx = list.indexOf(current);
        return list.get((idx + delta + size) % size);
    }

    public N terminalItem(boolean forward) {
        return forward ? list.get(0) : list.get(size - 1);
    }

    public List<N> getList() {
        return list;
    }
}
