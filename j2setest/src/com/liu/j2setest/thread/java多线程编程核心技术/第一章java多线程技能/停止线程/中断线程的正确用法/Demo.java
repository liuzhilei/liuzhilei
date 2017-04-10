package com.liu.j2setest.thread.java多线程编程核心技术.第一章java多线程技能.停止线程.中断线程的正确用法;

/**
 * Created by liuzhilei on 2017/4/10.
 * interrupt 不会真正中断线程，只是一标识
 * 正确的中断线程，停止阻塞状态的方法是使用共享的boolean变量，然后调用thread.interrupt()方法（变量要在调用interrupt之前）
 *
 */
public class Demo extends Thread {
    volatile boolean stop = false;
    public static void main( String args[] ) throws Exception {
        Demo thread = new Demo();
        System.out.println( "Starting thread..." );
        thread.start();
        Thread.sleep( 3000 );
        System.out.println( "Asking thread to stop..." );
        thread.stop = true;//如果线程阻塞，将不会检查此变量
        thread.interrupt();
        Thread.sleep( 3000);
        System.out.println( "Stopping application..." );
        //System.exit( 0 );
    }

    public void run() {
        while ( !stop ) {
            System.out.println( "Thread running..." );
            try {
                Thread.sleep( 1000 );
            } catch ( InterruptedException e ) {
                System.out.println( "Thread interrupted..." );
            }
        }
        System.out.println( "Thread exiting under request..." );
    }
}
