package com.liu.j2setest.thread.maintest.各种线程池;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liuzhilei on 2017/3/23.
 * <p/>
 * 无界线程池：Executors.newCachedThreadPool()使用了 SynchronousQueen
 * 线程池空闲60秒，会退出
 * SynchronousQueen是没有数据缓冲的BlockingQueue，线程的插入put必须等待另一个线程的移除take，反之也一样，也就是说需要配对。
 * 如果有空闲的，会重用空闲的可用线程。如果所有线程都在运行，再有新任务的到来，会创建新的线程，这就可能导致内存溢出
 */
public class NewCachedThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Task());
        /*for (int i = 0; i < 100; i++) {
            //代码可能导致死机
            executorService.execute(new Task());
        }*/
    }
}

class Task implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println("线程运行...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
