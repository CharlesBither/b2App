package com.charlesbither.B2App;

import java.util.List;

public class B2App {

    static String APIUrl;
    static String authToken;
    static String bucketID = System.getenv("BUCKET_ID");
    static String uploadUrl;
    static String uploadAuthToken;

    public static void main(String[] args) {

        List<String> authList = Authorization.authorizationList();
        APIUrl = authList.get(0);
        authToken = authList.get(1);
        UploadURL uploadURL = new UploadURL();

        String[] arr = uploadURL.getUploadURL();
        uploadUrl = arr[0];
        uploadAuthToken = arr[1];

        Reader reader = new Reader();
        String path = "C:\\Bucket";
        reader.traverseDirs(path, "");
    }
}
