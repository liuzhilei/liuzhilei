package com.liu.j2setest.designpattern.adapter;

/**
 * Created by liuzhilei on 2016/12/23.
 * 适配器分为两种：类适配器和对象适配器
 * 类适配器是adapter直接继承了需要适配的类，是静态的，使得adapter不能和适配的类的子类一起工作
 * 但是对象适配器是通过委派的方式，可以动态组合，所以java只支持这一种
 */
public class Main {
    public static void main(String[] args) {
        Duck mallardDuck = new MallardDuck();
        mallardDuck.quack();
        mallardDuck.fly();

        Turkey wildTurkey = new WildTurkey();
        wildTurkey.gobble();
        wildTurkey.fly();

        Duck turkeyAdapter = new TurkeyAdapter(wildTurkey);
        turkeyAdapter.quack();
        turkeyAdapter.fly();

        Duck duck = new MallardDuck();
        Turkey duckAdapter = new DuckAdapter(duck);
        duckAdapter.gobble();
        duckAdapter.fly();

    }
}
