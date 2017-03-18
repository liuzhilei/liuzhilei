package com.liu.j2setest.thread.java多线程编程核心技术.第二章对象及变量的并发访问.synchronized同步语句块;

/**
 * Created by liuzhilei on 2017/3/17.
 * <p/>
 * synchronized同步块和String一块使用。
 * string会放入常量池中,调用print时候，两个线程传入的变量都是"A"，他们是同一个对象，两个线程具有相同的锁，
 */
public class Test4 {

    public static void print(String stringParam) {
        try {
            synchronized (stringParam) {
                while (true) {
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Test4ThreadA extends Thread {
    @Override
    public void run() {
        super.run();
        Test4.print("A");
    }
}

class Test4ThreadB extends Thread {
    @Override
    public void run() {
        super.run();
        Test4.print("A");
    }
}

class Test4Main {
    public static void main(String[] args) {
        Test4 test4 = new Test4();
        Thread thread = new Test4ThreadA();
        thread.setName("A");
        Thread thread1 = new Test4ThreadB();
        thread1.setName("B");
        thread.start();
        thread1.start();
    }
}
