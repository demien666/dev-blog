package com.demien.xpath.parser;

import com.demien.xpath.model.DayReportVO;
import com.demien.xpath.model.TownReportVO;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;

public class DomParser implements IParser {

    private String TOWN_PATH = "/MMWEATHER/REPORT/TOWN";
    private String FORECAST = "FORECAST";

    private TownReportVO townReport = null;
    private DayReportVO dayReport = null;

    private DayReportVO DAY_TEMPLATE;
    private TownReportVO TOWN_TEMPLATE;

    // constructor
    public DomParser() {
        DAY_TEMPLATE = DayReportVO.getTemplate();
        TOWN_TEMPLATE = TownReportVO.getTemplate();
    }

    // implementation
    private String getNodeAttribute(NamedNodeMap attributes, String attribute) throws Exception{
        if (attributes==null) throw new Exception("Attribute map is null(key="+attribute+")");
        if (attribute==null) throw new Exception("Attribute key is null");
        Node node = attributes.getNamedItem(attribute);
        return node.getNodeValue();
    }

    public TownReportVO parse(String data) {
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            builderFactory.setNamespaceAware(true);
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            InputSource source = new InputSource(new StringReader(data));
            Document document=builder.parse(source);

            townReport = new TownReportVO();

            XPath xpath = XPathFactory.newInstance().newXPath();

            //-----------------------
            //--  get town data
            //------------------------
            String expression = TOWN_PATH;
            Node townNode = (Node) xpath.evaluate(expression, document, XPathConstants.NODE);
            NamedNodeMap attributes = townNode.getAttributes();
            townReport.setIndex(getNodeAttribute(attributes, TOWN_TEMPLATE.getIndex()));
            townReport.setSname(getNodeAttribute(attributes, TOWN_TEMPLATE.getSname()));


            // ------------------------
            //-- get days data
            //-------------------------
            NodeList nlForecast = document.getElementsByTagName(FORECAST);
            for (int i = 0; i < nlForecast.getLength(); i++) {
                dayReport = new DayReportVO();
                Node forecastNode = nlForecast.item(i);
                NamedNodeMap forecastAttr = forecastNode.getAttributes();
                dayReport.setForecastDay(getNodeAttribute(forecastAttr, DAY_TEMPLATE.getForecastDay()));
                dayReport.setForecastHour(getNodeAttribute(forecastAttr, DAY_TEMPLATE.getForecastHour()));
                dayReport.setForecastMonth(getNodeAttribute(forecastAttr, DAY_TEMPLATE.getForecastMonth()));
                dayReport.setForecastYear(getNodeAttribute(forecastAttr, DAY_TEMPLATE.getForecastYear()));
                dayReport.setForecastWeekday(getNodeAttribute(forecastAttr, DAY_TEMPLATE.getForecastWeekday()));

                NodeList dayItems= forecastNode.getChildNodes();

                // phenomena
                Node phenomenaNode=dayItems.item(1);
                NamedNodeMap phenomenaAttr = phenomenaNode.getAttributes();
                dayReport.setPhenomenaCloudiness(getNodeAttribute(phenomenaAttr,DAY_TEMPLATE.getPhenomenaCloudiness()));
                dayReport.setPhenomenaPrecipitation(getNodeAttribute(phenomenaAttr,DAY_TEMPLATE.getPhenomenaPrecipitation()));
                dayReport.setPhenomenaRpower(getNodeAttribute(phenomenaAttr,DAY_TEMPLATE.getPhenomenaRpower()));
                dayReport.setPhenomenaSpower(getNodeAttribute(phenomenaAttr,DAY_TEMPLATE.getPhenomenaSpower()));

                // pressure
                Node pressureNode=phenomenaNode.getNextSibling();
                pressureNode=pressureNode.getNextSibling();
                NamedNodeMap pressureAttr = pressureNode.getAttributes();
                dayReport.setPressureMin(getNodeAttribute(pressureAttr,DAY_TEMPLATE.getPressureMin()));
                dayReport.setPressureMax(getNodeAttribute(pressureAttr,DAY_TEMPLATE.getPressureMax()));

                //temperature
                Node temperatureNode=pressureNode.getNextSibling();
                temperatureNode=temperatureNode.getNextSibling();
                NamedNodeMap temperatureAttr=temperatureNode.getAttributes();
                dayReport.setTemperatureMax(getNodeAttribute(temperatureAttr,DAY_TEMPLATE.getTemperatureMax()));
                dayReport.setTemperatureMin(getNodeAttribute(temperatureAttr,DAY_TEMPLATE.getTemperatureMin()));

                // wind
                Node windNode=temperatureNode.getNextSibling();
                windNode=windNode.getNextSibling();
                NamedNodeMap windAttr=windNode.getAttributes();
                dayReport.setWindMax(getNodeAttribute(temperatureAttr,DAY_TEMPLATE.getWindMax()));
                dayReport.setWindMin(getNodeAttribute(temperatureAttr,DAY_TEMPLATE.getWindMin()));
                
                //relwet
                Node relwetNode=windNode.getNextSibling();
                relwetNode=relwetNode.getNextSibling();
                NamedNodeMap relwetAttr=relwetNode.getAttributes();
                dayReport.setRelwetMax(getNodeAttribute(temperatureAttr,DAY_TEMPLATE.getRelwetMax()));
                dayReport.setRelwetMin(getNodeAttribute(temperatureAttr,DAY_TEMPLATE.getRelwetMin()));

                //heat
                Node heatNode=relwetNode.getNextSibling();
                heatNode=heatNode.getNextSibling();
                NamedNodeMap heatAttr=heatNode.getAttributes();
                dayReport.setHeatMax(getNodeAttribute(temperatureAttr,DAY_TEMPLATE.getHeatMax()));
                dayReport.setHeatMin(getNodeAttribute(temperatureAttr,DAY_TEMPLATE.getHeatMin()));

                townReport.addDayReport(dayReport);

            }  // for

        } catch (Exception e) {
            e.printStackTrace();
        }

        return townReport;

    }
}
