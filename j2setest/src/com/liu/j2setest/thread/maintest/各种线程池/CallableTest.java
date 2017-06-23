package com.liu.j2setest.thread.maintest.各种线程池;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by liuzhilei on 2017/3/23.
 * <p/>
 * Callable的用法
 * 利用FutureTask，放入线程；取得返回值
 * FutureTask在Executor框架中表示异步任务
 */
public class CallableTest {
    public static void main(String[] args) {
        NewCachedThreadPoolTestCallable callableThread = new NewCachedThreadPoolTestCallable();
        FutureTask futureTask = new FutureTask<Integer>(callableThread);
        Thread thread = new Thread(futureTask);
        thread.start();

        try {
            /**
             * futureTask.get()可以捕获到Callable任务抛出的异常ExecutionException
             */
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println("捕获异常");
            e.printStackTrace();
        }
    }
}


/**
 * Callable 具有返回值，出现异常，会将异常封装到ExecutionException中返回
 */
class NewCachedThreadPoolTestCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        Thread.sleep(100);
        int i = 1;
        //故意抛出异常
        i = i / 0;
        return i;
    }
}

/**
 * Runnable 没有返回值，所以即便有future，get以后依然是null
 */
class NewCachedThreadPoolTestRunnable implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(100);
            int i = 1;
            //i = i / 0;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
