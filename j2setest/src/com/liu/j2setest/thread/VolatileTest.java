package com.liu.j2setest.thread;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by liuzhilei on 2016/12/29.
 */
public class VolatileTest {

    private static int count = 0;

    public static void incr() {

        Set set = new HashSet();
        set.iterator();
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

