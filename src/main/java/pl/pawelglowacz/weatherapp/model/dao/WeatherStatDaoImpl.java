package pl.pawelglowacz.weatherapp.model.dao;

import pl.pawelglowacz.weatherapp.model.WeatherStat;
import pl.pawelglowacz.weatherapp.model.utils.DataBaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2017-08-30.
 */
public class WeatherStatDaoImpl implements WeatherStatDao {
    private DataBaseConnection dataBaseConnection=DataBaseConnection.getService();

    @Override
    public void saveStat(WeatherStat weatherStat) {
        PreparedStatement preparedStatement= dataBaseConnection.getNewPreparedStatement("INSERT INTO weather VALUES(?,?,?)");

        try {
            preparedStatement.setInt(1,0);
            preparedStatement.setString(2,weatherStat.getCityname());
            preparedStatement.setFloat(3,weatherStat.getTemp());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<WeatherStat> getLastSixStats(String cityname) {

        List<WeatherStat> weatherStatList = new ArrayList<>(); //lista  z temperaturami ostatnich 6 wynikow
        /* wykonuje zapytanie do bazy*/
        PreparedStatement preparedStatement= dataBaseConnection.getNewPreparedStatement("SELECT * FROM weather WHERE city=? ORDER BY id DESC LIMIT 6 ");

        try {
            preparedStatement.setString(1,cityname);

            ResultSet resultSet = preparedStatement.executeQuery(); //wykonuje zapytanie
            while(resultSet.next()){
                weatherStatList.add(new WeatherStat(resultSet.getString("city"),resultSet.getFloat("temp")));

            }
            resultSet.close();;
            preparedStatement.close();
            return weatherStatList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<String> getAllCities() {
        List<String> citynamelist = new ArrayList<>(); //lista  miast jakie wystepuja u nas w bazie
        PreparedStatement preparedStatement= dataBaseConnection.getNewPreparedStatement("SELECT DISTINCT city FROM weather  ");

        try {

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                citynamelist.add(resultSet.getString("city"));

            }
            resultSet.close();;
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return citynamelist;
    }

    @Override
    public float averageTempInCity(String cityname) {
        PreparedStatement preparedStatement= dataBaseConnection.getNewPreparedStatement("SELECT  temp FROM weather WHERE city=? ");
        float averageTemp=0;
        float counter=0;
        try {
            preparedStatement.setString(1,cityname);

            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                averageTemp+=resultSet.getFloat("temp");
                counter++;
            }
            resultSet.close();;
            preparedStatement.close();
            return averageTemp/counter;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
