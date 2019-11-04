package com.example.springbootspotify.controller;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.example.springbootspotify.models.Song;
import com.example.springbootspotify.service.SongService;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SongControllerTest {
    @InjectMocks
    SongController songController;

    @Mock
    SongService songService;

    private MockMvc mockMvc;
    private Song song;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(songController).build();
        song = new Song();
        song.setTitle("Song");
        song.setLength(10);
    }
    @Test
    public void getAllSongs_SongList_Success() throws Exception {
        List<Song> list = new ArrayList<>();
        list.add(song);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/song/list")
                .accept(MediaType.APPLICATION_JSON);

        when(songService.listSongs()).thenReturn(list);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json(  postJSON()  ));
    }
    private String postJSON() {
        return "{ \"length\": \"" + song.getLength() + "\", " + "\"title\": \"" + song.getTitle() + "\"}";
    }
}
