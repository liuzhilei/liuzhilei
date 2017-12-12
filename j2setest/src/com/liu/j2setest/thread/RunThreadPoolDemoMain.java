package com.liu.j2setest.thread;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by liuzhilei on 2017/12/12.
 */
public class RunThreadPoolDemoMain {
    public static void main(String[] args) {
        ThreadPoolDemo threadPoolDemo = new ThreadPoolDemo(5, 20, new LinkedBlockingQueue<Runnable>(100));
        for (int i = 0; i < 100; i++) {
            testRunnable testRunnable = new testRunnable(i);
            threadPoolDemo.execute(testRunnable);
        }
        System.out.println("你好");

    }
}

class testRunnable implements Runnable {
    int i;
    public testRunnable(int i){
        this.i = i;
    }
    @Override
    public void run() {
        try {
            System.out.println("test run ..." + i);
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
