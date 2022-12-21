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

    @PostMapping(value = "/uploadFile", consumes = MediaType.ALL_VALUE)
    public void addFile(@RequestParam("file") MultipartFile file) throws IOException {
        musicStorage.uploadFileToGcs( "arc2-370113", "arc2-370113.appspot.com","userMusic/"+file.getName(),file.getBytes(),file.getContentType());
    }


    @PostMapping("/filesExtensions")
    public List<String> getFileExtension() throws IOException {
        return musicStorage.getFilesExtensions();
    }
    }
