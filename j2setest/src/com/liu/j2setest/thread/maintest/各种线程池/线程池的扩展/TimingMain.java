package com.liu.j2setest.thread.maintest.各种线程池.线程池的扩展;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/7/1.
 */
public class TimingMain {
    public static void main(String[] args) {
        ExecutorService executorService = new TimingThreadPool(5, 5, 60L,
                TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
        executorService.execute(new ThreadTest("thread1"));
        executorService.execute(new ThreadTest("thread2"));
        executorService.execute(new ThreadTest("thread3"));

        executorService.shutdown();

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
                Thread.sleep(3000);
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
