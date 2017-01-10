package com.liu.j2setest.designpattern.factory;

/**
 * Created by liuzhilei on 2016/12/15.
 */
public abstract class Pizza {
    /**
     * 名称
     */
    String name;
    /**
     * 面团
     */
    String dough;
    /**
     * 酱料
     */
    String sauce;

    void bake(){
        System.out.println("默认烘焙..........");
    }

    void cut(){
        System.out.println("默认切片..........");
    }

    void box(){
        System.out.println("默认打包..........");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDough() {
        return dough;
    }

    public void setDough(String dough) {
        this.dough = dough;
    }

    public String getSauce() {
        return sauce;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }
}
