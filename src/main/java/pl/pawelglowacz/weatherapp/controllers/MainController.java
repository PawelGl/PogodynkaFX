package pl.pawelglowacz.weatherapp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import pl.pawelglowacz.weatherapp.model.WeatherData;
import pl.pawelglowacz.weatherapp.model.WeatherStat;
import pl.pawelglowacz.weatherapp.model.dao.WeatherStatDao;
import pl.pawelglowacz.weatherapp.model.dao.WeatherStatDaoImpl;
import pl.pawelglowacz.weatherapp.model.service.IWeatherObserver;
import pl.pawelglowacz.weatherapp.model.service.WeatherService;
import pl.pawelglowacz.weatherapp.model.utils.DataBaseConnection;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Admin on 2017-08-30.
 */
public class MainController implements IWeatherObserver, Initializable{

    @FXML
    Button buttonZnajdzID;

    @FXML
    TextField textFieldiD;

    @FXML
    TextArea textAreaID;

    @FXML
    ProgressIndicator progressLoad;

    @FXML
    Button buttonStatystyki;

    private WeatherService weatherService = WeatherService.getService(); //instancja obiektu weatherserwice
    private DataBaseConnection dataBaseConnection=DataBaseConnection.getService(); //polaczenie z baza
    private WeatherStatDao weatherStatDao=new WeatherStatDaoImpl(); //implementacja interfejsu ktory zawiera zapytania do bazy
    private String lastCityName; // string potrzebny do zapameitania miasta ktore wpisujemy
    @Override
    public void onWeatherUpdate(WeatherData data) {
        //textAreaID.setText();
        weatherStatDao.saveStat(new WeatherStat(lastCityName, (float) data.getTemp()));
        textAreaID.appendText(data.toString());
        textAreaID.setWrapText(true); //zawijanie
        progressLoad.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        weatherService.registerObserver(this);
        registeSHowButtonAction();
        registerEnterListener();
        registerButtonStatsAction();

    }

    private void registeSHowButtonAction(){
        buttonZnajdzID.setOnMouseClicked(s -> {
            lastCityName=textFieldiD.getText();
            weatherService.init(textFieldiD.getText());
        });

    }

    private void registerButtonStatsAction(){
        buttonStatystyki.setOnMouseClicked(Clickedme -> {
            Stage stage =(Stage)buttonStatystyki.getScene().getWindow();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("stats.fxml"));
                stage.setScene(new Scene(root, 600, 400));
            } catch (IOException e) {
                e.printStackTrace();
            }


        });
    }

    private void registerEnterListener(){
        textFieldiD.setOnKeyPressed(e -> {
            if(e.getCode()== KeyCode.ENTER){
                lastCityName=textFieldiD.getText();
                progressLoad.setVisible(true);
                weatherService.init(textFieldiD.getText());
            }
        });
    }
}
