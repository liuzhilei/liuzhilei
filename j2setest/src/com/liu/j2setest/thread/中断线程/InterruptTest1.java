package com.liu.j2setest.thread.中断线程;

import org.omg.CosNaming.IstringHelper;

import javax.sound.midi.Soundbank;

/**
 * Created by liuzhilei on 2017/6/25.
 * 使用中断信号量中断非阻塞状态的线程
 * <p/>
 * 中断线程最受推荐的方式是：使用共享变量发出信号，告诉线程必须停止正在运行的任务。
 * 线程必须周期性的检查这个变量，然后有秩序的中止任务
 */
public class InterruptTest1 extends Thread {
    //线程中断信号量，需要设置为volatile类型
    private volatile boolean isStop = false;

    @Override
    public void run() {
        while (!isStop) {
            System.out.println("线程正在运行......");

            long now = System.currentTimeMillis();
            while (System.currentTimeMillis() - now < 1000) {
                //这个while只是代替sleep方法，防止因调用sleep需要抛出interrupt方法
            }
        }
        System.out.println("线程停止运行");
    }

    public static void main(String[] args) throws Exception {
        InterruptTest1 test1 = new InterruptTest1();
        System.out.println("线程开始运行......");
        test1.start();
        Thread.sleep(3000);
        System.out.println("设置线程中断信号量为true，以便让正在运行的线程停止");
        test1.isStop = true;
        Thread.sleep(3000);
        System.out.println("应用退出。");
    }
}
