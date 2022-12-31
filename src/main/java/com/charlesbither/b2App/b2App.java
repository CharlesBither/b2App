package com.charlesbither.b2App;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class b2App {

    static String APIUrl;
    static String authToken;
    static String bucketID = System.getenv("BUCKET_ID");

    public static void main(String[] args) {

        UploadFile uploadFile = new UploadFile();
        Reader reader = new Reader();

        List<String> authList = Authorization.authorizationList();
        APIUrl = authList.get(0);
        authToken = authList.get(1);

        System.out.println("url = " + APIUrl);
        System.out.println("token = " + authToken);

        File file = new File("hello.txt");
        try {
            uploadFile.uploadFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}
