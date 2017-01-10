package com.liu.j2setest.designpattern.adapter;

/**
 * Created by liuzhilei on 2016/12/23.
 * 鸭子适配器，为了让鸭子适配火鸡
 */
public class DuckAdapter implements Turkey {

    Duck duck;
    public DuckAdapter(Duck duck){
        this.duck = duck;
    }

    @Override
    public void gobble() {
        duck.quack();
    }

    @Override
    public void fly() {
        duck.fly();
    }
}
