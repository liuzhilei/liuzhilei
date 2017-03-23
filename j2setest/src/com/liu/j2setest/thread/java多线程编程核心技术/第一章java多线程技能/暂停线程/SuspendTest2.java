package com.liu.j2setest.thread.java多线程编程核心技术.第一章java多线程技能.暂停线程;

/**
 * Created by liuzhilei on 2017/3/15.
 */
public class SuspendTest2 extends Thread {
    private long i = 0;

    @Override
    public void run() {
        while (true) {
            i++;

            System.out.println(i);
            /**
             * println源码：
             * public void println(long x) {
                synchronized (this) {
                    print(x);
                    newLine();
                }
             }

             如果写上这个输出，那main end永远不会打印出来。
             因为当程序运行到println，同步锁不会释放，main end肯定不会打印
             */
        }
    }
}

class SuspendMain2 {
    public static void main(String[] args) {
        Thread thread = new SuspendTest2();
        thread.start();
        try {
            thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.suspend();
        System.out.println("main end");
    }
}
