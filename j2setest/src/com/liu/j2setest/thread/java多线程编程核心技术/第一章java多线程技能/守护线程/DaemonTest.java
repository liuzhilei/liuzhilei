package com.liu.j2setest.thread.java多线程编程核心技术.第一章java多线程技能.守护线程;

/**
 * Created by liuzhilei on 2017/3/15.
 * 守护线程
 * <p/>
 * java中有两种线程：用户线程和守护线程
 * 守护线程：
 * 有陪伴的意思，总是伴随非守护线程存在 ，当非守护线程不存在，守护线程自动销毁。
 * 典型的守护线程就是GC垃圾回收器
 */
public class DaemonTest extends Thread {
    int i = 0;

    @Override
    public void run() {
        while (true) {
            System.out.println(i++);
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class DaemonMain {
    public static void main(String[] args) {
        Thread thread = new DaemonTest();
        thread.setDaemon(true);//设置为守护线程
        thread.start();
        try {
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main线程退出，守护线程也不会存在，所以不再打印");
    }
}
