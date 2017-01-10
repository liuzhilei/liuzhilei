package com.liu.j2setest.designpattern.observer;

/**
 * Created by liuzhilei on 2016/12/12.
 */
public class Main {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        CurrentConditionDisplay currentConditionDisplay = new CurrentConditionDisplay(weatherData);
        //StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
        weatherData.setMeasurement(10,15,20);
        weatherData.setMeasurement(50,100,200);
    }
}
