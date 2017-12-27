package com.liu.j2setest.thread.maintest.各种线程池;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by liuzhilei on 2017/12/27.
 */
public class ScheduledThreadPoolTest {
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);

        int index = 0;
        ThreadDemo threadDemo = new ThreadDemo(index);

        //首次执行延迟2秒，只会执行一次
        executor.schedule(threadDemo, 2, TimeUnit.SECONDS);

        //首次执行延迟1秒，以后执行周期为2秒
        executor.scheduleAtFixedRate(threadDemo, 1, 2, TimeUnit.SECONDS);

        //首次执行延迟1秒，以后每次执行，都在上一次执行结束后2秒触发
        executor.scheduleWithFixedDelay(threadDemo, 1, 2, TimeUnit.SECONDS);
    }
}

class ThreadDemo implements Runnable {
    private int index;

    public ThreadDemo(int index) {
        this.index = index;
    }

    @Override
    public void run() {
        index++;
        System.out.println("2 = " + getTimes() + " " + index);
    }


    private String getTimes() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
        Date date = new Date(System.currentTimeMillis());
        return format.format(date);
    }
}
