package com.liu.j2setest.java数据结构和算法.递归.三角函数;

/**
 * Created by liuzhilei on 2017/12/13.
 */
public class demo {
    public static void main(String[] args) {
        int sum = sum(5);
        System.out.println(sum);
    }

    private static int sum(int num) {
        if (num == 1) {
            System.out.println("已经到最小值1");
            return 1;
        }
        num = num + sum(num - 1);
        return num;
    }
}
