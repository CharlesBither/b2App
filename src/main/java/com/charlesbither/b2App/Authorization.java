package com.charlesbither.b2App;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Authorization {

    static public List<String> authorizationList() {
        String applicationKeyId = "004417531fd6d8c0000000002"; // Obtained from your B2 account page.
        String applicationKey = "K004Xn571hrcUVUIt+lJMT3JNN6sWxk"; // Obtained from your B2 account page.
        HttpURLConnection connection = null;
        String headerForAuthorizeAccount = "Basic " + Base64.getEncoder().encodeToString((applicationKeyId + ":" + applicationKey).getBytes());

        ArrayList<String> list = new ArrayList<>();

        try {
            URL url = new URL("https://api.backblazeb2.com/b2api/v2/b2_authorize_account");
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", headerForAuthorizeAccount);
            InputStream in = new BufferedInputStream(connection.getInputStream());
            String jsonResponse = myInputStreamReader(in);
            System.out.println(jsonResponse);

            //API URL & authorizationToken
            JSONObject json = new JSONObject(jsonResponse);
            String apiUrl = json.getString("apiUrl");
            String authorizationToken = json.getString("authorizationToken");


            list.add(apiUrl);
            list.add(authorizationToken);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return list;
    }

    static public String myInputStreamReader(InputStream in) throws IOException {
        InputStreamReader reader = new InputStreamReader(in);
        StringBuilder sb = new StringBuilder();
        int c = reader.read();
        while (c != -1) {
            sb.append((char)c);
            c = reader.read();
        }
        reader.close();
        return sb.toString();
    }

}
