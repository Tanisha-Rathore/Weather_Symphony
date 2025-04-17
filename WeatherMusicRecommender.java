package weathermusic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class WeatherMusicRecommender {
    
    private static final String WEATHER_API_KEY = "261eb3ffddc30855ab65e7c583f051cc";
    private static final String WEATHER_API_URL = "https://api.openweathermap.org/data/2.5/weather";
    
    // Map to store weather conditions and corresponding music genres
    private static final Map<String, String[]> weatherToGenre = new HashMap<>();
    
    // Map to store genres and sample tracks
    private static final Map<String, List<String>> genreToTracks = new HashMap<>();
    
    static {
        // Initialize weather to genre mappings
        weatherToGenre.put("Clear", new String[]{"Pop", "Acoustic", "Indie"});
        weatherToGenre.put("Clouds", new String[]{"Alternative", "Jazz", "Lo-fi"});
        weatherToGenre.put("Rain", new String[]{"Blues", "Ambient", "Classical"});
        weatherToGenre.put("Thunderstorm", new String[]{"Rock", "Metal", "Electronic"});
        weatherToGenre.put("Snow", new String[]{"Indie Folk", "Acoustic", "Classical"});
        weatherToGenre.put("Mist", new String[]{"Ambient", "Trip-hop", "Chillout"});
        weatherToGenre.put("Fog", new String[]{"Ambient", "Trip-hop", "Chillout"});
        weatherToGenre.put("Haze", new String[]{"Ambient", "Trip-hop", "Chillout"});
        
        // Initialize genre to tracks mappings
        initializeGenreToTracks();
    }
    
    private static void initializeGenreToTracks() {
        // Pop music
        List<String> popTracks = new ArrayList<>();
        popTracks.add("Taylor Swift - Cruel Summer");
        popTracks.add("Dua Lipa - Levitating");
        popTracks.add("The Weeknd - Blinding Lights");
        popTracks.add("Harry Styles - As It Was");
        popTracks.add("Olivia Rodrigo - good 4 u");
        genreToTracks.put("Pop", popTracks);
        
        // Acoustic
        List<String> acousticTracks = new ArrayList<>();
        acousticTracks.add("Ed Sheeran - Photograph");
        acousticTracks.add("Bon Iver - Skinny Love");
        acousticTracks.add("Jack Johnson - Better Together");
        acousticTracks.add("Vance Joy - Riptide");
        acousticTracks.add("Jason Mraz - I'm Yours");
        genreToTracks.put("Acoustic", acousticTracks);
        
        // Indie
        List<String> indieTracks = new ArrayList<>();
        indieTracks.add("Arctic Monkeys - 505");
        indieTracks.add("The Killers - Mr. Brightside");
        indieTracks.add("Tame Impala - The Less I Know The Better");
        indieTracks.add("Arcade Fire - Wake Up");
        indieTracks.add("Vampire Weekend - A-Punk");
        genreToTracks.put("Indie", indieTracks);
        
        // Alternative
        List<String> alternativeTracks = new ArrayList<>();
        alternativeTracks.add("Radiohead - Karma Police");
        alternativeTracks.add("The Strokes - Last Nite");
        alternativeTracks.add("Nirvana - Come As You Are");
        alternativeTracks.add("Red Hot Chili Peppers - Under The Bridge");
        alternativeTracks.add("Cage The Elephant - Ain't No Rest For The Wicked");
        genreToTracks.put("Alternative", alternativeTracks);
        
        // Jazz
        List<String> jazzTracks = new ArrayList<>();
        jazzTracks.add("Miles Davis - So What");
        jazzTracks.add("John Coltrane - My Favorite Things");
        jazzTracks.add("Dave Brubeck - Take Five");
        jazzTracks.add("Thelonious Monk - 'Round Midnight");
        jazzTracks.add("Ella Fitzgerald - Summertime");
        genreToTracks.put("Jazz", jazzTracks);
        
        // Lo-fi
        List<String> lofiTracks = new ArrayList<>();
        lofiTracks.add("Nujabes - Feather");
        lofiTracks.add("Kupla - Owls of the Night");
        lofiTracks.add("Quickly, Quickly - Hushed");
        lofiTracks.add("Idealism - Phosphenes");
        lofiTracks.add("Potsu - just friends");
        genreToTracks.put("Lo-fi", lofiTracks);
        
        // Blues
        List<String> bluesTracks = new ArrayList<>();
        bluesTracks.add("B.B. King - The Thrill Is Gone");
        bluesTracks.add("Muddy Waters - Mannish Boy");
        bluesTracks.add("Stevie Ray Vaughan - Pride and Joy");
        bluesTracks.add("Gary Clark Jr. - When My Train Pulls In");
        bluesTracks.add("Howlin' Wolf - Smokestack Lightnin'");
        genreToTracks.put("Blues", bluesTracks);
        
        // Ambient
        List<String> ambientTracks = new ArrayList<>();
        ambientTracks.add("Brian Eno - Ambient 1: Music for Airports");
        ambientTracks.add("Aphex Twin - Selected Ambient Works 85-92");
        ambientTracks.add("Boards of Canada - Dayvan Cowboy");
        ambientTracks.add("Stars of the Lid - Requiem for Dying Mothers");
        ambientTracks.add("Tycho - Awake");
        genreToTracks.put("Ambient", ambientTracks);
        
        // Classical
        List<String> classicalTracks = new ArrayList<>();
        classicalTracks.add("Ludwig van Beethoven - Moonlight Sonata");
        classicalTracks.add("Claude Debussy - Clair de Lune");
        classicalTracks.add("Johann Sebastian Bach - Air on the G String");
        classicalTracks.add("Erik Satie - Gymnopédie No.1");
        classicalTracks.add("Antonio Vivaldi - The Four Seasons");
        genreToTracks.put("Classical", classicalTracks);
        
        // Rock
        List<String> rockTracks = new ArrayList<>();
        rockTracks.add("Led Zeppelin - Stairway to Heaven");
        rockTracks.add("Queen - Bohemian Rhapsody");
        rockTracks.add("The Rolling Stones - Paint It Black");
        rockTracks.add("The Who - Baba O'Riley");
        rockTracks.add("AC/DC - Back In Black");
        genreToTracks.put("Rock", rockTracks);
        
        // Metal
        List<String> metalTracks = new ArrayList<>();
        metalTracks.add("Metallica - Enter Sandman");
        metalTracks.add("Iron Maiden - The Trooper");
        metalTracks.add("Black Sabbath - Paranoid");
        metalTracks.add("Slipknot - Duality");
        metalTracks.add("System of a Down - Chop Suey!");
        genreToTracks.put("Metal", metalTracks);
        
        // Electronic
        List<String> electronicTracks = new ArrayList<>();
        electronicTracks.add("Daft Punk - Get Lucky");
        electronicTracks.add("The Chemical Brothers - Hey Boy Hey Girl");
        electronicTracks.add("Justice - D.A.N.C.E.");
        electronicTracks.add("Deadmau5 - Strobe");
        electronicTracks.add("Aphex Twin - Windowlicker");
        genreToTracks.put("Electronic", electronicTracks);
        
        // Indie Folk
        List<String> indieFolkTracks = new ArrayList<>();
        indieFolkTracks.add("Fleet Foxes - White Winter Hymnal");
        indieFolkTracks.add("Sufjan Stevens - Mystery of Love");
        indieFolkTracks.add("Bon Iver - Holocene");
        indieFolkTracks.add("The Lumineers - Ho Hey");
        indieFolkTracks.add("Mumford & Sons - I Will Wait");
        genreToTracks.put("Indie Folk", indieFolkTracks);
        
        // Trip-hop
        List<String> triphopTracks = new ArrayList<>();
        triphopTracks.add("Massive Attack - Teardrop");
        triphopTracks.add("Portishead - Glory Box");
        triphopTracks.add("Tricky - Vent");
        triphopTracks.add("DJ Shadow - Midnight In A Perfect World");
        triphopTracks.add("UNKLE - Rabbit in Your Headlights");
        genreToTracks.put("Trip-hop", triphopTracks);
        
        // Chillout
        List<String> chilloutTracks = new ArrayList<>();
        chilloutTracks.add("Zero 7 - Destiny");
        chilloutTracks.add("Air - La Femme d'Argent");
        chilloutTracks.add("Bonobo - Kerala");
        chilloutTracks.add("Thievery Corporation - Lebanese Blonde");
        chilloutTracks.add("Nightmares on Wax - You Wish");
        genreToTracks.put("Chillout", chilloutTracks);
    }
    
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Weather-Based Music Recommender");
            System.out.println("-------------------------------");
            
            System.out.print("Enter your city: ");
            String city = scanner.nextLine();
            
            // Get weather data
            String weatherData = getWeatherData(city);
            if (weatherData == null) {
                System.out.println("Failed to get weather data. Please check your API key and city name.");
                return;
            }
            
            // Parse weather data
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(weatherData);
            
            // Extract weather condition
            JSONArray weatherArray = (JSONArray) jsonObject.get("weather");
            JSONObject weatherObject = (JSONObject) weatherArray.get(0);
            String mainWeather = (String) weatherObject.get("main");
            String description = (String) weatherObject.get("description");
            
            // Extract temperature
            JSONObject mainObject = (JSONObject) jsonObject.get("main");
            double tempKelvin = ((Number) mainObject.get("temp")).doubleValue();
            double tempCelsius = tempKelvin - 273.15;
            double tempFahrenheit = tempCelsius * 9/5 + 32;
            
            // Display current weather
            System.out.println("\nCurrent Weather in " + city + ":");
            System.out.println("Weather: " + mainWeather + " (" + description + ")");
            System.out.println("Temperature: " + String.format("%.1f", tempCelsius) + "°C / " + 
                               String.format("%.1f", tempFahrenheit) + "°F");
            
            // Recommend music based on weather
            System.out.println("\nRecommended Music for " + mainWeather + " weather:");
            String[] recommendedGenres = weatherToGenre.getOrDefault(mainWeather, new String[]{"Pop", "Rock", "Classical"});
            
            // Choose a random genre from the recommended ones
            Random random = new Random();
            String selectedGenre = recommendedGenres[random.nextInt(recommendedGenres.length)];
            
            System.out.println("Selected Genre: " + selectedGenre);
            System.out.println("\nRecommended " + selectedGenre + " Playlist:");
            
            List<String> tracks = genreToTracks.getOrDefault(selectedGenre, new ArrayList<>());
            if (tracks.isEmpty()) {
                System.out.println("No tracks found for this genre.");
            } else {
                for (int i = 0; i < tracks.size(); i++) {
                    System.out.println((i + 1) + ". " + tracks.get(i));
                }
            }
            
            scanner.close();
            
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static String getWeatherData(String city) {
        try {
            String urlString = WEATHER_API_URL + "?q=" + city + "&appid=" + WEATHER_API_KEY;
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                
                return response.toString();
            } else {
                System.out.println("HTTP Error: " + responseCode);
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error getting weather data: " + e.getMessage());
            return null;
        }
    }
}