package com.example.springbootspotify.controller;


import com.example.springbootspotify.models.Song;
import com.example.springbootspotify.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//import com.ga.entity.Song;
//import com.ga.service.SongService;

@RestController
@RequestMapping("/song")
public class SongController {
    @Autowired
    SongService songService;

    @PostMapping
    public Song createSong(@RequestBody Song song) {

        return songService.createSong(song);
    }

//    @DeleteMapping("/delete/{songId}")
//    public Song deleteSongById(@PathVariable Long songId){
//        return songService.deleteSong(songId);
//    }

//    @GetMapping("")
//    public List<Song> getAllSongs(){
//        return songService.getAllSongs();
//    }
    @GetMapping("/list")
    public Iterable<Song> listSongs(){

        return songService.listSongs();
    }
}

