package com.liu.j2setest.thread.java多线程编程核心技术.第四章lock的使用.使用ReentrantLock类;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liuzhilei on 2017/3/23.
 * 使用ReentrantLock 实现同步
 */
public class Test1 {
    Lock lock = new ReentrantLock();

    public void test() {
        lock.lock();
        for (int i = 0; i < 5; i++) {
            System.out.println("ThreadName = " + Thread.currentThread().getName() + " i = " + (i + 1));
        }
        lock.unlock();
    }
}

class Test1Thread1 extends Thread {
    private Test1 test1;

    public Test1Thread1(Test1 test1) {
        this.test1 = test1;
    }

    @Override
    public void run() {
        test1.test();
    }
}

class Test1Main {
    public static void main(String[] args) {
        Test1 test1 = new Test1();
        Thread thread1 = new Test1Thread1(test1);
        Thread thread2 = new Test1Thread1(test1);
        Thread thread3 = new Test1Thread1(test1);
        Thread thread4 = new Test1Thread1(test1);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }
}
