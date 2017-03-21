package com.liu.j2setest.thread.test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuzhilei on 2017/3/20.
 * 下面是线程不安全的，因为Integer是final类型的类，integer++每次都对应生成一个新的对象
 */
public class HashMapDemo implements Runnable {

    public static Integer integer = 0;

    static HashMapDemo hashMapDemo = new HashMapDemo();

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            synchronized (integer) {
                integer++;
                //System.out.println(Thread.currentThread().getName());
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(hashMapDemo);
        t1.setName("A");
        Thread t2 = new Thread(hashMapDemo);
        t2.setName("B");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(integer);

    }
}
