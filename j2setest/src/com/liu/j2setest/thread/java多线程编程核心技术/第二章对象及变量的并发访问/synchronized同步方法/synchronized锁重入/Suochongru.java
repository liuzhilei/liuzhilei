package com.liu.j2setest.thread.java多线程编程核心技术.第二章对象及变量的并发访问.synchronized同步方法.synchronized锁重入;

/**
 * Created by liuzhilei on 2017/3/17.
 * synchronized锁是可以重入的，当一个线程得到一个对象锁以后，是可以再次调用这个对象的锁的
 * 因为如果不可以，肯定会发生死锁
 */
public class Suochongru {

    synchronized public void service1() {
        System.out.println("同步方法 service1 被调用");
        service2();
    }

    synchronized public void service2() {
        System.out.println("同步方法 service2 被调用");
        service3();
    }

    synchronized public void service3() {
        System.out.println("同步方法 service3 被调用");
    }

}

class SuochongruThread extends Thread {
    @Override
    public void run() {
        new Suochongru().service1();
    }
}

class SuochongruMain {
    public static void main(String[] args) {
        Thread thread = new SuochongruThread();
        thread.start();
    }
}
