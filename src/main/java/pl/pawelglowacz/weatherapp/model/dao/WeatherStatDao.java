package pl.pawelglowacz.weatherapp.model.dao;

import pl.pawelglowacz.weatherapp.model.WeatherStat;

import java.util.List;

/**
 * Created by Admin on 2017-08-30.
 */
public interface WeatherStatDao {
    void saveStat(WeatherStat weatherStat);
    List<WeatherStat> getLastSixStats(String cityname);
    List<String>getAllCities();
    float averageTempInCity(String cityname);
}

