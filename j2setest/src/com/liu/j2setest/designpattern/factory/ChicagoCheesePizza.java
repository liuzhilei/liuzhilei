package com.liu.j2setest.designpattern.factory;

/**
 * Created by liuzhilei on 2016/12/15.
 */
public class ChicagoCheesePizza extends Pizza{

    public ChicagoCheesePizza(){
        name = "芝加哥 cheese 披萨";
        dough = "芝加哥 cheese 面团";
        sauce = "芝加哥 cheese 酱油";
    }

    @Override
    void bake() {
        System.out.println("芝加哥烘焙方式");
    }
}
