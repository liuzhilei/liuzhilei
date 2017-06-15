package com.liu.j2setest.javaVM.java的关闭钩子;

/**
 * Created by liuzhilei on 2017/6/15.
 * Runtime.getRuntime().addShutdownHook(new Thread())
 * 这个方法的意思是在jvm中添加一个关闭的钩子，当jvm关闭之前，会执行系统中通过addShutdownHook设置的所有钩子，然后再关闭jvm
 * 虚拟机，所以在钩子函数里面可以设置内存清理，对象销毁等操作。
 */
public class Demo {

    public static void start() {
        System.out.println("jvm启动");
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("关闭后的操作");
            }
        });

    }

    public static void main(String[] args) {
        start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
