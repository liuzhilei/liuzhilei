package com.liu.j2setest.javaPuzzles.表达式谜题;

/**
 * Created by liuzhilei on 2017/7/24.
 * 奇数性：判断一个参数是否为奇数的最合理表达式
 */
public class Demo1 {

    public static void main(String[] args) {
        System.out.println(isOdd(-1));
    }

    /**
     * i % 2 != 0也可以
     * 不要使用i % 2 == 1，因为参数有可能是负数
     * 判断一个数是否为奇数
     */
    public static boolean isOdd(int i) {
        return (i & 1) != 0;
    }

    /**
     * 判断一个数是否为正数
     * @param i
     * @return
     */
    private static boolean isZhengShu(int i) {
        return (i >> 31) == 0;
    }

}
