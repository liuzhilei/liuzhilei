package com.liu.j2setest.forkjointest;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

/**
 * Created by liuzhilei on 2016/12/8.
 */
public class MainPool{// implements Runnable

    //@Override
    public void run() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future<Integer> future = forkJoinPool.submit(new Calculator(1, 3000));
        System.out.println(future);

    }

    public static void main(String[] args) {
        try{
            ForkJoinPool forkJoinPool = new ForkJoinPool();
            Future<Integer> future = forkJoinPool.submit(new Calculator(1, 3000));
            System.out.println(future.get());
        }catch (Exception e){

        }

        int sum = 0;
        for(int i=1;i<=3000;i++){
            sum+=i;
        }
        System.out.println(sum+"======");

    }
}
