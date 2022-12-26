package com.charlesbither.b2App;

import java.util.ArrayList;
import java.util.List;

public class b2App {


    public static void main(String[] args) {

//        List<String> authList = Authorization.authorizationList();
//        String APIUrl = authList.get(0);
//        String token = authList.get(1);
//
//        System.out.println("url = " + APIUrl);
//        System.out.println("token = " + token);
//
//        UploadURL uploadURL = new UploadURL();
//        List<String> uploadList = uploadURL.getUploadURL(APIUrl, token, System.getenv("BUCKET_ID"));
//
//        System.out.println(uploadList.get(0));
//        System.out.println(uploadList.get(1));

        Reader reader = new Reader();
        Writer writer = new Writer();
        List<String> list = reader.readFile(System.getProperty("user.home") + "\\Backblaze\\DocumentsB2\\test.txt");
        for(String line : list) {
            System.out.println(line);
        }
        writer.writeFile(list, System.getProperty("user.home") + "\\Backblaze\\DocumentsB2\\test1.txt");
    }

}
