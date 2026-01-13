package com.sebastian.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.sebastian.exception.SongAlreadyExistsException;
import com.sebastian.exception.SongNotFoundException;
import com.sebastian.model.Song;
import com.sebastian.repository.SongRepository;

public class SongService {
    private final SongRepository songRepo;

    public SongService(SongRepository repo){
        songRepo = repo;
    }

    public void addSong(Song song){
        if(song == null) throw new IllegalArgumentException("The song can't be null");
        if(songRepo.findById(song.getId()).isPresent()) throw new SongAlreadyExistsException("The song already exists");

        songRepo.save(song);
    }

    public Song findById(String id){
        if(id == null || id.isBlank()) throw new IllegalArgumentException("The id for the song cannot be null");
        
        return songRepo.findById(id).orElseThrow(() -> new SongNotFoundException("The song couldn't be found"));
    }

    public List<Song> findAll(){
        return new ArrayList<>(songRepo.findAll());
    }

    public List<Song> getSongsByGenre(String genre){
        if (genre == null || genre.isBlank()) throw new IllegalArgumentException("Genre cannot be null or blank");
        return findAll().stream().filter(x -> x.getGenre().equalsIgnoreCase(genre)).toList();
    }

    public List<Song> getSongsByArtist(String artist){
        if(artist == null || artist.isBlank()) throw new IllegalArgumentException("Artisg cannot be null or blank");
        return findAll().stream().filter(x -> x.getArtist().equalsIgnoreCase(artist)).toList();
    }
}
