package pl.pawelglowacz.weatherapp.model.utils;

import java.sql.*;

/**
 * Created by Admin on 2017-08-30.
 */
public class DataBaseConnection {
    private static  final String SQL_LINK="jdbc:mysql://5.135.218.27:3306/PawelG?characterEncoding=utf8";
    private static  final String SQL_USER= "PawelG";
    private static  final String PASSWORD= "pawelgl";
    private static  final String SQL_CLASS="com.mysql.jdbc.Driver";
    public static DataBaseConnection INSTANCE = new DataBaseConnection();
    public static DataBaseConnection getService(){
        return INSTANCE;
    }

    private Connection connection;

    private  DataBaseConnection(){
        try {
            Class.forName(SQL_CLASS).newInstance();
            connection= DriverManager.getConnection(SQL_LINK,SQL_USER,PASSWORD);
            System.out.println("Polaczono");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("hehe");
            e.printStackTrace();
        }


    }
    public PreparedStatement getNewPreparedStatement(String sql){
        try {
            return connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Statement getNewStatement(String sql){
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



}