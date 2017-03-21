package com.liu.j2setest.thread.java多线程编程核心技术.第三章线程间通信.等待通知机制;

/**
 * Created by liuzhilei on 2017/3/21.
 * wait/notify:wait是线程暂停，notify使暂停的线程继续执行
 * <p/>
 * 注意：
 * 1.调用wait前，线程必须获得该对象的锁，必须在同步方法或者同步代码块中
 * 2.调用notify前，线程也不洗获得该对象的锁，必须在同步方法或同步代码块中,调用notify不会立马释放该对象的锁，而是同步方法或者代码块之行结束，才回释放锁
 */
public class Test1 {
    public static void main(String[] args) {
        /*
        //这段代码错误，因为没有拿到对象的锁
        String string = "";
        try {
            string.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        try {
            String str = "";
            System.out.println("同步代码块之前...");
            synchronized (str) {
                System.out.println("进入同步代码块...");
                str.wait();
                System.out.println("执行wait方法之后...");
            }
            System.out.println("退出同步代码块...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
