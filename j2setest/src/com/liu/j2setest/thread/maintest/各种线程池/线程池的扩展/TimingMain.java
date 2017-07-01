package com.liu.j2setest.thread.maintest.各种线程池.线程池的扩展;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/7/1.
 */
public class TimingMain {
    public static void main(String[] args) {
        /**
         * 此处如果不将核心线程数设置为0，那么该线程池永远不会结束
         * 因为超时时间针对的时超过核心线程数的线程。超时以后，会回收多余的线程，但是不会回收核心线程
         */
        ExecutorService executorService = new TimingThreadPool(0, 100, 10L,
                TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
        executorService.execute(new ThreadTest("thread1"));
        executorService.execute(new ThreadTest("thread2"));
        executorService.execute(new ThreadTest("thread3"));

        //executorService.shutdown();

    }

    static class ThreadTest implements Runnable {
        private String name;

        public ThreadTest(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                System.out.println(name);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "Runnable:ThreadTest{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
