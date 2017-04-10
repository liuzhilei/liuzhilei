package com.liu.j2setest.thread.java多线程编程核心技术.第一章java多线程技能.停止线程;

/**
 * Created by liuzhilei on 2017/3/15.
 * 先中断，后sleep
 */
public class InterruptingSleep extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            System.out.println("i" + i);
        }

        try {
            System.out.println("run begin...");
            Thread.sleep(200000);
            System.out.println("run end");
        } catch (InterruptedException e) {
            System.out.println("先中断，后睡眠，进入catch!");
            e.printStackTrace();
        }

    }
}

class InterruptingMain {
    public static void main(String[] args) {
        Thread thread = new InterruptingSleep();
        thread.start();
        thread.interrupt();
    }
}
