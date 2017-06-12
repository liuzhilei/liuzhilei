package com.liu.j2setest.designpattern.享元模式;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuzhilei on 2017/6/12.
 * 享元工厂：创建并管理享元对象，享元池一般设计为键值对
 */
public class ChessFlyWeightFactory {

    //享元池
    private static Map<String, ChessFlyWeight> map = new HashMap<String, ChessFlyWeight>();

    public static ChessFlyWeight getChess(String color) {
        if (map.get(color) != null) {
            return map.get(color);
        } else {
            ChessFlyWeight weight = new ConcreteChess(color);
            map.put(color, weight);
            return weight;
        }
    }

}
