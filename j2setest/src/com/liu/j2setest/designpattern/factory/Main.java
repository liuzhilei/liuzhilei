package com.liu.j2setest.designpattern.factory;

/**
 * Created by liuzhilei on 2016/12/15.
 */
public class Main {

    public static void main(String[] args) {
        PizzaStore nyStore = new NYStylePizzaStore();
        PizzaStore chStore = new ChicagoPizzaStore();

        Pizza pizza = nyStore.createPizza("cheese");
        //System.out.println(pizza.getName());

        System.out.println("-------------------------------------------");

        pizza = chStore.createPizza("cheese");
        //System.out.println(pizza.getName());

    }
}
