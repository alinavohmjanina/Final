package com.weather.reports;

import org.json.JSONObject;

public class GeoWeatherReport extends Report {
    public GeoWeatherReport(JSONObject json) {
        JSONObject data = json;

        this.cityName = data.getString("name");
        this.countryCode = data.getJSONObject("sys").getString("country");

        this.longitude = data.getJSONObject("coord").getDouble("lon");
        this.latitude = data.getJSONObject("coord").getDouble("lat");

        this.currentTemperature = data.getJSONObject("main").getDouble("temp");
        this.minimalTemperature = data.getJSONObject("main").getDouble("temp_min");
        this.maximumTemperature = data.getJSONObject("main").getDouble("temp_max");
    }
}
