package com.example.wetherforecasttest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.List;

/**
 * Created by vovahoft on 29/07/2017.
 */

public class HourForecastAdapter extends RecyclerView.Adapter<HourForecastAdapter.ViewHolder> {

    private final Context mContext;
    private LayoutInflater mInflater;
    private List<com.example.wetherforecasttest.models.List> mDayForecasts;
    private DateTime mDateTime = new DateTime();


    public HourForecastAdapter(Context context) {
        super();
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView timeTv;
        public TextView temperatureTv;
        public ImageView weatherIcon;
        public TextView windTv;
        public TextView pressureTv;
        public TextView humidityTv;

        ViewHolder(View v) {
            super(v);
            this.timeTv = (TextView) itemView.findViewById(R.id.time);
            this.weatherIcon = (ImageView) itemView.findViewById(R.id.weather_icon);
            this.temperatureTv = (TextView) itemView.findViewById(R.id.temperature);
            this.windTv = (TextView) itemView.findViewById(R.id.wind);
            this.pressureTv = (TextView) itemView.findViewById(R.id.pressure);
            this.humidityTv = (TextView) itemView.findViewById(R.id.humidity);
        }

    }

    public void setDataset(List<com.example.wetherforecasttest.models.List> dayForecasts) {
        mDayForecasts = dayForecasts;
        notifyDataSetChanged();
    }

    @Override
    public HourForecastAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_forecast, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        com.example.wetherforecasttest.models.List list = mDayForecasts.get(position);
        DateTime dateTime = mDateTime.withMillis(list.getDt() * 1000L);
        double temp = list.getMain().getTemp();
        double windSpeed = list.getWind().getSpeed();
        double pressure = list.getMain().getPressure();
        int humidity = list.getMain().getHumidity();

        holder.timeTv.setText(dateTime.toString(DateTimeFormat.shortTime()));
        holder.temperatureTv.setText(Utils.getFormatedTemperature(temp));
        holder.windTv.setText(Utils.getFormatedWindSpeed(windSpeed));
        holder.pressureTv.setText(Utils.getFormatedPressure(pressure));
        holder.humidityTv.setText(Utils.getFormatedHumidity(humidity));

        String iconId = list.getWeather().get(0).getIcon();
        Glide.with(mContext)
                .load(Utils.getWeatherIconUrl(iconId))
                .into(holder.weatherIcon);
    }

    @Override
    public int getItemCount() {
        return mDayForecasts != null ? mDayForecasts.size() : 0;
    }
}
