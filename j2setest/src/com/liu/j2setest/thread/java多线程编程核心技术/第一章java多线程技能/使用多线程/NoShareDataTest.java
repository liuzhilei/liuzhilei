package com.liu.j2setest.thread.java多线程编程核心技术.第一章java多线程技能.使用多线程;

/**
 * Created by liuzhilei on 2017/3/15.
 */
public class NoShareDataTest extends Thread {
    private int count = 5;

    @Override
    public synchronized void run() {
        count--;
        System.out.println("当前由线程：" + this.currentThread().getName() + "对count进行计算：count=" + count);
    }
}

class NoShareDateMain {
    public static void main(String[] args) {
        NoShareDataTest noShareDataTest = new NoShareDataTest();
        Thread t1 = new Thread(noShareDataTest, "A");
        Thread t2 = new Thread(noShareDataTest, "B");
        Thread t3 = new Thread(noShareDataTest, "C");
        Thread t4 = new Thread(noShareDataTest, "D");
        Thread t5 = new Thread(noShareDataTest, "E");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
