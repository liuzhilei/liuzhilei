package com.liu.j2setest.designpattern.observer;

import java.util.Observable;

/**
 * Created by liuzhilei on 2016/12/12.
 */
public class WeatherData extends Observable {

    private float temperature;
    private float humidity;
    private float pressure;

    public void measurementsChanged() {
        setChanged();//表示状态已经改变
        notifyObservers(); //没有参数，表示从被观察的那里主动拉取数据

    }

    public void setMeasurement(float temperature, float humidity, float pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.measurementsChanged();

    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}
