package com.liu.j2setest.javaPuzzles.表达式谜题;

import java.math.BigDecimal;

/**
 * Created by liuzhilei on 2017/7/24.
 * 找零问题：对于double，float一定要注意精度问题
 */
public class Demo2 {
    public static void main(String[] args) {

        /**
         * 这里不能正确输出0.9，因为输出的值并不知道到底保留小数点后几位，
         */
        System.out.println(2.00 - 1.10);

        /**
         * 这也不能精确输出，因为对于new BigDecimal(1.10)返回的不是1.10，一定要用string类型
         */
        System.out.println((new BigDecimal(2.00)).subtract(new BigDecimal(1.10)));

        /**
         * 使用String类型为正确输出形式
         */
        System.out.println((new BigDecimal("2")).subtract(new BigDecimal("1.10")));
    }
}
