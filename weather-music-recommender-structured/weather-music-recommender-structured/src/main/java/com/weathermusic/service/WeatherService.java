package com.weathermusic.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.weathermusic.model.WeatherResponse;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String weatherApiKey;

    @Value("${weather.api.url}")
    private String weatherApiUrl;

    public WeatherResponse getWeatherData(String city) {
        try {
            // Build URL string
            String urlString = weatherApiUrl + "?q=" + city + "&appid=" + weatherApiKey;
            
            // Modern way: Use URI first, then URL
            URI uri = new URI(urlString);
            URL url = uri.toURL();
            
            // Open connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                
                return parseWeatherData(response.toString());
            } else {
                System.out.println("HTTP Error while fetching weather data: " + responseCode);
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error getting weather data: " + e.getMessage());
            return null;
        }
    }

    private WeatherResponse parseWeatherData(String weatherData) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(weatherData);

            // Extract weather condition
            JSONArray weatherArray = (JSONArray) jsonObject.get("weather");
            JSONObject weatherObject = (JSONObject) weatherArray.get(0);
            String mainWeather = (String) weatherObject.get("main");
            String description = (String) weatherObject.get("description");

            // Extract temperature and humidity
            JSONObject mainObject = (JSONObject) jsonObject.get("main");
            double tempKelvin = ((Number) mainObject.get("temp")).doubleValue();
            double tempCelsius = tempKelvin - 273.15;
            double tempFahrenheit = tempCelsius * 9 / 5 + 32;
            int humidity = ((Number) mainObject.get("humidity")).intValue();

            // Create and populate WeatherResponse object
            WeatherResponse response = new WeatherResponse();
            response.setCity(jsonObject.get("name").toString());
            response.setWeatherMain(mainWeather);
            response.setDescription(description);
            response.setTemperatureC(tempCelsius);
            response.setTemperatureF(tempFahrenheit);
            response.setHumidity(humidity);

            return response;
        } catch (Exception e) {
            System.out.println("Error parsing weather data: " + e.getMessage());
            return null;
        }
    }
}
