package com.liu.j2setest.thread.第二章线程安全.原子性;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liuzhilei on 2017/3/12.
 * CountDownLatch 闭锁测试
 * <p/>
 * 场景：
 * 计数器，当for循环真正执行完毕以后，在循环外输出count的数值
 * <p/>
 * 定义：完成一组线程操作之前，允许之后的操作一直处于等待状态，直到这组操作执行完毕。
 * 闭锁最初处于封闭状态，每次调用countDown()方法，闭锁计数器都会-1，知道为0后，遇到await()方法，闭锁将会打开，然后开始执行
 * <p/>
 * 闭锁和栅栏区别：
 * 闭锁用于所有线程等待一个外部事件的发生；
 * 栅栏用于所有线程相互等待，直到所有线程到达某一个公共点以后，打开栅栏，通过栅栏执行
 */
public class CountDownLatchTest {

    private static Integer count = 0;

    public synchronized int getValue() {
        ++count;
        return count;
    }

    public static void main(String[] args) throws Exception {
        CountDownLatchTest countTest = new CountDownLatchTest();
        CountDownLatch countDownLatch = new CountDownLatch(1000);
        for (int i = 0; i < 1000; i++) {
            CountDownLatchThread countThread = new CountDownLatchThread(countTest, countDownLatch);
            Thread thread = new Thread(countThread);
            thread.start();
        }
        countDownLatch.await();
        System.out.println(count);
    }

}

class CountDownLatchThread implements Runnable {

    private CountDownLatchTest countTest;
    private CountDownLatch countDownLatch;


    public CountDownLatchThread() {
    }

    public CountDownLatchThread(CountDownLatchTest countTest, CountDownLatch countDownLatch) {
        this.countTest = countTest;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        countTest.getValue();

        countDownLatch.countDown();
    }
}
