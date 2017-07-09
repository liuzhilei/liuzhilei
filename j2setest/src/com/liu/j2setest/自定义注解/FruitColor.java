package com.liu.j2setest.自定义注解;

import java.lang.annotation.*;

/**
 * Created by liuzhilei on 2017/7/9.
 * 水果颜色注解
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitColor {

    /**
     * 颜色枚举
     */
    public enum Color {
        BLUE, RED, GREEN;
    }

    /**
     * 颜色属性
     * @return
     */
    Color fruitColor() default Color.BLUE;
}
