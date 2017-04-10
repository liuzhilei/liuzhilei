package com.liu.j2setest.thread.java多线程编程核心技术.第二章对象及变量的并发访问.synchronized同步语句块;

/**
 * Created by liuzhilei on 2017/3/18.
 * <p/>
 * 死锁现象
 * <p/>
 * 互相持有了对方的锁，所以发生了死锁现象，导致程序假死
 */
public class Test5 implements Runnable {
    public String userName;
    public Object lock1 = new Object();
    public Object lock2 = new Object();

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public void run() {
        if (userName.equals("a")) {
            synchronized (lock1) {
                System.out.println("userName:" + userName);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println("按照lock1 ---> lock2的代码顺序执行");
                }
            }
        }
        if (userName.equals("b")) {
            synchronized (lock2) {
                System.out.println("userName:" + userName);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println("按照lock2 ---> lock1的代码顺序执行");
                }
            }
        }
    }
}

class Test5Main {
    public static void main(String[] args) {
        Test5 test5 = new Test5();
        Thread thread = new Thread(test5);
        test5.setUserName("a");
        thread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread thread1 = new Thread(test5);
        test5.setUserName("b");
        thread1.start();
    }
}


