package pl.pawelglowacz.weatherapp.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.pawelglowacz.weatherapp.model.WeatherStat;
import pl.pawelglowacz.weatherapp.model.dao.WeatherStatDao;
import pl.pawelglowacz.weatherapp.model.dao.WeatherStatDaoImpl;
import pl.pawelglowacz.weatherapp.model.utils.DataBaseConnection;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Admin on 2017-08-30.
 */
public class StatsController implements Initializable {
    @FXML
    BarChart<String,Number> chartWeather;
    @FXML
    ListView listOfCity;
    @FXML
    Button buttonBack;
    @FXML
    TextField textFieldAverage;

    private DataBaseConnection dataBaseConnection=DataBaseConnection.getService();
    private WeatherStatDao weatherStatDao=new WeatherStatDaoImpl();

    private void loadCityname(){
        listOfCity.setItems(FXCollections.observableList(weatherStatDao.getAllCities())); //obserowwalne koleckcje ktore obserwuja czy cos sie zmienia;p;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        registerButtonBack();
        loadCityname();
        registerCLickItemOnList();

    }
    private void registerCLickItemOnList(){
        listOfCity.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            loadChart((String)newValue);
            registerAverageTextField((String)newValue);
        });
    }
    private void loadChart(String city){
        XYChart.Series series1 =new XYChart.Series();
        series1.setName(city);
        int counter=1;
        for (WeatherStat weatherStat : weatherStatDao.getLastSixStats(city)) {
            series1.getData().add(new XYChart.Data(""+counter,weatherStat.getTemp()) );
            counter++;

        }
        chartWeather.getData().clear();
        chartWeather.getData().add(series1);
    }

    private void registerButtonBack() {
        buttonBack.setOnMouseClicked(ClickMe->{
            Stage stage =(Stage)buttonBack.getScene().getWindow();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"));
                stage.setScene(new Scene(root, 600, 400));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    private void registerAverageTextField(String city){
        Float average=weatherStatDao.averageTempInCity(city);
        textFieldAverage.setText(average.toString());
    }
}
