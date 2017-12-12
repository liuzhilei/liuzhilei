package com.liu.j2setest.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liuzhilei on 2017/12/12.
 */
public class ThreadPoolDemo {
    private volatile int corePoolSize;
    private volatile LinkedBlockingQueue<Runnable> linkedBlockingQueue;
    private volatile int maxPoolSize;
    private volatile int currentThreadSize;
    private ReentrantLock lock = new ReentrantLock();
    private volatile List<Thread> threads = new ArrayList<Thread>();

    public ThreadPoolDemo(int corePoolSize, int maxPoolSize, LinkedBlockingQueue<Runnable> linkedBlockingQueue) {
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxPoolSize;
        this.linkedBlockingQueue = linkedBlockingQueue;
        runQueue();
    }

    public void execute(Runnable runnable) {
        try {
            lock.lock();
            //是否在核心线程中运行
            if (checkCurrentThread(runnable)) {
                return;
            }
            //是否再增加线程到最大线程
            if (!checkAddMaxThread(runnable)) {
                throw new RuntimeException("已经到达最大线程数量，执行拒绝策略，抛出异常");
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * 判断是否再增加线程
     *
     * @return
     */
    private boolean checkCurrentThread(Runnable runnable) {
        try {
            //lock.lock();
            if (currentThreadSize < corePoolSize) {
                currentThreadSize++;
                Thread thread = new Thread(runnable);
                System.out.println("线程池中第" + currentThreadSize + "个线程执行");
                thread.start();
                threads.add(thread);
                return true;
            }
            return false;
        } finally {
            //lock.unlock();
        }
    }

    /**
     * 判断是否再增加线程到最大线程
     *
     * @return
     */
    private boolean checkAddMaxThread(Runnable runnable) {
        try {
            //lock.lock();
            //是否加入到队列中
            if (linkedBlockingQueue.offer(runnable)) {
                System.out.println("加入到了队列。。。");
                return true;
            }
            if (currentThreadSize >= corePoolSize && currentThreadSize < maxPoolSize) {
                currentThreadSize++;
                Thread thread = new Thread(runnable);
                System.out.println("线程池中第" + currentThreadSize + "个线程执行");
                thread.start();
                threads.add(thread);
                return true;
            }
            return false;
        } finally {
            //lock.unlock();
        }
    }

    private void runQueue() {
        innerThread innerThread = new innerThread();
        Thread thread = new Thread(innerThread);
        thread.start();
    }

    class innerThread implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println("loop.......");
                    Thread.sleep(100);
                    int threadIndex = new Random().nextInt(threads.size());
                    Thread thread = threads.get(threadIndex);
                    if (!thread.getState().equals(Thread.State.TERMINATED)) {
                        System.out.println("重新选择线程");
                        continue;
                    }
                    Runnable take = linkedBlockingQueue.take();
                    thread = new Thread(take);
                    System.out.println("新建的线程执行。。。" + new Random().nextInt());
                    thread.start();

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
