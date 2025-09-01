package com.weathermusic.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class MusicRecommendationService {
    
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
    
    public String[] getRecommendedGenres(String weatherCondition) {
        return weatherToGenre.getOrDefault(weatherCondition, new String[]{"Pop", "Rock", "Classical"});
    }
    
    public String selectRandomGenre(String[] genres) {
        Random random = new Random();
        return genres[random.nextInt(genres.length)];
    }
    
    public List<String> getTracksForGenre(String genre) {
        return genreToTracks.getOrDefault(genre, new ArrayList<>());
    }
}