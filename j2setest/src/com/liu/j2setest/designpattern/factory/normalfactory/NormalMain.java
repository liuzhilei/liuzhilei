package com.liu.j2setest.designpattern.factory.normalfactory;

/**
 * Created by liuzhilei3 on 2018/10/16.
 */
public class NormalMain {
    public static void main(String[] args) {
        NormalFactory factory = new FactoryCat();
        factory.getAnimal().eatFood();
    }
}
