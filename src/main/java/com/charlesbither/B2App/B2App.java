package com.charlesbither.B2App;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class B2App {

    static String APIUrl;
    static String authToken;
    static String bucketID = System.getenv("BUCKET_ID");
    static String uploadUrl;
    static String uploadAuthToken;
    static String srcPath = System.getenv("SRC_PATH");

    public static void main(String[] args) {

//        List<String> authList = Authorization.authorizationList();
//        APIUrl = authList.get(0);
//        authToken = authList.get(1);
//        UploadURL uploadURL = new UploadURL();
//
//        String[] arr = uploadURL.getUploadURL();
//        uploadUrl = arr[0];
//        uploadAuthToken = arr[1];
//
//        Reader reader = new Reader();
//        String pathStr = "C:\\Bucket";
//        reader.traverseDirs(pathStr, "");

        Watcher.init(srcPath);


        //Watcher watcher = new Watcher();
//        try {
//            //watcher.watch(dir);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
