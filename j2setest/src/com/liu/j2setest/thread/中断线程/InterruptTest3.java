package com.liu.j2setest.thread.中断线程;

/**
 * Created by liuzhilei on 2017/6/25.
 * 使用thread.interrupt()中断阻塞状态的线程
 * <p/>
 * 线程处于阻塞状态时，调用interrupt会使线程收到异常，进入异常代码块后我们需要再次确定
 * 线程是否真的应该停止，以方便做后续的操作
 */
public class InterruptTest3 extends Thread {

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("线程正在运行");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                /**
                 * 当线程处于sleep，Object.wait()，或者join等阻塞状态，调用interrupt()方法
                 * 会被InterruptedException异常捕获到，而且中断状态会被清除
                 */
                System.out.println("线程中断，被InterruptedException异常捕获到");
                System.out.println("因为线程为了处理异常已经重新处于就绪状态，所以中断状态会被清除，所以状态为：" + this.isInterrupted());
                /**
                 * 线程中不中断由我们决定，如果确定要中断线程，需要重新设置中断标志位
                 * 如果不中断，就不调用
                 */
                this.interrupt();
            }
        }
        System.out.println("线程退出");
    }

    public static void main(String[] args) throws Exception {
        InterruptTest3 test1 = new InterruptTest3();
        System.out.println("线程开始运行......");
        test1.start();
        Thread.sleep(3000);
        test1.interrupt();
        System.out.println("通过thread.interrupt()发出中断请求，以便让正在运行的线程停止");
        Thread.sleep(3000);
        System.out.println("应用退出。");
    }

}
