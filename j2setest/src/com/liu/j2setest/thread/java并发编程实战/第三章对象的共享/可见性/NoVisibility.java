package com.liu.j2setest.thread.java并发编程实战.第三章对象的共享.可见性;

/**
 * Created by liuzhilei on 2017/3/13.
 */
public class NoVisibility {

    private static boolean ready;
    private static int number;

    private static class ReaderThread extends  Thread{
        @Override
        public void run() {
            while (!ready)
                Thread.yield();
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }

}
