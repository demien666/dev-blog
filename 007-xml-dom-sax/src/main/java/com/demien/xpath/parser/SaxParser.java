package com.demien.xpath.parser;

import com.demien.xpath.model.DayReportVO;
import com.demien.xpath.model.TownReportVO;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Iterator;


public class SaxParser implements IParser {
    private String TOWN = "TOWN";
    private String FORECAST = "FORECAST";
    private String PHENOMENA = "PHENOMENA";
    private String PRESSURE = "PRESSURE";
    private String TEMPERATURE = "TEMPERATURE";
    private String WIND = "WIND";
    private String RELWET = "RELWET";
    private String HEAT = "HEAT";


    private TownReportVO townReport = null;
    private DayReportVO dayReport = null;

    private DayReportVO DAY_TEMPLATE;
    private TownReportVO TOWN_TEMPLATE;

    public SaxParser() {
        DAY_TEMPLATE = DayReportVO.getTemplate();
        TOWN_TEMPLATE = TownReportVO.getTemplate();
    }

    public TownReportVO parse(String data) {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        InputStream is = new ByteArrayInputStream(data.getBytes());
        XMLEventReader eventReader = null;
        XMLEvent event;

        try {
            eventReader = inputFactory.createXMLEventReader(is);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (eventReader != null) {
            while (eventReader.hasNext()) {
                event = null;
                try {
                    event = eventReader.nextEvent();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (event != null) {
                    if (event.isStartElement()) {
                        StartElement startElement = event.asStartElement();
                        //----------------------------------------------------
                        //-----    TOWN --------------------------------------
                        //-----------------------------------------------------
                        if (startElement.getName().getLocalPart() == (TOWN)) {
                            if (townReport == null) townReport = new TownReportVO();
                            Iterator<Attribute> attributes = startElement
                                    .getAttributes();
                            while (attributes.hasNext()) {
                                Attribute attribute = attributes.next();
                                if (attribute.getName().toString().equals(TOWN_TEMPLATE.getIndex())) {
                                    townReport.setIndex(attribute.getValue());
                                }
                                if (attribute.getName().toString().equals(TOWN_TEMPLATE.getSname())) {
                                    townReport.setSname(attribute.getValue());
                                }
                                if (attribute.getName().toString().equals(TOWN_TEMPLATE.getLatitude())) {
                                    townReport.setLatitude(attribute.getValue());
                                }
                                if (attribute.getName().toString().equals(TOWN_TEMPLATE.getLongitude())) {
                                    townReport.setLongitude(attribute.getValue());
                                }
                            } // while (attributes.hasNext()) {
                        } // if (startElement.getName().getLocalPart() == (TOWN)) {
                        //--------------------------------------------------------
                        //-----    FORECAST --------------------------------------
                        //--------------------------------------------------------
                        if (startElement.getName().getLocalPart() == (FORECAST)) {
                            if (dayReport == null) {
                                dayReport = new DayReportVO();
                            } else { // new element
                                if (townReport == null) townReport = new TownReportVO();
                                townReport.addDayReport(dayReport);
                                dayReport = new DayReportVO();
                            }
                            ;
                            Iterator<Attribute> attributes = startElement
                                    .getAttributes();
                            while (attributes.hasNext()) {
                                Attribute attribute = attributes.next();
                                if (attribute.getName().toString().equals(DAY_TEMPLATE.getForecastDay())) {
                                    dayReport.setForecastDay(attribute.getValue());
                                }
                                if (attribute.getName().toString().equals(DAY_TEMPLATE.getForecastMonth())) {
                                    dayReport.setForecastMonth(attribute.getValue());
                                }
                                if (attribute.getName().toString().equals(DAY_TEMPLATE.getForecastYear())) {
                                    dayReport.setForecastYear(attribute.getValue());
                                }
                                if (attribute.getName().toString().equals(DAY_TEMPLATE.getForecastHour())) {
                                    dayReport.setForecastHour(attribute.getValue());
                                }
                                if (attribute.getName().toString().equals(DAY_TEMPLATE.getForecastTod())) {
                                    dayReport.setForecastTod(attribute.getValue());
                                }
                                if (attribute.getName().toString().equals(DAY_TEMPLATE.getForecastWeekday())) {
                                    dayReport.setForecastWeekday(attribute.getValue());
                                }
                            } // while (attributes.hasNext()) {
                        } // if (startElement.getName().getLocalPart() == (FORECAST)) {

                        //-----------------------------------------------------------------------------------
                        //---- PHENOMENA --------------------------------------------------------------------
                        //-----------------------------------------------------------------------------------
                        if (startElement.getName().getLocalPart() == (PHENOMENA)) {
                            if (dayReport == null) {
                                dayReport = new DayReportVO();
                            }
                            ;
                            Iterator<Attribute> attributes = startElement
                                    .getAttributes();
                            while (attributes.hasNext()) {
                                Attribute attribute = attributes.next();
                                if (attribute.getName().toString().equals(DAY_TEMPLATE.getPhenomenaCloudiness())) {
                                    dayReport.setPhenomenaCloudiness(attribute.getValue());
                                }
                                if (attribute.getName().toString().equals(DAY_TEMPLATE.getPhenomenaPrecipitation())) {
                                    dayReport.setPhenomenaPrecipitation(attribute.getValue());
                                }
                                if (attribute.getName().toString().equals(DAY_TEMPLATE.getPhenomenaRpower())) {
                                    dayReport.setPhenomenaRpower(attribute.getValue());
                                }
                                if (attribute.getName().toString().equals(DAY_TEMPLATE.getPhenomenaSpower())) {
                                    dayReport.setPhenomenaSpower(attribute.getValue());
                                }
                            } // while (attributes.hasNext()) {
                        } // if (startElement.getName().getLocalPart() == (PHENOMENA)) {

                        //-----------------------------------------------------------------------------------
                        //---- PRESSURE --------------------------------------------------------------------
                        //-----------------------------------------------------------------------------------
                        if (startElement.getName().getLocalPart() == (PRESSURE)) {
                            if (dayReport == null) {
                                dayReport = new DayReportVO();
                            }
                            ;
                            Iterator<Attribute> attributes = startElement
                                    .getAttributes();
                            while (attributes.hasNext()) {
                                Attribute attribute = attributes.next();
                                if (attribute.getName().toString().equals(DAY_TEMPLATE.getPressureMax())) {
                                    dayReport.setPressureMax(attribute.getValue());
                                }
                                if (attribute.getName().toString().equals(DAY_TEMPLATE.getPressureMin())) {
                                    dayReport.setPressureMin(attribute.getValue());
                                }
                            } // while (attributes.hasNext()) {
                        } // if (startElement.getName().getLocalPart() == (PRESSURE)) {

                        //-----------------------------------------------------------------------------------
                        //---- TEMPERATURE --------------------------------------------------------------------
                        //-----------------------------------------------------------------------------------
                        if (startElement.getName().getLocalPart() == (TEMPERATURE)) {
                            if (dayReport == null) {
                                dayReport = new DayReportVO();
                            }
                            ;
                            Iterator<Attribute> attributes = startElement
                                    .getAttributes();
                            while (attributes.hasNext()) {
                                Attribute attribute = attributes.next();
                                if (attribute.getName().toString().equals(DAY_TEMPLATE.getTemperatureMax())) {
                                    dayReport.setTemperatureMax(attribute.getValue());
                                }
                                if (attribute.getName().toString().equals(DAY_TEMPLATE.getTemperatureMin())) {
                                    dayReport.setTemperatureMin(attribute.getValue());
                                }
                            } // while (attributes.hasNext()) {
                        } // if (startElement.getName().getLocalPart() == (TEMPERATURE)) {

                        //-----------------------------------------------------------------------------------
                        //---- WIND --------------------------------------------------------------------
                        //-----------------------------------------------------------------------------------
                        if (startElement.getName().getLocalPart() == (WIND)) {
                            if (dayReport == null) {
                                dayReport = new DayReportVO();
                            }
                            ;
                            Iterator<Attribute> attributes = startElement
                                    .getAttributes();
                            while (attributes.hasNext()) {
                                Attribute attribute = attributes.next();
                                if (attribute.getName().toString().equals(DAY_TEMPLATE.getWindMax())) {
                                    dayReport.setWindMax(attribute.getValue());
                                }
                                if (attribute.getName().toString().equals(DAY_TEMPLATE.getWindMin())) {
                                    dayReport.setWindMin(attribute.getValue());
                                }
                                if (attribute.getName().toString().equals(DAY_TEMPLATE.getWindDirection())) {
                                    dayReport.setWindDirection(attribute.getValue());
                                }
                            } // while (attributes.hasNext()) {
                        } // if (startElement.getName().getLocalPart() == (WIND)) {

                        //-----------------------------------------------------------------------------------
                        //---- RELWET --------------------------------------------------------------------
                        //-----------------------------------------------------------------------------------
                        if (startElement.getName().getLocalPart() == (RELWET)) {
                            if (dayReport == null) {
                                dayReport = new DayReportVO();
                            }
                            ;
                            Iterator<Attribute> attributes = startElement
                                    .getAttributes();
                            while (attributes.hasNext()) {
                                Attribute attribute = attributes.next();
                                if (attribute.getName().toString().equals(DAY_TEMPLATE.getRelwetMax())) {
                                    dayReport.setRelwetMax(attribute.getValue());
                                }
                                if (attribute.getName().toString().equals(DAY_TEMPLATE.getRelwetMin())) {
                                    dayReport.setRelwetMin(attribute.getValue());
                                }
                            } // while (attributes.hasNext()) {
                        } // if (startElement.getName().getLocalPart() == (RELWET)) {

                        //-----------------------------------------------------------------------------------
                        //---- HEAT --------------------------------------------------------------------
                        //-----------------------------------------------------------------------------------
                        if (startElement.getName().getLocalPart() == (HEAT)) {
                            if (dayReport == null) {
                                dayReport = new DayReportVO();
                            }

                            Iterator<Attribute> attributes = startElement
                                    .getAttributes();
                            while (attributes.hasNext()) {
                                Attribute attribute = attributes.next();
                                if (attribute.getName().toString().equals(DAY_TEMPLATE.getHeatMax())) {
                                    dayReport.setHeatMax(attribute.getValue());
                                }
                                if (attribute.getName().toString().equals(DAY_TEMPLATE.getHeatMin())) {
                                    dayReport.setHeatMin(attribute.getValue());
                                }
                            } // while (attributes.hasNext()) {
                        } // if (startElement.getName().getLocalPart() == (HEAT)) {


                    } // if (event.isStartElement()) {
                } //if (event!=null) {

            } // while
            // add final day report
            townReport.addDayReport(dayReport);
        } // if
        //System.out.println(townReport.toString());
        return townReport;
    }

}
