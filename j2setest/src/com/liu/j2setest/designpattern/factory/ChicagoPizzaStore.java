package com.liu.j2setest.designpattern.factory;

/**
 * Created by liuzhilei on 2016/12/15.
 */
public class ChicagoPizzaStore extends PizzaStore {
    @Override
    Pizza newPizza(String type) {
        if(type.equals("cheese")){
            return new ChicagoCheesePizza();
        }else{
            return null;
        }
    }
}
