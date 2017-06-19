package com.liu.j2setest.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by liuzhilei on 2017/6/19.
 * ArrayBlockingQueue是基于数组实现的阻塞队列
 * 生产和消费用的同一把锁(使用ReentrantLock，默认使用非公平锁)，这意味着生产者放入数据的时候，消费者就不能消费数据。
 * 如果队列固定大小，使用ArrayBlockingQueue，因为使用循环数组实现；
 * 如果队列不固定大小，使用LinkedBlockingQueue，避免了使用ArrayBlockingQueue带来的数组复制。
 */
public class ArrayBlockingQueueTest {
    public static void main(String[] args) throws InterruptedException{
        BlockingQueue<String> queue = new ArrayBlockingQueue(10);
        //队列满，就返回false
        queue.offer("添加");
        //取出队头元素，为空就阻塞
        queue.take();
    }
}
