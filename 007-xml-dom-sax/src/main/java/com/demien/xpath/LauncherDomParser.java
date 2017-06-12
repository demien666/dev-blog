package com.demien.xpath;

import com.demien.xpath.model.TownReportVO;
import com.demien.xpath.parser.DomParser;
import com.demien.xpath.parser.IParser;
import com.demien.xpath.utils.Debug;
import com.demien.xpath.utils.UrlReader;

/**
 * Created by IntelliJ IDEA.
 * User: dkovalsky
 * Date: 4/20/12
 * Time: 12:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class LauncherDomParser {
    private final static String WEATHER_URL="http://informer.gismeteo.ru/xml/27612_1.xml";
    //private final static String WEATHER_URL = "http://localhost:8080/cafe/weather.xml";

    public static void main(String args[]) {
        IParser parser = new DomParser();
        TownReportVO report=parser.parse(UrlReader.getData(WEATHER_URL));
        Debug.message(report.toString());
    }
}
