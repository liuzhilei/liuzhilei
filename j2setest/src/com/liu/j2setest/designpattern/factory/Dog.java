package com.liu.j2setest.designpattern.factory;

import com.liu.j2setest.designpattern.factory.Animal;

/**
 * Created by liuzhilei3 on 2018/10/16.
 */
public class Dog implements Animal {
    @Override
    public void eatFood() {
        System.out.println("狗喜欢吃骨头");
    }
}
