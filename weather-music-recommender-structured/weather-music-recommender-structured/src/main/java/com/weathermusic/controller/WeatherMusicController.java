package com.weathermusic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weathermusic.model.WeatherResponse;
import com.weathermusic.service.MusicRecommendationService;
import com.weathermusic.service.WeatherService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WeatherMusicController {
    
    @Autowired
    private WeatherService weatherService;
    
    @Autowired
    private MusicRecommendationService musicService;
    
    @GetMapping("/")
    public String index() {
        return "index";
    }
    
    @PostMapping("/recommend")
    public String getRecommendation(@RequestParam("city") String city, Model model) {
        WeatherResponse weatherData = weatherService.getWeatherData(city);
        
        if (weatherData == null) {
            model.addAttribute("error", "Failed to get weather data. Please check the city name and try again.");
            return "index";
        }
        
        String[] recommendedGenres = musicService.getRecommendedGenres(weatherData.getWeatherMain());
        String selectedGenre = musicService.selectRandomGenre(recommendedGenres);
        List<String> tracks = musicService.getTracksForGenre(selectedGenre);
        
        model.addAttribute("weather", weatherData);
        model.addAttribute("genres", recommendedGenres);
        model.addAttribute("selectedGenre", selectedGenre);
        model.addAttribute("tracks", tracks);
        
        return "result";
    }
    
    @GetMapping("/api/recommend")
    @ResponseBody
    public Map<String, Object> getRecommendationApi(@RequestParam("city") String city) {
        WeatherResponse weatherData = weatherService.getWeatherData(city);
        
        Map<String, Object> response = new HashMap<>();
        
        if (weatherData == null) {
            response.put("error", "Failed to get weather data. Please check the city name and try again.");
            return response;
        }
        
        String[] recommendedGenres = musicService.getRecommendedGenres(weatherData.getWeatherMain());
        String selectedGenre = musicService.selectRandomGenre(recommendedGenres);
        List<String> tracks = musicService.getTracksForGenre(selectedGenre);
        
        response.put("city", weatherData.getCity());
        response.put("weather", weatherData.getWeatherMain());
        response.put("description", weatherData.getDescription());
        response.put("temperatureC", weatherData.getTemperatureC());
        response.put("temperatureF", weatherData.getTemperatureF());
        response.put("humidity", weatherData.getHumidity());
        response.put("genre", selectedGenre);
        response.put("tracks", tracks);
        
        return response;
    }
}