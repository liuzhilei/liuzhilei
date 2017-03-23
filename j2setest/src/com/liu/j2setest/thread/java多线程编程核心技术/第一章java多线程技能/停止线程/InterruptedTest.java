package com.liu.j2setest.thread.java多线程编程核心技术.第一章java多线程技能.停止线程;

/**
 * Created by liuzhilei on 2017/3/15.
 * 判断线程是否是停止状态
 * <p/>
 * 有两种方法：
 * 1.public static boolean interrupted()
 * 判断<b>当前线程</b>是否是中断状态，如果是，就会把状态标识设置为false
 * 2.public boolean isInterrupted()
 * 判断<b>线程</b>是否是中断状态，但不清除终端标识
 */
public class InterruptedTest extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 50000; i++) {
            System.out.println("i=" + i);
        }
    }

}

class InterruptedMain {
    public static void main(String[] args) {
        Thread test = new InterruptedTest();
        test.start();
        try {
            test.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //test.interrupt();
        test.currentThread().interrupt();//中断当前线程
        System.out.println("Thread.interrupted():是否停止1" + Thread.interrupted());//测试当前线程是否为中断状态，第一次为true，并且会把标识置为false
        System.out.println("Thread.interrupted():是否停止2" + Thread.interrupted());//第二次打印的就是false了

    }
}

class IsInterruptedMain {
    public static void main(String[] args) {
        Thread test = new InterruptedTest();
        test.start();
        try {
            test.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test.interrupt();
        System.out.println("test.interrupted():是否停止1" + test.isInterrupted());//测试线程是否为中断状态，第一次为true,不会改变之后的状态
        System.out.println("test.interrupted():是否停止2" + test.isInterrupted());//第二次也为true
    }
}
