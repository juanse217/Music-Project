package com.sebastian.model;

public class Song {
    private final String id; 
    private final String artist; 
    private final String name; 
    private final String genre; 
    private final int lengthInSeconds;

    
    public Song(String id, String artist, String name, String genre, int lengthInSeconds) {
        if(id == null || id.isBlank()) throw new IllegalArgumentException("id cannot be null or blank");
        if(artist == null || artist.isBlank()) throw new IllegalArgumentException("artist cannot be null or blank");
        if(name == null || name.isBlank()) throw new IllegalArgumentException("name cannot be null or blank");
        if(genre == null || genre.isBlank()) throw new IllegalArgumentException("genre cannot be null or blank");
        if(lengthInSeconds <= 0) throw new IllegalArgumentException("The length cant' be less than 1s");
        this.id = id;
        this.artist = artist;
        this.name = name;
        this.genre = genre;
        this.lengthInSeconds = lengthInSeconds;
    }

    public String getId() {
        return id;
    }
    
    public String getArtist() {
        return artist;
    }
    
    public String getName() {
        return name;
    }
    
    public String getGenre() {
        return genre;
    }
    
    public double getLengthInSeconds() {
        return lengthInSeconds;
    }

    @Override
    public String toString() {
        return "Song" + name + " - " + artist; 
    }

    
}
