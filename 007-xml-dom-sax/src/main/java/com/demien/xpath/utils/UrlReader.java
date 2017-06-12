package com.demien.xpath.utils;

/**
 * Created by IntelliJ IDEA.
 * User: dkovalsky
 * Date: 4/20/12
 * Time: 12:51 PM
 * To change this template use File | Settings | File Templates.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class UrlReader {

    public static String getData(String link) {
        String res = "";

        try {
            URL url = new URL(link);
            URLConnection urlConnection = url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuffer theText = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null) {
                theText.append(line + "\n");
            }
            res = theText.toString();

        } catch (Exception e) {
            //res=e.getMessage()+e.toString();
            e.printStackTrace();
        }
        return res;
    }
}
