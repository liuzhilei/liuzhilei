package com.liu.j2setest.thread.maintest.多线程异常捕获.线程池情况;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liuzhilei on 2017/3/22.
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
    }

}
