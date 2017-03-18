package com.liu.j2setest.thread.java多线程编程核心技术.第二章对象及变量的并发访问.synchronized同步语句块;

/**
 * Created by liuzhilei on 2017/3/17.
 * 静态方法synchronized方法和普通synchronized方法
 *
 * 下面输出结果为异步输出，因为 静态方法synchronized方法是Class类锁、普通synchronized方法是对象锁，不是同一个锁
 */
public class Test3 {
    synchronized public static void printA() {
        System.out.println("进入同步静态方法A，线程名称：" + Thread.currentThread().getName() + ", 当前时间：" + System.currentTimeMillis());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("离开同步静态方法A，线程名称：" + Thread.currentThread().getName() + ", 当前时间：" + System.currentTimeMillis());
    }

    synchronized public static void printB() {
        System.out.println("进入同步静态方法B，线程名称：" + Thread.currentThread().getName() + ", 当前时间：" + System.currentTimeMillis());
        System.out.println("离开同步静态方法B，线程名称：" + Thread.currentThread().getName() + ", 当前时间：" + System.currentTimeMillis());
    }

    synchronized public void printC() {
        System.out.println("进入普通同步方法C，线程名称：" + Thread.currentThread().getName() + ", 当前时间：" + System.currentTimeMillis());
        System.out.println("离开普通同步方法C，线程名称：" + Thread.currentThread().getName() + ", 当前时间：" + System.currentTimeMillis());
    }

}

class Test3ThreadA extends Thread {
    private Test3 test3;

    public Test3ThreadA(Test3 test3) {
        this.test3 = test3;
    }

    @Override
    public void run() {
        super.run();
        test3.printA();
    }
}

class Test3ThreadB extends Thread {
    private Test3 test3;

    public Test3ThreadB(Test3 test3) {
        this.test3 = test3;
    }

    @Override
    public void run() {
        super.run();
        test3.printB();
    }
}

class Test3ThreadC extends Thread {
    private Test3 test3;

    public Test3ThreadC(Test3 test3) {
        this.test3 = test3;
    }

    @Override
    public void run() {
        super.run();
        test3.printC();
    }
}

class Test3Main {
    public static void main(String[] args) {
        Test3 test3 = new Test3();
        Thread threadA = new Test3ThreadA(test3);
        threadA.setName("A");
        Thread threadB = new Test3ThreadB(test3);
        threadB.setName("B");
        Thread threadC = new Test3ThreadC(test3);
        threadC.setName("C");

        threadA.start();
        threadB.start();
        threadC.start();
    }
}
