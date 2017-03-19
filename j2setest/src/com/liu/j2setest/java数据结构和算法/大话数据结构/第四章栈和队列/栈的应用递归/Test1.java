package com.liu.j2setest.java数据结构和算法.大话数据结构.第四章栈和队列.栈的应用递归;

/**
 * Created by liuzhilei on 2017/3/19.
 * <p/>
 * 场景：兔子两个月以后就有繁殖能力，然后每个月会生出一对小兔子，如果兔子都不死，打印出40个月所有兔子的情况
 * <p/>
 * 分析：第一个月：1，第二个月：1，第三个月：2，第四个月：3，第五个月：5，第六个月：8。
 * 规律：前面相邻两数之和等于后一项
 */
public class Test1 {

    /**
     * 第一种方法，直接循环迭代
     */
    public static void method1() {
        int arr[] = new int[40];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i < 40; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
            System.out.println(arr[i]);
        }
    }

    /**
     * 第二种方法：使用递归
     * 因为会创建函数的副本，所以浪费更多的时间和内存，两种方法，视情况使用
     */
    public static int method2(int i) {
        //满足下面的条件，递归就不再进行，否则递归将无限循环
        if (i < 2) {
            return 1;
        }
        return method2(i - 1) + method2(i - 2);
    }


    public static void main(String args[]) {
        //普通方法
        long method1Start = System.currentTimeMillis();
        method1();
        long method1end = System.currentTimeMillis();
        System.out.println("直接循环迭代耗时：" + (method1end - method1Start));

        //递归方法
        /*
        long method2Start = System.currentTimeMillis();
        for (int i = 0; i < 40; i++) {
            System.out.println(method2(i));
        }
        long method2end = System.currentTimeMillis();
        System.out.println("使用递归，耗时为：" + (method1end - method1Start));
        */
    }
}
