package com.liu.j2setest.designpattern.factory.abstractfactory;

/**
 * Created by liuzhilei3 on 2018/10/16.
 */
public class WhiteDog implements IDog {
    @Override
    public void eatFood() {
        System.out.println("白狗吃骨头");
    }
}
