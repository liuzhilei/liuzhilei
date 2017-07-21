package com.liu.j2setest.designpattern.SingleTon;

/**
 * Created by liuzhilei on 2017/7/21.
 */
public class Main {

    public static void main(String[] args) {
        SingleTon5 singleTon5 = SingleTon5.getInstance();
        SingleTon5 singleTon51 = SingleTon5.getInstance();

        System.out.println(singleTon5 == singleTon51);
    }
}
