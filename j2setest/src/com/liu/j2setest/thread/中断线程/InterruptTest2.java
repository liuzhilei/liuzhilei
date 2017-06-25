package com.liu.j2setest.thread.中断线程;

/**
 * Created by liuzhilei on 2017/6/25.
 * 使用thread.interrupt()中断非阻塞状态的线程
 * <p/>
 * 使用thread.interrupt()相比中断信号量来说，代码比较简洁
 */
public class InterruptTest2 extends Thread {

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("线程正在运行......");

            long now = System.currentTimeMillis();
            while (System.currentTimeMillis() - now < 1000) {
                //这个while只是代替sleep方法，防止因调用sleep需要抛出interrupt方法
            }
        }
        System.out.println("线程停止运行");
    }

    public static void main(String[] args) throws Exception {
        InterruptTest2 test1 = new InterruptTest2();
        System.out.println("线程开始运行......");
        test1.start();
        Thread.sleep(3000);
        test1.interrupt();
        System.out.println("通过thread.interrupt()发出中断请求，以便让正在运行的线程停止");
        Thread.sleep(3000);
        System.out.println("应用退出。");
    }
}
