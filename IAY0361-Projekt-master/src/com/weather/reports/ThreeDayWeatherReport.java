package com.weather.reports;

import com.weather.File;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
//nabirajet infu iz jsn objekta,
public class ThreeDayWeatherReport extends Report {
    public Map<String, Map<String, Double>> temperatures;

    public ThreeDayWeatherReport(JSONObject json) {
        this.cityName = json.getJSONObject("city").getString("name");
        this.countryCode = json.getJSONObject("city").getString("country");

        this.latitude = json.getJSONObject("city").getJSONObject("coord").getDouble("lat");
        this.longitude = json.getJSONObject("city").getJSONObject("coord").getDouble("lon");

        JSONArray list = json.getJSONArray("list"); //delaet pustoq array
        Map<String, Map<String, Double>> temperatures = new HashMap<String, Map<String, Double>>();

        for (int i = 0; i < list.length(); i++) {
            JSONObject item = list.getJSONObject(i);

            if (temperatures.size() >= 3) {
                break;
            }

            Date date = new Date((long) item.getInt("dt") * 1000);
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            String key = localDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            Map<String, Double> weather = temperatures.getOrDefault(key, null);

            if (weather == null) {
                weather = new HashMap<String, Double>();

                weather.put("min", item.getJSONObject("main").getDouble("temp_min"));
                weather.put("max", item.getJSONObject("main").getDouble("temp_max"));
            }

            if (weather.get("min") > item.getJSONObject("main").getDouble("temp_min")) {
                weather.put("min", item.getJSONObject("main").getDouble("temp_min"));
            }

            if (weather.get("max") < item.getJSONObject("main").getDouble("temp_max")) {
                weather.put("max", item.getJSONObject("main").getDouble("temp_max"));
            }

            temperatures.put(key, weather);
        }

        this.temperatures = temperatures;

        this.currentTemperature = json.getJSONArray("list").getJSONObject(0).getJSONObject("main").getDouble("temp");
        this.minimalTemperature = json.getJSONArray("list").getJSONObject(0).getJSONObject("main").getDouble("temp_min");
        this.maximumTemperature = json.getJSONArray("list").getJSONObject(0).getJSONObject("main").getDouble("temp_max");
    }

    public void writeToFile() throws IOException {
        File fh = new File();
        List<String> lines = new ArrayList<>();

        lines.add("Linna nimi: " + this.getCityName());
        lines.add("Linna koordinaadid: " + this.getLatitude() + "," + this.getLongitude());
        lines.add("Ilm 3 päeva kohta:");

        this.temperatures.forEach((date, temperature) -> {
            lines.add("\t * " + date + ": Min = " + temperature.get("min") + ", Max = " + temperature.get("max"));
        });

        lines.add("Hetke ilma temperaturr: " + this.getCurrentTemperature());

        fh.write(lines, this.getCityName() + ".txt");
    }
}
