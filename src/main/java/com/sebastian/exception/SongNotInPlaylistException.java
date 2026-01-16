package com.sebastian.exception;

public class SongNotInPlaylistException extends RuntimeException{
    public SongNotInPlaylistException(String message) {
        super(message);
    }
}
