package com.liu.j2setest.thread.maintest.forkjoin;

import java.util.concurrent.*;

/**
 * Created by liuzhilei on 2017/7/25.
 * <p/>
 * fork/join 对于无计算结果的，继承RecursiveAction；对于需要返回结果的，继承RecursiveTask
 * forkJoinTask需要ForkJoinPool来执行
 * 例子：利用 fork/join实现1+2+3+4的相加运算，要求最小任务为两个数相加
 */
public class Demo extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 2;
    private int start;
    private int end;

    public Demo(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        boolean flag = (end - start) < THRESHOLD;
        //如果任务足够小，就计算你任务
        if (flag) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        }
        //不符合计算条件
        else {
            //划分成两个子任务
            int middle = (start + end) / 2;
            Demo leftTask = new Demo(start, middle);
            Demo rightTask = new Demo(middle + 1, end);
            //异步执行子任务
            leftTask.fork();
            rightTask.fork();
            //join会阻塞，等待子任务执行完成，然后取到结果
            Integer left = leftTask.join();
            Integer right = rightTask.join();
            //合并计算结果
            sum = left + right;
        }
        return sum;
    }

    public static void main(String[] args) {
        Demo task = new Demo(1, 4);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> submit = forkJoinPool.submit(task);

        try {
            //取得最终计算结果
            Integer integer = submit.get();
            System.out.println(integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
