package com.example.wetherforecasttest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wetherforecasttest.models.List;

import org.joda.time.DateTime;

/**
 * Created by vovahost on 29/07/2017.
 */

public class DayFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;
    private DateTime mDateTime;
    private RecyclerView mRecycler;
    private HourForecastAdapter mAdapter;
    private java.util.List<List> mForecast;

    public static DayFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        DayFragment fragment = new DayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
        mDateTime = (new DateTime()).plusDays(mPage);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_page, container, false);
        TextView weatherDate = (TextView) root.findViewById(R.id.weather_date);
        String date = getCurrentDate();
        weatherDate.setText(date);

        mRecycler = (RecyclerView) root.findViewById(R.id.list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecycler.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), layoutManager.getOrientation());
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.list_item_forecast_divider));
        mRecycler.addItemDecoration(dividerItemDecoration);
        mAdapter = new HourForecastAdapter(getContext());
        mRecycler.setAdapter(mAdapter);
        mAdapter.setDataset(mForecast);
        return root;
    }

    private String getCurrentDate() {
        String dayOfWeekName = Utils.capitalizeFirstLetter(mDateTime.dayOfWeek().getAsText());
        int dayOfMonth = mDateTime.getDayOfMonth();
        String monthName = Utils.capitalizeFirstLetter(mDateTime.monthOfYear().getAsText());
        int year = mDateTime.getYear();

        return getContext().getString(R.string.weather_for_date, dayOfWeekName, dayOfMonth, monthName, year);
    }

    public void setForecast(java.util.List<List> forecast) {
        mForecast = forecast;
    }
}