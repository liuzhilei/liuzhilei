package com.liu.j2setest.collection.利用map实现LRU;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by liuzhilei on 2017/9/28.
 * LRU，即最近最少使用。也就是删除最老的数据，保留最新的固定数据
 */
public class LRUCacheDemo1<K, V> extends LinkedHashMap<K, V> {
    private static int max_size;

    public LRUCacheDemo1(int size) {
        //为true，会按照访问顺序排序。为false，会按照插入顺序排序
        super((int) (Math.ceil(size / 0.75)) + 1, 0.75f, true);
        max_size = size;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > max_size;
    }

    public static void main(String[] args) {
        LRUCacheDemo1<Integer, String> lruCacheDemo1 = new LRUCacheDemo1(2);
        lruCacheDemo1.put(1, "1");
        lruCacheDemo1.put(2, "2");
        lruCacheDemo1.put(3, "3");
        lruCacheDemo1.put(4, "4");

        for (Map.Entry entry : lruCacheDemo1.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

        //这种方式最实用的写法如下：true为按照访问顺序排序
        final int maxSize = 2;
        Map map = new LinkedHashMap(maxSize, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > maxSize;
            }
        };
    }
}
