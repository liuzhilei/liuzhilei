package com.liu.j2setest.designpattern.factory;

/**
 * Created by liuzhilei on 2016/12/15.
 */
public class NYStyleCheesePizza extends Pizza {

    public NYStyleCheesePizza(){
        name = "纽约 cheese 披萨";
        dough = "纽约 cheese 面团";
        sauce = "纽约 cheese 酱油";
    }

    @Override
    void cut() {
        System.out.println("cheese 切片方式");
    }
}
