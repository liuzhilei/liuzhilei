package com.liu.j2setest.thread.java并发编程实战.第三章对象的共享.线程封闭;

/**
 * Created by liuzhilei on 2017/3/14.
 */
public class ThreadLocalTest {

    private static ThreadLocal<Integer> localTest = new ThreadLocal<Integer>(){
        public Integer getString(){
            return 1;
        };
    };

    public static void main(String[] args) {
        System.out.println(localTest.get());
    }
}
