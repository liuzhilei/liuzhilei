package com.liu.j2setest.thread.maintest.多线程异常捕获.单线程情况;

/**
 * Created by liuzhilei on 2017/3/21.
 * 单线程下捕获异常，实现Thread.UncaughtExceptionHandler 即可
 */
public class SingleThread implements Runnable {
    @Override
    public void run() {
        int i = 1 / 0;
    }
}

class SingleThreadMain {
    public static void main(String[] args) {
        Thread thread = new Thread(new SingleThread());
        thread.setUncaughtExceptionHandler(new CatchExceptionHandler());
        thread.start();
    }
}

class CatchExceptionHandler implements Thread.UncaughtExceptionHandler{
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("异常捕获");
        e.printStackTrace();
    }
}
