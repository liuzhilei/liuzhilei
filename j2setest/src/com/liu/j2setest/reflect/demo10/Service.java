package com.liu.j2setest.reflect.demo10;

import java.lang.annotation.*;

/**
 * Created by liuzhilei on 2017/7/7.
 */
@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {
}
