package com.liu.j2setest.thread.java多线程编程核心技术.第三章线程间通信.ThreadLocal;

/**
 * Created by liuzhilei on 2017/3/22.
 */
public class Test {
    public static void main(String[] args) {
        Thread thread = new ThreadTest1Thread1();
        thread.start();
        /*try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        Thread thread1 = new ThreadTest1Thread2();
        thread1.start();

        Tools tools = new Tools();
        System.out.println("Tools的默认值是：" + tools.get());

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
        try {
            for (int i = 0; i < 10; i++) {
                Tools.t1.set("ThreadA :" + (i + 1));
                Thread.sleep(200);
                System.out.println("ThreadA get value:" + Tools.t1.get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadTest1Thread2 extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                Tools.t1.set("ThreadB :" + (i + 1));
                Thread.sleep(200);
                System.out.println("ThreadB get value:" + Tools.t1.get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
