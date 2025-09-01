package com.weathermusic.model;

public class WeatherResponse {
    private String city;
    private String weatherMain;
    private String description;
    private double temperatureC;
    private double temperatureF;
    private int humidity;
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getWeatherMain() {
        return weatherMain;
    }
    
    public void setWeatherMain(String weatherMain) {
        this.weatherMain = weatherMain;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public double getTemperatureC() {
        return temperatureC;
    }
    
    public void setTemperatureC(double temperatureC) {
        this.temperatureC = temperatureC;
    }
    
    public double getTemperatureF() {
        return temperatureF;
    }
    
    public void setTemperatureF(double temperatureF) {
        this.temperatureF = temperatureF;
    }
    
    public int getHumidity() {
        return humidity;
    }
    
    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
}