package com.liu.j2setest.自定义注解;

/**
 * Created by liuzhilei on 2017/7/9.
 */
public class Apple {

    @FruitName("Apple")
    private String appleName;

    @FruitColor(fruitColor = FruitColor.Color.RED)
    private String appleColor;

    public String getAppleName() {
        return appleName;
    }

    public void setAppleName(String appleName) {
        this.appleName = appleName;
    }

    public String getAppleColor() {
        return appleColor;
    }

    public void setAppleColor(String appleColor) {
        this.appleColor = appleColor;
    }

    public static void main(String[] args) {
        Apple apple = new Apple();

        System.out.println(apple.getAppleName());
    }
}
