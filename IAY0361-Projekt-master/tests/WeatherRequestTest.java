import com.weather.reports.ThreeDayWeatherReport;
import com.weather.repositories.ThreeDayWeatherRepository;
import com.weather.requests.WeatherRequest;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
//prverjaeb chto chitaet iz unput.txt, beret strochki i delaet request
class WeatherRequestTest {
    @Test
    void requestsFromFile() {
        try {
            List<WeatherRequest> requests = WeatherRequest.requestsFromFile();

            requests.forEach(request -> {
                request.setUnits("metric");

                ThreeDayWeatherRepository.mock();
                ThreeDayWeatherRepository threeDayWeatherRepository = new ThreeDayWeatherRepository();

                try {
                    ThreeDayWeatherReport report = threeDayWeatherRepository.getThreeDayWeather(request);

                    report.writeToFile();
                } catch (Exception e) {
                    fail("Failure cause: " + e.getMessage());
                }
            });
        } catch (IOException e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    void requestFromConsole() {
        String data = "Tallinn,EE";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        WeatherRequest request = WeatherRequest.requestFromConsole();

        assertEquals("Tallinn", request.getCityName());
        assertEquals("EE", request.getCountryCode());
    }

}