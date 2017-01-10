package com.liu.j2setest.designpattern.decorator;

/**
 * Created by liuzhilei on 2016/12/13.
 * 摩卡调料
 */
public class Mocha extends CondimentDecorator {
    Beverage beverage;

    @Override
    public int getSize() {
        return beverage.getSize();
    }

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ",Mocha摩卡";
    }

    @Override
    public double cost() {
        return 0.20 + beverage.cost();
    }
}
