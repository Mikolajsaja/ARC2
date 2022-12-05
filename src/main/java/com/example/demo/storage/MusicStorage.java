package com.example.demo.storage;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.google.cloud.datastore.*;
import java.io.IOException;
import com.example.demo.UploadObjectFromMemory;

@Service
public class MusicStorage {

    private final Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    private final KeyFactory keyFactory = datastore.newKeyFactory().setKind("user");

    public boolean SetMusic (String username, MultipartFile file) throws IOException {
        UploadObjectFromMemory.uploadObjectFromMemory(
                "arc2-370113",
                "arc2-370113.appspot.com",
                "userMusic" + username,
                file.getBytes(),
                file.getContentType());
    return true;
    }
}
