package com.example.springbootspotify.service;

import com.example.springbootspotify.models.Song;
import com.example.springbootspotify.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
public class SongServiceImpl implements SongService {
    @Autowired
    SongRepository songRepository;

    @Override
    public Song createSong(Song song) {
        return songRepository.save(song);
    }

    @Override
    public Iterable<Song> listSongs(){
        return songRepository.findAll();
    }

    @Override
    public void deleteSong(String username, Integer id) {
        songRepository.deleteById(id);
    }
}