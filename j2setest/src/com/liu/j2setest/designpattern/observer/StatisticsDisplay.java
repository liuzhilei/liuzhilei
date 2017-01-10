package com.liu.j2setest.designpattern.observer;

import java.util.Observable;

/**
 * Created by liuzhilei on 2016/12/12.
 */
public class StatisticsDisplay implements java.util.Observer, DisplayElement {
    private float humidity;
    private float pressure;
    Observable observable;

    public StatisticsDisplay(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }


    @Override
    public void display() {
        System.out.println("humidity:" + humidity + ",pressure:" + pressure);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof WeatherData){
            WeatherData weatherData = (WeatherData)o;
            this.humidity = weatherData.getHumidity();
            this.pressure = weatherData.getPressure();
            display();
        }
    }
}
