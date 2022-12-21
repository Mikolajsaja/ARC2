package com.example.demo.storage;


import com.example.demo.storage.exceptions.CouldNotAddFile;
import com.google.api.gax.paging.Page;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Service;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



@Service
public class MusicStorage {

    public void uploadFileToGcs(String projectId, String bucketName, String objectName, byte[] fileContents, String contentType){
        Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
        BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of(bucketName, objectName)).setContentType(contentType).build();
        try {
            storage.createFrom(blobInfo,new ByteArrayInputStream(fileContents));
        } catch (IOException e) {
            throw new CouldNotAddFile();
        }
    }

    public List<String> getFilesExtensions() throws IOException {
        Storage storage = StorageOptions.getDefaultInstance().getService();
        List<String> extensions = new ArrayList<>();

        Page<Blob> blobs = storage.list("arc2-370113.appspot.com",
                Storage.BlobListOption.prefix("userMusic/"),
                Storage.BlobListOption.currentDirectory());

        for (Blob blob : blobs.iterateAll()) {
            extensions.add(blob.getContentType());
        }
//        MultipartFile file = new MockMultipartFile();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);
        for (String element : extensions) {
            out.writeUTF(element);
        }
        byte[] bytes = baos.toByteArray();
        this.uploadFileToGcs("arc2-370113", "arc2-370113.appspot.com","extensions",bytes,"txt");
        return extensions;

    }
}