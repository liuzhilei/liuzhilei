package com.liu.ioc;

import com.liu.ioc.selfIoc.SpringInit;

/**
 * Created by liuzhilei3 on 2018/6/26.
 */
public class WeatherMain {
    public static void main(String[] args) throws Exception{
        SpringInit springInit = new SpringInit();
        WeatherService weatherService = (WeatherService)springInit.init();
        weatherService.weather_rain();
    }
}
