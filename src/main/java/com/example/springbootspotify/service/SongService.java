package com.example.springbootspotify.service;
import com.example.springbootspotify.models.Song;
public interface SongService {
    public Song createSong(Song song);
    public Iterable<Song> listSongs();
    public void deleteSong(String username, Integer id);
}







