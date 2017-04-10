package com.liu.j2setest.thread.maintest.多线程异常捕获.线程池情况;

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
            System.out.println("实现Runnable没有返回值，输出肯定是空：-------  " + submit.get());
        } catch (InterruptedException e) {
            System.out.println("22222");
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println("111111");
            e.printStackTrace();
        }

        Future<Integer> submit1 = executorService.submit(new ThreadTest1());
        try {
            System.out.println("实现Callable有返回值，输出数值为：--------" + submit1.get());
        } catch (InterruptedException e) {
            System.out.println("333333");
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println("444444");
            e.printStackTrace();
        }


    }
}


class ThreadTest implements Runnable {
    @Override
    public void run() {
        int i = 1;
        //i = i / 0;
    }
}

class ThreadTest1 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        int i = 1/0;
        return 1;
    }
}

class MyException implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("异常捕获");
        e.printStackTrace();
    }
}
