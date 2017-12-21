package com.weather.repositories;

import com.weather.reports.GeoWeatherReport;
import com.weather.requests.WeatherRequest;
import org.json.JSONObject;

import java.io.IOException;
//lan lon search
public class GeoWeatherRepository extends Repository {
    public GeoWeatherReport getGeoWeather(WeatherRequest request) throws IOException {
        if (this.isMock()) {
            return new GeoWeatherReport(new JSONObject(this.mockString(request)));
        }

        JSONObject data = this.readJsonFromUrl("https://api.openweathermap.org/data/2.5/weather?lat=" + request.getLatitude() + "&lon=" + request.getLongitude() + "&units=" + request.getUnits() + "&APPID=" + this.getApiKey());

        return new GeoWeatherReport(data);
    }

    public String mockString(WeatherRequest request) {
        return "{\"coord\":{\"lon\":" + request.getLongitude() + ",\"lat\":" + request.getLatitude() + "},\"weather\":[{\"id\":802,\"main\":\"Clouds\",\"description\":\"scattered clouds\",\"icon\":\"03n\"}],\"base\":\"stations\",\"main\":{\"temp\":281.461,\"pressure\":1025.65,\"humidity\":100,\"temp_min\":281.461,\"temp_max\":281.461,\"sea_level\":1035.44,\"grnd_level\":1025.65},\"wind\":{\"speed\":3.46,\"deg\":58.5011},\"clouds\":{\"all\":36},\"dt\":1513531446,\"sys\":{\"message\":0.0022,\"country\":\"JP\",\"sunrise\":1513460765,\"sunset\":1513496077},\"id\":1851632,\"name\":\"Shuzenji\",\"cod\":200}";
    }
}
