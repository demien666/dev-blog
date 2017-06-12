package com.demien.sparktest.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class RestTestUtil {

    public static class RequestResult {

        public final String body;
        public final int status;

        private RequestResult(int status, String body) {
            this.body = body;
            this.status = status;
        }
    }

    public static RequestResult sendRequest(String method, String path, String urlParameters) throws IOException {

        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setInstanceFollowRedirects(false);
        conn.setRequestMethod(method);

        if (urlParameters!=null) {
            byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
            int postDataLength = postData.length;

            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
            conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
            conn.setUseCaches(false);
            conn.getOutputStream().write(postData);
        }

        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (int c; (c = in.read()) >= 0; )
            sb.append((char) c);
        String responseBody = sb.toString();
        int responseCode=conn.getResponseCode();
        return new RequestResult(responseCode, responseBody);

    }

    public static RequestResult sendRequest(String method, String path) throws IOException {
        return sendRequest(method, path, null);
    }


}
