package com.weather.requests;

import com.weather.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//parametrq zaproza vse
public class WeatherRequest {
    protected String cityName;
    protected String countryCode;
    protected String units;

    protected Double latitude;
    protected Double longitude;

    public WeatherRequest(String cityName, String countryCode, String units) {
        this.cityName = cityName;
        this.countryCode = countryCode;
        this.units = units;
    }

    public WeatherRequest(Double latitude, Double longitude, String units) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.units = units;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public static List<WeatherRequest> requestsFromFile() throws IOException {
        File fh = new File();

        List<String> lines = fh.read("input.txt");
        List<WeatherRequest> requests = new ArrayList<>();

        lines.forEach(line -> {
            String[] osad = line.split(",");

            if (osad.length == 2) {
                requests.add(new WeatherRequest(osad[0], osad[1], "metric"));
            }
        });

        return requests;
    }

    public static WeatherRequest requestFromConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Sisestage linna nimi (Tallinn,EE): ");
        String input = scanner.nextLine();

        String[] osad = input.split(",");

        return new WeatherRequest(osad[0], osad[1], "metric");
    }
}
