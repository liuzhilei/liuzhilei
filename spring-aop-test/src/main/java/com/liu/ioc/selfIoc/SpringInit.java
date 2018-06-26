package com.liu.ioc.selfIoc;

import com.liu.ioc.WeatherService;

import java.util.List;

/**
 * Created by liuzhilei3 on 2018/6/26.
 * 模拟spring的初始化
 */
public class SpringInit {

    public Object init() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        WeatherService weatherService = new WeatherService();
        SpringReflect springReflect = new SpringReflect();
        springReflect.reflectSetObjectList(weatherService);

        return springReflect.getObjectList().get(0);
    }
}
