package com.liu.j2setest.thread.java多线程编程核心技术.第三章线程间通信.等待通知机制;

/**
 * Created by liuzhilei on 2017/3/22.
 * 利用wait/notify 实现生产消费模式
 */
public class Test4 {
    public static String value = "";

}

class Product {
    private String lock;

    public Product(String lock) {
        this.lock = lock;
    }

    public void setValue() {
        try {
            synchronized (lock) {
                if (!Test4.value.equals("")) {
                    lock.wait();
                }
                String string = Thread.currentThread().getName() + "--" + System.currentTimeMillis() + "--" + System.nanoTime();
                System.out.println("setValue:Test4.value的值是：" + Test4.value);
                Test4.value = string;
                lock.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class Consume {
    private String lock;

    public Consume(String lock) {
        this.lock = lock;
    }

    public void getValue() {
        try {
            synchronized (lock) {
                if (Test4.value.equals("")) {
                    lock.wait();
                }
                System.out.println("getValue:Test4.value的值是：" + Test4.value);
                Test4.value = "";
                lock.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Test4Thread1 extends Thread {
    private String lock;

    public Test4Thread1(String lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        Consume consume = new Consume(lock);
        while (true) {
            consume.getValue();
        }

    }
}

class Test4Thread2 extends Thread {
    private String lock;

    public Test4Thread2(String lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        Product product = new Product(lock);
        while (true) {
            product.setValue();
        }

    }
}

class Test4Main {
    public static void main(String[] args) {
        String string = "";
        Thread thread = new Test4Thread1(string);
        thread.start();
        Thread thread1 = new Test4Thread2(string);
        thread1.start();
    }
}
