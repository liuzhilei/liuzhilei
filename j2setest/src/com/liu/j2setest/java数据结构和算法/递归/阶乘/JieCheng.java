package com.liu.j2setest.java数据结构和算法.递归.阶乘;

/**
 * Created by liuzhilei on 2017/5/27.
 * 阶乘
 */
public class JieCheng {

    public static void main(String[] args) {
        System.out.println(jieCheng(4));
    }

    public static int jieCheng(int n) {
        if (n == 0) {
            return 1;
        } else {
            return (n * jieCheng(n - 1));
        }
    }
}
