package com.liu.j2setest.thread.java多线程编程核心技术.第二章对象及变量的并发访问.synchronized同步语句块;

/**
 * Created by liuzhilei on 2017/3/17.
 * <p/>
 * 执行到同步代码块会同步执行，不同步的地方线程会交叉执行
 */
public class Test1 {

    public void doLongTimeTask() {
        for (int i = 0; i < 100; i++) {
            System.out.println("不同步的线程名：" + Thread.currentThread().getName() + ",i=" + i);
        }
        System.out.println("");
        System.out.println("");

        synchronized (this) {
            for (int i = 0; i < 100; i++) {
                System.out.println("同步的线程名：" + Thread.currentThread().getName() + ",i=" + i);
            }
        }
    }

}

class ThreadA extends Thread {
    private Test1 task1;

    public ThreadA(Test1 task1) {
        this.task1 = task1;
    }

    @Override
    public void run() {
        super.run();
        task1.doLongTimeTask();
    }
}

class ThreadB extends Thread {
    private Test1 task1;

    public ThreadB(Test1 task1) {
        this.task1 = task1;
    }

    @Override
    public void run() {
        super.run();
        task1.doLongTimeTask();
    }
}

class Task1Main {
    public static void main(String[] args) {
        Test1 task1 = new Test1();
        Thread thread = new ThreadA(task1);
        Thread thread1 = new ThreadB(task1);
        thread.start();
        thread1.start();
    }
}
