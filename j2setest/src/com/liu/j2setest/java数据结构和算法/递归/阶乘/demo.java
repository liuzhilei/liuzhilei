package com.liu.j2setest.java数据结构和算法.递归.阶乘;

/**
 * Created by liuzhilei on 2017/12/13.
 */
public class demo {
    public static void main(String[] args) {
        int i = jieCheng(4);
        System.out.println(i);
    }

    private static int jieCheng(int num) {
        if (num == 1) {
            return 1;
        }
        num = num * jieCheng(num - 1);
        return num;
    }

}
