package com.sebastian.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Playlist {
    private final String id; 
    private String name; 
    private final Map<String, Song> songs = new HashMap<>();
    
    public Playlist(String id, String name) {
        if(id == null || id.isBlank()) throw new IllegalArgumentException("id can't be blank or null");
        if(name == null || name.isBlank()) throw new IllegalArgumentException("name can't be blank or null");
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        if(newName == null || newName.isBlank()) throw new IllegalArgumentException("name can't be blank or null");
        this.name = newName;
    }

    //Esto lo hacemos como recomendacion de GPT. Lo que buscamos es mantener un buen encapsulamiento (el caller no necesita saber que DS estamos usando, solo necesita los datos). Ademas, permite mayor flexibilidad en el contrato en caso de que el DS usado, cambie
    public Collection<Song> getSongs() {
        return Collections.unmodifiableCollection(songs.values());
    }

    public void addSong(Song song){
        if (song == null) {
            throw new IllegalArgumentException("song cannot be null");
        }
        if(songs.containsKey(song.getId())){
            throw new IllegalArgumentException("Song already exists");
        }
        songs.put(song.getId(), song);
    }

    public void removeSong(String idSong){
        if(idSong == null || idSong.isBlank()) throw new IllegalArgumentException("The song id can't be blank or null");
        songs.remove(idSong);
    }
}
