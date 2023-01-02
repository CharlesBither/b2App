package com.charlesbither.B2App;

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

        Path dir = Path.of("C:\\Users\\bithe\\Buckets");

        while (true) {
            try {
// java watcher : create an instance of the watch Service
                Path pathDirectory = Paths.get("C:\\Users\\bithe\\Buckets");
                WatchService watcher = pathDirectory.getFileSystem().newWatchService();
// java watcher : register the events that are to be notified.
                //pathDirectory.register(watcher, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,StandardWatchEventKinds.ENTRY_MODIFY);
                Watcher watcherClass = new Watcher();
                watcherClass.watch(watcher, pathDirectory);
// java watcher : watch key
                WatchKey watchKey = watcher.take();
// java watcher : enter the events into a list
                List<WatchEvent<?>> eventsList = watchKey.pollEvents();
//  for all events create a loop that iterates till the end of the
// list
                for (WatchEvent event : eventsList) {
//  get the file name for the event
                    Path fileWatched = (Path) event.context();
                    if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
//file is created
                        System.out.println("File created: " + fileWatched);
                    }
// file is deleted
                    if (event.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
                        System.out.println("File deleted: " + fileWatched);
                    }
//  file is modified.
                    if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                        System.out.println("File modified: " + fileWatched);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.toString());
            }
        }

        //Watcher watcher = new Watcher();
//        try {
//            //watcher.watch(dir);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
