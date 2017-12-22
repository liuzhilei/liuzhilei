package com.liu.j2setest.mianshiTest.线程交替打印;

/**
 * Created by liuzhilei on 2017/12/6.
 * 利用wait notify
 */
public class Demo1 {
    private boolean flag = true;
    public int num;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public static void main(String[] args) {
        Demo1 demo1 = new Demo1();
        Thread thread = new Demo1Thread1(demo1);
        Thread thread1 = new Demo1Thread2(demo1);
        thread.start();
        thread1.start();
    }
}

class Demo1Thread1 extends Thread {
    private Demo1 demo1;

    public Demo1Thread1(Demo1 demo1) {
        this.demo1 = demo1;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                synchronized (demo1) {
                    //这里一定要用while，不要用if，为了防止虚假唤醒，当拿到锁以后，需要循环验证是否真正的拿到了锁
                    while (demo1.isFlag()) {
                        demo1.wait();
                    }
                    demo1.num++;
                    System.out.println(currentThread().getName() + "=====" + demo1.num);
                    demo1.setFlag(true);
                    demo1.notify();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Demo1Thread2 extends Thread {
    private Demo1 demo1;

    public Demo1Thread2(Demo1 demo1) {
        this.demo1 = demo1;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                synchronized (demo1) {
                    while (!demo1.isFlag()){
                        demo1.wait();
                    }
                    demo1.num++;
                    System.out.println(currentThread().getName() + "=====" + demo1.num);
                    demo1.setFlag(false);
                    demo1.notify();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

