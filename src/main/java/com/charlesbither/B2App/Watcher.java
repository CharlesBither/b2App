package com.charlesbither.B2App;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;

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

}
