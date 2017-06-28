package com.liu.j2setest.thread.maintest.各种线程池;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by liuzhilei on 2017/3/23.
 * <p/>
 * 固定大小线程池，Executors.newFixedThreadPool利用的是 LinkedBlockingQueue，新任务来的时候，会阻塞到队列中，不会新增线程池
 * 如果不主动关闭，线程池会一直存在。直到调用shutdown
 */
public class NewFixedThreadPoolTest {
    public static void main(String[] args) {
        //Executors.newSingleThreadExecutor();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.execute(new FixedThreadPoolThread());
        try {
            Thread.sleep(5000);
            //主动关闭
            /**
             * 关闭了submit通道，不再接受新的任务，线程池中已有的任务执行
             * 结束以后，线程池关闭
             */
            executorService.shutdown();
            /**
             * 立即关闭正在执行的任务，不再接受新任务，返回线程池中等待
             * 执行的任务列表
             */
            executorService.shutdownNow();
            /**
             * 当前线程会阻塞，直到线程池中所有任务执行完成，或者到达
             * 超时时间，或者线程被中断
             * 调用该方法后，线程池还会接受新的任务
             */
            executorService.awaitTermination(60, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class FixedThreadPoolThread implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(1);
    }
}
