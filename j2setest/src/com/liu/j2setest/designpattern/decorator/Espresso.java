package com.liu.j2setest.designpattern.decorator;

/**
 * Created by liuzhilei on 2016/12/13.
 * 浓咖啡饮料
 */
public class Espresso extends Beverage {
    @Override
    public int getSize() {
        return VENTI;
    }

    public Espresso() {
        description = "Espresso 浓咖啡饮料";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}
