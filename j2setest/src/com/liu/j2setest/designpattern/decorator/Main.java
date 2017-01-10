package com.liu.j2setest.designpattern.decorator;

/**
 * Created by liuzhilei on 2016/12/13.
 */
public class Main {
    public static void main(String[] args) {
        //订一杯浓咖啡，不需要任何调料
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription() + "，￥" + beverage.cost());

        //订一杯混合饮料，两份摩卡，一份奶油
        Beverage beverage1 = new HouseBlend();
        beverage1 = new Mocha(beverage1);
        beverage1 = new Mocha(beverage1);
        beverage1 = new Whip(beverage1);
        System.out.println(beverage1.getDescription() + "，￥" + beverage1.cost());

        //订一杯浓咖啡，一份摩卡，一份奶油，一份豆浆
        Beverage beverage2 = new Espresso();
        beverage2 = new Mocha(beverage2);
        beverage2 = new Whip(beverage2);
        beverage2 = new Soy(beverage2);
        System.out.println(beverage2.getDescription() + "，￥" + beverage2.cost());

    }
}
