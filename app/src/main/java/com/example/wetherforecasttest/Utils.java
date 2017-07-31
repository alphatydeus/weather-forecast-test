package com.example.wetherforecasttest;

/**
 * Created by vovahost on 29/07/2017.
 */

public class Utils {

    public static String capitalizeFirstLetter(String text) {
        if (text != null) {
            return text.substring(0, 1).toUpperCase() + text.substring(1);
        } else {
            return null;
        }
    }

    /* All the strings from below methods should be extracted to string resources to allow translations etc.*/

    public static String getWeatherIconUrl(String weatherIconId) {
        return "http://openweathermap.org/img/w/" + weatherIconId + ".png";
    }

    public static String getFormatedTemperature(double temperature) {
        return temperature + " °C";
    }

    public static String getFormatedWindSpeed(double windSpeed) {
        return "Wind speed: " + windSpeed + " m/s";
    }

    public static String getFormatedPressure(double pressure) {
        return "Pressure: " + pressure + " hPa";
    }

    public static String getFormatedHumidity(double humidity) {
        return "Humidity: " + humidity + " %";
    }
}
