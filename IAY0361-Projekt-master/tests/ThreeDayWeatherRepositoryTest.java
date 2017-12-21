import com.weather.reports.ThreeDayWeatherReport;
import com.weather.repositories.ThreeDayWeatherRepository;
import com.weather.requests.WeatherRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThreeDayWeatherRepositoryTest {

    @Test
    public void testIfThreeDayWRepositoryRespCityEqualsReqSity() {
        try  {
            WeatherRequest request = new WeatherRequest("Tallinn", "EE", "metric");

            ThreeDayWeatherRepository.mock();
            ThreeDayWeatherRepository repository = new ThreeDayWeatherRepository();
            ThreeDayWeatherReport report = repository.getThreeDayWeather(request);

            assertEquals(report.getCityName(), request.getCityName());
            assertEquals(report.getCountryCode(), request.getCountryCode());
            assertTrue(report.temperatures.size() == 3);
        }catch(Exception e){
            fail("Failure cause: " + e.getMessage());
        }
    }

}