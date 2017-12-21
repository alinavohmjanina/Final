import com.weather.reports.CurrentWeatherReport;
import com.weather.repositories.CurrentWeatherRepository;
import com.weather.requests.WeatherRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrentWeatherRepositoryTest {

    @Test
    public void testIfWeatherRepositoryRespCityEqualsReqSity() {
        try  {
            WeatherRequest request = new WeatherRequest("Tallinn", "EE", "metric");

            CurrentWeatherRepository.mock();
            CurrentWeatherRepository repository = new CurrentWeatherRepository();
            CurrentWeatherReport report = repository.getCurrentWeather(request);

            assertEquals(report.getCityName(), request.getCityName());
            assertEquals(report.getCountryCode(), request.getCountryCode());
            assertNotNull(report.getCurrentTemperature());
            assertNotNull(report.getMaximumTemperature());
            assertNotNull(report.getMinimalTemperature());
        }catch(Exception e){
            fail("Failure cause: " + e.getMessage());
        }
    }
}