package com.liu.j2setest.thread.java多线程编程核心技术.java多线程技能.使用多线程;

/**
 * Created by liuzhilei on 2017/3/15.
 * 线程间不共享数据的情况
 */
public class ShareDataTest extends Thread {
    private int count = 5;

    public ShareDataTest(String name) {
        //设置线程名
        this.setName(name);
    }

    @Override
    public void run() {
        while (count > 0) {
            count--;
            System.out.println("当前由线程" + this.currentThread().getName() + "计算count变量的数值：" + count);
        }
    }
}

class ShareDataMain{
    public static void main(String[] args) {
        Thread t1 = new ShareDataTest("A");
        Thread t2 = new ShareDataTest("B");
        Thread t3 = new ShareDataTest("C");
        t1.start();
        t2.start();
        t3.start();
    }
}
