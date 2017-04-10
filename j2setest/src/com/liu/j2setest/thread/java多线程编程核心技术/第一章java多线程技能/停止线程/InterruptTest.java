package com.liu.j2setest.thread.java多线程编程核心技术.第一章java多线程技能.停止线程;

/**
 * Created by liuzhilei on 2017/3/15.
 * <p/>
 * interrupt()仅仅是在当前线程中打一个停止的标识，并不是真的停止线程
 * Thread.interrupt()不会中断一个正在运行的线程，他的作用是在线程受到阻塞时抛出一个中断信号，这样线程就可以退出阻塞。
 * 更确切的说，如果线程被Object.wait(),Thread.join(),Thread.sleep()方法之一阻塞，他将接收到一个中断异常，从而提早的结束阻塞状态
 */
public class InterruptTest extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            System.out.println("i=" + i);
        }
    }
}

class InterruptMain {
    public static void main(String[] args) {
        Thread t1 = new InterruptTest();
        t1.start();
        try {
            //t1.sleep(1);
            System.out.println(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        t1.interrupt();
    }
}
