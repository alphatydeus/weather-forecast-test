package com.example.wetherforecasttest;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.wetherforecasttest.api.ForecastApi;
import com.example.wetherforecasttest.models.Forecast;

public class MainActivity extends AppCompatActivity implements ForecastApi.ForecastApiListener, Settings.SettingsListener {

    private static final String TAG = "MainActivity";
    private static final int FORECAST_DAY_COUNT = 5;
    private DayForecastPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupLayout();
        Settings settings = new Settings();
        settings.load(this);
    }

    @Override
    public void onSettingsLoaded(Settings settings) {
        loadWeather(settings);
    }

    private void loadWeather(Settings settings) {
        ForecastApi forecastApi = new ForecastApi();
        forecastApi.requestWeatherForecastByCity(settings.getCityId(), settings.getLanguage(), settings.getUnitsFormat(), this);
    }

    private void setupLayout() {
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter =
                new DayForecastPagerAdapter(getSupportFragmentManager(), MainActivity.this);
        mViewPager.setAdapter(mPagerAdapter);
    }

    private void setupTabLayout() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(mViewPager);
        // Iterate over all tabs and set the custom view
        for (int i = 0; i < FORECAST_DAY_COUNT; i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(mPagerAdapter.getTabView(i));
        }
    }

    @Override
    public void onCityForecastLoaded(Forecast forecast) {
        if (forecast != null) {
            mPagerAdapter.setForecast(forecast);
            setupTabLayout();
        }
    }

    @Override
    public void onApiError(String message) {
        Log.e(TAG, "onApiError -> message: " + message);
    }
}
