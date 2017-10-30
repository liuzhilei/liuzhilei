package com.liu.j2setest.泛型;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzhilei on 2017/10/30.
 * 该类包含：
 * 1.
 * <? extends T>：是指 “ 上界通配符 （Upper Bounds Wildcards） ”
 * <? super T>：是指 “ 下界通配符 （Lower Bounds Wildcards） ”
 *
 * 2.泛型擦除
 * 3.如何获取泛型信息
 */
public class Demo {

    static class Species {
    }

    static class Human extends Species {
    }

    static class Man extends Human {
    }

    static class Woman extends Human {
    }

    public static void main(String[] args) {

        List<Human> list = new ArrayList<Human>();
        list.add(new Man());
        list.add(new Woman());
        Man o11 = (Man) list.get(0); // 这不能保证转型成功，也不是泛型的初衷
        Human o12 = list.get(0);


        //上界通配符
        /**
         * <? extends Human> ？所指的元素，并不是指的是human以及human的所有子类。
         * 仅仅指的是human的一个不确定的子类，没有具体指明哪个类
         * 因此不能向里面插入任何类型对象，唯一可以保证的是可以从中读取到human或者human的子类
         */
        List<? extends Human> list2 = new ArrayList<Human>();
//        list2.add(new Object());// 编译错误
//        list2.add(new Species());// 编译错误
//        list2.add(new Human());// 编译错误
//        list2.add(new Man());// 编译错误
//        list2.add(new Woman());// 编译错误
        //Man o21 = (Man) list2.get(0);// 这不能保证转型成功，也不是泛型的初衷
        //Human o22 = list2.get(0); //这是正常的

        //下界通配符
        /**
         * <? super Human> ?所指的元素，并不是human以及human所有的父类。
         * 仅仅指的是一个不确定的父类，没有指明具体哪一个父类
         * 因此不能向里面插入任何父类，但是可以插入子类。但是可以读取到object或者object子类对象
         */
        List<? super Human> list3 = new ArrayList<Human>();
//        list3.add(new Object());// 编译错误
//        list3.add(new Species());// 编译错误
        list3.add(new Human());
        list3.add(new Man());
        list3.add(new Woman());
        //Man o31 = (Man) list3.get(0); // 这不能保证转型成功，也不是泛型的初衷
        Species o32 = (Species) list3.get(0);
//        Human hu = list3.get(0);//编译错误，无法自动转型
        Object o33 = list3.get(0); //正确


        /**
         * 总结：
         * 如果频繁要求读取数据，不特别要求写数据，可以使用<? extends T>
         * 如果频繁要求写入数据，不特别要求读数据，可以使用<? super T>
         * 如果都要求，那么就要使用<T>
         */

        //==============================================================================================


        //泛型擦除  编译时候会进行擦除
        Class c1 = new ArrayList<Integer>().getClass();
        Class c2 = new ArrayList<Long>().getClass();
        System.out.println(c1 == c2);


        //===============================================================================================

    }


}
