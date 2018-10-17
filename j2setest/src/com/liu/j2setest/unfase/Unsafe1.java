package com.liu.j2setest.unfase;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by liuzhilei3 on 2018/8/20.
 * 通过unsafe实例化一个私有构造方法的类
 */
public class Unsafe1 {

    public static void main(String[] args) throws Exception{
        //通过反射实例化unsafe
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);

        //实例化Player
        Player player = (Player) unsafe.allocateInstance(Player.class);
        player.setName("lzl");
        System.out.println(player.getName());

    }

}


