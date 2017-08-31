package pl.pawelglowacz.weatherapp;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Admin on 2017-08-21.
 */
public class Utils {
    public static String readWebsiteContext(String url){
        StringBuilder builder=new StringBuilder();

        try {
            HttpURLConnection httpURLConnection =(HttpURLConnection)new URL(url).openConnection();
           InputStream inputStream = httpURLConnection.getInputStream();
           int resposne=0;
           while((resposne=inputStream.read())!=-1) {
                builder.append((char)resposne);


           }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }
}
