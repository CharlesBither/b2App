package com.charlesbither.B2App;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;


public class UploadFile {

    public void uploadFile(File file) throws IOException {

        Reader reader = new Reader();
        StreamReader streamReader = new StreamReader();
        String sha1 = reader.createSha1(file);

        // Get upload params
        UploadURL uploadURL = new UploadURL();
        List<String> uploadList = uploadURL.getUploadURL();
        String uploadUrl = uploadList.get(0); // Provided by b2_get_upload_url
        String uploadAuthorizationToken = uploadList.get(1); // Provided by b2_get_upload_url
        HttpURLConnection connection = null;

        // Get file header info
        String fileName = file.getName(); // The name of the file you are uploading

        // Get file body
        byte[] fileData = Files.readAllBytes(file.toPath());

        try {
            URL url = new URL(uploadUrl);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", uploadAuthorizationToken);
            connection.setRequestProperty("X-Bz-File-Name", fileName);
            connection.setRequestProperty("Content-Type", "b2/x-auto");
            connection.setRequestProperty("X-Bz-Content-Sha1", sha1);
            connection.setRequestProperty("X-Bz-Info-Author", "unknown");
            connection.setRequestProperty("X-Bz-Server-Side-Encryption", "AES256");
            connection.setDoOutput(true);
            DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
            writer.write(fileData);

            String jsonResponse = streamReader.myInputStreamReader(connection.getInputStream());
            System.out.println(jsonResponse);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }


    }


}
