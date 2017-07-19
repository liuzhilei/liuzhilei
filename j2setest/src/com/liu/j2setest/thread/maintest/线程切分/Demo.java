package com.liu.j2setest.thread.maintest.线程切分;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by liuzhilei on 2017/3/23.
 * 线程切分，是线程池访问arrayList，根据cpu数将list划分成对应的数组，然后查找特定的数值所在list中的位置
 */
public class Demo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long psearch = MyTask.psearch(909);
        System.out.println(psearch);
    }

    static ArrayList<Long> list = new ArrayList<Long>() {{
        add(1l);
        add(3l);
        add(56l);
        add(87l);
        add(5765l);
        add(78l);
        add(1l);
        add(1l);
        add(56l);
        add(567l);
        add(567l);
        add(800l);
        add(1l);
        add(3l);
        add(56l);
        add(87l);
        add(5765l);
        add(78l);
        add(1l);
        add(1l);
        add(56l);
        add(567l);
        add(567l);
        add(800l);
        add(1l);
        add(3l);
        add(56l);
        add(87l);
        add(5765l);
        add(78l);
        add(1l);
        add(1l);
        add(56l);
        add(567l);
        add(567l);
        add(9l);
        add(1l);
        add(3l);
        add(56l);
        add(87l);
        add(5765l);
        add(78l);
        add(1l);
        add(1l);
        add(56l);
        add(567l);
        add(567l);
        add(909l);
        add(1l);
        add(3l);
        add(56l);
        add(87l);
        add(5765l);
        add(78l);
        add(1l);
        add(1l);
        add(56l);
        add(567l);
        add(567l);
        add(9l);
        add(1l);
        add(3l);
        add(56l);
        add(87l);
        add(5765l);
        add(78l);
        add(1l);
        add(1l);
        add(56l);
        add(567l);
        add(567l);
        add(9l);
        add(1l);
        add(3l);
        add(56l);
        add(87l);
        add(5765l);
        add(78l);
        add(1l);
        add(1l);
        add(56l);
        add(567l);
        add(567l);
        add(9l);
        add(1l);
        add(3l);
        add(56l);
        add(87l);
        add(5765l);
        add(78l);
        add(1l);
        add(1l);
        add(56l);
        add(567l);
        add(567l);
        add(909l);
    }};

    static int cpuNum = Runtime.getRuntime().availableProcessors();//取到cup数
    static ExecutorService executorService = Executors.newFixedThreadPool(cpuNum);
    static AtomicLong aLong = new AtomicLong(-1);

    public static long search(int startPos, int endPos, int value) {

        int i = 0;
        for (i = startPos; i < endPos; i++) {
            System.out.println("当前线程：" + Thread.currentThread().getName() + " list开始: " + startPos + " list结束: " + endPos
                    + " list中的值: " +
                    list.get(i));
            // 如果有其他线程找到，就直接返回
            if (aLong.get() >= 0) {
                return aLong.get();
            }
            //如果没有，那么就开始遍历查找
            if (list.get(i) == value) {
                if (!aLong.compareAndSet(-1, i)) {
                    return aLong.get();
                }
                return i;
            }
        }
        return -1;
    }


    public static class MyTask implements Callable<Long> {

        int startPos, endPos, searchValue;

        public MyTask(int startPos, int endPos, int searchValue) {
            this.startPos = startPos;
            this.endPos = endPos;
            this.searchValue = searchValue;
        }

        @Override
        public Long call() throws Exception {
            return search(startPos, endPos, searchValue);
        }

        public static Long psearch(int value) throws ExecutionException, InterruptedException {
            int subArrSize = list.size() / cpuNum + 1;//根据线程数分割数组
            List<Future<Long>> futures = new ArrayList();
            for (int i = 0; i < list.size(); i += subArrSize) {
                int end = i + subArrSize;
                if (end >= list.size()) {
                    end = list.size();
                }
                futures.add(executorService.submit(new MyTask(i, end, value)));
            }
            for (Future<Long> fu : futures) {
                if (fu.get() >= 0) {
                    return fu.get();
                }
            }
            return -1l;
        }
    }
}
