package com.charlesbither.b2App;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    public List<String> readFile(String path) {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line = reader.readLine();
            while(line != null) {
                list.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

//    public void readImage() {
//        BufferedImage image = new BufferedImage()
//    }
}
