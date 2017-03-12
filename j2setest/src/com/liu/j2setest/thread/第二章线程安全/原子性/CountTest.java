package com.liu.j2setest.thread.第二章线程安全.原子性;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liuzhilei on 2017/3/12.
 * 原子基数
 */
public class CountTest {

    private static Integer count = 0;

    public synchronized int getValue() {
        ++count;
        System.out.println(count);
        return count;
    }

    /*private static AtomicInteger count = new AtomicInteger();
    public Integer getValue(){
        return count.incrementAndGet();
    }*/

    public static void main(String[] args) throws Exception{
        CountTest countTest = new CountTest();
        for (int i = 0; i < 1000; i++) {
            CountThread countThread = new CountThread(countTest);
            Thread thread = new Thread(countThread);
            thread.start();
        }

        /**
         * System.out.println(count);
         * 注意，此处的输出最终结果不一样，因为这个是在线程之外，所以结果肯定会有偏差
         *
         */
    }

}

class CountThread implements Runnable {

    private CountTest countTest;

    public CountThread(){}

    public CountThread(CountTest countTest) {
        this.countTest = countTest;
    }

    @Override
    public void run() {
        countTest.getValue();
    }
}
