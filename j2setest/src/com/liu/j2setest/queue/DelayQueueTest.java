package com.liu.j2setest.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by liuzhilei on 2017/6/19.
 * DelayQueue是一个具有延期的无界阻塞队列，只有延期满了以后，他的队头才有元素，未满取出的结果是null。
 * 他其实是一个按照delay时间排序的PriorityQueue，里面的元素都实现了Delayed接口，相关操作判断是否到期。
 * 一般应用在跟实际相关的缓存，session等具有过期时间的操作上面
 * <p/>
 * 例子：网吧上网，到点下机的例子
 */
public class DelayQueueTest {

    public static void main(String[] args) {
        DelayQueueTest test = new DelayQueueTest();
        BlockingQueue<Consumer> queue = new DelayQueue<Consumer>();
        DelayThread thread = test.new DelayThread(queue);
        new Thread(thread).start();

        thread.onComputer("小明", 11111, 3);
        thread.onComputer("小红", 22222, 7);
        thread.onComputer("小蓝", 33333, 15);


        int i = 0;
        while (true) {
            try {
                Thread.sleep(1000);
                i++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }

    }

    class DelayThread implements Runnable {
        BlockingQueue<Consumer> queue = null;

        public DelayThread(BlockingQueue<Consumer> queue) {
            this.queue = queue;
        }

        /**
         * 上机
         */
        public void onComputer(String name, Integer idCard, Integer money) {
            try {
                Consumer consumer = new Consumer(name, idCard, money);
                queue.put(consumer);
                System.out.println("身份证：" + idCard + "，姓名：" + name + "，上机时间：" + money);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void outComputer(Consumer c) {
            System.out.println("身份证：" + c.getIdCard() + "，姓名：" + c.getName() + "下机");
        }

        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println("检查是否到点...");
                    //直到有到期的元素，队头才回有元素，take才能执行
                    Consumer consumer = queue.take();
                    outComputer(consumer);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Consumer implements Delayed {

        private String name;

        private Integer idCard;

        public Consumer(String name, Integer idCard, Integer money) {
            this.name = name;
            this.idCard = idCard;
            this.endTime = money * 1000 + System.currentTimeMillis();
        }

        //规定一秒钟一块钱
        private long endTime;

        /**
         * 判断过期时间
         *
         * @param unit
         * @return
         */
        @Override
        public long getDelay(TimeUnit unit) {
            return this.endTime - System.currentTimeMillis();
        }

        /**
         * 设置优先级
         *
         * @param o
         * @return
         */
        @Override
        public int compareTo(Delayed o) {
            Consumer c = (Consumer) o;
            return this.endTime - c.endTime > 0 ? 1 : (this.endTime - c.endTime < 0 ? -1 : 0);

        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getIdCard() {
            return idCard;
        }

        public void setIdCard(Integer idCard) {
            this.idCard = idCard;
        }
    }
}
