package com.liu.j2setest.designpattern.adapter;

/**
 * Created by liuzhilei on 2016/12/23.
 * 继承鸭子接口的火鸡适配器,为了让火鸡适配鸭子
 */
public class TurkeyAdapter implements Duck {
    Turkey turkey;

    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        turkey.gobble();
    }

    @Override
    public void fly() {
        for (int i = 0; i < 5; i++) {
            turkey.fly();
        }
    }
}
