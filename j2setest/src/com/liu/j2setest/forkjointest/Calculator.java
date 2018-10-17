package com.liu.j2setest.forkjointest;

import java.util.concurrent.RecursiveTask;

/**
 * Created by liuzhilei on 2016/12/7.
 * 并发，把大任务切分成小任务,切分线程，进行同时计算，具有线程窃取的特性,cpu利用率最大化
 */
public class Calculator extends RecursiveTask<Integer> {

    /*private int start;
    private int end;

    public Calculator(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int cal() {
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += i;
        }
        return sum;
    }


    @Override
    protected Integer compute() {

        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += i;
        }

        Calculator left = new Calculator(1, 1000);
        Calculator middle = new Calculator(1001, 2000);
        Calculator right = new Calculator(2001, 3000);

        left.fork();
        middle.fork();
        right.fork();
        sum = left.join() + middle.join() + right.join();

        System.out.println(sum);
        return sum;
    }*/

    private static final int THRESHOLD = 100;
    private int start;
    private int end;

    public Calculator(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        if((end - start) <= THRESHOLD){
            for(int i = start; i<= end;i++){
                sum += i;
            }
        }else{
            int middle = (start + end) /2;
            Calculator left = new Calculator(start, middle);
            Calculator right = new Calculator(middle + 1, end);
            left.fork();
            right.fork();

            sum = left.join() + right.join();
        }
        return sum;
    }
}
