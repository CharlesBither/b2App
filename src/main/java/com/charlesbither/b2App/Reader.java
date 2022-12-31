package com.charlesbither.b2App;

import org.apache.commons.codec.digest.DigestUtils;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    public String createSha1(File file) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            InputStream fis = new FileInputStream(file);
            //DigestInputStream dis = new DigestInputStream(fis, digest);

            String sha1 = DigestUtils.sha1Hex(fis);

            //return DigestUtils.sha1Hex(digest.digest());
            System.out.println(sha1);

            return sha1;
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] getFileData(File file) throws IOException {
        byte[] fileContent = Files.readAllBytes(file.toPath());
        return null;
    }

//    public void readImage() {
//        BufferedImage image = new BufferedImage()
//    }
}
