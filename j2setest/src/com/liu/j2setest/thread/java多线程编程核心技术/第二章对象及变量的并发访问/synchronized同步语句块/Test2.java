package com.liu.j2setest.thread.java多线程编程核心技术.第二章对象及变量的并发访问.synchronized同步语句块;

/**
 * Created by liuzhilei on 2017/3/17.
 * <p/>
 * synchronized代码块锁的是当前调用的对象,运行到同步代码块以后，其他用synchronized修饰的代码或者方法就必须等其他同步方法执行结束以后才能够执行
 */
public class Test2 {

    public void otherMethod() {
        synchronized (this){
            System.out.println("运行其他方法");
        }
    }

    public synchronized void doSynchronizedTest() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10000; i++) {
            System.out.println("同步方法线程名：" + Thread.currentThread().getName() + ",i=" + i);
        }

    }
}

class Test2Thread1 extends Thread {
    private Test2 test2;

    public Test2Thread1(Test2 test2) {
        this.test2 = test2;
    }

    @Override
    public void run() {
        test2.doSynchronizedTest();
        super.run();
    }
}

class Test2Thread2 extends Thread {
    private Test2 test2;

    public Test2Thread2(Test2 test2) {
        this.test2 = test2;
    }

    @Override
    public void run() {
        test2.otherMethod();
        super.run();
    }
}

class Test2Main {
    public static void main(String[] args) {
        Test2 test2 = new Test2();
        Thread thread = new Test2Thread1(test2);
        Thread thread1 = new Test2Thread2(test2);
        thread.start();
        /*try {
            thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        thread1.start();
    }
}
