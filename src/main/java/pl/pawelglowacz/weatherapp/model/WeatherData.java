package pl.pawelglowacz.weatherapp.model;

/**
 * Created by Admin on 2017-08-21.
 */
public class WeatherData {

    private float temp;
    private int humidity;
    private int pressure;
    private int clouds;

    public double getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }



    public WeatherData(float temp, int humidity, int pressure, int clouds) {
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        this.clouds = clouds;
    }

    public WeatherData() {

    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "temp=" + temp +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                ", clouds=" + clouds +
                '}';
    }
}
