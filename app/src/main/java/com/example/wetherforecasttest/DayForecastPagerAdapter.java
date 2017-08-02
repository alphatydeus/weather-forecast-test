package com.example.wetherforecasttest;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.wetherforecasttest.models.Forecast;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by vovahost on 29/07/2017.
 */

public class DayForecastPagerAdapter extends FragmentPagerAdapter {

    private static class Day {
        static int one = 0;
        static int two = 1;
        static int three = 2;
        static int four = 3;
        static int five = 4;
    }

    private final Context mContext;
    private List<Fragment> mFragments = Collections.EMPTY_LIST;
    private DateTime mDateTime;

    public DayForecastPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
        mDateTime = new DateTime();
    }

    public View getTabView(int position) {
        DateTime dateTime = mDateTime.plusDays(position);
        View v = LayoutInflater.from(mContext).inflate(R.layout.day_tab, null);
        String dayOfWeekName = dateTime.dayOfWeek().getAsShortText();
        int dayOfMonth = dateTime.getDayOfMonth();
        TextView dayOfWeekTv = (TextView) v.findViewById(R.id.day_of_week_name);
        dayOfWeekTv.setText(Utils.capitalizeFirstLetter(dayOfWeekName));
        TextView dayOfMonthTv = (TextView) v.findViewById(R.id.day_of_month_number);
        dayOfMonthTv.setText(String.valueOf(dayOfMonth));

        return v;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    public DayFragment addFragment(int page) {
        DayFragment dayFragment = DayFragment.newInstance(page);

        mFragments.add(dayFragment);
        return dayFragment;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    public void setForecast(Forecast forecast) {
        mFragments = new ArrayList<>();

        List<List<com.example.wetherforecasttest.models.List>> dailyForecast = extractDailyForecast(forecast);

        addFragment(Day.one).setForecast(dailyForecast.get(Day.one));
        addFragment(Day.two).setForecast(dailyForecast.get(Day.two));
        addFragment(Day.three).setForecast(dailyForecast.get(Day.three));
        addFragment(Day.four).setForecast(dailyForecast.get(Day.four));
        addFragment(Day.five).setForecast(dailyForecast.get(Day.five));
        notifyDataSetChanged();
    }

    private List<List<com.example.wetherforecasttest.models.List>> extractDailyForecast(Forecast forecast) {
        List<List<com.example.wetherforecasttest.models.List>> dayForecast = new ArrayList<>();
        List<com.example.wetherforecasttest.models.List> hourForecast = new ArrayList<>();
        dayForecast.add(hourForecast);

        for (com.example.wetherforecasttest.models.List list : forecast.getList()) {
            DateTime forecastDateTime = new DateTime().withMillis(list.getDt() * 1000L);
            if (mDateTime.getDayOfMonth() != forecastDateTime.getDayOfMonth()) {
                hourForecast = new ArrayList<>();
                dayForecast.add(hourForecast);
                mDateTime = mDateTime.plusDays(1);
            }
            hourForecast.add(list);
        }
        return dayForecast;
    }
}
