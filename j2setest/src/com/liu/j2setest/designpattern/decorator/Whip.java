package com.liu.j2setest.designpattern.decorator;

/**
 * Created by liuzhilei on 2016/12/13.
 * 奶油调料
 */
public class Whip extends CondimentDecorator {
    Beverage beverage;

    @Override
    public int getSize() {
        return beverage.getSize();
    }

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ",Whip奶油";
    }

    @Override
    public double cost() {
        return 0.10 + beverage.cost();
    }
}
