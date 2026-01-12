package com.sebastian.repository;

import java.util.Collection;
import java.util.Optional;

import com.sebastian.model.Song;

public interface SongRepository {
    void save(Song song); 
    Optional<Song> findById(String id); //It's better to use the ID, we need to ask what happens if 2 songs share name. 
    //List<Song> findByArtist(String artistName);
    //List<Song> findByGenre(String Genre); These methods will be added in the serivce layer. 
    Collection<Song> findAll();
}
