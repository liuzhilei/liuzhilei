package com.liu.j2setest.refrencetest;

import java.util.*;

/**
 * Created by liuzhilei on 2017/7/31.
 * -Xms2m -Xmx2m
 * 使用weakHashMap可以正常运行，但是实用hashMap就不能运行，报堆内存溢出
 * weakHashMap可以用于缓存，数据存放在内存中，当空间不够可以回收，也不影响正常使用
 */
public class WeakHashMapTest {
    public static void main(String[] args) {
        Map weakMap = new WeakHashMap();
        List list = new ArrayList();
        for (int i = 0; i < 100000; i++) {
            Integer ii = new Integer(i);
            /**
             * weakHashMap的key是弱引用，但是如果这个key的引用在代码别的地方存在强引用，就不能进行回收
             * 所以如果此处添加list.add(ii)，那么key就有了强引用，会报对内存溢出
             */
            //list.add(ii);
            weakMap.put(ii, new byte[i]);
        }
        System.out.println(weakMap.get(1));

        /*Map hashMap = new HashMap();
        for (int i = 0; i < 100000; i++) {
            Integer ii = new Integer(i);
            hashMap.put(ii, new byte[i]);
        }
        System.out.println(hashMap.get("1"));*/

    }
}
