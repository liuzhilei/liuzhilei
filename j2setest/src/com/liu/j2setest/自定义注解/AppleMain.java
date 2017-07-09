package com.liu.j2setest.自定义注解;

import java.lang.reflect.Field;

/**
 * Created by liuzhilei on 2017/7/9.
 */
public class AppleMain {

    public static void getFruitInfo(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(FruitName.class)) {
                FruitName fruitName = field.getAnnotation(FruitName.class);
                System.out.println("水果名称：" + fruitName.value());
            }
            if (field.isAnnotationPresent(FruitColor.class)) {
                FruitColor fruitColor = field.getAnnotation(FruitColor.class);
                System.out.println("水果颜色：" + fruitColor.fruitColor());
            }
        }
    }

    public static void main(String[] args) {
        getFruitInfo(Apple.class);
    }
}
