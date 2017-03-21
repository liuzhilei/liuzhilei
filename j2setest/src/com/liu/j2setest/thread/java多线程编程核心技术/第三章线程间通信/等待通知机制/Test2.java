package com.liu.j2setest.thread.java多线程编程核心技术.第三章线程间通信.等待通知机制;

/**
 * Created by liuzhilei on 2017/3/21.
 */

class Test2Thread1 extends Thread {
    private Object object;

    public Test2Thread1(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        synchronized (object) {
            System.out.println("调用wait方法之前...");
            try {
                object.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("调用wait方法之后...");
        }
    }
}

class Test2Thread2 extends Thread {
    private Object object;

    public Test2Thread2(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        synchronized (object) {
            System.out.println("调用notify方法之前...");
            object.notify();
            System.out.println("调用notify方法之后");
        }
    }
}

public class Test2 {
    public static void main(String[] args) {
        Object o = new Object();
        Thread thread = new Test2Thread1(o);
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread thread1 = new Test2Thread2(o);
        thread1.start();

    }
}
