package com.liu.j2setest.thread.java多线程编程核心技术.第一章java多线程技能.停止线程;

/**
 * Created by liuzhilei on 2017/3/15.
 * 在睡眠时中断线程
 *
 * 先sleep，再interrupt,在睡眠中中断线程，会报错
 */
public class SleepingInterrupt extends Thread {

    @Override
    public void run() {
        try {
            System.out.println("thread run...");
            Thread.sleep(20000);
            System.out.println("thread end");
        } catch (InterruptedException e) {
            System.out.println("在sleep时被停止，进入catch：" + this.isInterrupted());
            e.printStackTrace();
        }

    }
}

class SleepingMain {
    public static void main(String[] args) {
        Thread thread = new SleepingInterrupt();
        thread.start();
        try {
            thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("main exception");
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
