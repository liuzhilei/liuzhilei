package com.liu.j2setest.thread.java多线程编程核心技术.第二章对象及变量的并发访问.synchronized同步方法.synchronized锁重入;

/**
 * Created by liuzhilei on 2017/3/17.
 * <p/>
 * 该方法证明了继承关系中，子类可以通过“可重入锁”调用父类的同步方法
 */
public class SuperSuoChongRu {
    protected int i = 10;

    synchronized public void operationSupClass() {
        try {
            i--;
            System.out.println("父类方法 输出i=" + i);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class subSuoChongRu extends SuperSuoChongRu {

    synchronized public void subMethod() {
        while (i > 0) {
            i--;
            System.out.println("子类方法 输出i=" + i);
            operationSupClass();
        }
    }
}

class SuperSuoChongRuThread extends Thread {
    @Override
    public void run() {
        new subSuoChongRu().subMethod();
    }
}

class SuperSuoChongRuMain {
    public static void main(String[] args) {
        Thread thread = new SuperSuoChongRuThread();
        thread.start();
    }
}