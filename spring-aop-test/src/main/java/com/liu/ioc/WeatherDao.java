package com.liu.ioc;

/**
 * Created by liuzhilei3 on 2018/6/26.
 * rain代表从其他地方获取天气数据（数据库或者服务器）
 */
public class WeatherDao {
    public void getWeather() {
        System.out.println("正在下雨......");
    }
}
