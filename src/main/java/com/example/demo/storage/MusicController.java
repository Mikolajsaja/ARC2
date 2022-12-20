package com.example.demo.storage;


import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MusicController {

    private final MusicStorage musicStorage;

    public MusicController(MusicStorage musicStorage) {
        this.musicStorage = musicStorage;
    }

    @PostMapping(value = "/addData", consumes = MediaType.ALL_VALUE)
    public void setUserData(
            @RequestParam("file") MultipartFile file) throws IOException {
        musicStorage.setMusic(file);
    }


    @PostMapping("/getFileExtension")
    public List<String> getFileExtension(){
        return musicStorage.getFilesExtensions();
    }
}