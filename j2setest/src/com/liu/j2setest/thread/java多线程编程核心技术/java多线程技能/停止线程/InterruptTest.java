package com.liu.j2setest.thread.java多线程编程核心技术.java多线程技能.停止线程;

import javax.sound.midi.Soundbank;

/**
 * Created by liuzhilei on 2017/3/15.
 *
 * interrupt()仅仅是在当前线程中打一个停止的标识，并不是真的停止线程
 */
public class InterruptTest extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            System.out.println("i=" + i);
        }
    }
}

class InterruptMain {
    public static void main(String[] args) {
        Thread t1 = new InterruptTest();
        t1.start();
        try {
            //t1.sleep(1);
            System.out.println(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        t1.interrupt();
    }
}
