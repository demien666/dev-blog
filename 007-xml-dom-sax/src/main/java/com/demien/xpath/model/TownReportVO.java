package com.demien.xpath.model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: dkovalsky
 * Date: 4/20/12
 * Time: 3:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class TownReportVO {
    private String index;
    private String sname;
    private String latitude;
    private String longitude;
    private ArrayList<DayReportVO> dayReports = new ArrayList<DayReportVO>();

    // constructor
    public TownReportVO() {

    }

    // getters and setters
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    // implementation
    public void addDayReport(DayReportVO dayReportVO) {
        this.dayReports.add(dayReportVO);
    }

    public String toString() {
        String res = "";
        res = "Report for : " + getSname() + "(" + getIndex() + ")";
        if (dayReports != null) {
            Iterator iterator = dayReports.iterator();
            while (iterator.hasNext()) {
                DayReportVO dayReportVO = (DayReportVO) iterator.next();
                res = res + "\n" + dayReportVO.toString();
            }
        }
        return res;
    }

    public static TownReportVO getTemplate() {
        TownReportVO TEMPLATE = new TownReportVO();
        TEMPLATE.setIndex("index");
        TEMPLATE.setSname("sname");
        TEMPLATE.setLatitude("latitude");
        TEMPLATE.setLongitude("longtitude");
        return TEMPLATE;
    }

    public void refresh() {
        Iterator iterator = dayReports.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            DayReportVO dayReportVO = (DayReportVO) iterator.next();

        }
    }

}
