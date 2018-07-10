package com.material.utility;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class SetConnection {


    static String accept = "application/json";

    static String contentType = accept;

    static String authorizationKey;

    public HttpURLConnection requestGenericConfigurations(String toUrl, String method) throws IOException {

        URL url = new URL(toUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod(method);
        conn.setRequestProperty("Accept", accept);
        conn.setRequestProperty("Content-Type", contentType);
        return conn;
    }


}
