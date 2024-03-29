package com.charlesbither.B2App;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class UploadURL {

    String accountToken = B2App.authToken;
    String apiUrl = B2App.APIUrl;
    String bucketId = B2App.bucketID;

    public String[] getUploadURL() {

        StreamReader streamReader = new StreamReader();

        HttpURLConnection connection = null;
        String postParams = "{\"bucketId\":\"" + bucketId + "\"}";

        byte[] postData = postParams.getBytes(StandardCharsets.UTF_8);
        try {
            URL url = new URL(apiUrl + "/b2api/v2/b2_get_upload_url");
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", accountToken);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("charset", "utf-8");

            System.out.println((postData.length));

            connection.setRequestProperty("Content-Length", Integer.toString(postData.length));
            connection.setDoOutput(true);
            DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
            writer.write(postData);
            String jsonResponse = streamReader.myInputStreamReader(connection.getInputStream());
            JSONObject json = new JSONObject(jsonResponse);
            String uploadUrl = json.getString("uploadUrl");
            String authorizationToken = json.getString("authorizationToken");

            String[] list = {uploadUrl, authorizationToken};
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }

        return null;
    }
}
