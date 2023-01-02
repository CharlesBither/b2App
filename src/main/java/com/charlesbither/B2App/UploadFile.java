package com.charlesbither.B2App;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class UploadFile {

    public void uploadFile(String path, File file) throws IOException {
        Reader reader = new Reader();
        StreamReader streamReader = new StreamReader();
        String sha1 = reader.getSha1(file);

        // Get upload params
        String uploadUrl = B2App.uploadUrl; // Provided by b2_get_upload_url
        String uploadAuthorizationToken = B2App.uploadAuthToken; // Provided by b2_get_upload_url
        HttpURLConnection connection = null;

        // Get file header info
        String fileName = path;
        fileName = fileName.replace("\\", "/");
        fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8);

        System.out.println(fileName);
        // Get file body
        byte[] fileData = Files.readAllBytes(file.toPath());
        System.out.println(file.toPath());
        System.out.println(fileData.length);

        try {
            URL url = new URL(uploadUrl);
            connection = (HttpURLConnection) url.openConnection();
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
