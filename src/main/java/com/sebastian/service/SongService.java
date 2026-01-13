package com.sebastian.service;

import com.sebastian.repository.SongRepository;

public class SongService {
    private final SongRepository songRepo;

    public SongService(SongRepository repo){
        songRepo = repo;
    }
    

}
