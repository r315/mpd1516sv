import org.junit.Before;
import org.junit.Test;

import weathergw.domain.Location;
import weathergw.domain.WeatherInfo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Assert;

/**
 * Created by lfalcao on 07/03/16.
 */
public class LocationTests {
    private Location location;
    private WeatherInfo weatherInfo1;
    private WeatherInfo weatherInfo2;
    private WeatherInfo weatherInfo3;
    private WeatherInfo weatherInfo4;

    @Before
    public void before() {
        location = new Location("Lisbon");
        weatherInfo1 = new WeatherInfo(LocalDate.now(), 14, 11, LocalTime.of(7, 43), LocalTime.of(17, 58), LocalTime.of(1, 9), LocalTime.of(12, 9));
        weatherInfo2 = new WeatherInfo(LocalDate.now().minusDays(1), 14, 11, LocalTime.of(7, 43), LocalTime.of(17, 58), LocalTime.of(1, 9), LocalTime.of(12, 9));
        weatherInfo3 = new WeatherInfo(LocalDate.now().minusDays(2), 14, 11, LocalTime.of(7, 43), LocalTime.of(17, 58), LocalTime.of(1, 9), LocalTime.of(12, 9));
        weatherInfo4 = new WeatherInfo(LocalDate.now().minusDays(3), 14, 11, LocalTime.of(7, 43), LocalTime.of(17, 58), LocalTime.of(1, 9), LocalTime.of(12, 9));        

    }

    @Test
    public void shouldBeAbleToAddWeatherInfosToLocation() {
        location.add(weatherInfo1);
        location.add(weatherInfo2);


        int count = 0;
        for (WeatherInfo wi: location) {
            ++count;
        }

        Assert.assertEquals(2, count);
    }
    @Test
    public void shouldBeAbleToGetWeatherInfoBetweenDates(){
    	location.add(weatherInfo1);
        location.add(weatherInfo2);
        location.add(weatherInfo3);
        location.add(weatherInfo4);
         
         List <WeatherInfo> wil1 = location.getHistory(LocalDate.now().minusDays(3), LocalDate.now().minusDays(1));
         List <WeatherInfo> wil2 = location.getHistory(LocalDate.now().minusDays(3), LocalDate.now().minusDays(1));
         int count = 0;
         for (WeatherInfo wi: wil1) {
             ++count;
         }
         Assert.assertEquals(1, count);         
        
         count = 0;
         for (WeatherInfo wi: wil2) {
             ++count;
         }
         Assert.assertEquals(1, count);
    }
}
