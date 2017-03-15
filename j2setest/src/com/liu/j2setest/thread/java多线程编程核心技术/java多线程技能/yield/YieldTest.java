package com.liu.j2setest.thread.java多线程编程核心技术.java多线程技能.yield;

import com.sun.org.apache.xpath.internal.SourceTree;

/**
 * Created by liuzhilei on 2017/3/15.
 */
public class YieldTest extends Thread {

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            //Thread.yield();
            //System.out.println("i:" + i);
        }
        long end = System.currentTimeMillis();
        System.out.println("用时：" + (end - start));
    }

}

class YieldMain {
    public static void main(String[] args) {
        Thread thread = new YieldTest();
        thread.setPriority(2);
        thread.start();
    }
}
