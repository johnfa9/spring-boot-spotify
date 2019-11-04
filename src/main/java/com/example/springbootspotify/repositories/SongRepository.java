package com.example.springbootspotify.repositories;
import com.example.springbootspotify.models.Song;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SongRepository extends CrudRepository<Song, Integer> {
}