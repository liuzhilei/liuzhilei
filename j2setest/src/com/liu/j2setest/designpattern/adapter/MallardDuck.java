package com.liu.j2setest.designpattern.adapter;

/**
 * Created by liuzhilei on 2016/12/23.
 */
public class MallardDuck implements Duck {
    @Override
    public void quack() {
        System.out.println("绿头鸭-----------呱呱叫");
    }

    @Override
    public void fly() {
        System.out.println("绿头鸭-----------飞");
    }
}
