package com.weather.repositories;

import com.weather.reports.CurrentWeatherReport;
import com.weather.reports.GeoWeatherReport;
import com.weather.requests.WeatherRequest;
import org.json.JSONObject;

import java.io.IOException;
//zaprawivajet dannqje kotorqe ukazanq v reqeste, idet na server i zaprawivajet pogodu c moimi parametrami
public class CurrentWeatherRepository extends Repository {
    public CurrentWeatherReport getCurrentWeather(WeatherRequest request) throws IOException {
        if (this.isMock()) {
            return new CurrentWeatherReport(new JSONObject(this.mockString(request)));
        }

        JSONObject data = this.readJsonFromUrl("https://api.openweathermap.org/data/2.5/weather?q=" + request.getCityName() + "," + request.getCountryCode() + "&units=" + request.getUnits() + "&APPID=" + this.getApiKey());

        return new CurrentWeatherReport(data);
    }

    public String mockString(WeatherRequest request) {
        return "{\"coord\":{\"lon\":24.75,\"lat\":59.44},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04n\"}],\"base\":\"stations\",\"main\":{\"temp\":1,\"pressure\":1001,\"humidity\":100,\"temp_min\":1,\"temp_max\":1},\"visibility\":9000,\"wind\":{\"speed\":2.6,\"deg\":160},\"clouds\":{\"all\":90},\"dt\":1513439400,\"sys\":{\"type\":1,\"id\":5014,\"message\":0.0022,\"country\":\"" + request.getCountryCode() + "\",\"sunrise\":1513408478,\"sunset\":1513430357},\"id\":590447,\"name\":\"" + request.getCityName() + "\",\"cod\":200}";
    }
}
