package com.liu.j2setest.designpattern.factory.normalfactory;

import com.liu.j2setest.designpattern.factory.Animal;

/**
 * Created by liuzhilei3 on 2018/10/16.
 * 普通工厂方法，符合开闭原则，添加了新的动物不用再改动原有方法，只需要新增类就可以
 */
public interface NormalFactory {
    Animal getAnimal();
}
