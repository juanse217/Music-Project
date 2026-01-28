package com.sebastian.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.sebastian.exception.SongAlreadyExistsException;
import com.sebastian.exception.SongNotFoundException;
import com.sebastian.model.Song;
import com.sebastian.repository.SongRepository;

public class SongService {
    private final SongRepository songRepo;

    public SongService(SongRepository repo) {
        if (repo == null)
            throw new IllegalArgumentException("The repo cannot be null");
        songRepo = repo;
    }

    public void addSong(Song song) {
        if (song == null)
            throw new IllegalArgumentException("The song can't be null");
        if (song.getId() == null || song.getId().isBlank())
            throw new IllegalArgumentException("The song id cannot be null or blank"); // each layer has to protect itself at its boundary, even though the Song class already checks for null, the service needs to protects itself.
        if (songRepo.findById(song.getId()).isPresent())
            throw new SongAlreadyExistsException("The song already exists");

        songRepo.save(song);
    }

    public Song findById(String id) {
        if (id == null || id.isBlank())
            throw new IllegalArgumentException("The song id cannot be null");

        return songRepo.findById(id).orElseThrow(() -> new SongNotFoundException("The song couldn't be found"));
    }

    public List<Song> findAll() {
        return new ArrayList<>(songRepo.findAll());
    }

    private List<Song> filter(Predicate<Song> predicate) { // We use this method to avoid repeating ourselves in the
                                                           // genre and artist methods.
        return findAll().stream().filter(predicate).toList();
    }

    public List<Song> getSongsByGenre(String genre) {
        if (genre == null || genre.isBlank())
            throw new IllegalArgumentException("Genre cannot be null or blank");
        return filter(x -> x.getGenre().equalsIgnoreCase(genre));
    }

    public List<Song> getSongsByArtist(String artist) {
        if (artist == null || artist.isBlank())
            throw new IllegalArgumentException("Artist cannot be null or blank");
        return filter(x -> x.getArtist().equalsIgnoreCase(artist));
    }
}
