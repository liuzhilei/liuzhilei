package com.liu.j2setest.优化;

/**
 * Created by liuzhilei on 2017/7/31.
 * 涉及到乘除法运算，一定要位运算符代替乘除法
 * 下面两个例子性能差距很明显
 */
public class Demo1 {
    public static void main(String[] args) {
        int a = 10;
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            a *= 2;
            a /= 2;
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        start = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            a <<= 1;
            a >>= 1;
        }
        end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
