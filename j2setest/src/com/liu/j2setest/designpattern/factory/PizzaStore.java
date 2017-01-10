package com.liu.j2setest.designpattern.factory;

/**
 * Created by liuzhilei on 2016/12/15.
 * 制作披萨工厂抽象类
 */
public abstract class PizzaStore {

    public Pizza createPizza(String type) {
        Pizza pizza;
        pizza = newPizza(type);
        pizza.bake();
        pizza.cut();
        pizza.box();
        System.out.println(pizza.getName());
        System.out.println(pizza.dough);
        return pizza;
    }

    abstract Pizza newPizza(String type);
}
