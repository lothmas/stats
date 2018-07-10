package com.material.utility;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.logging.Logger;

public class JsonMessageSender {

    Logger serviceRequest = Logger.getLogger(this.getClass().getName());
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    JsonParser jp = new JsonParser();

    public JsonMessageSender() {
    }

    public String sendGETMessage(HttpURLConnection conn) {
        StringBuffer outputAppended = new StringBuffer();
        String output = null;

        try {

            if (conn.getResponseCode() != 200) {
                errorResponseLogger(conn);
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            while ((output = br.readLine()) != null) {
                outputAppended.append(output);
            }
            conn.disconnect();
            return outputAppended.toString();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return outputAppended.toString();
    }

    public String sendDataMessage(HttpURLConnection conn, String jsonRequest) {
        StringBuffer outputAppended = new StringBuffer();
        int res = 0;
        try {
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write(jsonRequest);
            out.flush();
            out.close();

            res = conn.getResponseCode();

            if (res > 300) {
                errorResponseLogger(conn);
            }

            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = br.readLine()) != null) {
                outputAppended.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        conn.disconnect();
        return outputAppended.toString();

    }

    private void errorResponseLogger(HttpURLConnection conn) throws IOException {
        StringBuffer outputAppended = new StringBuffer();
        InputStream is = conn.getErrorStream();
        BufferedReader error = new BufferedReader(new InputStreamReader(is));
        String line = null;

        while ((line = error.readLine()) != null) {
            outputAppended.append(line);
        }

        try {
            JsonElement je = jp.parse(outputAppended.toString());
            String prettyJsonString = gson.toJson(je);
//            serviceRequest.fatal("Failed : HTTP error code : "
//                    + conn.getResponseCode() + " error message: " + conn.getResponseMessage() + "\n" + prettyJsonString);
        } catch (Exception exp) {
         //   serviceRequest.error(exp.getMessage() + outputAppended.toString());
        }
    }
}
