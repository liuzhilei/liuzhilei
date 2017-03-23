package com.liu.j2setest.thread.maintest.各种线程池;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liuzhilei on 2017/3/23.
 *
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
            executorService.shutdown();
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
