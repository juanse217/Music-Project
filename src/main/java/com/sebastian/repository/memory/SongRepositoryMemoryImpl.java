package com.sebastian.repository.memory;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.sebastian.model.Song;
import com.sebastian.repository.SongRepository;

public class SongRepositoryMemoryImpl implements SongRepository {

    private final Map<String, Song> songs = new HashMap<>(); 

    @Override
    public void save(Song song) {
        if(song == null) throw new IllegalArgumentException("The song can't be null");
        songs.put(song.getId(), song);
    }

    @Override
    public Optional<Song> findById(String id) {
        if(id == null || id.isBlank()) return Optional.empty();
        return Optional.ofNullable(songs.get(id));
    }

    @Override
    public Collection<Song> findAll() {
        return Collections.unmodifiableCollection(songs.values());
    }

}
