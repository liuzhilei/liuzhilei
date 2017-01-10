package com.liu.j2setest.designpattern.decorator;

/**
 * Created by liuzhilei on 2016/12/13.
 * 饮料抽象类
 */
public abstract class Beverage {
    String description = "unknown beverage";
    int TALL = 1;
    int GRANDE = 2;
    int VENTI = 3;

    public String getDescription(){
        return description;
    }

    public abstract int getSize();

    //需要子类具体实现
    public abstract double cost();

}
