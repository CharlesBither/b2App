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

    public static List<String> authorizationList() {
        String applicationKeyId = System.getenv("APPLICATION_KEY_ID"); // Obtained from your B2 account page.
        String applicationKey = System.getenv("APPLICATION_KEY"); // Obtained from your B2 account page.
        HttpURLConnection connection = null;
        String headerForAuthorizeAccount = "Basic " + Base64.getEncoder().encodeToString((applicationKeyId + ":" + applicationKey).getBytes());

        ArrayList<String> list = new ArrayList<>();
        StreamReader streamReader = new StreamReader();

        try {
            URL url = new URL("https://api.backblazeb2.com/b2api/v2/b2_authorize_account");
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", headerForAuthorizeAccount);
            InputStream in = new BufferedInputStream(connection.getInputStream());
            String jsonResponse = streamReader.myInputStreamReader(in);

            //API URL & authorizationToken
            JSONObject json = new JSONObject(jsonResponse);
            //System.out.println(jsonResponse);
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

}
