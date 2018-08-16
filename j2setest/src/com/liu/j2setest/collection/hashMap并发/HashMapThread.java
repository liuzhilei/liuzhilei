package com.liu.j2setest.collection.hashMap并发;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liuzhilei3 on 2018/8/15.
 */
public class HashMapThread extends Thread {
    static Map<Integer, Integer> map = new HashMap<>();
    static AtomicInteger at = new AtomicInteger();

    @Override
    public void run() {
        while (at.get() < 100000) {
            map.put(at.get(), at.get());
            at.incrementAndGet();
        }
    }
}
