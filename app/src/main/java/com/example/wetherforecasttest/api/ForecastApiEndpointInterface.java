package com.example.wetherforecasttest.api;

import com.example.wetherforecasttest.models.Forecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by vovahost on 29/07/2017.
 */

public interface ForecastApiEndpointInterface {

    @GET("forecast/")
    Call<Forecast> getWetherForecast(@Query("id") String id, @Query("lang") String language, @Query("units") String unitsFormat, @Query("APPID") String apiKey);

}
