package com.liu.j2setest.designpattern.decorator;

/**
 * Created by liuzhilei on 2016/12/13.
 * d豆浆调料
 */
public class Soy extends CondimentDecorator {
    Beverage beverage;

    public Soy(Beverage beverage) {
        this.beverage = beverage;

    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ",Soy豆浆";
    }

    @Override
    public int getSize() {
        return beverage.getSize();
    }

    @Override
    public double cost() {
        double cost = beverage.cost();
        if (getSize() == beverage.TALL) {
            cost += 0.1;
        } else if (getSize() == beverage.GRANDE) {
            cost += 0.1;
        } else if (getSize() == beverage.VENTI) {
            cost += 0.3;
        }
        return cost;
    }
}
