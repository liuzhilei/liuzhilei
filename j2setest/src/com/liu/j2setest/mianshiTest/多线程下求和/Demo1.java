package com.liu.j2setest.mianshiTest.多线程下求和;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liuzhilei on 2017/12/6.
 * 利用原子操作类 atomicInteger
 */
public class Demo1 {
    static AtomicInteger num = new AtomicInteger(0);

    public static void main(String[] args) throws Exception{
        Thread thread1 = new Demo1Thread1();
        Thread thread2 = new Demo1Thread2();
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(num.get());
    }
}

class Demo1Thread1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 500; i++) {
            Demo1.num.getAndAdd(1);
        }
    }
}


class Demo1Thread2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 500; i++) {
            Demo1.num.getAndAdd(1);
        }
    }
}