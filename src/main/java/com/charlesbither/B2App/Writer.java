package com.charlesbither.B2App;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Writer {
    public void writeFile(List<String> list, String path) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            for(int i = 0; i < list.size(); i++) {
                if (i == 0) { writer.write(list.get(i)); }
                else {
                    writer.write("\n" + list.get(i));
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
