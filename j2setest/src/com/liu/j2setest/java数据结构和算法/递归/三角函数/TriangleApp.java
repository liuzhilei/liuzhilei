package com.liu.j2setest.java数据结构和算法.递归.三角函数;

/**
 * Created by liuzhilei on 2017/5/27.
 * 三角函数
 * 总和等于 n + 之前值总和
 */
public class TriangleApp {

    public static void main(String[] args) {
        int triangle = triangle(4);
        System.out.println("最终数值是：" + triangle);
    }

    public static int triangle(int n) {
        System.out.println("输入的值是：" + n);
        if (n == 1) {
            System.out.println("已经到最小值");
            return 1;
        } else {
            int temp = n + triangle(n - 1);
            System.out.println("中间值是：" + temp);
            return temp;
        }
    }
}
