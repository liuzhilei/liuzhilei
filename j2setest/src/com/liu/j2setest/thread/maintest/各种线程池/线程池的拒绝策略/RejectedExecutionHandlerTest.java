package com.liu.j2setest.thread.maintest.各种线程池.线程池的拒绝策略;

import java.util.concurrent.*;

/**
 * Created by liuzhilei on 2017/3/23.
 * <p/>
 * 拒绝策略测试
 * 执行拒绝策略的条件：
 * 核心线程数，最大线程数，缓存队列 三者都满了，会执行拒绝策略
 */
public class RejectedExecutionHandlerTest {
    public static void main(String[] args) {
        int corePoolSize = 2;                               //核心线程数
        int maximumPoolSize = 5;                            //最大线程数
        long keepAliveTime = 5;                             //线程池维护线程锁允许的空闲时间
        BlockingQueue<Runnable> workQueue
                = new LinkedBlockingDeque<Runnable>(10);    //线程池锁使用的缓存队列
        RejectedExecutionHandler handler = null;            //使用的拒绝策略

        /**
         * 1.直接抛出 RejectedExecutionException 异常
         * 线程池默认使用的是该拒绝策略
         */
        handler = new ThreadPoolExecutor.AbortPolicy();

        /**
         * 2.对于被拒绝的线程任务，不再通过线程池，而是直接调用execute方法执行,如果调用execute方法执行的程序关闭，则放弃这些线程任务
         */
        //handler = new ThreadPoolExecutor.CallerRunsPolicy();

        /**
         * 3.抛弃旧的任务，执行新的任务。
         */
        //handler = new ThreadPoolExecutor.DiscardOldestPolicy();

        /**
         * 4.抛弃当前的任务
         */
        //handler = new ThreadPoolExecutor.DiscardPolicy();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                keepAliveTime, TimeUnit.SECONDS, workQueue, handler);

        for (int i = 0; i < 20; i++) {
            threadPoolExecutor.execute(new RejectTask());
        }

        threadPoolExecutor.shutdown();
    }
}

class RejectTask implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " --- is running...");
    }
}
