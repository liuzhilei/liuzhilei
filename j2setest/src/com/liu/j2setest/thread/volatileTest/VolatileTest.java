package com.liu.j2setest.thread.volatileTest;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by liuzhilei on 2016/12/29.
 * 使用volatile，说明该变量不会在各个线程栈中缓存，所有线程共享该变量，并不能保证同步
 */
public class VolatileTest {

    private static volatile int count = 0;

    public static void incr() {

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        count++;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    incr();
                }
            }).start();

        }

        System.out.println(VolatileTest.count);

    }
}

