package com.charlesbither.B2App;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;

public class Reader {

    public String getSha1(File file) {
        try {
            InputStream fis = new FileInputStream(file);
            String sha1 = DigestUtils.sha1Hex(fis);
            System.out.println(sha1);

            return sha1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void traverseDirs(String path, String offset) {
        UploadFile uploadFile = new UploadFile();
        System.out.println("*******START RECURSIVE FUNCTION*******");
        File file = new File(path);
        if (file.exists() && file.isDirectory()) {
            File[] arr = file.listFiles();
            for (File f : arr) {
                String append = "\\" + f.getName();
                try {
                    if (f.isDirectory()) {
                        System.out.println(offset + "dir = " + append);
                        traverseDirs(path + append, offset + "-");
                    } else {
                        uploadFile.uploadFile(path + append, f);
                        System.out.println(offset + "file = " + f.getName());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("*******END RECURSIVE FUNCTION*******");
        }
    }
}
