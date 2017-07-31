package com.example.wetherforecasttest.api;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.wetherforecasttest.BuildConfig;
import com.example.wetherforecasttest.models.Forecast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vovahost on 29/07/2017.
 */

public class ForecastApi {

    private static final String TAG = "ForecastApi";
    private ForecastApiEndpointInterface mForecastApiService;
    private ForecastApiListener mForecastApiListener;

    public interface ForecastApiListener {
        void onCityForecastLoaded(Forecast forecast);
        void onApiError(String message);
    }

    private void initForecastApiService() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.OPEN_WEATHER_MAP_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        mForecastApiService = retrofit.create(ForecastApiEndpointInterface.class);
    }

    public void requestWeatherForecastByCity(String cityId, String language, String unitsFormat, ForecastApiListener listener) {
        if (mForecastApiService == null) {
            initForecastApiService();
        }
        mForecastApiListener = listener;
        Call<Forecast> call = mForecastApiService.getWetherForecast(cityId, language, unitsFormat, BuildConfig.OPEN_WEATHER_MAP_API_KEY);
        call.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(@NonNull Call<Forecast> call, @NonNull Response<Forecast> response) {
                int statusCode = response.code();
                Forecast forecast = response.body();
                if (mForecastApiListener != null) {
                    mForecastApiListener.onCityForecastLoaded(forecast);
                }
                Log.d(TAG, "onResponse -> statusCode: " + statusCode);
            }

            @Override
            public void onFailure(@NonNull Call<Forecast> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, "requestWeatherForecastForCity.onFailure()", t);
                if (mForecastApiListener != null) {
                    mForecastApiListener.onApiError("requestWeatherForecastForCity.onFailure()");
                }
            }
        });
    }
}
