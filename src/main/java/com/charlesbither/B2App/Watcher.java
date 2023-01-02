package com.charlesbither.B2App;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.StandardWatchEventKinds.*;

public class Watcher {

    public void watch(WatchService watcher, Path dir) throws IOException {
        Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                dir.register(watcher, ENTRY_MODIFY, ENTRY_CREATE, ENTRY_DELETE);
                return FileVisitResult.CONTINUE;
            }
        });
    }

}
