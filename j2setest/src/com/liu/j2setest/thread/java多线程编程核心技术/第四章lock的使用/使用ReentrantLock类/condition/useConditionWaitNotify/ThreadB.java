package com.liu.j2setest.thread.java多线程编程核心技术.第四章lock的使用.使用ReentrantLock类.condition.useConditionWaitNotify;

/**
 * Created by liuzhilei on 2017/3/23.
 */
public class ThreadB extends Thread {
    private MyService myService;

    public ThreadB(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.signal();
    }
}
