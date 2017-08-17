package com.liu.j2setest.thread.java多线程编程核心技术.第三章线程间通信.ThreadLocal;

/**
 * Created by liuzhilei on 2017/3/22.
 * 对于threadlocal，每个线程会创建一个threadlocalmap，虽然map由默认16长度的entry数组构成，但是重复set，会覆盖原set的值，因为通过hash定位的数组索引是一样的
 */
public class ThreadLocalTest {
    public static void main(String[] args) {
        Thread thread = new ThreadTest1Thread1();
        thread.start();
        Thread thread1 = new ThreadTest1Thread2();
        thread1.start();

        Tools tools = new Tools();
        System.out.println("Tools的默认值是：" + tools.get());

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class Tools extends ThreadLocal {
    public static ThreadLocal t1 = new ThreadLocal();

    /**
     * 延迟加载方法，在线程第一次调用get或set时调用，并且仅执行一次，缺省值返回null
     * @return
     */
    @Override
    protected Object initialValue() {

        return "我是Tools类的默认值，该方法是延迟加载的方法，在第一次get或者set的时候才调用";
    }
}

class ThreadTest1Thread1 extends Thread {
    @Override
    public void run() {
        Tools.t1.set("ThreadA");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Tools.t1.set("ThreadA1");
    }
}

class ThreadTest1Thread2 extends Thread {
    @Override
    public void run() {
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Tools.t1.set("ThreadB");
    }
}
