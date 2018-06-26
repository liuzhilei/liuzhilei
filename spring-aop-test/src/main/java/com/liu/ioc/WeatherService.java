package com.liu.ioc;

import com.liu.ioc.selfIoc.MyAutoWired;
import com.liu.ioc.selfIoc.MyService;

/**
 * Created by liuzhilei3 on 2018/6/26.
 * 获取天气数据
 */
@MyService
public class WeatherService {

    @MyAutoWired
    private WeatherDao weatherDao;

    public void weather_rain(){
        weatherDao.getWeather();
    }

}
