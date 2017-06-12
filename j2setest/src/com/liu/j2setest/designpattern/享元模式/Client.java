package com.liu.j2setest.designpattern.享元模式;


/**
 * Created by liuzhilei on 2017/6/12.
 */
public class Client {


    public static void main(String[] args) {
        ChessFlyWeight chessFlyWeight1 = ChessFlyWeightFactory.getChess("黑色");
        ChessFlyWeight chessFlyWeight2 = ChessFlyWeightFactory.getChess("黑色");

        System.out.println(chessFlyWeight1);
        System.out.println(chessFlyWeight2);

        System.out.println("-----增加外部状态的处理------");
        chessFlyWeight1.display(new Coordinate(10, 10));
        chessFlyWeight2.display(new Coordinate(20, 20));

    }
}
