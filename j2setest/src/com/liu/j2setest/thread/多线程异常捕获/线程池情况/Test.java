package com.liu.j2setest.thread.多线程异常捕获.线程池情况;

import java.util.concurrent.*;

/**
 * Created by liuzhilei on 2017/3/22.
 */
public class Test {
    public static void main(String[] args) {
        ThreadTest threadTest = new ThreadTest();
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        /*Thread.setDefaultUncaughtExceptionHandler(new MyException());
        executorService.execute(threadTest);*/

        Future<?> submit = executorService.submit(threadTest);
        try {
            submit.get();
        } catch (InterruptedException e) {
            System.out.println("22222");
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println("111111");
            e.printStackTrace();
        }


    }
}


class ThreadTest implements Runnable {
    @Override
    public void run() {
        int i = 1;
        i = i / 0;
    }
}

class MyException implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("异常捕获");
        e.printStackTrace();
    }
}
