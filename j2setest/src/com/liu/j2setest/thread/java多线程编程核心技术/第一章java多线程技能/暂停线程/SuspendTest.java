package com.liu.j2setest.thread.java多线程编程核心技术.第一章java多线程技能.暂停线程;

/**
 * Created by liuzhilei on 2017/3/15.
 * suspend()缺点：独占
 */
public class SuspendTest {
    synchronized public void printString() {
        System.out.println("begin");
        if (Thread.currentThread().getName().equals("a")) {
            System.out.println("a 线程被永远的suspend了");
            Thread.currentThread().suspend();
        }
        System.out.println("end");
    }
}

class SynchronizedMain {
    public static void main(String[] args) {
        final SuspendTest synchronizedObject = new SuspendTest();
        Thread thread = new Thread() {
            @Override
            public void run() {
                synchronizedObject.printString();
            }
        };
        try {
            thread.setName("a");
            thread.start();
            thread.sleep(1000);
            Thread thread1 = new Thread() {
                @Override
                public void run() {
                    System.out.println("线程 thread1 启动了，但是进入不了printString()方法，只打印了一个begin。因为printString被a线程永远的suspend暂停了");
                    synchronizedObject.printString();
                }
            };
            thread1.start();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
