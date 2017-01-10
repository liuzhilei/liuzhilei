package com.liu.j2setest.designpattern.adapter;

/**
 * Created by liuzhilei on 2016/12/23.
 * 野火鸡
 */
public class WildTurkey implements Turkey {
    @Override
    public void gobble() {
        System.out.println("野火鸡 gobble叫");
    }

    @Override
    public void fly() {
        System.out.println("野火鸡 飞");
    }
}
