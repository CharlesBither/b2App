package com.charlesbither.B2App;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.List;

import static java.nio.file.StandardWatchEventKinds.*;

public class Watcher {

    public static HashMap<WatchKey, Path> keyMap = new HashMap<>();

    public void watch(WatchService watcher, Path dir) throws IOException {
        Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                WatchKey watchKey = dir.register(watcher, ENTRY_MODIFY, ENTRY_CREATE, ENTRY_DELETE);
                keyMap.put(watchKey, dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    public static void init(String path) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("New thread created");

                while (true) {
                    try {
                        // java watcher : create an instance of the watch Service
                        Path pathDirectory = Paths.get(path);
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
                        for (WatchEvent<?> event : eventsList) {
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


                                Path path = Watcher.keyMap.get(watchKey);
                                String fullPath = path.toString() + "\\" + fileWatched;
                                File file = new File(fullPath);
                                if(!file.isDirectory()) {
                                    long time = new File(fullPath).lastModified();
                                    System.out.println("File modified: " + fileWatched);
                                    System.out.println(time);
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Error: " + e.toString());
                    }
                }
            }
        }).start();
    }

}
