package com.liu.j2setest.javaPuzzles.表达式谜题;

/**
 * Created by liuzhilei on 2017/7/24.
 */
public class Demo3 {

    public static void main(String[] args) {
        //一天中的微秒数
        final long micro_second = 24 * 60 * 60 * 1000 * 1000;
        //一天中的毫秒数
        final long millis_second = 24 * 60 * 60 * 1000;

        /**
         * 此处并不能正确打印出1000，因为存在了类型溢出的问题，因为这个“/”计算完全是按照int来进行的，
         * 然后将结果再提升成long类型，但是micro_second变量int已经存放不下，所以在计算的时候已经溢出了
         */
        System.out.println(micro_second / millis_second);


        //一天中的微秒数
        final long micro_second_L = 24L * 60 * 60 * 1000 * 1000;
        //一天中的毫秒数
        final long millis_second_L = 24L * 60 * 60 * 1000;
        /**
         * 强制用long类型进行计算，所以能正确打印出值
         */
        System.out.println(micro_second_L / millis_second_L);
    }
}
