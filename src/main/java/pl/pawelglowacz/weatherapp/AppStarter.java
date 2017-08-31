package pl.pawelglowacz.weatherapp;



import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


import javax.swing.*;
import java.util.Scanner;

/**
 * Created by Admin on 2017-08-21.
 */
public class AppStarter extends Application {
    public static void main(String[] args) {
        // System.out.println(Utils.readWebsiteContext("http://api.openweathermap.org/data/2.5/weather?q=Cracow&appid=0b8ba4d76c953f66e439581efb4264c0" ));

        launch(args);

    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"));
        primaryStage.setTitle("Pogodynka");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {

                System.exit(0);
            }
        });
    }


}


