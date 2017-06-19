package com.liu.j2setest.queue;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by liuzhilei on 2017/6/19.
 * 一种阻塞队列，每次插入操作必须和一次移除操作配对，就是没有数据缓冲的BlockingQueue
 * SynchronousQueue主要用于单个元素在多线程之间的传递
 * <p/>
 * 应用举例：我们要在多个线程中传递一个变量
 */
public class SynchronousQueueTest {

    class Producer implements Runnable {
        private BlockingQueue<String> queue;
        List<String> list = Arrays.asList("one", "two", "three");

        public Producer(BlockingQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                for (String str : list) {
                    queue.put(str);
                }
                queue.put("done");//结束标识
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Consumer implements Runnable {
        private BlockingQueue<String> queue;

        public Consumer(BlockingQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            String str = null;
            try {
                while (!(str = queue.take()).equals("done")) {
                    System.out.println(str);
                    Thread.sleep(3000);//估计sleep，证明Producer是put不进去元素的
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        SynchronousQueueTest test = new SynchronousQueueTest();
        BlockingQueue<String> queue = new SynchronousQueue<String>();

        Producer producer = test.new Producer(queue);
        Consumer consumer = test.new Consumer(queue);

        new Thread(producer).start();
        new Thread(consumer).start();
    }

}
