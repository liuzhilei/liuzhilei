package com.liu.j2setest.thread.maintest.各种线程池.线程池的扩展;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2017/7/1.
 * 继承ThreadPoolExecutor，对其进行扩展
 * <p/>
 * 一般情况下对于线程池的扩展可以用于收集统计信息和添加日志记录等
 */
public class TimingThreadPool extends ThreadPoolExecutor {

    private final ThreadLocal<Long> startTime = new ThreadLocal<Long>();
    //执行任务的次数
    private final AtomicLong numTasks = new AtomicLong();
    //总的执行时间
    private final AtomicLong totalTime = new AtomicLong();
    private final Logger logger = Logger.getLogger("TimingThreadPool");

    public TimingThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        logger.info(String.format("将要执行任务的线程：%s,将执行的任务：%s", t, r));
        startTime.set(System.nanoTime());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        try {
            long endTime = System.nanoTime();
            long executeTime = endTime - startTime.get();
            numTasks.incrementAndGet();
            totalTime.addAndGet(executeTime);
            logger.info(String.format("任务：%s，time：%dns", r, executeTime));
        } finally {
            super.afterExecute(r, t);
        }
    }

    @Override
    protected void terminated() {
        try {
            logger.info(String.format("线程池终止时调用的方法，任务平均执行时间为：%dns", totalTime.get() / numTasks.get()));
        } finally {
            super.terminated();
        }

    }
}
