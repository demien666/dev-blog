package com.demien.ssecurity.testapp.utils;

import java.io.BufferedReader;

import org.apache.http.NameValuePair;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class RestTestClient {

    public static final int RESPONSE_OK = 200;

    public String getResponseData(HttpResponse response)
            throws Exception {
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            throw new Exception("Exception in getResponseData:"
                    + e.getMessage() + ".");
        }
        return result.toString();
    }

    @SuppressWarnings("rawtypes")
    public Object Json2Object(String src, Class cl, boolean asList) throws JsonHelper.JsonHelperException {
        Object result = null;
        if (asList) {
            result = JsonHelper.Json2ObjectList(src, cl);
        } else {
            result = JsonHelper.Json2Object(src, cl);
        }
        return result;
    }

    public HttpResponse sendGet(String url) throws ClientProtocolException,
            IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(url);
        get.setHeader(
                "Accept",
                "text/html,application/xhtml+xml,application/xml,application/json;q=0.9,*/*;q=0.8");
        get.setHeader("Content-Type", "application/json");
        HttpResponse response = client.execute(get);

        return response;
    }

    public String sendGetStringResult(String url) throws Exception {
        return getResponseData(sendGet(url));
    }

    @SuppressWarnings("rawtypes")
    public Object sendGetObjectResult(String url, Class cl) throws Exception {
        return sendGetObjectResult(url, cl, false);
    }

    @SuppressWarnings("rawtypes")
    public Object sendGetObjectResult(String url, Class cl, boolean asList)
            throws Exception {
        String src = sendGetStringResult(url);
        Object result = null;
        if (asList) {
            result = JsonHelper.Json2ObjectList(src, cl);
        } else {
            result = JsonHelper.Json2Object(src, cl);
        }
        return result;
    }

    public HttpResponse sendPost(String url, List<NameValuePair> postParams, Object object) throws Exception {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);

        post.setHeader("Accept",
                "text/html,application/xhtml+xml,application/xml, application/json;q=0.9,*/*;q=0.8");

        if (postParams != null) {
            post.setEntity(new UrlEncodedFormEntity(postParams));
            post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        }
        if (object != null) {
            String json = JsonHelper.object2json(object);
            StringEntity input = new StringEntity(json);
            input.setContentType("application/json");
            post.setEntity(input);
            post.setHeader("Content-Type", "application/json");

        }

        HttpResponse response = client.execute(post);

        return response;
    }

    public HttpResponse sendPost(String url, List<NameValuePair> postParams)
            throws Exception {
        return sendPost(url, postParams, null);
    }

    public HttpResponse sendPost(String url, Object object) throws Exception {
        return sendPost(url, null, object);
    }

    public String sendPostStringResult(String url,
                                       List<NameValuePair> postParams) throws IllegalStateException,
            IOException, Exception {
        return getResponseData(sendPost(url, postParams));
    }

    @SuppressWarnings("rawtypes")
    public Object sendPostObjectResult(String url,
                                       List<NameValuePair> postParams, Class cl)
            throws IllegalStateException, IOException, Exception {
        return sendPostObjectResult(url, postParams, cl, false);
    }

    @SuppressWarnings("rawtypes")
    public Object sendPostObjectResult(String url,
                                       List<NameValuePair> postParams, Class cl, boolean asArray)
            throws IllegalStateException, IOException, Exception {
        String src = sendPostStringResult(url, postParams);
        return Json2Object(src, cl, asArray);
    }

}
