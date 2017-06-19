package com.liu.j2setest.queue;

import java.util.concurrent.*;

/**
 * Created by liuzhilei on 2017/6/17.
 * <p/>
 * LinkedBlockingQueue是基于链表的阻塞队列，默认容量是Integer.MAX_VALUE。
 * 是线程安全的阻塞队列，使用ReentrantLock来实现插入锁(putLock)和取出锁(takeLock)，生产消费模式的首选
 * put方法在<b>队列满会阻塞</b>直到有队列成员被消费，
 * take方法在<b>队列空会阻塞</b>直到有队列成员放进来。
 * <p/>
 * 下面是多线程模拟实现生产者/消费者模型的例子
 */
public class LinkedBlockingQueueTest {

    /**
     * 定义装苹果的篮子
     */
    public class Basket {
        //定义容量为3的阻塞队列，可以存放三个苹果
        BlockingQueue<String> queue = new LinkedBlockingQueue<String>(3);

        public void produce() throws InterruptedException {
            //队列满时，会自动阻塞
            queue.put("放入一个苹果");
        }

        public void consume() throws InterruptedException {
            //队列为空时，会自动阻塞
            queue.take();
        }

    }

    /**
     * 苹果生产者
     */
    class Producer implements Runnable {
        private String instance;
        private Basket basket;

        public Producer(String instance, Basket basket) {
            this.instance = instance;
            this.basket = basket;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println(instance + ",放入苹果…………start");
                    basket.produce();
                    System.out.println(instance + ",放入苹果…………end");
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 苹果消费者
     */
    class Consumer implements Runnable {
        private String instance;
        private Basket basket;

        public Consumer(String instance, Basket basket) {
            this.instance = instance;
            this.basket = basket;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println(instance + ",消费苹果…………start");
                    basket.consume();
                    System.out.println(instance + ",消费苹果…………end");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        LinkedBlockingQueueTest test = new LinkedBlockingQueueTest();
        Basket basket = test.new Basket();
        Producer producer1 = test.new Producer("生产者1", basket);
        Producer producer2 = test.new Producer("生产者2", basket);
        Consumer consumer = test.new Consumer("消费者", basket);

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(producer1);
        executorService.execute(producer2);
        executorService.execute(consumer);

    }

}
