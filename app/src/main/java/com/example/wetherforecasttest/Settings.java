package com.example.wetherforecasttest;

import com.example.wetherforecasttest.api.Options;

/**
 * Created by vovahost on 29/07/2017.
 */

public class Settings {

    public interface SettingsListener {
        void onSettingsLoaded(Settings settings);
    }

    private String cityId;
    private String language;
    private String unitsFormat;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUnitsFormat() {
        return unitsFormat;
    }

    public void setUnitsFormat(String unitsFormat) {
        this.unitsFormat = unitsFormat;
    }

    public void load(SettingsListener listener) {
        if (listener != null) {
            cityId = "3169070";
            language = "en";
            unitsFormat = Options.UNITS_METRIC;
            listener.onSettingsLoaded(this);
        }
    }

}
