package com.example.demo.storage;


import com.example.demo.storage.exceptions.WrongFileExtension;
import com.google.api.gax.paging.Page;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.google.cloud.datastore.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class MusicStorage {

    private final Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    private final KeyFactory keyFactory = datastore.newKeyFactory().setKind("user");

    public void setMusic (MultipartFile file) throws IOException {
        String extension = Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[1];
        if(extension.equals("audio/mpeg")){
            Storage storage = StorageOptions.newBuilder().setProjectId("arc2-370113").build().getService();
            BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of("arc2-370113.appspot.com", "userMusic")).setContentType(extension).build();
            try {
                storage.createFrom(blobInfo,new ByteArrayInputStream(file.getBytes()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            throw new WrongFileExtension();
        }

    }

    public List<String> getFilesExtensions(){
        Storage storage = StorageOptions.getDefaultInstance().getService();
        List<String> extensions = new ArrayList<>();

        Page<Blob> blobs = storage.list("arc2-370113.appspot.com");

        for (Blob blob : blobs.iterateAll()) {
            extensions.add(blob.getContentType());
        }
        return extensions;

    }
}