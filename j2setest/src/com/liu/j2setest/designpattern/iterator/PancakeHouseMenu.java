package com.liu.j2setest.designpattern.iterator;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by liuzhilei on 2017/1/4.
 * 符合菜单规定的煎饼屋
 */
public class PancakeHouseMenu implements Menu{
    ArrayList menuItems;

    public PancakeHouseMenu(){
        menuItems = new ArrayList();

        addItems("煎饼1","第一种煎饼",true,10);
        addItems("煎饼2","第二种煎饼",false,20);
        addItems("煎饼3","第三种煎饼",true,30);
        addItems("煎饼4","第四种煎饼",false,40);
    }

    public void addItems(String name,String description,boolean vegetarian,double price){
        MenuItem menuItem = new MenuItem(name,description,vegetarian,price);
        menuItems.add(menuItem);
    }

    public Iterator createIterator(){
        return menuItems.iterator();
    }

}
