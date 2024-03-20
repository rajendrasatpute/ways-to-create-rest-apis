package io.github.rajendrasatpute.samplespringbootapi.service;

import io.github.rajendrasatpute.samplespringbootapi.client.OpenMeteoClient;
import io.github.rajendrasatpute.samplespringbootapi.client.SunriseSunsetClient;
import io.github.rajendrasatpute.samplespringbootapi.dto.CityInfoResponse;
import io.github.rajendrasatpute.samplespringbootapi.dto.NewCityRequest;
import io.github.rajendrasatpute.samplespringbootapi.model.City;
import io.github.rajendrasatpute.samplespringbootapi.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CityService {

    private final CityRepository cityRepository;
    private final SunriseSunsetClient sunriseSunsetClient;
    private final OpenMeteoClient openMeteoClient;

    public CityInfoResponse getCityInfo(String cityName) {
        City city = getCityCoordinates(cityName);
        if (null == city) {
            return CityInfoResponse.builder().build();
        }
        Object sunriseAndSunset = getSunriseSunsetTimesForCity(city);
        Object weather = getWeatherForCity(city);
        return CityInfoResponse.builder()
                .cityName(city.getCityName())
                .latitude(city.getLatitude())
                .longitude(city.getLongitude())
                .sunriseAndSunset(sunriseAndSunset)
                .weather(weather)
                .build();
    }

    public void addCity(NewCityRequest newCityRequest) {
        City city = City.builder()
                .cityName(newCityRequest.getCityName())
                .latitude(newCityRequest.getLatitude())
                .longitude(newCityRequest.getLongitude())
                .build();
        cityRepository.save(city);
    }

    private City getCityCoordinates(String cityName) {
        City city = null;
        try {
            List<City> cities = cityRepository.findByCityName(cityName.toUpperCase());
            if (!cities.isEmpty()) {
                city = cities.get(0);
            }
        } catch (Exception exception) {
            log.error("Error while fetching city coordinates", exception);
        }
        return city;
    }

    private Object getSunriseSunsetTimesForCity(City city) {
        Object sunriseAndSunset = null;
        try {
            sunriseAndSunset = sunriseSunsetClient.getSunriseSunsetTimes(city.getLatitude(), city.getLongitude());
        } catch (Exception exception) {
            log.error("Error while fetching sunset sunrise details", exception);
        }
        return sunriseAndSunset;
    }

    private Object getWeatherForCity(City city) {
        Object weather = null;
        try {
            weather = openMeteoClient.getWeather(city.getLatitude(), city.getLongitude());
        } catch (Exception exception) {
            log.error("Error while fetching weather details", exception);
        }
        return weather;
    }
}
