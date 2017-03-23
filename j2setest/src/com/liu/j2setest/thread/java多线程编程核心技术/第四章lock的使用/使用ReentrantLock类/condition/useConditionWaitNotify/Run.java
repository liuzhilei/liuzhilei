package com.liu.j2setest.thread.java多线程编程核心技术.第四章lock的使用.使用ReentrantLock类.condition.useConditionWaitNotify;

/**
 * Created by liuzhilei on 2017/3/23.
 */
public class Run {
    public static void main(String[] args) {
        MyService myService = new MyService();
        Thread thread = new ThreadA(myService);
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread thread1 = new ThreadB(myService);
        thread1.start();
        //myService.signal();
    }
}
