package com.weather.reports;

import org.json.JSONArray;
import org.json.JSONObject;
//vqtaskivaet infu iz jsn objekta po kljuchevqm slovam cherez repository
public class CurrentWeatherReport extends Report {
    public CurrentWeatherReport(JSONObject json) {
        JSONObject data = json;

        this.cityName = data.getString("name");
        this.countryCode = data.getJSONObject("sys").getString("country");

        this.currentTemperature = data.getJSONObject("main").getDouble("temp");
        this.minimalTemperature = data.getJSONObject("main").getDouble("temp_min");
        this.maximumTemperature = data.getJSONObject("main").getDouble("temp_max");
    }
}
