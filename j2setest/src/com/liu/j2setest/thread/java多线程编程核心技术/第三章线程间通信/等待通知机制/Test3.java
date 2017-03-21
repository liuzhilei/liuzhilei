package com.liu.j2setest.thread.java多线程编程核心技术.第三章线程间通信.等待通知机制;

/**
 * Created by liuzhilei on 2017/3/21.
 * 调用notify方法每次只随机通知一个线程进行唤醒
 */
public class Test3 {
    public void testMethod(Object o) {
        synchronized (o) {
            System.out.println("调用wait方法之前:" + Thread.currentThread().getName());
            try {
                o.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("调用wait方法之后" + Thread.currentThread().getName());
        }
    }
}

class Test3Thread1 extends Thread {
    private Object o;

    public Test3Thread1(Object o) {
        this.o = o;
    }

    @Override
    public void run() {
        Test3 test3 = new Test3();
        test3.testMethod(o);
    }
}

class Test3Thread2 extends Thread {
    private Object o;

    public Test3Thread2(Object o) {
        this.o = o;
    }

    @Override
    public void run() {
        Test3 test3 = new Test3();
        test3.testMethod(o);
    }
}

class Test3Thread3 extends Thread {
    private Object o;

    public Test3Thread3(Object o) {
        this.o = o;
    }

    @Override
    public void run() {
        Test3 test3 = new Test3();
        test3.testMethod(o);
    }
}

class Test3NotifyThread extends Thread {
    private Object o;

    public Test3NotifyThread(Object o) {
        this.o = o;
    }

    @Override
    public void run() {
        synchronized (o) {
            //随机唤醒一个wait线程
            //o.notify();

            //唤醒全部wait线程
            o.notifyAll();
        }
    }
}

class Test3Main {
    public static void main(String[] args) {
        Object o = new Object();
        Thread thread = new Test3Thread1(o);
        thread.start();

        Thread thread1 = new Test3Thread2(o);
        thread1.start();

        Thread thread2 = new Test3Thread3(o);
        thread2.start();

        try {
            //这里需要暂停几秒钟，因为如果让下面的notify线程先执行，那么wait方法就不会再会唤醒了，
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread thread3 = new Test3NotifyThread(o);
        thread3.start();

    }
}
