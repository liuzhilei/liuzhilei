package com.liu.j2setest.mianshiTest.多线程下求和;

/**
 * Created by liuzhilei on 2017/12/6.
 * 利用synchronized同步
 */
public class Demo2 {
    static int num = 0;
    public static void main(String[] args) throws Exception{
        Demo2 demo2 = new Demo2();

        Thread thread1 = new Demo2Thread1(demo2);
        Thread thread2 = new Demo2Thread2(demo2);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(num);
    }
}

class Demo2Thread1 extends Thread {
    private Demo2 demo2;

    public Demo2Thread1(Demo2 demo2) {
        this.demo2 = demo2;
    }

    @Override
    public void run() {
        synchronized (demo2) {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Demo2.num++;
            }
        }
    }
}

class Demo2Thread2 extends Thread {
    private Demo2 demo2;

    public Demo2Thread2(Demo2 demo2) {
        this.demo2 = demo2;
    }

    @Override
    public void run() {
        synchronized (demo2) {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Demo2.num++;
            }
        }
    }
}