package com.liu.j2setest.thread.多线程异常捕获.线程池情况;

import java.util.concurrent.*;

/**
 * Created by liuzhilei on 2017/3/21.
 * <p/>
 * 多线程捕获异常
 * 方法1：新建一个类实现Thread.UncaughtExceptionHandler，运行线程池之前指定异常捕获类就可以
 * 方法2，运行executorService.submit(),返回future，调用future.get，然后进行捕获：注意需要是Callable，因为Runnable不会有返回值
 */
public class ThreadPool implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        int i = 1;
        return i / 0;
    }
}

class ThreadPoolMain {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        //1.executorService.execute(new Runnable())不会有返回结果，拿不到线程池的异常)
        Thread.setDefaultUncaughtExceptionHandler(new MyThreadException());
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                i = i / 0;
            }
        });


        //2.executorService.submit(new Callable())可以拿到结果，然后可以捕获异常
        /*Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int i = 0;
                return i / 0;
            }
        });
        try {
            System.out.println("拿到了线程的运行结果，开始做一下乱七八糟的操作");
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println("捕获到异常");
            e.printStackTrace();
        }*/


    }
}

class MyThreadException implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("异常捕获到了");
        e.printStackTrace();
    }
}

