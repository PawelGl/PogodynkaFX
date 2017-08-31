package pl.pawelglowacz.weatherapp.model.service;


import pl.pawelglowacz.weatherapp.model.WeatherData;

/**
 * Created by Admin on 2017-08-21.
 */
public interface IWeatherObserver {
    void onWeatherUpdate(WeatherData data);
}
