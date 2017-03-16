package com.liu.j2setest.thread.java多线程编程核心技术.第二章对象及变量的并发访问.synchronized同步方法.synchronized方法与锁对象;

/**
 * Created by liuzhilei on 2017/3/16.
 * <p/>
 * synchronized同步，锁的是整个对象
 * <p/>
 * 下面方法中，methodA和methodB都是synchronized修饰，当线程先执行到了A，然后进
 * 入sleep，那这时其他线程执行methodB就不能执行，因为synchronized锁住了整个对象
 */
public class SynchronizedTest {
    synchronized public void methodA() {
        try {
            System.out.println("begin method A , threadName =" + Thread.currentThread().getName());
            Thread.sleep(20000);
            System.out.println("method A end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public void methodB() {
        System.out.println("begin method B,threadName=" + Thread.currentThread().getName());
        System.out.println("method b end");
    }
}

class ThreadA extends Thread {
    private SynchronizedTest test;

    public ThreadA(SynchronizedTest test) {
        this.test = test;
    }

    @Override
    public void run() {
        super.run();
        test.methodA();
    }
}

class ThreadB extends Thread {
    private SynchronizedTest test;

    public ThreadB(SynchronizedTest test) {
        this.test = test;
    }

    @Override
    public void run() {
        super.run();
        test.methodB();
    }
}

class SynchronizedMain {
    public static void main(String[] args) {
        SynchronizedTest test = new SynchronizedTest();
        Thread thread = new ThreadA(test);
        thread.setName("a");
        Thread thread1 = new ThreadB(test);
        thread1.setName("b");
        thread.start();
        thread1.start();
    }
}
