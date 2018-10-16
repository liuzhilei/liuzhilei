package com.liu.j2setest.designpattern.factory;

/**
 * Created by liuzhilei3 on 2018/10/16.
 */
public class CatFactory implements AnimalFactory {
    @Override
    public Animal getAnimal(int type) {
        return new Cat();
    }
}
