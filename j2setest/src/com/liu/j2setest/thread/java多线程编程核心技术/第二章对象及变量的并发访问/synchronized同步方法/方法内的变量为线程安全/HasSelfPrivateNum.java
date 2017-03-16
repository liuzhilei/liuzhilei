package com.liu.j2setest.thread.java多线程编程核心技术.第二章对象及变量的并发访问.synchronized同步方法.方法内的变量为线程安全;

/**
 * Created by liuzhilei on 2017/3/16.
 *
 * 方法内的变量为线程安全,实例变量为非线程安全
 *
 * synchronized获得的是对象级别的锁
 */
public class HasSelfPrivateNum {
    int num = 0; //实例变量，非线程安全
    public void add(String name) {
        try {
            //int num = 0;//方法内变量，线程安全
            if (name.equals("a")) {
                num = 100;
                System.out.println("name为a，设置num");
                Thread.sleep(2000);
            } else if (name.equals("b")) {
                num = 200;
                System.out.println("name为b，设置num");
            }
            System.out.println("name:" + name + " ,num:" + num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class AThread extends Thread{
    private HasSelfPrivateNum hasSelfPrivateNum;
    public AThread(HasSelfPrivateNum hasSelfPrivateNum){
        this.hasSelfPrivateNum = hasSelfPrivateNum;
    }

    @Override
    public void run() {
        hasSelfPrivateNum.add("a");
    }
}

class BThread extends Thread{
    private HasSelfPrivateNum hasSelfPrivateNum;
    public BThread(HasSelfPrivateNum hasSelfPrivateNum){
        this.hasSelfPrivateNum = hasSelfPrivateNum;
    }

    @Override
    public void run() {
        hasSelfPrivateNum.add("b");
    }
}

class HasSelfMain{
    public static void main(String[] args) {
        HasSelfPrivateNum hasSelfPrivateNum = new HasSelfPrivateNum();
        Thread thread = new AThread(hasSelfPrivateNum);
        thread.start();
        Thread thread1 = new BThread(hasSelfPrivateNum);
        thread1.start();
    }
}
