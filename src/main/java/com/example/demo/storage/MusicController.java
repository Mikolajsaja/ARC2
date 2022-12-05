package com.example.demo.storage;


import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MusicController {

    private final MusicStorage musicStorage;

    public MusicController(MusicStorage musicStorage) {
        this.musicStorage = musicStorage;
    }

    @PostMapping(value = "/setUserData", consumes = MediaType.ALL_VALUE)
    public HttpStatus setUserData(
            @RequestParam("file") MultipartFile file) throws IOException {
        if (musicStorage.SetMusic(file)) return HttpStatus.OK;
        return HttpStatus.BAD_REQUEST;
    }
}
