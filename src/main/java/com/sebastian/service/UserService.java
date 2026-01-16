package com.sebastian.service;

import java.util.ArrayList;
import java.util.List;

import com.sebastian.exception.PlaylistNotFoundException;
import com.sebastian.exception.SongNotFoundException;
import com.sebastian.exception.UserAlreadyExistsException;
import com.sebastian.exception.UserNotFoundException;
import com.sebastian.exception.UsernameAlreadyExistsException;
import com.sebastian.model.Playlist;
import com.sebastian.model.Song;
import com.sebastian.model.User;
import com.sebastian.repository.SongRepository;
import com.sebastian.repository.UserRepository;

public class UserService {
    private final UserRepository userRepo; // We make it final so it cannot be changed (the reference). We can still
                                           // change its internal data.
    private final SongRepository songRepo;

    // This is SOLID, we use Dependency Inversion Principle.
    public UserService(UserRepository userRepository, SongRepository songRepository) {
        this.userRepo = userRepository;
        this.songRepo = songRepository;
    }

    public void addUser(User user) {
        if (user == null)
            throw new IllegalArgumentException("The user can't be null");
        if ((user.getId() == null || user.getId().isBlank()))
            throw new IllegalArgumentException("The user id can't be null");
        if (userRepo.findById(user.getId()).isPresent())
            throw new UserAlreadyExistsException("The user already exists");

        boolean exists = userRepo.findAll().stream()
                .anyMatch(x -> x.getUsername().equalsIgnoreCase(user.getUsername()));

        if (exists)
            throw new UsernameAlreadyExistsException("The username is already in use");

        userRepo.save(user);
    }

    public User findById(String id) {
        if (id == null || id.isBlank())
            throw new IllegalArgumentException("The id cannot be null or blank");
        return userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("The user couldn't be found"));
    }

    public List<User> findAll() {
        return new ArrayList<>(userRepo.findAll()); // we also have the option to do the same as the repo with
                                                    // Collection<User>
    }

    public void updateUsername(String userId, String username) {
        if (username == null || username.isBlank())
            throw new IllegalArgumentException("The username cannot be null");
        if (userId == null || userId.isBlank())
            throw new IllegalArgumentException("The UserId Can't be null");

        User user = findById(userId);
        boolean exists = userRepo.findAll().stream()
                .anyMatch(x -> !x.getId().equals(userId) && x.getUsername().equalsIgnoreCase(username));

        if (exists)
            throw new UsernameAlreadyExistsException("The username is already in use");

        user.setUsername(username);
    }

    public List<Playlist> getLibrary(String id) {
        return new ArrayList<>(findById(id).getLibrary());
    }

    public Playlist findPlaylistById(String userId, String playlistId) {
        if (playlistId == null || playlistId.isBlank())
            throw new IllegalArgumentException("Playlist id cannot be null or blank");
        return findById(userId).getLibrary().stream().filter(x -> x.getId().equals(playlistId)).findFirst()
                .orElseThrow(() -> new PlaylistNotFoundException("The Playlist couldn't be found"));
    }

    

    public void addPlaylist(String userId, Playlist playlist) {
        if (userId == null || userId.isBlank())
            throw new IllegalArgumentException("The user id cannot be null");
        if (playlist == null)
            throw new IllegalArgumentException("The playlist cannot be null"); 
        User user = findById(userId); 

        user.addPlaylist(playlist);
    }

    public void removePlaylist(String userId, String playlistId) {
        if (userId == null || userId.isBlank())
            throw new IllegalArgumentException("The user id cannot be null");
        if (playlistId == null || playlistId.isBlank())
            throw new IllegalArgumentException("The playlist id cannot be null");

        User user = findById(userId);
        user.removePlaylist(playlistId);
    }

    public List<Song> getPlayListSongs(String userId, String playlistId) {
        return new ArrayList<>(findPlaylistById(userId, playlistId).getSongs());
    }

    public void addSongToPlaylist(String userId, String playlistId, String songId) {
        if (songId == null || songId.isBlank())
            throw new IllegalArgumentException("Song id cannot be null or blank");

        Playlist playlist = findPlaylistById(userId, playlistId);
        Song song = songRepo.findById(songId)
                .orElseThrow(() -> new SongNotFoundException("The song couldn't be found"));

        playlist.addSong(song);
    }

    public void removeSong(String userId, String playlistId, String songId) {
        findPlaylistById(userId, playlistId).removeSong(songId);
    }

}
