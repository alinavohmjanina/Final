package com.weather.reports;
//perem i getterq
abstract public class Report {
    protected String cityName;
    protected String countryCode;

    protected Double latitude;
    protected Double longitude;

    protected Double currentTemperature;
    protected Double minimalTemperature;
    protected Double maximumTemperature;

    public String getCityName() {
        return cityName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public Double getCurrentTemperature() {
        return currentTemperature;
    }

    public Double getMinimalTemperature() {
        return minimalTemperature;
    }

    public Double getMaximumTemperature() {
        return maximumTemperature;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
