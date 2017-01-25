package com.liu.j2setest.effectivejava.createdestoryobject.two;

import java.lang.reflect.Constructor;

/**
 * Created by liuzhilei on 2017/1/25.
 * 利用反射攻击单例模式
 */
public class Main {

    public static void main(String[] args) {
        Single singlea = Single.getSingle();
        Single singleb = Single.getSingle();
        System.out.println(singlea == singleb);

        try{
            Single single = Single.getSingle();
            Class clazz = Single.class;
            Constructor constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            Single single1 = (Single)constructor.newInstance();

            System.out.println(single == single1);

        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
