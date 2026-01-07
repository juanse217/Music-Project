package com.sebastian.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class User {
    private final String id;
    private String username;
    private final Map<String, Playlist> library = new HashMap<>(); //Usamos final para que no se pueda REASIGNAR y asi evitar bugs o errores. Nos aseguramos de que se mantenga la misma referencia siempre (no afecta la posibilidad de agregar playlists). Inicializamos de una vez para no depender del constructor. 

    public User(String id, String username) {
        if(id == null || id.isBlank()) throw new IllegalArgumentException("id can't be blank or null");
        if(username == null || username.isBlank()) throw new IllegalArgumentException("name can't be blank or null");
        this.id = id;
        this.username = username;
    }

    public String getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if(username == null || username.isBlank()) throw new IllegalArgumentException("name can't be blank or null");
        this.username = username; 
    }

    public Collection<Playlist> getLibrary() {
        return Collections.unmodifiableCollection(library.values());
    }

    public void addPlaylist(Playlist newPlaylist){
        if(newPlaylist == null) throw new IllegalArgumentException("The playlist can't be null");
        if(library.containsKey(newPlaylist.getId())) throw new IllegalArgumentException("The playlist already exists");
        library.put(newPlaylist.getId(), newPlaylist);
    }

    public void removePlaylist(String playlistId){
        library.remove(playlistId);
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", library=" + library.size() + "]";
    }

    
}
