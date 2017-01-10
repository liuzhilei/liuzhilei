package com.liu.j2setest.designpattern.decorator;

/**
 * Created by liuzhilei on 2016/12/13.
 * 混合饮料
 */
public class HouseBlend extends Beverage {
    @Override
    public int getSize() {
        return GRANDE;
    }

    public HouseBlend(){
        description = "HouseBlend 混合饮料";
    }

    @Override
    public double cost() {
        return 5;
    }
}
