package com.charlesbither.B2App;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;

public class Reader {

    public String createSha1(File file) {
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
}
