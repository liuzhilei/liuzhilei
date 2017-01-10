package com.liu.j2setest.designpattern.observer;

import java.util.Observable;

/**
 * Created by liuzhilei on 2016/12/12.
 */
public class CurrentConditionDisplay implements java.util.Observer, DisplayElement {
    private float temperature;
    private float humidity;
    Observable observable;

    //构造方法内进行注册
    public CurrentConditionDisplay(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }


    @Override
    public void display() {
        System.out.println("temperature:" + temperature + ",humidity:" + humidity);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof WeatherData){
            WeatherData weatherData = (WeatherData)o;
            this.temperature = weatherData.getTemperature();
            this.humidity = weatherData.getHumidity();
            display();
        }
    }
}
