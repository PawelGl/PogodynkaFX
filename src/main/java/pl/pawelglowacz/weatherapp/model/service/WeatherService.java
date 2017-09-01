package pl.pawelglowacz.weatherapp.model.service;

import org.json.JSONObject;
import pl.pawelglowacz.weatherapp.Config;
import pl.pawelglowacz.weatherapp.Utils;
import pl.pawelglowacz.weatherapp.model.WeatherData;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Admin on 2017-08-21.
 */
public class WeatherService { //singleton
    public static WeatherService INSTANCE = new WeatherService();
    public static WeatherService getService(){
        return INSTANCE;
    }
    private ExecutorService service;
    private List<IWeatherObserver> observerList;



    private void parseJsonFromString(String text){
        WeatherData data= new WeatherData();

        JSONObject root = new JSONObject(text);

        JSONObject main = root.getJSONObject("main");
        data.setTemp((float)main.getDouble("temp"));

        JSONObject clouds = root.getJSONObject("clouds");
        data.setClouds(clouds.getInt("all"));
        data.setHumidity(main.getInt("humidity"));
        data.setPressure(main.getInt("pressure"));

        notifyObservers(data);

    }

    private WeatherService(){
        service = Executors.newFixedThreadPool(2);
        observerList=new ArrayList<>();
    }

    public void registerObserver(IWeatherObserver iWeatherObserver){
        observerList.add(iWeatherObserver);
    }

    private void notifyObservers(WeatherData weatherData){
        for (IWeatherObserver iWeatherObserver : observerList) {
            iWeatherObserver.onWeatherUpdate(weatherData);
        }
    }

    public void init(final  String city) {
        Runnable taskInit = new Runnable() {
            @Override
            public void run() {
                String text = Utils.readWebsiteContext(Config.API_URL + city + "&appid=" + Config.APP_KEY);
                parseJsonFromString(text);

            }
        };
        service.execute(taskInit); //uruchamianie !

    }

}
