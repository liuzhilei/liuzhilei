package com.liu.j2setest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liuzhilei on 2017/1/10.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(1111111);

        List<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i <= 100; i++) {
            list.add(i);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        //executorService.execute();

    }

}

class MainThread implements Runnable {
    private List list;
    private int per;

    public MainThread(List list, int per) {
        this.list = list;
        this.per = per;
    }

    @Override
    public void run() {
        for (int i = 0; i < per; i++) {

        }
        System.out.println("ThreadName = " + Thread.currentThread().getName() + " i = " );
    }
}
