import com.weather.reports.GeoWeatherReport;
import com.weather.repositories.GeoWeatherRepository;
import com.weather.requests.WeatherRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeoWeatherRepositoryTest {

    @Test
    public void testIfGeoRepositoryRespCityEqualsReqSity() {
        try  {
            WeatherRequest request = new WeatherRequest(35.5, 70.0, "metric");

            GeoWeatherRepository.mock();
            GeoWeatherRepository repository = new GeoWeatherRepository();
            GeoWeatherReport report = repository.getGeoWeather(request);

            assertEquals(report.getLatitude(), request.getLatitude());
            assertEquals(report.getLongitude(), request.getLongitude());
            assertNotNull(report.getCurrentTemperature());
            assertNotNull(report.getMaximumTemperature());
            assertNotNull(report.getMinimalTemperature());
        }catch(Exception e){
            fail("Failure cause: " + e.getMessage());
        }
    }
}